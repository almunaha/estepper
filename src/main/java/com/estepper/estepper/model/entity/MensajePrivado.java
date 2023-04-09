package com.estepper.estepper.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mensajePrivado")
public class MensajePrivado implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private LocalDateTime fechayHoraEnvio;

    @Column
    private String mensaje;

    @ManyToOne 
    @JoinColumn(name="idCoordinador", nullable=false)
    private Coordinador coordinador;

    @ManyToOne 
    @JoinColumn(name="idParticipante", nullable=false)
    private Participante participante;

    @ManyToOne 
    @JoinColumn(name="idUsuario", nullable=false)
    private Usuario usuario;


    public MensajePrivado(Integer id, LocalDateTime fechayHoraEnvio, String mensaje, Coordinador coordinador, Participante participante, Usuario usuario) {
        this.id = id;
        this.fechayHoraEnvio = fechayHoraEnvio;
        this.mensaje = mensaje;
        this.coordinador = coordinador;
        this.participante = participante;
        this.usuario = usuario;
    }
    
    public MensajePrivado() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getFechayHoraEnvio() {
        return fechayHoraEnvio;
    }

    public void setFechayHoraEnvio(LocalDateTime fecha) {
        this.fechayHoraEnvio = fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Coordinador getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(Coordinador coordinador) {
        this.coordinador = coordinador;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
