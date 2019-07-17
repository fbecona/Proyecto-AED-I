package OnceIdeales;

import OnceIdeales.EstructurasDeDatos.TElementoAB;
import OnceIdeales.EstructurasDeDatos.TArbolBB;

/**
 * Esta clase representa un partido.
 * @author fedec
 */

public class Match {
    
    private final String id;
    
    private final String date;
    
    private final Team  homeTeam;
    
    private final Team awayTeam;
    
    private final TArbolBB<Event> events;
      
    /**
     * Crea un partido.
     * @param id id del partido.
     * @param date fecha del partido.
     * @param homeTeam equipo local del partido.
     * @param awayTeam equipo visitante del partido.
     */
    public Match(String id, String date, Team homeTeam, Team awayTeam) {
        this.id = id;
        this.date = date;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.events = new TArbolBB<>();
    }

    /**
     * Carga los jugadores locales y visitantes.
     * @param homePlayers jugadores locales.
     * @param awayPlayers jugadores visitantes.
     */
    public void setStandings(TArbolBB<Player> homePlayers, TArbolBB<Player> awayPlayers) {
        this.homeTeam.setPlayers(homePlayers);
        this.awayTeam.setPlayers(awayPlayers);
    }   
    
    /**
     * Añade un evento al partido
     * @param aEvent un evento.
     */
    public void addEvent(TElementoAB<Event> aEvent) {
        this.events.insertar(aEvent);
    }    
   
    /**
     * Carga los eventos en los jugadores.
     */
    public void setEventsInPlayersAndTeamsGoals(){ 
        TArbolBB<Player> homePlayers = this.homeTeam.getPlayers();
        TArbolBB<Player> awayPlayers = this.awayTeam.getPlayers();
        if(homePlayers.getRaiz()!=null && awayPlayers.getRaiz()!=null){
            if(this.events.getRaiz()!=null){
                this.setPlayersEvents(this.events.getRaiz(),homePlayers.getRaiz());
                this.setPlayersEvents(this.events.getRaiz(),awayPlayers.getRaiz());
            }
            int homeGoals = this.getPlayersGoals(homePlayers.getRaiz());
            int awayGoals = this.getPlayersGoals(awayPlayers.getRaiz());
            this.homeTeam.setGoals(homeGoals, awayGoals);
            this.awayTeam.setGoals(awayGoals,homeGoals);
            this.setGoalsAgainstPlayers(homePlayers.getRaiz(),awayGoals);
            this.setGoalsAgainstPlayers(awayPlayers.getRaiz(),homeGoals);
        }
        
    }    
    
    /**
     * Carga los eventos en los jugadores salvo los goles recibidos.
     * @param thisPlayers unos jugadores
     */
    private void setPlayersEvents(TElementoAB<Event> aEvent, TElementoAB<Player> aPlayer) {
        this.loadEventInPlayers(aEvent, aPlayer);
        if(aEvent.getHijoIzq()!=null){
            setPlayersEvents(aEvent.getHijoIzq(), aPlayer);
        }
        if(aEvent.getHijoDer()!=null){
            setPlayersEvents(aEvent.getHijoDer(), aPlayer);
        }
    }
    
    /**
     * Carga un evento en los jugadores.
     * @param aEvent eventos a cargar
     * @param aPlayer jugadores en los que se van a cargar los eventos
     */
    private void loadEventInPlayers(TElementoAB<Event> aEvent, TElementoAB<Player> aPlayer) {
        Event event = aEvent.getDatos();
        Player player = aPlayer.getDatos();
        if((player.getName()).equals(event.getPlayer())){
            if("1".equals(event.getIsGoal())){
                player.addGoals(1);
            }
            if("3".equals(event.getEventType1())){
                player.addFouls(1);
            }
            if("4".equals(event.getEventType1())||
                    "5".equals(event.getEventType1())||
                    "6".equals(event.getEventType1())){
                player.addCards(1);
            }
        }        
        if(aPlayer.getHijoIzq()!=null){
            loadEventInPlayers(aEvent, aPlayer.getHijoIzq());
        }
        if(aPlayer.getHijoDer()!=null){
            loadEventInPlayers(aEvent, aPlayer.getHijoDer());
        }
    }    
    
    /**
     * Dada unos jugadores devulve los goles que hicieron entre todos.
     * @param players jugadores.
     * @return goles que hicieron los jugadores.
     */    
    private int getPlayersGoals(TElementoAB<Player> aPlayer) {
        Player player = aPlayer.getDatos();
        int playerGoals = player.getGoals();
        int leftGoals = 0;
        int rightGoals = 0;
        if(aPlayer.getHijoIzq()!=null){
            leftGoals = getPlayersGoals(aPlayer.getHijoIzq());
        }
        if(aPlayer.getHijoDer()!=null){
            rightGoals = getPlayersGoals(aPlayer.getHijoDer());
        }
        return playerGoals + leftGoals + rightGoals;
    }    
    
    
    /**
     * Carga los goles en contra a los jugadores.
     * @param players jugadores dentro de los que se encuentran aquellos a los
     * que se les quiere fijar los goles que le metieron al equipo.
     * @param goals goles que le hicieron al equipo en el partido.
     */
    private void setGoalsAgainstPlayers(TElementoAB<Player> aPlayer, int goals) {
        aPlayer.getDatos().addGoalAgainst(goals);
        if(aPlayer.getHijoIzq()!=null){
            setGoalsAgainstPlayers(aPlayer.getHijoIzq(), goals);
        }
        if(aPlayer.getHijoDer()!=null){
            setGoalsAgainstPlayers(aPlayer.getHijoDer(), goals);
        }
    }    

    /**
     * Devuelve el id del partido.
     * @return id del partido.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Devuelve la fecha del partido.
     * @return fecha del partido.
     */
    public String getDate() {
        return this.date;
    }
    
    /**
     * Devuelve el equipo local.
     * @return equipo local.
     */
    public Team getHomeTeam() {
        return this.homeTeam;
    }
    
    /**
     * Devuelve el equipo visitante.
     * @return equipo visitante.
     */
    public Team  getAwayTeam() {
        return this.awayTeam;
    }
    
    /**
     * Devuelve una el árbol con los eventos del partido.
     * @return eventos del partido.
     */
    public TArbolBB<Event> getEvents() {
        return this.events;
    }
}
