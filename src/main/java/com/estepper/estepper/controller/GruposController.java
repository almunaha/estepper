package com.estepper.estepper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.estepper.estepper.model.entity.Grupo;
import com.estepper.estepper.service.GrupoService;

@Controller
public class GruposController {
    @Autowired //inyectar recursos de la clase GrupoService
    private GrupoService grupo;
    
    @GetMapping("/listaGrupos")
    public String grupos(Model model){
        List<Grupo> listaGrupos = grupo.listaGrupos();
        model.addAttribute("listaGrupos", listaGrupos);

        return "grupos";
    } 
}







