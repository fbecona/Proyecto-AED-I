package OnceIdeales;

import OnceIdeales.EstructurasDeDatos.TArbolBB;

/**
 * Esta clase representa una liga.
 * 
 * @author fedec
 */

public class League {
    
    private final String id;
    
    private TArbolBB<Season> seasons;
        
    /**
     * Crea una liga.
     * @param id id de la liga.
     */
    public League(String id) {
        this.id = id;
        this.seasons = new TArbolBB<>();
    }
    
    /**
     * Fija las temporadas cargadas en la liga.
     * @param seasons árbol de temporadas de la liga con la estructura.
     */
    public void setSeasons(TArbolBB<Season> seasons){
        this.seasons = seasons;
    } 
            
    /**
     * Devuelve el id de la liga.
     * @return id de la liga.
     */
    public String getId(){
        return this.id;
    } 
    
    /**
     * Devuelve las temporadas de la liga.
     * @return temporadas de la liga.
     */
    public TArbolBB<Season> getSeasons(){
        return this.seasons;
    }
}
