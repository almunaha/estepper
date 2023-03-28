package com.estepper.estepper.service;

import java.util.List;

import com.estepper.estepper.model.entity.Alimentacion;
import com.estepper.estepper.model.entity.AlimentosConsumidos;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Receta;

public interface AlimentacionService {
    public void deleteByParticipante(Participante p);
    public List<Alimentacion> getAlimentos();
    public List<Receta> getRecetas();
    public List<AlimentosConsumidos> getAlimentosCon(Participante p);
    public void saveAlCon(AlimentosConsumidos al);
    public void saveAlimento(Alimentacion al);
    public void deleteAlCon(Integer id);
    public void updateReceta(Receta receta);
    public void borraralconSem(Participante p);
    public Receta getRecetasById(Integer id);
}
