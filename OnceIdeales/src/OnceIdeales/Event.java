package OnceIdeales;

/**
 * Esta clase genera objetos evento.
 * 
 * @author fedec
 */

public class Event {
    
    private final String matchId,  eventId, sortOrder, time,  text, eventType1, eventType2,  side,  eventTeam, opponentTeam, 
            player, player2, playerIn, playerOut, shotPlace, shotOutcome, isGoal, location, bodyPart, assistMethod, situation;    
    
    /**
     * Crea un evento.
     * @param matchId id del partido en el que ocurrió el evento.
     * @param eventId id del evento.
     * @param sortOrder orden del evento en el partido.
     * @param time momento en el que sucedió el evento.
     * @param text explica lo que pasó en el evento.
     * @param eventType1 -tipo 1 del evento.
     * @param eventType2 tipo 2 del evento.
     * @param side muestra si el evento lo hizo el equipo local o visitante.
     * @param eventTeam equipo que hizo el evento.
     * @param opponentTeam equipo al que le hicieron el evento.
     * @param player primer jugador involucrado en el evento.
     * @param player2 segundo jugador involucrado en el evento.
     * @param playerIn jugador que entra en un cambio.
     * @param playerOut jugador que sale en un cambio.
     * @param shotPlace lugar de tiro.
     * @param shotOutcome resultado del tiro.
     * @param isGoal expresa si hubo gol o no.
     * @param location lugar de la cancha en el que sucedió el evento.
     * @param bodyPart parte del cuerpo con la que se hizo el intento de gol.
     * @param assistMethod forma de asistencia.
     * @param situation situación en la que ocurrió el evento.
     */
    public Event(String matchId, String eventId, String sortOrder, String time, String text, 
            String eventType1, String eventType2, String side, String eventTeam, String opponentTeam,  
            String player, String player2, String playerIn, String playerOut, String shotPlace, 
            String shotOutcome, String isGoal, String location, String bodyPart, String assistMethod, 
            String situation){
        this.matchId = matchId;
        this.eventId = eventId;
        this.sortOrder = sortOrder;
        this.time = time;
        this.text = text;
        this.eventType1 = eventType1;
        this.eventType2 = eventType2;
        this.side = side;
        this.eventTeam = eventTeam;
        this.opponentTeam = opponentTeam;
        this.player = player;
        this.player2 = player2;
        this.playerIn = playerIn;
        this.playerOut = playerOut;
        this.shotPlace = shotPlace;
        this.shotOutcome = shotOutcome;
        this.isGoal = isGoal;
        this.location = location;
        this.bodyPart = bodyPart;
        this.assistMethod = assistMethod;
        this.situation = situation;
    }
    
    /**
     * Devuelve el id del partido.
     * @return id del partido.
     */
    public String getMatchId() {
        return this.matchId;
    }
    
    /**
     * Devuelve el id del evento.
     * @return id del evento.
     */
    public String getEventId() {
        return this.eventId;
    }
    
    /**
     * Devuelve el orden del evento el partido.
     * @return orden del evento en el partido.
     */
    public String getSortOrder() {
        return this.sortOrder;
    }

    /**
     * Devuelve el momento en el que sucedió el evento.
     * @return momento en el que sucedió el evento. 
     */
    public String getTime() {
        return this.time;
    }
    
    /**
     * Devuelve una explicación del evento.
     * @return explicación de lo que sucedió en el evento.
     */
    public String getText() {
        return this.text;
    }
    
    /**
     * Devuelve el tipo1 del evento.
     * @return tipo1 del evento.
     */
    public String getEventType1() {
        return this.eventType1;
    }

    /**
     * Devuelve el tipo2 del evento.
     * @return tipo2 del evento.
     */
     public String getEventType2() {
        return this.eventType2;
    }
 
    /**
     * Devuelve si el evento lo hizo el equipo local o visitante.
     * @return si el equipo del evento es local o visitante.
     */
    public String getSide() {
        return this.side;
    }
    
    /**
     * Devuelve el equipo del evento.
     * @return equipo del evento.
     */
    public String getEventTeam() {
        return this.eventTeam;
    }
    
    /**
     * Devuelve el equipo contrario al del evento.
     * @return equipo contrario al del evento.
     */
    public String getOpponentTeam() {
        return this.opponentTeam;
    }
    
    /**
     * Devuelve el primer jugador involucrado en el evento.
     * @return primer jugador involucrado en el evento.
     */
    public String getPlayer() {
        return this.player;
    }
    
    /**
     * Devuelve el segundo jugador involucrado en el evento.
     * @return segundo jugador involucrado en el evento.
     */
    public String getPlayer2() {
        return this.player2;
    }
    
    /**
     * Devuelve el jugador que entra en un cambio.
     * @return jugador que entra en un cambio.
     */
    public String getPlayerIn() {
        return this.playerIn;
    }
    
    /**
     * Devuelve el jugador que sale en un cambio.
     * @return jugador que sale en un cambio.
     */
    public String getPlayerOut() {
        return this.playerOut;
    }    
    
    /**
     * Devuelve el lugar del tiro.
     * @return lugar del tiro.
     */
    public String getShotPlace() {
        return this.shotPlace;
    }
    
    /**
     * Devuelve el resultado del tiro.
     * @return resultado del tiro.
     */
    public String getShotOutcome() {
        return this.shotOutcome;
    }

    /**
     * Devulve si hubo gol o no.
     * @return hubo gol o no.
     */
    public String getIsGoal() {
        return this.isGoal;
    }
     
    /**
     * Devuelve el lugar de la cancha en el que sucedió el evento.
     * @return lugar de la cancha del evento.
     */
    public String getLocation() {
        return this.location;
    }
    
    /**
     * Devuelve la parte del cuerpo con la que se hizo el intento de gol.
     * @return parte del cuerpo con la que se hizo el intento de gol. 
     */
    public String getBodyPart() {
        return this.bodyPart;
    }

    /**
     * Devuelve la forma de asistencia.
     * @return forma de asistencia.
     */
    public String getAssistMethod() {
        return this.assistMethod;
    } 
    
    /**
     * Devuelve la situación en la que ocurrió el evento.
     * @return situación del evento.
     */
    public String getSituation() {
        return this.situation;
    }        
}
