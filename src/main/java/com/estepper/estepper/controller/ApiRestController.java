package com.estepper.estepper.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.estepper.estepper.model.entity.Usuario;
import com.estepper.estepper.model.entity.Progreso;
import com.estepper.estepper.model.enums.TipoProgreso;
import com.estepper.estepper.model.entity.Participante;

import com.estepper.estepper.service.UsuarioService;
import com.estepper.estepper.service.ParticipanteService;
import com.estepper.estepper.service.ProgresoService;

//Controlador de recursos
//Controlador para API REST que se encarga de las solicitudes HTTP entrantes para luego llamar a un servicio
//y coger los datos de la base de datos
@RestController
public class ApiRestController {

    @Autowired
    private UsuarioService usuario;

    @Autowired
    private ParticipanteService part;

    @Autowired
    private ProgresoService progreso;

    // Función que realiza una petición http devolviendo un json con los datos de
    // progreso del participante correspondiente
    // Además según los valores de ini o fin, que son fecha de inicio y fecha de
    // fin, se filtrarán los datos del progreso
    // pueden venir vacíos por lo que se mostrarán todos los datos
    @GetMapping("/progreso/peso/{id}/{ini}/{fin}")
    public List<Progreso> datosPeso(@PathVariable Integer id, @PathVariable String ini, @PathVariable String fin) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Define el formato del string
        LocalDate fechaIni, fechaFin;

        Participante p = part.findById(id).get();

        if (ini.equals("0") && fin.equals("0")) { // ninguna fecha seleccionada -> no se aplica filtro
            return progreso.datos(p, TipoProgreso.PESO);
        }

        else if (ini != "0" && fin.equals("0")) { // fecha ini seleccionada
            fechaIni = LocalDate.parse(ini, formatter); // Convierte el string a LocalDate
            LocalDateTime iniDate = fechaIni.atStartOfDay();

            return progreso.PesoPorFecha(iniDate, TipoProgreso.PESO, p);
        }

        else if (ini.equals("0") && fin != "0") { // fecha fin seleccionada
            fechaFin = LocalDate.parse(fin, formatter); // Convierte el string a LocalDate
            LocalDateTime finDate = fechaFin.atStartOfDay().with(LocalTime.MAX); // para coger valores hasta las
                                                                                 // 23:59:59

            return progreso.PesoPorFechaAntes(finDate, TipoProgreso.PESO, p);
        }

        else { // los dos filtros
            fechaIni = LocalDate.parse(ini, formatter); // Convierte el string a LocalDate
            LocalDateTime iniDate = fechaIni.atStartOfDay();

            fechaFin = LocalDate.parse(fin, formatter); // Convierte el string a LocalDate
            LocalDateTime finDate = fechaFin.atStartOfDay().with(LocalTime.MAX); // para coger valores hasta las
                                                                                 // 23:59:59

            return progreso.datoFechas(p, TipoProgreso.PESO, iniDate, finDate);
        }

    }

    // Función que realiza una petición http devolviendo un json con los datos de
    // perímetro del participante correspondiente
    // Además según los valores de ini o fin, que son fecha de inicio y fecha de
    // fin, se filtrarán los datos del perímetro
    // pueden venir vacíos por lo que se mostrarán todos los datos
    @GetMapping("/progreso/perimetro/{id}/{ini}/{fin}")
    public List<Progreso> datosPerimetro(@PathVariable Integer id, @PathVariable String ini, @PathVariable String fin) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Define el formato del string
        LocalDate fechaIni, fechaFin;

        Participante p = part.findById(id).get();

        if (ini.equals("0") && fin.equals("0")) { // ninguna fecha seleccionada -> no se aplica filtro
            return progreso.datos(p, TipoProgreso.PERIMETRO);
        }

        else if (ini != "0" && fin.equals("0")) { // fecha ini seleccionada
            fechaIni = LocalDate.parse(ini, formatter); // Convierte el string a LocalDate
            LocalDateTime iniDate = fechaIni.atStartOfDay();

            return progreso.PesoPorFecha(iniDate, TipoProgreso.PERIMETRO, p);
        }

        else if (ini.equals("0") && fin != "0") { // fecha fin seleccionada
            fechaFin = LocalDate.parse(fin, formatter); // Convierte el string a LocalDate
            LocalDateTime finDate = fechaFin.atStartOfDay().with(LocalTime.MAX); // para coger valores hasta las
                                                                                 // 23:59:59

            return progreso.PesoPorFechaAntes(finDate, TipoProgreso.PERIMETRO, p);
        }

        else { // los dos filtros
            fechaIni = LocalDate.parse(ini, formatter); // Convierte el string a LocalDate
            LocalDateTime iniDate = fechaIni.atStartOfDay();

            fechaFin = LocalDate.parse(fin, formatter); // Convierte el string a LocalDate
            LocalDateTime finDate = fechaFin.atStartOfDay().with(LocalTime.MAX); // para coger valores hasta las
                                                                                 // 23:59:59

            return progreso.datoFechas(p, TipoProgreso.PERIMETRO, iniDate, finDate);
        }

    }

    public Usuario getUsuario() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }

        if (userDetails != null) {
            String codigo = userDetails.getUsername(); // codigo del logueado

            Usuario user = usuario.logueado(Integer.parseInt(codigo)); // atributos del logueado
            return user;
        }
        return null;
    }

    @GetMapping("/index/sesiones")
    public Participante getParticipante() {

        Usuario u = getUsuario();
        if (u instanceof Participante) {
            Participante p = part.findById(u.getId()).get();
            return p;
        }

        return null;
    }

}
