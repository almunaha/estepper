package com.estepper.estepper.model.entity;

import java.io.Serializable;
import com.estepper.estepper.model.enums.Estado;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Inheritance(strategy=InheritanceType.JOINED)
@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique=true)
    private Integer codigo;
    private String nombre;
    private String apellidos;
    private String email;
    private String contrasenia;

    @Enumerated(value = EnumType.STRING)
    private Estado estadoCuenta;
    
    public Usuario(Integer id, Integer codigo, String nombre, String apellidos, String email, String contrasenia, Estado estadoCuenta) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.contrasenia = contrasenia;
        this.estadoCuenta = estadoCuenta;
    }
    
    public Usuario() {
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getCodigo() {
        return codigo;
    }
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getContrasenia() {
        return contrasenia;
    }
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    public Estado getEstadoCuenta() {
        return estadoCuenta;
    }
    public void setEstadoCuenta(Estado estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }
}

    