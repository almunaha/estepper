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
@Table(name = "mensaje")
public class Mensaje implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private LocalDateTime fechayHoraEnvio;

    @Column
    private String mensaje;

    @ManyToOne
    @JoinColumn(name = "idGrupo", nullable = false)
    private Grupo grupo;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    public Mensaje(Integer id, LocalDateTime fechayHoraEnvio, String mensaje, Grupo grupo, Usuario usuario) {
        this.id = id;
        this.fechayHoraEnvio = fechayHoraEnvio;
        this.mensaje = mensaje;
        this.grupo = grupo;
        this.usuario = usuario;
    }

    public Mensaje() {
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

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
