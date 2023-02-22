package com.estepper.estepper.service;
import java.util.List;


import com.estepper.estepper.model.entity.Progreso;
import com.estepper.estepper.model.enums.TipoProgreso;
import com.estepper.estepper.model.entity.Participante;

public interface ProgresoService {

    public void guardar(Progreso s);
    public List<Progreso> datos(Participante participante, TipoProgreso tipo);
    
}
