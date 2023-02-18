package com.estepper.estepper.service;

import com.estepper.estepper.model.entity.FaseValoracion;
import com.estepper.estepper.model.entity.Exploracion;
import com.estepper.estepper.model.entity.Findrisc;
import java.util.List;

public interface FaseValoracionService {
    public List<FaseValoracion> faseValoracion(Integer id);
    public void crearFormularios(Integer id);
    public void crearFormulariosNuevos(Integer id);
    public void updateExploracion(String primeravez, Integer peso, Integer talla, Integer cmcintura, Integer edad, Integer imc, Integer id);
    public void updateFindrisc(Integer id,Integer idParticipante,Integer puntosedad, Integer puntosimc, Integer puntoscmcintura, Integer ptosactfisica,
    Integer ptosfrecfruta, Integer ptosmedicacion, Integer ptosglucosa, Integer ptosdiabetes, Integer puntuacion,
    String escalarriesgo);
    public Exploracion findByIdParticipante(Integer id);
    public void actualizarFindrisc(Exploracion exploracion);
    public void activarcuenta(Exploracion exploracion, Findrisc findrisc, Integer id);
    public void eliminarcuenta(Integer id);
}
