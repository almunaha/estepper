package com.estepper.estepper.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import com.estepper.estepper.model.enums.EstadoObjetivo;
import com.estepper.estepper.model.enums.Repeticion;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "objetivo")
public class Objetivo implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne 
    @JoinColumn(name="idParticipante", nullable=false)
    private Participante participante;

    private String titulo;
    //private String descripcion;  LA QUITO PORQUE ES MUY SIMILAR A LO DE TÍTULO
   
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = true, name = "repeticion", columnDefinition = "ENUM('NINGUNA', 'DIARIAMENTE', 'SEMANALMENTE', 'MENSUALMENTE', 'ANUALMENTE')")
    private Repeticion repeticion;

    @Column
    private LocalDate fechaInicio;

    @Column
    private LocalDate fechaVencimiento;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = true, name = "estado", columnDefinition = "ENUM('COMPLETADO', 'PENDIENTE')")
    private EstadoObjetivo estado;

    public Objetivo(Integer id, Participante participante, String titulo, Repeticion repeticion,LocalDate fechaInicio, LocalDate fechaVencimiento, EstadoObjetivo estado) {
        this.id = id;
        this.participante = participante;
        this.titulo = titulo;
        this.fechaInicio = fechaInicio;
        this.repeticion = repeticion;
        this.fechaVencimiento = fechaVencimiento;
        this.estado = estado;
    }

    public Objetivo() {
        estado = EstadoObjetivo.PENDIENTE;
        fechaInicio = LocalDate.now();
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


    public Repeticion getRepeticion() {
        return repeticion;
    }

    public void setRepeticion(Repeticion repeticion) {
        this.repeticion = repeticion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public EstadoObjetivo getEstadoObjetivo() {
        return estado;
    }

    public void setEstadoObjetivo(EstadoObjetivo estado) {
        this.estado = estado;
    }


    
}
