package  com.enlatados.api.modelo;

public class NodoAVL {

    Cliente cliente;
    NodoAVL izq, der;
    int altura;

    public NodoAVL(Cliente cliente){

        this.cliente = cliente;
        this.altura = 1;


    }


}
