package com.estepper.estepper.model.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="fichas0")
public class Ficha0 extends Ficha{

    private Date fecha1;

    private Date fecha2;

    private Date fecha3;

    private Date fecha4;

    private Date fecha5;

    private Date fecha6;

    private Date fecha7;

    private Date fecha8;

    private Date fecha9;

    private Date fecha10;

    public Ficha0(){}

    public Ficha0(Date fecha1, Date fecha2, Date fecha3, Date fecha4, Date fecha5, Date fecha6, Date fecha7,
            Date fecha8, Date fecha9, Date fecha10) {
        this.fecha1 = fecha1;
        this.fecha2 = fecha2;
        this.fecha3 = fecha3;
        this.fecha4 = fecha4;
        this.fecha5 = fecha5;
        this.fecha6 = fecha6;
        this.fecha7 = fecha7;
        this.fecha8 = fecha8;
        this.fecha9 = fecha9;
        this.fecha10 = fecha10;
    }

    public Ficha0(Integer id, Integer idParticipante, Integer idSesion, Date fecha1, Date fecha2, Date fecha3,
            Date fecha4, Date fecha5, Date fecha6, Date fecha7, Date fecha8, Date fecha9, Date fecha10) {
        super(id, idParticipante, idSesion);
        this.fecha1 = fecha1;
        this.fecha2 = fecha2;
        this.fecha3 = fecha3;
        this.fecha4 = fecha4;
        this.fecha5 = fecha5;
        this.fecha6 = fecha6;
        this.fecha7 = fecha7;
        this.fecha8 = fecha8;
        this.fecha9 = fecha9;
        this.fecha10 = fecha10;
    }

    public Date getFecha1() {
        return fecha1;
    }

    public void setFecha1(Date fecha1) {
        this.fecha1 = fecha1;
    }

    public Date getFecha2() {
        return fecha2;
    }

    public void setFecha2(Date fecha2) {
        this.fecha2 = fecha2;
    }

    public Date getFecha3() {
        return fecha3;
    }

    public void setFecha3(Date fecha3) {
        this.fecha3 = fecha3;
    }

    public Date getFecha4() {
        return fecha4;
    }

    public void setFecha4(Date fecha4) {
        this.fecha4 = fecha4;
    }

    public Date getFecha5() {
        return fecha5;
    }

    public void setFecha5(Date fecha5) {
        this.fecha5 = fecha5;
    }

    public Date getFecha6() {
        return fecha6;
    }

    public void setFecha6(Date fecha6) {
        this.fecha6 = fecha6;
    }

    public Date getFecha7() {
        return fecha7;
    }

    public void setFecha7(Date fecha7) {
        this.fecha7 = fecha7;
    }

    public Date getFecha8() {
        return fecha8;
    }

    public void setFecha8(Date fecha8) {
        this.fecha8 = fecha8;
    }

    public Date getFecha9() {
        return fecha9;
    }

    public void setFecha9(Date fecha9) {
        this.fecha9 = fecha9;
    }

    public Date getFecha10() {
        return fecha10;
    }

    public void setFecha10(Date fecha10) {
        this.fecha10 = fecha10;
    }

    

    
}
