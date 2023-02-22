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
import com.estepper.estepper.model.entity.Progreso;
import com.estepper.estepper.model.entity.Ficha;
import com.estepper.estepper.model.entity.Usuario;
import com.estepper.estepper.model.entity.Sesion;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.enums.TipoProgreso;

import com.estepper.estepper.service.FaseValoracionService;
import com.estepper.estepper.service.FichaService;
import com.estepper.estepper.service.ParticipanteService;
import com.estepper.estepper.service.SesionService;
import com.estepper.estepper.service.UsuarioService;
import com.estepper.estepper.service.ProgresoService;


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

    @Autowired
    private ProgresoService pro;


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

    @GetMapping("/sesion/{num}")
    public String sesion1(@PathVariable Integer num, Model model){
        //necesito idParticipante y numSesion para saber el id de la sesión correspondiente
        model.addAttribute("user", getUsuario());
        Participante p = participante.findById(getUsuario().id).get();
        //sesión seleccionada
        Sesion sesion = ses.buscarSesion(p, num); //cambiar segun sesion
        model.addAttribute("sesion", sesion); 

        //lista de fichas de la sesión seleccionada a través del idSesion
        List<Ficha> fichas = f.fichasSesion(sesion.getId());
        model.addAttribute("fichas", fichas);
        
        model.addAttribute("participante", participante.findById(1)); //coger participante 

        return "sesion";
    }

    @GetMapping("/exploracion/{id}")
    public String exploracion(@PathVariable Integer id, Model model){
        model.addAttribute("user", getUsuario());
        model.addAttribute("participante", participante.findById(id).get());
        List<FaseValoracion> formularios = fasevaloracion.faseValoracion(participante.findById(id).get());
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
        List<FaseValoracion> formularios = fasevaloracion.faseValoracion(participante.findById(id).get());
        Findrisc findrisc = null;
        for(int i = 0; i < formularios.size(); i++){
            if(formularios.get(i) instanceof Findrisc) {
                findrisc = (Findrisc) formularios.get(i);
            }
        }
        fasevaloracion.updateExploracion(exploracion.primeravez, exploracion.sexo, exploracion.peso, exploracion.talla, exploracion.cmcintura, exploracion.edad, exploracion.imc, participante.findById(id).get());
        fasevaloracion.actualizarFindrisc(exploracion, findrisc);

        return "redirect:/valoracion/{id}";
    }

    @GetMapping("/findrisc/{id}")
    public String findrisc(@PathVariable Integer id, Model model){
        model.addAttribute("user", getUsuario());
        model.addAttribute("participante", participante.findById(id).get());
        List<FaseValoracion> formularios = fasevaloracion.faseValoracion(participante.findById(id).get());
        Findrisc findrisc = null;
        for(int i = 0; i < formularios.size(); i++){
            if(formularios.get(i) instanceof Findrisc) {
                findrisc = (Findrisc) formularios.get(i);
            }
        }

        model.addAttribute("findrisc", findrisc);
        return "findriscPart";

    }

    @PostMapping("/process_findrisc/{id}")
    public String processFindrisc(@PathVariable("id") Integer id, @ModelAttribute Findrisc findrisc){
        fasevaloracion.updateFindrisc(participante.findById(id).get(),findrisc.puntosedad, findrisc.puntosimc, findrisc.puntoscmcintura, findrisc.ptosactfisica,
        findrisc.ptosfrecfruta, findrisc.ptosmedicacion, findrisc.ptosglucosa, findrisc.ptosdiabetes, findrisc.puntuacion,
        findrisc.escalarriesgo);

        Exploracion exploracion = null;
        List<FaseValoracion> formularios = fasevaloracion.faseValoracion(participante.findById(id).get());
        for(int i = 0; i < formularios.size(); i++){
            if(formularios.get(i) instanceof Exploracion) {
                exploracion = (Exploracion) formularios.get(i);
            }
        }
        if((exploracion.edad >= 35) & (findrisc.puntuacion >= 15)){
            fasevaloracion.crearFormulariosNuevos(findrisc.getParticipante());
        }

        return "redirect:/valoracion/{id}";
    }

    @GetMapping("/activarcuenta/{id}")
    public String processActCuenta(@PathVariable("id") Integer id) {
        List<FaseValoracion> formularios = fasevaloracion.faseValoracion(participante.findById(id).get());
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
        fasevaloracion.activarcuenta(exploracion, findrisc, id, getUsuario().id);

       return "redirect:/valoracion/{id}";
    }

    @GetMapping("/eliminarcuenta/{id}")
    public String processElimCuenta(@PathVariable("id") Integer id) {
        fasevaloracion.eliminarcuenta(participante.findById(id).get());

       return "redirect:/";
    }

    @GetMapping("/ficha0")
    public String ficha0(){
        return "ficha0";
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

    //PROGRESO:

    @GetMapping("/progreso")
    public String progreso(Model model){
        model.addAttribute("user", getUsuario());
        return "progreso";
    }  

    @GetMapping("/registrarPeso")
    public String registrarPeso(Model model){
        //que solo se pueda fecha actual o anterior
        model.addAttribute("user", getUsuario());
        model.addAttribute("progreso", new Progreso());
        return "registrarPeso";
    }

    @PostMapping("/process_peso")
    public String process_peso(Progreso progreso, Model model){
        Participante p = participante.findById(getUsuario().id).get();
        progreso.setParticipante(p);
        progreso.setTipo(TipoProgreso.PESO);

        pro.guardar(progreso);

        return "redirect:/progreso";

    }

}
