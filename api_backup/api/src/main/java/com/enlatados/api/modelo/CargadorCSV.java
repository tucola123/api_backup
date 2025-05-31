package com.enlatados.api.modelo;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CargadorCSV {

    public static Lista cargarUsuarioCsv(String rutaArchivo){
        Lista lista = new Lista("usuarios");

        try(BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))){
            String linea;

            while ((linea = br.readLine()) !=null){
                String[] partes = linea.split(";");
                if(partes.length == 4){
                    Usuario u = new Usuario(partes[0], partes[1], partes[2], partes[3]);
                    lista.insertarAlFinal(u);
                }

            }

        }
        catch (IOException e){
            System.err.println("Error al leer usuarios: "+ e.getMessage());
        }

        return lista;
    }

    public static Cola cargarRepartidoresCSV(String rutaArchivo) {
        Cola cola = new Cola();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 5) {
                    Repartidor r = new Repartidor(partes[0], partes[1], partes[2], partes[3], partes[4]);
                    cola.encolar(r);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer repartidores: " + e.getMessage());
        }

        return cola;
    }

    public static Cola cargarVehiculosCSVDesdeMultipart(MultipartFile archivo) {
        Cola cola = new Cola();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(archivo.getInputStream()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 6) {
                    Vehiculo v = new Vehiculo(
                            partes[0],
                            partes[1],
                            partes[2],
                            partes[3],
                            Integer.parseInt(partes[4]),
                            partes[5]
                    );
                    cola.encolar(v);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer vehículos: " + e.getMessage());
        }

        return cola;
    }
    public static ArbolAVL cargarClientesCSV(String rutaArchivo){
        ArbolAVL arbol = new ArbolAVL();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))){
            String linea;
            while ((linea = br.readLine()) != null){
                String[] partes = linea.split(";");
                if (partes.length == 4){
                    Cliente cliente = new Cliente(partes[0], partes[1], partes[2], partes[3]);
                    arbol.insertar(cliente);

                }
            }

        }
        catch (IOException e){
            System.err.println("Error al Leer clientes."+ e.getMessage());
        }
        return arbol;

    }
    public static List<Repartidor> cargarRepartidores() {
        List<Repartidor> repartidores = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("repartidores.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length >= 5) {
                    Repartidor r = new Repartidor(datos[0], datos[1], datos[2], datos[3], datos[4]);
                    repartidores.add(r);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repartidores;
    }
    public static List<Vehiculo> cargarVehiculos() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("vehiculos.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 6) {
                    Vehiculo v = new Vehiculo(
                            datos[0],
                            datos[1],
                            datos[2],
                            datos[3],
                            Integer.parseInt(datos[4]),
                            datos[5]
                    );
                    vehiculos.add(v);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vehiculos;
    }

}
