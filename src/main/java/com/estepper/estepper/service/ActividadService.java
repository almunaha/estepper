package com.estepper.estepper.service;
import java.time.LocalDateTime;
import java.util.List;

import com.estepper.estepper.model.entity.Actividad;

public interface ActividadService {
    public void guardar(Actividad a);
    public List<Actividad> listado();
    public Actividad actividad(Integer id);
    public List<Actividad> asistenciaParticipante(Integer id);
    public void borrar(Actividad act);
    public Integer asistencia(Integer idActividad, Integer idParticipante);
    public List<Actividad> actividadesPendientes(LocalDateTime fechaActual);
}
