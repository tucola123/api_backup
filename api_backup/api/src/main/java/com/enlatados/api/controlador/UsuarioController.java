package com.enlatados.api.controlador;

import com.enlatados.api.modelo.Lista;
import com.enlatados.api.modelo.Nodo;
import com.enlatados.api.shared.DatosCompartidos;
import com.enlatados.api.util.UsuarioCSVUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private DatosCompartidos datos;

    @PostMapping("/cargar")
    public ResponseEntity<String> cargarUsuarios(@RequestParam("archivo") MultipartFile archivo){
        if (archivo.isEmpty()) {
            return ResponseEntity.badRequest().body("Archivo vacío.");
        }

        Lista usuariosCargados = UsuarioCSVUtil.cargarUsuariosDesdeCSV(archivo);
        datos.getListaUsuarios().vaciar();
        Nodo actual = usuariosCargados.getInicio();
        while (actual != null) {
            datos.getListaUsuarios().insertarAlFinal(actual.data);
            actual = actual.sig;
        }

        return ResponseEntity.ok("Usuarios cargados correctamente:\n" + datos.getListaUsuarios().imprimir());
    }

    @GetMapping
    public ResponseEntity<String> listaUsuarios(){
        return ResponseEntity.ok(datos.getListaUsuarios().imprimir());
    }
}
