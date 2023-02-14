package com.estepper.estepper.service;

import java.util.List;
import java.util.Optional;

import com.estepper.estepper.model.entity.Usuario;

//Operaciones 
public interface UsuarioService {
    public List<Usuario> listadoTotal();
    public Usuario logueado(String codigo);
    public Optional<Usuario> findById(Integer id);
    public void eliminarUsuario(Usuario usuario);
}
