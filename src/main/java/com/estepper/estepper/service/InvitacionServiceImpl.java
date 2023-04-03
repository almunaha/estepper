package com.estepper.estepper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estepper.estepper.model.entity.Actividad;
import com.estepper.estepper.model.entity.Coordinador;
import com.estepper.estepper.model.entity.Invitacion;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.enums.EstadoInvitacion;
import com.estepper.estepper.repository.InvitacionRepository;

@Service
public class InvitacionServiceImpl implements InvitacionService{

    @Autowired
    private InvitacionRepository repo; //inyección de dependencias de la invtacion dao api

    @Override
    public List<Invitacion> listadoCoor(Coordinador c){
        return(List<Invitacion>) repo.findByCoordinador(c);
    } 

    @Override
    public List<Invitacion> listadoByAct(Actividad a){
        return(List<Invitacion>) repo.findByActividad(a);
    } 

    @Override
    public void borrar(Invitacion i){
        repo.delete(i);
    }

    @Override
    public List<Invitacion> invitacionesPartAndEstado(Participante p, EstadoInvitacion e){
        return(List<Invitacion>) repo.findByParticipanteAndEstado(p, e);
    }

    @Override
    public Invitacion findById(Integer id){
        return repo.findById(id).get();
    }

    @Override
    public void guardar(Invitacion i){
        repo.save(i);
    }

    @Override
    public List<Invitacion> listadoCoordAct(Coordinador c, Actividad a){
        return (List<Invitacion>) repo.findByCoordinadorAndActividad(c, a);
    }

    @Override
    public Invitacion invitacionByPartAndActi(Participante p, Actividad a){
        return repo.findByParticipanteAndActividad(p, a);
    }

    @Override
    public void eliminarPorParticipante(Participante p){
        repo.deleteByParticipante(p);
    }

    @Override
    public void eliminarPorCoordinador(Coordinador c){
        repo.deleteByCoordinador(c);
    }

}
