package inventario.infraestructure.adapters.in.rest.out;

public record MedicamentoOutDTO(
        int Id,
        String nombre,
        String laboratorioFabrica,
        String fechaFabricacion,
        String fechaVencimiento,
        int cantidadStock,
        float valorUnitario,
        Boolean activo
) {
}
