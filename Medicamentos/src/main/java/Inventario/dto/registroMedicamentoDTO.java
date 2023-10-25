package inventario.dto;

public record RegistroMedicamentoDTO(
        String nombre,
        String laboratorioFabrica,
        String fechaFabricacion,
        String fechaVencimiento,
        int cantidadStock,
        float valorUnitario,
        boolean activo) {
}
