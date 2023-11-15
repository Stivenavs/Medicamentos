package inventario.application;

import inventario.domain.models.Medicamento;
import inventario.infraestructure.adapters.out.database.entities.MedicamentoEntity;
import inventario.infraestructure.adapters.in.rest.in.MedicamentoInDTO;
import inventario.infraestructure.adapters.in.rest.out.MedicamentoOutDTO;
import inventario.infraestructure.adapters.out.database.repositories.MedicamentoRepository;
import inventario.infraestructure.adapters.out.mappers.MedicamentoMapper;
import inventario.infraestructure.ports.MedicamentoService;
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
    private MedicamentoMapper medicamentoMapper = new MedicamentoMapper();

    @Override
    public int crearMedicamento(MedicamentoInDTO medicamentoInDTO) throws Exception {
        Medicamento medicamento = new Medicamento();
        MedicamentoEntity medicamentoEntity = new MedicamentoEntity();
        MedicamentoEntity medicamentoNuevo = new MedicamentoEntity();
        LocalDate fechaFabricacion;
        LocalDate fechaVencimiento;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        fechaFabricacion = LocalDate.parse(medicamentoInDTO.fechaFabricacion(), formatter);
        fechaVencimiento = LocalDate.parse(medicamentoInDTO.fechaVencimiento(), formatter);

        try{
            medicamento.setNombre(medicamentoInDTO.nombre());
            medicamento.setLaboratorioFabrica(medicamentoInDTO.laboratorioFabrica());
            medicamento.setFechaFabricacion(fechaFabricacion);
            medicamento.setFechaVencimiento(fechaVencimiento);
            medicamento.setCantidadStock(medicamentoInDTO.cantidadStock());
            medicamento.setValorUnitario(medicamentoInDTO.valorUnitario());
            medicamento.setActivo(true);

            medicamentoEntity = medicamentoMapper.modelToEntity(medicamento);
            medicamentoNuevo = medicamentoRepository.save(medicamentoEntity);

            return medicamentoNuevo.getId();
        }
        catch (Exception ex){
            throw new Exception("Error al registrar el medicamento");
        }
    }

    @Override
    public MedicamentoOutDTO obtenerMedicamento(int id) throws Exception {
        Optional<MedicamentoEntity> medicamentoOptional = medicamentoRepository.findById(id);
        MedicamentoEntity medicamentoEntity = new MedicamentoEntity();
        Medicamento medicamento = new Medicamento();

        if(medicamentoOptional.isEmpty()){
            throw new Exception("No existe un medicamento con el id: " + id);
        }

        medicamentoEntity = medicamentoOptional.get();
        medicamento = medicamentoMapper.entityToModel(medicamentoEntity);

        return new MedicamentoOutDTO(medicamento.getId(),
                medicamento.getNombre(),
                medicamento.getLaboratorioFabrica(),
                medicamento.getFechaFabricacion().toString(),
                medicamento.getFechaVencimiento().toString(),
                medicamento.getCantidadStock(),
                medicamento.getValorUnitario(),
                medicamento.getActivo());
    }

    @Override
    public int actualizarMedicamento(MedicamentoOutDTO medicamentoOutDTO) throws Exception {

        Optional<MedicamentoEntity> medicamentoOptional = medicamentoRepository.findById(medicamentoOutDTO.Id());
        MedicamentoEntity medicamentoEntity = new MedicamentoEntity();
        Medicamento medicamento = new Medicamento();
        MedicamentoEntity medicamentoNuevo = new MedicamentoEntity();
        LocalDate fechaFabricacion;
        LocalDate fechaVencimiento;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if(medicamentoOptional.isEmpty()){
            throw new Exception("No existe un medicamento con el id: " + medicamentoOutDTO.Id());
        }

        fechaFabricacion = LocalDate.parse(medicamentoOutDTO.fechaFabricacion(), formatter);
        fechaVencimiento = LocalDate.parse(medicamentoOutDTO.fechaVencimiento(), formatter);

        try {
            medicamento = medicamentoMapper.entityToModel(medicamentoEntity);

            medicamento.setNombre(medicamentoOutDTO.nombre());
            medicamento.setLaboratorioFabrica(medicamentoOutDTO.laboratorioFabrica());
            medicamento.setFechaFabricacion(fechaFabricacion);
            medicamento.setFechaVencimiento(fechaVencimiento);
            medicamento.setCantidadStock(medicamentoOutDTO.cantidadStock());
            medicamento.setValorUnitario(medicamentoOutDTO.valorUnitario());
            medicamento.setActivo(medicamentoOutDTO.activo());

            medicamentoEntity = medicamentoMapper.modelToEntity(medicamento);
            medicamentoNuevo = medicamentoRepository.save(medicamentoEntity);

            return medicamentoNuevo.getId();
        }
        catch (Exception ex){
           throw new Exception("Error al actualizar el medicamento con el id: " + medicamentoOutDTO.Id());
        }
    }

    @Override
    public int eliminarMedicamento(int id) throws Exception {
        Optional<MedicamentoEntity> medicamentoOptional = medicamentoRepository.findById(id);
        MedicamentoEntity medicamentoEntity = new MedicamentoEntity();
        Medicamento medicamento = new Medicamento();
        MedicamentoEntity medicamentoNuevo = new MedicamentoEntity();

        if(medicamentoOptional.isEmpty()){
            throw new Exception("No existe un medicamento con el id: " + id);
        }

        try{
            medicamentoEntity = medicamentoOptional.get();
            medicamento = medicamentoMapper.entityToModel(medicamentoEntity);

            medicamento.setActivo(false);

            medicamentoEntity = medicamentoMapper.modelToEntity(medicamento);
            medicamentoNuevo = medicamentoRepository.save(medicamentoEntity);

            return medicamentoNuevo.getId();
        }
        catch (Exception ex){
            throw new Exception("Error al eliminar el medicamento con el id: " + id);
        }
    }

    @Override
    public List<MedicamentoOutDTO> listarMedicamentos() throws Exception {
        List<MedicamentoEntity> medicamentos = medicamentoRepository.findAll();
        List<MedicamentoOutDTO>  listaMedicamentos = new ArrayList<>();
        Medicamento medicamento = new Medicamento();

        if(medicamentos.isEmpty()){
            throw new Exception("No hay Medicamentos registrados");
        }

        for (MedicamentoEntity medicamentoEntity : medicamentos) {
            medicamento = medicamentoMapper.entityToModel(medicamentoEntity);

            listaMedicamentos.add(new MedicamentoOutDTO(medicamento.getId(),
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
