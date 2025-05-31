package com.enlatados.api.util;


import com.enlatados.api.modelo.Cola;
import com.enlatados.api.modelo.Vehiculo;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VehiculoCSVUtil {

    public static Cola cargarColaCSV(MultipartFile archivo) {
        Cola cola = new Cola();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(archivo.getInputStream()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 6) {
                    Vehiculo vehiculo = new Vehiculo(partes[0], partes[1], partes[2], partes[3], Integer.parseInt(partes[4]), partes[5]);
                    cola.encolar(vehiculo);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer archivo de vehículos: " + e.getMessage());
        }
        return cola;
    }
}
