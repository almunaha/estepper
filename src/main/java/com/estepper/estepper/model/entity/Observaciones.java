package com.estepper.estepper.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "observaciones")
public class Observaciones implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(unique=false, nullable=true)
    private Integer idCoordinador;
     
    
    @Column(unique=false, nullable=true)
    private Integer idGrupo;

    private String nota;
   
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = ISO.DATE)
    @NotNull
    private Date fecha;



    public Observaciones(Integer id, Integer idCoordinador, Integer idGrupo, String nota, Date fecha) {
        this.id = id;
        this.idCoordinador = idCoordinador;
        this.idGrupo = idGrupo;
        this.nota = nota;
        this.fecha = fecha;
    }

    public Observaciones() {
        fecha = new Date();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCoordinador() {
        return idCoordinador;
    }

    public void setIdCoordinador(Integer idCoordinador) {
        this.idCoordinador = idCoordinador;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

  
    
}
