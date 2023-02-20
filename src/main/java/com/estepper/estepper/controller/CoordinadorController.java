package com.estepper.estepper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.estepper.estepper.model.entity.Participante;

import com.estepper.estepper.model.entity.Sesion;
import com.estepper.estepper.model.enums.Asistencia;
import com.estepper.estepper.model.enums.Estado;
import com.estepper.estepper.model.enums.EstadoSesion;

import com.estepper.estepper.model.entity.Usuario;
import com.estepper.estepper.model.entity.Grupo;

import com.estepper.estepper.service.ParticipanteService;
import com.estepper.estepper.service.SesionService;
import com.estepper.estepper.service.UsuarioService;
import com.estepper.estepper.service.GrupoService;


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

    @GetMapping("/listado")
    public String participantes(Model model) {
        List<Participante> listado = part.listado();
        model.addAttribute("listado", listado);
        model.addAttribute("user", getUsuario());
        return "participantes";
    }

    @GetMapping("/valoracion/{id}")
    public String fasedevaloracion(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("participante", part.findById(id).get());
        model.addAttribute("user", getUsuario());
        model.addAttribute("idparticipante", id);
        return "valoracion";
    }



    @GetMapping("/actualizarGrupos/{idP}/{idG}")
    public String actualizarGrupos(@PathVariable(name = "idP") Integer idP, @PathVariable(name = "idG") Integer idG,
            Model model) {
        Participante usuario = part.findById(idP).get();
        model.addAttribute("user", getUsuario());
        Grupo g = grupo.getGrupo(idG);

        if(usuario.getEstadoCuenta() == Estado.ALTA){

            if (usuario.getIdGrupo() == idG) { // El usuario ya está en ese grupo

                // mandar alerta ajax que diga que el usuario ya está en ese grupo
            } else if (usuario.getIdGrupo() == 0) { // El usuario aún no está en ningún grupo
                part.update(usuario.edad, usuario.sexo, usuario.getFotoParticipante(), g, usuario.getAsistencia(), usuario.getIdCoordinador(), usuario.getPerdidaDePeso(), usuario.getSesionesCompletas(), idP);
                Integer participantes = g.getNumParticipantes() + 1;
                grupo.updateParticipantes(idG, participantes);

                // crear las sesiones del participante
                Sesion s;
                for (int i = 1; i <= 10; i++) {
                s = new Sesion(0, i, usuario, EstadoSesion.BLOQUEADA, "", Asistencia.NO, 0, 0);
                sesion.guardar(s);
                
                }
            } else if ((usuario.getIdGrupo() != idG) && (usuario.getIdGrupo() != null)) { // El usuario ya está en un grupo
                                                                                        // distinto al que le quieres
                                                                                        // añadir
                // mandar alerta ajax que diga que el usuario ya pertenece a otro grupo
            }
        }
        else{ //El usuario no está dado de alta asique no se le puede añadir a un grupo
            //mandar alera ajax que diga que no se puede añadir a un grupo a un usuario que no está dado de alta
        }

        return "redirect:/listaGrupos";
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
