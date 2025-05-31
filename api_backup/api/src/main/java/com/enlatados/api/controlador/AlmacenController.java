package com.enlatados.api.controlador;

import com.enlatados.api.modelo.CajaProducto;
import com.enlatados.api.shared.DatosCompartidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/almacen")
@CrossOrigin
public class AlmacenController {

    @Autowired
    private DatosCompartidos datos;

    @PostMapping("/crear")
    public ResponseEntity<String> crearCajas(@RequestParam int cantidad) {
        if (cantidad <= 0) {
            return ResponseEntity.badRequest().body("Debe ingresar una cantidad válida.");
        }

        for (int i = 0; i < cantidad; i++) {
            datos.getPilaCajas().apilar(new CajaProducto());
        }

        return ResponseEntity.ok("Se han agregado " + cantidad + " cajas al almacén.");
    }

    @GetMapping
    public ResponseEntity<String> verCajas() {
        return ResponseEntity.ok(datos.getPilaCajas().imprimir());
    }
}