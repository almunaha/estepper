package com.estepper.estepper.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import com.estepper.estepper.model.enums.Categoria;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;

@Entity
@Table(name = "actividades")
public class Actividad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String ubicacion;

    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", columnDefinition = "ENUM('DEPORTE', 'ALIMENTACIÓN', 'SALUDMENTAL', 'EDUCACIÓN')")
    private Categoria categoria;

    private Integer numParticipantes;

    private Integer plazas;

    @Column(name = "fechaRealizacion", columnDefinition = "DATETIME")
    private LocalDateTime fechaRealizacion;

    private String foto;

    @ManyToAny
    @JoinTable(name = "asistencia_actividades", joinColumns = @JoinColumn(name = "id_actividad"), inverseJoinColumns = @JoinColumn(name = "id_participante"))
    private Set<Participante> participantes = new HashSet<>();

    public Actividad() {
    }

    public Actividad(Integer id, String nombre, String ubicacion, String descripcion, Categoria categoria,
            Integer numParticipantes,
            Integer plazas, LocalDateTime fechaRealizacion, String foto, Set<Participante> participantes) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.numParticipantes = numParticipantes;
        this.plazas = plazas;
        this.fechaRealizacion = fechaRealizacion;
        this.foto = foto;
        this.participantes = participantes;
    }

    public Set<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(Set<Participante> participantes) {
        this.participantes = participantes;
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

}
