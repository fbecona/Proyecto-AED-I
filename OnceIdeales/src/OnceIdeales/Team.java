package OnceIdeales;

import OnceIdeales.EstructurasDeDatos.TElementoAB;
import OnceIdeales.EstructurasDeDatos.TArbolBB;

/**
 * Esta clase representa un equipo.
 * 
 * @author fedec
 */

public class Team {

    private final String id;
    
    private final String name;
    
    private int goalsMade;
    
    private int goalsReceived;
    
    private TArbolBB<Player> players;
    
    /**
     * Crea un equipo.
     * @param id id del equipo.
     * @param name nombre del equipo.
     */
    public Team(String id, String name) {
        this.id = id;
        this.name = name;
        this.players = new TArbolBB<>();
    }
    
    /**
     * Carga los jugadores del partido.
     * @param players jugadores a cargar
     */
    public void setPlayers(TArbolBB<Player> players){
        this.players = players;
    }
    
    /**
     * Actualiza las estadísticas del equipo.
     * @param team equipo con estadísticas para actualizar.
     */
    public void updateTeam(Team team) {
        TArbolBB<Player> teamPlayers = team.getPlayers();
        if(teamPlayers.getRaiz()!=null){
            this.updateTeamPlayers(teamPlayers.getRaiz());
        }
        this.goalsMade += team.getGoalsMade();
        this.goalsReceived += team.getGoalsReceived();
    }
    
    /**
     * Añade jugadores al equipo, si ya está dentro actualiza sus 
     * atributos.
     * @param aPlayer jugador del equipo a agregar. 
     */   
    private void updateTeamPlayers(TElementoAB<Player> aPlayer){
        Player player = aPlayer.getDatos();
        TElementoAB<Player> samePlayer = this.players.buscar(aPlayer.getEtiqueta());
        if(samePlayer == null){
            Player playerToAdd = new Player(player.getId(),player.getName());
            playerToAdd.updatePlayer(player);
            this.players.insertar(new TElementoAB<>(playerToAdd.getId(),playerToAdd));
        }else{
            Player theSamePlayer = samePlayer.getDatos();
            theSamePlayer.updatePlayer(player);
        }
        if(aPlayer.getHijoIzq()!=null){
           updateTeamPlayers(aPlayer.getHijoIzq());
        }
        if(aPlayer.getHijoDer()!=null){
           updateTeamPlayers(aPlayer.getHijoDer());
        }
    }
    
    /**
     * Fija los goles hechos y recibidos por el equipo.
     * @param goalsMade goles hechos.
     * @param goalsReceived goles recibidos
     */
    public void setGoals(int goalsMade, int goalsReceived){
        this.goalsMade = goalsMade;
        this.goalsReceived = goalsReceived;
    }
        
    /**
     * Devuelve el id del equipo.
     * @return id del equipo.
     */
    public String getId(){
        return this.id;
    }   
    
    /**
     * Devuelve el nombre del equipo.
     * @return nombre del equipo.
     */
    public String getName(){
        return this.name;
    }    

    /**
     * Devuelve jugadores del equipo.
     * @return jugadores del equipo.
     */
    public TArbolBB<Player> getPlayers(){
        return this.players;
    }    
        
    /**
     * Devuelve los goles hechos por el equipo.
     * @return goles hechos por el equipo.
     */
    public int getGoalsMade(){
        return this.goalsMade;
    }
    
    /**
     * Devuelve los goles recibidos por el equipo.
     * @return goles recibidos por el equipo.
     */
    public int getGoalsReceived(){
        return this.goalsReceived;
    }

}
