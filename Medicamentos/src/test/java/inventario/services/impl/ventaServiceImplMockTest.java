package inventario.services.impl;

import inventario.application.VentaServiceImpl;
import inventario.infraestructure.adapters.out.database.repositories.MedicamentoRepository;
import inventario.infraestructure.adapters.out.database.repositories.VentaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class VentaServiceImplMockTest {

    @Mock
    private MedicamentoRepository medicamentoRepository;
    @Mock
    private VentaRepository ventaRepository;

    private VentaServiceImpl ventaService;
    @Test
    void registrarVenta() {
    }

    @Test
    void listarVentas() {
    }

    @Test
    void listarVentasFechas() {
    }
}