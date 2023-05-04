package com.estepper.estepper.service;

import java.util.List;

import com.estepper.estepper.model.entity.Coordinador;
import com.estepper.estepper.model.entity.Grupo;
import com.estepper.estepper.model.entity.Mensaje;
import com.estepper.estepper.model.entity.MensajePrivado;
import com.estepper.estepper.model.entity.MensajeAdmin;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Usuario;

public interface MensajeService {

    // MENSAJE GRUPAL
    public Mensaje getMensaje(Integer id);

    public void save(Mensaje mensaje);

    public List<Mensaje> obtenerMensajes(Grupo grupo);

    public void deleteByParticipante(Usuario p);

    public void deleteByGrupo(Grupo g);

    // MENSAJES PRIVADOS
    public MensajePrivado getMensajePrivado(Integer id);

    public void saveMensajePrivado(MensajePrivado mensaje);

    public List<MensajePrivado> obtenerMensajesPrivados(Participante participante);

    public void deleteByParticipanteMensajePrivado(Participante p);

    public void deleteByCoordinadorMensajePrivado(Coordinador c);

    // MENSAJE ADMINISTRADOR
    public MensajeAdmin getMensajeAdmin(Integer id);

    public void saveMensajeAdmin(MensajeAdmin mensaje);

    public List<MensajeAdmin> obtenerMensajesAdmin();

    public List<MensajeAdmin> obtenerMensajesUsuario(Usuario usuario);

    public void deleteByUsuarioMensajeAdmin(Usuario u);
}
