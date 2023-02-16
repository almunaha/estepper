package com.estepper.estepper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estepper.estepper.model.entity.FaseValoracion;
import com.estepper.estepper.model.entity.Findrisc;
import com.estepper.estepper.model.entity.Exploracion;
import com.estepper.estepper.repository.FaseValoracionRepository;

@Service
public class FaseValoracionServiceImpl implements FaseValoracionService {
    @Autowired
    private FaseValoracionRepository repo;

    @Override
    public List<FaseValoracion> faseValoracion(Integer id) {
        return repo.findByIdParticipante(id);
    }

    @Override
    public void crearFormularios(Integer id){
        //EXPLORACIÓN
        Exploracion exploracion = new Exploracion(0, id, "no", 0, 0, 0, 0, 0);
        repo.save(exploracion);
        //FINDRISC
        Findrisc findrisc = new Findrisc(0, id, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Bajo");
        repo.save(findrisc);
    }

    @Override
    public void crearFormulariosNuevos(Integer id){
        //COMPROBAR QUE NO ESTÉN CREADOS YA
        //CLASIFICACION
        //ANTECEDENTES
        //ALIMENTACION
        //ACTIVIDAD FISICA
    }

    @Override
    public void updateExploracion(String primeravez, Integer peso, Integer talla, Integer cmcintura, Integer edad,
            Integer imc, Integer id) {
        repo.updateExploracion(primeravez, peso, talla, cmcintura, edad, imc, id);
        
    }

    @Override
    public void updateFindrisc(Integer id,Integer idParticipante,Integer puntosedad, Integer puntosimc, Integer puntoscmcintura, Integer ptosactfisica,
    Integer ptosfrecfruta, Integer ptosmedicacion, Integer ptosglucosa, Integer ptosdiabetes, Integer puntuacion,
    String escalarriesgo){
        repo.updateFindrisc(id, idParticipante, puntosedad, puntosimc, puntoscmcintura, ptosactfisica, ptosfrecfruta, ptosmedicacion, ptosglucosa, ptosdiabetes, puntuacion, escalarriesgo);
    }
    
}
