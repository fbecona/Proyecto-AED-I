package OnceIdeales;

import OnceIdeales.EstructurasDeDatos.TElementoAB;
import OnceIdeales.EstructurasDeDatos.TArbolBB;

/**
 * Esta clase representa una temporada.
 * 
 * @author fedec
 */

public class Season {
    
    private final String year;
        
    private final TArbolBB<Match> matches;

    /**
     * Crea una temporada.
     * @param year año de la temporada.
     */
    public Season(String year) {
        this.year = year;
        this.matches = new TArbolBB<>();
    }
    
    /**
     * Añade un partido a la temporada.
     * @param aMatch 
     */
    public void addMatch(TElementoAB<Match> aMatch){
        this.matches.insertar(aMatch);
    }
    
    /**
     * Procesa la temporada cargando los eventos en los jugadores de los 
     * partidos y luego los equipos ya cargados en la temporada.
     * @param aMatch partidos a procesar
     */
    public void loadMatches(TElementoAB<Match> aMatch) {
        Match match = aMatch.getDatos();
        match.setEventsInPlayersAndTeamsGoals();
        if(aMatch.getHijoIzq()!=null){
            loadMatches(aMatch.getHijoIzq());
        }
        if(aMatch.getHijoDer()!=null){
            loadMatches(aMatch.getHijoDer());
        }
    }    
        
    /**
     * Devuelve el año de la temporada.
     * @return año de la temporada.
     */
    public String getYear(){
        return this.year;
    }

    /**
     * Devuelve el árbol de partidos de la temporada.
     * @return partidos de la temporada.
     */    
    public TArbolBB<Match> getMatches(){
        return this.matches;
    }
}