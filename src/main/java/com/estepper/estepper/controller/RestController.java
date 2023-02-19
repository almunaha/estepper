/*package com.estepper.estepper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

import com.estepper.estepper.model.entity.Usuario;
import com.estepper.estepper.model.entity.Progreso;
import com.estepper.estepper.service.UsuarioService;

//Controlador de recursos
@RestController
@RequestMapping("/usuarios")
public class RestController {

    @Autowired
    private UsuarioService usuario;

    @GetMapping //siempre que accedamos a /usuarios accedera al listado y lo ejecutará
    public List<Usuario> listado(){
        return usuario.listadoTotal();
    }

    public Usuario getUsuario(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }

        String codigo = userDetails.getUsername(); //codigo del logueado

        Usuario user = usuario.logueado(Integer.parseInt(codigo));
        return user;
    }

 
    
}*/
