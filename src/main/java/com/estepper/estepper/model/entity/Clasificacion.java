package com.estepper.estepper.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="clasificacion")
public class Clasificacion extends FaseValoracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String analiticahecha;
    private Integer glucemia;
    private Integer colesterol;
    private Integer ldl;
    private Integer sog;
    private Integer hdl;
    private Integer trigliceridos;
    private float hbA1c;
    private String pediranalitica;
    private Integer clasificacionusuario;
    private String montesa;
    private String motivomontesa;
    private String taller;
    private String motivotaller;
    private String actividadfisica;

    public Clasificacion(){
        super();
    }

    public Clasificacion(Integer id, Participante participante, String analiticahecha, Integer glucemia, Integer colesterol, Integer ldl, Integer sog, Integer hdl, Integer trigliceridos, float hbA1c, String pediranalitica, Integer clasificacionusuario, String montesa, String motivomontesa, String taller, String motivotaller, String actividadfisica){
        super(id, participante);
        this.analiticahecha = analiticahecha;
        this.glucemia = glucemia;
        this.colesterol = colesterol;
        this.ldl = ldl;
        this.sog = sog;
        this.hdl = hdl;
        this.trigliceridos = trigliceridos;
        this.hbA1c = hbA1c;
        this.pediranalitica = pediranalitica;
        this.clasificacionusuario = clasificacionusuario;
        this.montesa = montesa;
        this.motivomontesa = motivomontesa;
        this.taller = taller;
        this.motivotaller = motivotaller;
        this.actividadfisica = actividadfisica;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnaliticahecha() {
        return analiticahecha;
    }

    public void setAnaliticahecha(String analiticahecha) {
        this.analiticahecha = analiticahecha;
    }

    public Integer getGlucemia() {
        return glucemia;
    }

    public void setGlucemia(Integer glucemia) {
        this.glucemia = glucemia;
    }

    public Integer getColesterol() {
        return colesterol;
    }

    public void setColesterol(Integer colesterol) {
        this.colesterol = colesterol;
    }

    public Integer getLdl() {
        return ldl;
    }

    public void setLdl(Integer ldl) {
        this.ldl = ldl;
    }

    public Integer getSog() {
        return sog;
    }

    public void setSog(Integer sog) {
        this.sog = sog;
    }

    public Integer getHdl() {
        return hdl;
    }

    public void setHdl(Integer hdl) {
        this.hdl = hdl;
    }

    public Integer getTrigliceridos() {
        return trigliceridos;
    }

    public void setTrigliceridos(Integer trigliceridos) {
        this.trigliceridos = trigliceridos;
    }

    public float getHbA1c() {
        return hbA1c;
    }

    public void setHbA1c(float hbA1c) {
        this.hbA1c = hbA1c;
    }

    public String getPediranalitica() {
        return pediranalitica;
    }

    public void setPediranalitica(String pediranalitica) {
        this.pediranalitica = pediranalitica;
    }

    public Integer getClasificacionusuario() {
        return clasificacionusuario;
    }

    public void setClasificacionusuario(Integer clasificacionusuario) {
        this.clasificacionusuario = clasificacionusuario;
    }

    public String getMontesa() {
        return montesa;
    }

    public void setMontesa(String montesa) {
        this.montesa = montesa;
    }

    public String getMotivomontesa() {
        return motivomontesa;
    }

    public void setMotivomontesa(String motivomontesa) {
        this.motivomontesa = motivomontesa;
    }

    public String getTaller() {
        return taller;
    }

    public void setTaller(String taller) {
        this.taller = taller;
    }

    public String getMotivotaller() {
        return motivotaller;
    }

    public void setMotivotaller(String motivotaller) {
        this.motivotaller = motivotaller;
    }

    public String getActividadfisica() {
        return actividadfisica;
    }

    public void setActividadfisica(String actividadfisica) {
        this.actividadfisica = actividadfisica;
    }

    
}
