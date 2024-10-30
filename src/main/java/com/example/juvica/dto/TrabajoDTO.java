package com.example.juvica.dto;

import java.util.Date;
import java.util.List;

public class TrabajoDTO {

    private Long id;
    private String nombre;
    private List<String> listaImagenes;
    private Date fechaRegistro;
    private String comentarioLargo;

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

    public List<String> getListaImagenes() {
        return listaImagenes;
    }

    public void setListaImagenes(List<String> listaImagenes) {
        this.listaImagenes = listaImagenes;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getComentarioLargo() {
        return comentarioLargo;
    }

    public void setComentarioLargo(String comentarioLargo) {
        this.comentarioLargo = comentarioLargo;
    }
}
