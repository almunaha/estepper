package com.estepper.estepper.model.entity;

import com.estepper.estepper.model.enums.TipoAlimentacion;

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
    private Integer sal;
    private Integer fibra_alimentaria;
    private Integer grasas_saturadas;
    private Integer hidratos_de_carbono;
    private Integer proteinas;
    private Integer porcion;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", columnDefinition = "ENUM('DULCE','CARNE_GRASA','EMBUTIDO','CARNE_MAGRA','PESCADO','HUEVO','LEGUMBRE','FRUTOS_SECOS','LACTEOS','ACEITE','VERDURA','FRUTA','CEREALES','ARROZ', 'PASTA','PAN')")
    private TipoAlimentacion tipo;

    public Alimentacion(){
        this.sal = 0;
        this.fibra_alimentaria = 0;
        this.grasas_saturadas = 0;
        this.hidratos_de_carbono = 0;
        this.proteinas = 0;
    }

    public Alimentacion(Integer id, String nombre, Integer sal, Integer fibra_alimentaria, Integer grasas_saturadas, Integer hidratos_de_carbono, Integer proteinas, Integer porcion, TipoAlimentacion tipo){
        this.sal = sal;
        this.fibra_alimentaria = fibra_alimentaria;
        this.grasas_saturadas = grasas_saturadas;
        this.hidratos_de_carbono = hidratos_de_carbono;
        this.proteinas = proteinas;
        this.tipo = tipo;
        this.nombre = nombre;
        this.id = id;
        this.porcion = porcion;

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

    public Integer getSal() {
        return sal;
    }

    public void setSal(Integer sal) {
        this.sal = sal;
    }

    public Integer getFibra_alimentaria() {
        return fibra_alimentaria;
    }

    public void setFibra_alimentaria(Integer fibra_alimentaria) {
        this.fibra_alimentaria = fibra_alimentaria;
    }

    public Integer getGrasas_saturadas() {
        return grasas_saturadas;
    }

    public void setGrasas_saturadas(Integer grasas_saturadas) {
        this.grasas_saturadas = grasas_saturadas;
    }

    public Integer getHidratos_de_carbono() {
        return hidratos_de_carbono;
    }

    public void setHidratos_de_carbono(Integer hidratos_de_carbono) {
        this.hidratos_de_carbono = hidratos_de_carbono;
    }

    public Integer getProteinas() {
        return proteinas;
    }

    public void setProteinas(Integer proteinas) {
        this.proteinas = proteinas;
    }

    public TipoAlimentacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoAlimentacion tipo) {
        this.tipo = tipo;
    }

    public Integer getPorcion() {
        return porcion;
    }

    public void setPorcion(Integer porcion) {
        this.porcion = porcion;
    }

}
