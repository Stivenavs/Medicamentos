package inventario.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Data
public class venta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime FechaHora;

    private int cantidad;

    @OneToOne
    private inventario.entities.medicamento medicamento;

    private float valorUnitario;

    private float valorTotal;

}
