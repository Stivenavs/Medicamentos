package inventario.infraestructure.adapters.in.rest.controllers;

import inventario.infraestructure.adapters.in.rest.in.FiltroVentaInDTO;
import inventario.infraestructure.adapters.in.rest.in.RegistroInDTO;
import inventario.infraestructure.adapters.in.rest.out.VentaOutDTO;
import inventario.infraestructure.ports.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/api/ventas")
public class VentaController {

    @Autowired
    VentaService ventaService;

    @PostMapping(value = "/registrar")
    public ResponseEntity<String> RegistrarVenta(@RequestBody RegistroInDTO registroInDTO) {

        try{
            ventaService.registrarVenta(registroInDTO);
        }
        catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok("Venta Registrada de " + registroInDTO);
    }


    @GetMapping(value = "/listar")
    public ResponseEntity<List<VentaOutDTO>> ListarVentas(@RequestBody FiltroVentaInDTO filtroVentaDTO) {
        List<VentaOutDTO> listaVentas = new ArrayList<>();

        try {
            listaVentas = ventaService.listarVentas(filtroVentaDTO);
        }
        catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(listaVentas);
    }
}
