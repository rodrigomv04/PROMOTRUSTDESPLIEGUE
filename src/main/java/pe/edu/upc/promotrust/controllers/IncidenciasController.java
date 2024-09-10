package pe.edu.upc.promotrust.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.promotrust.dtos.IncidenciasDTO;
import pe.edu.upc.promotrust.dtos.PreguntasDTO;
import pe.edu.upc.promotrust.entities.Incidencias;
import pe.edu.upc.promotrust.serviceinterface.IIncidenciasService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Incidencias")
public class IncidenciasController {
    @Autowired
    private IIncidenciasService iS;

    @GetMapping
    public List<IncidenciasDTO> list(){
        return iS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, IncidenciasDTO.class);
        }).collect(Collectors.toList());
    }
    @PostMapping
    public void registrar(@RequestBody IncidenciasDTO dto) {
        ModelMapper m = new ModelMapper();
        Incidencias in = m.map(dto, Incidencias.class);
        iS.insert(in);
    }
    @PutMapping
    public void modificar(@RequestBody IncidenciasDTO dto) {
        ModelMapper m = new ModelMapper();
        Incidencias in = m.map(dto, Incidencias.class);
        iS.update(in);
    }
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id) {
        iS.delete(id);
    }

    @GetMapping("/busquedas")
    public List<PreguntasDTO> buscar(@RequestParam String nombre) {
        return iS.buscarI(nombre).stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, PreguntasDTO.class);
        }).collect(Collectors.toList());
    }
}
