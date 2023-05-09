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
@Table(name = "ObjetivoDescanso")
public class ObjetivoDescanso implements Serializable {

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

    private Integer horasSuenio;
    private Integer minutosSuenio;
    private Integer horasSuenioObjetivo;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = true, name = "estado", columnDefinition = "ENUM('COMPLETADO', 'PENDIENTE')")
    private EstadoObjetivo estado;

    public ObjetivoDescanso(Integer id, Participante participante, Date fecha, Integer horasSuenio,
            Integer minutosSuenio, Integer horasSuenioObjetivo, EstadoObjetivo estado) {
        this.id = id;
        this.participante = participante;
        this.fecha = fecha;
        this.horasSuenio = horasSuenio;
        this.minutosSuenio = minutosSuenio;
        this.horasSuenioObjetivo = horasSuenioObjetivo;
        this.estado = estado;
    }

    public ObjetivoDescanso() {
        estado = EstadoObjetivo.PENDIENTE;
        fecha = new Date();
        horasSuenioObjetivo = 8;
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

    public Integer getHorasSuenio() {
        return horasSuenio;
    }

    public void setHorasSuenio(Integer horasSuenio) {
        this.horasSuenio = horasSuenio;
    }

    public Integer getMinutosSuenio() {
        return minutosSuenio;
    }

    public void setMinutosSuenio(Integer minutosSuenio) {
        this.minutosSuenio = minutosSuenio;
    }

    public Integer getHorasSuenioObjetivo() {
        return horasSuenioObjetivo;
    }

    public void setHorasSueñoObjetivo(Integer horasSuenioObjetivo) {
        this.horasSuenioObjetivo = horasSuenioObjetivo;
    }

    public EstadoObjetivo getEstadoObjetivo() {
        return estado;
    }

    public void setEstadoObjetivo(EstadoObjetivo estado) {
        this.estado = estado;
    }

}
