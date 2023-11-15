package inventario.services.impl;

import inventario.application.MedicamentoServiceImpl;
import inventario.infraestructure.adapters.out.database.entities.MedicamentoEntity;
import inventario.infraestructure.adapters.in.rest.out.MedicamentoOutDTO;
import inventario.infraestructure.adapters.out.database.repositories.MedicamentoRepository;
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

class MedicamentoServiceImplMockTest {

    @InjectMocks
    MedicamentoServiceImpl medicamentoService;

    private MedicamentoEntity medicamento01;
    private MedicamentoOutDTO medicamentoDetalleDTO01;

    @Mock
    MedicamentoRepository medicamentoRepo;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        medicamentoService = new MedicamentoServiceImpl(medicamentoRepo);

        medicamento01 = MedicamentoEntity.builder()
                .id(100)
                .nombre("Omeprazol")
                .laboratorioFabrica("TQ")
                .fechaFabricacion(LocalDate.of(2023, 10, 1))
                .fechaVencimiento(LocalDate.of(2030, 1, 1))
                .cantidadStock(10000)
                .valorUnitario(500)
                .activo(true).build();

        medicamentoDetalleDTO01 = new MedicamentoOutDTO(100, "Omeprazol", "TQ",
                                                "01/10/2023","01/01/2030", 10000,
                                        500, true);

        }

    @Test
    void crearMedicamento() {
        Mockito.when(medicamentoRepo.save(Mockito.any(MedicamentoEntity.class))).thenReturn(medicamento01);
        Assertions.assertTrue(medicamento01.getId() == 100);
    }

    @Test
    void obtenerMedicamento() {
        Mockito.when(medicamentoRepo.findAllById(Mockito.any())).thenReturn(Arrays.asList(medicamento01));
        Assertions.assertTrue(medicamento01.getId() == 100);
    }

    @Test
    void actualizarMedicamento() {
        Mockito.when(medicamentoRepo.save(Mockito.any(MedicamentoEntity.class))).thenReturn(medicamento01);
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