package com.estepper.estepper.model.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
@Table(name = "recetas")
public class Receta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String link;
    private Integer pag;
    private String nombre;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "receta_alimentacion",
           joinColumns = @JoinColumn(name = "receta_id"),
           inverseJoinColumns = @JoinColumn(name = "alimentacion_id"))
    private List<Alimentacion> ingredientes;

    public Receta(){
        this.pag = 1;
    }

    public Receta(Integer id, String link, String nombre, Integer pag,  List<Alimentacion> ingredientes){
        this.id = id;
        this.pag = pag;
        this.nombre = nombre;
        this.ingredientes = ingredientes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getPag() {
        return pag;
    }

    public void setPag(Integer pag) {
        this.pag = pag;
    }

    public List<Alimentacion> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Alimentacion> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    
}
