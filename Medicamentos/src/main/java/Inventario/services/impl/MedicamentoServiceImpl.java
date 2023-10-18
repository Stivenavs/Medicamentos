package inventario.services.impl;

import inventario.dto.*;
import inventario.entities.medicamento;
import inventario.repositories.medicamentoRepository;
import inventario.services.interfaces.medicamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class medicamentoServiceImpl implements medicamentoService {

    private final medicamentoRepository medicamentoRepository;

    @Override
    public int crearMedicamento(registroMedicamentoDTO medicamentoDTO) throws Exception {
        medicamento medicamento = new medicamento();
        inventario.entities.medicamento medicamentoNuevo = new medicamento();

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

            medicamentoNuevo = medicamentoRepository.save(medicamento);
            return medicamentoNuevo.getId();
        }
        catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public medicamentoDetalleDTO obtenerMedicamento(int id) throws Exception {
        Optional<medicamento> medicamentoOptional = medicamentoRepository.findById(id);
        medicamento medicamento = new medicamento();

        if(medicamentoOptional.isEmpty()){
            throw new Exception("No existe un medicamento con el id: " + id);
        }

        medicamento = medicamentoOptional.get();

        return new medicamentoDetalleDTO(medicamento.getId(),
                medicamento.getNombre(),
                medicamento.getLaboratorioFabrica(),
                medicamento.getFechaFabricacion().toString(),
                medicamento.getFechaVencimiento().toString(),
                medicamento.getCantidadStock(),
                medicamento.getValorUnitario(),
                medicamento.getActivo());
    }

    @Override
    public int actualizarMedicamento(medicamentoDetalleDTO detalle_MedicamentoDTO) throws Exception {

        Optional<medicamento> medicamentoOptional = medicamentoRepository.findById(detalle_MedicamentoDTO.Id());
        medicamento medicamento = new medicamento();
        LocalDate fechaFabricacion;
        LocalDate fechaVencimiento;

        if(medicamentoOptional.isEmpty()){
            throw new Exception("No existe un medicamento con el id: " + detalle_MedicamentoDTO.Id());
        }

        medicamento = medicamentoOptional.get();
        fechaFabricacion = LocalDate.of(Integer.parseInt(detalle_MedicamentoDTO.fechaFabricacion().split("/")[2]),
                                        Integer.parseInt(detalle_MedicamentoDTO.fechaFabricacion().split("/")[1]),
                                        Integer.parseInt(detalle_MedicamentoDTO.fechaFabricacion().split("/")[0]));

        fechaVencimiento = LocalDate.of(Integer.parseInt(detalle_MedicamentoDTO.fechaVencimiento().split("/")[2]),
                                        Integer.parseInt(detalle_MedicamentoDTO.fechaVencimiento().split("/")[1]),
                                        Integer.parseInt(detalle_MedicamentoDTO.fechaVencimiento().split("/")[0]));;

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
           throw ex;
        }
    }

    @Override
    public int eliminarMedicamento(int id) throws Exception {
        Optional<medicamento> medicamentoOptional = medicamentoRepository.findById(id);
        medicamento medicamento = new medicamento();

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
            throw ex;
        }
    }

    @Override
    public List<medicamentoDetalleDTO> listarMedicamentos() throws Exception {
        List<medicamento> medicamentos = medicamentoRepository.findAll();
        List<medicamentoDetalleDTO>  listaMedicamentos = new ArrayList<>();

        if(medicamentos.isEmpty()){
            throw new Exception("No hay Medicamentos registrados");
        }

        for (inventario.entities.medicamento medicamento : medicamentos) {
            listaMedicamentos.add(new medicamentoDetalleDTO(medicamento.getId(),
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
