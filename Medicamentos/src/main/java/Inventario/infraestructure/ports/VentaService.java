package inventario.infraestructure.ports;

import inventario.infraestructure.adapters.in.rest.out.VentaOutDTO;
import inventario.infraestructure.adapters.in.rest.in.RegistroInDTO;
import inventario.infraestructure.adapters.in.rest.in.FiltroVentaInDTO;

import java.util.List;

public interface VentaService {

    int registrarVenta(RegistroInDTO regVentaDTO) throws Exception;
    List<VentaOutDTO> listarVentas(FiltroVentaInDTO ventaFiltroDTO) throws Exception;

}
