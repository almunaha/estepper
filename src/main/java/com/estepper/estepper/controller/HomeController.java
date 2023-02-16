package com.estepper.estepper.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

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

import com.estepper.estepper.model.entity.Administrador;
import com.estepper.estepper.model.entity.Coordinador;
import com.estepper.estepper.model.entity.Grupo;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Usuario;
import com.estepper.estepper.model.enums.Estado;
import com.estepper.estepper.service.UsuarioService;
import com.estepper.estepper.service.ParticipanteService;
import com.estepper.estepper.service.GrupoService;

@Controller
public class HomeController {

    @Autowired //inyectar recursos de la clase UsuarioService
    private UsuarioService usuario;

    @Autowired //inyectar recursos de la clase GrupoService
    private GrupoService grupo;

    @Autowired
    private ParticipanteService participante; 

    @Autowired
	private BCryptPasswordEncoder hash;
   
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/")
    public String index(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }

        String codigo = userDetails.getUsername(); //codigo del logueado

        Usuario user = usuario.logueado(Integer.parseInt(codigo)); //atributos del logueado
        model.addAttribute("user", user);
        if(user instanceof Coordinador){
            return "coordinador";
        }

        else if(user instanceof Administrador){
            List<Usuario> lista = usuario.listadoTotal();
            model.addAttribute("usuarios", lista);
            return "admin";
        }

        else{
            List<Grupo> listaGrupos = grupo.listaGrupos();
            model.addAttribute("grupos", listaGrupos);
            if(user.getEstadoCuenta().equals(Estado.ALTA)){
                Optional<Participante> part = participante.findById(user.getId());
                if(part.isPresent()) model.addAttribute("participante", part.get());
                return "index";
            }
            else return "baja";
        }        
    }

     @GetMapping("/findrisc")
    public String test(){
        return "findrisc";
    }


    @GetMapping("/recomendaciones")
    public String recomendaciones(){
        return "recomendaciones";
    }

    @GetMapping("/perfil/{id}")
    public String perfil(@PathVariable("id") Integer id, Model model){
        Usuario elusuario = usuario.findById(id).get();
        model.addAttribute("user", elusuario);
        if(elusuario instanceof Participante) {
            model.addAttribute("participante",  participante.findById(id).get());
            return "editarperfilParticipante";
        }
        else return "editarperfil";
        
    }    

    @GetMapping("/mostrarperfil/{id}")
    public String mostrarperfil(@PathVariable("id") Integer id, Model model){
        Usuario elusuario = usuario.findById(id).get();
        model.addAttribute("user", elusuario);
        if(elusuario instanceof Participante) {
            model.addAttribute("participante",  participante.findById(id).get());
            return "mostrarperfilParticipante";
        }
        else return "mostrarperfil";
        
    }    

    @PostMapping("/process_perfil/{id}")
    public String processPerfil(@PathVariable("id") Integer id, @ModelAttribute Usuario user, @ModelAttribute Participante p) {
        
        usuario.update(user.nickname, user.email, hash.encode(user.contrasenia), user.id);

        if(p!=null) { 
            participante.updateParticipante(p.edad, p.sexo, id);
        }

       return "redirect:/";
    }

    @GetMapping("/baja")
    public String baja(){
        return "baja";
    }

    @GetMapping("/register")  //mostrar el formulario de registro
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Usuario());
                
        return "registro";
    }

    @PostMapping("/process_register") //Procesar el registro
    public String processRegister(Participante user, Model model) { //Model para poder enviar información a la vista
        hash = new BCryptPasswordEncoder();
        String encodedPassword = hash.encode(user.getContrasenia());
        user.setContrasenia(encodedPassword);
        Integer elcodigo = new Random().nextInt(100000 + 1);
        while(usuario.logueado(elcodigo) != null){
            elcodigo = new Random().nextInt(100000 + 1);
        }
        user.setCodigo(elcodigo);
        user.setEstadoCuenta(Estado.BAJA);
        
        usuario.guardar(user); 

        model.addAttribute("nickname", user.getNickname());
        model.addAttribute("codigo", user.getCodigo());
        model.addAttribute("estadoCuenta", user.getEstadoCuenta());        
        
        return "register_success";
    }
    
    @GetMapping("/recuperarcodigo")
    public String recuperarCodigo(){
        return "recuperarCodigo";
    }

    @PostMapping("/process_recuperarCodigo")
    public String procesoRecuperarCodigo(@RequestParam(value = "correo") String correo){
        usuario.recuperarCodigo(correo);
        return "redirect:/login";
    }
}
