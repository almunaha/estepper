package com.estepper.estepper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.estepper.estepper.model.entity.Grupo;
import com.estepper.estepper.model.entity.Usuario;
import com.estepper.estepper.model.enums.Estado;
import com.estepper.estepper.model.enums.Rol;
import com.estepper.estepper.repository.UsuarioRepository;
import com.estepper.estepper.service.UsuarioService;
import com.estepper.estepper.service.GrupoService;

@Controller
public class HomeController {

    @Autowired
    private UsuarioRepository repo; //inyección de dependencias del usuario dao api //ESTO NO SE PUEDE AQUÍ CREO

    @Autowired //inyectar recursos de la clase UsuarioService
    private UsuarioService usuario;

    @Autowired //inyectar recursos de la clase GrupoService
    private GrupoService grupo;

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

        Usuario user = usuario.logueado(codigo); //atributos edl logueado

        if(user.getRol().equals(Rol.COORDINADOR)) return "coordinador";
        else if(user.getRol().equals(Rol.ADMINISTRADOR)){
            List<Usuario> lista = usuario.listado();
            model.addAttribute("usuarios", lista);
            return "admin";
        } 
        
        else {
            List<Grupo> listaGrupos = grupo.listaGrupos();
            model.addAttribute("grupos", listaGrupos);
            return "index";
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
    
    @GetMapping("/register")  //mostrar el formulario de registro
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Usuario());
                
        return "registro";
    }

    @PostMapping("/process_register") //Procesar el registro
    public String processRegister(Usuario user, Model model) { //Model para poder enviar información a la vista
        hash = new BCryptPasswordEncoder();
        String encodedPassword = hash.encode(user.getContrasenia());
        user.setContrasenia(encodedPassword);

        user.setEstadoCuenta(Estado.BAJA);
        user.setRol(Rol.PARTICIPANTE);
        
        repo.save(user);

        model.addAttribute("nombre", user.getNombre());
        model.addAttribute("codigo", user.getCodigo());
        model.addAttribute("estadoCuenta", user.getEstadoCuenta());        
        
        return "register_success";
    }
    
}
