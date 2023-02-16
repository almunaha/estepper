package com.estepper.estepper.service;

import java.util.List;
import java.util.Optional;

import com.estepper.estepper.model.entity.Usuario;

//Operaciones 
public interface UsuarioService {
    public void guardar(Usuario u);
    public List<Usuario> listadoTotal();
    public Usuario logueado(String codigo);
    public Optional<Usuario> findById(Integer id);

    public void eliminarUsuario(Usuario usuario);

    public void update(String nombre, String apellidos, String email, String contrasenia, Integer id);

}
