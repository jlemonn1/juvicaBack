package com.example.juvica.service;

import com.example.juvica.entity.Trabajo;
import com.example.juvica.repository.TrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrabajoService {

    @Autowired
    private TrabajoRepository trabajoRepository;

    // Método para guardar un nuevo trabajo
    public Trabajo guardarTrabajo(Trabajo trabajo) {
        return trabajoRepository.save(trabajo);
    }

    // Método para obtener un trabajo por su ID
    public Optional<Trabajo> obtenerTrabajoPorId(Long id) {
        return trabajoRepository.findById(id);
    }

    // Método para obtener todos los trabajos
    public List<Trabajo> obtenerTodosLosTrabajos() {
        return trabajoRepository.findAll();
    }

    // Método para actualizar un trabajo
    public Trabajo actualizarTrabajo(Long id, Trabajo trabajoDetalles) {
        return trabajoRepository.findById(id).map(trabajo -> {
            trabajo.setNombre(trabajoDetalles.getNombre());
            trabajo.setListaImagenes(trabajoDetalles.getListaImagenes());
            trabajo.setComentarioLargo(trabajoDetalles.getComentarioLargo());
            return trabajoRepository.save(trabajo);
        }).orElseThrow(() -> new RuntimeException("Trabajo no encontrado con el ID: " + id));
    }

    // Método para eliminar un trabajo por su ID
    public void eliminarTrabajo(Long id) {
        trabajoRepository.deleteById(id);
    }
}
