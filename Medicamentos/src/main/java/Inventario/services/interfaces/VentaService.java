package Inventario.services.interfaces;

import Inventario.dto.DetalleVentaDTO;
import Inventario.dto.RegVentaDTO;
import Inventario.dto.VentaFiltroDTO;

import java.util.List;

public interface VentaService {

    int registrarVenta(RegVentaDTO regVentaDTO) throws Exception;
    List<DetalleVentaDTO> listarVentas() throws Exception;

    List<DetalleVentaDTO> listarVentasFechas(VentaFiltroDTO ventaFiltroDTO) throws Exception;
}
