package com.estepper.estepper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.estepper.estepper.model.entity.Usuario;
import com.estepper.estepper.model.entity.Progreso;
import com.estepper.estepper.model.enums.TipoProgreso;
import com.estepper.estepper.model.entity.Participante;

import com.estepper.estepper.service.UsuarioService;
import com.estepper.estepper.service.ParticipanteService;
import com.estepper.estepper.service.ProgresoService;

//Controlador de recursos
//Controlador para API REST que se encarga de las solicitudes HTTP entrantes para luego llamar a un servicio
//y coger los datos de la base de datos
//se necesita estar logueado hay que mirar eso
@RestController
public class ApiRestController {

    @Autowired
    private UsuarioService usuario;

    @Autowired
    private ParticipanteService part;

    @Autowired
    private ProgresoService progreso;

    @GetMapping("/progreso/peso") 
    public List<Progreso> datosPeso() {
        Participante p =  part.findById(getUsuario().id).get();
        return progreso.datos(p, TipoProgreso.PESO);
    }

    @GetMapping("/progreso/perimetro") 
    public List<Progreso> datosPerimetro() {
        Participante p =  part.findById(getUsuario().id).get();
        return progreso.datos(p, TipoProgreso.PERIMETRO);
    }

    public Usuario getUsuario() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }

        String codigo = userDetails.getUsername(); // codigo del logueado

        Usuario user = usuario.logueado(Integer.parseInt(codigo));
        return user;
    }

    @GetMapping("/index/sesiones")
    public Participante getParticipante() {
 
        Usuario u = getUsuario();
        if(u instanceof Participante){
            Participante p = part.findById(u.id).get();
            return p;
        }

        return null;
    }



    

}
