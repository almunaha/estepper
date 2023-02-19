package com.estepper.estepper.service;

import java.util.List;
import java.util.Optional;

import com.estepper.estepper.model.entity.Usuario;

public interface UsuarioService {
    public void guardar(Usuario u);
    public List<Usuario> listadoTotal();
    public Usuario logueado(Integer codigo);
    public Optional<Usuario> findById(Integer id);
    public void eliminar(Integer id);

    public void recuperarCodigo(String correo);

    public void update(String nickname, String email, String contrasenia, Integer id);

}
