package com.estepper.estepper.service;

import java.util.List;

import com.estepper.estepper.model.entity.Coordinador;
import com.estepper.estepper.model.entity.Grupo;
import com.estepper.estepper.model.entity.Observaciones;

public interface ObservacionesService {

    public List<Observaciones> findByIdCoordinador(Integer idCoordinador);

    public List<Observaciones> findByIdGrupo(Integer idGrupo);

    public void guardar(Observaciones o);

    public void borrar(Integer id);

    public void deleteByGrupo(Grupo g);

    public void deleteByCoordinador(Coordinador c);

    public Observaciones getObservacion(Integer idObservacion);

    public void actualizar(Observaciones o);

}
