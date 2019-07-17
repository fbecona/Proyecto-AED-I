package OnceIdeales;

import OnceIdeales.EstructurasDeDatos.TElementoAB;
import OnceIdeales.EstructurasDeDatos.TArbolBB;

/**
 * Esta clase representa a un jugador.
 * 
 * @author fedec
 */

public class Player {
    
   private final String id;
    
   private final String name;
    
   private final TArbolBB<String> positions;

   private int matchesPlayed;
   private int goals;
   private int goalsAgainst;
   private int fouls;
   private int cards;
   
    /**
     * Crea a un jugador.
     * @param id - id del jugador.
     * @param name - nombre del jugador.
     */
    public Player(String id, String name) { 
       this.id = id;
       this.name = name;
       this.matchesPlayed = 0;
       this.goals = 0;
       this.fouls = 0;
       this.cards = 0;
       this.goalsAgainst = 0;
       this.positions = new TArbolBB<>();
    }

    /**
     * Actualiza las estadísticas del jugador.
     * @param player jugador con estadísticas para actualizar.
     */
    public void updatePlayer (Player player){
        this.matchesPlayed += player.getMatchesPlayed();
        this.goals += player.getGoals();
        this.cards += player.getCards();
        this.fouls += player.getFouls();
        this.goalsAgainst += player.getGoalsAgainst();
        if(player.getPositions().getRaiz()!=null){
            this.updatePositions(player.getPositions().getRaiz());
        }
    }
    
    /**
     * Añade de un árbol de posiciones al árbol de posiciones
     * del jugador.
     * @param aPosition posiciones a insertar.
     */
    public void updatePositions(TElementoAB<String> aPosition) {
        this.positions.insertar(aPosition.clonar());
        if(aPosition.getHijoIzq()!=null){
            updatePositions(aPosition.getHijoIzq());
        }
        if(aPosition.getHijoDer()!=null){
            updatePositions(aPosition.getHijoDer());
        }
    }
    
    /**
     * Devuelve si un jugador es mejor que otro en una posición.
     * @param position posición de la comparación.
     * @param anotherPlayer jugador a comparar.
     * @return si es mejor o no.
     */
    public boolean isBetterThan(String position, Player anotherPlayer){
        if(anotherPlayer.matchesPlayed==0){
            return true;
        }else{
            if(position.contains("A")){
                return getRating("A",this)<=getRating("A",anotherPlayer);
            }
            if(position.contains("Z")||position.contains("MC")){
                return getRating("Z",this)<=getRating("Z",anotherPlayer);
            }
            if(position.contains("M")){
                return getRating("M",this)>=getRating("M",anotherPlayer);
            }
            if(position.contains("D")){
                return getRating("D",this)>=getRating("D",anotherPlayer);
            }       
            return false;
        }
    }
    
    /**
     * Dado un jugador y una posición se le pone un puntaje para ese puesto
     * según sus estadísticas.
     * @param position posición para puntuar.
     * @param player jugador a puntuar.
     * @return puntuación.
     */
    private double getRating(String position, Player player){        
        switch(position){
            case "A":
                return (double)(player.getCards()*500 + 
                        player.getGoalsAgainst()*20)
                        /player.getMatchesPlayed() - 
                        player.getMatchesPlayed();
            case "Z":
                return (double)(player.getGoalsAgainst()*5000 +
                        player.getPositions().obtenerTamanio()*15000 +
                        player.getCards() + player.getGoals()*10
                        - player.getFouls()*200) / player.getMatchesPlayed() 
                        - player.getMatchesPlayed();
            case "M":
                return (double)(player.getGoals()*5000 -
                        player.getPositions().obtenerTamanio()*10000 -
                        player.getFouls() - 
                        player.getCards()) /
                        player.getMatchesPlayed() + 
                        player.getMatchesPlayed();
            case "D":
                return (double) (player.getGoals()*100-
                        player.getPositions().obtenerTamanio()*750)
                        /player.getMatchesPlayed() + 
                        player.getMatchesPlayed();
        }
        return 0;
    }
    
    /**
     * Devuelve el id del jugador.
     * @return id del jugador.
     */
    public String getId(){
        return this.id;
    }   
   
    /**
     * Devuelve el nombre del jugador.
     * @return nombre del jugador.
     */
    public String getName(){
        return this.name;
    }
  
    /**
     * Devuelve el árbol de posiciones del jugador.
     * @return posiciones de un jugador.
     */
    public TArbolBB<String> getPositions() {
        return this.positions;
    } 
        
    /**
     * Devuelve la cantidad de partidos jugados por el jugador.
     * @return 
     */
    public int getMatchesPlayed() {
        return this.matchesPlayed;
    }    
    
    /**
     * Añade un partido jugado al jugador.
     */
    public void addOneMatchMore() {
        this.matchesPlayed ++;
    }    

    /**
     * Devuelve la cantidad de goles hechos por el jugador.
     * @return cantidad de goles hechos del jugador.
     */
    public int getGoals() {
        return this.goals;
    }
    
    /**
     * Añade una cierta cantidad de goles hechos al jugador.
     * @param amount cantidad de goles a agregar.
     */
    public void addGoals(int amount) {
        this.goals += amount;
    }

    /**
     * Devuelve la cantidad de faltas hechas por el jugador.
     * @return cantidad de faltas del jugador.
     */
    public int getFouls() {
        return this.fouls;
    }
    
    /**
     * Añade una cierta cantidad de faltas hechas por el jugador.
     * @param amount - cantidad de faltas a agregar.
     */
    public void addFouls(int amount) {
        this.fouls += amount;
    }
    
    /**
     * Devuelve la cantidad de tarjetas del jugador.
     * @return cantidad de tarjetas del jugador.
     */
    public int getCards() {
        return this.cards;
    }
    
    /**
     * Añade una cierta cantidad de tarjetas al jugador.
     * @param amount - cantidad de tarjetas a agregar.
     */    
    public void addCards(int amount) {
        this.cards += amount;
    }

    /**
     * Devuelve la cantidad de goles que le metieron a su o sus equipos mientras
     * que el jugador jugaba.
     * @return cantidad de goles que le metieron a su o sus equipos 
     * mientras jugaba el jugador.
     */    
    public int getGoalsAgainst() {
        return this.goalsAgainst;
    }

    /**
     * Añade una cantidad de goles que le metieron al equipo o equipos mientras
     * que el jugador jugaba.
     * @param amount - cantidad de goles a agregar.
     */
    public void addGoalAgainst(int amount) {
        this.goalsAgainst += amount;
    }
}
