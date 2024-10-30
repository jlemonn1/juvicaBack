package com.example.juvica.dto;

import java.util.List;

public class CategoriaConTrabajosDTO {

    private Long id;
    private String nombre;
    private String imgUrl;
    private List<TrabajoDTO> trabajos;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<TrabajoDTO> getTrabajos() {
        return trabajos;
    }

    public void setTrabajos(List<TrabajoDTO> trabajos) {
        this.trabajos = trabajos;
    }
}
