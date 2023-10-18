package inventario.services.interfaces;

import inventario.dto.ventaDetalleDTO;
import inventario.dto.registroVentaDTO;
import inventario.dto.filtroVentaDTO;

import java.util.List;

public interface ventaService {

    int registrarVenta(registroVentaDTO regVentaDTO) throws Exception;
    List<ventaDetalleDTO> listarVentas() throws Exception;

    List<ventaDetalleDTO> listarVentasFechas(filtroVentaDTO ventaFiltroDTO) throws Exception;
}
