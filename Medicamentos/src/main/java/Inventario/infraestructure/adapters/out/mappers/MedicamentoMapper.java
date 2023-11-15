package inventario.infraestructure.adapters.out.mappers;

import inventario.domain.models.Medicamento;
import inventario.infraestructure.adapters.out.database.entities.MedicamentoEntity;

public class MedicamentoMapper {

    public Medicamento entityToModel(MedicamentoEntity entity){
        Medicamento medicamento = new Medicamento();

        medicamento.setId(entity.getId());
        medicamento.setNombre(entity.getNombre());
        medicamento.setLaboratorioFabrica(entity.getLaboratorioFabrica());
        medicamento.setFechaFabricacion(entity.getFechaFabricacion());
        medicamento.setFechaVencimiento(entity.getFechaVencimiento());
        medicamento.setCantidadStock(entity.getCantidadStock());
        medicamento.setValorUnitario(entity.getValorUnitario());
        medicamento.setActivo(entity.getActivo());

        return medicamento;
    }

    public MedicamentoEntity modelToEntity(Medicamento model){
        MedicamentoEntity medicamentoEntity = new MedicamentoEntity();

        medicamentoEntity.setId(model.getId());
        medicamentoEntity.setNombre(model.getNombre());
        medicamentoEntity.setLaboratorioFabrica(model.getLaboratorioFabrica());
        medicamentoEntity.setFechaFabricacion(model.getFechaFabricacion());
        medicamentoEntity.setFechaVencimiento(model.getFechaVencimiento());
        medicamentoEntity.setCantidadStock(model.getCantidadStock());
        medicamentoEntity.setValorUnitario(model.getValorUnitario());
        medicamentoEntity.setActivo(model.getActivo());

        return medicamentoEntity;
    }
}
