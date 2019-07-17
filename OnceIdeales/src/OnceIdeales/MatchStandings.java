package OnceIdeales;

import OnceIdeales.EstructurasDeDatos.TArbolBB;

/**
 * Esta clase representa un MatchStandings o la formación de un partido.
 * 
 * @author fedec
 */

public class MatchStandings {
    
    private final String id;
    
    private final TArbolBB<Player> homeTeamPlayers;
    
    private final TArbolBB<Player> awayTeamPlayers;
    
    /**
     * Crea un MatchStandings.
     * @param id id del MatchStandings
     * @param homeTeamPlayers jugadores locales del partido.
     * @param awayTeamPlayers jugadores visitantes del partido.
     */
    public MatchStandings(String id, TArbolBB<Player> homeTeamPlayers, TArbolBB<Player> awayTeamPlayers){
        this.id = id;
        this.homeTeamPlayers = homeTeamPlayers;
        this.awayTeamPlayers = awayTeamPlayers;        
    }
    
    /**
     * Devuelve el id del MatchStandings.
     * @return id del MatchStandings.
     */
    public String getId(){
        return this.id;
    }
    
    /**
     * Devuelve una árbol con los jugadores locales del partido.
     * @return jugadores locales del partido.
     */
    public TArbolBB<Player> getHomeTeamPlayers(){
        return this.homeTeamPlayers;
    }
    
    /**
     * Devuelve una árbol con los jugadores visitantes del partido.
     * @return jugadores visitantes del partido.
     */
    public TArbolBB<Player> getAwayTeamPlayers(){
        return this.awayTeamPlayers;
    }
    
}
