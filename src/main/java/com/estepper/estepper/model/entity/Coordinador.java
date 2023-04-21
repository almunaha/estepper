package com.estepper.estepper.model.entity;

import com.estepper.estepper.model.enums.Estado;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="coordinadores")
public class Coordinador extends Usuario{ 

    /* @Column(nullable=true)
    private String fotoParticipante;*/

    @Column(unique=false, nullable=true)
    private Integer idAdministrador;
    
    public Coordinador(){
        super();
    }

    public Coordinador(Integer id, Integer codigo, String nickname, String email, String contrasenia, Estado estadoCuenta,Integer idAdministrador, String fotoUsuario){
        super(id, codigo, nickname, email, contrasenia, estadoCuenta,fotoUsuario);
        //this.fotoParticipante=fotoParticipante;
        this.idAdministrador = idAdministrador;
    }

    /*public String getFotoParticipante() {
        return fotoParticipante;
    }

    public void setFotoParticipante(String fotoParticipante) {
        this.fotoParticipante = fotoParticipante;
    }*/

    public Integer getIdAdministrador() {
        return idAdministrador;
    }
    public void setIdAdministrador(Integer idAdministrador) {
        this.idAdministrador = idAdministrador;
    }
    
}
