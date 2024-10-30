package com.example.juvica.service;

import com.example.juvica.dto.CategoriaConTrabajosDTO;
import com.example.juvica.dto.CategoriaDTO;
import com.example.juvica.dto.TrabajoDTO;
import com.example.juvica.entity.Categoria;
import com.example.juvica.entity.Trabajo;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class DtoConverterService {

    public CategoriaConTrabajosDTO convertToCategoriaConTrabajosDTO(Categoria categoria) {
        CategoriaConTrabajosDTO dto = new CategoriaConTrabajosDTO();
        dto.setId(categoria.getId());
        dto.setNombre(categoria.getNombre());
        dto.setImgUrl(categoria.getImgUrl());
        dto.setTrabajos(categoria.getTrabajos().stream().map(this::convertToTrabajoDTO).collect(Collectors.toList()));
        return dto;
    }

    public CategoriaDTO convertToCategoriaDTO(Categoria categoria) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(categoria.getId());
        dto.setNombre(categoria.getNombre());
        dto.setImgUrl(categoria.getImgUrl());
        return dto;
    }

    public TrabajoDTO convertToTrabajoDTO(Trabajo trabajo) {
        TrabajoDTO dto = new TrabajoDTO();
        dto.setId(trabajo.getId());
        dto.setNombre(trabajo.getNombre());
        dto.setListaImagenes(trabajo.getListaImagenes());
        dto.setFechaRegistro(trabajo.getFechaRegistro());
        dto.setComentarioLargo(trabajo.getComentarioLargo());
        return dto;
    }
}
