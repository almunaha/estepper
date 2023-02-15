package com.estepper.estepper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estepper.estepper.model.entity.Usuario;
import com.estepper.estepper.service.UsuarioService;

//Controlador de recursos
@RestController
@RequestMapping("/usuarios")
public class ProgresoController {

    @Autowired
    private UsuarioService usuario;

    @GetMapping //siempre que accedamos a /usuarios accedera al listado y lo ejecutará
    public List<Usuario> listado(){
        return usuario.listadoTotal();
    }


    
}