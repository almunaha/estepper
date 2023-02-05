package com.estepper.estepper.model.entity;

import com.estepper.estepper.model.enums.Estado;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="administradores")
public class Administrador extends Usuario{ //habrá que añadir atributos nuevos

    public Administrador(){
        super();
    }
    
    public Administrador(Integer id, Integer codigo, String nombre, String apellidos, String email, String contrasenia, Estado estadoCuenta){
        super(id, codigo, nombre, apellidos, email, contrasenia, estadoCuenta);
    }
}
