package com.estepper.estepper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.estepper.estepper.model.entity.Usuario;
import com.estepper.estepper.model.enums.Estado;
import com.estepper.estepper.model.enums.Rol;
import com.estepper.estepper.repository.UsuarioRepository;

@Controller
public class HomeController {

    @Autowired
    private UsuarioRepository repo; //inyección de dependencias del usuario dao api

    @Autowired
	private BCryptPasswordEncoder hash;
   
    @GetMapping("/login")
    public String login(){
        return "login";
    }     

    @GetMapping("/")
    public String index(){
  
        return "index";
    }

    @GetMapping("/findrisc")
    public String test(){
        return "findrisc";
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
