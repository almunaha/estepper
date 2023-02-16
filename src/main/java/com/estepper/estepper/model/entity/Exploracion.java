package com.estepper.estepper.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="exploracion")
public class Exploracion extends FaseValoracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    private Integer idParticipante;
    public String primeravez;
    public Integer peso;
    public Integer cmcintura;
    public Integer talla;
    public Integer edad;
    public Integer imc;  

    public Exploracion(){}

    public Exploracion(Integer id, Integer idParticipante,String primeravez, Integer peso, Integer cmcintura, Integer talla, Integer edad, Integer imc ){
        super(id, idParticipante);
        this.primeravez = primeravez;
        this.peso = peso;
        this.cmcintura = cmcintura;
        this.talla = talla;
        this.edad = edad;
        this.imc = imc;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(Integer idParticipante) {
        this.idParticipante = idParticipante;
    }

    public String getPrimeravez() {
        return primeravez;
    }

    public void setPrimeravez(String primeravez) {
        this.primeravez = primeravez;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Integer getCmcintura() {
        return cmcintura;
    }

    public void setCmcintura(Integer cmcintura) {
        this.cmcintura = cmcintura;
    }

    public Integer getTalla() {
        return talla;
    }

    public void setTalla(Integer talla) {
        this.talla = talla;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Integer getImc() {
        return imc;
    }

    public void setImc(Integer imc) {
        this.imc = imc;
    }

    
}