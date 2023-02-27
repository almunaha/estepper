package com.estepper.estepper.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.time.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.estepper.estepper.model.entity.Administrador;
import com.estepper.estepper.model.entity.Coordinador;
import com.estepper.estepper.model.entity.Progreso;

import com.estepper.estepper.model.entity.Materiales;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Usuario;
import com.estepper.estepper.model.enums.Estado;
import com.estepper.estepper.model.enums.TipoProgreso;
import com.estepper.estepper.service.UsuarioService;

import com.estepper.estepper.service.ParticipanteService;
import com.estepper.estepper.service.FaseValoracionService;
import com.estepper.estepper.service.GrupoService;
import com.estepper.estepper.service.ProgresoService;

@Controller
public class HomeController {

    @Autowired // inyectar recursos de la clase UsuarioService
    private UsuarioService usuario;

    @Autowired
    private FaseValoracionService fasevaloracion;

    @Autowired
    private ParticipanteService participante;

    @Autowired
    private ProgresoService progreso;

    @Autowired
    private BCryptPasswordEncoder hash;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String index(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }

        String codigo = userDetails.getUsername(); // codigo del logueado

        Usuario user = usuario.logueado(Integer.parseInt(codigo)); // atributos del logueado
        model.addAttribute("user", user);
        if (user instanceof Coordinador) {
            return "coordinador";
        }

        else if (user instanceof Administrador) {
            List<Usuario> lista = usuario.listadoTotal();
            model.addAttribute("usuarios", lista);
            return "admin";
        } else { // PARTICIPANTE

            // si está dado de alta
            if (user.getEstadoCuenta().equals(Estado.ALTA)) {
                Optional<Participante> part = participante.findById(user.getId());
                if (part.isPresent()){
                        model.addAttribute("participante", part.get());
                        //enviar alerta de peso
                        LocalDate datosSemana = LocalDate.now().minusDays(7); //Buscar fechas de una semana atrás
                        Date fechaSemana = Date.from(datosSemana.atStartOfDay(ZoneId.systemDefault()).toInstant());
                        List<Progreso> datos = progreso.PesoPorFecha(fechaSemana, TipoProgreso.PESO, part.get());

                        if (datos.isEmpty()) {
                            model.addAttribute("mostrarAlerta", true);
                        }
                    }

                return "index";
            }
            // si está de baja
            else
                return "baja";
        }
    }

    @GetMapping("/findrisc")
    public String test() {
        return "findrisc";
    }

    @GetMapping("/recomendaciones")
    public String recomendaciones() {
        return "recomendaciones";
    }

    @GetMapping("/perfil/{id}")
    public String perfil(@PathVariable("id") Integer id, Model model) {
        if(id != getUsuario().id) id= getUsuario().id; //mejor no pasar ids para evitar errores, cambiarlo más adelante

        Usuario elusuario = usuario.findById(id).get();
        model.addAttribute("user", elusuario);
        if (elusuario instanceof Participante) {
            model.addAttribute("participante", participante.findById(id).get());
            return "editarperfilParticipante";
        } else
            return "editarperfil";

    }

    @GetMapping("/mostrarperfil/{id}")
    public String mostrarperfil(@PathVariable("id") Integer id, Model model) {
        
        if(id != getUsuario().id) id= getUsuario().id; //mejor no pasar ids para evitar errores

        Usuario elusuario = usuario.findById(id).get();
        model.addAttribute("user", elusuario);
        if (elusuario instanceof Participante) {
            Participante p = participante.findById(id).get();
            model.addAttribute("participante", p);

            // Nombre del grupo
            String grupo = "No asignado";

            if (p.getGrupo() != null)
                grupo = p.getGrupo().getNombre();

            model.addAttribute("grupo", grupo);

            return "mostrarperfilParticipante";
        } else
            return "mostrarperfil";

    }

    @PostMapping("/process_perfil/{id}")
    public String processPerfil(@PathVariable("id") Integer id, @ModelAttribute Usuario user,
            @ModelAttribute Participante p) {

        Usuario orig = usuario.findById(user.id).get(); // usuario antes de editarlo

        if (user.contrasenia == "")
            user.contrasenia = orig.contrasenia; // si no se ha cambiado la contraseña
        else
            user.contrasenia = hash.encode(user.contrasenia);

        usuario.update(user.nickname, user.email, user.contrasenia, orig.estadoCuenta, orig.id);

        if (orig instanceof Participante) { // si es un participante
            Participante part = participante.findById(id).get();

            participante.update(p.edad, p.sexo, p.getFotoParticipante(), part.getGrupo(), part.getAsistencia(),
                    part.getIdCoordinador(), part.getPerdidaDePeso(), part.getSesionesCompletas(), id);
        }

        return "redirect:/mostrarperfil/{id}";
    }

    @GetMapping("/baja")
    public String baja() {
        if(!getUsuario().estadoCuenta.equals(Estado.BAJA)) return "redirect:/";
        return "baja";
    }

    @GetMapping("/register") // mostrar el formulario de registro
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Usuario());
        return "registro";
    }

    @PostMapping("/process_register") // Procesar el registro
    public String processRegister(Participante user, Model model) { // Model para poder enviar información a la vista

        hash = new BCryptPasswordEncoder();
        String encodedPassword = hash.encode(user.getContrasenia());
        user.setContrasenia(encodedPassword);

        Integer elcodigo = new Random().nextInt(100000 + 1);

        // comprobar que no existe un usuario con ese codigo
        while (usuario.logueado(elcodigo) != null) {
            elcodigo = new Random().nextInt(100000 + 1);
        }

        user.setCodigo(elcodigo);
        user.setEstadoCuenta(Estado.BAJA);
        user.setFotoParticipante("/img/p1.png");

        usuario.guardar(user);

        fasevaloracion.crearFormularios(participante.findById(user.id).get());

        model.addAttribute("nickname", user.getNickname());
        model.addAttribute("codigo", user.getCodigo());
        model.addAttribute("estadoCuenta", user.getEstadoCuenta());

        return "register_success";
    }

    @GetMapping("/recuperarcodigo")
    public String recuperarCodigo() {
        return "recuperarCodigo";
    }

    @PostMapping("/process_recuperarCodigo")
    public String procesoRecuperarCodigo(@RequestParam(value = "correo") String correo) {
        usuario.recuperarCodigo(correo);
        return "redirect:/login";
    }

    // MATERIALES:
    @GetMapping("/materiales/{id}")
    public String mostrarMateriales(@PathVariable("id") Integer id, Model model) {
        Usuario elusuario = getUsuario();
        model.addAttribute("user", elusuario);
        if (elusuario instanceof Coordinador) {
            model.addAttribute("listado", participante.materiales(id));
            Materiales material = new Materiales();
            model.addAttribute("material", material);
            model.addAttribute("id", id);
            return "materialesCoor";
        } else {
            model.addAttribute("listado", participante.materiales(id));
            return "materialesPart";
        }
    }

    @PostMapping("/process_material/{id}")
    public String procesoMaterial(@PathVariable("id") Integer id, @ModelAttribute Materiales material,
            @RequestParam(value = "enlace") MultipartFile enlace) {
        // material.setLink("materiales/" + enlace.getOriginalFilename());
        material.setParticipante(participante.findById(id).get());
        material.setGrupo(material.getParticipante().getGrupo());
        participante.updateMaterial(material);
        // Path rutaArchivo = Paths.get("materiales/" + enlace.getOriginalFilename());
        // try{Files.write(rutaArchivo, enlace.getBytes());}catch (IOException e) {
        // Manejar la excepción
        // }
        return "redirect:/";
    }

    public Usuario getUsuario() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }

        String codigo = userDetails.getUsername(); // codigo del logueado

        Usuario user = usuario.logueado(Integer.parseInt(codigo));
        return user;
    }
}
