package com.estepper.estepper.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.estepper.estepper.model.entity.Usuario;

//se utiliza?
public class CustomUserDetails implements UserDetails{

    private Usuario user;
    
    public CustomUserDetails(Usuario user){
        this.user=user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getContrasenia();
    }

    @Override
    public String getUsername() {
        
        String s=Integer.toString(user.getCodigo());
        return s;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getNombre() {
        return user.getNombre();
    }

    public String getEstadoCuenta() {
        return user.getEstadoCuenta().toString();
    }
    
}
