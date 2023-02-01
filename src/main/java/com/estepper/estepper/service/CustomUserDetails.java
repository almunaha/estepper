package com.estepper.estepper.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.estepper.estepper.model.entity.Usuario;

public class CustomUserDetails implements UserDetails{

    private Usuario user;
    
    public CustomUserDetails(Usuario user){
        this.user=user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return user.getContrasenia();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        
        String s=Integer.toString(user.getCodigo());
        return s;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

    public String getNombre() {
        return user.getNombre();
    }

    public String getEstadoCuenta() {
        return user.getEstadoCuenta().toString();
    }
    
}
