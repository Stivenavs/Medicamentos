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
import java.util.Optional;
import static org.mockito.Mockito.when;

class medicamentoServiceImplMockTest {

    @Mock
    private medicamentoRepository medicamentoRepo;

    @InjectMocks
    medicamentoServiceImpl medicamentoService;

    private medicamento medicamento01;
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
    }

    @Test
    void crearMedicamento() {

        medicamento medicamento02 = new medicamento();

        Mockito.when(medicamentoRepo.save(medicamento01)).thenReturn();

    }

    @Test
    void obtenerMedicamento() {

        medicamentoRepo.save(medicamento01);

        Optional<medicamento> medicamentoNuevo = medicamentoRepo.findById(medicamento01.getId());
        Assertions.assertTrue(medicamentoNuevo.isPresent());
    }

    @Test
    void actualizarMedicamento() {
    }

    @Test
    void eliminarMedicamento() {
    }

    @Test
    void listarMedicamentos() {
        when(medicamentoRepo.findAll()).thenReturn(Arrays.asList(medicamento01));
    }
}