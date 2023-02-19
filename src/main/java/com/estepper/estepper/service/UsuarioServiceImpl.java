package com.estepper.estepper.service;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.estepper.estepper.model.entity.Usuario;
import com.estepper.estepper.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService{

    @Autowired
    private UsuarioRepository repo; //inyección de dependencias del usuario dao api

    final String correoEstepper = "proyectoestepper@gmail.com";
    final String contrasenia = "estepperPAI2023";

    public UserDetails loadUserByUsername(String codigo) throws UsernameNotFoundException {
        
        Usuario user = repo.findByCodigo(Integer.parseInt(codigo));
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }

    @Override
    public void guardar(Usuario u){
        repo.save(u);
    }

    @Override
    public List<Usuario> listadoTotal(){
        return(List<Usuario>) repo.findAll();
    }

    @Override
    public Usuario logueado(Integer codigo){
        return repo.findByCodigo(codigo);
    }

    @Override
    public Optional<Usuario> findById(Integer id) {
        return repo.findById(id);
    }

    @Override
    public void update(String nickname, String email, String contrasenia, Integer id) {
        repo.update(nickname, email, contrasenia, id);
    }

    @Override
    public void eliminar(Integer id){
        repo.deleteById(id);
    }

    @Override
    public void recuperarCodigo(String correo) {
        Usuario usuario = repo.findByEmail(correo);
        String texto = "";
        if(usuario != null){
            texto = "Le remitimos sus datos: Código --> " + usuario.getCodigo() + " Contraseña -->" + usuario.getContrasenia();
            texto += "Si desea cambiar su contraseña acceda a Mi Perfil y edite sus datos.";
        } else {
            texto = "Ha intentado recuperar sus datos de Estepper sin estar registrado. Acceda a estepper.com para registrarse.";
        }
        mandarcorreo(correo, texto);
       
    }

    private void mandarcorreo(String correo, String texto) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(correoEstepper, contrasenia);
            }
        });

        try {

            // Define message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correoEstepper));
            message.setSubject("Recuperación de datos");
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(correo));
            message.setText(texto);
            // Envia el mensaje
            Transport.send(message);
        } catch (Exception e) {
        }
                
    }
}
