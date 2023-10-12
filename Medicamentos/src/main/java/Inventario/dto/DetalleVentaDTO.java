package Inventario.dto;

import java.util.Date;

public record DetalleVentaDTO(
        int id,
        Date FechaHora,
        int cantidad,
        RegMedicamentoDTO medicamentoDTO,
        float valorTotal
) {
}
