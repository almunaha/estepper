package com.estepper.estepper.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="findrisc")
public class Findrisc extends FaseValoracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public Integer puntosedad;
    public Integer puntosimc;
    public Integer puntoscmcintura;
    public Integer ptosactfisica;
    public Integer ptosfrecfruta;
    public Integer ptosmedicacion;
    public Integer ptosglucosa;
    public Integer ptosdiabetes;
    public Integer puntuacion;
    public String escalarriesgo;

    public Findrisc(){
        super();
    }

    public Findrisc(Integer id, Participante participante,Integer puntosedad, Integer puntosimc, Integer puntoscmcintura, Integer ptosactfisica,
                    Integer ptosfrecfruta, Integer ptosmedicacion, Integer ptosglucosa, Integer ptosdiabetes, Integer puntuacion,
                    String escalarriesgo){
        super(id, participante);
        this.puntosedad = puntosedad;
        this.puntosimc = puntosimc;
        this.puntoscmcintura = puntoscmcintura;
        this.ptosactfisica = ptosactfisica;
        this.ptosfrecfruta = ptosfrecfruta;
        this.ptosmedicacion = ptosmedicacion;
        this.ptosglucosa = ptosglucosa;
        this.ptosdiabetes = ptosdiabetes;
        this.puntuacion = puntuacion;
        this.escalarriesgo = escalarriesgo;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPuntosedad() {
        return puntosedad;
    }

    public void setPuntosedad(Integer puntosedad) {
        this.puntosedad = puntosedad;
    }

    public Integer getPuntosimc() {
        return puntosimc;
    }

    public void setPuntosimc(Integer puntosimc) {
        this.puntosimc = puntosimc;
    }

    public Integer getPuntoscmcintura() {
        return puntoscmcintura;
    }

    public void setPuntoscmcintura(Integer puntoscmcintura) {
        this.puntoscmcintura = puntoscmcintura;
    }

    public Integer getPtosactfisica() {
        return ptosactfisica;
    }

    public void setPtosactfisica(Integer ptosactfisica) {
        this.ptosactfisica = ptosactfisica;
    }

    public Integer getPtosfrecfruta() {
        return ptosfrecfruta;
    }

    public void setPtosfrecfruta(Integer ptosfrecfruta) {
        this.ptosfrecfruta = ptosfrecfruta;
    }

    public Integer getPtosmedicacion() {
        return ptosmedicacion;
    }

    public void setPtosmedicacion(Integer ptosmedicacion) {
        this.ptosmedicacion = ptosmedicacion;
    }

    public Integer getPtosglucosa() {
        return ptosglucosa;
    }

    public void setPtosglucosa(Integer ptosglucosa) {
        this.ptosglucosa = ptosglucosa;
    }

    public Integer getPtosdiabetes() {
        return ptosdiabetes;
    }

    public void setPtosdiabetes(Integer ptosdiabetes) {
        this.ptosdiabetes = ptosdiabetes;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getEscalarriesgo() {
        return escalarriesgo;
    }

    public void setEscalarriesgo(String escalarriesgo) {
        this.escalarriesgo = escalarriesgo;
    }

    
}
