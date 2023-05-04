package com.estepper.estepper.service;

import java.util.List;

import com.estepper.estepper.model.entity.Actividad;
import com.estepper.estepper.model.entity.Coordinador;
import com.estepper.estepper.model.entity.Invitacion;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.enums.EstadoInvitacion;

public interface InvitacionService {

    public List<Invitacion> listadoCoor(Coordinador c);

    public List<Invitacion> listadoCoordAct(Coordinador c, Actividad a);

    public List<Invitacion> listadoByAct(Actividad a);

    public void borrar(Invitacion i);

    public List<Invitacion> invitacionesPartAndEstado(Participante p, EstadoInvitacion e);

    public Invitacion findById(Integer id);

    public void guardar(Invitacion i);

    public Invitacion invitacionByPartAndActi(Participante p, Actividad a);

    public void eliminarPorParticipante(Participante p);

    public void eliminarPorCoordinador(Coordinador c);

    public Integer numInvitacionesPosibles(Actividad a, EstadoInvitacion e);

}
