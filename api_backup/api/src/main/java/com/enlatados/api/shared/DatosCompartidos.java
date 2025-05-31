package com.enlatados.api.shared;

import com.enlatados.api.modelo.*;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class DatosCompartidos {

    private final Pila pilaCajas = new Pila();
    private final ArbolAVL arbolClientes = new ArbolAVL();
    private final Cola colaRepartidores = new Cola();
    private final Cola colaVehiculos = new Cola();
    private final Lista listaPedidos = new Lista("pedidos");
    private final Lista listaUsuarios = new Lista("usuarios");

    public Pila getPilaCajas() { return pilaCajas; }
    public ArbolAVL getArbolClientes() { return arbolClientes; }
    public Cola getColaRepartidores() { return colaRepartidores; }
    public Cola getColaVehiculos() { return colaVehiculos; }
    public Lista getListaPedidos() { return listaPedidos; }
    public Lista getListaUsuarios() { return listaUsuarios; }
}