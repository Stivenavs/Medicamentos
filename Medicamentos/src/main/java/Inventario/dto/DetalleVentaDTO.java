package Inventario.dto;

import java.time.LocalDateTime;
import java.util.Date;

public record DetalleVentaDTO(
        int id,
        LocalDateTime FechaHora,
        int cantidad,
        int idMedicamento,
        float valorTotal
) {
}
