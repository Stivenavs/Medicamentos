package inventario.repositories;

import inventario.entities.medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface medicamentoRepository extends JpaRepository<medicamento, Integer>{
}
