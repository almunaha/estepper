package com.estepper.estepper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.estepper.estepper.model.entity.Usuario;
import com.estepper.estepper.model.enums.Rol;
import com.estepper.estepper.service.UsuarioService;

@Controller
public class CoordinadorController {

    @Autowired //inyectar recursos de la clase UsuarioService
    private UsuarioService usuario;
    
    @GetMapping("/inicio")
    public String coordinador(){
        return "coordinador";
    }   

    @GetMapping("/listado")
    public String participantes(Model model){
        List<Usuario> listado = usuario.listadoParticipantes(Rol.PARTICIPANTE);
        model.addAttribute("listado", listado);

        return "participantes";
    } 
}
