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
   
    @GetMapping("/")
    public String index(){
     return "index";
    }

    @GetMapping("/register")  //mostrar el formulario de registro
    public String showRegistrationForm(Model model) {
    model.addAttribute("user", new Usuario());
            
    return "registro";
    }

    @PostMapping("/process_register") //Procesar el registro
    public String processRegister(Usuario user) {
    hash = new BCryptPasswordEncoder();
    String encodedPassword = hash.encode(user.getContrasenia());
    user.setContrasenia(encodedPassword);

    user.setEstadoCuenta(Estado.BAJA);
    user.setRol(Rol.PARTICIPANTE);
     
    repo.save(user);
     
     return "register_success";
    }

    @GetMapping("/contacto_correo")
    public String contactoCorreo(){
     return "contacto_correo";
    }

    @GetMapping("/contacto_telefono")
    public String contactoTelefono(){
     return "contacto_telefono";
    }
    
}
