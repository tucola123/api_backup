package com.enlatados.api.modelo;

import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;
import static guru.nidi.graphviz.model.Factory.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ArbolAVL {

    private NodoAVL raiz;

    public void insertar (Cliente cliente){

        raiz = insertarRec(raiz, cliente);

    }
    public Cliente buscar(String cui) {
        return buscarRecursivo(raiz, cui);
    }

    private Cliente buscarRecursivo(NodoAVL nodo, String cui) {
        if (nodo == null) return null;

        int comparacion = cui.compareTo(nodo.cliente.getCui());

        if (comparacion == 0) {
            return nodo.cliente;
        } else if (comparacion < 0) {
            return buscarRecursivo(nodo.izq, cui);
        } else {
            return buscarRecursivo(nodo.der, cui);
        }
    }
    private NodoAVL insertarRec(NodoAVL nodo, Cliente cliente) {

        if (nodo == null)
            return new NodoAVL(cliente);

        int comparar = cliente.getCui().compareTo(nodo.cliente.getCui());

        if (comparar < 0) {
            nodo.izq = insertarRec(nodo.izq, cliente);

        } else if (comparar > 0) {
            nodo.der = insertarRec(nodo.der, cliente);
        } else{
            return nodo;
    }

        actualizarAltura(nodo);
        return balancear(nodo);

    }

    private void actualizarAltura(NodoAVL nodo){

        int altIzq = (nodo.izq !=null) ? nodo.izq.altura : 0;
        int altDer = (nodo.der !=null) ? nodo.der.altura : 0;
        nodo.altura = Math.max(altIzq,altDer) + 1;

    }
    private int obtenerBalance(NodoAVL nodo){

        int altIzq = (nodo.izq != null) ? nodo.izq.altura : 0;
        int altDer = (nodo.der != null) ? nodo.der.altura : 0;
        return altIzq - altDer;

    }
    private NodoAVL balancear(NodoAVL nodo){
        if(nodo == null)
            return null;

        int balance = obtenerBalance(nodo);

        if (balance > 1 && obtenerBalance(nodo.izq) >= 0)
            return rotarDerecha(nodo);

        if (balance > 1 && obtenerBalance(nodo.izq) < 0){
            nodo.izq = rotarIzquierda(nodo.izq);
            return rotarDerecha(nodo);
        }

        if (balance < -1 && obtenerBalance(nodo.der) <= 0)
            return rotarIzquierda(nodo);

        if (balance < -1 && obtenerBalance(nodo.der) > 0) {
            nodo.der = rotarDerecha(nodo.der);
            return rotarIzquierda(nodo);
        }

        return nodo;

    }
    private NodoAVL rotarDerecha(NodoAVL y){
        NodoAVL x = y.izq;
        NodoAVL T2 = x.der;

        x.der = y;
        y.izq = T2;

        actualizarAltura(y);
        actualizarAltura(x);

        return x;
    }
    private NodoAVL rotarIzquierda(NodoAVL x) {
        NodoAVL y = x.der;
        NodoAVL T2 = y.izq;

        y.izq = x;
        x.der = T2;

        actualizarAltura(x);
        actualizarAltura(y);

        return y;
    }
    public String imprimirInOrden() {
        StringBuilder sb = new StringBuilder();
        inOrden(raiz,sb);
        return sb.toString();
    }

    private void inOrden(NodoAVL nodo, StringBuilder sb) {
        if (nodo != null) {
            inOrden(nodo.izq,sb);
           sb.append(nodo.cliente).append("\n");
            inOrden(nodo.der, sb);
        }
    }
    public void graficarArbol(String rutaArchivo) {
        try {
            List<Node> nodos = new ArrayList<>();
            generarNodos(raiz, nodos); // genera nodos y enlaces
            Graph g = graph("ClientesAVL").directed().with(nodos);
            Graphviz.fromGraph(g).render(Format.PNG).toFile(new File(rutaArchivo));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Node generarNodos(NodoAVL nodo, List<Node> nodos) {
        if (nodo == null) return null;

        Node actual = node(nodo.cliente.getCui())
                .with(Label.of(nodo.cliente.getNombre() + " " + nodo.cliente.getApellido()));

        Node izq = generarNodos(nodo.izq, nodos);
        Node der = generarNodos(nodo.der, nodos);

        if (izq != null) actual = actual.link(izq);
        if (der != null) actual = actual.link(der);

        nodos.add(actual);
        return actual;
    }
    public void copiarDesde(ArbolAVL otro) {
        this.raiz = otro.raiz;
    }

}
