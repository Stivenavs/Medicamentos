package inventario.services.impl;

import inventario.dto.VentaDetalleDTO;
import inventario.dto.RegistroVentaDTO;
import inventario.dto.FiltroVentaDTO;
import inventario.entities.Medicamento;
import inventario.entities.Venta;
import inventario.repositories.MedicamentoRepository;
import inventario.repositories.VentaRepository;
import inventario.services.interfaces.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService {

    private final MedicamentoRepository medicamentoRepository;
    private final VentaRepository ventaRepository;

    @Override
    public int registrarVenta(RegistroVentaDTO regVentaDTO) throws Exception {
       Venta venta = new Venta();
       Venta ventaNuevo;
       Medicamento medicamento;
       Optional<Medicamento> medicamentoOptional = medicamentoRepository.findById(regVentaDTO.idMedicamento());

       if(medicamentoOptional.isEmpty()){
           throw new Exception("No existe un medicamento con el id: " + regVentaDTO.idMedicamento());
       }

       medicamento = medicamentoOptional.get();

       if(medicamento.getCantidadStock() < regVentaDTO.cantidad()){
           throw new Exception("Cantidad en stock insuficiente de " + medicamento.getNombre());
       }

       try{
           venta.setFechaHora(LocalDateTime.now());
           venta.setCantidad(regVentaDTO.cantidad());
           venta.setMedicamento(medicamento);
           venta.setValorUnitario(medicamento.getValorUnitario());
           venta.setValorTotal(medicamento.getValorUnitario() * regVentaDTO.cantidad());
           ventaNuevo = ventaRepository.save(venta);

           //disminuir el stock del medicmamento
           medicamento.setCantidadStock(medicamento.getCantidadStock()-regVentaDTO.cantidad());
           medicamentoRepository.save(medicamento);

           return ventaNuevo.getId();
       }
       catch (Exception ex){
           throw new Exception("Erro al registrar la venta");
       }
    }

    @Override
    public List<VentaDetalleDTO> listarVentas() throws Exception {
        List<Venta> ventas = ventaRepository.findAll();
        List<VentaDetalleDTO> listaVentas = new ArrayList<>();

        if(ventas.isEmpty()){
            throw new Exception("No hay ventas registradas");
        }

        for (Venta venta: ventas) {
            listaVentas.add(new VentaDetalleDTO(venta.getId(),
                    venta.getFechaHora(),
                    venta.getCantidad(),
                    venta.getMedicamento().getId(),
                    venta.getValorTotal())
            );
        }

        return  listaVentas;
    }

    @Override
    public List<VentaDetalleDTO> listarVentasFechas(FiltroVentaDTO ventaFiltroDTO) throws Exception {
        List<Venta> ventas = ventaRepository.findAll();
        List<VentaDetalleDTO> listaVentas = new ArrayList<>();
        LocalDateTime fechaInicio;
        LocalDateTime fechaFinal;

        if(ventas.isEmpty()){
            throw new Exception("No hay ventas registradas");
        }

        for (Venta venta: ventas) {
            listaVentas.add(new VentaDetalleDTO(venta.getId(),
                    venta.getFechaHora(),
                    venta.getCantidad(),
                    venta.getMedicamento().getId(),
                    venta.getValorTotal())
            );
        }

        return  listaVentas;
    }
}
