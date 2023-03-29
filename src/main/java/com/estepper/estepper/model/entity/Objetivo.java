package com.estepper.estepper.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.estepper.estepper.model.enums.EstadoObjetivo;
import com.estepper.estepper.model.enums.Repeticion;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "objetivo")
public class Objetivo implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne //Asegurarme de que sea asi
    @JoinColumn(name="idParticipante", nullable=false)
    private Participante participante;

    private String titulo;
    private String descripcion;
   
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = true, name = "repeticion", columnDefinition = "ENUM('NINGUNA', 'DIARIAMENTE', 'SEMANALMENTE', 'MENSUALMENTE', 'ANUALMENTE')")
    private Repeticion repeticion;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = ISO.DATE)
    @NotNull
    private Date fechaVencimiento;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = true, name = "estado", columnDefinition = "ENUM('COMPLETADO', 'PENDIENTE')")
    private EstadoObjetivo estado;

    public Objetivo(Integer id, Participante participante, String titulo, String descripcion, Repeticion repeticion, Date fechaVencimiento, EstadoObjetivo estado) {
        this.id = id;
        this.participante = participante;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.repeticion = repeticion;
        this.fechaVencimiento = fechaVencimiento;
        this.estado = estado;
    }

    public Objetivo() {
        estado = EstadoObjetivo.PENDIENTE;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Repeticion getRepeticion() {
        return repeticion;
    }

    public void setRepeticion(Repeticion repeticion) {
        this.repeticion = repeticion;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public EstadoObjetivo getEstadoObjetivo() {
        return estado;
    }

    public void setEstadoObjetivo(EstadoObjetivo estado) {
        this.estado = estado;
    }


    
}
