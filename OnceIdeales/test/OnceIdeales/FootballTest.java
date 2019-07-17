package OnceIdeales;

import OnceIdeales.EstructurasDeDatos.Lista;
import OnceIdeales.EstructurasDeDatos.TArbolBB;
import OnceIdeales.EstructurasDeDatos.TElementoAB;
import org.junit.Test;
import static org.junit.Assert.*;

public class FootballTest {

    Football football;
    
    public FootballTest() {
        TArbolBB<League> structure = new TArbolBB<>();

        //Partido 1: Real Madrid vs Atlético De Madrid - 2016
    
        Team madrid = new Team("Madrid", "Real Madrid");
        TArbolBB madridPlayers = new TArbolBB();        
        Player ronaldo = new Player("Ronaldo", "Cristiano Ronaldo");
        madridPlayers.insertar(new TElementoAB("7", ronaldo));
        ronaldo.addOneMatchMore();
        ronaldo.addGoals(1);
        ronaldo.updatePositions(new TElementoAB<>("DI",null));     
        madrid.setPlayers(madridPlayers);
        
        Team atletico = new Team("Atletico", "Atletico de Madrid");        
        TArbolBB atleticoPlayers = new TArbolBB();        
        Player oblak = new Player("Oblak", "Jan Oblak");
        atleticoPlayers.insertar(new TElementoAB("13", oblak));
        oblak.addOneMatchMore();
        oblak.addGoalAgainst(1);
        oblak.updatePositions(new TElementoAB<>("A",null));          
        atletico.setPlayers(atleticoPlayers);    
        
        Match match1 = new Match("1", "27/2/2016", madrid, atletico);
        
        //Partido 2: Barcelona vs Valencia - 2019
        
        Team barcelona = new Team("Barcelona", "Barcelona F.C.");        
        TArbolBB barcelonaPlayers = new TArbolBB();        
        Player messi = new Player("Messi", "Lionel Messi");
        barcelonaPlayers.insertar(new TElementoAB("10", messi));
        messi.addOneMatchMore();
        messi.addOneMatchMore(); //Para test de partidos mas jugados
        messi.updatePositions(new TElementoAB<>("DD",null));          
        barcelona.setPlayers(barcelonaPlayers);     
        
        Team valencia = new Team("Valencia", "Valencia C.F.");
        TArbolBB valenciaPlayers = new TArbolBB();        
        Player gameiro = new Player("Gameiro", "Kevin Gameiro");
        valenciaPlayers.insertar(new TElementoAB("9", gameiro));
        gameiro.addOneMatchMore();
        gameiro.updatePositions(new TElementoAB<>("DC",null));     
        valencia.setPlayers(valenciaPlayers);    
        
        Match match2 = new Match("1", "21/7/2019", barcelona, valencia);

        //Partido 3: Bayern Munich vs Borussia Dortmund - 2016
    
        Team bayern = new Team("Bayern", "Bayern Munich");
        TArbolBB bayernPlayers = new TArbolBB();
        Player neuer = new Player("Neuer", "Manuel Neuer");
        bayernPlayers.insertar(new TElementoAB("1", neuer));
        neuer.addOneMatchMore();
        neuer.updatePositions(new TElementoAB<>("A",null));     
        bayern.setPlayers(bayernPlayers);    
        
        Team dortmund = new Team("Dortmund", "Borussia Dortumund");        
        TArbolBB dortumundPlayers = new TArbolBB();     
        Player aubameyang = new Player("Aubameyang","Pierre-Emerick Aubameyang");
        dortumundPlayers.insertar(new TElementoAB("17", aubameyang));
        aubameyang.addOneMatchMore();
        aubameyang.updatePositions(new TElementoAB<>("DC",null));          
        dortmund.setPlayers(dortumundPlayers);    
        
        Match match3 = new Match("1", "5/3/2016", bayern, dortmund);
        
        //Partido 4: Leipzig vs Bayern Leverkusen - 2019
    
        Team leipzig = new Team("Leipzig", "RB. Leipzig");
        TArbolBB leipzigPlayers = new TArbolBB();
        Player werner = new Player("Werner", "Timo Werner");
        leipzigPlayers.insertar(new TElementoAB("9", werner));
        werner.addOneMatchMore();
        werner.updatePositions(new TElementoAB<>("DC",null));     
        leipzig.setPlayers(leipzigPlayers);    
        
        Team leverkusen = new Team("Leverkusen", "Bayern Leverkusen");        
        TArbolBB leverkusenPlayers = new TArbolBB();     
        Player brandt = new Player("Brandt", "Julian Brandt");
        leverkusenPlayers.insertar(new TElementoAB("10", brandt));
        brandt.addOneMatchMore();
        brandt.updatePositions(new TElementoAB<>("DI",null));          
        leverkusen.setPlayers(leverkusenPlayers);    
        
        Match match4 = new Match("1", "6/4/2019", leipzig, leverkusen);
        
        //Se carga la estructura
        
        String[] leaguesId = new String[]{"SP1","D1"};
        String[] seasonsYears = new String[]{"2016","2019"};
        for(String leagueId : leaguesId){
            League league = new League(leagueId);
            TArbolBB<Season> seasons = new TArbolBB<>();
            for(String aYear : seasonsYears){
                Season aSeason = new Season(aYear);
                seasons.insertar(new TElementoAB<>(aSeason.getYear(), aSeason));
                if(leagueId.equals("SP1")){
                    if(aYear.equals("2016")){
                        aSeason.addMatch(new TElementoAB<>("1",match1));
                    }else{
                        aSeason.addMatch(new TElementoAB<>("2",match2));
                    }
                }else{
                    if(aYear.equals("2016")){
                        aSeason.addMatch(new TElementoAB<>("3",match3));
                    }else{
                        aSeason.addMatch(new TElementoAB<>("4",match4));
                    }
                }
                aSeason.loadMatches(aSeason.getMatches().getRaiz());
            }
            league.setSeasons(seasons);
            structure.insertar(new TElementoAB<>(league.getId(), league));
        }
        football = new Football(structure, leaguesId);
    }
    
    
    /**
     * Test of getPlayersALeague method, of class Football.
     */
    @Test
    public void testGetPlayersALeague() {
        Lista<Player> playersSP1 = football.getPlayersALeague("SP1").inOrdenLista();
        String playersSP1Names = playersSP1.imprimir("-");
        assertEquals("Gameiro-Messi-Oblak-Ronaldo", playersSP1Names);
    }

    /**
     * Test of getTeamsALeague method, of class Football.
     */
    @Test
    public void testGetTeamsALeague() {
        Lista<Team> teamsD1 = football.getTeamsALeague("D1").inOrdenLista();
        String teamsD1Names = teamsD1.imprimir("-");
        assertEquals("Bayern-Dortmund-Leipzig-Leverkusen", teamsD1Names);
    }

    /**
     * Test of getPlayersASeason method, of class Football.
     */
    @Test
    public void testGetPlayersASeason() {
        Lista<Player> players2016 = football.getPlayersASeason("2016").inOrdenLista();
        String players2016Names = players2016.imprimir("-");
        assertEquals("Aubameyang-Neuer-Oblak-Ronaldo", players2016Names);
    }

    /**
     * Test of getTeamsASeason method, of class Football.
     */
    @Test
    public void testGetTeamsASeason() {
        Lista<Team> teams2016 = football.getTeamsASeason("2016").inOrdenLista();
        String teamsD1Names = teams2016.imprimir("-");
        assertEquals("Atletico-Bayern-Dortmund-Madrid", teamsD1Names);
    }

    /**
     * Test of getPlayerWithMostMatches method, of class Football.
     */
    @Test
    public void testGetPlayerWithMostMatches() {
        String result = football.getPlayerWithMostMatches("2019");
        assertEquals("Lionel Messi con 2 partidos jugados "
                + "en la liga SP1", result);
    }    

    /**
     * Test of getEffectivePlayers method, of class Football.
     */
    @Test
    public void testGetEffectivePlayers() {
        String result = football.getEffectivePlayer("2016");
        assertEquals("Manuel Neuer con 0 goles en contra en 1 partidos y "
            + "Pierre-Emerick Aubameyang con 0 goles en 1 partidos", result);
    }
    
    /**
     * Test of getAllLeaguesOfPlayers method, of class Football.
     */
    @Test
    public void testGetAllLeaguesOfPlayers() {
        String result = football.getAllLeaguesOfAPlayers("Cristiano Ronaldo");
        assertEquals("Ligas: SP1, ", result);
    }
    
    /**
     * Test of getTeamGoals method, of class Football.
     */    
    @Test
    public void testGetTeamGoals() {
        String result = football.getTeamGoals("2016");
        assertEquals("Mejor: Real Madrid con 1 goles hechos, Peor: "
                + "Atletico de Madrid con 1 goles recibidos", result);
    }   
    
}
