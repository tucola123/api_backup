package com.enlatados.api.modelo;

import lombok.Getter;

public class Cliente implements Comparable<Cliente> {

    @Getter
    private String cui;
    @Getter
    private String nombre;
    @Getter
    private String apellido;
    @Getter
    private String telefono;

    public Cliente (String cui, String nombre, String apellido, String telefono) {
        this.cui = cui;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return " (CUI: " + cui + ")"+" "+ nombre + " " + apellido + " "+ telefono ;
    }
    public int compareTo(Cliente otro) {
        return this.cui.compareTo(otro.cui);
    }

}
