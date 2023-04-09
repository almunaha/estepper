package com.estepper.estepper.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.estepper.estepper.model.enums.Ejercicio;
import com.estepper.estepper.model.enums.EstadoAnimo;
import com.estepper.estepper.model.enums.EstadoObjetivo;
import com.estepper.estepper.model.enums.Repeticion;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ObjetivoEstadoAnimo")
public class ObjetivoEstadoAnimo implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne 
    @JoinColumn(name="idParticipante", nullable=false)
    private Participante participante;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = ISO.DATE)
    @NotNull
    private Date fecha;

    
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = true, name = "estadoAnimo", columnDefinition = "ENUM('CONTENTO', 'FELIZ', 'TRISTE', 'DEPRIMIDO','CANSADO','ENFADADO','SERIO','ENAMORADO','NERVIOSO')")
    private EstadoAnimo estadoAnimo;
    

    public ObjetivoEstadoAnimo(Integer id, Participante participante, Date fecha, EstadoAnimo estadoAnimo ) {
        this.id = id;
        this.participante = participante;
        this.fecha = fecha;
        this.estadoAnimo = estadoAnimo;
    }

    public ObjetivoEstadoAnimo() {
        fecha = new Date();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }


    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public EstadoAnimo getEstadoAnimo() {
        return estadoAnimo;
    }

    public void setEstadoAnimo(EstadoAnimo estadoAnimo) {
        this.estadoAnimo = estadoAnimo;
    }




    
}
