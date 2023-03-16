package com.estepper.estepper.controller;

import java.util.List;

import javax.swing.JOptionPane;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.nio.file.Files;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.estepper.estepper.model.entity.FichaEleccion;
import com.estepper.estepper.model.entity.Usuario;
import com.estepper.estepper.model.entity.Sesion;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Coordinador;
import com.estepper.estepper.model.entity.Clasificacion;
import com.estepper.estepper.model.entity.Antecedentes;
import com.estepper.estepper.model.entity.Actividad;
import com.estepper.estepper.model.entity.ActividadFisica;
import com.estepper.estepper.model.entity.AlimentacionVal;

import com.estepper.estepper.model.enums.Asistencia;
import com.estepper.estepper.model.enums.Estado;
import com.estepper.estepper.model.enums.EstadoSesion;
import com.estepper.estepper.model.enums.TipoProgreso;
import com.estepper.estepper.service.ActividadService;
import com.estepper.estepper.service.FaseValoracionService;
import com.estepper.estepper.service.FichaService;
import com.estepper.estepper.service.MaterialService;
import com.estepper.estepper.service.ObjetivoService;
import com.estepper.estepper.service.ParticipanteService;
import com.estepper.estepper.service.SesionService;
import com.estepper.estepper.service.UsuarioService;
import com.estepper.estepper.service.ProgresoService;
import com.estepper.estepper.service.GrupoService;

@Controller
public class ParticipanteController {

    @Autowired
    private ParticipanteService participante;

    @Autowired
    private FaseValoracionService fasevaloracion;

    @Autowired // inyectar recursos de la clase UsuarioService
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

    @Autowired
    private ActividadService act;

    @Autowired
    private GrupoService grupoS;

    @GetMapping("/menu")
    public String menu() {
        return "menu";
    }

    // SESIONES:
    @GetMapping("/sesiones")
    public String sesiones(Model model) {

        Usuario u = getUsuario();
        model.addAttribute("user", getUsuario());

        if (u instanceof Participante && u.getEstadoCuenta().equals(Estado.ALTA)) {
            return "sesiones";
        } else
            return "acceso";

    }

    @GetMapping("/sesion/{num}")
    public String sesion1(@PathVariable Integer num, Model model) {

        if (num < 1 || num > 10) {
            return "redirect:/sesiones";
        } else {
            // necesito idParticipante y numSesion para saber el id de la sesión
            // correspondiente
            model.addAttribute("user", getUsuario());
            Participante p = participante.findById(getUsuario().getId()).get();
            if (p.getEstadoCuenta().equals(Estado.ALTA)) {
                // sesión seleccionada
                Sesion sesion = ses.buscarSesion(p, num); // cambiar segun sesion
                model.addAttribute("sesion", sesion);

                model.addAttribute("participante", participante.findById(1)); // coger participante

                return "sesion";
            } else
                return "acceso";
        }
    }

    @PostMapping("process_sesion/{num}")
    public String actualizar(@PathVariable("num") Integer num, @ModelAttribute Sesion sesion) {
        Participante p = participante.getParticipante(getUsuario().getId());
        if (p.getEstadoCuenta().equals(Estado.ALTA)) {
            Sesion orig = ses.buscarSesion(p, num);

            Sesion actualizada = new Sesion(orig.getId(), orig.getNumSesion(), orig.getParticipante(), orig.getEstado(),
                    sesion.getObservaciones(), sesion.getAsistencia(), orig.getCmsPerdidos(), orig.getPesoPerdido());

            ses.guardar(actualizada);

            return "redirect:/sesiones";
        } else
            return "acceso";
    }

    @GetMapping("/exploracion/{id}")
    public String exploracion(@PathVariable Integer id, Model model) {
        Participante p = participante.findById(id).get();
        if (getUsuario() instanceof Coordinador
                && (p.getIdCoordinador() == getUsuario().getId() || p.getEstadoCuenta() == Estado.BAJA)) {
            model.addAttribute("user", getUsuario());
            model.addAttribute("participante", p);
            List<FaseValoracion> formularios = fasevaloracion.faseValoracion(p);
            Exploracion exploracion = null;
            for (int i = 0; i < formularios.size(); i++) {
                if (formularios.get(i) instanceof Exploracion) {
                    exploracion = (Exploracion) formularios.get(i);
                }
            }

            model.addAttribute("exploracion", exploracion);
            return "exploracion";
        } else
            return "redirect:/";

    }

    @PostMapping("/process_exploracion/{id}")
    public String processExploracion(@PathVariable("id") Integer id, @ModelAttribute Exploracion exploracion) {
        Participante p = participante.findById(id).get();
        if (getUsuario() instanceof Coordinador
                && (p.getIdCoordinador() == getUsuario().getId() || p.getEstadoCuenta() == Estado.BAJA)) {
            List<FaseValoracion> formularios = fasevaloracion.faseValoracion(p);
            Findrisc findrisc = null;
            for (int i = 0; i < formularios.size(); i++) {
                if (formularios.get(i) instanceof Findrisc) {
                    findrisc = (Findrisc) formularios.get(i);
                }
            }
            fasevaloracion.updateExploracion(exploracion.getPrimeravez(), exploracion.getSexo(), exploracion.getPeso(),
                    exploracion.getTalla(), exploracion.getCmcintura(), exploracion.getEdad(), exploracion.getImc(),
                    participante.findById(id).get());
            fasevaloracion.actualizarFindrisc(exploracion, findrisc);

            return "redirect:/valoracion/{id}";
        } else
            return "redirect:/";
    }

    @GetMapping("/valoracion/{id}")
    public String fasedevaloracion(@PathVariable("id") Integer id, Model model) {
        if ((getUsuario() instanceof Coordinador)
                && ((participante.findById(id).get().getIdCoordinador() == getUsuario().getId())
                        || participante.findById(id).get().getEstadoCuenta().equals(Estado.BAJA))) {
            Participante part = participante.findById(id).get();
            List<FaseValoracion> formularios = fasevaloracion.faseValoracion(part);
            if(formularios.size() > 2) model.addAttribute("formularios", formularios);
            model.addAttribute("participante", part);
            model.addAttribute("usuario", usuario.findById(id).get());
            model.addAttribute("user", getUsuario());
            model.addAttribute("idparticipante", id);
            return "valoracion";
        }

        else
            return "redirect:/";
    }

    @GetMapping("/expediente/{id}")
    public String expediente(@PathVariable("id") Integer id, Model model) {
        if ((getUsuario() instanceof Coordinador)
                && ((participante.findById(id).get().getIdCoordinador() == getUsuario().getId())
                        || participante.findById(id).get().getEstadoCuenta().equals(Estado.BAJA))) {
            Participante part = participante.findById(id).get();
            model.addAttribute("participante", part);
            model.addAttribute("user", getUsuario());
            return "expediente";
        }

        else
            return "redirect:/";
    }

    @GetMapping("/findrisc/{id}")
    public String findrisc(@PathVariable Integer id, Model model) {
        Participante p = participante.findById(id).get();
        if (getUsuario() instanceof Coordinador
                && (p.getIdCoordinador() == getUsuario().getId() || p.getEstadoCuenta() == Estado.BAJA)) {
            model.addAttribute("user", getUsuario());
            model.addAttribute("participante", p);
            List<FaseValoracion> formularios = fasevaloracion.faseValoracion(p);
            Findrisc findrisc = null;
            for (int i = 0; i < formularios.size(); i++) {
                if (formularios.get(i) instanceof Findrisc) {
                    findrisc = (Findrisc) formularios.get(i);
                }
            }

            model.addAttribute("findrisc", findrisc);
            return "findriscPart";
        } else
            return "redirect:/";
    }

    @PostMapping("/process_findrisc/{id}")
    public String processFindrisc(@PathVariable("id") Integer id, @ModelAttribute Findrisc findrisc) {
        Participante p = participante.findById(id).get();
        if (getUsuario() instanceof Coordinador
                && (p.getIdCoordinador() == getUsuario().getId() || p.getEstadoCuenta() == Estado.BAJA)) {
            fasevaloracion.updateFindrisc(p, findrisc.getPuntosedad(), findrisc.getPuntosimc(), findrisc.getPuntoscmcintura(),
                    findrisc.getPtosactfisica(),
                    findrisc.getPtosfrecfruta(), findrisc.getPtosmedicacion(), findrisc.getPtosglucosa(), findrisc.getPtosdiabetes(),
                    findrisc.getPuntuacion(),
                    findrisc.getEscalarriesgo());

            Exploracion exploracion = null;
            List<FaseValoracion> formularios = fasevaloracion.faseValoracion(p);
            for (int i = 0; i < formularios.size(); i++) {
                if (formularios.get(i) instanceof Exploracion) {
                    exploracion = (Exploracion) formularios.get(i);
                }
            }
            if ((exploracion.getEdad() >= 35) & (findrisc.getPuntuacion() >= 15)) {
                fasevaloracion.crearFormulariosNuevos(p);
            }

            return "redirect:/valoracion/{id}";
        } else
            return "redirect:/";
    }

    @GetMapping("/clasificacion/{id}")
    public String clasificacion(@PathVariable Integer id, Model model) {
        Participante p = participante.findById(id).get();
        if (getUsuario() instanceof Coordinador
                && (p.getIdCoordinador() == getUsuario().getId() || p.getEstadoCuenta() == Estado.BAJA)) {
            model.addAttribute("user", getUsuario());
            model.addAttribute("participante", p);
            List<FaseValoracion> formularios = fasevaloracion.faseValoracion(p);
            Clasificacion clasificacion = null;
            Exploracion exploracion = null;
            Findrisc findrisc = null;
            for (int i = 0; i < formularios.size(); i++) {
                if (formularios.get(i) instanceof Clasificacion) {
                    clasificacion = (Clasificacion) formularios.get(i);
                }
                if (formularios.get(i) instanceof Exploracion) {
                    exploracion = (Exploracion) formularios.get(i);
                }
                if (formularios.get(i) instanceof Findrisc) {
                    findrisc = (Findrisc) formularios.get(i);
                }
            }
            model.addAttribute("clasificacion", clasificacion);
            model.addAttribute("exploracion", exploracion);
            model.addAttribute("findrisc", findrisc);
            return "clasificacion";
        } else
            return "redirect:/";
    }

    @PostMapping("/process_clasificacion/{id}")
    public String processClasificacion(@PathVariable("id") Integer id, @ModelAttribute Clasificacion clasificacion) {
        Participante p = participante.findById(id).get();
        if (getUsuario() instanceof Coordinador
                && (p.getIdCoordinador() == getUsuario().getId() || p.getEstadoCuenta() == Estado.BAJA)) {
            fasevaloracion.updateClasificacion(clasificacion, p);

            return "redirect:/valoracion/{id}";
        } else
            return "redirect:/";
    }

    @GetMapping("/antecedentes/{id}")
    public String antecedentes(@PathVariable Integer id, Model model) {
        Participante p = participante.findById(id).get();
        if (getUsuario() instanceof Coordinador
                && (p.getIdCoordinador() == getUsuario().getId() || p.getEstadoCuenta() == Estado.BAJA)) {
            model.addAttribute("user", getUsuario());
            model.addAttribute("participante", p);
            List<FaseValoracion> formularios = fasevaloracion.faseValoracion(p);
            Antecedentes antecedentes = null;
            for (int i = 0; i < formularios.size(); i++) {
                if (formularios.get(i) instanceof Antecedentes) {
                    antecedentes = (Antecedentes) formularios.get(i);
                }
            }

            model.addAttribute("antecedentes", antecedentes);
            return "antecedentes";
        } else
            return "redirect:/";
    }

    @PostMapping("/process_antecedentes/{id}")
    public String processantecedentes(@PathVariable("id") Integer id, @ModelAttribute Antecedentes antecedentes) {
        Participante p = participante.findById(id).get();
        if (getUsuario() instanceof Coordinador
                && (p.getIdCoordinador() == getUsuario().getId() || p.getEstadoCuenta() == Estado.BAJA)) {
            fasevaloracion.updateAntecedentes(antecedentes, p);

            return "redirect:/valoracion/{id}";
        } else
            return "redirect:/";
    }

    @GetMapping("/alimentacionval/{id}")
    public String alimentacionval(@PathVariable Integer id, Model model) {
        Participante p = participante.findById(id).get();
        if (getUsuario() instanceof Coordinador
                && (p.getIdCoordinador() == getUsuario().getId() || p.getEstadoCuenta() == Estado.BAJA)) {
            model.addAttribute("user", getUsuario());
            model.addAttribute("participante", p);
            List<FaseValoracion> formularios = fasevaloracion.faseValoracion(p);
            AlimentacionVal alimentacionval = null;
            for (int i = 0; i < formularios.size(); i++) {
                if (formularios.get(i) instanceof AlimentacionVal) {
                    alimentacionval = (AlimentacionVal) formularios.get(i);
                }
            }

            model.addAttribute("alimentacionval", alimentacionval);
            return "alimentacionval";
        } else
            return "redirect:/";
    }

    @PostMapping("/process_alimentacionval/{id}")
    public String processalimentacionval(@PathVariable("id") Integer id,
            @ModelAttribute AlimentacionVal alimentacionval) {
        Participante p = participante.findById(id).get();
        if (getUsuario() instanceof Coordinador
                && (p.getIdCoordinador() == getUsuario().getId() || p.getEstadoCuenta() == Estado.BAJA)) {
            fasevaloracion.updateAlimentacionVal(alimentacionval, p);

            return "redirect:/valoracion/{id}";
        } else
            return "redirect:/";
    }

    @GetMapping("/actfisica/{id}")
    public String actfisica(@PathVariable Integer id, Model model) {
        Participante p = participante.findById(id).get();
        if (getUsuario() instanceof Coordinador
                && (p.getIdCoordinador() == getUsuario().getId() || p.getEstadoCuenta() == Estado.BAJA)) {
            model.addAttribute("user", getUsuario());
            model.addAttribute("participante", p);
            List<FaseValoracion> formularios = fasevaloracion.faseValoracion(p);
            ActividadFisica actfisica = null;
            for (int i = 0; i < formularios.size(); i++) {
                if (formularios.get(i) instanceof ActividadFisica) {
                    actfisica = (ActividadFisica) formularios.get(i);
                }
            }

            model.addAttribute("actfisica", actfisica);
            return "actfisica";
        } else
            return "redirect:/";
    }

    @PostMapping("/process_actfisica/{id}")
    public String processactfisica(@PathVariable("id") Integer id, @ModelAttribute ActividadFisica actfisica) {
        Participante p = participante.findById(id).get();
        if (getUsuario() instanceof Coordinador
                && (p.getIdCoordinador() == getUsuario().getId() || p.getEstadoCuenta() == Estado.BAJA)) {
            fasevaloracion.updateActividadFisica(actfisica, p);

            return "redirect:/valoracion/{id}";
        } else
            return "redirect:/";
    }

    @GetMapping("/activarcuenta/{id}")
    public String processActCuenta(@PathVariable("id") Integer id) {
        if (getUsuario() instanceof Coordinador) {
            Participante p = participante.findById(id).get();
            List<FaseValoracion> formularios = fasevaloracion.faseValoracion(p);
            Findrisc findrisc = null;
            Exploracion exploracion = null;
            for (int i = 0; i < formularios.size(); i++) {
                if (formularios.get(i) instanceof Findrisc) {
                    findrisc = (Findrisc) formularios.get(i);
                }
                if (formularios.get(i) instanceof Exploracion) {
                    exploracion = (Exploracion) formularios.get(i);
                }
            }
            fasevaloracion.activarcuenta(exploracion, findrisc, id, getUsuario().getId());
            // crear las sesiones del participante
            Sesion sesion1 = ses.buscarSesion(p, 1);
            if (sesion1 == null) { // si no tiene la sesion1 creada
                Sesion s;
                for (int i = 1; i <= 10; i++) {
                    s = new Sesion(0, i, p, EstadoSesion.ENCURSO, "", Asistencia.NO, 0, 0);
                    ses.guardar(s);
                }
            }

            if(!f.existe(p)){
                f.crearFichas(p);
            }

            return "redirect:/listado";
        } else
            return "redirect:/";
    }

    @GetMapping("/eliminarcuenta/{id}")
    public String processElimCuenta(@PathVariable("id") Integer id) {
        if (getUsuario() instanceof Coordinador || getUsuario().getId() == id) {
            Participante p = participante.findById(id).get();
            materialS.deleteByParticipante(p);
            ses.deleteByParticipante(p);
            obj.deleteByParticipante(p);
            pro.deleteByParticipante(p);
            fasevaloracion.eliminarcuenta(p);
            p.getGrupo().setNumParticipantes(p.getGrupo().getNumParticipantes() - 1);
            grupoS.update(p.getGrupo());
            if(getUsuario() == null) return "redirect:/login";

        }
        return "redirect:/";
    }

    public Usuario getUsuario() {
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

    // PROGRESO:

    @GetMapping("/progreso")
    public String progreso(Model model) {

        model.addAttribute("user", getUsuario());
        Participante p = participante.findById(getUsuario().getId()).get();

        if (p.getEstadoCuenta().equals(Estado.ALTA)) {
            model.addAttribute("participante", p);

            List<Progreso> peso = pro.datos(p, TipoProgreso.PESO);
            model.addAttribute("peso", peso);

            List<Progreso> perimetro = pro.datos(p, TipoProgreso.PERIMETRO);
            model.addAttribute("perimetro", perimetro);

            List<Sesion> sesiones = ses.sesiones(p);
            model.addAttribute("sesiones", sesiones);

            // CALCULAR IMC
            // Coger formulario de exploración:
            List<FaseValoracion> formularios = fasevaloracion.faseValoracion(p);
            Exploracion exploracion = null;
            for (int i = 0; i < formularios.size(); i++) {
                if (formularios.get(i) instanceof Exploracion) {
                    exploracion = (Exploracion) formularios.get(i);
                }
            }

            // Altura:
            Double altura = exploracion.getTalla().doubleValue();
            altura = altura / 100;

            // Buscar último registro de peso
            Progreso progrPes = pro.pesoAntiguo(p, TipoProgreso.PESO);
            Double ultPeso = null;

            if (progrPes != null) {
                ultPeso = progrPes.getDato().doubleValue();
            }

            else {
                ultPeso = exploracion.getPeso().doubleValue();
            }

            // Calcular IMC
            Double imc = ultPeso / (altura * altura);
            DecimalFormat imc2 = new DecimalFormat("#.00");

            model.addAttribute("imc", imc2.format(imc));

            model.addAttribute("progreso", new Progreso());
            return "progreso";
        }

        else
            return "acceso";
    }

    @PostMapping("/process_peso")
    public String process_peso(Progreso progreso, Model model) {
        Participante p = participante.findById(getUsuario().getId()).get();
        progreso.setParticipante(p);
        progreso.setTipo(TipoProgreso.PESO);

        // Coger formulario de exploración:
        List<FaseValoracion> formularios = fasevaloracion.faseValoracion(p);
        Exploracion exploracion = null;
        for (int i = 0; i < formularios.size(); i++) {
            if (formularios.get(i) instanceof Exploracion) {
                exploracion = (Exploracion) formularios.get(i);
            }
        }

        Progreso primerPeso = pro.primerPeso(p, TipoProgreso.PESO);
        Double pe = null;

        if (primerPeso != null) { // aunque realmente el primero siempre será el de exploración yo creo
            pe = primerPeso.getDato().doubleValue();
        }

        else {
            pe = exploracion.getPeso().doubleValue();
        }

        Double pesoPerdido = null;

        // positivo -> ha ganado, negativo -> ha perdido
        if (progreso.getDato() - pe > 0)
            pesoPerdido = 0.0;
        else
            pesoPerdido = progreso.getDato() - pe;

        p.setPerdidaDePeso(pesoPerdido);
        participante.update(p.getEdad(), p.getSexo(), p.getFotoParticipante(), p.getGrupo(), p.getAsistencia(),
                p.getIdCoordinador(), pesoPerdido, p.getSesionesCompletas(), p.getId());

        pro.guardar(progreso);

        return "redirect:/progreso";
    }

    @PostMapping("/process_perimetro")
    public String process_perimetro(Progreso progreso, Model model) {
        Participante p = participante.findById(getUsuario().getId()).get();
        progreso.setParticipante(p);
        progreso.setTipo(TipoProgreso.PERIMETRO);

        pro.guardar(progreso);

        return "redirect:/progreso";
    }

    @GetMapping("/objetivos")
    public String objetivos(Model model) {
        Participante p = participante.findById(getUsuario().getId()).get();
        List<Objetivo> listaObjetivos = obj.listaObjetivos(p);
        model.addAttribute("listaObjetivos", listaObjetivos);
        model.addAttribute("user", getUsuario());

        return "objetivos";
    }

    @GetMapping("/objetivos/nuevo")
    public String mostrarFormularioDeNuevoObjetivo(Model model) {
        if (getUsuario() instanceof Participante) {
            model.addAttribute("objetivo", new Objetivo());
            model.addAttribute("user", getUsuario());
            return "nuevo_objetivo";
        } else
            return "redirect:/";
    }

    @PostMapping("/objetivos/guardar")
    public String guardarObjetivo(Objetivo objetivo) {
        Participante p = participante.findById(getUsuario().getId()).get();
        objetivo.setParticipante(p);
        if (getUsuario().getId() == objetivo.getParticipante().getId()) {
            objetivo.setParticipante(p);
            obj.guardar(objetivo);
            return "redirect:/objetivos";
        } else
            return "redirect:/";
    }

    @RequestMapping("/objetivos/eliminar/{id}")
    public String eliminarObjetivo(@PathVariable(name = "id") Integer id) {
        Objetivo o = obj.getObjetivo(id);
        if (getUsuario() instanceof Participante && getUsuario().getId() == o.getParticipante().getId()) {
            obj.borrar(id);
            return "redirect:/objetivos";
        } else
            return "redirect:/";
    }

    /*
     * @GetMapping("/objetivos/editar/{id}")
     * public ModelAndView mostrarFormularioDeEditarObjetivo(@PathVariable(name =
     * "id") Integer id){
     * ModelAndView modelo = new ModelAndView("editar_objetivo");
     * 
     * Objetivo o = obj.getObjetivo(id);
     * 
     * modelo.addObject("objetivo", o);
     * modelo.addObject("user", getUsuario());
     * 
     * return modelo;
     * }
     */

    @GetMapping("/objetivos/editar/{id}")
    public String editarObjetivo(@PathVariable("id") Integer id, Model model) {
        Objetivo o = obj.getObjetivo(id);
        if (getUsuario() instanceof Participante && getUsuario().getId() == o.getParticipante().getId()) {
            model.addAttribute("user", getUsuario());
            model.addAttribute("objetivo", o);
            return "editar_objetivo";
        } else
            return "redirect:/";
    }

    @PostMapping("/objetivos/guardar/{id}")
    public String process_editarObjetivo(@PathVariable("id") Integer id, @ModelAttribute Objetivo objetivo) {
        if (getUsuario().getId() == objetivo.getParticipante().getId()) {
            Participante p = participante.findById(getUsuario().getId()).get();
            objetivo.setParticipante(p);
            obj.guardar(objetivo);
            return "redirect:/objetivos";
        } else
            return "redirect:/";
    }

    // MATERIALES:
    @GetMapping("/eliminarMaterial/{id}")
    public String processElimMaterial(@PathVariable("id") Integer id) {
        Materiales material = materialS.getMaterial(id);
        if (getUsuario() instanceof Coordinador) {
            materialS.eliminarMaterial(id);
        } else if (getUsuario().getId() == material.getParticipante().getId()) {
            materialS.eliminarMaterial(id);
        }

        Integer idP = material.getParticipante().getId();
        return "redirect:/materiales/" + idP;
    }

    // DIAPOSITIVAS
    // Descargar diapositivas
    @GetMapping("/diapos/descargar/{id}")
    public ResponseEntity<byte[]> descargar(@PathVariable Integer id) {
        // el id es el numero de la sesion

        try {
            String archivo = "Taller online sesion " + id + " (nuevo formato).pdf";
            Path rutaArchivo = Paths.get("src//main//resources//static/diapositivas/" + archivo);
            byte[] bytesArc = Files.readAllBytes(rutaArchivo);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); // establecer tipo de contenido que se descarga
            headers.setContentLength(bytesArc.length);

            headers.setContentDisposition(
                    ContentDisposition.attachment().filename(archivo).build());
            ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytesArc, headers, HttpStatus.OK);

            return response;

        } catch (Exception e) {
            String mensaje = "Ha ocurrido un error: " + e.getMessage();
            JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje.getBytes());
        }
    }

    // ACTIVIDADES
    @GetMapping("/actividades")
    public String actividades(Model model) {
        Usuario user = getUsuario();
        model.addAttribute("user", user);

        List<Actividad> listado = act.listado();
        model.addAttribute("listado", listado);

        if (user instanceof Coordinador){
            return "actividadesCoor";
        }

        else if (user instanceof Participante)
            return "actividades";
        else
            return "redirect:/";
    }

    //CUADERNO
    @GetMapping("/cuaderno")
    public String cuaderno(Model model) {
        Usuario user = getUsuario();
        model.addAttribute("user", user);
        if (user instanceof Participante && user.getEstadoCuenta().equals(Estado.ALTA))
            return "cuaderno";
        else
            return "redirect:/";
    }

    //ALIMENTACIÓN
    @GetMapping("/alimentacion")
    public String alimentacion(Model model) {
        Usuario user = getUsuario();
        model.addAttribute("user", user);
        if (user instanceof Participante && user.getEstadoCuenta().equals(Estado.ALTA))
            return "alimentacion";
        else
            return "redirect:/";
    }

    //FICHAS
    @GetMapping("/fichas")
    public String fichas(Model model) {

        Usuario u = getUsuario();
        model.addAttribute("user", getUsuario());

        if (u instanceof Participante && u.getEstadoCuenta().equals(Estado.ALTA)) {
            return "fichas";
        } else
            return "acceso";

    }

    @GetMapping("/fichaEleccion")
    public String fichaEleccion(Model model) {

        Usuario u = getUsuario();
        model.addAttribute("user", getUsuario());

        if (u instanceof Participante && u.getEstadoCuenta().equals(Estado.ALTA)) {
            return "fichaEleccion";
        } else
            return "acceso";

    }

    @GetMapping("/fichaEleccion/{id}")
    public String decisiones(@PathVariable("id") Integer id, Model model) {
        Usuario u = getUsuario();
        model.addAttribute("user", getUsuario());

        if (u instanceof Participante && u.getEstadoCuenta().equals(Estado.ALTA)) {
            FichaEleccion fichaEleccion = f.getFichaEleccion(participante.findById(u.getId()).get(), id);
            model.addAttribute("ficha", fichaEleccion);
            return "fichaEleccionConcreta";
        } else
            return "acceso";
    }

    @PostMapping("/process_fichae/{id}")
    public String processdecisiones(@PathVariable("id") Integer id, @ModelAttribute FichaEleccion ficha) {
        Usuario u = getUsuario();

        if (u instanceof Participante && u.getEstadoCuenta().equals(Estado.ALTA)) {
            f.updateFichaEleccion(ficha);
            return "redirect:/fichas";
        } else
            return "acceso";
    }
}
