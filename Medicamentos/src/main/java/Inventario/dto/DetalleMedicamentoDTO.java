package Inventario.dto;

import java.time.LocalDate;

public record DetalleMedicamentoDTO(
        int Id,
        String nombre,
        String laboratorioFabrica,
        LocalDate fechaFabricacion,
        LocalDate FechaVencimiento,
        int cantidadStock,
        float valorUnitario,
        Boolean activo) {
}
