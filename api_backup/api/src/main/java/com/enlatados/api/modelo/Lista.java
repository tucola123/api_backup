package com.enlatados.api.modelo;
import lombok.Setter;

public class Lista {
    private Nodo ini,fin;
    @Setter
    private String nombre;


    public Lista(String nombre){
        ini=fin=null;
        setNombre(nombre);
    }

    public Lista() {
    this("mi-lista");
    }
    public boolean estaVacia(){
    return null == ini;

    }
    public void insertarAlFrente( Object d ){
    if( estaVacia() )
    ini = fin = new Nodo( d );

    else
        ini = new Nodo( d, ini);
    }
    public void insertarAlFinal ( Object d ){
    if ( estaVacia() )
    ini = fin = new Nodo( d );
    else
        fin = fin.sig = new Nodo( d );
    }

    public Object eliminarDelFrente() throws ExcepcionListaVacia {
    if (estaVacia())
        throw new ExcepcionListaVacia(nombre);
    Object elementoEliminado = ini.data;
    if(ini==fin)
        ini = fin = null;
    else
        ini = ini.sig;
    return elementoEliminado;
    }

    public Object eliminarDelFinal() throws ExcepcionListaVacia {
        if (estaVacia())
            throw new ExcepcionListaVacia(nombre);

        Object elementoEliminado = fin.data;

        if (ini == fin) {
            ini = fin = null;
        } else {
            Nodo actual = ini;
            while (actual.sig != fin) {
                actual = actual.sig;
            }
            fin = actual;
            fin.sig = null;
        }

        return elementoEliminado;
    }


    public String imprimir() {
        StringBuilder str = new StringBuilder();
        if ( estaVacia() )
            return String.format("%s vacia\n", nombre );
        Nodo actual = ini;
        str.append(String.format("La %s es: ", nombre));
        while( actual != null ) {
            str.append(String.format("%s ", actual.data));
            actual = actual.sig;
        }
        return str.toString().trim();
    }

    @Override
    public String toString(){
        return "Lista{"+
                imprimir() +
                '}';
    }
    public Nodo getInicio(){
        return ini;
    }

    public void vaciar() {
        ini = null;
        fin = null;
    }


}
