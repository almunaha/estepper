package com.estepper.estepper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.estepper.estepper.model.entity.Grupo;

import com.estepper.estepper.service.GrupoService;
import com.estepper.estepper.service.ParticipanteService;


import com.estepper.estepper.service.UsuarioService;//no estoy segura de si lo necesito

@Controller
public class GruposController {

    @Autowired //inyectar recursos de la clase GrupoService
    private GrupoService grupo;

    @Autowired 
    private ParticipanteService part;

    @Autowired 
    private UsuarioService user;

    
    @GetMapping("/listaGrupos")
    public String grupos(Model model){
        List<Grupo> listaGrupos = grupo.listaGrupos();
        model.addAttribute("listaGrupos", listaGrupos);
        
        return "grupos";
    } 

    @GetMapping("/unirAgrupo/{id}")
    public String unirAgrupo(@PathVariable("id") Integer id, Model model){
        model.addAttribute("participante", part.findById(id).get());
        model.addAttribute("user", user.findById(id).get());
        model.addAttribute("idparticipante", id);

        List<Grupo> listaGrupos = grupo.listaGrupos();
        model.addAttribute("listaGrupos", listaGrupos);
     
        return "unirAgrupo";
    }  

}







