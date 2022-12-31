package com.estepper.estepper.service;

import com.estepper.estepper.model.entity.Usuario;

//Esta interfaz no haría falta porque vamos a usar la de UserDetailsService que tiene ya java -> borrar
public interface UsuarioService {
    public Usuario loadUserByName(String nombre);
}
