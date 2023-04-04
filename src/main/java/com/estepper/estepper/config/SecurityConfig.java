package com.estepper.estepper.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.estepper.estepper.service.UsuarioServiceImpl;


@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Autowired
    private UsuarioServiceImpl usuarioDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(usuarioDetailsService);
    }
    
   @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{
       http
               .authorizeHttpRequests()
               .requestMatchers("/register**").permitAll()
               .requestMatchers("/img/logo.png").permitAll()
               .requestMatchers("/process_register").permitAll()
               .requestMatchers("/process_recuperarCodigo").permitAll()
               .requestMatchers("/recuperarcodigo").permitAll()
               .requestMatchers("/findrisc").permitAll()
               .requestMatchers("/recomendaciones").permitAll()
               .requestMatchers("/img/agua.png").permitAll()
               .requestMatchers("/img/alimentacion.png").permitAll()
               .requestMatchers("/img/deporte.png").permitAll()
               .requestMatchers("/img/descanso.png").permitAll()
               .requestMatchers("/js/validaciones.js").permitAll()
               .requestMatchers("/css/out.css").permitAll()
               .requestMatchers("/client_secret_997788153381-j7h2r75bek6g35no8jmsmfq729cgc1g1.apps.googleusercontent.com.json").permitAll()

               .requestMatchers("/material/descargar/**").permitAll() // permitir acceso a todos

               .requestMatchers("/img/elecciones/**").permitAll()


               .anyRequest()
               .authenticated()
               .and()
               .formLogin(login -> login
                       //especificar nombre pagina del login personalizado 
                       .loginPage("/login")
                       //url del login
                       .loginProcessingUrl("/autenticacion")
                       //permitir que todos puedan ver esta página
                       .permitAll()).logout(logout -> logout.permitAll());
            ;
       http.headers(headers -> headers.frameOptions().disable());

        return http.build();

    }
   
    
}
