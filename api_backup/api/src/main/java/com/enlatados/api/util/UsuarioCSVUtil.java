package com.enlatados.api.util;

import com.enlatados.api.modelo.Usuario;
import com.enlatados.api.modelo.Lista;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public class UsuarioCSVUtil {
    public static Lista cargarUsuariosDesdeCSV(MultipartFile archivo) {
        Lista listaUsuarios = new Lista("usuarios");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(archivo.getInputStream()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 4) {
                    Usuario usuario = new Usuario(partes[0], partes[1], partes[2], partes[3]);
                    listaUsuarios.insertarAlFinal(usuario);
                }
            }
        } catch (IOException e) {
            System.err.println("Error de lectura CSV: " + e.getMessage());
        }

        return listaUsuarios;
    }

}
