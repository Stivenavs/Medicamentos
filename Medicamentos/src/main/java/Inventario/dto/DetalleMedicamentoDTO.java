package Inventario.dto;

import java.time.LocalDate;

public record DetalleMedicamentoDTO(
        int Id,
        String nombre,
        String laboratorioFabrica,
        String fechaFabricacion,
        String fechaVencimiento,
        int cantidadStock,
        float valorUnitario,
        Boolean activo) {
}
