package com.enlatados.api.modelo;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Pedido {
    private static final AtomicInteger contador = new AtomicInteger(1);



    @Getter
    private String deptoOrigen;
    @Getter
    private String deptoDestino;
    @Getter
    private LocalDateTime fechaHoraInicio;
    @Getter
    private Cliente cliente;
    @Getter
    private Repartidor repartidor;
    @Getter
    private Vehiculo vehiculo;
    @Getter
    private Lista cajas;
    @Getter
    private int cantidadCajas;
    @Getter
    private int numeroPedido;
    @Getter
    private String estado;

    public Pedido (String deptoOrigen, String deptoDestino, Cliente cliente, Repartidor repartidor, Vehiculo vehiculo, Lista cajas ){
        this.numeroPedido = contador.getAndIncrement();
        this.deptoOrigen = deptoOrigen;
        this.deptoDestino = deptoDestino;
        this.fechaHoraInicio = LocalDateTime.now();
        this.cliente = cliente;
        this.repartidor= repartidor;
        this.vehiculo = vehiculo;
        this.cajas = cajas;
        this.cantidadCajas = contarCajas();
        this.estado = "Pendiente";
    }
    private int contarCajas(){
        int count = 0;
        Nodo actual = cajas.getInicio();
        while (actual != null){
            count++;
            actual = actual.sig;
        }
        return count;
    }
    public void completar(){
        this.estado = "Completado";
    }

    @Override
    public String toString() {
        return "Pedido #" + numeroPedido + " [" + estado + "]\n" +
                "Cliente: " + cliente + "\n" +
                "Repartidor: " + repartidor + "\n" +
                "Vehículo: " + vehiculo + "\n" +
                "Desde: " + deptoOrigen + " -> " + deptoDestino + "\n" +
                "Cajas: " + cantidadCajas + " - Fecha: " + fechaHoraInicio + "\n";
    }

}
