package inventario.dto;

public record registroMedicamentoDTO(
        String nombre,
        String laboratorioFabrica,
        String fechaFabricacion,
        String fechaVencimiento,
        int cantidadStock,
        float valorUnitario,
        boolean activo) {
}
