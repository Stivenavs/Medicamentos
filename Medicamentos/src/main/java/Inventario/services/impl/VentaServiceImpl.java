package inventario.services.impl;

import inventario.dto.ventaDetalleDTO;
import inventario.dto.registroVentaDTO;
import inventario.dto.filtroVentaDTO;
import inventario.entities.medicamento;
import inventario.entities.venta;
import inventario.repositories.medicamentoRepository;
import inventario.repositories.ventaRepository;
import inventario.services.interfaces.ventaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ventaServiceImpl implements ventaService {

    private final medicamentoRepository medicamentoRepository;
    private final ventaRepository ventaRepository;

    @Override
    public int registrarVenta(registroVentaDTO regVentaDTO) throws Exception {
       venta venta = new venta();
       inventario.entities.venta ventaNuevo;
       medicamento medicamento;
       Optional<inventario.entities.medicamento> medicamentoOptional = medicamentoRepository.findById(regVentaDTO.idMedicamento());

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
           throw ex;
       }
    }

    @Override
    public List<ventaDetalleDTO> listarVentas() throws Exception {
        List<venta> ventas = ventaRepository.findAll();
        List<ventaDetalleDTO> listaVentas = new ArrayList<>();

        if(ventas.isEmpty()){
            throw new Exception("No hay ventas registradas");
        }

        for (inventario.entities.venta venta: ventas) {
            listaVentas.add(new ventaDetalleDTO(venta.getId(),
                    venta.getFechaHora(),
                    venta.getCantidad(),
                    venta.getMedicamento().getId(),
                    venta.getValorTotal())
            );
        }

        return  listaVentas;
    }

    @Override
    public List<ventaDetalleDTO> listarVentasFechas(filtroVentaDTO ventaFiltroDTO) throws Exception {
        List<venta> ventas = ventaRepository.findAll();
        List<ventaDetalleDTO> listaVentas = new ArrayList<>();
        LocalDateTime fechaInicio;
        LocalDateTime fechaFinal;

        if(ventas.isEmpty()){
            throw new Exception("No hay ventas registradas");
        }

        for (inventario.entities.venta venta: ventas) {
            listaVentas.add(new ventaDetalleDTO(venta.getId(),
                    venta.getFechaHora(),
                    venta.getCantidad(),
                    venta.getMedicamento().getId(),
                    venta.getValorTotal())
            );
        }

        return  listaVentas;
    }
}
