package com.estepper.estepper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.estepper.estepper.model.entity.Grupo;
import com.estepper.estepper.repository.GrupoRepository;
import com.estepper.estepper.service.GrupoService;
import com.estepper.estepper.service.ParticipanteService;


import com.estepper.estepper.service.UsuarioService;//no estoy segura de si lo necesito

@Controller
public class GruposController {

    @Autowired //inyectar recursos de la clase GrupoService
    private GrupoService grupo;

    @Autowired 
    private GrupoRepository grupoRepository; //PREGUNTAR SI ESTO SE PUEDE HACER

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

    @GetMapping("/grupos/nuevo")
    public String mostrarFormularioDeNuevoProducto(Model model){
        model.addAttribute("grupo", new Grupo());
        return "nuevo_grupo";
    }

    @PostMapping("/grupos/guardar")
    public String guardarGrupo(Grupo grupo){
        grupoRepository.save(grupo);
        return"redirect:/listaGrupos";
    }

    @GetMapping("/grupos/editar/{id}")
    public ModelAndView mostrarFormularioDeEditarGrupo(@PathVariable(name = "id") Integer id){
       ModelAndView modelo = new ModelAndView("editar_grupo");

       Grupo gr = grupo.getGrupo(id);
     
       modelo.addObject("grupo", gr);

       return modelo;
    }

    //ESTE ESTABA BIEN CON LO DE ANTES
    @GetMapping("/unirAgrupo/{id}")
    public String unirAgrupo(@PathVariable("id") Integer id, Model model){
        model.addAttribute("participante", part.findById(id).get());
        model.addAttribute("user", user.findById(id).get());
        model.addAttribute("idparticipante", id);

        List<Grupo> listaGrupos = grupo.listaGrupos();
        model.addAttribute("listaGrupos", listaGrupos);
     
        return "unirAgrupo";
    }  

    
    /*@GetMapping("/actualizarGrupos/{idP}/{idG}")
    public String actualizarGrupos(@PathVariable(name = "idP") Integer idP, @PathVariable(name = "idG") Integer idG, Model model){
        //List<Grupo> listaGrupos = grupo.listaGrupos();
        //model.addAttribute("listaGrupos", listaGrupos);

        Grupo gr = grupo.getGrupo(idG);
        Participante pa = part.getParticipante(idP);

        pa.setGrupo(gr);

       // pa.setIdGrupo(idG);
        gr.setNumParticipantes(gr.getNumParticipantes() + 1);
        
        
        return"redirect:/listaGrupos";
    } 
    */

   /* @PostMapping("/actualizarGrupos/{idP}/{idG}")
    public String actualizarGrupos(@PathVariable(name = "idP") Integer id, @PathVariable(name = "idG") Integer idG,Model model){
        List<Grupo> listaGrupos = grupo.listaGrupos();
        model.addAttribute("listaGrupos", listaGrupos);
        
        return"redirect:/listaGrupos";
    } */



    @GetMapping("/unGrupo/{idGrupo}")
    public String unGrupo(@PathVariable("idGrupo") Integer idGrupo, Model model){

        Grupo g = grupo.getGrupo(idGrupo);
        model.addAttribute("listadoParticipantesGrupo", part.listadoGrupo(g));
        model.addAttribute("total", g.getNumParticipantes());
        model.addAttribute("nombreGrupo", g.getNombre());
        //model.addAttribute("grupo", user.findById(idGrupo).get());
     
        return "unGrupo";
    }  

   

}







