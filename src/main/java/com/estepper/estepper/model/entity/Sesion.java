package com.estepper.estepper.model.entity;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

import com.estepper.estepper.model.enums.Asistencia;
import com.estepper.estepper.model.enums.EstadoSesion;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="sesiones")
public class Sesion implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer numSesion;

    private Integer idParticipante;

    @Enumerated(value = EnumType.STRING)
    private EstadoSesion estado;

    private String observaciones;

    @Enumerated(value = EnumType.STRING)
    private Asistencia asistencia;

    private double cmsPerdidos;

    private double pesoPerdido;

    public Sesion(){
    
    }

    public Sesion(Integer id, Integer numSesion, Integer idParticipante, EstadoSesion estado, String observaciones,
     Asistencia asistencia, double cmsPerdidos, double pesoPerdido){
        this.id = id;
        this.numSesion = numSesion;
        this.idParticipante = idParticipante;
        this.estado = estado;
        this.observaciones = observaciones;
        this.asistencia = asistencia;
        this.cmsPerdidos = cmsPerdidos;
        this.pesoPerdido = pesoPerdido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

     public Integer getNumSesion() {
        return numSesion;
    }

    public void setNumSesion(Integer numSesion) {
        this.numSesion = numSesion;
    }

    public Integer getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(Integer idParticipante) {
        this.idParticipante = idParticipante;
    }

    public EstadoSesion getEstado() {
        return estado;
    }

    public void setEstado(EstadoSesion estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Asistencia getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Asistencia asistencia) {
        this.asistencia = asistencia;
    }

    public double getCmsPerdidos() {
        return cmsPerdidos;
    }

    public void setCmsPerdidos(double cmsPerdidos) {
        this.cmsPerdidos = cmsPerdidos;
    }

    public double getPesoPerdido() {
        return pesoPerdido;
    }

    public void setPesoPerdido(double pesoPerdido) {
        this.pesoPerdido = pesoPerdido;
    }

    public String jsonToString(String file) throws IOException{
       /* try {
            JSONParser parser = new JSONParser();
            //Use JSONObject for simple JSON and JSONArray for array of JSON.
            JSONObject data = (JSONObject) parser.parse(
                  new FileReader("file"));//path to the JSON file.
    
            String json = data.toString();
            return json;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }*/ 
        String result;  
        result = new String(Files.readAllBytes(Paths.get(file)));  
        return result;  

    }

    public JSONObject StringToJSON(String string){
        JSONObject json = new JSONObject(string);
        //System.out.println(json.toString());  
        return json;
        
    }
 

    
}

