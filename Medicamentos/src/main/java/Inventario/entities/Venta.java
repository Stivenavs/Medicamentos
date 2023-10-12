package Inventario.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Data
public class Venta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime FechaHora;

    private int cantidad;

    @OneToOne
    private Medicamento medicamento;

    private float valorUnitario;

    private float valorTotal;

}
