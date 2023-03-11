package com.estepper.estepper.service;

import com.estepper.estepper.model.entity.Mensaje;

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

}
