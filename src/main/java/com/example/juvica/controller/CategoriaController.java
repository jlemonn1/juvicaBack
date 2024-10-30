package com.example.juvica.controller;

import com.example.juvica.dto.CategoriaConTrabajosDTO;
import com.example.juvica.dto.CategoriaDTO;
import com.example.juvica.dto.TrabajoDTO;
import com.example.juvica.entity.Categoria;
import com.example.juvica.entity.Trabajo;
import com.example.juvica.service.CategoriaService;
import com.example.juvica.service.DtoConverterService;
import com.example.juvica.service.TrabajoService;

import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private TrabajoService trabajoService;

    @Autowired
    private DtoConverterService dtoConverterService;

    // Método para crear una nueva categoría
    @PostMapping
    public ResponseEntity<CategoriaDTO> crearCategoria(@RequestBody Categoria categoria) {
        Categoria nuevaCategoria = categoriaService.guardarCategoria(categoria);
        CategoriaDTO categoriaDTO = dtoConverterService.convertToCategoriaDTO(nuevaCategoria);
        return ResponseEntity.ok(categoriaDTO);
    }

    // Método para obtener todas las categorías
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> obtenerTodasLasCategorias() {
        List<Categoria> categorias = categoriaService.obtenerTodasLasCategorias();
        List<CategoriaDTO> categoriasDTO = new ArrayStack<>();
        for(Categoria categoria : categorias){
            categoriasDTO.add(dtoConverterService.convertToCategoriaDTO(categoria));
        }
        return ResponseEntity.ok(categoriasDTO);
    }

    // Método para obtener una categoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaConTrabajosDTO> obtenerCategoriaPorId(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaService.obtenerCategoriaPorId(id);
        if(categoria.isPresent()){
            CategoriaConTrabajosDTO categoriaConTrabajosDTO = dtoConverterService.convertToCategoriaConTrabajosDTO(categoria.get());
            return ResponseEntity.ok(categoriaConTrabajosDTO);
        }
        else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Método para añadir un trabajo a una categoría
    @PostMapping("/{id}/trabajos")
    public ResponseEntity<TrabajoDTO> añadirTrabajoACategoria(@PathVariable Long id, @RequestBody Trabajo trabajo) {
        Optional<Categoria> categoriaOpt = categoriaService.obtenerCategoriaPorId(id);
        if (categoriaOpt.isPresent()) {
            Categoria categoria = categoriaOpt.get();
            trabajo.setCategoria(categoria); // Suponiendo que tienes un método para establecer la categoría en Trabajo
            Trabajo nuevoTrabajo = trabajoService.guardarTrabajo(trabajo);
            categoria.getTrabajos().add(nuevoTrabajo); // Añadimos el trabajo a la lista de trabajos de la categoría
            categoriaService.guardarCategoria(categoria); // Guardamos la categoría nuevamente
            TrabajoDTO trabajoDTO = dtoConverterService.convertToTrabajoDTO(nuevoTrabajo);
            return ResponseEntity.ok(trabajoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
