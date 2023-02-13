package com.estepper.estepper.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "grupos")
public class Grupo implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //porque va a ser incrementable
    private Integer id;
    @Column(unique=true)
    private Integer codigo;
    @Column(unique=true)
    private String nombre;
    @Column
    private Integer numParticipantes; //VER CÓMO HACER QUE SEA 0 POR DEFECTO, A NO SER QUE AÑADAN PARTICIPANTES

    
    public Grupo(Integer id, Integer codigo, String nombre, Integer numParticipantes) {
        this.id = id;
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

  
}

    



