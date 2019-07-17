package OnceIdeales.EstructurasDeDatos;

public class TElementoAB<T> implements IElementoAB<T> {

    private final Comparable etiqueta;
    private TElementoAB<T> hijoIzq;
    private TElementoAB<T> hijoDer;
    private final T datos;
        
    
    public TElementoAB(Comparable unaEtiqueta, T unosDatos) {
        etiqueta = unaEtiqueta;
        datos = unosDatos;
    }
    
    @Override
    public TElementoAB<T> buscar(Comparable unaEtiqueta) {
        if (etiqueta.equals(unaEtiqueta)) {
            return this;
        } else if (unaEtiqueta.compareTo(etiqueta) < 0) {
            if (hijoIzq != null) {
                return getHijoIzq().buscar(unaEtiqueta);
            } else {
                return null;
            }
        } else if (hijoDer != null) {
            return getHijoDer().buscar(unaEtiqueta);
        } else {
            return null;
        }
    }        
    
    public TElementoAB<T> buscarConContains(Comparable unaEtiqueta) {
        String container = (String) etiqueta;
        String contained = ((String) unaEtiqueta).replace("\"","");
        if (container.contains(contained)) {  
            return this;
        }else{
            TElementoAB<T> left = null;
            TElementoAB<T> right = null;
            if (hijoIzq != null) {
                left = getHijoIzq().buscarConContains(unaEtiqueta);
            }
            if (hijoDer != null) {
                right = getHijoDer().buscarConContains(unaEtiqueta);
            }
            if(left == null && right == null){
                return null;
            }else{
                if(left!=null && right == null){
                    return left;
                }
                return right;
            }
        }
    }  

    @Override
    public boolean insertar(TElementoAB<T> unElemento) {
        if (unElemento.getEtiqueta().compareTo(etiqueta) < 0) {
            if (hijoIzq != null) {
                return getHijoIzq().insertar(unElemento);
            } else {
                hijoIzq = unElemento;
                return true;
            }
        } else if (unElemento.getEtiqueta().compareTo(etiqueta) > 0) {
            if (hijoDer != null) {
                return getHijoDer().insertar(unElemento);
            } else {
                hijoDer = unElemento;
                return true;
            }
        } else {
            return false;
        }
    }
    
    public TElementoAB<T> clonar(){
        return new TElementoAB(etiqueta,datos);
    }

    public String inOrden() {
        String tempStr = "";
        if (hijoIzq != null) {
            tempStr = hijoIzq.inOrden();
        }
        tempStr = tempStr + this.etiqueta + "~";
        if (hijoDer != null) {
            tempStr = tempStr + hijoDer.inOrden();
        }
        return tempStr;
    }
    
    public void inOrden(Lista<T> unaLista) {
        if (hijoIzq != null) {
            hijoIzq.inOrden(unaLista);
        }
        unaLista.insertar(new Nodo<>(etiqueta,datos));
        if (hijoDer != null) {
            hijoDer.inOrden(unaLista);
        }
    }

    public int obtenerTamanio() {
        int subIzquierdo = 0;
        int subDerecho = 0;
        if (hijoIzq != null) {
            subIzquierdo += hijoIzq.obtenerTamanio();
        }
        if (hijoDer != null) {
            subDerecho += hijoDer.obtenerTamanio();
        }
        return subIzquierdo + subDerecho + 1;
    }
    
    @Override
    public Comparable getEtiqueta() {
        return etiqueta;
    }

    @Override
    public T getDatos() {
        return datos;
    }

    @Override
    public TElementoAB<T> getHijoIzq() {
        return hijoIzq;
    }

    @Override
    public TElementoAB<T> getHijoDer() {
        return hijoDer;
    }
    
    @Override
    public void setHijoIzq(TElementoAB<T> elemento) {
        this.hijoIzq = elemento;
    }    
    
    @Override
    public void setHijoDer(TElementoAB<T> elemento) {
        this.hijoDer = elemento;
    }        
}
