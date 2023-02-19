package com.estepper.estepper.service;

import com.estepper.estepper.model.entity.FaseValoracion;
import com.estepper.estepper.model.entity.Exploracion;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Findrisc;
import com.estepper.estepper.model.enums.Sexo;

import java.util.List;

public interface FaseValoracionService {
    public List<FaseValoracion> faseValoracion(Participante participante);
    public void crearFormularios(Participante participante);
    public void crearFormulariosNuevos(Participante participante);
    public void updateExploracion(String primeravez, Sexo sexo, Integer peso, Integer talla, Integer cmcintura, Integer edad, Integer imc, Participante participante);
    public void updateFindrisc(Participante participante,Integer puntosedad, Integer puntosimc, Integer puntoscmcintura, Integer ptosactfisica,
    Integer ptosfrecfruta, Integer ptosmedicacion, Integer ptosglucosa, Integer ptosdiabetes, Integer puntuacion,
    String escalarriesgo);
    public Exploracion findByParticipante(Participante participante);
    public void actualizarFindrisc(Exploracion exploracion, Findrisc findrisc);
    public void activarcuenta(Exploracion exploracion, Findrisc findrisc, Integer id, Integer idCoor);
    public void eliminarcuenta(Participante participante);
}
