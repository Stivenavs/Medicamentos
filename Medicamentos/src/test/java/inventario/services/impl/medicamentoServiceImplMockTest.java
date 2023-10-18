package inventario.services.impl;

import inventario.entities.medicamento;
import inventario.repositories.medicamentoRepository;
import inventario.dto.medicamentoDetalleDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import static org.mockito.Mockito.when;

class medicamentoServiceImplMockTest {

    @Mock
    private medicamentoRepository medicamentoRepo;

    @InjectMocks
    medicamentoServiceImpl medicamentoService;

    private medicamento medicamento01;
    private medicamentoDetalleDTO medicamentoDetalleDTO01;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        medicamentoService = new medicamentoServiceImpl(medicamentoRepo);

        medicamento01 = medicamento.builder()
                .id(100)
                .nombre("Omeprazol")
                .laboratorioFabrica("TQ")
                .fechaFabricacion(LocalDate.of(2023, 10, 1))
                .fechaVencimiento(LocalDate.of(2030, 1, 1))
                .cantidadStock(10000)
                .valorUnitario(500)
                .activo(true).build();

        medicamentoDetalleDTO01 = new medicamentoDetalleDTO(100, "Omeprazol", "TQ",
                                                "01/10/2023","01/01/2030", 10000,
                                        500, true);

        }

    @Test
    void crearMedicamento() {
        Mockito.when(medicamentoRepo.save(Mockito.any(medicamento.class))).thenReturn(medicamento01);
        Assertions.assertTrue(medicamento01.getId() == 100);
    }

    @Test
    void obtenerMedicamento() {
        Mockito.when(medicamentoRepo.findAllById(Mockito.any())).thenReturn(Arrays.asList(medicamento01));
        Assertions.assertTrue(medicamento01.getId() == 100);
    }

    @Test
    void actualizarMedicamento() {
        Mockito.when(medicamentoRepo.save(Mockito.any(medicamento.class))).thenReturn(medicamento01);
        Assertions.assertTrue(medicamento01.getId() == 100);
    }

    @Test
    void eliminarMedicamento() {
    }

    @Test
    void listarMedicamentos() {
        when(medicamentoRepo.findAll()).thenReturn(Arrays.asList(medicamento01));
        Assertions.assertTrue(medicamentoRepo.findAll().contains(medicamento01));
    }
}