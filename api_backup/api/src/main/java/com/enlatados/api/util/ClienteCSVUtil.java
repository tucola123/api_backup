package com.enlatados.api.util;

import com.enlatados.api.modelo.ArbolAVL;
import com.enlatados.api.modelo.Cliente;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ClienteCSVUtil {

    public static ArbolAVL cargarClientesDesdeCSV(MultipartFile archivo) {
        ArbolAVL arbol = new ArbolAVL();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(archivo.getInputStream()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 4) {
                    Cliente cliente = new Cliente(partes[0], partes[1], partes[2], partes[3]);
                    arbol.insertar(cliente);
                }
            }
        } catch (Exception e) {
            System.err.println("Error cargando clientes: " + e.getMessage());
        }

        return arbol;
    }
}
