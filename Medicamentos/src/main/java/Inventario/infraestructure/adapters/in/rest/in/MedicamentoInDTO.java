package inventario.infraestructure.adapters.in.rest.in;

public record MedicamentoInDTO(
        String nombre,
        String laboratorioFabrica,
        String fechaFabricacion,
        String fechaVencimiento,
        int cantidadStock,
        float valorUnitario,
        boolean activo) {
}
