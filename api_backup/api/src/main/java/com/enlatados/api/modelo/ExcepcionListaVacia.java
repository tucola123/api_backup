package  com.enlatados.api.modelo;
import lombok.Setter;
@Setter
public class ExcepcionListaVacia extends RuntimeException{
    public ExcepcionListaVacia(){
        this("mi_Lista");
    }

    public ExcepcionListaVacia(String miLista) {
        super(miLista+"esta vacia");
    }
}
