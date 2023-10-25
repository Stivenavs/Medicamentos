package inventario.services.interfaces;

import inventario.dto.VentaDetalleDTO;
import inventario.dto.RegistroVentaDTO;
import inventario.dto.FiltroVentaDTO;

import java.util.List;

public interface VentaService {

    int registrarVenta(RegistroVentaDTO regVentaDTO) throws Exception;
    List<VentaDetalleDTO> listarVentas() throws Exception;

    List<VentaDetalleDTO> listarVentasFechas(FiltroVentaDTO ventaFiltroDTO) throws Exception;
}
