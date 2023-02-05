package com.estepper.estepper.service;

import java.util.List;

import com.estepper.estepper.model.entity.Usuario;

//Operaciones 
public interface UsuarioService {
    public List<Usuario> listadoTotal();
    public Usuario logueado(String codigo);
}
