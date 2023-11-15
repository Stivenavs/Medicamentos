package inventario.entities;

import inventario.infraestructure.adapters.out.database.entities.MedicamentoEntity;
import inventario.infraestructure.adapters.out.database.repositories.MedicamentoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

@DataJpaTest
public class MedicamentoRepositoryJUTest {

    @Autowired
    private MedicamentoRepository medicamentoRepo;

    @Test
    public void WhenFindById_ThenReturnMedicamento(){
        MedicamentoEntity medicamento01 = MedicamentoEntity.builder()
                .id(2)
                .nombre("Omeprazol")
                .laboratorioFabrica("TQ")
                .fechaFabricacion(LocalDate.of(2023, 10, 1))
                .fechaVencimiento(LocalDate.of(2030, 1, 1))
                .cantidadStock(10000)
                .valorUnitario(500)
                .activo(true).build();
        medicamentoRepo.save(medicamento01);

        Optional<MedicamentoEntity> medicamentoNuevo = medicamentoRepo.findById(medicamento01.getId());
        Assertions.assertTrue(medicamentoNuevo.isPresent());

    }

}
