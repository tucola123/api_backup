package com.enlatados.api.modelo;

import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;

import java.io.File;

import static guru.nidi.graphviz.model.Factory.*;

public class Cola {

    private Lista lista;

    public  Cola (){
        lista = new Lista("myCola");

    }
    public void encolar (Object o){
        lista.insertarAlFinal (o);
    }
    public Object descolar() throws ExcepcionListaVacia {
        return lista.eliminarDelFrente();

    }
    public boolean estaVacia(){
        return lista.estaVacia();
    }
    public void imprimir(){
        lista.imprimir();
    }

    public Lista getLista() {
        return this.lista;
    }
    public void graficarCola(String rutaArchivo) {
        try {
            Graph grafo = graph("Cola").directed();
            Nodo actual = lista.getInicio();
            Nodo previo = null;

            while (actual != null) {
                String id = obtenerEtiqueta(actual.data);
                grafo = grafo.with(node(id));
                if (previo != null) {
                    String idPrevio = obtenerEtiqueta(previo.data);
                    grafo = grafo.with(node(idPrevio).link(node(id)));
                }
                previo = actual;
                actual = actual.sig;
            }

            Graphviz.fromGraph(grafo).render(Format.PNG).toFile(new File(rutaArchivo));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String obtenerEtiqueta(Object o) {
        if (o instanceof Repartidor r) {
            return "Repartidor " + r.getCui();
        } else if (o instanceof Vehiculo v) {
            return "Vehiculo " + v.getPlaca();
        } else {
            return o.toString();
        }
    }
    public void copiarDesde(Cola otra) {
        this.lista = otra.getLista();
    }
}


