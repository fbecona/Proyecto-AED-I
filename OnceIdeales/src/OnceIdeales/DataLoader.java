package OnceIdeales;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import OnceIdeales.EstructurasDeDatos.TArbolBB;
import OnceIdeales.EstructurasDeDatos.TElementoAB;

/**
 * Esta clase es la responsable de cargar los datos y de generar la estructura.
 * 
 * @author fedec
 */

public class DataLoader{
    
    private final TArbolBB<League> structure = new TArbolBB<>();
    
    private final TArbolBB<Team> teamsData = new TArbolBB<>();
    private final TArbolBB<Player> playersData = new TArbolBB<>();
    private final TArbolBB<MatchStandings> matchStandingsData = new TArbolBB<>();
    private final TArbolBB<Match> matchesData = new TArbolBB<>();
    
    private final String matchesFile;
    private final String matchStandingsFile; 
    private final String teamsFile;
    private final String playersFile;
    private final String eventsFile; 
    
    private final String[] leaguesId; 
    private final String[] seasonsYears; 
   
    /**
     * Crea un DataLoader, carga los archivos y genera la estructura cargada.
     * @param leaguesId - ids de las ligas a cargar
     * @param seasonYears - años de las temporadas a cargar
     * @param matchesFile - archivo de partidos
     * @param matchStandingsFile - archivo de alineaciones de partidos
     * @param teamsFile - archivo de equipos
     * @param playersFile - archivo de jugadores
     * @param eventsFile - archivo de eventos
     * @throws IOException 
     */
    public DataLoader(String[]leaguesId, String[]seasonYears,
            String matchesFile, String matchStandingsFile,
            String teamsFile, String playersFile, 
            String eventsFile) throws IOException{
        this.leaguesId = leaguesId;
        this.seasonsYears = seasonYears;
        this.matchesFile = matchesFile;
        this.matchStandingsFile = matchStandingsFile;
        this.teamsFile = teamsFile;
        this.playersFile = playersFile;
        this.eventsFile = eventsFile;        
        this.setStructure();
        this.loadPlayersData();
        this.loadMatchStandingsData();
        this.loadTeamsData();
        this.loadMatchesData();
        this.loadEventsData();
        this.loadLeague(this.structure.getRaiz());
    }
    
    /**
     * Genera la estructura de árbol de ligas conteniendo árbol de temporadas.
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private void setStructure() throws FileNotFoundException, IOException {
        for(String leagueId : this.leaguesId){
            League league = new League(leagueId);
            TArbolBB<Season> seasons = new TArbolBB<>();
            for(String aYear : this.seasonsYears){
                Season aSeason = new Season(aYear);
                if(aSeason.getMatches()!=null){
                    seasons.insertar(new TElementoAB<>(aSeason.getYear(), aSeason));
                }
            }
            league.setSeasons(seasons);
            this.structure.insertar(new TElementoAB<>(league.getId(), league));
        }
    }
    
    /**
     * Carga el árbol de jugadores leyendo del archivo players.csv.
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private void loadPlayersData() throws FileNotFoundException, IOException{
        FileReader frData = new FileReader(playersFile);
        BufferedReader bfData = new BufferedReader(frData);
        String newLine = bfData.readLine();
        while (newLine!= null){
           String[] data = newLine.split(";");
           Player player = new Player(data[0],data[1]);
           TElementoAB<Player> aPlayer = new TElementoAB<>(player.getId(),player);
           this.playersData.insertar(aPlayer);
           newLine = bfData.readLine();
        }         
    }
    
    /**
     * Carga el árbol de equipos leyendo del archivo teams.csv.
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private void loadTeamsData() throws FileNotFoundException, IOException{
        FileReader frData = new FileReader(teamsFile);
        BufferedReader bfData = new BufferedReader(frData);
        String newLine = bfData.readLine();
        while (newLine!= null){
           String[] data = newLine.split(";"); 
           Team team = new Team(data[0],data[1]);
           TElementoAB<Team> aTeam = new TElementoAB<>(team.getName(),team);
           this.teamsData.insertar(aTeam);
           newLine = bfData.readLine();
        }
    }       
    
    /**
     * Carga el árbol de MatchStandings con las formaciones de partidos leyendo 
     * de match-standings.csv.
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private void loadMatchStandingsData() throws FileNotFoundException, IOException{
        FileReader frData = new FileReader(matchStandingsFile);
        BufferedReader bfData = new BufferedReader(frData);
        String newLine = bfData.readLine();
        while (newLine!= null){
           String[] data = newLine.split(";");
           TArbolBB<Player> homePlayers = new TArbolBB<>();
           TArbolBB<Player> awayPlayers = new TArbolBB<>();
           for(int i=2;i<=23;i++){
               TElementoAB<Player> searchPlayer = this.playersData.buscar(data[i]);
               if(searchPlayer!=null){
                   Player player = new Player(searchPlayer.getDatos().getId(),
                           searchPlayer.getDatos().getName());
                   player.updatePositions(new TElementoAB<>(data[i+22],null));
                   player.addOneMatchMore();
                   TElementoAB<Player> aPlayer = new TElementoAB<>(player.getId(),player);
                   if(i<13){
                        homePlayers.insertar(aPlayer);
                   }else{ 
                        awayPlayers.insertar(aPlayer);
                   }
               }
           }
           MatchStandings matchStandings = new MatchStandings(data[48]+
                   data[0]+ data[1], homePlayers, awayPlayers);
           TElementoAB<MatchStandings> aMatchStandings = new TElementoAB<>
                   (matchStandings.getId(),matchStandings); 
           this.matchStandingsData.insertar(aMatchStandings);
           newLine = bfData.readLine();
        }
    } 
    
    /**
     * Carga los partidos en la estructura leyendo de matches.csv y si 
     * hay algún MatchStanding en el árbol cargado que corresponda 
     * al partido le agrega las formaciones, lo mismo sucede con los 
     * equipos y el árbol de Teams. También es mantienen referencias
     * a los partidos cargados en la estructura en otro árbol
     * para poder cargarles los eventos directamente.
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private void loadMatchesData() throws FileNotFoundException, IOException{
        FileReader frData = new FileReader(matchesFile);
        BufferedReader bfData = new BufferedReader(frData);
        String newLine = bfData.readLine();
        while (newLine!= null){
            String[] data = newLine.split(";"); 
            TElementoAB<Team> aHomeTeam = this.teamsData.buscarWithContains(data[4]);
            TElementoAB<Team> aAwayTeam = this.teamsData.buscarWithContains(data[5]);
            if(aHomeTeam!=null&&aAwayTeam!=null){
                Team homeTeam = new Team(aHomeTeam.getDatos().getId(),aHomeTeam.getDatos().getName());
                Team awayTeam = new Team(aAwayTeam.getDatos().getId(),aAwayTeam.getDatos().getName());
                TElementoAB<MatchStandings> aMatchStandings = this.matchStandingsData.buscar(
                        data[1] + homeTeam.getId() + awayTeam.getId());
                if(aMatchStandings!=null){
                    Match match = new Match(data[0],data[1], homeTeam, awayTeam);
                    MatchStandings matchStandings = aMatchStandings.getDatos();
                    match.setStandings(matchStandings.getHomeTeamPlayers(),
                            matchStandings.getAwayTeamPlayers());
                    this.matchesData.insertar(new TElementoAB<>(match.getId(),match));
                    TElementoAB<League> aLeague = this.structure.buscar(data[2].replace("\"", ""));
                    if(aLeague!=null){
                        TElementoAB<Season> aSeason = aLeague.getDatos().getSeasons().buscar(data[3]);
                        if(aSeason!=null){
                            aSeason.getDatos().addMatch(new TElementoAB<>(match.getId(),match));
                        }
                    }
                }
            }
            newLine = bfData.readLine();
        }
    }  
    
    /**
     * Carga los eventos en los partidos leyendo del archivo events.csv.
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private void loadEventsData() throws FileNotFoundException, IOException{
        FileReader frData = new FileReader(eventsFile);
        BufferedReader bfData = new BufferedReader(frData);
        String newLine = bfData.readLine();
        while (newLine!= null){
            String[] data = newLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); 
            Event event = new Event(data[0],data[1],data[2],data[3],data[4],
                    data[5],data[6], data[7], data[8], data[9],data[10], 
                    data[11], data[12],data[13], data[14], data[15], data[16],
                    data[17], data[18], data[19],data[20]);
            TElementoAB<Match> matchOfTheEvent = this.matchesData.buscar(event.getMatchId());
            if(matchOfTheEvent!=null){
                Match match = matchOfTheEvent.getDatos();
                match.addEvent(new TElementoAB<>(event.getEventId(),event));            
            }
            newLine = bfData.readLine();
        }
    }

    /**
     * Se procesa una liga de la estructua luego de que esta ya
     * contenga toda los datos necesarios.
     * @param aLeague liga a cargar.
     */
    private void loadLeague(TElementoAB<League> aLeague) {
        League league = aLeague.getDatos();
        this.loadSeason(league.getSeasons().getRaiz());
        if(aLeague.getHijoIzq()!=null){
            this.loadLeague(aLeague.getHijoIzq());
        }
        if(aLeague.getHijoDer()!=null){
            this.loadLeague(aLeague.getHijoDer());
        }
    }
    
    /**
     * Se procesa una temporada de la estructura luego de que esta ya
     * contenga toda los datos necesarios.
     * @param aSeason temporada a cargar.
     */
    private void loadSeason(TElementoAB<Season> aSeason) {
        Season season = aSeason.getDatos();
        if(season.getMatches().getRaiz()!=null){
            season.loadMatches(season.getMatches().getRaiz());
        }
        if(aSeason.getHijoIzq()!=null){
            this.loadSeason(aSeason.getHijoIzq());
        }
        if(aSeason.getHijoDer()!=null){
            this.loadSeason(aSeason.getHijoDer());
        }
    }
    
    /**
     * Devuelve la estructura cargada.
     * @return estructura cargada.
     */
    public TArbolBB<League> getStructure(){
        return this.structure;
    }    
    
    /**
     * Devuelve las ligas cargadas en la estructura.
     * @return ligas cargadas en la estructura.
     */
    public String[] getLeaguesId(){
        return this.leaguesId;
    }

    /**
     * Devuelve las temporadas cargadas en la estructura.
     * @return temporadas cargadas en la estructura.
     */
    public String[] getSeasonsYears(){
        return this.seasonsYears;
    }
}