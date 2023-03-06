package com.estepper.estepper.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.estepper.estepper.model.enums.Categoria;
import com.estepper.estepper.model.enums.EstadoActividad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="actividades")
public class Actividad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String ubicacion;

    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", columnDefinition = "ENUM('DEPORTE', 'ALIMENTACIÓN')")
    private Categoria categoria; 

    private Integer numParticipantes;

    private Integer plazas;

    @Column(name = "fechaRealizacion", columnDefinition = "DATETIME")
    private LocalDateTime fechaRealizacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", columnDefinition = "ENUM('DISPONIBLE', 'FINALIZADA')")
    private EstadoActividad estado; 

    private String foto;

    public Actividad(){}

    public Actividad(Integer id, String nombre, String ubicacion, String descripcion, Categoria categoria, Integer numParticipantes,
            Integer plazas, LocalDateTime fechaRealizacion, EstadoActividad estado, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.numParticipantes = numParticipantes;
        this.plazas = plazas;
        this.fechaRealizacion = fechaRealizacion;
        this.estado = estado;
        this.foto = foto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getNumParticipantes() {
        return numParticipantes;
    }

    public void setNumParticipantes(Integer numParticipantes) {
        this.numParticipantes = numParticipantes;
    }

    public Integer getPlazas() {
        return plazas;
    }

    public void setPlazas(Integer plazas) {
        this.plazas = plazas;
    }

    public LocalDateTime getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(LocalDateTime fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public EstadoActividad getEstado() {
        return estado;
    }

    public void setEstado(EstadoActividad estado) {
        this.estado = estado;
    }





}
