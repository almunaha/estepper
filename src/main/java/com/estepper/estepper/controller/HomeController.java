package com.estepper.estepper.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.swing.JOptionPane;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.*;

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
import org.springframework.web.multipart.MultipartFile;

import com.estepper.estepper.model.entity.Administrador;
import com.estepper.estepper.model.entity.Coordinador;
import com.estepper.estepper.model.entity.FichaObjetivo;
import com.estepper.estepper.model.entity.Progreso;
import com.estepper.estepper.model.entity.Materiales;
import com.estepper.estepper.model.entity.MensajeAdmin;
import com.estepper.estepper.model.entity.Notificacion;
import com.estepper.estepper.model.entity.ObjetivoAgua;
import com.estepper.estepper.model.entity.ObjetivoDescanso;
import com.estepper.estepper.model.entity.ObjetivoEjercicio;
import com.estepper.estepper.model.entity.ObjetivoEstadoAnimo;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Usuario;

import com.estepper.estepper.model.enums.Estado;
import com.estepper.estepper.model.enums.EstadoNotificacion;
import com.estepper.estepper.model.enums.EstadoObjetivo;
import com.estepper.estepper.model.enums.TipoProgreso;

import com.estepper.estepper.service.UsuarioService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.estepper.estepper.service.ParticipanteService;
import com.estepper.estepper.service.FaseValoracionService;
import com.estepper.estepper.service.FichaService;
import com.estepper.estepper.service.ProgresoService;
import com.estepper.estepper.service.MaterialService;
import com.estepper.estepper.service.MensajeService;
import com.estepper.estepper.service.NotificacionService;
import com.estepper.estepper.service.ObjetivoService;

@Controller
public class HomeController {

    @Autowired // inyectar recursos de la clase UsuarioService
    private UsuarioService usuario;

    @Autowired
    private FaseValoracionService fasevaloracion;

    @Autowired
    private ParticipanteService participante;

    @Autowired
    private MaterialService materialS;

    @Autowired
    private ProgresoService progreso;

    @Autowired
    private ObjetivoService obj;

    @Autowired
    private MensajeService mensaje;

    @Autowired
    private NotificacionService noti;

    @Autowired
    private FichaService f;

    @Autowired
    private BCryptPasswordEncoder hash;

    /**
     * @brief Esta función maneja la solicitud GET de la ruta "/login" y devuelve la
     *        plantilla de inicio de sesión "login.html".
     *        La plantilla "login.html" es una página HTML que contiene un
     *        formulario de inicio de sesión para que los usuarios ingresen sus
     *        credenciales.
     * 
     * @return "login.html"
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        Usuario user = getUsuario();
        model.addAttribute("user", user);
        if ((user instanceof Coordinador || user instanceof Administrador)
                && user.getEstadoCuenta().equals(Estado.BAJA)) {
            MensajeAdmin menAdmin = new MensajeAdmin();
            List<MensajeAdmin> mensajesAdmin = mensaje.obtenerMensajesUsuario(user);
            model.addAttribute("messageAdmin", menAdmin);
            model.addAttribute("mensajesAdministrador", mensajesAdmin);

            return "bajaUsuario";
        } else if (user instanceof Coordinador) {
            return "coordinador";
        }

        else if (user instanceof Administrador) {
            List<Usuario> lista = usuario.listadoTotal();
            lista.remove(user);
            model.addAttribute("usuarios", lista);

            model.addAttribute("user", getUsuario());
            model.addAttribute("mensajeAdmin", new MensajeAdmin());

            model.addAttribute("coordinador", new Coordinador());

            return "admin";

        } else if (user instanceof Participante) { // PARTICIPANTE
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("consentimiento")
                            && cookie.getValue().equals(getUsuario().getId().toString())) {
                        // El usuario ha aceptado los términos y condiciones

                        // si está dado de alta
                        if (user.getEstadoCuenta().equals(Estado.ALTA)) {
                            Optional<Participante> part = participante.findById(user.getId());
                            if (part.isPresent()) {
                                model.addAttribute("participante", part.get());
                                // enviar alerta de peso
                                LocalDateTime datosSemana = LocalDateTime.now().minusDays(7); // Buscar fechas de una
                                                                                              // semana atrás
                                List<Progreso> datos = progreso.PesoPorFecha(datosSemana, TipoProgreso.PESO,
                                        part.get());

                                if (datos.isEmpty()) {
                                    model.addAttribute("recordatorio", true);
                                }

                                // buscar notificaciones
                                List<Notificacion> notificaciones = noti.notificaciones(part.get());
                                model.addAttribute("notificaciones", notificaciones);
                                ObjetivoAgua objetivoAgua = obj.findByFechaAndParticipanteAgua(new Date(), part.get());
                                Integer contadorObjetivos = 0;

                                if (objetivoAgua == null) {
                                    objetivoAgua = new ObjetivoAgua();
                                    model.addAttribute("aguaCompletado", false);
                                } else {
                                    if (objetivoAgua.getEstadoObjetivo() == EstadoObjetivo.COMPLETADO) {
                                        model.addAttribute("aguaCompletado", true);
                                        contadorObjetivos = contadorObjetivos + 1;
                                    } else {
                                        model.addAttribute("aguaCompletado", false);
                                    }
                                }

                                List<ObjetivoEjercicio> listaEjercicioParticipante = obj.listaEjercicio(new Date(),
                                        part.get());

                                if (listaEjercicioParticipante.isEmpty()) {
                                    model.addAttribute("ejercicioCompletado", false);
                                } else {
                                    model.addAttribute("ejercicioCompletado", true);
                                    contadorObjetivos = contadorObjetivos + 1;
                                }

                                ObjetivoDescanso objetivoDescanso = obj.findByFechaAndParticipanteDescanso(new Date(),
                                        part.get());

                                if (objetivoDescanso == null) {
                                    objetivoDescanso = new ObjetivoDescanso();
                                    model.addAttribute("descansoCompletado", false);
                                } else {
                                    if (objetivoDescanso.getEstadoObjetivo() == EstadoObjetivo.COMPLETADO) {
                                        model.addAttribute("descansoCompletado", true);
                                        contadorObjetivos = contadorObjetivos + 1;
                                    } else {
                                        model.addAttribute("descansoCompletado", false);
                                    }
                                }

                                ObjetivoEstadoAnimo objetivoEstadoAnimo = obj
                                        .findByFechaAndParticipanteEstadoAnimo(new Date(), part.get());

                                if (objetivoEstadoAnimo == null) {
                                    objetivoEstadoAnimo = new ObjetivoEstadoAnimo();
                                    model.addAttribute("estadoAnimoCompletado", false);
                                } else {
                                    model.addAttribute("estadoAnimoCompletado", true);
                                    contadorObjetivos = contadorObjetivos + 1;
                                }

                                model.addAttribute("contadorObjetivos", contadorObjetivos);
                                Integer porcentajeObjetivos = contadorObjetivos * 100 / 4;
                                model.addAttribute("porcentajeObjetivos", porcentajeObjetivos);

                                FichaObjetivo fichaObjetivo = f
                                        .getFichaObjetivo(participante.findById(part.get().getId()).get());
                                model.addAttribute("ficha", fichaObjetivo);

                                // cálculo de porcentaje de progreso
                                Double porcentajeProgreso = 0.00;
                                if (f.getFichaObjetivo(part.get()).getPerdida() != null
                                        && part.get().getPerdidaDePeso() != null) {
                                    if (f.getFichaObjetivo(part.get()).getPerdida() <= -part.get().getPerdidaDePeso())
                                        porcentajeProgreso = 100.00;
                                    else {
                                        porcentajeProgreso = -part.get().getPerdidaDePeso() * 100
                                                / f.getFichaObjetivo(part.get()).getPerdida();
                                    }
                                }
                                String progresoPer = String.format("%.2f", porcentajeProgreso).replace(",", "."); // Formatear
                                                                                                                  // a
                                                                                                                  // dos
                                                                                                                  // decimales
                                model.addAttribute("porcentajeProgreso", progresoPer);

                            }

                            return "index";
                        } else
                            return "baja";

                    } else
                        return "redirect:/terminos-y-condiciones";
                }

            }
        }

        return "login";
    }

    @GetMapping("/findrisc")
    public String test() {
        return "findrisc";
    }

    @GetMapping("/recomendaciones")
    public String recomendaciones() {
        return "recomendaciones";
    }

    @GetMapping("/perfil/{id}")
    public String perfil(@PathVariable("id") Integer id, Model model) {
        if (id != getUsuario().getId())
            id = getUsuario().getId();

        Usuario elusuario = usuario.findById(id).get();
        model.addAttribute("user", elusuario);

        if (elusuario instanceof Participante && elusuario.getEstadoCuenta().equals(Estado.ALTA)) {

            model.addAttribute("participante", participante.findById(id).get());

            // buscar notificaciones
            List<Notificacion> notificaciones = noti.notificaciones(participante.getParticipante(getUsuario().getId()));
            model.addAttribute("notificaciones", notificaciones);

            return "editarperfilParticipante";
        } else if (elusuario instanceof Coordinador) {

            return "editarperfil";
        } else {
            return "editarperfil";
        }

    }

    @GetMapping("/mostrarperfil/{id}")
    public String mostrarperfil(@PathVariable("id") Integer id, Model model) {

        if (id != getUsuario().getId())
            id = getUsuario().getId();

        Usuario elusuario = usuario.findById(id).get();
        model.addAttribute("user", elusuario);

        if (elusuario instanceof Participante) {
            Participante p = participante.findById(id).get();
            model.addAttribute("participante", p);

            // Nombre del grupo
            String grupo = "No asignado";
            String edad = "No asignado";
            String sexo = "No asignado";

            if (p.getAsistencia() == null) {
                p.setAsistencia(0);
            }
            if (p.getPerdidaDePeso() == null) {
                p.setPerdidaDePeso(0.0);
            }
            if (p.getSexo() != null) {
                sexo = String.valueOf(p.getSexo());
            }
            if (p.getEdad() != null) {
                edad = String.valueOf(p.getEdad());
            }

            if (p.getGrupo() != null)
                grupo = p.getGrupo().getNombre();

            model.addAttribute("grupo", grupo);
            model.addAttribute("edad", edad);
            model.addAttribute("sexo", sexo);

            // buscar notificaciones
            List<Notificacion> notificaciones = noti.notificaciones(participante.getParticipante(getUsuario().getId()));
            model.addAttribute("notificaciones", notificaciones);

            return "mostrarperfilParticipante";
        } else if (elusuario instanceof Coordinador) {

            return "mostrarperfil";
        } else {
            return "mostrarperfil";
        }

    }

    @PostMapping("/process_perfil/{id}")
    public String processPerfil(@PathVariable("id") Integer id, @ModelAttribute Usuario user,
            @ModelAttribute Participante p) {

        if (getUsuario().getId() == id) {
            Usuario orig = usuario.findById(user.getId()).get(); // usuario antes de editarlo

            if (user.getContrasenia() == "")
                user.setContrasenia(orig.getContrasenia()); // si no se ha cambiado la contraseña
            else
                user.setContrasenia(hash.encode(user.getContrasenia()));

            usuario.update(user.getNickname(), user.getEmail(), user.getContrasenia(), orig.getEstadoCuenta(),
                    orig.getId());

            if (orig instanceof Participante) { // si es un participante
                Participante part = participante.findById(id).get();

                participante.update(p.getEdad(), p.getSexo(), p.getFotoUsuario(), part.getGrupo(),
                        part.getAsistencia(),
                        part.getCoordinador(), part.getPerdidaDePeso(),
                        part.getSesionesCompletas(),
                        part.getPerdidacmcintura(), id);
            }

            return "redirect:/mostrarperfil/{id}";
        } else
            return "redirect:/";
    }

    @GetMapping("/baja")
    public String baja() {
        if (!getUsuario().getEstadoCuenta().equals(Estado.BAJA))
            return "redirect:/";
        return "baja";
    }

    @GetMapping("/register") // mostrar el formulario de registro
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Usuario());
        return "registro";
    }

    @GetMapping("/terminos-y-condiciones")
    public String terminosycondiciones(Model model) {
        if (getUsuario() != null)
            model.addAttribute("user", getUsuario());
        return "terminos";
    }

    @PostMapping("/aceptar-cookie")
    public String aceptarCookie(HttpServletResponse response, @RequestParam("userId") Integer userId) {
        Cookie cookie = new Cookie("consentimiento", userId.toString());
        int tiempoExpiracion = 365 * 24 * 60 * 60; // 1 año en segundos
        cookie.setMaxAge(tiempoExpiracion);
        cookie.setHttpOnly(true); // Para proteger la cookie de ataques XSS
        cookie.setSecure(true); // Para que la cookie solo se envíe a través de HTTPS
        response.addCookie(cookie);
        return "login";
    }

    @PostMapping("/process_register") // Procesar el registro
    public String processRegister(Participante user, Model model, HttpServletRequest request,
            HttpServletResponse response) { // Model para poder enviar información a la vista

        hash = new BCryptPasswordEncoder();
        String encodedPassword = hash.encode(user.getContrasenia());
        user.setContrasenia(encodedPassword);

        Integer elcodigo = new Random().nextInt(100000 + 1);

        // comprobar que no existe un usuario con ese codigo
        while (usuario.logueado(elcodigo) != null) {
            elcodigo = new Random().nextInt(100000 + 1);
        }

        user.setCodigo(elcodigo);
        user.setEstadoCuenta(Estado.BAJA);
        user.setFotoUsuario("/img/p1.png");

        usuario.guardar(user);

        // COOKIES
        if (request.getParameter("consentimiento") != null) {
            Cookie cookie = new Cookie("consentimiento", user.getId().toString());
            int tiempoExpiracion = 365 * 24 * 60 * 60; // 1 año en segundos
            cookie.setMaxAge(tiempoExpiracion);
            cookie.setHttpOnly(true); // Para proteger la cookie de ataques XSS
            cookie.setSecure(true); // Para que la cookie solo se envíe a través de HTTPS
            response.addCookie(cookie);
        }

        fasevaloracion.crearFormularios(participante.findById(user.getId()).get());

        model.addAttribute("nickname", user.getNickname());
        model.addAttribute("codigo", user.getCodigo());
        model.addAttribute("estadoCuenta", user.getEstadoCuenta());

        return "register_success";
    }

    @GetMapping("/recuperarcodigo")
    public String recuperarCodigo() {
        return "recuperarCodigo";
    }

    public String generarNuevaContrasenia() {
        int longitudContrasenia = 8;
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        Random rand = new Random();
        StringBuilder sb = new StringBuilder(longitudContrasenia);
        for (int i = 0; i < longitudContrasenia; i++) {
            int index = rand.nextInt(caracteres.length());
            sb.append(caracteres.charAt(index));
        }
        return sb.toString();
    }

    @PostMapping("/process_recuperarCodigo")
    public String procesoRecuperarCodigo(@RequestParam(value = "correo") String correo) {

        String contrasenia = generarNuevaContrasenia();
        hash = new BCryptPasswordEncoder();
        String encodedPassword = hash.encode(contrasenia);
        usuario.recuperarCodigo(correo, contrasenia, encodedPassword);
        return "redirect:/login";
    }

    // MATERIALES:
    @GetMapping("/materiales/{id}")
    public String mostrarMateriales(@PathVariable("id") Integer id, Model model) {
        Usuario elusuario = getUsuario();
        model.addAttribute("user", elusuario);
        Participante p = participante.findById(id).get();

        if (elusuario instanceof Coordinador
                && ((p.getCoordinador() != null && p.getCoordinador().getId() == elusuario.getId())
                        || participante.findById(id).get().getEstadoCuenta().equals(Estado.BAJA))) {
            model.addAttribute("listado", materialS.materiales(id));
            Materiales material = new Materiales();
            model.addAttribute("material", material);
            model.addAttribute("id", id);

            return "materialesCoor";
        } else if (elusuario instanceof Participante && getUsuario().getId() == id
                && getUsuario().getEstadoCuenta().equals(Estado.ALTA)) {
            model.addAttribute("listado", materialS.materiales(id));

            // buscar notificaciones
            List<Notificacion> notificaciones = noti.notificaciones(participante.getParticipante(elusuario.getId()));
            model.addAttribute("notificaciones", notificaciones);

            return "materialesPart";
        } else if (elusuario instanceof Participante && getUsuario().getId() == id) {
            return "acceso";
        } else
            return "redirect:/";
    }

    // subir material a un solo participante
    @PostMapping("/process_material/{id}")
    public String procesoMaterial(@PathVariable("id") Integer id, @ModelAttribute Materiales material,
            @RequestParam("file") MultipartFile file) {
        Participante p = participante.findById(id).get();
        if ((p.getCoordinador() != null && getUsuario().getId() == p.getCoordinador().getId())
                || getUsuario().getId() == id
                || p.getEstadoCuenta().equals(Estado.BAJA)) {
            material.setParticipante(p);
            material.setGrupo(null);
            if (!file.isEmpty()) {
                try {
                    Path rutaArchivo = Paths.get("src//main//resources//static/materiales");
                    String rutaAbsoluta = rutaArchivo.toFile().getAbsolutePath();
                    byte[] bytesArc = file.getBytes();
                    Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + file.getOriginalFilename());
                    Files.write(rutaCompleta, bytesArc);
                    material.setLink(rutaCompleta.toString());
                    material.setId(0);
                    materialS.updateMaterial(material);

                    // Crear notificación de nuevo material
                    Notificacion notificacion = new Notificacion(0, p,
                            "Nuevo material para descargar: " + material.getTitulo(), LocalDateTime.now(),
                            EstadoNotificacion.PENDIENTE, "/materiales/" + p.getId());
                    noti.guardar(notificacion);

                } catch (Exception e) {
                    String mensaje = "Ha ocurrido un error: " + e.getMessage();
                    JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        return "redirect:/materiales/{id}";
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

    @GetMapping("/mensajesAdmin")
    public String mensajesAdmin(Model model, @RequestParam(required = false) Integer id) {

        Usuario user = getUsuario();
        if (user instanceof Administrador) {
            List<Usuario> lista = usuario.listadoTotal();
            lista.remove(user);
            MensajeAdmin menAdmin = new MensajeAdmin();
            List<MensajeAdmin> mensajesAdmin = mensaje.obtenerMensajesAdmin();
            if (id != null) {
                if(usuario.findById(id).isPresent()) model.addAttribute("mensajero", usuario.findById(id).get());
                else model.addAttribute("mensajero", lista.get(0));
            } else {
                model.addAttribute("mensajero", lista.get(0));
            }

            model.addAttribute("user", user);
            model.addAttribute("usuarios", lista);
            model.addAttribute("mensajeAdmin", menAdmin);
            model.addAttribute("mensajesAdmin", mensajesAdmin);

            return "mensajesAdmin";
        } else
            return "redirect:/";
    }

    @GetMapping("/chatCordAdmin") // vista coordinador
    public String chatCordAdmin(Model model) {

        Usuario u = getUsuario();
        model.addAttribute("user", u);

        if (u instanceof Coordinador) {

            MensajeAdmin menAdmin = new MensajeAdmin();
            List<MensajeAdmin> mensajesAdmin = mensaje.obtenerMensajesUsuario(u);
            model.addAttribute("messageAdmin", menAdmin);
            model.addAttribute("mensajesAdministrador", mensajesAdmin);

            return "chatCordAdmin";
        } else

            return "redirect:/";
    }

    @GetMapping("/chatPartAdmin")
    public String chatPartAdmin(Model model) {

        Usuario u = getUsuario();
        model.addAttribute("user", u);

        if (u instanceof Participante) { // revisar esto

            MensajeAdmin menAdmin = new MensajeAdmin();
            List<MensajeAdmin> mensajesAdmin = mensaje.obtenerMensajesUsuario(u);
            model.addAttribute("messageAdmin", menAdmin);
            model.addAttribute("mensajesAdministrador", mensajesAdmin);

            return "chatPartAdmin";
        } else

            return "redirect:/";
    }

}
