package com.estepper.estepper.service;
import com.estepper.estepper.repository.ProgresoRepository;
import com.estepper.estepper.model.entity.Progreso;

import com.estepper.estepper.model.enums.TipoProgreso;
import com.estepper.estepper.model.entity.Participante;
import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgresoServiceImpl implements ProgresoService{

    @Autowired
    private ProgresoRepository repo;

    @Override
    public void guardar(Progreso s){
        repo.save(s);
    }

    @Override
    public List<Progreso> datos(Participante participante, TipoProgreso tipo){
        return repo.findByParticipanteAndTipoOrderByFechaAsc(participante, tipo);
    }

    @Override
    public List<Progreso> PesoPorFecha(Date fecha, TipoProgreso tipo, Participante participante){
        return repo.findByFechaAfterAndTipoAndParticipante(fecha, tipo, participante);
    }

    @Override
    public void deleteByParticipante(Participante p) {
        repo.deleteAllByParticipante(p);
    }

    
}
