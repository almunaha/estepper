package com.estepper.estepper.controller;

import java.util.List;
import java.util.Map;

import java.util.stream.IntStream;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import com.estepper.estepper.model.entity.Participante;

import com.estepper.estepper.model.entity.Sesion;
import com.estepper.estepper.model.enums.Asistencia;
import com.estepper.estepper.model.enums.Estado;
import com.estepper.estepper.model.enums.EstadoSesion;

import com.estepper.estepper.model.entity.Usuario;
import com.estepper.estepper.model.entity.Coordinador;
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
    public String participantes(@RequestParam Map<String, Object> params, Model model) {

        if (getUsuario() instanceof Coordinador) { // controlar acceso

            int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
            PageRequest pageable = PageRequest.of(page, 6); // define página solicitada y tamaño de la página, se
                                                            // inicializa a cero
            Page<Participante> paginaPart = part.paginas(pageable); // listado de páginas de 6 participantes cada una
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
            model.addAttribute("listado", listado);
            model.addAttribute("user", getUsuario());

            return "participantes";
        }

        else
            return "redirect:/";
    }

    @GetMapping("/valoracion/{id}")
    public String fasedevaloracion(@PathVariable("id") Integer id, Model model) {
        // realmente si es su coordinador tiene que aparecerle que si está dado de alta
        // le aparezcan sus datos pero le tiene que dejar entrar
        if ((getUsuario() instanceof Coordinador)
                && ((part.findById(id).get().getIdCoordinador() == getUsuario().getId())
                        || part.findById(id).get().estadoCuenta.equals(Estado.BAJA))) {
            model.addAttribute("participante", part.findById(id).get());
            model.addAttribute("usuario", user.findById(id).get());
            model.addAttribute("user", getUsuario());
            model.addAttribute("idparticipante", id);
            return "valoracion";
        }

        else
            return "redirect:/";
    }

    @GetMapping("/actualizarGrupos/{idP}/{idG}")
    public String actualizarGrupos(@PathVariable(name = "idP") Integer idP, @PathVariable(name = "idG") Integer idG,
            Model model) {

        if (getUsuario() instanceof Coordinador) {
            Participante usuario = part.findById(idP).get();
            model.addAttribute("user", getUsuario());
            Grupo g = grupo.getGrupo(idG);

            part.update(usuario.edad, usuario.sexo, usuario.getFotoParticipante(), g, usuario.getAsistencia(),
                    usuario.getIdCoordinador(), usuario.getPerdidaDePeso(), usuario.getSesionesCompletas(), idP);
            Integer participantes = g.getNumParticipantes() + 1;
            grupo.updateParticipantes(idG, participantes);

            // crear las sesiones del participante
            Sesion s;
            for (int i = 1; i <= 10; i++) {
                s = new Sesion(0, i, usuario, EstadoSesion.BLOQUEADA, "", Asistencia.NO, 0, 0);
                sesion.guardar(s);
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

            part.update(usuario.edad, usuario.sexo, usuario.getFotoParticipante(), null, usuario.getAsistencia(),
                    usuario.getIdCoordinador(), usuario.getPerdidaDePeso(), usuario.getSesionesCompletas(), idP);

            Integer participantes = g.getNumParticipantes() - 1;
            grupo.updateParticipantes(idG, participantes);
            if (g.getNumParticipantes() <= 1) {
                grupo.delete(idG);
                return "redirect:/listaGrupos";
            }

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

        String codigo = userDetails.getUsername(); // codigo del logueado

        Usuario usuario = user.logueado(Integer.parseInt(codigo));
        return usuario;
    }
}
