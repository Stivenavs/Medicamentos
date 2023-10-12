package Inventario.dto;

import java.util.Date;

public record RegVentaDTO(
        Date FechaHora,
        int cantidad,
        RegMedicamentoDTO medicamentoDTO,
        float valorTotal
        ) {
}
