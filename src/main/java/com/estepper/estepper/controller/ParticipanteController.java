package com.estepper.estepper.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.nio.file.Files;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PathVariable;

import com.estepper.estepper.model.entity.FaseValoracion;
import com.estepper.estepper.model.entity.Exploracion;
import com.estepper.estepper.model.entity.Findrisc;
import com.estepper.estepper.model.entity.Invitacion;
import com.estepper.estepper.model.entity.Materiales;
import com.estepper.estepper.model.entity.Notificacion;
import com.estepper.estepper.model.entity.Objetivo;
import com.estepper.estepper.model.entity.ObjetivoAgua;
import com.estepper.estepper.model.entity.ObjetivoDescanso;
import com.estepper.estepper.model.entity.ObjetivoEjercicio;
import com.estepper.estepper.model.entity.ObjetivoEstadoAnimo;
import com.estepper.estepper.model.entity.Progreso;
import com.estepper.estepper.model.entity.Receta;
import com.estepper.estepper.model.entity.FichaEleccion;
import com.estepper.estepper.model.entity.FichaObjetivo;
import com.estepper.estepper.model.entity.FichaTaller;
import com.estepper.estepper.model.entity.Usuario;
import com.estepper.estepper.model.entity.Sesion;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Coordinador;
import com.estepper.estepper.model.entity.Clasificacion;
import com.estepper.estepper.model.entity.Antecedentes;
import com.estepper.estepper.model.entity.Actividad;
import com.estepper.estepper.model.entity.ActividadFisica;
import com.estepper.estepper.model.entity.Alimentacion;
import com.estepper.estepper.model.entity.AlimentacionVal;
import com.estepper.estepper.model.entity.AlimentoIntercambio;
import com.estepper.estepper.model.entity.AlimentosConsumidos;
import com.estepper.estepper.model.enums.Asistencia;
import com.estepper.estepper.model.enums.Ejercicio;
import com.estepper.estepper.model.enums.Estado;
import com.estepper.estepper.model.enums.EstadoInvitacion;
import com.estepper.estepper.model.enums.EstadoNotificacion;
import com.estepper.estepper.model.enums.EstadoObjetivo;
import com.estepper.estepper.model.enums.EstadoSesion;
import com.estepper.estepper.model.enums.TipoProgreso;

import com.estepper.estepper.service.ActividadService;
import com.estepper.estepper.service.AlimentacionService;
import com.estepper.estepper.service.AlimentoIntercambioService;
import com.estepper.estepper.service.FaseValoracionService;
import com.estepper.estepper.service.FichaService;
import com.estepper.estepper.service.MaterialService;
import com.estepper.estepper.service.MensajeService;
import com.estepper.estepper.service.NotificacionService;
import com.estepper.estepper.service.ObjetivoService;
import com.estepper.estepper.service.ParticipanteService;
import com.estepper.estepper.service.SesionService;
import com.estepper.estepper.service.UsuarioService;
import com.estepper.estepper.service.ProgresoService;
import com.estepper.estepper.service.PythonService;
import com.estepper.estepper.service.GrupoService;
import com.estepper.estepper.service.InvitacionService;

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

    @Autowired
    private AlimentacionService alimentacion;

    @Autowired
    private MensajeService mensajeS;

    @Autowired
    @Qualifier("PythonServiceImpl")
    private PythonService service;

    @Autowired
    private InvitacionService invi;

    @Autowired
    private NotificacionService noti;

    @Autowired
    private AlimentoIntercambioService inter;

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
            Participante part = participante.findById(u.getId()).get();
            // buscar notificaciones
            List<Notificacion> notificaciones = noti.notificaciones(part);
            model.addAttribute("notificaciones", notificaciones);

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
                model.addAttribute("lasesion", sesion);

                model.addAttribute("participante", p); // coger participante

                // buscar notificaciones
                List<Notificacion> notificaciones = noti.notificaciones(p);
                model.addAttribute("notificaciones", notificaciones);

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

            // actualizar asistencia media y sesiones completas participante
            List<Sesion> lista = ses.sesiones(p);
            Integer asistencia = 0;
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getAsistencia().equals(Asistencia.SI))
                    asistencia++;
            }
            participante.update(p.getEdad(), p.getSexo(), p.getFotoUsuario(), p.getGrupo(), asistencia * 10,
                    p.getCoordinador(), p.getPerdidaDePeso(), p.getSesionesCompletas(),
                    p.getPerdidacmcintura(),
                    p.getId());
            return "redirect:/sesiones";
        } else
            return "acceso";
    }

    @PostMapping("process_sesionTerm/{num}")
    public String actualizarTerm(@PathVariable("num") Integer num, @ModelAttribute Sesion lasesion) {
        Participante p = participante.getParticipante(getUsuario().getId());
        if (p.getEstadoCuenta().equals(Estado.ALTA)) {
            Sesion orig = ses.buscarSesion(p, num);

            Sesion actualizada = new Sesion(orig.getId(), orig.getNumSesion(), orig.getParticipante(),
                    EstadoSesion.COMPLETA,
                    orig.getObservaciones(), orig.getAsistencia(), lasesion.getCmsPerdidos(),
                    lasesion.getPesoPerdido());

            ses.guardar(actualizada);

            // actualizar asistencia media y sesiones completas participante
            List<Sesion> lista = ses.sesiones(p);
            Integer completas = 0;
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getEstado().equals(EstadoSesion.COMPLETA))
                    completas++;
            }
            participante.update(p.getEdad(), p.getSexo(), p.getFotoUsuario(), p.getGrupo(), p.getAsistencia(),
                    p.getCoordinador(), p.getPerdidaDePeso(), completas,
                    p.getPerdidacmcintura(), p.getId());
            return "redirect:/sesiones";
        } else
            return "acceso";
    }

    @GetMapping("/exploracion/{id}")
    public String exploracion(@PathVariable Integer id, Model model) {
        Usuario user = getUsuario();

        if (user instanceof Coordinador) { // si el usuario es un coordinador
            Participante p = participante.findById(id).get();

            if ((p.getCoordinador() != null && p.getCoordinador().getId() == user.getId())
                    || (p.getEstadoCuenta().equals(Estado.BAJA))) { // si el participante está asignado al coordinador o
                                                                    // el participante está dado de baja
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

        } else
            return "redirect:/";

    }

    @PostMapping("/process_exploracion/{id}")
    public String processExploracion(@PathVariable("id") Integer id, @ModelAttribute Exploracion exploracion) {
        Participante p = participante.findById(id).get();
        if (getUsuario() instanceof Coordinador
                && ((p.getCoordinador() != null && p.getCoordinador().getId() == getUsuario().getId())
                        || p.getEstadoCuenta() == Estado.BAJA)) {
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

        Usuario user = getUsuario();

        if (user instanceof Coordinador) { // si el usuario es un coordinador
            Participante p = participante.findById(id).get();

            if ((p.getCoordinador() != null && p.getCoordinador().getId() == user.getId())
                    || (p.getEstadoCuenta().equals(Estado.BAJA))) { // si el participante está asignado al coordinador o
                                                                    // el participante está dado de baja

                List<FaseValoracion> formularios = fasevaloracion.faseValoracion(p);

                if (formularios.size() > 2)
                    model.addAttribute("formularios", formularios);
                model.addAttribute("participante", p);
                model.addAttribute("usuario", usuario.findById(id).get());
                model.addAttribute("user", user);
                model.addAttribute("idparticipante", id);

                return "valoracion";
            } else
                return "redirect:/";
        }

        else
            return "redirect:/";
    }

    @GetMapping("/expediente/{id}")
    public String expediente(@PathVariable("id") Integer id, Model model) {
        Usuario user = getUsuario();

        if (user instanceof Coordinador) { // si el usuario es un coordinador
            Participante p = participante.findById(id).get();

            if ((p.getCoordinador() != null && p.getCoordinador().getId() == user.getId())
                    || (p.getEstadoCuenta().equals(Estado.BAJA))) { // si el participante está asignado al coordinador o
                                                                    // el participante está dado de baja

                model.addAttribute("participante", p);
                model.addAttribute("user", user);

                return "expediente";
            }

            else
                return "redirect:/";
        }

        else
            return "redirect:/";
    }

    @GetMapping("/findrisc/{id}")
    public String findrisc(@PathVariable Integer id, Model model) {

        Participante p = participante.findById(id).get();
        if (getUsuario() instanceof Coordinador
                && ((p.getCoordinador() != null && p.getCoordinador().getId() == getUsuario().getId())
                        || p.getEstadoCuenta() == Estado.BAJA)) {
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
                && ((p.getCoordinador() != null && p.getCoordinador().getId() == getUsuario().getId())
                        || p.getEstadoCuenta() == Estado.BAJA)) {
            fasevaloracion.updateFindrisc(p, findrisc.getPuntosedad(), findrisc.getPuntosimc(),
                    findrisc.getPuntoscmcintura(),
                    findrisc.getPtosactfisica(),
                    findrisc.getPtosfrecfruta(), findrisc.getPtosmedicacion(), findrisc.getPtosglucosa(),
                    findrisc.getPtosdiabetes(),
                    findrisc.getPuntuacion(),
                    findrisc.getEscalarriesgo());

            Exploracion exploracion = null;
            List<FaseValoracion> formularios = fasevaloracion.faseValoracion(p);
            for (int i = 0; i < formularios.size(); i++) {
                if (formularios.get(i) instanceof Exploracion) {
                    exploracion = (Exploracion) formularios.get(i);
                }
            }
            if (exploracion != null) {
                if ((exploracion.getEdad() >= 35) & (findrisc.getPuntuacion() >= 15)) {
                    fasevaloracion.crearFormulariosNuevos(p);
                }
            }

            return "redirect:/valoracion/{id}";
        } else
            return "redirect:/";
    }

    @GetMapping("/clasificacion/{id}")
    public String clasificacion(@PathVariable Integer id, Model model) {
        Participante p = participante.findById(id).get();
        if (getUsuario() instanceof Coordinador
                && ((p.getCoordinador() != null && p.getCoordinador().getId() == getUsuario().getId())
                        || p.getEstadoCuenta() == Estado.BAJA)) {
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
                && ((p.getCoordinador() != null && p.getCoordinador().getId() == getUsuario().getId())
                        || p.getEstadoCuenta() == Estado.BAJA)) {
            fasevaloracion.updateClasificacion(clasificacion, p);

            return "redirect:/valoracion/{id}";
        } else
            return "redirect:/";
    }

    @GetMapping("/antecedentes/{id}")
    public String antecedentes(@PathVariable Integer id, Model model) {
        Participante p = participante.findById(id).get();
        if (getUsuario() instanceof Coordinador
                && ((p.getCoordinador() != null && p.getCoordinador().getId() == getUsuario().getId())
                        || p.getEstadoCuenta() == Estado.BAJA)) {
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
                && ((p.getCoordinador() != null && p.getCoordinador().getId() == getUsuario().getId())
                        || p.getEstadoCuenta() == Estado.BAJA)) {
            fasevaloracion.updateAntecedentes(antecedentes, p);

            return "redirect:/valoracion/{id}";
        } else
            return "redirect:/";
    }

    @GetMapping("/alimentacionval/{id}")
    public String alimentacionval(@PathVariable Integer id, Model model) {
        Participante p = participante.findById(id).get();
        if (getUsuario() instanceof Coordinador
                && ((p.getCoordinador() != null && p.getCoordinador().getId() == getUsuario().getId())
                        || p.getEstadoCuenta() == Estado.BAJA)) {
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
                && ((p.getCoordinador() != null && p.getCoordinador().getId() == getUsuario().getId())
                        || p.getEstadoCuenta() == Estado.BAJA)) {
            fasevaloracion.updateAlimentacionVal(alimentacionval, p);

            return "redirect:/valoracion/{id}";
        } else
            return "redirect:/";
    }

    @GetMapping("/actfisica/{id}")
    public String actfisica(@PathVariable Integer id, Model model) {
        Participante p = participante.findById(id).get();
        if (getUsuario() instanceof Coordinador
                && ((p.getCoordinador() != null && p.getCoordinador().getId() == getUsuario().getId())
                        || p.getEstadoCuenta() == Estado.BAJA)) {
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
                && ((p.getCoordinador() != null && p.getCoordinador().getId() == getUsuario().getId())
                        || p.getEstadoCuenta() == Estado.BAJA)) {
            fasevaloracion.updateActividadFisica(actfisica, p);

            return "redirect:/valoracion/{id}";
        } else
            return "redirect:/";
    }

    @GetMapping("/activarcuenta/{id}")
    public String processActCuenta(@PathVariable("id") Integer id, Model model) {
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

            if (!f.existe(p)) {
                f.crearFichas(p);
            }

            // notificación cuenta activada
            Notificacion notificacion = new Notificacion(0, p,
                    "¡Bienvenido, has sido dado de alta en Estepper!", LocalDateTime.now(),
                    EstadoNotificacion.PENDIENTE, "/");
            noti.guardar(notificacion);

            return "redirect:/listado";
        } else
            return "redirect:/";
    }

    @GetMapping("/eliminarcuenta/{id}")
    public String processElimCuenta(@PathVariable("id") Integer id, Model model) {
        if (getUsuario() instanceof Coordinador || getUsuario().getId() == id) {
            Participante p = participante.findById(id).get();
            materialS.deleteByParticipante(p);
            ses.deleteByParticipante(p);
            obj.deleteByParticipante(p);
            pro.deleteByParticipante(p);
            alimentacion.deleteByParticipante(p);
            f.deleteByParticipante(p);
            mensajeS.deleteByParticipante(p);
            invi.eliminarPorParticipante(p); // invitacionesPart
            // eliminar asistencia y aumentar plazas
            List<Actividad> actividades = act.asistenciaParticipante(id);
            for (Actividad actividad : actividades) {
                actividad.setNumParticipantes(actividad.getNumParticipantes() - 1);
                actividad.setPlazas(actividad.getPlazas() + 1);
                actividad.getParticipantes().remove(p);
                act.guardar(actividad);
            }

            // eliminar notificaciones
            List<Notificacion> notificaciones = noti.notificaciones(p);
            for (Notificacion notif : notificaciones) {
                noti.eliminar(notif);
            }

            if (p.getGrupo() != null) {
                p.getGrupo().setNumParticipantes(p.getGrupo().getNumParticipantes() - 1);
                grupoS.update(p.getGrupo());
            }

            if (getUsuario() == null)
                return "redirect:/login";
            fasevaloracion.eliminarcuenta(p);

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

    @GetMapping("/progreso/{id}")
    public String progreso(Model model, @PathVariable Integer id) {

        Usuario user = getUsuario();
        model.addAttribute("user", user);

        Participante p = participante.findById(id).orElse(null);

        if (p != null && p.getEstadoCuenta().equals(Estado.ALTA)) { // si el id proporcionado pertenece a un
                                                                    // participante
            if (user instanceof Participante) {

                if (id.equals(user.getId())) { // si el id es el mismo que el logueado
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
                        if (exploracion != null) {
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
                        }

                        model.addAttribute("progreso", new Progreso());

                        // buscar notificaciones
                        List<Notificacion> notificaciones = noti.notificaciones(p);
                        model.addAttribute("notificaciones", notificaciones);

                        return "progreso";
                    }

                    else // si no está dado de alta no puede acceder a esta funcionalidad
                        return "acceso";
                }

                else // si el id no es el mismo que el participante logueado no puede acceder al
                     // progreso de otros participantes
                    return "redirect:/";
            }

            else if (user instanceof Coordinador) { // si el usuario es un coordinador

                if (p.getCoordinador().getId().equals(user.getId())) { // si el progreso que quiere ver es de un
                                                                       // participante
                    // que le pertenece

                    model.addAttribute("participante", p);
                    List<Progreso> peso = pro.datos(p, TipoProgreso.PESO);
                    model.addAttribute("peso", peso);
                    model.addAttribute("idPart", id);

                    return "progresoCoor";
                }

                else
                    return "redirect:/";

            }

            else
                return "redirect:/";

        } else if (p != null && p.getEstadoCuenta().equals(Estado.BAJA) && p instanceof Participante) {
            return "acceso";
        } else {
            return "redirect:/"; // si el id proporcionado no pertenece a un participante no existe progreso
        }
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

        if (exploracion != null) {
            Double pe = exploracion.getPeso().doubleValue();

            Double pesoPerdido = null;

            // positivo -> ha ganado, negativo -> ha perdido
            if (progreso.getDato() - pe > 0)
                pesoPerdido = 0.0;
            else
                pesoPerdido = progreso.getDato() - pe;

            p.setPerdidaDePeso(pesoPerdido);
            participante.update(p.getEdad(), p.getSexo(), p.getFotoUsuario(), p.getGrupo(), p.getAsistencia(),
                    p.getCoordinador(), pesoPerdido, p.getSesionesCompletas(),
                    p.getPerdidacmcintura(), p.getId());

            pro.guardar(progreso);
        }
        return "redirect:/progreso/" + getUsuario().getId();
    }

    @PostMapping("/process_perimetro")
    public String process_perimetro(Progreso progreso, Model model) {
        Participante p = participante.findById(getUsuario().getId()).get();
        progreso.setParticipante(p);
        progreso.setTipo(TipoProgreso.PERIMETRO);

        // Coger formulario de exploración:
        List<FaseValoracion> formularios = fasevaloracion.faseValoracion(p);
        Exploracion exploracion = null;
        for (int i = 0; i < formularios.size(); i++) {
            if (formularios.get(i) instanceof Exploracion) {
                exploracion = (Exploracion) formularios.get(i);
            }
        }
        if (exploracion != null) {
            Double pe = exploracion.getCmcintura().doubleValue();

            Double cmCinturaPerdido = null;

            // positivo -> ha ganado, negativo -> ha perdido
            if (progreso.getDato() - pe > 0)
                cmCinturaPerdido = 0.0;
            else
                cmCinturaPerdido = progreso.getDato() - pe;

            p.setPerdidacmcintura(cmCinturaPerdido);
            participante.update(p.getEdad(), p.getSexo(), p.getFotoUsuario(), p.getGrupo(), p.getAsistencia(),
                    p.getCoordinador(), p.getPerdidaDePeso(), p.getSesionesCompletas(),
                    cmCinturaPerdido, p.getId());

            pro.guardar(progreso);
        }

        return "redirect:/progreso/" + getUsuario().getId();
    }

    @GetMapping("/objetivos")
    public String objetivos(Model model) {

        if (getUsuario() instanceof Participante) {
            Participante p = participante.findById(getUsuario().getId()).get();
            List<Objetivo> listaObjetivos = obj.listaObjetivos(p);
            Date fechaActual1 = new Date();

            model.addAttribute("listaObjetivos", listaObjetivos);
            model.addAttribute("user", getUsuario());
            model.addAttribute("objetivo", new Objetivo());
            model.addAttribute("fechaActual1", fechaActual1);

            LocalDate fechaActual = LocalDate.now();
            int mesActual = fechaActual.getMonthValue();
            int anioActual = fechaActual.getYear();
            Integer mesActualInteger = mesActual;
            Integer anioActualInteger = anioActual;

            List<Objetivo> listaObjetivosPorMes = obj.listaObjetivosPorMes(p, mesActualInteger, anioActualInteger);
            model.addAttribute("listaObjetivosPorMes", listaObjetivosPorMes);

            ObjetivoAgua objetivoAgua = obj.findByFechaAndParticipanteAgua(new Date(), p);

            if (objetivoAgua == null) {
                objetivoAgua = new ObjetivoAgua();
            }

            model.addAttribute("objetivoAgua", objetivoAgua);

            List<ObjetivoEjercicio> listaEjercicioParticipante = obj.listaEjercicio(new Date(), p);
            model.addAttribute("listaEjercicioParticipante", listaEjercicioParticipante);

            ObjetivoDescanso objetivoDescanso = obj.findByFechaAndParticipanteDescanso(new Date(), p);

            if (objetivoDescanso == null) {
                objetivoDescanso = new ObjetivoDescanso();
            }

            model.addAttribute("objetivoDescanso", objetivoDescanso);

            ObjetivoEstadoAnimo objetivoEstadoAnimo = obj.findByFechaAndParticipanteEstadoAnimo(new Date(), p);

            if (objetivoEstadoAnimo == null) {
                objetivoEstadoAnimo = new ObjetivoEstadoAnimo();
            }

            model.addAttribute("objetivoEstadoAnimo", objetivoEstadoAnimo);

            // buscar notificaciones
            List<Notificacion> notificaciones = noti.notificaciones(p);
            model.addAttribute("notificaciones", notificaciones);

            return "objetivos";
        } else {
            return "redirect:/";
        }

    }

    @GetMapping(value = "/objetivos/listaObjetivosPorMes/{mes}/{anio}", produces = "application/json")
    @ResponseBody
    public List<Objetivo> listaObjetivos(Model model, @PathVariable("mes") Integer mes,
            @PathVariable("anio") Integer anio) {

        if (getUsuario() instanceof Participante) {
            Participante p = participante.findById(getUsuario().getId()).get();

            List<Objetivo> listaObjetivosPorMes = obj.listaObjetivosPorMes(p, mes, anio);
            return listaObjetivosPorMes;
        } else {
            return null;
        }

    }

    @PostMapping("/objetivos/guardar")
    public String guardarObjetivo(Objetivo objetivo) {

        if (getUsuario() instanceof Participante) {
            Participante p = participante.findById(getUsuario().getId()).get();
            objetivo.setParticipante(p);
            if (getUsuario().getId() == objetivo.getParticipante().getId()) {
                objetivo.setParticipante(p);
                obj.guardar(objetivo);
                return "redirect:/objetivos";
            } else
                return "redirect:/";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping("/objetivos/eliminar/{id}")
    public String eliminarObjetivo(@PathVariable(name = "id") Integer id) {

        if (getUsuario() instanceof Participante) {
            Objetivo o = obj.getObjetivo(id);
            if (getUsuario() instanceof Participante && getUsuario().getId() == o.getParticipante().getId()) {
                obj.borrar(id);
                return "redirect:/objetivos";
            } else
                return "redirect:/";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/objetivos/editar/{id}")
    public String editarObjetivo(@PathVariable("id") Integer id, Model model) {

        if (getUsuario() instanceof Participante) {
            Objetivo o = obj.getObjetivo(id);

            if (o != null) {

                if (getUsuario() instanceof Participante && getUsuario().getId() == o.getParticipante().getId()) {
                    model.addAttribute("user", getUsuario());
                    model.addAttribute("objetivo", o);

                    // buscar notificaciones
                    List<Notificacion> notificaciones = noti
                            .notificaciones(participante.getParticipante(getUsuario().getId()));
                    model.addAttribute("notificaciones", notificaciones);

                    return "editar_objetivo";
                } else
                    return "redirect:/";

            } else {
                return "redirect:/objetivos";
            }

        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/objetivos/guardar/{id}")
    public String process_editarObjetivo(@PathVariable("id") Integer id, @ModelAttribute Objetivo objetivo) {

        if (getUsuario() instanceof Participante) {
            Participante p = participante.findById(getUsuario().getId()).get();
            objetivo.setParticipante(p);
            obj.guardar(objetivo);
            return "redirect:/objetivos";
        } else {
            return "redirect:/";
        }

    }

    @PostMapping("/objetivoAgua/guardar/{id}")
    public String process_ObjetivoAgua(@PathVariable("id") Integer id, @ModelAttribute ObjetivoAgua objetivoAgua) {
        if (getUsuario() instanceof Participante) {
            Participante p = participante.findById(getUsuario().getId()).get();
            objetivoAgua.setParticipante(p);
            obj.guardarAgua(objetivoAgua);
            return "redirect:/objetivos";
        } else {
            return "redirect:/";
        }
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

        if (user instanceof Coordinador) { // todas las actividades
            List<Actividad> listado = act.listado();

            model.addAttribute("listado", listado);
            model.addAttribute("actividad", new Actividad());

            return "actividadesCoor";
        }

        else if (user instanceof Participante) {
            if (user.getEstadoCuenta().equals(Estado.ALTA)) {
                // actividades que no se hayan acabado
                List<Actividad> listado = act.actividadesPendientes(LocalDateTime.now());
                model.addAttribute("listado", listado);

                // asistencia confirmada
                List<Actividad> asistencia = act.asistenciaParticipante(user.getId());
                model.addAttribute("asistencia", asistencia);

                // buscar notificaciones
                List<Notificacion> notificaciones = noti.notificaciones(participante.getParticipante(user.getId()));
                model.addAttribute("notificaciones", notificaciones);

                return "actividades";
            } else
                return "acceso";
        } else
            return "redirect:/";
    }

    @GetMapping("/actividad/{id}")
    public String actividad(Model model, @PathVariable Integer id) {

        Usuario user = getUsuario();

        if (user instanceof Participante || user instanceof Coordinador) {
            Actividad acti = act.actividad(id);
            model.addAttribute("user", user);
            model.addAttribute("actividad", acti);

            if (user instanceof Participante) { // si es participante comprobar asistencia confirmada a actividad
                boolean asiste = false;
                Integer asistencia = act.asistencia(acti.getId(), getUsuario().getId());

                if (asistencia > 0)
                    asiste = true;
                model.addAttribute("asistencia", asiste);

                // buscar notificaciones
                List<Notificacion> notificaciones = noti.notificaciones(participante.getParticipante(user.getId()));
                model.addAttribute("notificaciones", notificaciones);
            }

            else { // Coordinador, enviar listado de asistentes
                Set<Participante> asistentes = acti.getParticipantes();
                model.addAttribute("asistentes", asistentes);

            }

            return "actividad";
        } else {
            return "redirect:/";
        }

    }

    // confirmar inscripción
    @GetMapping("/confirmar/{id}")
    public String confirmar(@PathVariable Integer id, Model model) {
        Usuario user = getUsuario();

        if (user instanceof Participante) {

            Actividad acti = act.actividad(id);
            Participante parti = participante.findById(user.getId()).get();

            acti.getParticipantes().add(parti);
            acti.setNumParticipantes(acti.getNumParticipantes() + 1);
            acti.setPlazas(acti.getPlazas() - 1);
            act.guardar(acti);

            // comprobar si las plazas se han quedado a cero
            List<Invitacion> invitaciones = invi.listadoByAct(acti);
            if (acti.getPlazas() == 0) {
                for (Invitacion invitacion : invitaciones) {
                    if (invitacion.getEstado().equals(EstadoInvitacion.PENDIENTE)) {
                        invi.borrar(invitacion);

                        // notificsción invitación a la que ya no hay disponibilidad
                        Notificacion notificacion = new Notificacion(0, invitacion.getParticipante(),
                                "Invitación eliminada a " + acti.getNombre() + "Se han acabado las plazas",
                                LocalDateTime.now(),
                                EstadoNotificacion.PENDIENTE, "/panel_invitaciones");
                        noti.guardar(notificacion);

                    }
                }
            }

            return "redirect:/actividades";
        }

        else
            return "redirect:/";
    }

    // eliminar inscripción
    @GetMapping("/eliminar_inscripcion/{id}")
    public String process_invitacion(@PathVariable(name = "id") Integer id, Model model) {

        Participante p = participante.getParticipante(getUsuario().getId());
        Actividad actividad = act.actividad(id);

        // 1. Eliminar participante de la actividad
        actividad.getParticipantes().remove(p);

        // 2. Eliminar asistente del número de participantes
        actividad.setNumParticipantes(actividad.getNumParticipantes() - 1);

        // 3. Aumentar número de plazas
        actividad.setPlazas(actividad.getPlazas() + 1);

        // 4. Guardar actividad
        act.guardar(actividad);

        return "redirect:/actividades";
    }

    @GetMapping("/confirmar_invitacion/{id}")
    public String confirmarInvitacion(@PathVariable Integer id, Model model) {
        Usuario user = getUsuario();

        if (user instanceof Participante) {
            Invitacion invitacion = invi.findById(id);

            Actividad acti = invitacion.getActividad();
            Participante parti = invitacion.getParticipante();

            acti.getParticipantes().add(parti);
            acti.setNumParticipantes(acti.getNumParticipantes() + 1);
            acti.setPlazas(acti.getPlazas() - 1);
            act.guardar(acti);

            invitacion.setEstado(EstadoInvitacion.ACEPTADA);
            invi.guardar(invitacion);

            // Comprobar si las plazas se han quedado a cero
            List<Invitacion> invitaciones = invi.listadoByAct(acti);
            if (acti.getPlazas() == 0) {
                for (Invitacion invit : invitaciones) {
                    if (invit.getEstado().equals(EstadoInvitacion.PENDIENTE)) {
                        invi.borrar(invit);

                        // notificsción invitación a la que ya no hay disponibilidad
                        Notificacion notificacion = new Notificacion(0, invit.getParticipante(),
                                "Invitación eliminada a la actividad: " + acti.getNombre() + ". Sin disponibilidad",
                                LocalDateTime.now(),
                                EstadoNotificacion.PENDIENTE, "/panel_invitaciones");
                        noti.guardar(notificacion);

                    }
                }
            }

            return "redirect:/panel_invitaciones";
        }

        else
            return "redirect:/";
    }

    @GetMapping("/rechazar_invitacion/{id}")
    public String rechazarInvitacion(@PathVariable Integer id, Model model) {
        Usuario user = getUsuario();

        if (user instanceof Participante) {

            Invitacion invitacion = invi.findById(id);

            invitacion.setEstado(EstadoInvitacion.RECHAZADA);
            invi.guardar(invitacion);

            return "redirect:/panel_invitaciones";
        }

        else
            return "redirect:/";
    }

    // Vista del panel de invitaciones que recibe el participante donde puede ver
    // las invitaciones pendientes (aceptar o rechazar),
    // invitaciones aceptadas e invitaciones rechazadas
    @GetMapping("/panel_invitaciones")
    public String invitacionesPart(Model model) {
        Usuario user = getUsuario();
        model.addAttribute("user", user);

        if (user instanceof Participante) {

            List<Invitacion> pendientes = invi.invitacionesPartAndEstado(participante.findById(user.getId()).get(),
                    EstadoInvitacion.PENDIENTE);

            model.addAttribute("pendientes", pendientes);

            List<Invitacion> aceptadas = invi.invitacionesPartAndEstado(participante.findById(user.getId()).get(),
                    EstadoInvitacion.ACEPTADA);
            model.addAttribute("aceptadas", aceptadas);

            List<Invitacion> rechazadas = invi.invitacionesPartAndEstado(participante.findById(user.getId()).get(),
                    EstadoInvitacion.RECHAZADA);
            model.addAttribute("rechazadas", rechazadas);

            // buscar notificaciones
            List<Notificacion> notificaciones = noti.notificaciones(participante.getParticipante(user.getId()));
            model.addAttribute("notificaciones", notificaciones);

            return "invitacionesPart";
        }

        else
            return "redirect:/";
    }

    // CUADERNO
    @GetMapping("/cuaderno")
    public String cuaderno(Model model) {
        Usuario user = getUsuario();
        model.addAttribute("user", user);
        if (user instanceof Participante && user.getEstadoCuenta().equals(Estado.ALTA)) {
            // buscar notificaciones
            List<Notificacion> notificaciones = noti.notificaciones(participante.getParticipante(user.getId()));
            model.addAttribute("notificaciones", notificaciones);
            return "cuaderno";
        } else
            return "acceso";
    }

    @GetMapping("/info")
    public String info(Model model) {
        Usuario user = getUsuario();
        model.addAttribute("user", user);

        if (user instanceof Participante && user.getEstadoCuenta().equals(Estado.ALTA)) {
            // buscar notificaciones
            List<Notificacion> notificaciones = noti.notificaciones(participante.getParticipante(user.getId()));
            model.addAttribute("notificaciones", notificaciones);
            return "info";
        } else
            return "acceso";
    }

    // ALIMENTACIÓN
    @GetMapping("/alimentacion")
    public String alimentacion(Model model) {
        Usuario user = getUsuario();
        model.addAttribute("user", user);

        if (user instanceof Participante && user.getEstadoCuenta().equals(Estado.ALTA)) {
            // buscar notificaciones
            List<Notificacion> notificaciones = noti.notificaciones(participante.getParticipante(user.getId()));
            model.addAttribute("notificaciones", notificaciones);

            return "alimentacion";
        } else
            return "acceso";
    }

    // Juego en la funcionalidad de alimentación con el objetivo de que el
    // participante pueda crear dietas personalizadas
    // según la cantidad de calorías que seleccione
    @GetMapping("/juego")
    public String juego(Model model) {
        Usuario user = getUsuario();
        model.addAttribute("user", user);

        if (user instanceof Participante && user.getEstadoCuenta().equals(Estado.ALTA)) {
            // buscar notificaciones
            List<Notificacion> notificaciones = noti.notificaciones(participante.getParticipante(user.getId()));
            model.addAttribute("notificaciones", notificaciones);

            // alimentos intercambio
            List<AlimentoIntercambio> alimentos = inter.alimentos();
            model.addAttribute("listado", alimentos);
            return "juego";
        } else
            return "acceso";
    }

    @GetMapping("/alimentos")
    public String alimentos(Model model) {
        Usuario user = getUsuario();
        model.addAttribute("user", user);

        if (user instanceof Participante && user.getEstadoCuenta().equals(Estado.ALTA)) {
            model.addAttribute("alimentoCon", new AlimentosConsumidos());
            List<AlimentosConsumidos> listal = alimentacion.getAlimentosCon(participante.findById(user.getId()).get());
            List<Float> nutrienteshoy = new ArrayList<Float>(Arrays.asList(0f, 0f, 0f, 0f, 0f)); // Inicializar con
                                                                                                 // ceros
            for (int i = 0; i < listal.size(); i++) {
                nutrienteshoy.set(0, Float.parseFloat(String.format("%.2f", nutrienteshoy.get(0)
                        + (listal.get(i).getAlimento().getFibra_alimentaria() * listal.get(i).getRaciones()))
                        .replace(",", ".")));
                nutrienteshoy.set(1, Float.parseFloat(String.format("%.2f", nutrienteshoy.get(1)
                        + (listal.get(i).getAlimento().getGrasas_saturadas() * listal.get(i).getRaciones()))
                        .replace(",", ".")));
                nutrienteshoy.set(2, Float.parseFloat(String.format("%.2f", nutrienteshoy.get(2)
                        + (listal.get(i).getAlimento().getHidratos_de_carbono() * listal.get(i).getRaciones()))
                        .replace(",", ".")));
                nutrienteshoy.set(3, Float.parseFloat(String.format("%.2f", nutrienteshoy.get(3)
                        + (listal.get(i).getAlimento().getProteinas() * listal.get(i).getRaciones()))
                        .replace(",", ".")));
                nutrienteshoy.set(4, Float.parseFloat(String.format("%.2f", nutrienteshoy.get(4)
                        + (listal.get(i).getAlimento().getSal() * listal.get(i).getRaciones()))
                        .replace(",", ".")));

            }
            model.addAttribute("listaAlimentos", alimentacion.getAlimentos());
            model.addAttribute("nutrientes", nutrienteshoy);
            model.addAttribute("listaAlimentosCon", listal);

            // BORRAR ALIMENTOS DE HACE MÁS DE 1 SEMANA
            alimentacion.borraralconSem(participante.findById(user.getId()).get());

            // buscar notificaciones
            List<Notificacion> notificaciones = noti.notificaciones(participante.getParticipante(user.getId()));
            model.addAttribute("notificaciones", notificaciones);

            return "alimentos";
        } else
            return "acceso";
    }

    @GetMapping("/nutrientes")
    public String nutrientes(Model model) {
        Usuario user = getUsuario();
        model.addAttribute("user", user);

        if (user instanceof Participante && user.getEstadoCuenta().equals(Estado.ALTA)) {
            model.addAttribute("alimentoCon", new AlimentosConsumidos());
            List<AlimentosConsumidos> listal = alimentacion.getAlimentosCon(participante.findById(user.getId()).get());
            List<Float> nutrienteshoy = new ArrayList<Float>(Arrays.asList(0f, 0f, 0f, 0f, 0f)); // Inicializar con
                                                                                                 // ceros
            for (int i = 0; i < listal.size(); i++) {
                nutrienteshoy.set(0, Float.parseFloat(String.format("%.2f", nutrienteshoy.get(0)
                        + (listal.get(i).getAlimento().getFibra_alimentaria() * listal.get(i).getRaciones()))
                        .replace(",", ".")));
                nutrienteshoy.set(1, Float.parseFloat(String.format("%.2f", nutrienteshoy.get(1)
                        + (listal.get(i).getAlimento().getGrasas_saturadas() * listal.get(i).getRaciones()))
                        .replace(",", ".")));
                nutrienteshoy.set(2, Float.parseFloat(String.format("%.2f", nutrienteshoy.get(2)
                        + (listal.get(i).getAlimento().getHidratos_de_carbono() * listal.get(i).getRaciones()))
                        .replace(",", ".")));
                nutrienteshoy.set(3, Float.parseFloat(String.format("%.2f", nutrienteshoy.get(3)
                        + (listal.get(i).getAlimento().getProteinas() * listal.get(i).getRaciones()))
                        .replace(",", ".")));
                nutrienteshoy.set(4, Float.parseFloat(String.format("%.2f", nutrienteshoy.get(4)
                        + (listal.get(i).getAlimento().getSal() * listal.get(i).getRaciones()))
                        .replace(",", ".")));

            }
            model.addAttribute("listaAlimentos", alimentacion.getAlimentos());
            model.addAttribute("nutrientes", nutrienteshoy);
            model.addAttribute("listaAlimentosCon", listal);

            Participante p = (Participante) user;

            // buscar notificaciones
            List<Notificacion> notificaciones = noti.notificaciones(p);
            model.addAttribute("notificaciones", notificaciones);

            return "nutrientes";
        } else
            return "acceso";
    }

    @PostMapping("/process_alimentoCon/{id}")
    public String process_alimento(@PathVariable("id") Integer id, @ModelAttribute AlimentosConsumidos alimento) {
        if (getUsuario().getId() == id) {
            alimento.setParticipante(participante.findById(getUsuario().getId()).get());
            alimento.setFecha_consumicion(LocalDateTime.now());
            alimento.setId(0);
            alimentacion.saveAlCon(alimento);
            return "redirect:/alimentos";
        } else
            return "redirect:/";
    }

    @GetMapping("/nuevoalimento")
    public String nuevoal(Model model) {
        Usuario user = getUsuario();
        model.addAttribute("user", user);

        if (user instanceof Participante && user.getEstadoCuenta().equals(Estado.ALTA)) {
            model.addAttribute("alimento", new Alimentacion());

            // buscar notificaciones
            List<Notificacion> notificaciones = noti.notificaciones(participante.getParticipante(user.getId()));
            model.addAttribute("notificaciones", notificaciones);

            return "nuevoalimento";
        } else
            return "acceso";
    }

    @PostMapping("/process_alimento")
    public String process_al(@ModelAttribute Alimentacion alimento) {
        if (getUsuario().getEstadoCuenta().equals(Estado.ALTA) && getUsuario() instanceof Participante) {
            alimento.setId(0);
            alimentacion.saveAlimento(alimento);
            return "redirect:/alimentos";
        } else
            return "redirect:/";
    }

    @RequestMapping("/alimento/eliminar/{id}")
    public String process_alimentoCeliminar(@PathVariable(name = "id") Integer id) {
        if (getUsuario().getEstadoCuenta().equals(Estado.ALTA)) {
            alimentacion.deleteAlCon(id);
            return "redirect:/alimentos";
        } else
            return "redirect:/";
    }

    @GetMapping("/recetas")
    public String recetas(Model model) {
        Usuario user = getUsuario();
        model.addAttribute("user", user);

        if (user instanceof Participante && user.getEstadoCuenta().equals(Estado.ALTA)) {
            model.addAttribute("lareceta", new Receta());
            model.addAttribute("listaRecetas", alimentacion.getRecetas());
            model.addAttribute("listaIng", alimentacion.getAlimentos());

            // buscar notificaciones
            List<Notificacion> notificaciones = noti.notificaciones(participante.getParticipante(user.getId()));
            model.addAttribute("notificaciones", notificaciones);
            return "recetas";
        } else
            return "acceso";
    }

    @PostMapping("/process_receta")
    public String procesoReceta(@ModelAttribute Receta lareceta, @RequestParam("file") MultipartFile file) {
        if (getUsuario().getEstadoCuenta().equals(Estado.ALTA)) {
            if (!file.isEmpty()) {
                try {
                    Path rutaArchivo = Paths.get("src//main//resources//static/recetas");
                    String rutaAbsoluta = rutaArchivo.toFile().getAbsolutePath();
                    byte[] bytesArc = file.getBytes();
                    Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + file.getOriginalFilename());
                    Files.write(rutaCompleta, bytesArc);
                } catch (Exception e) {
                    String mensaje = "Ha ocurrido un error: " + e.getMessage();
                    JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
                }
                lareceta.setLink(file.getOriginalFilename());
                lareceta.setId(0);
                alimentacion.updateReceta(lareceta);
            }
        }
        return "redirect:/recetas";
    }

    @GetMapping("/unareceta/{link}")
    public String unareceta(@PathVariable("link") String link, Model model) {
        if (getUsuario().getEstadoCuenta().equals(Estado.ALTA)) {
            model.addAttribute("link", link);
            model.addAttribute("user", getUsuario());

            // buscar notificaciones
            List<Notificacion> notificaciones = noti.notificaciones(participante.getParticipante(getUsuario().getId()));
            model.addAttribute("notificaciones", notificaciones);

            return "unareceta";
        } else
            return "redirect:/";
    }

    @GetMapping("/recetasparecidas")
    public String recetasParecidas(@RequestParam(required = false) String[] want,
            @RequestParam(required = false) String[] dontwant, Model model) {
        Usuario u = getUsuario();
        model.addAttribute("user", u);

        List<Receta> listaRecetas = new ArrayList<>();
        if (u instanceof Participante && u.getEstadoCuenta().equals(Estado.ALTA)) {
            if (want.length == 0)
                model.addAttribute("nohaywants", "No ha seleccionado ningún ingrediente que busque");
            // EN PYTHON:
            else {
                List<String> recetas = new ArrayList<String>();
                String[] recetasArray = service.recetasparecidas(want, dontwant);
                recetas = Arrays.asList(recetasArray);
                String[] recetaArray = recetas.get(0).split(",");
                for (String idReceta : recetaArray) {
                    if (!idReceta.equals("")) {
                        Receta receta = alimentacion.getRecetasById(Integer.parseInt(idReceta));
                        if (receta != null) {
                            listaRecetas.add(receta);
                        }
                    }
                }

            }
            model.addAttribute("listaRecetas", listaRecetas);
            // buscar notificaciones
            List<Notificacion> notificaciones = noti.notificaciones(participante.getParticipante(u.getId()));
            model.addAttribute("notificaciones", notificaciones);

            return "recetasparecidas";
        } else
            return "acceso";
    }

    @GetMapping("/recomendaciones/{id}")
    public String recomendaciones(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("user", getUsuario());

        if (getUsuario().getEstadoCuenta().equals(Estado.ALTA) && getUsuario() instanceof Participante) {

            // lista con tendencias globales
            List<String> recetas = new ArrayList<String>();
            String[] recetasArray = service.recomendacionesglobales();
            recetas = Arrays.asList(recetasArray);
            String[] recetaArray = recetas.get(0).split(",");
            List<Receta> listaRecetas = new ArrayList<>();
            for (String idReceta : recetaArray) {
                if (!idReceta.equals("")) {
                    Receta receta = alimentacion.getRecetasById(Integer.parseInt(idReceta));
                    if (receta != null) {
                        listaRecetas.add(receta);
                    }
                }
            }
            model.addAttribute("globales", listaRecetas);

            // lista con recomendaciones individuales
            List<String> recetas1 = new ArrayList<String>();
            String[] recetasArray1 = service.recomendacionesindividuales(id);
            recetas1 = Arrays.asList(recetasArray1);
            String[] recetaArray1 = recetas1.get(0).split(",");
            List<Receta> listaRecetas1 = new ArrayList<>();
            for (String idReceta : recetaArray1) {
                if (!idReceta.equals("")) {
                    Receta receta = alimentacion.getRecetasById(Integer.parseInt(idReceta));
                    if (receta != null) {
                        listaRecetas1.add(receta);
                    }
                }
            }

            model.addAttribute("individuales", listaRecetas1);

            // buscar notificaciones
            List<Notificacion> notificaciones = noti.notificaciones(participante.getParticipante(getUsuario().getId()));
            model.addAttribute("notificaciones", notificaciones);

            return "recetasrecomendadas";
        } else
            return "redirect:/";
    }

    // FICHAS
    @GetMapping("/fichas")
    public String fichas(Model model) {

        Usuario u = getUsuario();
        model.addAttribute("user", getUsuario());

        if (u instanceof Participante && u.getEstadoCuenta().equals(Estado.ALTA)) {
            // buscar notificaciones
            List<Notificacion> notificaciones = noti.notificaciones(participante.getParticipante(u.getId()));
            model.addAttribute("notificaciones", notificaciones);
            return "fichas";
        } else
            return "acceso";

    }

    @GetMapping("/fichaEleccion")
    public String fichaEleccion(Model model) {

        Usuario u = getUsuario();
        model.addAttribute("user", getUsuario());

        if (u instanceof Participante && u.getEstadoCuenta().equals(Estado.ALTA)) {
            // buscar notificaciones
            List<Notificacion> notificaciones = noti.notificaciones(participante.getParticipante(u.getId()));
            model.addAttribute("notificaciones", notificaciones);
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

            // buscar notificaciones
            List<Notificacion> notificaciones = noti.notificaciones(participante.getParticipante(u.getId()));
            model.addAttribute("notificaciones", notificaciones);

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

    @GetMapping("/fichaTaller")
    public String fichataller(Model model) {
        Usuario u = getUsuario();
        model.addAttribute("user", getUsuario());

        if (u instanceof Participante && u.getEstadoCuenta().equals(Estado.ALTA)) {
            FichaTaller fichaTaller = f.getFichaTaller(participante.findById(u.getId()).get());
            model.addAttribute("ficha", fichaTaller);

            // buscar notificaciones
            List<Notificacion> notificaciones = noti.notificaciones(participante.getParticipante(u.getId()));
            model.addAttribute("notificaciones", notificaciones);

            return "fichaTaller";
        } else
            return "acceso";
    }

    @PostMapping("/process_fichat/{id}")
    public String processtaller(@PathVariable("id") Integer id, @ModelAttribute FichaTaller ficha) {
        Usuario u = getUsuario();

        if (u instanceof Participante && u.getEstadoCuenta().equals(Estado.ALTA)) {
            f.updateFichaTaller(ficha);
            return "redirect:/fichas";
        } else
            return "acceso";
    }

    @GetMapping("/fichaObjetivo")
    public String fichaObjetivo(Model model) {
        Usuario u = getUsuario();
        model.addAttribute("user", getUsuario());

        if (u instanceof Participante && u.getEstadoCuenta().equals(Estado.ALTA)) {
            Participante p = participante.getParticipante(u.getId());

            FichaObjetivo fichaObjetivo = f.getFichaObjetivo(participante.findById(u.getId()).get());
            model.addAttribute("ficha", fichaObjetivo);

            Exploracion exploracion = null;
            List<FaseValoracion> formularios = fasevaloracion.faseValoracion(p);
            for (int i = 0; i < formularios.size(); i++) {
                if (formularios.get(i) instanceof Exploracion) {
                    exploracion = (Exploracion) formularios.get(i);
                }
            }

            if (exploracion != null) {
                model.addAttribute("exploracion", exploracion);

                // altura
                String talla = String.format("%.2f", exploracion.getTalla() / 100.0).replace(",", "."); // Formatear a
                                                                                                        // dos decimales
                model.addAttribute("talla", talla);

                // peso saludable para imc de 25
                Double s = 25.00 * ((exploracion.getTalla() / 100.0) * (exploracion.getTalla() / 100.0));
                String saludable = String.format("%.2f", s).replace(",", ".");
                model.addAttribute("saludable", saludable);

                // 5% del peso con 2 decimales
                Double cincoPeso = exploracion.getPeso() * 0.05;
                String cinco = String.format("%.2f", cincoPeso).replace(",", ".");
                model.addAttribute("cinco", cinco);

                // 10% del peso con 2 decimales
                Double diezPeso = exploracion.getPeso() * 0.1;
                String diez = String.format("%.2f", diezPeso).replace(",", ".");
                model.addAttribute("diez", diez);

                // buscar notificaciones
                List<Notificacion> notificaciones = noti.notificaciones(participante.getParticipante(u.getId()));
                model.addAttribute("notificaciones", notificaciones);

                return "fichaObjetivo";
            }

            else
                return "acceso";

        } else
            return "acceso";
    }

    @PostMapping("/process_fichao/{id}")
    public String processFichaObjetivo(@PathVariable("id") Integer id, @ModelAttribute FichaObjetivo ficha) {
        Usuario u = getUsuario();

        if (u instanceof Participante && u.getEstadoCuenta().equals(Estado.ALTA)) {
            f.updateFichaObjetivo(ficha);
            return "redirect:/fichas";
        } else
            return "acceso";
    }

    @GetMapping("/agregar_vaso/{idParticipante}")
    public String agregarVaso(@PathVariable("idParticipante") Integer idParticipante, Model model) {

        Participante p = participante.getParticipante(idParticipante);
        ObjetivoAgua objetivoAgua = obj.findByFechaAndParticipanteAgua(new Date(), p);

        if (objetivoAgua == null) {
            objetivoAgua = new ObjetivoAgua();
            objetivoAgua.setCantidadObjetivo(8);
            objetivoAgua.setEstadoObjetivo(EstadoObjetivo.PENDIENTE);
            objetivoAgua.setFecha(new Date());
            objetivoAgua.setParticipante(p);
            objetivoAgua.setVasos(1);
        } else if (objetivoAgua.getVasos() < objetivoAgua.getCantidadObjetivo()) {
            int vasos = objetivoAgua.getVasos() + 1;
            objetivoAgua.setVasos(vasos);
        }

        if (objetivoAgua.getVasos() == objetivoAgua.getCantidadObjetivo()) {
            objetivoAgua.setEstadoObjetivo(EstadoObjetivo.COMPLETADO);
        }

        obj.guardarAgua(objetivoAgua);

        return "redirect:/objetivos";

    }

    @GetMapping("/quitar_vaso/{idParticipante}")
    public String quitarVaso(@PathVariable("idParticipante") Integer idParticipante, Model model) {

        Participante p = participante.getParticipante(idParticipante);
        ObjetivoAgua objetivoAgua = obj.findByFechaAndParticipanteAgua(new Date(), p);

        if (objetivoAgua != null) {

            if (objetivoAgua.getVasos() > 0) {
                int vasos = objetivoAgua.getVasos() - 1;
                objetivoAgua.setVasos(vasos);

                if (objetivoAgua.getVasos() == objetivoAgua.getCantidadObjetivo()) {
                    objetivoAgua.setEstadoObjetivo(EstadoObjetivo.COMPLETADO);
                } else {
                    objetivoAgua.setEstadoObjetivo(EstadoObjetivo.PENDIENTE);
                }

            }

        }

        obj.guardarAgua(objetivoAgua);

        return "redirect:/objetivos";

    }

    @PostMapping("/agregar_ejercicio/{idParticipante}")
    public String agregarEjercicio(@PathVariable("idParticipante") Integer idParticipante,
            ObjetivoEjercicio ejercicioObj) {

        Participante p = participante.getParticipante(idParticipante);

        if (ejercicioObj.getEjercicio() != Ejercicio.NINGUNO) {
            ObjetivoEjercicio objetivoEjercicio = new ObjetivoEjercicio();
            objetivoEjercicio.setEstadoObjetivo(EstadoObjetivo.PENDIENTE);
            objetivoEjercicio.setFecha(new Date());
            objetivoEjercicio.setParticipante(p);
            objetivoEjercicio.setEjercicio(ejercicioObj.getEjercicio());
            objetivoEjercicio.setDuracionEjercicio(ejercicioObj.getDuracionEjercicio());

            obj.guardarEjercicio(objetivoEjercicio);
        }

        return "redirect:/objetivos";

    }

    @GetMapping("/eliminar_ejercicio/{idObjetivo}")
    public String eliminarEjercicio(@PathVariable("idObjetivo") Integer idObjetivo, Model model) {

        ObjetivoEjercicio objetivoEjercicio = obj.getObjetivoEjercicio(idObjetivo);

        if (getUsuario() instanceof Participante
                && getUsuario().getId() == objetivoEjercicio.getParticipante().getId()) {
            obj.borrarObjetivoEjercicio(idObjetivo);
            return "redirect:/objetivos";
        } else
            return "redirect:/";

    }

    @PostMapping("/agregar_descanso/{idParticipante}")
    public String agregarDescanso(@PathVariable("idParticipante") Integer idParticipante,
            ObjetivoDescanso descansoObj) {

        Participante p = participante.getParticipante(idParticipante);
        ObjetivoDescanso objetivoDescanso = obj.findByFechaAndParticipanteDescanso(new Date(), p);

        if (objetivoDescanso == null) {

            objetivoDescanso = new ObjetivoDescanso();
            objetivoDescanso.setFecha(new Date());
            objetivoDescanso.setParticipante(p);
            objetivoDescanso.setHorasSuenio(descansoObj.getHorasSuenio());
            objetivoDescanso.setMinutosSuenio(descansoObj.getMinutosSuenio());

            if (descansoObj.getHorasSuenio() >= descansoObj.getHorasSuenioObjetivo()) {
                objetivoDescanso.setEstadoObjetivo(EstadoObjetivo.COMPLETADO);
            } else {
                objetivoDescanso.setEstadoObjetivo(EstadoObjetivo.PENDIENTE);
            }

        }

        obj.guardarDescanso(objetivoDescanso);

        return "redirect:/objetivos";

    }

    @GetMapping("/eliminar_descanso/{idParticipante}")
    public String eliminarDescanso(@PathVariable("idParticipante") Integer idParticipante, Model model) {

        Participante p = participante.getParticipante(idParticipante);

        ObjetivoDescanso objetivoDescanso = obj.findByFechaAndParticipanteDescanso(new Date(), p);

        if (getUsuario() instanceof Participante
                && getUsuario().getId() == objetivoDescanso.getParticipante().getId()) {
            obj.borrarObjetivoDescanso(objetivoDescanso.getId());
            return "redirect:/objetivos";
        } else
            return "redirect:/";

    }

    @PostMapping("/agregar_estadoAnimo/{idParticipante}")
    public String agregarEstadoAnimo(@PathVariable("idParticipante") Integer idParticipante,
            ObjetivoEstadoAnimo estadoAnimoObj) {

        Participante p = participante.getParticipante(idParticipante);
        ObjetivoEstadoAnimo objetivoEstadoAnimo = obj.findByFechaAndParticipanteEstadoAnimo(new Date(), p);

        if (objetivoEstadoAnimo == null) {

            objetivoEstadoAnimo = new ObjetivoEstadoAnimo();
            objetivoEstadoAnimo.setEstadoAnimo(estadoAnimoObj.getEstadoAnimo());
            objetivoEstadoAnimo.setFecha(new Date());
            objetivoEstadoAnimo.setParticipante(p);

        }

        obj.guardarEstadoAnimo(objetivoEstadoAnimo);

        return "redirect:/objetivos";
    }

    @GetMapping("/eliminar_estadoAnimo/{idParticipante}")
    public String eliminarEstadoAnimo(@PathVariable("idParticipante") Integer idParticipante, Model model) {

        Participante p = participante.getParticipante(idParticipante);

        ObjetivoEstadoAnimo objetivoEstadoAnimo = obj.findByFechaAndParticipanteEstadoAnimo(new Date(), p);

        if (getUsuario() instanceof Participante
                && getUsuario().getId() == objetivoEstadoAnimo.getParticipante().getId()) {
            obj.borrarObjetivoEstadoAnimo(objetivoEstadoAnimo.getId());
            return "redirect:/objetivos";
        } else
            return "redirect:/";

    }

    @GetMapping("/analitica/{id}")
    public String analitica(@PathVariable Integer id, Model model) {
        Participante p = participante.findById(id).get();
        if(p.getEstadoCuenta() == Estado.BAJA) usuario.mandarAnalitica(p);
        else if (getUsuario() instanceof Coordinador
                && (p.getCoordinador().getId() == getUsuario().getId())) {
            usuario.mandarAnalitica(p);
        }
        return "redirect:/expediente/{id}";

    }

}
