package inventario.dto;

import java.time.LocalDateTime;

public record VentaDetalleDTO(
        int id,
        LocalDateTime FechaHora,
        int cantidad,
        int idMedicamento,
        float valorTotal
) {
}
