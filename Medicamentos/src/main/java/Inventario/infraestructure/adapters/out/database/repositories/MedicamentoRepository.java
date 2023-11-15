package inventario.infraestructure.adapters.out.database.repositories;

import inventario.infraestructure.adapters.out.database.entities.MedicamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoRepository extends JpaRepository<MedicamentoEntity, Integer>{
}
