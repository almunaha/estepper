package com.estepper.estepper.model.entity;

import java.io.Serializable;

import com.estepper.estepper.model.enums.Asistencia;
import com.estepper.estepper.model.enums.EstadoSesion;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="sesiones")
public class Sesion implements Serializable{
    
    @Id
    private Integer id;
    private Integer idPaciente;

    @Enumerated(value = EnumType.STRING)
    private EstadoSesion estado;

    private String observaciones;

    @Enumerated(value = EnumType.STRING)
    private Asistencia asistencia;

    private double cmsPerdidos;

    private double pesoPerdido;

    //fichas con json

    public Sesion(Integer id, Integer idPaciente, EstadoSesion estado, String observaciones, Asistencia asistencia, double cmsPerdidos, double pesoPerdido){
        this.id = id;
        this.idPaciente = idPaciente;
        this.estado = estado;
        this.observaciones = observaciones;
        this.asistencia = asistencia;
        this.cmsPerdidos = cmsPerdidos;
        this.pesoPerdido = pesoPerdido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public EstadoSesion getEstado() {
        return estado;
    }

    public void setEstado(EstadoSesion estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Asistencia getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Asistencia asistencia) {
        this.asistencia = asistencia;
    }

    public double getCmsPerdidos() {
        return cmsPerdidos;
    }

    public void setCmsPerdidos(double cmsPerdidos) {
        this.cmsPerdidos = cmsPerdidos;
    }

    public double getPesoPerdido() {
        return pesoPerdido;
    }

    public void setPesoPerdido(double pesoPerdido) {
        this.pesoPerdido = pesoPerdido;
    }

    
}

