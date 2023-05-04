package com.estepper.estepper.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fichaTaller")
public class FichaTaller extends Ficha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String razones;
    private String dificultades;
    private String temores;
    private Integer importancia;
    private Integer capacidad;

    public FichaTaller() {
        super();
    }

    public FichaTaller(Integer id, Participante participante, String razones, String dificultades, String temores,
            Integer importancia, Integer capacidad) {
        super(id, participante);
        this.razones = razones;
        this.dificultades = dificultades;
        this.temores = temores;
        this.importancia = importancia;
        this.capacidad = capacidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRazones() {
        return razones;
    }

    public void setRazones(String razones) {
        this.razones = razones;
    }

    public String getDificultades() {
        return dificultades;
    }

    public void setDificultades(String dificultades) {
        this.dificultades = dificultades;
    }

    public String getTemores() {
        return temores;
    }

    public void setTemores(String temores) {
        this.temores = temores;
    }

    public Integer getImportancia() {
        return importancia;
    }

    public void setImportancia(Integer importancia) {
        this.importancia = importancia;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

}
