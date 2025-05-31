package com.enlatados.api.util;


import com.enlatados.api.modelo.Cola;
import com.enlatados.api.modelo.Lista;
import com.enlatados.api.modelo.Repartidor;
import com.enlatados.api.modelo.Nodo;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RepartidorCSVUtil {
    public static Lista cargarDesdeCSV(MultipartFile archivo) {
        Lista lista = new Lista("repartidores");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(archivo.getInputStream()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 5) {
                    Repartidor r = new Repartidor(
                            datos[0], datos[1], datos[2], datos[3], datos[4]
                    );
                    lista.insertarAlFinal(r);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    public static Cola cargarColaCSV(MultipartFile archivo) {
        Cola cola = new Cola();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(archivo.getInputStream()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 5) {
                    Repartidor repartidor = new Repartidor(partes[0], partes[1], partes[2], partes[3], partes[4]);
                    cola.encolar(repartidor);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }

        return cola;
    }
}