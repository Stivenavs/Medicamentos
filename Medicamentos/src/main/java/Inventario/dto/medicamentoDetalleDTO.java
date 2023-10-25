package inventario.dto;

public record MedicamentoDetalleDTO(int Id,
                                    String nombre,
                                    String laboratorioFabrica,
                                    String fechaFabricacion,
                                    String fechaVencimiento,
                                    int cantidadStock,
                                    float valorUnitario,
                                    Boolean activo
) {
}
