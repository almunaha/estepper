package com.estepper.estepper.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "antecedentes")
public class Antecedentes extends FaseValoracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    public String hta;
    public String tiroides;
    public String patmental;
    public String dislipemias;
    public String patmuscesq;
    public String medicacion;
    public String ecv;
    public String patsensorial;
    public String especificar;
    public String fuma;
    public String dejardefumar;
    public Integer tasistolica;
    public Integer tadiastolica;

    public Antecedentes() {
        super();
    }

    public Antecedentes(Integer id, Participante participante, String hta, String tiroides, String patmental, String dislipemias, String patmuscesq, String medicacion, String ecv, String patsensorial, String especificar, String fuma, String dejardefumar, Integer tasistolica, Integer tadiastolica) {
        super(id, participante);
        this.hta = hta; 
        this.tiroides = tiroides;
        this.patmental = patmental;
        this.dislipemias = dislipemias;
        this.patmuscesq = patmuscesq;
        this.medicacion = medicacion;
        this.fuma = fuma;
        this.dejardefumar = dejardefumar;
        this.tasistolica = tasistolica;
        this.tadiastolica = tadiastolica;
        this.ecv = ecv;
        this.patsensorial = patsensorial;
        this.especificar = especificar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHta() {
        return hta;
    }

    public void setHta(String hta) {
        this.hta = hta;
    }

    public String getTiroides() {
        return tiroides;
    }

    public void setTiroides(String tiroides) {
        this.tiroides = tiroides;
    }

    public String getPatmental() {
        return patmental;
    }

    public void setPatmental(String patmental) {
        this.patmental = patmental;
    }

    public String getDislipemias() {
        return dislipemias;
    }

    public void setDislipemias(String dislipemias) {
        this.dislipemias = dislipemias;
    }

    public String getPatmuscesq() {
        return patmuscesq;
    }

    public void setPatmuscesq(String patmuscesq) {
        this.patmuscesq = patmuscesq;
    }

    public String getMedicacion() {
        return medicacion;
    }

    public void setMedicacion(String medicacion) {
        this.medicacion = medicacion;
    }

    public String getFuma() {
        return fuma;
    }

    public void setFuma(String fuma) {
        this.fuma = fuma;
    }

    public String getDejardefumar() {
        return dejardefumar;
    }

    public void setDejardefumar(String dejardefumar) {
        this.dejardefumar = dejardefumar;
    }

    public Integer getTasistolica() {
        return tasistolica;
    }

    public void setTasistolica(Integer tasistolica) {
        this.tasistolica = tasistolica;
    }

    public Integer getTadiastolica() {
        return tadiastolica;
    }

    public void setTadiastolica(Integer tadiastolica) {
        this.tadiastolica = tadiastolica;
    }

    public String getEcv() {
        return ecv;
    }

    public void setEcv(String ecv) {
        this.ecv = ecv;
    }

    public String getPatsensorial() {
        return patsensorial;
    }

    public void setPatsensorial(String patsensorial) {
        this.patsensorial = patsensorial;
    }

    public String getEspecificar() {
        return especificar;
    }

    public void setEspecificar(String especificar) {
        this.especificar = especificar;
    }

    

}
