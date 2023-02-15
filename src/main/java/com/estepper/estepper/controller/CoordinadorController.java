package com.estepper.estepper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Usuario;
import com.estepper.estepper.model.entity.Grupo;

import com.estepper.estepper.service.ParticipanteService;
import com.estepper.estepper.service.UsuarioService;
import com.estepper.estepper.service.GrupoService;

@Controller
public class CoordinadorController {

    @Autowired //inyectar recursos de la clase ParticipanteService
    private ParticipanteService part;
    
    @Autowired //inyectar recursos de la clase UsuarioService
    private UsuarioService user;

    @Autowired //inyectar recursos de la clase GrupoService
    private GrupoService grupo;

    @GetMapping("/listado")
    public String participantes(Model model){
        List<Participante> listado = part.listado();
        model.addAttribute("listado", listado);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }

        String codigo = userDetails.getUsername(); //codigo del logueado

        Usuario usuario = user.logueado(Integer.parseInt(codigo)); //atributos del logueado
        model.addAttribute("user", usuario);
        return "participantes";
    } 

    @GetMapping("/valoracion/{id}")
    public String fasedevaloracion(@PathVariable("id") Integer id, Model model){
        model.addAttribute("participante", part.findById(id).get());
        model.addAttribute("user", user.findById(id).get());
        model.addAttribute("idparticipante", id);
        return "valoracion";
    }

    @GetMapping("/actualizarGrupos/{idP}/{idG}")
    public String actualizarGrupos(@PathVariable(name = "idP") Integer idP, @PathVariable(name = "idG") Integer idG, Model model){
       Participante usuario = part.findById(idP).get();
        model.addAttribute("user", usuario);
        Grupo g = grupo.getGrupo(idG);

        part.update(idP,usuario.edad, g);
        Integer participantes = g.getNumParticipantes()+1;
        grupo.updateParticipantes(idG, participantes);
   
        return"redirect:/listaGrupos";
    } 
}
