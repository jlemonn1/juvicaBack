package com.example.juvica.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Trabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToOne
    private Categoria categoria;

    @ElementCollection
    private List<String> listaImagenes;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    @Lob
    private String comentarioLargo;

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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @PrePersist
    protected void onCreate() {
        fechaRegistro = new Date();
    }
}