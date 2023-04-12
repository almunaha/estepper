package com.estepper.estepper.service;

import com.estepper.estepper.model.entity.Coordinador;
import com.estepper.estepper.model.entity.Grupo;
import com.estepper.estepper.model.entity.Mensaje;
import com.estepper.estepper.model.entity.MensajePrivado;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estepper.estepper.repository.MensajePrivadoRepository;
import com.estepper.estepper.repository.MensajeRepository;

@Service
public class MensajeServiceImpl implements MensajeService {
 
    @Autowired
    private MensajeRepository repo;

    @Autowired
    private MensajePrivadoRepository repoPrivado;


    //MENSAJES GRUPALES
    @Override
    public Mensaje getMensaje(Integer id){
        return repo.findById(id).get();
    } 
    
    @Override
    public void save(Mensaje mensaje) {
        repo.save(mensaje);
    }

    @Override
    public List<Mensaje> obtenerMensajes (Grupo grupo){
        return repo.findByGrupo(grupo);
    }


    @Override
    public void deleteByParticipante(Usuario p) {
        repo.deleteAllByUsuario(p);
    }

    @Override
    public void deleteByGrupo(Grupo g) {
        repo.deleteAllByGrupo(g);
    }

    //MENSAJES PRIVADOS

   @Override
    public MensajePrivado getMensajePrivado(Integer id){
        return repoPrivado.findById(id).get();
    } 
    
    @Override
    public void saveMensajePrivado(MensajePrivado mensaje) {
        repoPrivado.save(mensaje);
    }

    @Override
    public List<MensajePrivado> obtenerMensajesPrivados(Participante participante){
        return repoPrivado.findByParticipante(participante);
    }


    @Override
    public void deleteByParticipanteMensajePrivado(Participante p) {
        repoPrivado.deleteAllByParticipante(p);
    }

    @Override
    public void deleteByCoordinadorMensajePrivado(Coordinador c) {
        repoPrivado.deleteAllByCoordinador(c);
    }
}
