package com.estepper.estepper.service;

import com.estepper.estepper.model.entity.Coordinador;
import com.estepper.estepper.model.entity.Grupo;
import com.estepper.estepper.model.entity.MensajePrivado;
import com.estepper.estepper.model.entity.Participante;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estepper.estepper.repository.MensajePrivadoRepository;


@Service
public class MensajePrivadoServiceImpl implements MensajePrivadoService {
 
    @Autowired
    private MensajePrivadoRepository repo;

    @Override
    public MensajePrivado getMensaje(Integer id){
        return repo.findById(id).get();
    } 
    
    @Override
    public void save(MensajePrivado mensaje) {
        repo.save(mensaje);
    }

    @Override
    public List<MensajePrivado> obtenerMensajesPrivados(Participante participante){
        return repo.findByParticipante(participante);
    }


    @Override
    public void deleteByParticipante(Participante p) {
        repo.deleteAllByParticipante(p);
    }

    @Override
    public void deleteByCoordinador(Coordinador c) {
        repo.deleteAllByCoordinador(c);
    }

}
