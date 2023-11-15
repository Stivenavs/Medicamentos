package inventario.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Venta {
    private int id;

    private LocalDateTime FechaHora;

    private int cantidad;

    private Medicamento medicamento;

    private float valorUnitario;

    private float valorTotal;
}
