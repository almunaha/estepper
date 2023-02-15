package com.estepper.estepper.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.estepper.estepper.model.entity.Usuario;
import com.estepper.estepper.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService{

    @Autowired
    private UsuarioRepository repo; //inyección de dependencias del usuario dao api

    public UserDetails loadUserByUsername(String codigo) throws UsernameNotFoundException {
        
        Usuario user = repo.findByCodigo(Integer.parseInt(codigo));
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }

    @Override
    public List<Usuario> listadoTotal(){
        return(List<Usuario>) repo.findAll();
    }

    @Override
    public Usuario logueado(Integer codigo){
        return repo.findByCodigo(codigo);
    }

    @Override
    public Optional<Usuario> findById(Integer id) {
        return repo.findById(id);
    }

    @Override
    public void update(String nombre, String apellidos, String email, String contrasenia, Integer id) {
        repo.update(nombre, apellidos, email, contrasenia, id);
        
    }
    
    @Override
    public void eliminarUsuario(Usuario u){
        repo.delete(u);
    }
}
