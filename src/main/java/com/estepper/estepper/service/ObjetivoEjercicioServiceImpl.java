package com.estepper.estepper.service;

import com.estepper.estepper.repository.ObjetivoEjercicioRepository;
import com.estepper.estepper.model.entity.ObjetivoEjercicio;
import com.estepper.estepper.model.entity.Participante;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjetivoEjercicioServiceImpl implements ObjetivoEjercicioService {
    
    @Autowired
    private ObjetivoEjercicioRepository repo;

    @Override
    public void guardar(ObjetivoEjercicio obj){
        repo.save(obj);
    }

    @Override
    public void borrar(Integer id) {
        repo.delete(repo.findById(id).get());
    }

    @Override
    public ObjetivoEjercicio getObjetivoEjercicio(Integer id) {
        return repo.findById(id).get();    
    }

    /*@Override
    public ObjetivoEjercicio findByFechaAndParticipante(Date fecha,Participante p){
        return repo.findByFechaAndParticipante(fecha,p);
    }*/

    @Override
    public ObjetivoEjercicio findByParticipante(Participante p){
        return repo.findByParticipante(p);
    }

    @Override
    public List<ObjetivoEjercicio> listaEjercicio(Date fecha, Participante p) {
        return(List<ObjetivoEjercicio>) repo.findByFechaAndParticipante(fecha,p);
    }


}

