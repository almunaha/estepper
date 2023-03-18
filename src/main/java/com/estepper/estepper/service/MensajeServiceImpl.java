package com.estepper.estepper.service;

import com.estepper.estepper.model.entity.Grupo;
import com.estepper.estepper.model.entity.Mensaje;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estepper.estepper.repository.MensajeRepository;

@Service
public class MensajeServiceImpl implements MensajeService {
 
    @Autowired
    private MensajeRepository repo;

    @Override
    public Mensaje getMensaje(Integer id){
        return repo.findById(id).get();
    } 
    
    @Override
    public void save(Mensaje mensaje) {
        repo.save(mensaje);
    }

    public List<Mensaje> obtenerMensajes(){
        return repo.findAll();
    }

    @Override
    public void deleteByParticipante(Usuario p) {
        repo.deleteAllByUsuario(p);
    }

    @Override
    public void deleteByGrupo(Grupo g) {
        repo.deleteAllByGrupo(g);
    }

}
