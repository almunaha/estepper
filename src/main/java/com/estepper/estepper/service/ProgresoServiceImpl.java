package com.estepper.estepper.service;

import com.estepper.estepper.repository.ProgresoRepository;

import com.estepper.estepper.model.enums.TipoProgreso;

import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Progreso;

import java.util.List;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgresoServiceImpl implements ProgresoService {

    @Autowired
    private ProgresoRepository repo;

    @Override
    public void guardar(Progreso s) {
        repo.save(s);
    }

    @Override
    public List<Progreso> datos(Participante participante, TipoProgreso tipo) {
        return repo.findByParticipanteAndTipoOrderByFechaAsc(participante, tipo);
    }

    @Override
    public List<Progreso> PesoPorFecha(LocalDateTime fecha, TipoProgreso tipo, Participante participante) {
        return repo.findByFechaAfterAndTipoAndParticipante(fecha, tipo, participante);
    }

    @Override
    public List<Progreso> PesoPorFechaAntes(LocalDateTime fecha, TipoProgreso tipo, Participante participante) {
        return repo.findByFechaLessThanEqualAndTipoAndParticipante(fecha, tipo, participante);
    }

    @Override
    public void deleteByParticipante(Participante p) {
        repo.deleteAllByParticipante(p);
    }

    @Override
    public Progreso pesoAntiguo(Participante participante, TipoProgreso tipo) {
        return repo.findFirstByParticipanteAndTipoOrderByFechaDesc(participante, tipo);
    }

    @Override
    public Progreso primerPeso(Participante participante, TipoProgreso tipo) {
        return repo.findFirstByParticipanteAndTipoOrderByFechaAsc(participante, tipo);
    }

    @Override
    public List<Progreso> datoFechas(Participante participante, TipoProgreso tipo, LocalDateTime fechaInicial,
            LocalDateTime fechaFinal) {
        return repo.findByParticipanteAndTipoAndFechaBetweenOrderByFechaAsc(participante, tipo, fechaInicial, fechaFinal);
    }

}
