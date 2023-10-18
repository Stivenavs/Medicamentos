package Inventario.controllers;

import Inventario.dto.DetalleMedicamentoDTO;
import Inventario.dto.RegMedicamentoDTO;
import Inventario.services.interfaces.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    @PostMapping(value = "/crear")
    public ResponseEntity<HttpStatus> CrearMedicamento(@RequestBody RegMedicamentoDTO regMedicamentoDTO) {

        try{
            medicamentoService.crearMedicamento(regMedicamentoDTO);
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/listar")
    public ResponseEntity<List<DetalleMedicamentoDTO>> ListarMedicamentos() {
        List<DetalleMedicamentoDTO> listaMedicamentos = new ArrayList<>();

        try {
            listaMedicamentos = medicamentoService.listarMedicamentos();
        }
        catch (Exception ex){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(listaMedicamentos);
    }

    @GetMapping(value = "/obtener/{id}")
    public ResponseEntity<DetalleMedicamentoDTO> ObtenerMedicamento(@PathVariable("id") int id) {
        DetalleMedicamentoDTO medicamentoDTO;

        try {
            medicamentoDTO = medicamentoService.obtenerMedicamento(id);
        }
        catch (Exception ex){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(medicamentoDTO);
    }

    @GetMapping(value = "/actualizar")
    public ResponseEntity<HttpStatus> ActualizarMedicamento(@RequestBody DetalleMedicamentoDTO detalleMedicamentoDTO ) {

        try {
            medicamentoService.actualizarMedicamento(detalleMedicamentoDTO);
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/eliminar/{id}")
    public ResponseEntity<HttpStatus> EliminarMedicamento(@PathVariable("id") int id) {


        try {
            medicamentoService.eliminarMedicamento(id);
        }
        catch (Exception ex){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().build();
    }
}

