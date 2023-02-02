package com.estepper.estepper.service;

import java.util.List;

import com.estepper.estepper.model.entity.Usuario;
import com.estepper.estepper.model.enums.Rol;

//Operaciones 
public interface UsuarioService {
    //public Usuario loadUserByName(String nombre);
    public List<Usuario> listado();
    public List<Usuario> listadoParticipantes(Rol rol);
    public Usuario logueado(String codigo);
}
