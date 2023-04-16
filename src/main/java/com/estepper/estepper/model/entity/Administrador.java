package com.estepper.estepper.model.entity;

import com.estepper.estepper.model.enums.Estado;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="administradores")
public class Administrador extends Usuario{ 

    @Column(nullable=true)
    private String fotoParticipante;
    
    public Administrador(){
        super();
    }
    
    public Administrador(Integer id, Integer codigo, String nickname, String email, String contrasenia, Estado estadoCuenta, String fotoParticipante){
        super(id, codigo, nickname, email, contrasenia, estadoCuenta);
        this.fotoParticipante=fotoParticipante;
    }

    public String getFotoParticipante() {
        return fotoParticipante;
    }

    public void setFotoParticipante(String fotoParticipante) {
        this.fotoParticipante = fotoParticipante;
    }
}
