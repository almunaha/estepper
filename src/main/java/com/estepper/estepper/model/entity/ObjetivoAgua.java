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

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "objetivoAgua")
public class ObjetivoAgua implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idParticipante", nullable = false)
    private Participante participante;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = ISO.DATE)
    @NotNull
    private Date fecha;

    private Integer cantidadObjetivo;
    private Integer vasos;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = true, name = "estado", columnDefinition = "ENUM('COMPLETADO', 'PENDIENTE')")
    private EstadoObjetivo estado;

    public ObjetivoAgua(Integer id, Participante participante, Date fecha, Integer cantidadObjetivo, Integer vasos,
            EstadoObjetivo estado) {
        this.id = id;
        this.participante = participante;
        this.fecha = fecha;
        this.cantidadObjetivo = cantidadObjetivo;
        this.vasos = vasos;
        this.estado = estado;
    }

    public ObjetivoAgua() {
        estado = EstadoObjetivo.PENDIENTE;
        fecha = new Date();
        cantidadObjetivo = 8; // 2 litros
        vasos = 0;

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

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getCantidadObjetivo() {
        return cantidadObjetivo;
    }

    public void setCantidadObjetivo(Integer cantidadObjetivo) {
        this.cantidadObjetivo = cantidadObjetivo;
    }

    public Integer getVasos() {
        return vasos;
    }

    public void setVasos(Integer vasos) {
        this.vasos = vasos;
    }

    public EstadoObjetivo getEstadoObjetivo() {
        return estado;
    }

    public void setEstadoObjetivo(EstadoObjetivo estado) {
        this.estado = estado;
    }

}
