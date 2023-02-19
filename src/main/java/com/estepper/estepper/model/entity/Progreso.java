package com.estepper.estepper.model.entity;
import com.estepper.estepper.model.enums.TipoProgreso;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "progreso")
public class Progreso implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="idParticipante", nullable=false)
    private Participante participante;

    private Double dato;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = ISO.DATE)
    @NotNull
    private Date fecha;

    @Enumerated(value = EnumType.STRING)
    private TipoProgreso tipo;

    public Progreso(Integer id, Participante participante, Double dato, Date fecha, TipoProgreso tipo) {
        this.id = id;
        this.participante = participante;
        this.fecha = fecha;
        this.tipo = tipo;
    }

    public Progreso() {
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

    public Date getFecha() {
        return fecha;
    }

    public Double getDato() {
        return dato;
    }

    public void setDato(Double dato) {
        this.dato = dato;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public TipoProgreso getTipo() {
        return tipo;
    }

    public void setTipo(TipoProgreso tipo) {
        this.tipo = tipo;
    }


    


    
}
