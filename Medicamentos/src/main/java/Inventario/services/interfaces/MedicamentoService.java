package inventario.services.interfaces;

import inventario.dto.MedicamentoDetalleDTO;
import inventario.dto.RegistroMedicamentoDTO;
import java.util.List;

public interface MedicamentoService {
    int crearMedicamento(RegistroMedicamentoDTO medicamentoDTO) throws Exception;
    MedicamentoDetalleDTO obtenerMedicamento(int id) throws Exception;
    int actualizarMedicamento(MedicamentoDetalleDTO DetalleMedicamentoDTO) throws Exception;
    int eliminarMedicamento(int id) throws Exception;
    List<MedicamentoDetalleDTO> listarMedicamentos() throws Exception;
}
