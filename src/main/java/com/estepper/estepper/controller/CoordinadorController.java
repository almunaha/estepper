package com.estepper.estepper.controller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import java.util.stream.IntStream;

import javax.swing.JOptionPane;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.estepper.estepper.model.enums.Estado;

import com.estepper.estepper.model.entity.Participante;

import com.estepper.estepper.model.entity.Sesion;
import com.estepper.estepper.model.enums.Asistencia;
import com.estepper.estepper.model.enums.EstadoActividad;
import com.estepper.estepper.model.enums.EstadoInvitacion;
import com.estepper.estepper.model.enums.EstadoSesion;

import com.estepper.estepper.model.entity.Usuario;
import com.estepper.estepper.model.entity.Actividad;
import com.estepper.estepper.model.entity.Administrador;
import com.estepper.estepper.model.entity.Coordinador;
import com.estepper.estepper.model.entity.Grupo;
import com.estepper.estepper.model.entity.Invitacion;
import com.estepper.estepper.model.entity.Materiales;

import com.estepper.estepper.service.ParticipanteService;
import com.estepper.estepper.service.SesionService;
import com.estepper.estepper.service.UsuarioService;
import com.estepper.estepper.service.ActividadService;
import com.estepper.estepper.service.GrupoService;
import com.estepper.estepper.service.InvitacionService;
import com.estepper.estepper.service.MaterialService;

@Controller
public class CoordinadorController {

    @Autowired // inyectar recursos de la clase ParticipanteService
    private ParticipanteService part;

    @Autowired // inyectar recursos de la clase UsuarioService
    private UsuarioService user;

    @Autowired // inyectar recursos de la clase GrupoService
    private GrupoService grupo;

    @Autowired
    private SesionService sesion;

    @Autowired
    private MaterialService mat;

    @Autowired
    private ActividadService act;

    @Autowired
    private InvitacionService inv;

    @GetMapping("/listado")
    public String participantes(@RequestParam Map<String, Object> params, Model model) {

        if (getUsuario() instanceof Coordinador) { // controlar acceso

            int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
            PageRequest pageable = PageRequest.of(page, 6); // define página solicitada y tamaño de la página, se
                                                            // inicializa a cero
            Page<Participante> paginaPart = part.paginas(pageable, getUsuario().getId(), Estado.BAJA); // listado de
                                                                                                       // páginas de 6
                                                                                                       // participantes
                                                                                                       // cada una
            int totalPags = paginaPart.getTotalPages(); // total de páginas

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

            List<Participante> listado = paginaPart.getContent();
            model.addAttribute("user", getUsuario());
            model.addAttribute("listado", listado);

            return "participantes";
        }

        else
            return "redirect:/";
    }

    @GetMapping("/sesion/{num}/{id}")
    public String sesion1(@PathVariable Integer num, @PathVariable Integer id, Model model) {

        if (num < 1 || num > 10) {
            return "redirect:/";
        } else {
            model.addAttribute("user", getUsuario());
            Participante p = part.findById(id).get();
            // CONTROLAR ESTADO ALTA O BAJA. SI BAJA MOSTRAR QUE ESTÁ DE BAJA Y NO HA
            // COMENZADO SESIONES
            Sesion lasesion = sesion.buscarSesion(p, num); // cambiar segun sesion
            model.addAttribute("sesion", lasesion);
            model.addAttribute("lasesion", lasesion);
            model.addAttribute("participante", p);

            return "sesionPart";
        }
    }

    @GetMapping("/actualizarGrupos/{idP}/{idG}")
    public String actualizarGrupos(@PathVariable(name = "idP") Integer idP, @PathVariable(name = "idG") Integer idG,
            Model model) {
        Grupo g = grupo.getGrupo(idG);

        if (getUsuario() instanceof Coordinador && g.getIdCoordinador() == getUsuario().getId()) {
            Participante usuario = part.findById(idP).get();
            model.addAttribute("user", getUsuario());

            part.update(usuario.getEdad(), usuario.getSexo(), usuario.getFotoParticipante(), g, usuario.getAsistencia(),
                    usuario.getIdCoordinador(), usuario.getPerdidaDePeso(), usuario.getSesionesCompletas(),
                    usuario.getPerdidacmcintura(), idP);
            Integer participantes = g.getNumParticipantes() + 1;
            g.setNumParticipantes(participantes);
            grupo.update(g);

            // crear las sesiones del participante
            // hay una posibilidad de que le eche del grupo y ya tenga unas sesiones creadas
            // y le meta en otro, entonces ya tendría sus sesiones
            Sesion sesion1 = sesion.buscarSesion(usuario, 1);
            if (sesion1 == null) { // si no tiene la sesion1 creada
                Sesion s;
                for (int i = 1; i <= 10; i++) {
                    s = new Sesion(0, i, usuario, EstadoSesion.ENCURSO, "", Asistencia.NO, 0, 0);
                    sesion.guardar(s);
                }
            }
            return "redirect:/listaGrupos";
        }

        else
            return "redirect:/";
    }

    @GetMapping("/eliminarDeUnGrupo/{idP}/{idG}")
    public String eliminarDeUnGrupo(@PathVariable(name = "idP") Integer idP, @PathVariable(name = "idG") Integer idG,
            Model model) {

        if (getUsuario() instanceof Coordinador) {
            Participante usuario = part.findById(idP).get();
            model.addAttribute("user", getUsuario());
            Grupo g = grupo.getGrupo(idG);

            part.update(usuario.getEdad(), usuario.getSexo(), usuario.getFotoParticipante(), null,
                    usuario.getAsistencia(),
                    usuario.getIdCoordinador(), usuario.getPerdidaDePeso(), usuario.getSesionesCompletas(),
                    usuario.getPerdidacmcintura(), idP);

            Integer participantes = g.getNumParticipantes() - 1;
            grupo.updateParticipantes(idG, participantes);
            /*
             * LO HE COMENTADO PERO FUNCIONA, ES SEGÚN QUERAMOS, POR SI QUEREMOS QUE CUANDO
             * SEA 0 EL NÚMERO DE PARTICIPANTES SE BORRE O NO EL GRUPO
             * if (g.getNumParticipantes() <= 1) {
             * grupo.delete(idG);
             * return "redirect:/listaGrupos";
             * }
             */

            return "redirect:/grupos/editar/{idG}";
        }

        else
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

    // Descargar material
    @GetMapping("/material/descargar/{id}")
    public ResponseEntity<byte[]> descargar(@PathVariable Integer id) {
        Materiales m = mat.getMaterial(id); // material que se va a descargar
        Usuario u = getUsuario(); // Usuario logueado
        Participante p = part.getParticipante(m.getParticipante().getId()); // participante DUEÑO del material

        try {
            // Es Coordinador: si el idCoordinador del dueño del material es distinto que el
            // del coordinador: si no es un participante del coordinador
            // O Es Participante: si el participante logueado no tiene el mismo id que el
            // dueño del material
            // O es Administrador
            if (u instanceof Coordinador && u.getId() != p.getIdCoordinador()
                    || u instanceof Participante && p.getId() != u.getId() || u instanceof Administrador) {
                RedirectView inicio = new RedirectView("/");
                return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION, inicio.getUrl()).build();
            }

            Path rutaArchivo = Paths.get(m.getLink());
            byte[] bytesArc = Files.readAllBytes(rutaArchivo);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); // establecer tipo de contenido que se descarga
            headers.setContentLength(bytesArc.length);

            String nombreorig = rutaArchivo.getFileName().toString(); // nombre original del archivo

            headers.setContentDisposition(
                    ContentDisposition.attachment().filename(nombreorig).build());
            ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytesArc, headers, HttpStatus.OK);

            return response;

        } catch (Exception e) {
            String mensaje = "Ha ocurrido un error: " + e.getMessage();
            JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje.getBytes());
        }
    }

    // ACTIVIDADES
    @GetMapping("nuevaActividad")
    public String nuevaActividad(Model model) {
        Usuario user = getUsuario();
        model.addAttribute("user", user);

        model.addAttribute("actividad", new Actividad());

        return "nuevaActividad";
    }

    @PostMapping("/process_actividad")
    public String process_actividad(Model model, @ModelAttribute Actividad actividad,
            @RequestParam("file") MultipartFile file) {
        Usuario user = getUsuario();
        model.addAttribute("user", user);

        actividad.setEstado(EstadoActividad.DISPONIBLE); // actividad disponible hasta que finalice
        actividad.setNumParticipantes(0); // participantes apuntados: inicializar a cero

        // Foto de la actividad
        if (!file.isEmpty()) {
            Path rutaArchivo = Paths.get("src//main//resources//static/actividades");
            String rutaAbsoluta = rutaArchivo.toFile().getAbsolutePath();

            try {
                byte[] bytesArc = file.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + file.getOriginalFilename());
                Files.write(rutaCompleta, bytesArc);

                actividad.setFoto("/actividades/" + file.getOriginalFilename());
                act.guardar(actividad); // guardar la nueva actividad en la bd

            } catch (Exception e) {
                String mensaje = "Ha ocurrido un error: " + e.getMessage();
                JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return "redirect:/actividades";
    }

    @GetMapping("/editarActividad/{id}")
    public String editarActividad(@PathVariable("id") Integer id, Model model) {

        Usuario elusuario = getUsuario();
        model.addAttribute("user", elusuario);

        if (elusuario instanceof Coordinador) {
            model.addAttribute("actividad", act.actividad(id));
            return "editar_actividad";
        } else
            return "redirect:/";

    }

    @PostMapping("/guardar_actividad")
    public String guardarActividad(@ModelAttribute Actividad actividad, @RequestParam(name = "plazas", required=false) Integer plazas) {
        Usuario elusuario = getUsuario();
        
        if (elusuario instanceof Coordinador) {
            Actividad orig = act.actividad(actividad.getId());

            if(plazas != null)
                actividad.setPlazas(orig.getPlazas() + plazas);
            else actividad.setPlazas(orig.getPlazas());

            actividad.setEstado(orig.getEstado());
            actividad.setNumParticipantes(orig.getNumParticipantes());

            //guardar actividad
            act.guardar(actividad);

            return "redirect:/actividades";
        } else
            return "redirect:/";

    }


    @GetMapping("/invitaciones/{id}")
    public String invitaciones(@PathVariable Integer id, Model model) {
        Usuario user = getUsuario();
        model.addAttribute("user", user);

        Actividad actividad = act.actividad(id);
        model.addAttribute("actividad", actividad);

        List<Grupo> grupos = grupo.listaGrupos(user.getId());
        model.addAttribute("grupos", grupos);

        List<Invitacion> invitaciones = inv.listadoCoordAct((Coordinador) user, actividad);
        model.addAttribute("invitaciones", invitaciones);

        return "invitaciones";
    }

    @PostMapping("/process_invitacion/{id}")
    public String process_invitacion(@PathVariable Integer id, @RequestParam("codigoG") String codigoG, 
    @RequestParam("tipo") String tipo, @RequestParam(name = "codigoP", required=false) Integer codigoP){

       Actividad actividad = act.actividad(id); 
       Coordinador coordinador = (Coordinador) getUsuario();

        if(tipo.equals("GRUPAL")){
            //grupo
            Grupo g = grupo.findByCodigo(codigoG);        

            //listado participantes del grupo
            List<Participante> participantes = part.listadoGrupo(g);

            //por cada participante hacer una invitacion
            for(Participante p : participantes)
                inv.guardar(new Invitacion(0, actividad, p, coordinador, EstadoInvitacion.PENDIENTE));
        }

        else {
            Usuario u = user.findByCodigo(codigoP);
            Participante p = part.findById(u.getId()).get();
            if(p != null) 
                inv.guardar(new Invitacion(0, actividad, p, coordinador, EstadoInvitacion.PENDIENTE));
        }

        return "redirect:/invitaciones/" + id;
    }

    @GetMapping("/eliminar_actividad/{id}")
    public String process_invitacion(@PathVariable(name = "id") Integer id, Model model) {

        Actividad actividad = act.actividad(id);

        // 1. Eliminar las invitaciones a esa actividad
        List<Invitacion> invitaciones = inv.listadoByAct(actividad);

        for (Invitacion invitacion : invitaciones)
            inv.borrar(invitacion);

        // 2. Eliminar la actividad
        act.borrar(actividad);

        return "redirect:/actividades";
    }

}
