package com.enlatados.api.modelo;

public class Pila extends Lista {

    public Pila(){
        super ("pila");
    }

    public void apilar (Object object ){
        insertarAlFrente(object);
    }

    public Object desapilar () throws ExcepcionListaVacia {
        return eliminarDelFrente();

    }
    @Override
    public boolean estaVacia(){
        return super.estaVacia();
    }
    @Override
    public String toString(){
        return"Contenido de la PIla: \n" + super.toString();
    }
    public String imprimir() {
        StringBuilder sb = new StringBuilder();
        Nodo actual = getInicio();
        while (actual != null) {
            sb.append(actual.data.toString()).append("\n");
            actual = actual.sig;
        }
        return sb.toString();
    }


//    public String imprimir(){
//        return super.imprimir();
//    }

}


