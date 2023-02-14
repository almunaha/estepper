package com.estepper.estepper.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Inheritance(strategy=InheritanceType.JOINED)
@Entity
@Table(name="fichas")
public class Ficha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer idParticipante;

    private Integer idSesion;

    public Ficha(){}

    public Ficha(Integer id, Integer idParticipante, Integer idSesion){
        this.id = id;
        this.idParticipante = idParticipante; //creo que este sobraria porque con el idsesion ya sabemos el participante
        this.idSesion = idSesion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(Integer idParticipante) {
        this.idParticipante = idParticipante;
    }

    public Integer getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(Integer idSesion) {
        this.idSesion = idSesion;
    }


    
    
}
