package pe.edu.upc.promotrust.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.promotrust.dtos.PagoDTO;
import pe.edu.upc.promotrust.entities.Pago;
import pe.edu.upc.promotrust.serviceinterface.IPagoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pago")
public class PagoController {
    @Autowired
    private IPagoService pS;

    @PostMapping
    public void insertar(@RequestBody PagoDTO pagoDTO){
        ModelMapper e = new ModelMapper();
        Pago pago=e.map(pagoDTO, Pago.class);
        pS.insert(pago);
    }
    @GetMapping
    public List<PagoDTO> listar() {
        return pS.list().stream().map(x->{
            ModelMapper m=new ModelMapper();
            return m.map(x,PagoDTO.class);
        }).collect(Collectors.toList());
    }
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id) {
        pS.delete(id);
    }

    @GetMapping("/{id}")
    public PagoDTO listarId(@PathVariable("id") Integer id) {
        ModelMapper m=new ModelMapper();
        PagoDTO dto=m.map(pS.listId(id),PagoDTO.class);
        return dto;
    }
    @PutMapping
    public void modificar(@RequestBody PagoDTO dto) {
        ModelMapper m = new ModelMapper();
        Pago d = m.map(dto, Pago.class);
        pS.update(d);
    }
}
