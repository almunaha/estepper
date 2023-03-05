package com.estepper.estepper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.estepper.estepper.model.entity.FaseValoracion;
import com.estepper.estepper.model.entity.Exploracion;
import com.estepper.estepper.model.entity.Findrisc;
import com.estepper.estepper.model.entity.Materiales;
import com.estepper.estepper.model.entity.Objetivo;
import com.estepper.estepper.model.entity.Progreso;
import com.estepper.estepper.model.entity.Ficha;
import com.estepper.estepper.model.entity.Usuario;
import com.estepper.estepper.model.entity.Sesion;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Coordinador;
import com.estepper.estepper.model.entity.Clasificacion;
import com.estepper.estepper.model.entity.Antecedentes;
import com.estepper.estepper.model.entity.ActividadFisica;
import com.estepper.estepper.model.entity.AlimentacionVal;
import com.estepper.estepper.model.enums.Estado;
import com.estepper.estepper.model.enums.TipoProgreso;
import com.estepper.estepper.service.FaseValoracionService;
import com.estepper.estepper.service.FichaService;
import com.estepper.estepper.service.MaterialService;
import com.estepper.estepper.service.ObjetivoService;
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

    @Autowired
    private ObjetivoService obj;

    @Autowired
    private MaterialService materialS;


    @GetMapping("/menu")
    public String menu(){
        return "menu";
    }

    @GetMapping("/sesiones")
    public String sesiones(Model model){

        Usuario u = getUsuario();
        model.addAttribute("user", getUsuario());      
        
        if(u instanceof Participante){
            Participante p = participante.findById(u.id).get();

            if(p.getGrupo() != null) return "sesiones";
            else return "acceso";
        }

        else return "redirect:/";

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
        Participante p = participante.findById(id).get();
        if(getUsuario() instanceof Coordinador && (p.getIdCoordinador() == getUsuario().getId() || p.getEstadoCuenta() == Estado.BAJA)){
            model.addAttribute("user", getUsuario());
            model.addAttribute("participante", p);
            List<FaseValoracion> formularios = fasevaloracion.faseValoracion(p);
            Exploracion exploracion = null;
            for(int i = 0; i < formularios.size(); i++){
                if(formularios.get(i) instanceof Exploracion) {
                    exploracion = (Exploracion) formularios.get(i);
                }
            }

            model.addAttribute("exploracion", exploracion);
            return "exploracion";
        } else return "redirect:/";

    }

    @PostMapping("/process_exploracion/{id}")
    public String processExploracion(@PathVariable("id") Integer id, @ModelAttribute Exploracion exploracion) {
        Participante p = participante.findById(id).get();
        if(getUsuario() instanceof Coordinador && (p.getIdCoordinador() == getUsuario().getId() || p.getEstadoCuenta() == Estado.BAJA)){
            List<FaseValoracion> formularios = fasevaloracion.faseValoracion(p);
            Findrisc findrisc = null;
            for(int i = 0; i < formularios.size(); i++){
                if(formularios.get(i) instanceof Findrisc) {
                    findrisc = (Findrisc) formularios.get(i);
                }
            }
            fasevaloracion.updateExploracion(exploracion.primeravez, exploracion.sexo, exploracion.peso, exploracion.talla, exploracion.cmcintura, exploracion.edad, exploracion.imc, participante.findById(id).get());
            fasevaloracion.actualizarFindrisc(exploracion, findrisc);

            return "redirect:/valoracion/{id}";
        } else return "redirect:/";
    }

    @GetMapping("/findrisc/{id}")
    public String findrisc(@PathVariable Integer id, Model model){
        Participante p = participante.findById(id).get();
        if(getUsuario() instanceof Coordinador && (p.getIdCoordinador() == getUsuario().getId() || p.getEstadoCuenta() == Estado.BAJA)){
            model.addAttribute("user", getUsuario());
            model.addAttribute("participante", p);
            List<FaseValoracion> formularios = fasevaloracion.faseValoracion(p);
            Findrisc findrisc = null;
            for(int i = 0; i < formularios.size(); i++){
                if(formularios.get(i) instanceof Findrisc) {
                    findrisc = (Findrisc) formularios.get(i);
                }
            }

            model.addAttribute("findrisc", findrisc);
            return "findriscPart";
        } else return "redirect:/";
    }

    @PostMapping("/process_findrisc/{id}")
    public String processFindrisc(@PathVariable("id") Integer id, @ModelAttribute Findrisc findrisc){
        Participante p = participante.findById(id).get();
        if(getUsuario() instanceof Coordinador && (p.getIdCoordinador() == getUsuario().getId() || p.getEstadoCuenta() == Estado.BAJA)){
            fasevaloracion.updateFindrisc(p,findrisc.puntosedad, findrisc.puntosimc, findrisc.puntoscmcintura, findrisc.ptosactfisica,
            findrisc.ptosfrecfruta, findrisc.ptosmedicacion, findrisc.ptosglucosa, findrisc.ptosdiabetes, findrisc.puntuacion,
            findrisc.escalarriesgo);

            Exploracion exploracion = null;
            List<FaseValoracion> formularios = fasevaloracion.faseValoracion(p);
            for(int i = 0; i < formularios.size(); i++){
                if(formularios.get(i) instanceof Exploracion) {
                    exploracion = (Exploracion) formularios.get(i);
                }
            }
            if((exploracion.edad >= 35) & (findrisc.puntuacion >= 15)){
                fasevaloracion.crearFormulariosNuevos(p);
            }

            return "redirect:/valoracion/{id}";
        } else return "redirect:/";
    }

    @GetMapping("/clasificacion/{id}")
    public String clasificacion(@PathVariable Integer id, Model model){
        Participante p = participante.findById(id).get();
        if(getUsuario() instanceof Coordinador && (p.getIdCoordinador() == getUsuario().getId() || p.getEstadoCuenta() == Estado.BAJA)){
            model.addAttribute("user", getUsuario());
            model.addAttribute("participante", p);
            List<FaseValoracion> formularios = fasevaloracion.faseValoracion(p);
            Clasificacion clasificacion = null;
            for(int i = 0; i < formularios.size(); i++){
                if(formularios.get(i) instanceof Clasificacion) {
                    clasificacion = (Clasificacion) formularios.get(i);
                }
            }

            model.addAttribute("clasificacion", clasificacion);
            return "clasificacion";
        } else return "redirect:/";
    }

    @PostMapping("/process_clasificacion/{id}")
    public String processClasificacion(@PathVariable("id") Integer id, @ModelAttribute Clasificacion clasificacion){
        Participante p = participante.findById(id).get();
        if(getUsuario() instanceof Coordinador && (p.getIdCoordinador() == getUsuario().getId() || p.getEstadoCuenta() == Estado.BAJA)){
            fasevaloracion.updateClasificacion(clasificacion, p);

            return "redirect:/valoracion/{id}";
        } else return "redirect:/";
    }

    @GetMapping("/antecedentes/{id}")
    public String antecedentes(@PathVariable Integer id, Model model){
        Participante p = participante.findById(id).get();
        if(getUsuario() instanceof Coordinador && (p.getIdCoordinador() == getUsuario().getId() || p.getEstadoCuenta() == Estado.BAJA)){
            model.addAttribute("user", getUsuario());
            model.addAttribute("participante", p);
            List<FaseValoracion> formularios = fasevaloracion.faseValoracion(p);
            Antecedentes antecedentes = null;
            for(int i = 0; i < formularios.size(); i++){
                if(formularios.get(i) instanceof Antecedentes) {
                    antecedentes = (Antecedentes) formularios.get(i);
                }
            }

            model.addAttribute("antecedentes", antecedentes);
            return "antecedentes";
        } else return "redirect:/";
    }

    @PostMapping("/process_antecedentes/{id}")
    public String processantecedentes(@PathVariable("id") Integer id, @ModelAttribute Antecedentes antecedentes){
        Participante p = participante.findById(id).get();
        if(getUsuario() instanceof Coordinador && (p.getIdCoordinador() == getUsuario().getId() || p.getEstadoCuenta() == Estado.BAJA)){
            fasevaloracion.updateAntecedentes(antecedentes, p);

            return "redirect:/valoracion/{id}";
        } else return "redirect:/";
    }

    @GetMapping("/alimentacionval/{id}")
    public String alimentacionval(@PathVariable Integer id, Model model){
        Participante p = participante.findById(id).get();
        if(getUsuario() instanceof Coordinador && (p.getIdCoordinador() == getUsuario().getId() || p.getEstadoCuenta() == Estado.BAJA)){
            model.addAttribute("user", getUsuario());
            model.addAttribute("participante", p);
            List<FaseValoracion> formularios = fasevaloracion.faseValoracion(p);
            AlimentacionVal alimentacionval = null;
            for(int i = 0; i < formularios.size(); i++){
                if(formularios.get(i) instanceof AlimentacionVal) {
                    alimentacionval = (AlimentacionVal) formularios.get(i);
                }
            }

            model.addAttribute("alimentacionval", alimentacionval);
            return "alimentacionval";
        } else return "redirect:/";
    }

    @PostMapping("/process_alimentacionval/{id}")
    public String processalimentacionval(@PathVariable("id") Integer id, @ModelAttribute AlimentacionVal alimentacionval){
        Participante p = participante.findById(id).get();
        if(getUsuario() instanceof Coordinador && (p.getIdCoordinador() == getUsuario().getId() || p.getEstadoCuenta() == Estado.BAJA)){
            fasevaloracion.updateAlimentacionVal(alimentacionval, p);

            return "redirect:/valoracion/{id}";
        } else return "redirect:/";
    }

    @GetMapping("/actfisica/{id}")
    public String actfisica(@PathVariable Integer id, Model model){
        Participante p = participante.findById(id).get();
        if(getUsuario() instanceof Coordinador && (p.getIdCoordinador() == getUsuario().getId() || p.getEstadoCuenta() == Estado.BAJA)){
            model.addAttribute("user", getUsuario());
            model.addAttribute("participante", p);
            List<FaseValoracion> formularios = fasevaloracion.faseValoracion(p);
            ActividadFisica actfisica = null;
            for(int i = 0; i < formularios.size(); i++){
                if(formularios.get(i) instanceof ActividadFisica) {
                    actfisica = (ActividadFisica) formularios.get(i);
                }
            }

            model.addAttribute("actfisica", actfisica);
            return "actfisica";
        } else return "redirect:/";
    }

    @PostMapping("/process_actfisica/{id}")
    public String processactfisica(@PathVariable("id") Integer id, @ModelAttribute ActividadFisica actfisica){
        Participante p = participante.findById(id).get();
        if(getUsuario() instanceof Coordinador && (p.getIdCoordinador() == getUsuario().getId() || p.getEstadoCuenta() == Estado.BAJA)){
            fasevaloracion.updateActividadFisica(actfisica, p);

            return "redirect:/valoracion/{id}";
        } else return "redirect:/";
    }
    
    @GetMapping("/activarcuenta/{id}")
    public String processActCuenta(@PathVariable("id") Integer id) {
        if(getUsuario() instanceof Coordinador){
            Participante p = participante.findById(id).get();
            List<FaseValoracion> formularios = fasevaloracion.faseValoracion(p);
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

            return "redirect:/listado";
        } else return "redirect:/";
    }

    @GetMapping("/eliminarcuenta/{id}")
    public String processElimCuenta(@PathVariable("id") Integer id) {
        if(getUsuario() instanceof Coordinador)
        fasevaloracion.eliminarcuenta(participante.findById(id).get());

       return "redirect:/";
    }

    @GetMapping("/ficha0")
    public String ficha0(){
        if(getUsuario() instanceof Participante){
            return "ficha0";
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

            Usuario user = usuario.logueado(Integer.parseInt(codigo)); // atributos del logueado
            return user;
        }
        return null;
    }

    //PROGRESO:

    @GetMapping("/progreso")
    public String progreso(Model model){

        model.addAttribute("user", getUsuario());
        Participante p = participante.findById(getUsuario().id).get();

        if(p.getEstadoCuenta().equals(Estado.ALTA)){
            model.addAttribute("participante", p);

            List<Progreso> peso = pro.datos(p, TipoProgreso.PESO); 
            model.addAttribute("peso", peso);

            List<Progreso> perimetro = pro.datos(p, TipoProgreso.PERIMETRO); 
            model.addAttribute("perimetro", perimetro);

            model.addAttribute("progreso", new Progreso());
            return "progreso";
        }

        else return "acceso";
    }  

    @PostMapping("/process_peso")
    public String process_peso(Progreso progreso, Model model){
        Participante p = participante.findById(getUsuario().id).get();
        progreso.setParticipante(p);
        progreso.setTipo(TipoProgreso.PESO);

        pro.guardar(progreso);

        return "redirect:/progreso";
    }

    @PostMapping("/process_perimetro")
    public String process_perimetro(Progreso progreso, Model model){
        Participante p = participante.findById(getUsuario().id).get();
        progreso.setParticipante(p);
        progreso.setTipo(TipoProgreso.PERIMETRO);

        pro.guardar(progreso);

        return "redirect:/progreso";
    }

    @GetMapping("/objetivos")
    public String objetivos(Model model){
        Participante p = participante.findById(getUsuario().id).get();
        List<Objetivo> listaObjetivos = obj.listaObjetivos(p);
        model.addAttribute("listaObjetivos", listaObjetivos);
        model.addAttribute("user", getUsuario());

        return "objetivos";
    } 
    
    @GetMapping("/objetivos/nuevo")
    public String mostrarFormularioDeNuevoObjetivo(Model model){
        if(getUsuario() instanceof Participante){
            model.addAttribute("objetivo", new Objetivo());
            model.addAttribute("user", getUsuario());
            return "nuevo_objetivo";
        } else return "redirect:/";
    }

    @PostMapping("/objetivos/guardar")
    public String guardarObjetivo(Objetivo objetivo){
        Participante p = participante.findById(getUsuario().id).get();
        if(getUsuario().getId() == objetivo.getParticipante().getId()){
            objetivo.setParticipante(p);
            obj.guardar(objetivo); 
            return "redirect:/objetivos";
        } else return "redirect:/";
    }

    @RequestMapping("/objetivos/eliminar/{id}")
    public String eliminarObjetivo(@PathVariable(name = "id") Integer id){
        Objetivo o = obj.getObjetivo(id);
        if(getUsuario() instanceof Participante && getUsuario().getId() == o.getParticipante().getId()){
            obj.borrar(id);
            return"redirect:/objetivos";
        } else return "redirect:/";
    }

    /*@GetMapping("/objetivos/editar/{id}")
    public ModelAndView mostrarFormularioDeEditarObjetivo(@PathVariable(name = "id") Integer id){
       ModelAndView modelo = new ModelAndView("editar_objetivo");
        
       Objetivo o = obj.getObjetivo(id);
     
       modelo.addObject("objetivo", o);
       modelo.addObject("user", getUsuario());

       return modelo;
    }*/

    @GetMapping("/objetivos/editar/{id}")   
    public String editarObjetivo(@PathVariable("id") Integer id, Model model){
        Objetivo o = obj.getObjetivo(id);
        if(getUsuario() instanceof Participante && getUsuario().getId() == o.getParticipante().getId()){
            model.addAttribute("user", getUsuario());
            model.addAttribute("objetivo", o);
            return "editar_objetivo";
        } else return "redirect:/";
    }      
    
    @PostMapping("/objetivos/guardar/{id}")
    public String process_editarObjetivo(@PathVariable("id") Integer id, @ModelAttribute Objetivo objetivo){
        if(getUsuario().getId() == objetivo.getParticipante().getId()){
            Participante p = participante.findById(getUsuario().id).get();
            objetivo.setParticipante(p);
            obj.guardar(objetivo);        
            return "redirect:/objetivos";
        } else return "redirect:/";
    }
    

    //MATERIALES:
    @GetMapping("/eliminarMaterial/{id}")
    public String processElimMaterial(@PathVariable("id") Integer id) {
        Materiales material = materialS.getMaterial(id);
        if(getUsuario() instanceof Coordinador){
            materialS.eliminarMaterial(id);
        } else if (getUsuario().getId() == material.getParticipante().getId()){
            materialS.eliminarMaterial(id);
        }
        

       return "redirect:/";
    }

}
