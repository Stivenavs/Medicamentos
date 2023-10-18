package Inventario.dto;

import java.util.Date;

public record RegVentaDTO(
        String fechaHora,
        int cantidad,
        int idMedicamento,
        float valorUnitario,
        float valorTotal
        ) {
}
