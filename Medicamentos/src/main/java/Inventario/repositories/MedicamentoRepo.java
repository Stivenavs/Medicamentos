package Inventario.repositories;

import Inventario.entities.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoRepo extends JpaRepository<Medicamento, Integer>{
   //Medicamento findById(int id) ;
}
