package com.estepper.estepper.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

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
import com.estepper.estepper.model.entity.Coordinador;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Usuario;

import com.estepper.estepper.service.GrupoService;
import com.estepper.estepper.service.ParticipanteService;
import com.estepper.estepper.service.MaterialService;
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

    @Autowired
    private MaterialService materialS;

    
    @GetMapping("/listaGrupos")
    public String grupos(Model model){
        if(getUsuario() instanceof Coordinador){
            List<Grupo> listaGrupos = grupo.listaGrupos(getUsuario().getId());
            model.addAttribute("listaGrupos", listaGrupos);
            model.addAttribute("user", getUsuario());
            model.addAttribute("grupo", new Grupo());
            return "grupos";
        }
        else return "redirect:/";
    } 

    @PostMapping("/grupos/guardar")
    public String guardarGrupo(Grupo elgrupo){
        if(getUsuario() instanceof Coordinador){
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
        else return "redirect:/";
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
        Grupo gr = grupo.getGrupo(id);
        if(getUsuario() instanceof Coordinador && gr.getIdCoordinador() == getUsuario().getId()){
            materialS.deleteByGrupo(gr);
            grupo.delete(id);
            return "redirect:/listaGrupos";
        } else return "redirect:/";
    }

    
    @GetMapping("/unirAgrupo/{id}")
    public String unirAgrupo(@PathVariable("id") Integer id, Model model){
        if(getUsuario() instanceof Coordinador){
            model.addAttribute("participante", part.findById(id).get());
            model.addAttribute("user", user.findById(id).get());
            model.addAttribute("idparticipante", id);

            List<Grupo> listaGrupos = grupo.listaGrupos(getUsuario().getId());
            model.addAttribute("listaGrupos", listaGrupos);
        
            return "unirAgrupo";
        } else return "redirect:/";
    }  


    @GetMapping("/unGrupo/{idGrupo}")
    public String unGrupo(@PathVariable("idGrupo") Integer idGrupo, Model model){
        Grupo g = grupo.getGrupo(idGrupo);
        if(getUsuario() instanceof Coordinador && g.getIdCoordinador() == getUsuario().getId()){
            model.addAttribute("listadoParticipantesGrupo", part.listadoGrupo(g));
            model.addAttribute("grupo", g);
            model.addAttribute("user", getUsuario());
        
            return "unGrupo";
        } else return "redirect:/";
    }  

    //MATERIALES:
    @GetMapping("/materialesGrupo/{id}")
    public String mostrarMateriales(@PathVariable("id") Integer id, Model model) {
        Grupo g = grupo.getGrupo(id);
        if(getUsuario() instanceof Coordinador && g.getIdCoordinador() == getUsuario().getId()){
            Usuario elusuario = getUsuario();
            model.addAttribute("user", elusuario);
            model.addAttribute("listado", materialS.materialesGrupo(g));
            Materiales material = new Materiales();
            model.addAttribute("material", material);
            model.addAttribute("id", id);
            return "materialesGrupo";
        } else return "redirect:/";
    }

    //subir material a un grupo
    @PostMapping("/process_materialGrupo/{id}")
    public String procesoMaterial(@PathVariable("id") Integer id, @ModelAttribute Materiales material, @RequestParam("file") MultipartFile file){
            Grupo elgrupo = grupo.getGrupo(id);
            if(getUsuario() instanceof Coordinador && elgrupo.getIdCoordinador() == getUsuario().getId()){
                material.setGrupo(elgrupo);
                if(!file.isEmpty()){
                    Path rutaArchivo = Paths.get("src//main//resources//static/materiales");
                    String rutaAbsoluta = rutaArchivo.toFile().getAbsolutePath();
                    try {
                        byte[] bytesArc = file.getBytes(); 
                        Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + file.getOriginalFilename());
                        Files.write(rutaCompleta, bytesArc);
                        material.setLink(rutaCompleta.toString());
                        List<Participante> losparticipantes = part.listadoGrupo(elgrupo);
                        for(int i = 0; i < losparticipantes.size(); i++){
                            material.setParticipante(losparticipantes.get(i));
                            materialS.updateMaterial(material);
                        }
                    } catch (Exception e) {
                        String mensaje = "Ha ocurrido un error: " + e.getMessage();
                        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
                    }   
                    

                }
            }
        return "redirect:/materialesGrupo/{id}";
    }

    @GetMapping("/eliminarMaterialGrupo/{id}")
    public String processElimMaterial(@PathVariable("id") Integer id) {
        if(getUsuario() instanceof Coordinador){
            materialS.eliminarMaterialGrupo(id);
        }

        Integer idG = materialS.getMaterial(id).getGrupo().getId();
       return "redirect:/materialesGrupo/" + idG;
    }

    @GetMapping("/echargrupo/{id}")
    public String echargrupo(@PathVariable("id") Integer id){
        if(getUsuario() instanceof Coordinador){
            part.quitargrupo(id);
            return "redirect:/listaGrupos";
        } else return "redirect:/";
    }

    public Usuario getUsuario(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }

        if (userDetails != null) {
            String codigo = userDetails.getUsername(); // codigo del logueado

            Usuario usuario = user.logueado(Integer.parseInt(codigo)); // atributos del logueado
            return usuario;
        }
        return null;

    }
   

}







