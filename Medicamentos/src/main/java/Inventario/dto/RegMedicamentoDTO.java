package Inventario.dto;

import java.time.LocalDate;
import java.util.Date;

public record RegMedicamentoDTO(
        String nombre,
        String laboratorioFabrica,
        String fechaFabricacion,
        String fechaVencimiento,
        int cantidadStock,
        float valorUnitario,
        boolean activo) {
}
