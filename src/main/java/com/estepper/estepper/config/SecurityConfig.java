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

   /*@Autowired
   private BCryptPasswordEncoder hash;*/

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //auth.userDetailsService(usuarioDetailsService).passwordEncoder(hash);
        auth.userDetailsService(usuarioDetailsService);
    }

    
   @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{
        http           
            .authorizeHttpRequests()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            //especificar nombre pagina del login personalizado 
            .loginPage("/login")
            //url del login
            .loginProcessingUrl("/autenticacion")
            //permitir que todos puedan ver esta página
            .permitAll()
            //permitir logout a todos
            .and().logout().permitAll();
            ;
        /*http  redirigir según el rol del usuario 
            .authorizeHttpRequests()
            .requestMatchers("/").hasRole("participante")
            .and()
            .formLogin();
*/

        return http.build();

    }
   
    
}
