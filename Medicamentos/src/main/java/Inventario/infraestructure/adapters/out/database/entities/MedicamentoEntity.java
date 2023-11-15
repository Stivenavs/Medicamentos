package inventario.infraestructure.adapters.out.database.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medicamento")
public class MedicamentoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    private String laboratorioFabrica;

    private LocalDate fechaFabricacion;

    private LocalDate fechaVencimiento;

    private int cantidadStock;

    private float valorUnitario;

    private Boolean activo;
}

