package com.estepper.estepper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.estepper.estepper.model.entity.Participante;

import com.estepper.estepper.service.ParticipanteService;

@Controller
public class CoordinadorController {

    @Autowired //inyectar recursos de la clase UsuarioService
    private ParticipanteService part;
    
    @GetMapping("/listado")
    public String participantes(Model model){
        List<Participante> listado = part.listado();
        model.addAttribute("listado", listado);

        return "participantes";
    } 
}
