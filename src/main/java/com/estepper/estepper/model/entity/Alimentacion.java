package com.estepper.estepper.model.entity;

import com.estepper.estepper.model.enums.TipoAlimentacion;
import com.estepper.estepper.model.enums.TipoUnidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "alimentacion")
public class Alimentacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private float sal;
    private float fibra_alimentaria;
    private float grasas_saturadas;
    private float hidratos_de_carbono;
    private float proteinas;
    @Enumerated(EnumType.STRING)
    @Column(name = "unidad")
    private TipoUnidad unidad;
    private float porcion;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", columnDefinition = "ENUM('DULCE','CARNE_GRASA','EMBUTIDO','CARNE_MAGRA','PESCADO','HUEVO','LEGUMBRE','FRUTOS_SECOS','LACTEOS','ACEITE','VERDURA','FRUTA','CEREALES','ARROZ', 'PASTA','PAN')")
    private TipoAlimentacion tipo;

    public Alimentacion() {
        this.sal = 0;
        this.fibra_alimentaria = 0;
        this.grasas_saturadas = 0;
        this.hidratos_de_carbono = 0;
        this.proteinas = 0;
    }

    public Alimentacion(Integer id, String nombre, float sal, float fibra_alimentaria, float grasas_saturadas,
            float hidratos_de_carbono, float proteinas, TipoUnidad unidad, float porcion, TipoAlimentacion tipo) {
        this.sal = sal;
        this.fibra_alimentaria = fibra_alimentaria;
        this.grasas_saturadas = grasas_saturadas;
        this.hidratos_de_carbono = hidratos_de_carbono;
        this.proteinas = proteinas;
        this.tipo = tipo;
        this.nombre = nombre;
        this.id = id;
        this.porcion = porcion;
        this.unidad = unidad;

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

    public float getSal() {
        return sal;
    }

    public void setSal(float sal) {
        this.sal = sal;
    }

    public float getFibra_alimentaria() {
        return fibra_alimentaria;
    }

    public void setFibra_alimentaria(float fibra_alimentaria) {
        this.fibra_alimentaria = fibra_alimentaria;
    }

    public float getGrasas_saturadas() {
        return grasas_saturadas;
    }

    public void setGrasas_saturadas(float grasas_saturadas) {
        this.grasas_saturadas = grasas_saturadas;
    }

    public float getHidratos_de_carbono() {
        return hidratos_de_carbono;
    }

    public void setHidratos_de_carbono(float hidratos_de_carbono) {
        this.hidratos_de_carbono = hidratos_de_carbono;
    }

    public float getProteinas() {
        return proteinas;
    }

    public void setProteinas(float proteinas) {
        this.proteinas = proteinas;
    }

    public TipoAlimentacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoAlimentacion tipo) {
        this.tipo = tipo;
    }

    public float getPorcion() {
        return porcion;
    }

    public void setPorcion(float porcion) {
        this.porcion = porcion;
    }

    public TipoUnidad getUnidad() {
        return unidad;
    }

    public void setUnidad(TipoUnidad unidad) {
        this.unidad = unidad;
    }

}
