package Inventario.services.impl;

import Inventario.dto.DetalleVentaDTO;
import Inventario.dto.RegVentaDTO;
import Inventario.dto.VentaFiltroDTO;
import Inventario.entities.Medicamento;
import Inventario.entities.Venta;
import Inventario.repositories.MedicamentoRepo;
import Inventario.repositories.VentaRepo;
import Inventario.services.interfaces.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService {

    private final MedicamentoRepo medicamentoRepo;
    private final VentaRepo ventaRepo;

    @Override
    public int registrarVenta(RegVentaDTO regVentaDTO) throws Exception {
       Venta venta = new Venta();
       Venta ventaNuevo;
       Medicamento medicamento;
       Optional<Medicamento> medicamentoOptional = medicamentoRepo.findById(regVentaDTO.idMedicamento());

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
           ventaNuevo = ventaRepo.save(venta);

           //disminuir el stock del medicmamento
           medicamento.setCantidadStock(medicamento.getCantidadStock()-regVentaDTO.cantidad());
           medicamentoRepo.save(medicamento);

           return ventaNuevo.getId();
       }
       catch (Exception ex){
           throw ex;
       }
    }

    @Override
    public List<DetalleVentaDTO> listarVentas() throws Exception {
        List<Venta> ventas = ventaRepo.findAll();
        List<DetalleVentaDTO> listaVentas = new ArrayList<>();

        if(ventas.isEmpty()){
            throw new Exception("No hay ventas registradas");
        }

        for (Venta venta: ventas) {
            listaVentas.add(new DetalleVentaDTO(venta.getId(),
                    venta.getFechaHora(),
                    venta.getCantidad(),
                    venta.getMedicamento().getId(),
                    venta.getValorTotal())
            );
        }

        return  listaVentas;
    }

    @Override
    public List<DetalleVentaDTO> listarVentasFechas(VentaFiltroDTO ventaFiltroDTO) throws Exception {
        List<Venta> ventas = ventaRepo.findAll();
        List<DetalleVentaDTO> listaVentas = new ArrayList<>();
        LocalDateTime fechaInicio;
        LocalDateTime fechaFinal;

        if(ventas.isEmpty()){
            throw new Exception("No hay ventas registradas");
        }

        for (Venta venta: ventas) {

            if(venta.getFechaHora().toLocalDate() >= ventaFiltroDTO.fechaFinal().tol ){

            }
            listaVentas.add(new DetalleVentaDTO(venta.getId(),
                    venta.getFechaHora(),
                    venta.getCantidad(),
                    venta.getMedicamento().getId(),
                    venta.getValorTotal())
            );
        }

        return  listaVentas;
    }
}
