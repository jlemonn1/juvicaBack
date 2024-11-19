package com.example.juvica.controller;

import com.example.juvica.dto.TrabajoDTO;
import com.example.juvica.entity.Trabajo;
import com.example.juvica.service.DtoConverterService;
import com.example.juvica.service.TrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
        if (trabajo.isPresent()) {
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
            // trabajo.setListaImagenes(trabajoDetalles.getListaImagenes());
            Trabajo trabajoActualizado = trabajoService.guardarTrabajo(trabajo);
            TrabajoDTO trabajoDTO = dtoConverterService.convertToTrabajoDTO(trabajoActualizado);
            return ResponseEntity.ok(trabajoDTO);
        }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/media")
    public ResponseEntity<TrabajoDTO> añadirMediaATrabajo(
        @PathVariable Long id,
        @RequestParam("mediaFiles") List<MultipartFile> files) throws IllegalStateException, IOException {
    
    Optional<Trabajo> trabajo = trabajoService.obtenerTrabajoPorId(id);
    if (trabajo.isPresent()) {
        Trabajo trabajoEditar = trabajo.get();

        List<String> mediaPaths = new ArrayList<>(); // Cambiado a lista para guardar los paths
        String baseDir = "/opt/juvica/media/";

        for (MultipartFile file : files) {
            String extension = Objects.requireNonNull(file.getContentType()).split("/")[1];
            String fileName = UUID.randomUUID().toString() + "." + extension;

            // Determina el tipo y el directorio de destino
            String type = file.getContentType().startsWith("image") ? "image" : "video";
            String uploadDir = baseDir + (type.equals("image") ? "image/" : "video/");
            
            // Asegúrate de que el directorio exista
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Establece la ruta completa del archivo
            String filePath = uploadDir + fileName;
            File destinationFile = new File(filePath);
            file.transferTo(destinationFile);

            // Crea el valor con URL y tipo
            String mediaValue = "/media/" + (type.equals("image") ? "image/" : "video/") + fileName;

            mediaPaths.add(mediaValue); // Almacena el path completo en la lista
        }

        trabajoEditar.setListaMedia(mediaPaths); // Establece la lista en el trabajo
        trabajoService.actualizarTrabajo(id, trabajoEditar);

        TrabajoDTO trabajoDTO = dtoConverterService.convertToTrabajoDTO(trabajoEditar);
        return ResponseEntity.ok(trabajoDTO);
    }
    return ResponseEntity.badRequest().build();
}

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable Long id) {
        Optional<Trabajo> trabajoEliminar = trabajoService.obtenerTrabajoPorId(id);
        if (trabajoEliminar.isPresent()) {
            trabajoService.eliminarTrabajo(id);
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.badRequest().body(false);
    }
}
