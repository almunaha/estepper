package com.estepper.estepper.service;

import com.estepper.estepper.repository.ObjetivoAguaRepository;
import com.estepper.estepper.repository.ObjetivoDescansoRepository;
import com.estepper.estepper.repository.ObjetivoEjercicioRepository;
import com.estepper.estepper.repository.ObjetivoEstadoAnimoRepository;
import com.estepper.estepper.repository.ObjetivoRepository;

import com.estepper.estepper.model.entity.Objetivo;
import com.estepper.estepper.model.entity.ObjetivoAgua;
import com.estepper.estepper.model.entity.ObjetivoDescanso;
import com.estepper.estepper.model.entity.ObjetivoEjercicio;
import com.estepper.estepper.model.entity.ObjetivoEstadoAnimo;
import com.estepper.estepper.model.entity.Participante;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjetivoServiceImpl implements ObjetivoService {
    
    @Autowired
    private ObjetivoRepository repo;

    @Autowired
    private ObjetivoAguaRepository repoAgua;

    @Autowired
    private ObjetivoDescansoRepository repoDescanso;

    @Autowired
    private ObjetivoEjercicioRepository repoEjercicio;

    @Autowired
    private ObjetivoEstadoAnimoRepository repoEstadoAnimo;

    @Override
    public void guardar(Objetivo obj){
        repo.save(obj);
    }


    @Override
    public List<Objetivo> getObjetivos(){
        return repo.findAll();
    }


    @Override
    public List<Objetivo> listaObjetivos(Participante p) {
        return(List<Objetivo>) repo.findByParticipante(p);    
    }

    @Override
    public List<Objetivo> listaObjetivosPorMes(Participante p, Integer mes, Integer anio) {
        return(List<Objetivo>) repo.findByParticipanteyMesyAnio(p, mes, anio);    
    }

    @Override
    public void borrar(Integer id) {
        repo.delete(repo.findById(id).get());
    }

    @Override
    public Objetivo getObjetivo(Integer id) {
        return repo.findById(id).get();    
    }

    @Override
    public void deleteByParticipante(Participante p) {
        repo.deleteAllByParticipante(p);
    }

    //AGUA
    @Override
    public void guardarAgua(ObjetivoAgua obj){
        repoAgua.save(obj);
    }

    @Override
    public void borrarObjetivoAgua(Integer id) {
        repoAgua.delete(repoAgua.findById(id).get());
    }

    @Override
    public ObjetivoAgua getObjetivoAgua(Integer id) {
        return repoAgua.findById(id).get();    
    }

    @Override
    public ObjetivoAgua findByFechaAndParticipanteAgua(Date fecha,Participante p){
        return repoAgua.findByFechaAndParticipante(fecha,p);
    }

    //DESCANSO
    @Override
    public void guardarDescanso(ObjetivoDescanso obj){
        repoDescanso.save(obj);
    }

    @Override
    public void borrarObjetivoDescanso(Integer id) {
        repoDescanso.delete(repoDescanso.findById(id).get());
    }

    @Override
    public ObjetivoDescanso getObjetivoDescanso(Integer id) {
        return repoDescanso.findById(id).get();    
    }

    @Override
    public ObjetivoDescanso findByFechaAndParticipanteDescanso(Date fecha,Participante p){
        return repoDescanso.findByFechaAndParticipante(fecha,p);
    }

    @Override
    public ObjetivoDescanso findByParticipanteDescanso(Participante p){
        return repoDescanso.findByParticipante(p);
    }

    //EJERCICIO
    @Override
    public void guardarEjercicio(ObjetivoEjercicio obj){
        repoEjercicio.save(obj);
    }

    @Override
    public void borrarObjetivoEjercicio(Integer id) {
        repoEjercicio.delete(repoEjercicio.findById(id).get());
    }

    @Override
    public ObjetivoEjercicio getObjetivoEjercicio(Integer id) {
        return repoEjercicio.findById(id).get();    
    }

    @Override
    public ObjetivoEjercicio findByParticipanteEjercicio(Participante p){
        return repoEjercicio.findByParticipante(p);
    }

    @Override
    public List<ObjetivoEjercicio> listaEjercicio(Date fecha, Participante p) {
        return(List<ObjetivoEjercicio>) repoEjercicio.findByFechaAndParticipante(fecha,p);
    }

    //ESTADO ANIMO
    @Override
    public void guardarEstadoAnimo(ObjetivoEstadoAnimo obj){
        repoEstadoAnimo.save(obj);
    }

    @Override
    public void borrarObjetivoEstadoAnimo(Integer id) {
        repoEstadoAnimo.delete(repoEstadoAnimo.findById(id).get());
    }

    @Override
    public ObjetivoEstadoAnimo getObjetivoEstadoAnimo(Integer id) {
        return repoEstadoAnimo.findById(id).get();    
    }

    @Override
    public ObjetivoEstadoAnimo findByFechaAndParticipanteEstadoAnimo(Date fecha,Participante p){
        return repoEstadoAnimo.findByFechaAndParticipante(fecha,p);
    }

    @Override
    public ObjetivoEstadoAnimo findByParticipanteEstadoAnimo(Participante p){
        return repoEstadoAnimo.findByParticipante(p);
    }


}

