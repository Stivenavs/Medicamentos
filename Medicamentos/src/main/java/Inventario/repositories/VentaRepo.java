package Inventario.repositories;

import Inventario.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepo extends JpaRepository<Venta, Integer> {

}
