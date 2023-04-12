package com.estepper.estepper.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.estepper.estepper.model.entity.Grupo;
import com.estepper.estepper.model.entity.Materiales;
import com.estepper.estepper.model.entity.Coordinador;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Usuario;
import com.estepper.estepper.model.entity.Mensaje;
import com.estepper.estepper.model.entity.MensajePrivado;
import com.estepper.estepper.model.entity.Observaciones;
import com.estepper.estepper.model.enums.Estado;
import com.estepper.estepper.model.enums.EstadoGrupo;
import com.estepper.estepper.service.CoordinadorService;
import com.estepper.estepper.service.GrupoService;
import com.estepper.estepper.service.ParticipanteService;
import com.estepper.estepper.service.MensajeService;
import com.estepper.estepper.service.ObservacionesService;
import com.estepper.estepper.service.MaterialService;
import com.estepper.estepper.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;

//yo pasaria todo esto a CoordinadorController para no tener tantos
@Controller
public class GruposController {

    @Autowired // inyectar recursos de la clase GrupoService
    private GrupoService grupo;

    @Autowired
    private ParticipanteService part;

    @Autowired
    private CoordinadorService cord;

    @Autowired
    private UsuarioService user;

    @Autowired
    private MaterialService materialS;

    @Autowired
    private MensajeService mensaje;

    @Autowired // inyectar recursos de la clase GrupoService
    private ObservacionesService observaciones;

    @PostMapping("/grupos/guardar")
    public String guardarGrupo(@ModelAttribute("grupo") Grupo elgrupo,
            @RequestParam(value = "participantes", required = false) List<Integer> participantes,
            Model model) {

        elgrupo.setIdCoordinador(getUsuario().getId());

        // FUNCIONA PERO FALTA CONSEGUIR QUE SALGA LA ALERTA CON EL MENSAJITO
        if (grupo.findByNombre(elgrupo.getNombre()) != null) {

            return "redirect:/listaGrupos";

        }

        System.out.println(elgrupo.getNombre());

        String elcodigo = RandomStringUtils.randomAlphanumeric(15).toUpperCase();
        while (grupo.findByCodigo(elcodigo) != null) {
            elcodigo = RandomStringUtils.randomAlphanumeric(15).toUpperCase();
        }
        elgrupo.setCodigo(elcodigo);

        if (participantes != null) {
            List<Participante> participantesSeleccionadosList = new ArrayList<>();
            for (Integer participanteId : participantes) {
                Participante participante = part.findById(participanteId).get();
                System.out.println(participante.getNickname());
                participantesSeleccionadosList.add(participante);
                grupo.save(elgrupo);
                Grupo g = grupo.getGrupo(elgrupo.getId());

                part.update(participante.edad, participante.getSexo(), participante.getFotoParticipante(), g,
                        participante.getAsistencia(), participante.getIdCoordinador(), participante.getPerdidaDePeso(),
                        participante.getSesionesCompletas(), participante.getPerdidacmcintura(), participanteId);

                elgrupo.setNumParticipantes(participantesSeleccionadosList.size());

            }
            elgrupo.setParticipantes(participantesSeleccionadosList);

        } else {
            elgrupo.setNumParticipantes(0);
        }

        elgrupo.setFotoGrupo("/img/grupoA.png");
        elgrupo.setFechaInicioGrupo(LocalDate.now());

        if (elgrupo.getFechaFinGrupo() == null) {
            elgrupo.setEstadoGrupo(EstadoGrupo.ACTIVO);
        } else if (elgrupo.getFechaFinGrupo().isBefore(LocalDate.now())) { // Si quiero tmb que sea la misma: ||
            // elgrupo.getFechaFinGrupo().isEqual(LocalDate.now())
            elgrupo.setEstadoGrupo(EstadoGrupo.TERMINADO);
        } else {
            elgrupo.setEstadoGrupo(EstadoGrupo.ACTIVO);
        }

        grupo.save(elgrupo);
        return "redirect:/listaGrupos";
    }

    @PostMapping("/grupos/guardar2")
    public String guardarGrupo2(@ModelAttribute("grupo") Grupo elgrupo,
            @RequestParam(value = "participantes", required = false) List<Integer> participantes,
            Model model) {

        elgrupo.setIdCoordinador(getUsuario().getId());

        if (participantes != null) {
            List<Participante> participantesSeleccionadosList = new ArrayList<>();
            for (Integer participanteId : participantes) {
                Participante participante = part.findById(participanteId).get();
                participantesSeleccionadosList.add(participante);
                grupo.save(elgrupo);
                Grupo g = grupo.getGrupo(elgrupo.getId());

                part.update(participante.getEdad(), participante.getSexo(), participante.getFotoParticipante(), g,
                        participante.getAsistencia(), participante.getIdCoordinador(), participante.getPerdidaDePeso(),
                        participante.getSesionesCompletas(), participante.getPerdidacmcintura(), participanteId);

                elgrupo.setNumParticipantes(g.getNumParticipantes() + participantesSeleccionadosList.size());

            }
            elgrupo.setParticipantes(participantesSeleccionadosList);

        }

        if (elgrupo.getFechaFinGrupo() == null) {
            elgrupo.setEstadoGrupo(EstadoGrupo.ACTIVO);
        } else if (elgrupo.getFechaFinGrupo().isBefore(LocalDate.now())) { // Si quiero tmb que sea la misma: ||
            // elgrupo.getFechaFinGrupo().isEqual(LocalDate.now())
            elgrupo.setEstadoGrupo(EstadoGrupo.TERMINADO);

        } else {
            elgrupo.setEstadoGrupo(EstadoGrupo.ACTIVO);
        }

        grupo.save(elgrupo);
        return "redirect:/listaGrupos";
    }

    @GetMapping("/listaGrupos")
    public String grupos(@RequestParam Map<String, Object> params, Model model) {

        if (getUsuario() instanceof Coordinador) {

            List<Participante> participantesExistentes = part.listado(getUsuario().getId(), Estado.BAJA);

            int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
            PageRequest pageable = PageRequest.of(page, 5); // define página solicitada y tamaño de la página, se
                                                            // inicializa a cero
            Page<Grupo> paginaGrupo = grupo.paginas(pageable, getUsuario().getId()); // listado de páginas de 6 grupos
                                                                                     // cada una
            int totalPags = paginaGrupo.getTotalPages(); // total de páginas

            if (totalPags > 0) {
                List<Integer> paginas = IntStream.rangeClosed(1, totalPags).boxed().collect(Collectors.toList()); // listado
                                                                                                                  // con
                                                                                                                  // los
                                                                                                                  // números
                                                                                                                  // de
                                                                                                                  // las
                                                                                                                  // páginas
                model.addAttribute("paginas", paginas);
            }

            List<Grupo> listaGrupos = paginaGrupo.getContent();
            // List<Grupo> listaGrupos = grupo.listaGrupos(getUsuario().getId());
            model.addAttribute("listaGrupos", listaGrupos);
            model.addAttribute("user", getUsuario());
            model.addAttribute("mensajito", "No asignada");
            model.addAttribute("participantesExistentes", participantesExistentes);
            model.addAttribute("grupo", new Grupo());
            return "grupos";
        } else
            return "redirect:/";
    }

    @GetMapping("/grupos/editar/{id}")
    public ModelAndView mostrarFormularioDeEditarGrupo(@PathVariable(name = "id") Integer id) {
        ModelAndView modelo = new ModelAndView("editar_grupo");
        Grupo gr = grupo.getGrupo(id);

        List<Participante> participantesExistentes = part.listado(getUsuario().getId(), Estado.BAJA);// obtener lista de
                                                                                                     // participantes de
                                                                                                     // la base de
        modelo.addObject("participantesExistentes", participantesExistentes);

        modelo.addObject("grupo", gr);
        modelo.addObject("user", getUsuario());
        modelo.addObject("listadoParticipantesGrupo", part.listadoGrupo(gr));

        return modelo;
    }

    @GetMapping("/grupos/eliminar/{id}")
    public String eliminarGrupo(@PathVariable("id") Integer id) {
        Grupo gr = grupo.getGrupo(id);
        if (getUsuario() instanceof Coordinador && gr.getIdCoordinador() == getUsuario().getId()) {
            materialS.deleteByGrupo(gr);
            mensaje.deleteByGrupo(gr);
            grupo.delete(id);
            return "redirect:/listaGrupos";
        } else
            return "redirect:/";
    }

    @GetMapping("/unirAgrupo/{id}")
    public String unirAgrupo(@PathVariable("id") Integer id, Model model) {
        if (getUsuario() instanceof Coordinador) {
            model.addAttribute("participante", part.findById(id).get());
            model.addAttribute("user", user.findById(id).get());
            model.addAttribute("idparticipante", id);

            List<Grupo> listaGrupos = grupo.listaGrupos(getUsuario().getId());
            model.addAttribute("listaGrupos", listaGrupos);

            return "unirAgrupo";
        } else
            return "redirect:/";

    }

    @GetMapping("/unGrupo/{idGrupo}")
    public String unGrupo(@PathVariable("idGrupo") Integer idGrupo, Model model) {
        Grupo g = grupo.getGrupo(idGrupo);

        if (getUsuario() instanceof Coordinador && g.getIdCoordinador() == getUsuario().getId()) {
            model.addAttribute("listadoParticipantesGrupo", part.listadoGrupo(g));
            model.addAttribute("grupo", g);
            model.addAttribute("user", getUsuario());
            model.addAttribute("mensajito", "No asignada");
            Mensaje men = new Mensaje();
            model.addAttribute("message", men);
            List<Mensaje> mensajes = mensaje.obtenerMensajes(g);
            model.addAttribute("mensajes", mensajes);

            List<Observaciones> listaObservaciones = observaciones.findByIdGrupo(idGrupo);
            model.addAttribute("listaObservaciones", listaObservaciones);

            return "unGrupo";
        } else

            return "redirect:/";
    }

    // MATERIALES:
    @GetMapping("/materialesGrupo/{id}")
    public String mostrarMateriales(@PathVariable("id") Integer id, Model model) {
        Grupo g = grupo.getGrupo(id);
        if (getUsuario() instanceof Coordinador && g.getIdCoordinador() == getUsuario().getId()) {
            Usuario elusuario = getUsuario();
            model.addAttribute("user", elusuario);
            model.addAttribute("listado", materialS.materialesGrupo(g));
            Materiales material = new Materiales();
            model.addAttribute("material", material);
            model.addAttribute("id", id);
            return "materialesGrupo";
        } else
            return "redirect:/";
    }

    // subir material a un grupo
    @PostMapping("/process_materialGrupo/{id}")
    public String procesoMaterial(@PathVariable("id") Integer id, @ModelAttribute Materiales material,
            @RequestParam("file") MultipartFile file) {
        Grupo elgrupo = grupo.getGrupo(id);
        if (getUsuario() instanceof Coordinador && elgrupo.getIdCoordinador() == getUsuario().getId()) {
            material.setGrupo(elgrupo);
            if (!file.isEmpty()) {
                Path rutaArchivo = Paths.get("src//main//resources//static/materiales");
                String rutaAbsoluta = rutaArchivo.toFile().getAbsolutePath();
                try {
                    byte[] bytesArc = file.getBytes();
                    Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + file.getOriginalFilename());
                    Files.write(rutaCompleta, bytesArc);
                    material.setLink(rutaCompleta.toString());
                    List<Participante> losparticipantes = part.listadoGrupo(elgrupo);
                    for (int i = 0; i < losparticipantes.size(); i++) {
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
        Integer idG = materialS.getMaterial(id).getGrupo().getId();
        if (getUsuario() instanceof Coordinador) {
            materialS.eliminarMaterialGrupo(id);
        }

        return "redirect:/materialesGrupo/" + idG;
    }

    @GetMapping("/echargrupo/{id}")
    public String echargrupo(@PathVariable("id") Integer id) {
        if (getUsuario() instanceof Coordinador) {
            part.quitargrupo(id);
            return "redirect:/listaGrupos";
        } else
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

            Usuario usuario = user.logueado(Integer.parseInt(codigo)); // atributos del logueado
            return usuario;
        }
        return null;

    }

    @GetMapping("/chat") // Cambiar por miGrupo más tarde
    public String chat(Model model) {
        Usuario u = getUsuario();
        model.addAttribute("user", getUsuario());

        if (u instanceof Participante && u.getEstadoCuenta().equals(Estado.ALTA)) {
            Participante p = part.getParticipante(u.getId());
            Grupo g = grupo.getGrupo(p.getIdGrupo());

            Mensaje men = new Mensaje();
            MensajePrivado menPriv = new MensajePrivado();

            List<Mensaje> mensajes = mensaje.obtenerMensajes(g);
            List<MensajePrivado> mensajesPrivados = mensaje.obtenerMensajesPrivados(p);

            model.addAttribute("message", men);
            model.addAttribute("mensajes", mensajes);
            model.addAttribute("grupo", g);
            model.addAttribute("listadoParticipantesGrupo", part.listadoGrupo(g));

            model.addAttribute("messagePriv", menPriv);

            model.addAttribute("mensajesPrivados", mensajesPrivados);

            return "chat";
        } else
            return "acceso";
    }

    @GetMapping("/chatPrivado/{id}") // vista coordinador
    public String chatPrivado(@PathVariable("id") Integer idParticipante, Model model) {

        Usuario u = getUsuario();
        model.addAttribute("user", u);
        Participante p = part.getParticipante(idParticipante);

        if (u instanceof Coordinador) { // revisar esto

            MensajePrivado menPriv = new MensajePrivado();
            List<MensajePrivado> mensajesPrivados = mensaje.obtenerMensajesPrivados(p);
            model.addAttribute("participante", p);
            model.addAttribute("messagePriv", menPriv);
            model.addAttribute("mensajesPrivados", mensajesPrivados);

            return "chatPrivado";
        } else

            return "redirect:/";
    }

    @PostMapping("/mensajes/guardar/{id}")
    public String guardarMensaje(@ModelAttribute("message") Mensaje elmensaje, @PathVariable("id") Integer idGrupo) {
        if (elmensaje.getMensaje() != "") {
            elmensaje.setGrupo(grupo.getGrupo(idGrupo));
            elmensaje.setUsuario(getUsuario());
            elmensaje.setId(0);
            elmensaje.setFechayHoraEnvio(LocalDateTime.now());
            mensaje.save(elmensaje);

            if (getUsuario() instanceof Coordinador)
                return "redirect:/unGrupo/{id}";
            else if (getUsuario() instanceof Participante)
                return "redirect:/chat";
        }

        return "redirect:/chat";
    }

    @PostMapping("/mensajesPrivados/guardar/{idParticipante}")
    public String guardarMensajePrivado(@ModelAttribute("messagePriv") MensajePrivado elmensajePrivado,
            @PathVariable("idParticipante") Integer idParticipante) {
        String elmensaje = elmensajePrivado.getMensaje();
        Map<String, String> filtros = new HashMap<>();
        filtros.put("puta", "******");
        filtros.put("puto", "******");
        filtros.put("gilipollas", "******");
        filtros.put("joder", "******");
        filtros.put("coño", "******");
        filtros.put("cabrón", "******");
        filtros.put("maricón", "******");
        filtros.put("chinga tu madre", "******");
        filtros.put("hijueputa", "******");
        filtros.put("bastardo", "******");
        filtros.put("perra", "******");
        filtros.put("malparido", "******");
        filtros.put("mamón", "******");
        filtros.put("zorra", "******");
        filtros.put("pendejo", "******");
        filtros.put("conchatumadre", "******");
        filtros.put("imbécil", "******");
        filtros.put("idiota", "******");
        filtros.put("estúpido", "******");
        for (String palabra : elmensaje.split("\\s+")) {
            if (filtros.containsKey(palabra.toLowerCase())) {
                elmensaje = elmensaje.replaceAll("(?i)" + palabra, filtros.get(palabra.toLowerCase()));
            }
        }
        elmensajePrivado.setMensaje(elmensaje);
        elmensajePrivado.setCoordinador(cord.getCoordinador(part.getParticipante(idParticipante).getIdCoordinador()));
        elmensajePrivado.setParticipante(part.getParticipante(idParticipante));
        elmensajePrivado.setId(0);
        elmensajePrivado.setFechayHoraEnvio(LocalDateTime.now());
        elmensajePrivado.setUsuario(getUsuario());
        mensaje.saveMensajePrivado(elmensajePrivado);

        if (getUsuario() instanceof Coordinador)
            return "redirect:/chatPrivado/{idParticipante}";
        else if (getUsuario() instanceof Participante)
            return "redirect:/chat";
        return "redirect:/";
    }

    @PostMapping("/observaciones/guardar/{idGrupo}")
    public String guardarObervaciones(@ModelAttribute("observaciones") Observaciones observacion,
            @PathVariable("idGrupo") Integer id, Model model, HttpServletRequest request) {

        observacion.setIdCoordinador(getUsuario().getId());
        observacion.setIdGrupo(id);
        String nota = request.getParameter("nota");
        observacion.setNota(nota);
        observaciones.guardar(observacion);

        return "redirect:/unGrupo/{idGrupo}";
    }

    @GetMapping("/eliminarNota/{id}/{idGrupo}")
    public String eliminarNota(@PathVariable("id") Integer id, @PathVariable("idGrupo") Integer idGrupo) {
        Observaciones ob = observaciones.getObservacion(id);
        if (getUsuario() instanceof Coordinador && ob.getIdCoordinador() == getUsuario().getId()) {
            observaciones.borrar(id);
            return "redirect:/unGrupo/{idGrupo}";
        } else
            return "redirect:/";
    }

    @PostMapping("/observaciones/guardar2/{idGrupo}")
    public String guardarObservaciones2(@ModelAttribute("unaObservacion") Observaciones observacion,
            @PathVariable("idGrupo") Integer idGrupo, Model model, HttpServletRequest request) {
        Integer idNota = Integer.parseInt(request.getParameter("idNota"));
        if (idNota != null) {
            Observaciones observacionExistente = observaciones.getObservacion(idNota);
            observacionExistente.setIdCoordinador(getUsuario().getId());
            observacionExistente.setIdGrupo(idGrupo);
            String nota = request.getParameter("nota");
            observacionExistente.setNota(nota);
            observaciones.guardar(observacionExistente);
        }

        return "redirect:/unGrupo/{idGrupo}";
    }

}
