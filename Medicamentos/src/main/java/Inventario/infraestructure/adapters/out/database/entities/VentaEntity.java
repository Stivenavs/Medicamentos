package inventario.infraestructure.adapters.out.database.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "venta")
public class VentaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime FechaHora;

    private int cantidad;

    @ManyToOne
    private MedicamentoEntity medicamento;

    private float valorUnitario;

    private float valorTotal;

}
