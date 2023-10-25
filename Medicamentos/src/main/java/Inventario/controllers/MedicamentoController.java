package inventario.controllers;

import inventario.dto.MedicamentoDetalleDTO;
import inventario.dto.RegistroMedicamentoDTO;
import inventario.services.interfaces.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/api/medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamento_Service;

    @PostMapping(value = "/crear")
    public ResponseEntity<HttpStatus> CrearMedicamento(@RequestBody RegistroMedicamentoDTO reg_MedicamentoDTO) {

        try{
            medicamento_Service.crearMedicamento(reg_MedicamentoDTO);
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/listar")
    public ResponseEntity<List<MedicamentoDetalleDTO>> ListarMedicamentos() {
        List<MedicamentoDetalleDTO> listaMedicamentos = new ArrayList<>();

        try {
            listaMedicamentos = medicamento_Service.listarMedicamentos();
        }
        catch (Exception ex){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(listaMedicamentos);
    }

    @GetMapping(value = "/obtener/{id}")
    public ResponseEntity<MedicamentoDetalleDTO> ObtenerMedicamento(@PathVariable("id") int id) {
        MedicamentoDetalleDTO medicamentoDTO;

        try {
            medicamentoDTO = medicamento_Service.obtenerMedicamento(id);
        }
        catch (Exception ex){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(medicamentoDTO);
    }

    @GetMapping(value = "/actualizar")
    public ResponseEntity<HttpStatus> ActualizarMedicamento(@RequestBody MedicamentoDetalleDTO detalle_MedicamentoDTO ) {

        try {
            medicamento_Service.actualizarMedicamento(detalle_MedicamentoDTO);
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/eliminar/{id}")
    public ResponseEntity<HttpStatus> EliminarMedicamento(@PathVariable("id") int id) {


        try {
            medicamento_Service.eliminarMedicamento(id);
        }
        catch (Exception ex){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().build();
    }
}

