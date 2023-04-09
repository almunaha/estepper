package com.estepper.estepper.service;

import com.estepper.estepper.model.entity.ObjetivoEstadoAnimo;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.repository.ObjetivoEstadoAnimoRepository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjetivoEstadoAnimoServiceImpl implements ObjetivoEstadoAnimoService {
    
    @Autowired
    private ObjetivoEstadoAnimoRepository repo;

    @Override
    public void guardar(ObjetivoEstadoAnimo obj){
        repo.save(obj);
    }

    @Override
    public void borrar(Integer id) {
        repo.delete(repo.findById(id).get());
    }

    @Override
    public ObjetivoEstadoAnimo getObjetivoEstadoAnimo(Integer id) {
        return repo.findById(id).get();    
    }

    @Override
    public ObjetivoEstadoAnimo findByFechaAndParticipante(Date fecha,Participante p){
        return repo.findByFechaAndParticipante(fecha,p);
    }

    @Override
    public ObjetivoEstadoAnimo findByParticipante(Participante p){
        return repo.findByParticipante(p);
    }

    /*@Override
    public List<ObjetivoEstadoAnimo> listaEstadoAnimo(Date fecha, Participante p) {
        return(List<ObjetivoEstadoAnimo>) repo.findByFechaAndParticipante(fecha,p);
    }*/


}

