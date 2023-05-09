package com.estepper.estepper.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "actividadfisica")
public class ActividadFisica extends FaseValoracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer vecesafv;
    private Integer horaafv;
    private Integer minafv;
    private Integer metsafv;
    private Integer vecesafm;
    private Integer horaafm;
    private Integer minafm;
    private Integer metsafm;
    private Integer vecescaminar;
    private Integer horacaminar;
    private Integer mincaminar;
    private Integer metscaminar;
    private Integer horasentado;
    private Integer minsentado;
    private Integer metstotales;
    private String clasificacion;

    public ActividadFisica() {
        super();
    }

    public ActividadFisica(Integer id, Participante participante, Integer vecesafv, Integer horaafv, Integer minafv,
            Integer metsafv, Integer vecesafm, Integer horaafm, Integer minafm, Integer metsafm, Integer vecescaminar,
            Integer horacaminar, Integer mincaminar, Integer metscaminar, Integer horasentado, Integer minsentado,
            Integer metstotales, String clasificacion) {
        super(id, participante);
        this.vecesafv = vecesafv;
        this.horaafv = horaafv;
        this.minafv = minafv;
        this.metsafv = metsafv;
        this.vecesafm = vecesafm;
        this.horaafm = horaafm;
        this.minafm = minafm;
        this.metsafm = metsafm;
        this.vecescaminar = vecescaminar;
        this.horacaminar = horacaminar;
        this.mincaminar = mincaminar;
        this.metscaminar = metscaminar;
        this.horasentado = horasentado;
        this.minsentado = minsentado;
        this.metstotales = metstotales;
        this.clasificacion = clasificacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVecesafv() {
        return vecesafv;
    }

    public void setVecesafv(Integer vecesafv) {
        this.vecesafv = vecesafv;
    }

    public Integer getHoraafv() {
        return horaafv;
    }

    public void setHoraafv(Integer horaafv) {
        this.horaafv = horaafv;
    }

    public Integer getMinafv() {
        return minafv;
    }

    public void setMinafv(Integer minafv) {
        this.minafv = minafv;
    }

    public Integer getMetsafv() {
        return metsafv;
    }

    public void setMetsafv(Integer metsafv) {
        this.metsafv = metsafv;
    }

    public Integer getVecesafm() {
        return vecesafm;
    }

    public void setVecesafm(Integer vecesafm) {
        this.vecesafm = vecesafm;
    }

    public Integer getHoraafm() {
        return horaafm;
    }

    public void setHoraafm(Integer horaafm) {
        this.horaafm = horaafm;
    }

    public Integer getMinafm() {
        return minafm;
    }

    public void setMinafm(Integer minafm) {
        this.minafm = minafm;
    }

    public Integer getMetsafm() {
        return metsafm;
    }

    public void setMetsafm(Integer metsafm) {
        this.metsafm = metsafm;
    }

    public Integer getVecescaminar() {
        return vecescaminar;
    }

    public void setVecescaminar(Integer vecescaminar) {
        this.vecescaminar = vecescaminar;
    }

    public Integer getHoracaminar() {
        return horacaminar;
    }

    public void setHoracaminar(Integer horacaminar) {
        this.horacaminar = horacaminar;
    }

    public Integer getMincaminar() {
        return mincaminar;
    }

    public void setMincaminar(Integer mincaminar) {
        this.mincaminar = mincaminar;
    }

    public Integer getMetscaminar() {
        return metscaminar;
    }

    public void setMetscaminar(Integer metscaminar) {
        this.metscaminar = metscaminar;
    }

    public Integer getHorasentado() {
        return horasentado;
    }

    public void setHorasentado(Integer horasentado) {
        this.horasentado = horasentado;
    }

    public Integer getMinsentado() {
        return minsentado;
    }

    public void setMinsentado(Integer minsentado) {
        this.minsentado = minsentado;
    }

    public Integer getMetstotales() {
        return metstotales;
    }

    public void setMetstotales(Integer metstotales) {
        this.metstotales = metstotales;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

}
