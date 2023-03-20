package com.estepper.estepper.model.entity;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "recetas")
public class Receta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String link;
    private Integer pag;
    private String receta;
    @ElementCollection 
    @CollectionTable(name = "lista_ingredientes", joinColumns = @JoinColumn(name = "id")) 
    @Column(name = "ingredientes") 
    private List<Alimentacion> ingredientes;

    public Receta(){}

    public Receta(Integer id, String link, Integer pag, String receta, List<Alimentacion> ingredientes){
        this.id = id;
        this.pag = pag;
        this.receta = receta;
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

    public String getReceta() {
        return receta;
    }

    public void setReceta(String receta) {
        this.receta = receta;
    }

    public List<Alimentacion> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Alimentacion> ingredientes) {
        this.ingredientes = ingredientes;
    }


    
}
