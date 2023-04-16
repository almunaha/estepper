package com.estepper.estepper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estepper.estepper.model.entity.Notificacion;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.repository.NotificacionRepository;

@Service
public class NotificacionServiceImpl implements NotificacionService{
    
    @Autowired
    private NotificacionRepository repo;

    @Override
    public void guardar(Notificacion n){
        repo.save(n); 
    }

    @Override
    public List<Notificacion> notificaciones(Participante p){
        return (List<Notificacion>) repo.findByParticipanteOrderByFechaEnvioDesc(p);
    }

    @Override
    public void eliminar(Notificacion n){
        repo.delete(n);
    }
}
