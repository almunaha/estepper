package com.estepper.estepper.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fichaEleccion")
public class FichaEleccion extends Ficha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String texto;
    private Integer numEleccion;

    public FichaEleccion() {
        super();
    }

    public FichaEleccion(Integer id, Participante participante, String texto, Integer numEleccion) {
        super(id, participante);
        this.texto = texto;
        this.numEleccion = numEleccion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Integer getNumEleccion() {
        return numEleccion;
    }

    public void setNumEleccion(Integer numEleccion) {
        this.numEleccion = numEleccion;
    }

}
