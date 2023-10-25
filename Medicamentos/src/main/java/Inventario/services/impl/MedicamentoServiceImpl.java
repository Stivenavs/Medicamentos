package inventario.services.impl;

import inventario.dto.*;
import inventario.entities.Medicamento;
import inventario.repositories.MedicamentoRepository;
import inventario.services.interfaces.MedicamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicamentoServiceImpl implements MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;

    @Override
    public int crearMedicamento(RegistroMedicamentoDTO medicamentoDTO) throws Exception {
        Medicamento medicamento = new Medicamento();
        Medicamento medicamentoNuevo = new Medicamento();
        LocalDate fechaFabricacion;
        LocalDate fechaVencimiento;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        fechaFabricacion = LocalDate.parse(medicamentoDTO.fechaFabricacion(), formatter);
        fechaVencimiento = LocalDate.parse(medicamentoDTO.fechaVencimiento(), formatter);

        try{
            medicamento.setNombre(medicamentoDTO.nombre());
            medicamento.setLaboratorioFabrica(medicamentoDTO.laboratorioFabrica());
            medicamento.setFechaFabricacion(fechaFabricacion);
            medicamento.setFechaVencimiento(fechaVencimiento);
            medicamento.setCantidadStock(medicamentoDTO.cantidadStock());
            medicamento.setValorUnitario(medicamentoDTO.valorUnitario());
            medicamento.setActivo(true);

            medicamentoNuevo = medicamentoRepository.save(medicamento);
            return medicamentoNuevo.getId();
        }
        catch (Exception ex){
            throw new Exception("Error al registrar el medicamento");
        }
    }

    @Override
    public MedicamentoDetalleDTO obtenerMedicamento(int id) throws Exception {
        Optional<Medicamento> medicamentoOptional = medicamentoRepository.findById(id);
        Medicamento medicamento = new Medicamento();

        if(medicamentoOptional.isEmpty()){
            throw new Exception("No existe un medicamento con el id: " + id);
        }

        medicamento = medicamentoOptional.get();

        return new MedicamentoDetalleDTO(medicamento.getId(),
                medicamento.getNombre(),
                medicamento.getLaboratorioFabrica(),
                medicamento.getFechaFabricacion().toString(),
                medicamento.getFechaVencimiento().toString(),
                medicamento.getCantidadStock(),
                medicamento.getValorUnitario(),
                medicamento.getActivo());
    }

    @Override
    public int actualizarMedicamento(MedicamentoDetalleDTO detalle_MedicamentoDTO) throws Exception {

        Optional<Medicamento> medicamentoOptional = medicamentoRepository.findById(detalle_MedicamentoDTO.Id());
        Medicamento medicamento = new Medicamento();
        LocalDate fechaFabricacion;
        LocalDate fechaVencimiento;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if(medicamentoOptional.isEmpty()){
            throw new Exception("No existe un medicamento con el id: " + detalle_MedicamentoDTO.Id());
        }

        medicamento = medicamentoOptional.get();
        fechaFabricacion = LocalDate.parse(detalle_MedicamentoDTO.fechaFabricacion(), formatter);
        fechaVencimiento = LocalDate.parse(detalle_MedicamentoDTO.fechaVencimiento(), formatter);

        try {
            //medicamento.setId(detalle_MedicamentoDTO.Id());
            medicamento.setNombre(detalle_MedicamentoDTO.nombre());
            medicamento.setLaboratorioFabrica(detalle_MedicamentoDTO.laboratorioFabrica());
            medicamento.setFechaFabricacion(fechaFabricacion);
            medicamento.setFechaVencimiento(fechaVencimiento);
            medicamento.setCantidadStock(detalle_MedicamentoDTO.cantidadStock());
            medicamento.setValorUnitario(detalle_MedicamentoDTO.valorUnitario());
            medicamento.setActivo(detalle_MedicamentoDTO.activo());

            medicamentoRepository.save(medicamento);

            return medicamento.getId();
        }
        catch (Exception ex){
           throw new Exception("Error al actualizar el medicamento con el id: " + detalle_MedicamentoDTO.Id());
        }
    }

    @Override
    public int eliminarMedicamento(int id) throws Exception {
        Optional<Medicamento> medicamentoOptional = medicamentoRepository.findById(id);
        Medicamento medicamento = new Medicamento();

        if(medicamentoOptional.isEmpty()){
            throw new Exception("No existe un medicamento con el id: " + id);
        }

        try{
            medicamento = medicamentoOptional.get();
            medicamento.setActivo(false);
            medicamentoRepository.save(medicamento);

            return medicamento.getId();
        }
        catch (Exception ex){
            throw new Exception("Error al eliminar el medicamento con el id: " + id);
        }
    }

    @Override
    public List<MedicamentoDetalleDTO> listarMedicamentos() throws Exception {
        List<Medicamento> medicamentos = medicamentoRepository.findAll();
        List<MedicamentoDetalleDTO>  listaMedicamentos = new ArrayList<>();

        if(medicamentos.isEmpty()){
            throw new Exception("No hay Medicamentos registrados");
        }

        for (Medicamento medicamento : medicamentos) {
            listaMedicamentos.add(new MedicamentoDetalleDTO(medicamento.getId(),
                    medicamento.getNombre(),
                    medicamento.getLaboratorioFabrica(),
                    medicamento.getFechaFabricacion().toString(),
                    medicamento.getFechaVencimiento().toString(),
                    medicamento.getCantidadStock(),
                    medicamento.getValorUnitario(),
                    medicamento.getActivo())
            );
        }

        return listaMedicamentos;
    }
}
