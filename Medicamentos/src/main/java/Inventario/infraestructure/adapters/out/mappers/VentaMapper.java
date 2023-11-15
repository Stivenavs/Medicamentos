package inventario.infraestructure.adapters.out.mappers;

import inventario.domain.models.Venta;
import inventario.infraestructure.adapters.out.database.entities.VentaEntity;

public class VentaMapper {

    MedicamentoMapper medicamentoMapper = new MedicamentoMapper();

    public Venta entityToModel(VentaEntity entity){
        Venta venta = new Venta();

        venta.setId(entity.getId());
        venta.setFechaHora(entity.getFechaHora());
        venta.setCantidad(entity.getCantidad());
        venta.setMedicamento(medicamentoMapper.entityToModel(entity.getMedicamento()));
        venta.setValorUnitario(entity.getValorUnitario());
        venta.setValorTotal(entity.getValorTotal());

        return venta;
    }

    public VentaEntity modelToEntity(Venta model){
        VentaEntity ventaEntity = new VentaEntity();

        ventaEntity.setId(model.getId());
        ventaEntity.setFechaHora(model.getFechaHora());
        ventaEntity.setCantidad(model.getCantidad());
        ventaEntity.setMedicamento(medicamentoMapper.modelToEntity(model.getMedicamento()));
        ventaEntity.setValorUnitario(model.getValorUnitario());
        ventaEntity.setValorTotal(model.getValorTotal());

        return ventaEntity;
    }
}
