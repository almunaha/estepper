package com.estepper.estepper.controller;

import java.util.List;
import java.util.Random;

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
import com.estepper.estepper.model.entity.Usuario;
import com.estepper.estepper.model.enums.Estado;
import com.estepper.estepper.service.UsuarioService;

import org.springframework.ui.Model;

@Controller
public class AdminController {

    @Autowired
    private UsuarioService usuario;

    @Autowired
	private BCryptPasswordEncoder hash;

    @GetMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable(name = "id") Integer id, Model model){
        //eliminar usuario
        usuario.eliminar(id);

        //pasar usuario logueado y listado
        model.addAttribute("user", usuarioLogueado());

        List<Usuario> lista = listadoUsuarios();
        model.addAttribute("usuarios", lista);
       
        return "admin";
    }

    @GetMapping("/nuevoCoordinador")
        public String nuevoCoordinador(Model model){
            Usuario u = usuarioLogueado();
            model.addAttribute("user", u);

            model.addAttribute("coordinador", new Coordinador());

        return "nuevoCoordinador";
    }

    @PostMapping("/process_coordinador")
    public String crear(@ModelAttribute Coordinador coordinador, Model model){
        hash = new BCryptPasswordEncoder();
        String encodedPassword = hash.encode(coordinador.getContrasenia());
        coordinador.setContrasenia(encodedPassword);

        Integer elcodigo = new Random().nextInt(100000 + 1);

        //comprobar que no existe un usuario con ese codigo
        while(usuario.logueado(elcodigo) != null){
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

    public Usuario usuarioLogueado(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }

        String codigo = userDetails.getUsername(); //codigo del logueado

        Usuario user = usuario.logueado(Integer.parseInt(codigo)); //atributos del logueado
        return user;
    }

    public List <Usuario> listadoUsuarios(){
        List<Usuario> lista = usuario.listadoTotal();

        for(int i=0; i<lista.size(); i++){ //o hacer otra consulta pasandolo el id
            if(lista.get(i).id == usuarioLogueado().id) lista.remove(i);
        }
        return lista;
    }



    
}
