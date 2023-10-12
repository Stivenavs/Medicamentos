package Inventario.services.impl;

import Inventario.dto.DetalleMedicamentoDTO;
import Inventario.dto.RegMedicamentoDTO;
import Inventario.entities.Medicamento;
import Inventario.model.Estado;
import Inventario.repositories.MedicamentoRepo;
import Inventario.services.interfaces.MedicamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicamentoServiceImpl implements MedicamentoService {

    private final MedicamentoRepo medicamentoRepo;

    @Override
    public int crearMedicamento(RegMedicamentoDTO medicamentoDTO) throws Exception {
        Medicamento medicamento = new Medicamento();
        Medicamento medicamentoNuevo = new Medicamento();

        LocalDate fechaFabricacion = LocalDate.of(Integer.parseInt(medicamentoDTO.fechaFabricacion().split("/")[2]),
                                                    Integer.parseInt(medicamentoDTO.fechaFabricacion().split("/")[1]),
                                                    Integer.parseInt(medicamentoDTO.fechaFabricacion().split("/")[0]));

        LocalDate fechaVencimiento = LocalDate.of(Integer.parseInt(medicamentoDTO.fechaVencimiento().split("/")[2]),
                                                    Integer.parseInt(medicamentoDTO.fechaVencimiento().split("/")[1]),
                                                    Integer.parseInt(medicamentoDTO.fechaVencimiento().split("/")[0]));;

        try{
            medicamento.setNombre(medicamentoDTO.nombre());
            medicamento.setLaboratorioFabrica(medicamentoDTO.laboratorioFabrica());
            medicamento.setFechaFabricacion(fechaFabricacion);
            medicamento.setFechaVencimiento(fechaVencimiento);
            medicamento.setCantidadStock(medicamentoDTO.cantidadStock());
            medicamento.setValorUnitario(medicamentoDTO.valorUnitario());
            medicamento.setActivo(true);

            medicamentoNuevo = medicamentoRepo.save(medicamento);
            return medicamentoNuevo.getId();
        }
        catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public DetalleMedicamentoDTO obtenerMedicamento(int id) throws Exception {
        Optional<Medicamento> medicamentoOptional = medicamentoRepo.findById(id);
        Medicamento medicamento = new Medicamento();

        if(medicamentoOptional.isEmpty()){
            throw new Exception("No existe un medicamento con el id: " + id);
        }

        medicamento = medicamentoOptional.get();

        return new DetalleMedicamentoDTO(medicamento.getId(),
                medicamento.getNombre(),
                medicamento.getLaboratorioFabrica(),
                medicamento.getFechaFabricacion(),
                medicamento.getFechaVencimiento(),
                medicamento.getCantidadStock(),
                medicamento.getValorUnitario(),
                medicamento.getActivo());
    }

    @Override
    public int actualizarMedicamento(RegMedicamentoDTO medicamentoDTO) throws Exception {
        return 0;
    }

    @Override
    public void eliminarMedicamento(int id) throws Exception {

    }

    @Override
    public List<DetalleMedicamentoDTO> listarMedicamentos() throws Exception {
        List<Medicamento> medicamentos = medicamentoRepo.findAll();
        List<DetalleMedicamentoDTO>  listaMedicamentos = new ArrayList<>();

        if(medicamentos.isEmpty()){
            throw new Exception("No hay Medicamentos registrados");
        }

        for (Medicamento medicamento : medicamentos) {
            listaMedicamentos.add(new DetalleMedicamentoDTO(medicamento.getId(),
                    medicamento.getNombre(),
                    medicamento.getLaboratorioFabrica(),
                    medicamento.getFechaFabricacion(),
                    medicamento.getFechaVencimiento(),
                    medicamento.getCantidadStock(),
                    medicamento.getValorUnitario(),
                    medicamento.getActivo())
            );
        }

        return listaMedicamentos;
    }
}
