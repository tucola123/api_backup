package com.enlatados.api.modelo;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

public class Vehiculo {

    @Getter
    private String placa;
    @Getter
    private String marca;
    @Getter
    private String modelo;
    @Getter
    private String color;
    @Getter
    private int año;
    @Getter
    private String tipoTransmision;

    public Vehiculo(String placa, String marca, String modelo, String color, int año, String tipoTransmision){

        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.año = año;
        this.tipoTransmision = tipoTransmision;


    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "placa='" + placa + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", color='" + color + '\'' +
                ", año=" + año +
                ", Transmisión: " + tipoTransmision +
                '}';
    }

}
