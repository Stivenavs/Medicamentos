package inventario.application;

import inventario.domain.models.Medicamento;
import inventario.domain.models.Venta;
import inventario.infraestructure.adapters.in.rest.out.VentaOutDTO;
import inventario.infraestructure.adapters.in.rest.in.RegistroInDTO;
import inventario.infraestructure.adapters.in.rest.in.FiltroVentaInDTO;
import inventario.infraestructure.adapters.out.database.entities.MedicamentoEntity;
import inventario.infraestructure.adapters.out.database.entities.VentaEntity;
import inventario.infraestructure.adapters.out.database.repositories.MedicamentoRepository;
import inventario.infraestructure.adapters.out.database.repositories.VentaRepository;
import inventario.infraestructure.adapters.out.mappers.MedicamentoMapper;
import inventario.infraestructure.adapters.out.mappers.VentaMapper;
import inventario.infraestructure.ports.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService {

    private final MedicamentoRepository medicamentoRepository;
    private final VentaRepository ventaRepository;
    private VentaMapper ventaMapper = new VentaMapper();
    private MedicamentoMapper medicamentoMapper = new MedicamentoMapper();


    @Override
    public int registrarVenta(RegistroInDTO regVentaDTO) throws Exception {
       VentaEntity ventaEntity = new VentaEntity();
       Venta venta = new Venta();
       VentaEntity ventaNuevo = new VentaEntity();
       MedicamentoEntity medicamentoEntity = new MedicamentoEntity();
       Medicamento medicamento = new Medicamento();
       Optional<MedicamentoEntity> medicamentoOptional = medicamentoRepository.findById(regVentaDTO.idMedicamento());

       if(medicamentoOptional.isEmpty()){
           throw new Exception("No existe un medicamento con el id: " + regVentaDTO.idMedicamento());
       }

       medicamentoEntity = medicamentoOptional.get();
       medicamento = medicamentoMapper.entityToModel(medicamentoEntity);

       if(medicamento.getCantidadStock() < regVentaDTO.cantidad()){
           throw new Exception("Cantidad en stock insuficiente de " + medicamento.getNombre());
       }

       try{
           venta.setFechaHora(LocalDateTime.now());
           venta.setCantidad(regVentaDTO.cantidad());
           venta.setMedicamento(medicamento);
           venta.setValorUnitario(medicamento.getValorUnitario());
           venta.setValorTotal(medicamento.getValorUnitario() * regVentaDTO.cantidad());

           ventaEntity = ventaMapper.modelToEntity(venta);
           ventaNuevo = ventaRepository.save(ventaEntity);


           medicamento.setCantidadStock(medicamento.getCantidadStock()-regVentaDTO.cantidad());

           medicamentoEntity = medicamentoMapper.modelToEntity(medicamento);
           medicamentoRepository.save(medicamentoEntity);

           return ventaNuevo.getId();
       }
       catch (Exception ex){
           throw new Exception("Error al registrar la venta");
       }
    }

    @Override
    public List<VentaOutDTO> listarVentas(FiltroVentaInDTO filtroVentaDTO) throws Exception {
        List<VentaOutDTO> listaVentas = new ArrayList<>();

        if(filtroVentaDTO.fechaInicio().equals("") && filtroVentaDTO.fechaFinal().equals("")){
            listaVentas =  listarVentasTodas();
        }
        else{
            listaVentas = listarVentasFiltros(filtroVentaDTO);
        }

        return listaVentas;
    }

    private List<VentaOutDTO> listarVentasTodas() throws Exception {
        List<VentaEntity> ventas = ventaRepository.findAll();
        List<VentaOutDTO> listaVentas = new ArrayList<>();
        Venta venta = new Venta();

        if(ventas.isEmpty()){
            throw new Exception("No hay ventas registradas");
        }

        for (VentaEntity ventaEntity: ventas) {
            venta = ventaMapper.entityToModel(ventaEntity);

            listaVentas.add(new VentaOutDTO(venta.getId(),
                    venta.getFechaHora(),
                    venta.getCantidad(),
                    venta.getMedicamento().getId(),
                    venta.getMedicamento().getNombre(),
                    venta.getValorTotal())
            );
        }

        return  listaVentas;
    }

    private List<VentaOutDTO> listarVentasFiltros(FiltroVentaInDTO filtroVentaDTO) throws Exception {
        List<VentaOutDTO> listaVentas =  listarVentasTodas();
        List<VentaOutDTO> listaVentasFiltro = new ArrayList<>();
        LocalDateTime fechaInicio;
        LocalDateTime fechaFin;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        fechaInicio = LocalDateTime.parse(filtroVentaDTO.fechaInicio(), formatter);
        fechaFin = LocalDateTime.parse(filtroVentaDTO.fechaFinal(), formatter);

        for (VentaOutDTO venta: listaVentas) {
            if ((venta.FechaHora().isEqual(fechaInicio) || venta.FechaHora().isAfter(fechaInicio))
                    && (venta.FechaHora().isEqual(fechaFin) || venta.FechaHora().isBefore(fechaFin))){

                listaVentasFiltro.add(venta);
            }
        }

        return listaVentasFiltro;
    }
}
