package OnceIdeales;

import OnceIdeales.EstructurasDeDatos.ILista;
import OnceIdeales.EstructurasDeDatos.Lista;
import OnceIdeales.EstructurasDeDatos.Nodo;

/**
 * Esta clase es una lista que representa una formación para 11 mejores 
 * jugadores, las posiciones elegidas fueron las de la 4-3-3.
 * @author fedec
 */

public class BestXILista extends Lista implements ILista{
    
    private Lista<Player> bestPlayers;
     
    /**
     *Es contiene las posiciones de la formación.
     */
    private final String[] positions = new String[]{"\"A\"","\"ZI\"","\"ZC\"","\"ZC\"","\"ZD\"",
        "\"MC\"","\"MI\"","\"MD\"","\"DD\"","\"DC\"","\"DI\""};
    
    /**
     * Crea la lista y agrega un nodo con un jugador vacío para cada posición
     * de la formación, esto se hace para que después se pueda comparar con un 
     * jugador que tiene todas sus estadísticas son 0.
     */
    public BestXILista(){
        this.bestPlayers = new Lista<>();
        for (String position : positions) {
            this.bestPlayers.insertar(new Nodo(position, new Player(null,null)));
        }      
    }
    
    /**
     * Dada una lista de jugadores selecciona al mejor en cada posición para
     * la formación 4-3-3.
     * @param players - lista de jugadores a comparar.
     */
    public void setBestPlayers(Lista<Player> players){
        Lista<Player> newPlayers = new Lista<>();
        for (String position : positions) {
            Player bestPlayer = new Player(null,null);
            Nodo<Player> aPlayer = players.getPrimero();
            while (aPlayer!=null) {
                Player player = aPlayer.getDato();
                boolean isAlready = false;
                Nodo<Player> aux = newPlayers.getPrimero();
                while(aux!=null){
                    if(player.getId().equals(aux.getDato().getId())){ 
                        isAlready = true;
                    }
                    aux = aux.getSiguiente();
                }  
                if (player.getPositions().buscar(position) != null && 
                        isAlready == false && 
                        player.isBetterThan(position,bestPlayer)){
                    bestPlayer = player;
                }
                aPlayer = aPlayer.getSiguiente();
            }
            newPlayers.insertar(new Nodo(position,bestPlayer));
        }
        this.bestPlayers = newPlayers;
    }    
    
    /**
     * Imprime la lista de los mejores jugadores.
     * @return once ideal
     */
    public Lista<Player> getBestXI(){
        Lista<Player> formatedBestPlayers = new Lista<>();
        Nodo<Player> aux = this.bestPlayers.getPrimero();
        while(aux!=null){
            int quantity = 0;
            for (String position : positions) {
                if (position.equals(aux.getEtiqueta()) && quantity == 0) {
                    formatedBestPlayers.insertar(aux.clonar());
                    if("\"ZC\"".equals(aux.getEtiqueta())){
                        quantity++;
                    }
                }
            }
            aux = aux.getSiguiente();
        }
        return formatedBestPlayers;
    }        
    
    /**
     *Devuelve la lista de mejores jugadores.
     * @return lista de mejores jugadores.
     */
    public Lista<Player> getPlayers(){
        return this.bestPlayers;
    }  
}
