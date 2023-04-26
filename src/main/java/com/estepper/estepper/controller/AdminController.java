package com.estepper.estepper.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.estepper.estepper.model.entity.Coordinador;
import com.estepper.estepper.model.entity.Grupo;

import com.estepper.estepper.model.entity.MensajeAdmin;
import com.estepper.estepper.model.entity.Notificacion;

import com.estepper.estepper.model.entity.Usuario;
import com.estepper.estepper.model.entity.Actividad;
import com.estepper.estepper.model.entity.Administrador;
import com.estepper.estepper.model.entity.Participante;

import com.estepper.estepper.model.enums.Estado;
import com.estepper.estepper.service.ActividadService;
import com.estepper.estepper.service.AdministradorService;
import com.estepper.estepper.service.AlimentacionService;
import com.estepper.estepper.service.CoordinadorService;
import com.estepper.estepper.service.FaseValoracionService;
import com.estepper.estepper.service.FichaService;
import com.estepper.estepper.service.UsuarioService;
import com.estepper.estepper.service.MaterialService;
import com.estepper.estepper.service.MensajeService;
import com.estepper.estepper.service.NotificacionService;
import com.estepper.estepper.service.ParticipanteService;
import com.estepper.estepper.service.SesionService;
import com.estepper.estepper.service.GrupoService;
import com.estepper.estepper.service.InvitacionService;
import com.estepper.estepper.service.ObjetivoService;
import com.estepper.estepper.service.ObservacionesService;
import com.estepper.estepper.service.ProgresoService;

@Controller
public class AdminController {

    @Autowired
    private UsuarioService usuario;

    @Autowired
    private FaseValoracionService fasevaloracion;

    @Autowired
    private MaterialService materialS;

    @Autowired
    private GrupoService grupoS;

    @Autowired
    private ProgresoService pro;

    @Autowired
    private ObjetivoService obj;

    @Autowired
    private ObservacionesService obs;

    @Autowired
    private ParticipanteService participante;

    @Autowired
    private CoordinadorService coordinador;

    @Autowired
    private AdministradorService administrador;

    @Autowired
    private AlimentacionService alimentacion;

    @Autowired
    private SesionService ses;

    @Autowired
    private BCryptPasswordEncoder hash;

    @Autowired
    private FichaService f;

    @Autowired
    private MensajeService mensajeS;

    @Autowired
    private ActividadService acti;

    @Autowired
    private InvitacionService invitacion;

    @Autowired
    private NotificacionService noti;

    @GetMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable(name = "id") Integer id, Model model) {
        if (usuarioLogueado() instanceof Administrador) {
            // eliminar usuario
            if (usuario.findById(id).get() instanceof Participante) {
                Participante p = participante.findById(id).get();
                materialS.deleteByParticipante(p);
                ses.deleteByParticipante(p);
                obj.deleteByParticipante(p);
                pro.deleteByParticipante(p);
                alimentacion.deleteByParticipante(p);
                f.deleteByParticipante(p);
                mensajeS.deleteByParticipante(p);
                invitacion.eliminarPorParticipante(p); // invitacionesPart
                // eliminar asistencia y aumentar plazas
                List<Actividad> actividades = acti.asistenciaParticipante(id);
                for (Actividad actividad : actividades) {
                    actividad.setNumParticipantes(actividad.getNumParticipantes() - 1);
                    actividad.setPlazas(actividad.getPlazas() + 1);
                    actividad.getParticipantes().remove(p);
                    acti.guardar(actividad);
                }

                // eliminar notificaciones
                List<Notificacion> notificaciones = noti.notificaciones(p);
                for (Notificacion notif : notificaciones) {
                    noti.eliminar(notif);
                }

                fasevaloracion.eliminarcuenta(p);

                if (p.getGrupo() != null) {
                    p.getGrupo().setNumParticipantes(p.getGrupo().getNumParticipantes() - 1);
                    grupoS.update(p.getGrupo());
                }
            } else if (usuario.findById(id).get() instanceof Coordinador) {
                Coordinador c = coordinador.getCoordinador(id);

                mensajeS.deleteByCoordinadorMensajePrivado(c);
                obs.deleteByCoordinador(c);
                List<Grupo> listgrupos = grupoS.getGrupos();

                for (int i = 0; i < listgrupos.size(); i++) {
                    if (listgrupos.get(i).getIdCoordinador() == id) {
                        materialS.deleteByGrupo(listgrupos.get(i));
                        mensajeS.deleteByGrupo(listgrupos.get(i));
                        obs.deleteByGrupo(listgrupos.get(i));
                        grupoS.delete(listgrupos.get(i).getId());

                        // grupoS.delete(id);
                    }
                }
                invitacion.eliminarPorCoordinador(coordinador.getCoordinador(id));
                usuario.eliminar(id);
            } else
                usuario.eliminar(id);

            // pasar usuario logueado y listado
            model.addAttribute("user", usuarioLogueado());
            List<Usuario> lista = listadoUsuarios();
            model.addAttribute("usuarios", lista);
        }

        return "redirect:/";
    }

    @PostMapping("/process_coordinador")
    public String crear(@ModelAttribute Coordinador coordinador, Model model) {
        if (usuarioLogueado() instanceof Administrador) {

            hash = new BCryptPasswordEncoder();
            String encodedPassword = hash.encode(coordinador.getContrasenia());
            coordinador.setContrasenia(encodedPassword);

            Integer elcodigo = new Random().nextInt(100000 + 1);

            // comprobar que no existe un usuario con ese codigo
            while (usuario.logueado(elcodigo) != null) {
                elcodigo = new Random().nextInt(100000 + 1);
            }

            coordinador.setCodigo(elcodigo);
            coordinador.setEstadoCuenta(Estado.ALTA);
            coordinador.setFotoUsuario("/img/p1.png");
            coordinador.setIdAdministrador(usuarioLogueado().getId());

            usuario.guardar(coordinador);

            model.addAttribute("user", usuarioLogueado());

            List<Usuario> lista = listadoUsuarios();
            model.addAttribute("usuarios", lista);

            return "redirect:/";
        } else
            return "redirect:/";

    }

    @GetMapping("/actualizar-usuario/{id}")
    public String actualizarUsuario(@PathVariable(name = "id") Integer id, @RequestParam String nickname,
            @RequestParam String estado, @RequestParam String email) {

        Usuario elusuario = usuario.findById(id).get();
        Estado state = elusuario.getEstadoCuenta();
        if (estado.equals(Estado.ALTA.toString()))
            state = Estado.ALTA;
        else
            state = Estado.BAJA;
        usuario.update(nickname, email, elusuario.getContrasenia(), state, id);
        return "redirect:/";
    }

    public Usuario usuarioLogueado() {
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

    public List<Usuario> listadoUsuarios() {
        List<Usuario> lista = usuario.listadoTotal();

        for (int i = 0; i < lista.size(); i++) { // o hacer otra consulta pasandolo el id
            if (lista.get(i).getId() == usuarioLogueado().getId())
                lista.remove(i);
        }
        return lista;
    }

    @PostMapping("/mensajesAdmin/guardar/{idUsuario}")
    public String guardarMensajeAdmin(@ModelAttribute("mensajeAdmin") MensajeAdmin elmensajeAdmin,
            @PathVariable("idUsuario") Integer idUsuario) {
        
        Usuario u = usuarioLogueado();
        Administrador a = (Administrador) u;
        
        elmensajeAdmin.setAdministrador(a);
        elmensajeAdmin.setEmisor(u);
        elmensajeAdmin.setFechayHoraEnvio(LocalDateTime.now());
        elmensajeAdmin.setUsuario(usuario.findById(idUsuario).get());

        mensajeS.saveMensajeAdmin(elmensajeAdmin);

       /*  if (getUsuario() instanceof Coordinador)
            return "redirect:/chatPrivado/{idParticipante}";
        else if (getUsuario() instanceof Participante)
            return "redirect:/chat";*/
        return "redirect:/mensajesAdmin";
    }

    @PostMapping("/mensajesAdmin2/guardar/{idAdministrador}")
    public String guardarMensajeAdmin2(@ModelAttribute("mensajeAdmin") MensajeAdmin elmensajeAdmin,
            @PathVariable("idAdministrador") Integer idAdministrador) {
        
        Usuario u = usuarioLogueado();
        Administrador a = administrador.getAdministrador(idAdministrador);
        
        elmensajeAdmin.setAdministrador(a);
        elmensajeAdmin.setEmisor(u);
        elmensajeAdmin.setFechayHoraEnvio(LocalDateTime.now());
        elmensajeAdmin.setUsuario(u);
        mensajeS.saveMensajeAdmin(elmensajeAdmin);

        if(u instanceof Coordinador){
            return "redirect:/chatCordAdmin/{idAdministrador}";
        }
        else{
            return "redirect:/chatPartAdmin/{idAdministrador}";
        }

     
    }

    
}
