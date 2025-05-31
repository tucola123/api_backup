package com.enlatados.api.modelo;

import java.time.LocalDate;

public class CajaProducto {
    private static int contador= 0;
    private int correlativo;
    private String fechaIngreso;

    public CajaProducto(){
        this.correlativo = contador++;
        this.fechaIngreso= LocalDate.now().toString();

    }

    @Override
    public String toString() {
        return "Caja #" + correlativo + " (Fecha: " + fechaIngreso + ")";
    }

}
