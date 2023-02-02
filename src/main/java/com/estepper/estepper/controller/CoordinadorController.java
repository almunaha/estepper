package com.estepper.estepper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoordinadorController {
    
    @GetMapping("/inicio")
    public String coordinador(){
        return "coordinador";
    }   
}
