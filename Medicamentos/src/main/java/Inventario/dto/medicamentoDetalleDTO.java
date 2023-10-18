package inventario.dto;

public record medicamentoDetalleDTO(int Id,
                                    String nombre,
                                    String laboratorioFabrica,
                                    String fechaFabricacion,
                                    String fechaVencimiento,
                                    int cantidadStock,
                                    float valorUnitario,
                                    Boolean activo
) {
}
