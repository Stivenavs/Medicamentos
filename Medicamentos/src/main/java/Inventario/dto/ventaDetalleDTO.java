package inventario.dto;

import java.time.LocalDateTime;

public record ventaDetalleDTO(
        int id,
        LocalDateTime FechaHora,
        int cantidad,
        int idMedicamento,
        float valorTotal
) {
}
