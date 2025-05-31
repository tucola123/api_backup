package com.enlatados.api.controlador;

import com.enlatados.api.modelo.Cola;
import com.enlatados.api.modelo.Nodo;
import com.enlatados.api.modelo.Vehiculo;
import com.enlatados.api.shared.DatosCompartidos;
import com.enlatados.api.modelo.CargadorCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/vehiculos")
@CrossOrigin
public class VehiculoController {

    @Autowired
    private DatosCompartidos datos;

    @PostMapping("/cargar")
    public ResponseEntity<String> cargarVehiculos(@RequestParam("archivo") MultipartFile archivo) {
        if (archivo.isEmpty()) {
            return ResponseEntity.badRequest().body("Archivo vacío");
        }

        Cola cola = CargadorCSV.cargarVehiculosCSVDesdeMultipart(archivo);
        datos.getColaVehiculos().copiarDesde(cola);

        return ResponseEntity.ok("Vehículos cargados correctamente");
    }

    @GetMapping
    public ResponseEntity<List<Vehiculo>> obtenerVehiculos() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        Nodo actual = datos.getColaVehiculos().getLista().getInicio();

        while (actual != null) {
            vehiculos.add((Vehiculo) actual.data);
            actual = actual.sig;
        }

        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/graficar")
    public ResponseEntity<String> graficarVehiculos() {
        try {
            datos.getColaVehiculos().graficarCola("reporte_vehiculos.png");
            return ResponseEntity.ok("Gráfico generado en: " + new File("reporte_vehiculos.png").getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al generar gráfico: " + e.getMessage());
        }
    }
}
