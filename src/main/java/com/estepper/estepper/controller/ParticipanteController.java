package com.estepper.estepper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ParticipanteController {
    
    @GetMapping("/progreso")
    public String progreso(){
        return "progreso";
    }

    @GetMapping("/menu")
    public String menu(){
        return "menu";
    }
}
