package com.estepper.estepper.model.entity;

import com.estepper.estepper.model.enums.Estado;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "administradores")
public class Administrador extends Usuario {

    public Administrador() {
        super();
    }

    public Administrador(Integer id, Integer codigo, String nickname, String email, String contrasenia,
            Estado estadoCuenta, String fotoUsuario) {
        super(id, codigo, nickname, email, contrasenia, estadoCuenta, fotoUsuario);
    }

}
