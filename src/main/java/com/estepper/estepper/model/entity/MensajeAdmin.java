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
@Table(name = "mensajeAdmin")
public class MensajeAdmin implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private LocalDateTime fechayHoraEnvio;

    @Column
    private String mensaje;

    @ManyToOne 
    @JoinColumn(name="idUsuario", nullable=false)
    private Usuario usuario;

    /*@ManyToOne 
    @JoinColumn(name="idAdministrador", nullable=false)
    private Administrador administrador;*/

    @ManyToOne 
    @JoinColumn(name="idEmisor", nullable=false)
    private Usuario emisor;

    public MensajeAdmin(Integer id, LocalDateTime fechayHoraEnvio, String mensaje, Usuario usuario,Usuario emisor) {
        this.id = id;
        this.fechayHoraEnvio = fechayHoraEnvio;
        this.mensaje = mensaje;
        this.usuario = usuario;
        this.emisor = emisor;
    }
    
    public MensajeAdmin() {
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /*public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }*/

    public Usuario getEmisor() {
        return emisor;
    }

    public void setEmisor(Usuario emisor) {
        this.emisor = emisor;
    }
}
