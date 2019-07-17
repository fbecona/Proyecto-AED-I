package OnceIdeales;

import OnceIdeales.EstructurasDeDatos.TArbolBB;
import OnceIdeales.EstructurasDeDatos.TElementoAB;
import OnceIdeales.EstructurasDeDatos.Lista;
import OnceIdeales.EstructurasDeDatos.Nodo;

/**
 * Esta clase es la encargada de dada la estructura cargada 
 * armar el 11 ideal de los jugadores de las ligas y de responder
 * a las preguntas.
 *
 * @author fedec
 */
public class Football {

    private final TArbolBB<League> structure;    
    
    private final TArbolBB<Player> allPlayers;    

    private final BestXILista bestPlayers;
    
    private final String[] leaguesId; 

    /**
     * Crea un objeto Football.
     * @param structure estructura completamente cargada
     * @param leaguesLoaded ligas cargadas en la estructura
     */
    public Football(TArbolBB<League> structure, String[]leaguesLoaded) {
        this.structure = structure;
        this.leaguesId = leaguesLoaded;
        this.allPlayers = this.getAllPlayers();
        this.bestPlayers = new BestXILista();
        this.bestPlayers.setBestPlayers(this.allPlayers.inOrdenLista());
    }
    
    //MÉTODOS PARA CONSEGUIR JUGADORES
    
    /**
     * Se devuelve un árbol con los jugadores de una determinada liga.
     * @param league liga de la que se van a cargar jugadores.
     * @return árbol de jugadores resultante.
     */
    public TArbolBB<Player> getPlayersALeague(String league){
        TArbolBB<Player> leaguePlayers = new TArbolBB<>();
        League aLeague = structure.buscar(league).getDatos();
        if(aLeague!=null){
            this.getPlayersSeasons(aLeague.getSeasons().getRaiz(), leaguePlayers);
        }
        return leaguePlayers;
    }        

    /**
     * Añade los jugadores cargados de todas las ligas a un árbol de jugadores,
     * mismo jugador en distintas temporadas o ligas se consideran distinto.
     * Luego de agregegarlos se selecciona el 11 ideal entre todas las ligas y
     * temporadas.
     * @return árbol de jugadores resultante.
     */
    private TArbolBB<Player> getAllPlayers() {
        TArbolBB<Player> players = new TArbolBB<>();
        if (this.structure.getRaiz() != null) {
            this.getPlayersLeagues(this.structure.getRaiz(), players);
        }
        return players;
    }
    
    /**
     * Se añaden al árbol de jugadores aquellos de todas las ligas.
     * @param aLeague liga de la que junto con sus descendientes se van
     * a cargar jugadores.
     * @param players árbol de jugadores resultante.
     */
    public void getPlayersLeagues(TElementoAB<League> aLeague, TArbolBB<Player> players) {
        TArbolBB<Season> loadedSeasons = aLeague.getDatos().getSeasons();
        if (loadedSeasons.getRaiz() != null) {
            this.getPlayersSeasons(loadedSeasons.getRaiz(), players);
        }
        if (aLeague.getHijoIzq() != null) {
            getPlayersLeagues(aLeague.getHijoIzq(), players);
        }
        if (aLeague.getHijoDer() != null) {
            getPlayersLeagues(aLeague.getHijoDer(), players);
        }
    }    
        
    /**
     * Se devuelve un árbol con todos los jugadores de una temporada entre
     * todas las ligas.
     * @param season temporada de la que se van a cargar jugadores.
     * @return árbol de jugadores resultante.
     */
    public TArbolBB<Player> getPlayersASeason(String season){
        TArbolBB<Player> seasonPlayers = new TArbolBB<>();
        for(String league: this.leaguesId){
            League aLeague = structure.buscar(league).getDatos();
            TElementoAB<Season> aSeason = aLeague.getSeasons().buscar(season);
            if(aSeason!=null){
                this.getPlayersMatches(aSeason.getDatos().getMatches().getRaiz(),seasonPlayers);
            }
        }
        return seasonPlayers;
    }     
    
    /**
     * Se añaden al árbol de jugadores aquellos de todas las temporadas
     * dentro de una liga.
     * @param aSeason temporada de la que junto con sus descendientes se van
     * a cargar jugadores.
     * @param players átbol de jugadores resultante.
     */
    public void getPlayersSeasons(TElementoAB<Season> aSeason, TArbolBB<Player> players) {
        TArbolBB<Match> loadedMatches = aSeason.getDatos().getMatches();
        if (loadedMatches.getRaiz()!= null) {
            this.getPlayersMatches(loadedMatches.getRaiz(), players);
        }
        if (aSeason.getHijoIzq() != null) {
            getPlayersSeasons(aSeason.getHijoIzq(), players);
        }
        if (aSeason.getHijoDer() != null) {
            getPlayersSeasons(aSeason.getHijoDer(), players);
        }
    }    

    /**
     * Se añaden al árbol de jugadores aquellos que pertenecen a los equipos
     * de un árbol de equipos.
     * @param aMatch partido cuyos jugadores se quieren cargar.
     * @param players árbol de jugadores resultante.
     */
    public void getPlayersMatches(TElementoAB<Match> aMatch, TArbolBB<Player> players) {
        TArbolBB<Player> homePlayers = aMatch.getDatos().getHomeTeam().getPlayers();
        if (homePlayers.getRaiz() != null) {
            this.addPlayers(homePlayers.getRaiz(), players);
        }
        TArbolBB<Player> awayPlayers = aMatch.getDatos().getAwayTeam().getPlayers();
        if (awayPlayers.getRaiz() != null) {
            this.addPlayers(awayPlayers.getRaiz(), players);
        }
        if (aMatch.getHijoIzq() != null) {
            getPlayersMatches(aMatch.getHijoIzq(), players);
        }
        if (aMatch.getHijoDer() != null) {
            getPlayersMatches(aMatch.getHijoDer(), players);
        }
    }    
    
    /**
     * Se añaden al árbol de jugadores aquellos que pertenecen a otro árbol
     * de jugadores teniendo en cuenta de que si ya hay un jugador con esa
     * etiqueta se actualiza.
     * @param aPlayer jugador que junto a sus descendientes se va a añadir
     * al árbol.
     * @param players árbol de jugadores resultante.
     */
    public void addPlayers(TElementoAB<Player> aPlayer, TArbolBB<Player> players) {
        Player player = aPlayer.getDatos();
        TElementoAB<Player> samePlayer = players.buscar(aPlayer.getEtiqueta());
        if(samePlayer == null){
            Player playerToAdd = new Player(player.getId(),player.getName());
            playerToAdd.updatePlayer(player);
            players.insertar(new TElementoAB<>(playerToAdd.getId(),playerToAdd));
        }else{
            Player theSamePlayer = samePlayer.getDatos();
            theSamePlayer.updatePlayer(player);
        }
        if(aPlayer.getHijoIzq()!=null){
           addPlayers(aPlayer.getHijoIzq(),players);
        }
        if(aPlayer.getHijoDer()!=null){
           addPlayers(aPlayer.getHijoDer(),players);
        }        
    }
    
    //MÉTODOS PARA CONSEGUIR EQUIPOS
    
    /**
     * Se devuelve un árbol con los equipos de una determinada liga.
     * @param league liga de la que se van a cargar los equipos.
     * @return árbol de equipos resultante.
     */
    public TArbolBB<Team> getTeamsALeague(String league){
        TArbolBB<Team> leagueTeams = new TArbolBB<>();
        League aLeague = structure.buscar(league).getDatos();
        if(aLeague!=null){
            this.getTeamSeasons(aLeague.getSeasons().getRaiz(), leagueTeams);
        }
        return leagueTeams;
    }    
    
    /**
     * Añade los equipos cargados de todas las ligas a un árbol de jugadores,
     * mismo jugador en distintas temporadas o ligas se consideran distinto.
     * Luego de agregegarlos se selecciona el 11 ideal entre todas las ligas y
     * temporadas.
     * @return 
     */
    private TArbolBB<Team> getAllTeams() {
        TArbolBB<Team> allTeams = new TArbolBB<>();
        if (this.structure.getRaiz() != null) {
            this.getTeamsLeagues(this.structure.getRaiz(), allTeams);
        }
        return allTeams;
    }
        
   /**
     * Se añaden al árbol de equipos aquellos de todas las temporadas
     * dentro de una liga.
     * @param aLeague liga a de la que junto con sus descendientes se van
     * a cargar equipos.
     * @param teams árbol de equipos resultante. 
     */    
    public void getTeamsLeagues(TElementoAB<League> aLeague, TArbolBB<Team> teams) {
        this.getTeamSeasons(aLeague.getDatos().getSeasons().getRaiz(), teams);
        if (aLeague.getHijoIzq() != null) {
            getTeamsLeagues(aLeague.getHijoIzq(), teams);
        }
        if (aLeague.getHijoDer() != null) {
            getTeamsLeagues(aLeague.getHijoDer(), teams);
        }
    }    
    
    /**
     * Se devuelve un árbol con todos los equipos de una temporada entre 
     * todas las ligas
     * @param season temporada de la que se van a cargar equipos.
     * @return árbol de equipos resultante.
     */
    public TArbolBB<Team> getTeamsASeason(String season){
        TArbolBB<Team> seasonTeams = new TArbolBB<>();
        for(String league: this.leaguesId){
            League aLeague = structure.buscar(league).getDatos();
            TElementoAB<Season> aSeason = aLeague.getSeasons().buscar(season);
            if(aSeason!=null){
                this.getTeamsMatches(aSeason.getDatos().getMatches().getRaiz(), seasonTeams);
            }
        }
        return seasonTeams;
    }
    
   /**
     * Se añaden al árbol de equipos aquellos de todas las temporadas
     * dentro de una liga.
     * @param aSeason temporada de la que junto con sus descendientes se van
     * a cargar equipos.
     * @param teams árbol de equipos resultante. 
     */    
    public void getTeamSeasons(TElementoAB<Season> aSeason, TArbolBB<Team> teams) {
        this.getTeamsMatches(aSeason.getDatos().getMatches().getRaiz(), teams);
        if (aSeason.getHijoIzq() != null) {
            getTeamSeasons(aSeason.getHijoIzq(), teams);
        }
        if (aSeason.getHijoDer() != null) {
            getTeamSeasons(aSeason.getHijoDer(), teams);
        }
    }    
    
    /**
     * Se añaden al árbol los equipos locales y visitnantes de un partido.
     * @param aMatch partido cuyos equipos se quieren añadir.
     * @param teams árbol de equipos resultante.
     */
    public void getTeamsMatches(TElementoAB<Match> aMatch, TArbolBB<Team> teams){
        Match match = aMatch.getDatos();
        this.addATeam(match.getHomeTeam(),teams);
        this.addATeam(match.getAwayTeam(),teams);
        if(aMatch.getHijoIzq()!=null){
            getTeamsMatches(aMatch.getHijoIzq(),teams);
        }
        if(aMatch.getHijoDer()!=null){
            getTeamsMatches(aMatch.getHijoDer(),teams);
        }
    }
        
    /**
     * Se añaden al árbol de equipos aquellos que pertenecen a otro árbol
     * de equipos teniendo en cuenta de que si ya hay un equipo con esa
     * etiqueta se actualiza.
     * @param team eqiupo que se va a añadir
     * al árbol.
     * @param teams árbol de equipos resultante.
     */
    public void addATeam(Team team, TArbolBB<Team> teams) {
        TElementoAB<Team> sameTeam = teams.buscar(team.getId());
        if(sameTeam==null){
            Team seasonTeam = new Team(team.getId(),team.getName());
            seasonTeam.updateTeam(team);
            teams.insertar(new TElementoAB<>(seasonTeam.getId(),seasonTeam));
        }else{
            Team theSameTeam = sameTeam.getDatos();
            theSameTeam.updateTeam(team);
        }
    }    
    
    /**
     * Devuelve los 11 ideales entre todas las ligas y temporadas.
     * @return el 11 ideal
     */
    public Lista<Player> getBestXI() {
        return this.bestPlayers.getBestXI();
    }       
        
    /**
     * Responde a la primera pregunta.
     * @param season - el año de una temporada
     * @return jugador con más partidos de una temporada
     */        
    public String getPlayerWithMostMatches(String season){
        Player playerWithMostMatches = new Player(null,null);
        String playerLeague = "";
        for(String league: this.leaguesId){
            TArbolBB<Player> players = new TArbolBB<>();
            League aLeague = structure.buscar(league).getDatos();
            TElementoAB<Season> aSeason = aLeague.getSeasons().buscar(season);
            if(aSeason!=null){
                this.getPlayersMatches(aSeason.getDatos().getMatches().getRaiz(),players);
            }
            Lista<Player> seasonPlayers = players.inOrdenLista();
            if(seasonPlayers!=null){
               Nodo<Player> aPlayer = seasonPlayers.getPrimero();
               while(aPlayer!=null){
                    Player player = aPlayer.getDato();
                    if(player.getMatchesPlayed()>=playerWithMostMatches.getMatchesPlayed()){
                        playerWithMostMatches = player;
                        playerLeague = league;
                    }
                    aPlayer = aPlayer.getSiguiente();
                }
            }
        }
        if(playerWithMostMatches.getName()==null){
            return "No hay información de esa temporada";
        }else{
            return playerWithMostMatches.getName() + " con " + 
                       playerWithMostMatches.getMatchesPlayed() + " partidos jugados"
                       + " en la liga " + playerLeague;    
        }
    }
    
    /**
     * Responde a la segunda pregunta.
     * @param season - el año de una temporada
     * @return arquero más efectivo y delantero menos efectivo
     * de una temporada.
     */
     public String getEffectivePlayer(String season){        
        Player gk = new Player(null,null);
        Player st = new Player(null,null);
        Lista<Player> seasonPlayers = this.getPlayersASeason(season).inOrdenLista();
        if(seasonPlayers!=null){
            Nodo<Player> aPlayer = seasonPlayers.getPrimero();
            while(aPlayer!=null){
                Player player = aPlayer.getDato();
                if(player.getPositions().buscarWithContains("A")!=null){
                    if(gk.getMatchesPlayed()==0){
                        gk = player;
                    }else{
                        if(player.isBetterThan("A", gk)){
                            gk = player;
                        }
                    } 
                }
                if(player.getPositions().buscarWithContains("DC")!=null ||
                        player.getPositions().buscarWithContains("DD")!=null ||
                        player.getPositions().buscarWithContains("DI")!=null){
                    if(st.getMatchesPlayed()==0){
                        st = player;
                    }else{
                        if(st.isBetterThan("D", player)){
                            st = player;
                        }
                    }    
                }
                aPlayer = aPlayer.getSiguiente();
            }  
            return gk.getName() + " con "+ gk.getGoalsAgainst() + 
                   " goles en contra en " + gk.getMatchesPlayed() +
                   " partidos y " + st.getName() + " con " + st.getGoals()+ 
                   " goles en " + st.getMatchesPlayed() + " partidos";
        }else{
           return "No hay información de esa temporada";
        }
     }
     
    /**
     * Responde a la tercera pregunta.
     * @param somePlayer - el nombre de un jugador
     * @return todas las ligas en las que participó un jugador.
     */          
     public String getAllLeaguesOfAPlayers(String somePlayer){     
        String leaguesOfPlayer = "Ligas: ";
        if(somePlayer.equals("")){
            return "Por favor ingrese un jugador";
        }
        for(String league: this.leaguesId){
            Lista<Player> leaguePlayers = this.getPlayersALeague(league).inOrdenLista();            
            Nodo<Player> aPlayer = leaguePlayers.getPrimero();
            while(aPlayer!=null){
                Player player = aPlayer.getDato();
                if(player.getName().contains(somePlayer) && !leaguesOfPlayer.contains(league)){
                    leaguesOfPlayer += league + ", ";
                }
                aPlayer = aPlayer.getSiguiente();
            }
        }
        if(leaguesOfPlayer.equals("Ligas: ")){
            return "No hay información de ese jugador";
        }else{
            return leaguesOfPlayer;
        }
     }
     
    /**
     * Responde a la cuarta pregunta.
     * @param season - el año de una temporada.
     * @return equipo con más y menos goles de una temporada.
     */
    public String getTeamGoals(String season){
        Team bestTeam = new Team(null,null);
        Team worstTeam = new Team(null,null);
        Lista<Team> teams = this.getTeamsASeason(season).inOrdenLista();
        if(teams!=null){
            Nodo<Team> aTeam = teams.getPrimero();        
            while(aTeam!=null){
                Team team = aTeam.getDato();
                if(team.getGoalsMade()>=bestTeam.getGoalsMade()){
                    bestTeam = team;
                }
                if(team.getGoalsReceived()>=worstTeam.getGoalsReceived()){
                    worstTeam = team;
                }
                aTeam = aTeam.getSiguiente();
            }
            
        }
        if(bestTeam.getName() == (null)||worstTeam.getName() == (null)){
            return "No se tiene información para esa temporada";
        }else{
            return "Mejor: " + bestTeam.getName() + " con " + bestTeam.getGoalsMade() + 
                    " goles hechos, Peor: " + worstTeam.getName() + " con " + 
                    worstTeam.getGoalsReceived() + " goles recibidos";
        }
    }
}
