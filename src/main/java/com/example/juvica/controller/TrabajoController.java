package com.example.juvica.controller;

import com.example.juvica.dto.TrabajoDTO;
import com.example.juvica.entity.Trabajo;
import com.example.juvica.service.DtoConverterService;
import com.example.juvica.service.TrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/trabajos")
public class TrabajoController {

    @Autowired
    private TrabajoService trabajoService;

    @Autowired
    private DtoConverterService dtoConverterService;

    @GetMapping
    public ResponseEntity<List<TrabajoDTO>> obtenerTodosLosTrabajos() {
        List<Trabajo> trabajos = trabajoService.obtenerTodosLosTrabajos();
        trabajos.sort(Comparator.comparing(Trabajo::getFechaRegistro).reversed()); // Ordena de más nuevo a más antiguo
        List<TrabajoDTO> trabajosDTO = new ArrayList<>();
        for (Trabajo trabajo : trabajos) {
            trabajosDTO.add(dtoConverterService.convertToTrabajoDTO(trabajo));
        }
        return ResponseEntity.ok(trabajosDTO);
    }

    // Método para obtener un trabajo por ID
    @GetMapping("/{id}")
    public ResponseEntity<TrabajoDTO> obtenerTrabajoPorId(@PathVariable Long id) {
        Optional<Trabajo> trabajo = trabajoService.obtenerTrabajoPorId(id);
        if(trabajo.isPresent()){
            TrabajoDTO trabajoDTO = dtoConverterService.convertToTrabajoDTO(trabajo.get());
            return ResponseEntity.ok(trabajoDTO);
        }
        return ResponseEntity.notFound().build();
    }

    // Método para editar un trabajo existente
    @PutMapping("/{id}")
    public ResponseEntity<TrabajoDTO> editarTrabajo(@PathVariable Long id, @RequestBody Trabajo trabajoDetalles) {
        return trabajoService.obtenerTrabajoPorId(id).map(trabajo -> {
            trabajo.setNombre(trabajoDetalles.getNombre());
            trabajo.setComentarioLargo(trabajoDetalles.getComentarioLargo());
            trabajo.setListaImagenes(trabajoDetalles.getListaImagenes());
            Trabajo trabajoActualizado = trabajoService.guardarTrabajo(trabajo);
            TrabajoDTO trabajoDTO = dtoConverterService.convertToTrabajoDTO(trabajoActualizado);
            return ResponseEntity.ok(trabajoDTO);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Método para añadir imágenes a un trabajo existente
    @PostMapping("/{id}/imagenes")
    public ResponseEntity<TrabajoDTO> añadirImagenesATrabajo(@PathVariable Long id, @RequestBody List<String> nuevasImagenes) {
        return trabajoService.obtenerTrabajoPorId(id).map(trabajo -> {
            trabajo.setListaImagenes(nuevasImagenes);
            Trabajo trabajoActualizado = trabajoService.guardarTrabajo(trabajo);

            TrabajoDTO trabajoDTO = dtoConverterService.convertToTrabajoDTO(trabajoActualizado);
            return ResponseEntity.ok(trabajoDTO);
        }).orElse(ResponseEntity.notFound().build());
    }
}
