package com.estepper.estepper.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fichaObjetivo")
public class FichaObjetivo extends Ficha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double objetivo;
    private Double perdida;

    public FichaObjetivo() {
        super();
    }

    public FichaObjetivo(Integer id, Participante participante, Double objetivo, Double perdida) {
        super(id, participante);
        this.objetivo = objetivo;
        this.perdida = perdida;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Double objetivo) {
        this.objetivo = objetivo;
    }

    public Double getPerdida() {
        return perdida;
    }

    public void setPerdida(Double perdida) {
        this.perdida = perdida;
    }

}
