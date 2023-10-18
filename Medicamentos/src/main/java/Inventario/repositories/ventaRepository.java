package inventario.repositories;

import inventario.entities.venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ventaRepository extends JpaRepository<venta, Integer> {

}
