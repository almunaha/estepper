package com.estepper.estepper.service;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Date;


import com.estepper.estepper.model.entity.Progreso;
import com.estepper.estepper.model.enums.TipoProgreso;
import com.estepper.estepper.model.entity.Participante;

public interface ProgresoService {

    public void guardar(Progreso s);
    
    public List<Progreso> datos(Participante participante, TipoProgreso tipo); //datos de un tipo de progreso
    public List<Progreso> PesoPorFecha(LocalDateTime fecha, TipoProgreso tipo, Participante participante);
    
}
