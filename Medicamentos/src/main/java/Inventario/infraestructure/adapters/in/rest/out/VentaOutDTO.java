package inventario.infraestructure.adapters.in.rest.out;

import java.time.LocalDateTime;

public record VentaOutDTO(
        int id,
        LocalDateTime FechaHora,
        int cantidad,
        int idMedicamento,
        String medicamento,
        float valorTotal
) {
}
