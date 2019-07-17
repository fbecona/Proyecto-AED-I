package OnceIdeales;

import OnceIdeales.EstructurasDeDatos.TArbolBB;
import OnceIdeales.EstructurasDeDatos.TElementoAB;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author federico
 */
public class TeamTest {
    
    Team uruguayWithoutOffensive;
    
    Team uruguayOffensive;
    
    public TeamTest() {
        this.uruguayWithoutOffensive = new Team("Defensive", "Uruguay");
        this.uruguayOffensive = new Team("Offensive", "Uruguay");
        
        TArbolBB<Player> uruguayDefensivePlayers = new TArbolBB<>();
        uruguayDefensivePlayers.insertar(new TElementoAB<>("1",new Player("1", "Muslera")));
        uruguayDefensivePlayers.insertar(new TElementoAB<>("3",new Player("3", "Godín")));
        this.uruguayWithoutOffensive.setPlayers(uruguayDefensivePlayers);
        this.uruguayWithoutOffensive.setGoals(5, 10);
        
        TArbolBB<Player> uruguayOffensivePlayers = new TArbolBB<>();
        uruguayOffensivePlayers.insertar(new TElementoAB<>("9",new Player("9", "Suárez")));
        uruguayOffensivePlayers.insertar(new TElementoAB<>("21",new Player("21", "Cavani")));
        this.uruguayOffensive.setPlayers(uruguayOffensivePlayers);
        this.uruguayOffensive.setGoals(20, 5);
    }

    /**
     * Test of updateTeam method, of class Team.
     */
    @Test
    public void testUpdateTeam_Players() {
        this.uruguayWithoutOffensive.updateTeam(uruguayOffensive);
        assertEquals(4, this.uruguayWithoutOffensive.getPlayers().obtenerTamanio());
    }

    /**
     * Test of updateTeam method, of class Team.
     */
    @Test
    public void testUpdateTeam_GoalsMade() {
        this.uruguayWithoutOffensive.updateTeam(uruguayOffensive);
        assertEquals(25, this.uruguayWithoutOffensive.getGoalsMade());
    }

    /**
     * Test of updateTeam method, of class Team.
     */    
    @Test
    public void testUpdateTeam_GoalsReceived() {
        this.uruguayWithoutOffensive.updateTeam(uruguayOffensive);
        assertEquals(15, this.uruguayWithoutOffensive.getGoalsReceived());
    }    
}
