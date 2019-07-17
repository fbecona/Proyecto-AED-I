package OnceIdeales.EstructurasDeDatos;


public class TArbolBB<T> implements IArbolBB<T> {

    private TElementoAB<T> raiz;

    public TArbolBB() {
        raiz = null;
    }
    
    @Override
    public boolean insertar(TElementoAB<T> unElemento) {
        if (raiz==null) {
            raiz = unElemento;
            return true;
        } else {
            return raiz.insertar(unElemento);
        }
    }

    @Override
    public TElementoAB<T> buscar(Comparable unaEtiqueta) {
        if (raiz==null) {
            return null;
        } else {
            return raiz.buscar(unaEtiqueta);
        }
    }
    
    public TElementoAB<T> buscarWithContains(Comparable unaEtiqueta) {
        if (raiz==null) {
            return null;
        } else {
            return raiz.buscarConContains(unaEtiqueta);
        }
    }
    
    public String inOrden() {
        if (raiz == null) {
            return "arbol vacio";
        } else {
            return raiz.inOrden();
        }
    }    
    
    public Lista<T> inOrdenLista() {
        Lista <T> listaInorden = null;
        if (raiz!=null) {
            listaInorden = new Lista<>();
            raiz.inOrden(listaInorden);
        }
        return listaInorden;
    } 
    
    public int obtenerTamanio() {
        if (raiz!=null) {
            return raiz.obtenerTamanio();
        } else {
            return 0;
        }
    }
    
    public TElementoAB<T> getRaiz(){
        return this.raiz;
    }    
}
