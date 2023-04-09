package com.estepper.estepper.service;


import com.estepper.estepper.model.entity.ObjetivoDescanso;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.repository.ObjetivoDescansoRepository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjetivoDescansoServiceImpl implements ObjetivoDescansoService {
    
    @Autowired
    private ObjetivoDescansoRepository repo;

    @Override
    public void guardar(ObjetivoDescanso obj){
        repo.save(obj);
    }

    @Override
    public void borrar(Integer id) {
        repo.delete(repo.findById(id).get());
    }

    @Override
    public ObjetivoDescanso getObjetivoDescanso(Integer id) {
        return repo.findById(id).get();    
    }

    @Override
    public ObjetivoDescanso findByFechaAndParticipante(Date fecha,Participante p){
        return repo.findByFechaAndParticipante(fecha,p);
    }

    @Override
    public ObjetivoDescanso findByParticipante(Participante p){
        return repo.findByParticipante(p);
    }

    /*@Override
    public List<ObjetivoDescanso> listaDescanso(Date fecha, Participante p) {
        return(List<ObjetivoDescanso>) repo.findByFechaAndParticipante(fecha,p);
    }*/


}

