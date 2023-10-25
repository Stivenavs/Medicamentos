package inventario.dto;

public record RegistroVentaDTO(
        String fechaHora,
        int cantidad,
        int idMedicamento,
        float valorUnitario,
        float valorTotal
        ) {
}
