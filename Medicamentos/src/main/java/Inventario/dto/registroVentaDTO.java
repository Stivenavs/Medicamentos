package inventario.dto;

public record registroVentaDTO(
        String fechaHora,
        int cantidad,
        int idMedicamento,
        float valorUnitario,
        float valorTotal
        ) {
}
