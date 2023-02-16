package com.estepper.estepper.model.entity;

import com.estepper.estepper.model.enums.Estado;
import com.estepper.estepper.model.enums.Sexo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "participantes")
public class Participante extends Usuario{
    
    @Column(unique=false, nullable=true)
    private Integer idCoordinador;

    //@Column(unique=false, nullable=true)
   // private Integer idGrupo;

   @Column(nullable=true)
   private String fotoParticipante;

    @Column(nullable=true)
    private Integer perdidaDePeso;
    @Column(nullable=true)
    private Integer asistencia;
    @Column(nullable=true)
    public Integer edad;
    @Column(nullable=true)
    private Integer sesionesCompletas;
    @Column(nullable=true)
    @Enumerated(value = EnumType.STRING)
    public Sexo sexo;
    @Column
    public Integer id;

    @ManyToOne
    @JoinColumn(name="idGrupo")
    private Grupo grupo; 
    
    public Participante(){
        super();
    }

    public Participante(Integer id, Integer codigo, String nickname, String email, String contrasenia, Estado estadoCuenta,Integer idCoordinador,Grupo grupo,Integer perdidaDePeso,Integer asistencia,
    Integer edad, Integer sesionesCompletas,Sexo sexo, String fotoParticipante){
        super(id, codigo, nickname, email, contrasenia, estadoCuenta);
        this.idCoordinador=idCoordinador;
        this.grupo=grupo;
        this.perdidaDePeso=perdidaDePeso;
        this.asistencia=asistencia;
        this.edad=edad;
        this.sesionesCompletas=sesionesCompletas;
        this.sexo=sexo;
        this.fotoParticipante=fotoParticipante;

    }

    public Integer getIdCoordinador() {
        return idCoordinador;
    }
    public void setIdCoordinador(Integer idCoordinador) {
        this.idCoordinador = idCoordinador;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Integer getPerdidaDePeso() {
        return perdidaDePeso;
    }

    public void setPerdidaDePeso(Integer perdidaDePeso) {
        this.perdidaDePeso = perdidaDePeso;
    }

    public Integer getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Integer asistencia) {
        this.asistencia = asistencia;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Integer getSesionesCompletas() {
        return sesionesCompletas;
    }

    public void setSesionesCompletas(Integer sesionesCompletas) {
        this.sesionesCompletas = sesionesCompletas;
    }

    public Integer getId(){
        return id;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public String getFotoParticipante() {
        return fotoParticipante;
    }

    public void setFotoParticipante(String fotoParticipante) {
        this.fotoParticipante = fotoParticipante;
    }

    
}