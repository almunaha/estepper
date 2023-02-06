package com.estepper.estepper.model.entity;

import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;

import com.estepper.estepper.model.enums.Asistencia;
import com.estepper.estepper.model.enums.EstadoSesion;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="sesiones")
public class Sesion implements Serializable{
    
    @Id
    private Integer id;
    private Integer idPaciente;

    @Enumerated(value = EnumType.STRING)
    private EstadoSesion estado;

    private String observaciones;

    @Enumerated(value = EnumType.STRING)
    private Asistencia asistencia;

    private double cmsPerdidos;

    private double pesoPerdido;

    private String sesionesString;


    
    //fichas con json

    public Sesion(){
    
    }

    public Sesion(Integer id, Integer idPaciente, EstadoSesion estado, String observaciones,
     Asistencia asistencia, double cmsPerdidos, double pesoPerdido,String sesioneString){
        this.id = id;
        this.idPaciente = idPaciente;
        this.estado = estado;
        this.observaciones = observaciones;
        this.asistencia = asistencia;
        this.cmsPerdidos = cmsPerdidos;
        this.pesoPerdido = pesoPerdido;
        this.sesionesString=sesioneString;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
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

