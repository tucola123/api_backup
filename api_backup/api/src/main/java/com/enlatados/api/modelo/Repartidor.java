package com.enlatados.api.modelo;

import lombok.Getter;

public class Repartidor {

    @Getter
    private String cui;
    @Getter
    private String nombre;
    @Getter
    private String apellido;
    @Getter
    private String licencia;
    @Getter
    private String telefono;

    public Repartidor (String cui,String nombre, String apellido, String licencia, String telefono){
       this.cui = cui;
       this.nombre =nombre;
       this.apellido = apellido;
       this.licencia = licencia;
       this.telefono = telefono;

    }

    @Override
    public String toString() {
        return "Repartidor{" +
                "cui='" + cui + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", licencia='" + licencia + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }

}
