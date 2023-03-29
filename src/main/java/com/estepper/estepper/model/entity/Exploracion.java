package com.estepper.estepper.model.entity;

import com.estepper.estepper.model.enums.Sexo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="exploracion")
public class Exploracion extends FaseValoracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String primeravez;
    private Double peso;
    private Double cmcintura;
    private Integer talla;
    private Integer edad;
    private Integer imc;  
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = true, name = "sexo", columnDefinition = "ENUM('MASCULINO', 'FEMENINO')")
    private Sexo sexo;

    public Exploracion(){
        super();
    }

    public Exploracion(Integer id, Participante participante, Sexo sexo, String primeravez, Double peso, Double cmcintura, Integer talla, Integer edad, Integer imc ){
        super(id, participante);
        this.primeravez = primeravez;
        this.peso = peso;
        this.cmcintura = cmcintura;
        this.talla = talla;
        this.edad = edad;
        this.imc = imc;
        this.sexo = sexo;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrimeravez() {
        return primeravez;
    }

    public void setPrimeravez(String primeravez) {
        this.primeravez = primeravez;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getCmcintura() {
        return cmcintura;
    }

    public void setCmcintura(Double cmcintura) {
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

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    
}
