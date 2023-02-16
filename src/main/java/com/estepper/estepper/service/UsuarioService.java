package com.estepper.estepper.service;

import java.util.List;
import java.util.Optional;

import com.estepper.estepper.model.entity.Usuario;

public interface UsuarioService {
    public List<Usuario> listadoTotal();
    public Usuario logueado(Integer codigo);
    public Optional<Usuario> findById(Integer id);

    public void eliminarUsuario(Usuario usuario);
    public void recuperarCodigo(String correo);

    public void update(String nickname, String email, String contrasenia, Integer id);

}
