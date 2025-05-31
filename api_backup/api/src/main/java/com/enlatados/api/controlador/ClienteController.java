package com.enlatados.api.controlador;

import com.enlatados.api.modelo.ArbolAVL;
import com.enlatados.api.shared.DatosCompartidos;
import com.enlatados.api.util.ClienteCSVUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/clientes")
@CrossOrigin
public class ClienteController {

    @Autowired
    private DatosCompartidos datos;

    @PostMapping("/cargar")
    public ResponseEntity<String> cargarClientes(@RequestParam("archivo") MultipartFile archivo) {
        if (archivo.isEmpty()) {
            return ResponseEntity.badRequest().body("El archivo está vacío.");
        }

        ArbolAVL arbol = ClienteCSVUtil.cargarClientesDesdeCSV(archivo);
        datos.getArbolClientes().copiarDesde(arbol);

        return ResponseEntity.ok("Clientes cargados correctamente.");
    }

    @GetMapping
    public ResponseEntity<String> mostrarCliente() {
        return ResponseEntity.ok(datos.getArbolClientes().imprimirInOrden());
    }

    @GetMapping("/graficar")
    public ResponseEntity<String> graficarClientes() {
        try {
            String ruta = "reporte_clientes.png";
            datos.getArbolClientes().graficarArbol(ruta);
            return ResponseEntity.ok("Gráfico generado en: " + new File(ruta).getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al generar gráfico: " + e.getMessage());
        }
    }
}
