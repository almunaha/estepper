package com.estepper.estepper.model.entity;

import java.io.Serializable;

import com.estepper.estepper.model.enums.EstadoInvitacion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "invitaciones")
public class Invitacion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // actividad
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_actividad")
    private Actividad actividad;

    // participante
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_participante")
    private Participante participante;

    // coordinador
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_coordinador")
    private Coordinador coordinador;

    // estado
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", columnDefinition = "ENUM('ACEPTADA', 'RECHAZADA', 'PENDIENTE')")
    private EstadoInvitacion estado;

    public Invitacion() {
    }

    public Invitacion(Integer id, Actividad actividad, Participante participante, Coordinador coordinador,
            EstadoInvitacion estado) {
        this.id = id;
        this.actividad = actividad;
        this.participante = participante;
        this.coordinador = coordinador;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public Coordinador getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(Coordinador coordinador) {
        this.coordinador = coordinador;
    }

    public EstadoInvitacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoInvitacion estado) {
        this.estado = estado;
    }

}
