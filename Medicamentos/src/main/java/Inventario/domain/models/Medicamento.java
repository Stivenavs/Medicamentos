package inventario.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Medicamento {
    private int id;

    private String nombre;

    private String laboratorioFabrica;

    private LocalDate fechaFabricacion;

    private LocalDate fechaVencimiento;

    private int cantidadStock;

    private float valorUnitario;

    private Boolean activo;
}
