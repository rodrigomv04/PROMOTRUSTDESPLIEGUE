package pe.edu.upc.promotrust.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.promotrust.dtos.ContratoDTO;
import pe.edu.upc.promotrust.entities.Contrato;
import pe.edu.upc.promotrust.serviceinterface.IContratoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contrato")
public class ContratoController {

    @Autowired
    private IContratoService cS;

    @GetMapping
    public List<ContratoDTO> listar(){
        return cS.list().stream().map(x->{
            ModelMapper m=new ModelMapper();
            return m.map(x, ContratoDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void registrar(@RequestBody ContratoDTO dto){
        ModelMapper m=new ModelMapper();
        Contrato ct=m.map(dto, Contrato.class);
        cS.insert(ct);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id) {
        cS.delete(id);
    }
}