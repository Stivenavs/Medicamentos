package inventario.infraestructure.ports;

import inventario.infraestructure.adapters.in.rest.out.MedicamentoOutDTO;
import inventario.infraestructure.adapters.in.rest.in.MedicamentoInDTO;
import java.util.List;

public interface MedicamentoService {
    int crearMedicamento(MedicamentoInDTO medicamentoDTO) throws Exception;
    MedicamentoOutDTO obtenerMedicamento(int id) throws Exception;
    int actualizarMedicamento(MedicamentoOutDTO DetalleMedicamentoDTO) throws Exception;
    int eliminarMedicamento(int id) throws Exception;
    List<MedicamentoOutDTO> listarMedicamentos() throws Exception;
}
