package com.estepper.estepper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.estepper.estepper.model.entity.Ficha;
import com.estepper.estepper.model.entity.Usuario;
import com.estepper.estepper.model.entity.Sesion;
import com.estepper.estepper.service.FichaService;
import com.estepper.estepper.service.ParticipanteService;
import com.estepper.estepper.service.SesionService;
import com.estepper.estepper.service.UsuarioService;

import org.springframework.ui.Model;
@Controller
public class ParticipanteController {

    @Autowired
    private ParticipanteService participante;

    @Autowired //inyectar recursos de la clase UsuarioService
    private UsuarioService usuario;

    @Autowired 
    private SesionService ses;

    @Autowired 
    private FichaService f;


    @GetMapping("/menu")
    public String menu(){
        return "menu";
    }

    @GetMapping("/sesiones")
    public String sesiones(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }

        String codigo = userDetails.getUsername(); //codigo del logueado

        Usuario user = usuario.logueado(codigo); 
        model.addAttribute("part", user);
        model.addAttribute("user", getUsuario());        
        return "sesiones";
    }

    @GetMapping("/sesion1/{id}")
    public String sesion1(@PathVariable Integer id, Model model){
        //necesito idParticipante y numSesion para saber el id de la sesión correspondiente
        model.addAttribute("user", getUsuario());
        //sesión seleccionada
        Sesion sesion = ses.buscarSesion(1, 1); 
        model.addAttribute("sesion", sesion); 

        //lista de fichas de la sesión seleccionada a través del idSesion
        List<Ficha> fichas = f.fichasSesion(sesion.getId());
        model.addAttribute("fichas", fichas);
        
        model.addAttribute("participante", participante.findById(id)); //coger participante 

        return "sesion1";
    }

    @GetMapping("/ficha0")
    public String ficha0(){
        return "ficha0";
    }

    @GetMapping("/progreso")
    public String progreso(){
        return "progreso";
    }

    public Usuario getUsuario(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }

        String codigo = userDetails.getUsername(); //codigo del logueado

        Usuario user = usuario.logueado(codigo);
        return user;
    }
}
