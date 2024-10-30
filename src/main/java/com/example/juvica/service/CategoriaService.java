package com.example.juvica.service;

import com.example.juvica.entity.Categoria;
import com.example.juvica.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Método para guardar una nueva categoría
    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // Método para obtener una categoría por su ID
    public Optional<Categoria> obtenerCategoriaPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    // Método para obtener todas las categorías
    public List<Categoria> obtenerTodasLasCategorias() {
        return categoriaRepository.findAll();
    }

    // Método para actualizar una categoría
    public Categoria actualizarCategoria(Long id, Categoria categoriaDetalles) {
        return categoriaRepository.findById(id).map(categoria -> {
            categoria.setNombre(categoriaDetalles.getNombre());
            categoria.setImUrlg(categoriaDetalles.getImgUrl());
            categoria.setTrabajos(categoriaDetalles.getTrabajos());
            return categoriaRepository.save(categoria);
        }).orElseThrow(() -> new RuntimeException("Categoría no encontrada con el ID: " + id));
    }

    // Método para eliminar una categoría por su ID
    public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
}
