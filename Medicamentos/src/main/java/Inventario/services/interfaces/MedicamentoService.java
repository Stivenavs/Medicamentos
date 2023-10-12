package Inventario.services.interfaces;

import Inventario.dto.DetalleMedicamentoDTO;
import Inventario.dto.RegMedicamentoDTO;
import java.util.List;

public interface MedicamentoService {
    int crearMedicamento(RegMedicamentoDTO medicamentoDTO) throws Exception;
    DetalleMedicamentoDTO obtenerMedicamento(int id) throws Exception;
    int actualizarMedicamento(RegMedicamentoDTO medicamentoDTO) throws Exception;
    void eliminarMedicamento(int id) throws Exception;
    List<DetalleMedicamentoDTO> listarMedicamentos() throws Exception;
}
