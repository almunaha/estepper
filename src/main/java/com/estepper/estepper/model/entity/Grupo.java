package com.estepper.estepper.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;

@Entity
@Table(name = "grupos")
public class Grupo implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //porque va a ser incrementable
    private Integer id;

    @Column(unique=false, nullable=true)
    private Integer idCoordinador;

    @Column(unique=true)
    private Integer codigo;
    @Column(unique=true)
    private String nombre;
    @Column
    private Integer numParticipantes; 


    
    public Grupo(Integer id, Integer idCoordinador, Integer codigo, String nombre, Integer numParticipantes) {
        this.id = id;
        this.idCoordinador = idCoordinador;
        this.codigo = codigo;
        this.nombre = nombre;
        this.numParticipantes = numParticipantes;
    }
    
    public Grupo() {
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getCodigo() {
        return codigo;
    }
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getNumParticipantes() {
        return numParticipantes;
    }
    public void setNumParticipantes(Integer numParticipantes) {
        this.numParticipantes = numParticipantes;
    }

    public Integer getIdCoordinador() {
        return idCoordinador;
    }

    public void setIdCoordinador(Integer idCoordinador) {
        this.idCoordinador = idCoordinador;
    }

    

  
}

    



