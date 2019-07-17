package OnceIdeales;

import OnceIdeales.EstructurasDeDatos.TArbolBB;
import OnceIdeales.EstructurasDeDatos.TElementoAB;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author federico
 */
public class MatchTest {
    
    Match match;
    
    public MatchTest() {
        
        //Liverpool vs Tottenham
    
        Team liverpool = new Team("reds", "Liverpool");
        TArbolBB liverpoolPlayers = new TArbolBB();        
        Player salah = new Player("11", "Salah");
        liverpoolPlayers.insertar(new TElementoAB("11", salah));
        salah.updatePositions(new TElementoAB<>("DD",null));     
        liverpool.setPlayers(liverpoolPlayers);    
        
        Team tottenham = new Team("spurs", "Tottenham");        
        TArbolBB tottenhamPlayers = new TArbolBB();        
        Player lloris = new Player("1", "Lloris");
        tottenhamPlayers.insertar(new TElementoAB("1", lloris));
        lloris.updatePositions(new TElementoAB<>("A",null));        
        tottenham.setPlayers(tottenhamPlayers);    
        
        this.match = new Match("Final champions 2019", "1/6/2019", liverpool, tottenham);
        
        TElementoAB<Event> gol = new TElementoAB<>("1",
        new Event("", "", "", "", "", "", "", "", "", "", "Salah", "Dominguez", "", "", "", "", "1", "", "", "", ""));
        this.match.addEvent(gol);
    }

    /**
     * Test of setEventsInPlayers method, of class Match.
     */
    @Test
    public void setEventsInPlayersAndTeamsGoals_Event() {
        this.match.setEventsInPlayersAndTeamsGoals();
        Player salah = this.match.getHomeTeam().getPlayers().buscar("11").getDatos();
        assertEquals(1, salah.getGoals());
    }

    /**
     * Test of setEventsInPlayers method, of class Match.
     */    
    @Test
    public void testsetEventsInPlayersAndTeamsGoals_GoalsAgainst() {
        this.match.setEventsInPlayersAndTeamsGoals();
        Player lloris = this.match.getAwayTeam().getPlayers().buscar("1").getDatos();
        assertEquals(1, lloris.getGoalsAgainst());
    }

    /**
     * Test of setEventsInPlayers method, of class Match.
     */    
    @Test
    public void testSetEventsInPlayersAndTeamsGoals_HomeTeamGoalsMade() {
        this.match.setEventsInPlayersAndTeamsGoals();
        assertEquals(1, this.match.getHomeTeam().getGoalsMade());
    }

    /**
     * Test of setEventsInPlayers method, of class Match.
     */
    @Test
    public void testSetEventsInPlayersAndTeamsGoals_AwayTeamGoalsMade() {
        this.match.setEventsInPlayersAndTeamsGoals();
        assertEquals(0, this.match.getAwayTeam().getGoalsMade());
    }
}
