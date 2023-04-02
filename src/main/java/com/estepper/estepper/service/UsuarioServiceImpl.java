package com.estepper.estepper.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.tomcat.util.codec.binary.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.estepper.estepper.model.entity.Usuario;

import com.estepper.estepper.repository.UsuarioRepository;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePart;
import com.estepper.estepper.model.enums.Estado;

@Service
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService{

    @Autowired
    private UsuarioRepository repo; //inyección de dependencias del usuario dao api

    final String correoEstepper = "proyectoestepper@gmail.com";

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
    public Usuario findByCodigo(Integer codigo){
        return repo.findByCodigo(codigo);
    }

    @Override
    public void update(String nickname, String email, String contrasenia, Estado estado, Integer id) {
        repo.update(nickname, email, contrasenia, estado, id);
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
        try{
        mandarcorreo(correo, texto);}catch(Exception e){e.printStackTrace();}
       
    }

    private static Credential getCredentials(final NetHttpTransport httpTransport, GsonFactory jsonFactory) throws IOException{
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory, new InputStreamReader(UsuarioServiceImpl.class.getResourceAsStream("/client_secret_997788153381-j7h2r75bek6g35no8jmsmfq729cgc1g1.apps.googleusercontent.com.json")));
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory, clientSecrets, Set.of(GmailScopes.GMAIL_SEND))
        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
        .setAccessType("offline")
        .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        return credential;
    }
    private void mandarcorreo(String correo, String bodyText) throws Exception {
        String messageSubject = "Recuperación de datos - Estepper";
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        Gmail service = new Gmail.Builder(httpTransport, jsonFactory, getCredentials(httpTransport, jsonFactory))
            .setApplicationName("Estepper")
            .build();
            Properties props = new Properties();
            Session session = Session.getDefaultInstance(props);
            // Define message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correoEstepper));
            message.setSubject(messageSubject);
            message.addRecipient(javax.mail.Message.RecipientType.TO,new InternetAddress(correo));
            message.setText(bodyText);
    
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            message.writeTo(buffer);
            byte[] rawMessageBytes = buffer.toByteArray();
            String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
            Message email = new Message();
                email.setRaw(encodedEmail);
        try {
            email = service.users().messages().send("me", email).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
                
    }

    
}
