package com.estepper.estepper.service;

import java.util.List;
import java.util.Optional;

import com.estepper.estepper.model.entity.Usuario;

import com.estepper.estepper.model.enums.Estado;

public interface UsuarioService {
    public void guardar(Usuario u);

    public List<Usuario> listadoTotal();

    public Usuario logueado(Integer codigo);

    public Optional<Usuario> findById(Integer id);

    public void eliminar(Integer id);

    public Usuario findByCodigo(Integer codigo);

    public void recuperarCodigo(String correo, String contrasenia, String encodedPasword);

    public void update(String nickname, String email, String contrasenia, Estado estado, Integer id);

    public void mandarAnalitica(Usuario u);

}
