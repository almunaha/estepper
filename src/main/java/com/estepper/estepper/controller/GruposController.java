package com.estepper.estepper.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;
import org.apache.commons.lang3.RandomStringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.estepper.estepper.model.entity.Grupo;
import com.estepper.estepper.model.entity.Materiales;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Usuario;
import com.estepper.estepper.service.GrupoService;
import com.estepper.estepper.service.ParticipanteService;


import com.estepper.estepper.service.UsuarioService;
 //yo pasaria todo esto a CoordinadorController para no tener tantos
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
        List<Grupo> listaGrupos = grupo.listaGrupos(getUsuario().getId());
        model.addAttribute("listaGrupos", listaGrupos);
        model.addAttribute("user", getUsuario());
        model.addAttribute("grupo", new Grupo());
        return "grupos";
    } 

    @PostMapping("/grupos/guardar")
    public String guardarGrupo(Grupo elgrupo){
        elgrupo.setIdCoordinador(getUsuario().getId());

        String elcodigo = RandomStringUtils.randomAlphanumeric(15).toUpperCase();

        // comprobar que no existe un usuario con ese codigo
        while (grupo.findByCodigo(elcodigo) != null) {
            elcodigo = RandomStringUtils.randomAlphanumeric(15).toUpperCase();
        }
        elgrupo.setCodigo(elcodigo);
        elgrupo.setNumParticipantes(0);
        grupo.save(elgrupo); 
        return"redirect:/listaGrupos";
    }

    @GetMapping("/grupos/editar/{id}")
    public ModelAndView mostrarFormularioDeEditarGrupo(@PathVariable(name = "id") Integer id){
       ModelAndView modelo = new ModelAndView("editar_grupo");
        
       Grupo gr = grupo.getGrupo(id);
     
       modelo.addObject("grupo", gr);
       modelo.addObject("user", getUsuario());

       return modelo;
    }

    @RequestMapping("/grupos/eliminar/{id}")
    public String eliminarGrupo(@PathVariable(name = "id") Integer id){
        grupo.delete(id);
        return"redirect:/listaGrupos";
    }

    
    @GetMapping("/unirAgrupo/{id}")
    public String unirAgrupo(@PathVariable("id") Integer id, Model model){
        model.addAttribute("participante", part.findById(id).get());
        model.addAttribute("user", user.findById(id).get());
        model.addAttribute("idparticipante", id);

        List<Grupo> listaGrupos = grupo.listaGrupos(getUsuario().getId());
        model.addAttribute("listaGrupos", listaGrupos);
     
        return "unirAgrupo";
    }  


    @GetMapping("/unGrupo/{idGrupo}")
    public String unGrupo(@PathVariable("idGrupo") Integer idGrupo, Model model){

        Grupo g = grupo.getGrupo(idGrupo);
        model.addAttribute("listadoParticipantesGrupo", part.listadoGrupo(g));
        model.addAttribute("grupo", g);
        model.addAttribute("user", getUsuario());
     
        return "unGrupo";
    }  

    //MATERIALES:
    @GetMapping("/materialesGrupo/{id}")
    public String mostrarMateriales(@PathVariable("id") Integer id, Model model) {
        Usuario elusuario = getUsuario();
        model.addAttribute("user", elusuario);
        model.addAttribute("listado", part.materialesGrupo(grupo.getGrupo(id)));
        Materiales material = new Materiales();
        model.addAttribute("material", material);
        model.addAttribute("id", id);
        return "materialesGrupo";
    }

    @PostMapping("/process_materialGrupo/{id}")
    public String procesoMaterial(@PathVariable("id") Integer id, @ModelAttribute Materiales material, @RequestParam("file") MultipartFile file){
            Grupo elgrupo = grupo.getGrupo(id);
            material.setGrupo(elgrupo);
            if(!file.isEmpty()){
                Path rutaArchivo = Paths.get("src//main//resources//static/materiales");
                String rutaAbsoluta = rutaArchivo.toFile().getAbsolutePath();
                try {
                    byte[] bytesArc = file.getBytes(); 
                    Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + file.getOriginalFilename());
                    Files.write(rutaCompleta, bytesArc);
                    material.setLink(file.getOriginalFilename());
                    List<Participante> losparticipantes = part.listadoGrupo(elgrupo);
                    for(int i = 0; i < losparticipantes.size(); i++){
                        material.setParticipante(losparticipantes.get(i));
                        part.updateMaterial(material);
                    }
                } catch (Exception e) {
                    String mensaje = "Ha ocurrido un error: " + e.getMessage();
                    JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
                }   
                

            }
        return "redirect:/";
    }

    @GetMapping("/eliminarMaterialGrupo/{id}")
    public String processElimMaterial(@PathVariable("id") Integer id) {
        part.eliminarMaterialGrupo(id);

       return "redirect:/";
    }

    public Usuario getUsuario(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }

        String codigo = userDetails.getUsername(); //codigo del logueado

        Usuario usuario = user.logueado(Integer.parseInt(codigo));
        return usuario;
    }
   

}







