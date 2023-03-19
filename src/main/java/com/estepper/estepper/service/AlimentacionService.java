package com.estepper.estepper.service;

import java.util.List;

import com.estepper.estepper.model.entity.Alimentacion;
import com.estepper.estepper.model.entity.AlimentosConsumidos;
import com.estepper.estepper.model.entity.Participante;

public interface AlimentacionService {
    public void deleteByParticipante(Participante p);
    public List<Alimentacion> getAlimentos();
    public List<AlimentosConsumidos> getAlimentosCon(Participante p);
}
