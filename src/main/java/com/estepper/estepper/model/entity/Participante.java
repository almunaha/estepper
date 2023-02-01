package com.estepper.estepper.model.entity;

import com.estepper.estepper.model.enums.Estado;
import com.estepper.estepper.model.enums.Rol;
import com.estepper.estepper.model.enums.Sexo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "participantes")
public class Participante extends Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPaciente;
    @Column(unique=false)
    private Integer idCoordinador;
    private Integer idGrupo;
    private Integer perdidaDePeso;
    private Integer asistencia;
    private Integer edad;
    private Integer sesionesCompletas;
    @Enumerated(value = EnumType.STRING)
    private Sexo sexo;
    
    public Participante(){
        super();
    }

    public Participante(Integer idPaciente,Integer id, Integer codigo, String nombre, String apellidos, String email, String contrasenia,
    Rol rol, Estado estadoCuenta,Integer idCoordinador,Integer idGrupo,Integer perdidaDePeso,Integer asistencia,
    Integer edad, Integer sesionesCompletas,Sexo sexo){
        super(id, codigo, nombre, apellidos, email, contrasenia, rol, estadoCuenta);
        this.idPaciente=idPaciente;
        this.idCoordinador=idCoordinador;
        this.idGrupo=idGrupo;
        this.perdidaDePeso=perdidaDePeso;
        this.asistencia=asistencia;
        this.edad=edad;
        this.sesionesCompletas=sesionesCompletas;
        this.sexo=sexo;

    }

    public Integer getIdCoordinador() {
        return idCoordinador;
    }
    public void setIdCoordinador(Integer idCoordinador) {
        this.idCoordinador = idCoordinador;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }
    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
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
}
