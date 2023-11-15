package inventario.infraestructure.adapters.out.database.repositories;

import inventario.infraestructure.adapters.out.database.entities.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<VentaEntity, Integer> {

}
