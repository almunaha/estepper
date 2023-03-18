package com.estepper.estepper.controller;

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

import com.estepper.estepper.model.entity.Coordinador;
import com.estepper.estepper.model.entity.Grupo;
import com.estepper.estepper.model.entity.Usuario;
import com.estepper.estepper.model.entity.Administrador;
import com.estepper.estepper.model.entity.Participante;

import com.estepper.estepper.model.enums.Estado;

import com.estepper.estepper.service.AlimentacionService;
import com.estepper.estepper.service.FaseValoracionService;
import com.estepper.estepper.service.FichaService;
import com.estepper.estepper.service.UsuarioService;
import com.estepper.estepper.service.MaterialService;
import com.estepper.estepper.service.MensajeService;
import com.estepper.estepper.service.ParticipanteService;
import com.estepper.estepper.service.SesionService;
import com.estepper.estepper.service.GrupoService;
import com.estepper.estepper.service.ObjetivoService;
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
    private ParticipanteService participante;
    
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
                fasevaloracion.eliminarcuenta(p);
                
                p.getGrupo().setNumParticipantes(p.getGrupo().getNumParticipantes()-1);
                grupoS.update(p.getGrupo());
            }

            else if (usuario.findById(id).get() instanceof Coordinador) {
                List<Grupo> listgrupos = grupoS.getGrupos();
                for(int i = 0; i < listgrupos.size(); i++){
                    if(listgrupos.get(i).getIdCoordinador() == id){
                        materialS.deleteByGrupo(listgrupos.get(i));
                        mensajeS.deleteByGrupo(listgrupos.get(i));
                        grupoS.delete(id);
                    }
                }
            }
            else 
                usuario.eliminar(id);

            // pasar usuario logueado y listado
            model.addAttribute("user", usuarioLogueado());

            List<Usuario> lista = listadoUsuarios();
            model.addAttribute("usuarios", lista);
        }

        return "redirect:/";
    }

    @GetMapping("/nuevoCoordinador")
    public String nuevoCoordinador(Model model) {
        Usuario u = usuarioLogueado();

        if (u instanceof Administrador) {
            model.addAttribute("user", u);
            model.addAttribute("coordinador", new Coordinador());
            return "nuevoCoordinador";
        } else
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
            coordinador.setEstadoCuenta(Estado.BAJA);

            usuario.guardar(coordinador);

            model.addAttribute("user", usuarioLogueado());

            List<Usuario> lista = listadoUsuarios();
            model.addAttribute("usuarios", lista);

            return "admin";
        }
        else return "redirect:/";

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

}
