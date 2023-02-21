package com.estepper.estepper.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.FetchType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Inheritance(strategy=InheritanceType.JOINED)
@Entity
@Table(name="fasevaloracion")
public class FaseValoracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name="idParticipante")
    private Participante participante; 

    public FaseValoracion(){}

    public FaseValoracion(Integer id, Participante participante){
        this.id = id;
        this.participante = participante;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdParticipante() {
        return participante.id;
    }

    public Participante getParticipante(){
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    
}
