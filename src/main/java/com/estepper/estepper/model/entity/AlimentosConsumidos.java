package com.estepper.estepper.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "alimentosconsumidos")
public class AlimentosConsumidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private float raciones;
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idAlimento")
    private Alimentacion alimento;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idParticipante")
    private Participante participante;

    @Column(name = "fecha_consumicion", columnDefinition = "DATETIME")
    private LocalDateTime fecha_consumicion;

    public AlimentosConsumidos() {
        this.raciones = 1;
    }

    public AlimentosConsumidos(Integer id, Alimentacion alimento, Participante participante, float raciones,
            LocalDateTime fecha_consumicion) {
        this.id = id;
        this.alimento = alimento;
        this.participante = participante;
        this.fecha_consumicion = fecha_consumicion;
        this.raciones = raciones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Alimentacion getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimentacion alimento) {
        this.alimento = alimento;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public LocalDateTime getFecha_consumicion() {
        return fecha_consumicion;
    }

    public void setFecha_consumicion(LocalDateTime fecha_consumicion) {
        this.fecha_consumicion = fecha_consumicion;
    }

    public float getRaciones() {
        return raciones;
    }

    public void setRaciones(float raciones) {
        this.raciones = raciones;
    }

}
