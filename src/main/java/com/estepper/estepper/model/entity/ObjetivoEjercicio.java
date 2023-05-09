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

import com.estepper.estepper.model.enums.Ejercicio;
import com.estepper.estepper.model.enums.EstadoObjetivo;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ObjetivoEjercicio")
public class ObjetivoEjercicio implements Serializable {

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

    private Integer duracionEjercicio;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = true, name = "estado", columnDefinition = "ENUM('COMPLETADO', 'PENDIENTE')")
    private EstadoObjetivo estado;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = true, name = "ejercicio", columnDefinition = "ENUM('NINGUNO', 'ANDAR', 'CORRER','NATACION','BICICLETA','GIMNASIO','FUTBOL','BALONCESTO','TENIS','PADEL', 'BOXEO', 'BAILE', 'YOGA', 'PILATES', 'OTRO')")
    private Ejercicio ejercicio;

    public ObjetivoEjercicio(Integer id, Participante participante, Date fecha, Integer duracionEjercicio,
            EstadoObjetivo estado, Ejercicio ejercicio) {
        this.id = id;
        this.participante = participante;
        this.fecha = fecha;
        this.duracionEjercicio = duracionEjercicio;
        this.estado = estado;
        this.ejercicio = ejercicio;
    }

    public ObjetivoEjercicio() {
        estado = EstadoObjetivo.PENDIENTE;
        ejercicio = Ejercicio.NINGUNO;
        fecha = new Date();
        duracionEjercicio = 0;
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

    public Integer getDuracionEjercicio() {
        return duracionEjercicio;
    }

    public void setDuracionEjercicio(Integer duracionEjercicio) {
        this.duracionEjercicio = duracionEjercicio;
    }

    public EstadoObjetivo getEstadoObjetivo() {
        return estado;
    }

    public void setEstadoObjetivo(EstadoObjetivo estado) {
        this.estado = estado;
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

}
