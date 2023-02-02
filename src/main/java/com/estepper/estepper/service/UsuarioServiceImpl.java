package com.estepper.estepper.service;

import java.util.List;

/*import java.util.ArrayList;
import java.util.List;*/
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;*/
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.estepper.estepper.model.entity.Usuario;
import com.estepper.estepper.model.enums.Rol;
import com.estepper.estepper.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService{

    @Autowired
    private UsuarioRepository repo; //inyección de dependencias del usuario dao api

    @Override
    public UserDetails loadUserByUsername(String codigo) throws UsernameNotFoundException {
       /* Usuario u = repo.findByCodigo(codigo); //busca usuario por codigo 

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(u.getRol().toString()));
        
        UserDetails usuarioDet = new User(String.valueOf(u.getCodigo()), u.getContrasenia(), roles);
        
        return usuarioDet;*/ //devuelve el usuario que hemos cargado
        Usuario user = repo.findByCodigo(codigo);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }

    @Override
    public List<Usuario> listado(){
        return(List<Usuario>) repo.findAll();
    }

    @Override
    public List<Usuario> listadoParticipantes(Rol rol){
        return(List<Usuario>) repo.findAllByRol(rol);
    }  

    
}
