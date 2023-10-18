package inventario.services.interfaces;

import inventario.dto.medicamentoDetalleDTO;
import inventario.dto.registroMedicamentoDTO;
import java.util.List;

public interface medicamentoService {
    int crearMedicamento(registroMedicamentoDTO medicamentoDTO) throws Exception;
    medicamentoDetalleDTO obtenerMedicamento(int id) throws Exception;
    int actualizarMedicamento(medicamentoDetalleDTO DetalleMedicamentoDTO) throws Exception;
    int eliminarMedicamento(int id) throws Exception;
    List<medicamentoDetalleDTO> listarMedicamentos() throws Exception;
}
