package com.enlatados.api.controlador;

import com.enlatados.api.modelo.Cola;
import com.enlatados.api.modelo.Nodo;
import com.enlatados.api.modelo.Repartidor;
import com.enlatados.api.shared.DatosCompartidos;
import com.enlatados.api.util.RepartidorCSVUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/repartidores")
@CrossOrigin
public class RepartidorController {

    @Autowired
    private DatosCompartidos datos;

    @PostMapping("/cargar")
    public ResponseEntity<String> cargar(@RequestParam("archivo") MultipartFile archivo) {
        if (archivo.isEmpty()) {
            return ResponseEntity.badRequest().body("Archivo vacío.");
        }

        Cola cola = RepartidorCSVUtil.cargarColaCSV(archivo);
        datos.getColaRepartidores().copiarDesde(cola);

        return ResponseEntity.ok("Repartidores cargados correctamente.");
    }

    @GetMapping
    public ResponseEntity<List<Repartidor>> obtenerTodos() {
        List<Repartidor> repartidores = new ArrayList<>();
        Nodo actual = datos.getColaRepartidores().getLista().getInicio();

        while (actual != null) {
            repartidores.add((Repartidor) actual.data);
            actual = actual.sig;
        }

        return ResponseEntity.ok(repartidores);
    }

    @GetMapping("/graficar")
    public ResponseEntity<String> graficarRepartidores() {
        try {
            datos.getColaRepartidores().graficarCola("reporte_repartidores.png");
            return ResponseEntity.ok("Gráfico generado en: " + new File("reporte_repartidores.png").getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al generar gráfico: " + e.getMessage());
        }
    }
}