package com.estepper.estepper.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
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
    private String codigo;
    @Column(unique=true)
    private String nombre;
    @Column
    private Integer numParticipantes; 

    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Participante> participantes;

    @Column
    private LocalDate fechaInicioGrupo; 

    @Column
    private LocalDate fechaFinGrupo; 

    
    public Grupo(Integer id, Integer idCoordinador, String codigo, String nombre, Integer numParticipantes, LocalDate fechaInicioGrupo, LocalDate fechaFinGrupo) {
        this.id = id;
        this.idCoordinador = idCoordinador;
        this.codigo = codigo;
        this.nombre = nombre;
        this.numParticipantes = numParticipantes;
        this.fechaInicioGrupo = fechaInicioGrupo;
        this.fechaFinGrupo = fechaFinGrupo;
        this.participantes = participantes;
    }
    
    public Grupo() {
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
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

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }

    public LocalDate getFechaInicioGrupo() {
        return fechaInicioGrupo;
    }
    public void setFechaInicioGrupo(LocalDate fecha) {
        this.fechaInicioGrupo = fecha;
    }

    public LocalDate getFechaFinGrupo() {
        return fechaFinGrupo;
    }

    public void setFechaFinGrupo(LocalDate fecha) {
        this.fechaFinGrupo = fecha;
    }

    public Integer getIdCoordinador() {
        return idCoordinador;
    }

    public void setIdCoordinador(Integer idCoordinador) {
        this.idCoordinador = idCoordinador;
    }

    

  
}

    



