package com.estepper.estepper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.estepper.estepper.model.entity.FaseValoracion;
import com.estepper.estepper.model.entity.Exploracion;
import com.estepper.estepper.model.entity.Findrisc;
import com.estepper.estepper.model.entity.Ficha;
import com.estepper.estepper.model.entity.Usuario;
import com.estepper.estepper.model.entity.Sesion;
import com.estepper.estepper.service.FaseValoracionService;
import com.estepper.estepper.service.FichaService;
import com.estepper.estepper.service.ParticipanteService;
import com.estepper.estepper.service.SesionService;
import com.estepper.estepper.service.UsuarioService;

import org.springframework.ui.Model;
@Controller
public class ParticipanteController {

    @Autowired
    private ParticipanteService participante;

    @Autowired
    private FaseValoracionService fasevaloracion;

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

        Usuario user = usuario.logueado(Integer.parseInt(codigo)); 
        model.addAttribute("part", user);
        model.addAttribute("user", getUsuario());        
        return "sesiones";
    }

    @GetMapping("/sesion1/{id}")
    public String sesion1(@PathVariable Integer id, Model model){
        //necesito idParticipante y numSesion para saber el id de la sesión correspondiente
        model.addAttribute("user", getUsuario());
        //sesión seleccionada
        Sesion sesion = ses.buscarSesion(participante.findById(id), 1); //cambiar segun sesion
        model.addAttribute("sesion", sesion); 

        //lista de fichas de la sesión seleccionada a través del idSesion
        List<Ficha> fichas = f.fichasSesion(sesion.getId());
        model.addAttribute("fichas", fichas);
        
        model.addAttribute("participante", participante.findById(id)); //coger participante 

        return "sesion1";
    }

    @GetMapping("/exploracion/{id}")
    public String exploracion(@PathVariable Integer id, Model model){
        model.addAttribute("user", getUsuario());
        model.addAttribute("participante", participante.findById(id).get());
        List<FaseValoracion> formularios = fasevaloracion.faseValoracion(id);
        Exploracion exploracion = null;
        for(int i = 0; i < formularios.size(); i++){
            if(formularios.get(i) instanceof Exploracion) {
                exploracion = (Exploracion) formularios.get(i);
            }
        }

        model.addAttribute("exploracion", exploracion);
        return "exploracion";

    }

    @PostMapping("/process_exploracion/{id}")
    public String processExploracion(@PathVariable("id") Integer id, @ModelAttribute Exploracion exploracion) {
        
        fasevaloracion.updateExploracion(exploracion.primeravez, exploracion.peso, exploracion.talla, exploracion.cmcintura, exploracion.edad, exploracion.imc, exploracion.id);
        fasevaloracion.actualizarFindrisc(exploracion);

       return "redirect:/";
    }

    @GetMapping("/findrisc/{id}")
    public String findrisc(@PathVariable Integer id, Model model){
        model.addAttribute("user", getUsuario());
        model.addAttribute("participante", participante.findById(id).get());
        List<FaseValoracion> formularios = fasevaloracion.faseValoracion(id);
        Findrisc findrisc = null;
        Exploracion exploracion = null;
        for(int i = 0; i < formularios.size(); i++){
            if(formularios.get(i) instanceof Findrisc) {
                findrisc = (Findrisc) formularios.get(i);
            }
            if(formularios.get(i) instanceof Exploracion) {
                exploracion = (Exploracion) formularios.get(i);
            }
        }

        model.addAttribute("findrisc", findrisc);
        model.addAttribute("exploracion", exploracion);
        return "findriscPart";

    }

    @PostMapping("/process_findrisc/{id}")
    public String processFindrisc(@PathVariable("id") Integer id, @ModelAttribute Findrisc findrisc, @ModelAttribute Exploracion exploracion) {
        
        fasevaloracion.updateFindrisc(findrisc.id,findrisc.idParticipante,findrisc.puntosedad, findrisc.puntosimc, findrisc.puntoscmcintura, findrisc.ptosactfisica,
        findrisc.ptosfrecfruta, findrisc.ptosmedicacion, findrisc.ptosglucosa, findrisc.ptosdiabetes, findrisc.puntuacion,
        findrisc.escalarriesgo);

        if((exploracion.edad >= 35) & (findrisc.puntuacion >= 15)){
            fasevaloracion.crearFormulariosNuevos(findrisc.idParticipante);
        }

       return "redirect:/";
    }

    @PostMapping("/activarcuenta/{id}")
    public String processActCuenta(@PathVariable("id") Integer id) {
        List<FaseValoracion> formularios = fasevaloracion.faseValoracion(id);
        Findrisc findrisc = null;
        Exploracion exploracion = null;
        for(int i = 0; i < formularios.size(); i++){
            if(formularios.get(i) instanceof Findrisc) {
                findrisc = (Findrisc) formularios.get(i);
            }
            if(formularios.get(i) instanceof Exploracion) {
                exploracion = (Exploracion) formularios.get(i);
            }
        }
        fasevaloracion.activarcuenta(exploracion, findrisc, id);

       return "redirect:/";
    }

    @PostMapping("/eliminarcuenta/{id}")
    public String processElimCuenta(@PathVariable("id") Integer id) {
        fasevaloracion.eliminarcuenta(id);
        usuario.eliminarUsuario(usuario.findById(id).get());

       return "redirect:/";
    }

    @GetMapping("/ficha0")
    public String ficha0(){
        return "ficha0";
    }

    @GetMapping("/progreso")
    public String progreso(Model model){
        model.addAttribute("user", getUsuario());
        return "progreso";
    }

    public Usuario getUsuario(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }

        String codigo = userDetails.getUsername(); //codigo del logueado

        Usuario user = usuario.logueado(Integer.parseInt(codigo));
        return user;
    }
}
