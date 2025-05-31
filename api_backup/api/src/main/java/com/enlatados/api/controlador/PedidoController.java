package com.enlatados.api.controlador;

import com.enlatados.api.modelo.*;
import com.enlatados.api.shared.DatosCompartidos;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Factory.graph;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private DatosCompartidos datos;

    @PostMapping("/crear")
    public ResponseEntity<String> crearPedido(@RequestBody PedidoRequest req){
        Cliente cliente = datos.getArbolClientes().buscar(req.getCuiCliente());

        if(cliente == null){
            return ResponseEntity.badRequest().body("Cliente no encontrado.");
        }

        if (datos.getColaRepartidores().estaVacia() || datos.getColaVehiculos().estaVacia()){
            return ResponseEntity.badRequest().body("No hay repartidores o vehículos disponibles.");
        }

        if (datos.getPilaCajas().estaVacia()){
            return ResponseEntity.badRequest().body("No hay cajas disponibles.");
        }

        Repartidor repartidor = (Repartidor) datos.getColaRepartidores().descolar();
        Vehiculo vehiculo = (Vehiculo) datos.getColaVehiculos().descolar();

        Lista cajasParaPedido = new Lista("cajasPedido");
        try {
            for(int i = 0; i < req.getCantidadCajas(); i++){
                cajasParaPedido.insertarAlFinal(datos.getPilaCajas().desapilar());
            }
        } catch (Exception e){
            return ResponseEntity.badRequest().body("No hay suficientes cajas disponibles en almacén.");
        }

        Pedido pedido = new Pedido(req.getDeptoOrigen(), req.getDeptoDestino(), cliente, repartidor, vehiculo, cajasParaPedido);
        datos.getListaPedidos().insertarAlFinal(pedido);
        datos.getColaRepartidores().encolar(repartidor);
        datos.getColaVehiculos().encolar(vehiculo);

        return ResponseEntity.ok("Pedido creado:\n\n" + pedido.toString());
    }

    @GetMapping
    public ResponseEntity<String> verPedidos(){
        return ResponseEntity.ok(datos.getListaPedidos().imprimir());
    }

    @PostMapping("/completar/{numero}")
    public ResponseEntity<String> completarPedido(@PathVariable("numero") int numero) {
        Nodo actual = datos.getListaPedidos().getInicio();
        while (actual != null){
            Pedido pedido = (Pedido) actual.data;
            if (pedido.getNumeroPedido() == numero) {
                pedido.completar();
                return ResponseEntity.ok("Pedido #" + numero + " marcado como COMPLETADO.");
            }
            actual = actual.sig;
        }
        return ResponseEntity.status(404).body("Pedido #" + numero + " no encontrado.");
    }

    @GetMapping("/reporte")
    public ResponseEntity<String> reportePedidos(){
        StringBuilder pendientes = new StringBuilder("PEDIDOS PENDIENTES\n---------------------\n");
        StringBuilder completados = new StringBuilder("PEDIDOS COMPLETADOS\n---------------------\n");

        Nodo actual= datos.getListaPedidos().getInicio();

        while (actual != null){
            Pedido pedido = (Pedido) actual.data;
            String resumen = "Pedido #" + pedido.getNumeroPedido() + ": " +
                    pedido.getCliente().getNombre() + " " + pedido.getCliente().getApellido() +
                    " → " + pedido.getDeptoOrigen() + " → " + pedido.getDeptoDestino() +
                    " (" + pedido.getCantidadCajas() + " cajas)\n";

            if ("pendiente".equalsIgnoreCase(pedido.getEstado())){
                pendientes.append(resumen);
            } else {
                completados.append(resumen);
            }
            actual = actual.sig;
        }
        return ResponseEntity.ok(pendientes.toString() + "\n" + completados.toString());
    }

    @GetMapping("/graficar")
    public ResponseEntity<String> graficarPedidos() {
        try {
            Graph grafo = graph("Pedidos").directed();
            Nodo actual = datos.getListaPedidos().getInicio();

            while (actual != null) {
                Pedido pedido = (Pedido) actual.data;

                String nodoPedido = "Pedido #" + pedido.getNumeroPedido() + " [" + pedido.getEstado() + "]";
                String nodoCliente = "Cliente: " + pedido.getCliente().getNombre();
                String nodoRepartidor = "Repartidor: " + pedido.getRepartidor().getNombre();
                String nodoVehiculo = "Vehículo: " + pedido.getVehiculo().getPlaca();
                String nodoCajas = "Cajas: " + pedido.getCantidadCajas();

                grafo = grafo.with(
                        node(nodoPedido)
                                .link(node(nodoCliente))
                                .link(node(nodoRepartidor))
                                .link(node(nodoVehiculo))
                                .link(node(nodoCajas))
                );

                actual = actual.sig;
            }

            File archivo = new File("reporte_pedidos.png");
            Graphviz.fromGraph(grafo).render(Format.PNG).toFile(archivo);

            return ResponseEntity.ok("Reporte generado correctamente en: " + archivo.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al generar gráfico: " + e.getMessage());
        }
    }
}
