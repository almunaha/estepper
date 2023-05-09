package com.estepper.estepper.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;

import java.io.Serializable;

import com.estepper.estepper.model.enums.GrupoAlimento;

import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name = "alimentosintercambio")
public class AlimentoIntercambio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private Integer gramos;

    @Enumerated(EnumType.STRING)
    @Column(name = "grupo", columnDefinition = "ENUM('LÁCTEO', 'PROTEÍNA', 'VERDURA', 'FÉCULA', 'FRUTA', 'GRASA')")
    private GrupoAlimento categoria;

    public AlimentoIntercambio() {
    }

    public AlimentoIntercambio(Integer id, String nombre, Integer gramos, GrupoAlimento categoria) {
        this.id = id;
        this.nombre = nombre;
        this.gramos = gramos;
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getGramos() {
        return gramos;
    }

    public void setGramos(Integer gramos) {
        this.gramos = gramos;
    }

    public GrupoAlimento getCategoria() {
        return categoria;
    }

    public void setCategoria(GrupoAlimento categoria) {
        this.categoria = categoria;
    }

}
