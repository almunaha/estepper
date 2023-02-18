package com.estepper.estepper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estepper.estepper.model.entity.FaseValoracion;
import com.estepper.estepper.model.entity.Findrisc;
import com.estepper.estepper.model.enums.Sexo;
import com.estepper.estepper.model.entity.Exploracion;
import com.estepper.estepper.repository.FaseValoracionRepository;
import com.estepper.estepper.repository.ExploracionRepository;
import com.estepper.estepper.repository.FindriscRepository;

@Service
public class FaseValoracionServiceImpl implements FaseValoracionService {

    @Autowired
    private FaseValoracionRepository repoV;

    @Autowired
    private ExploracionRepository repoE;

    @Autowired
    private FindriscRepository repoF;

    @Override
    public List<FaseValoracion> faseValoracion(Integer id) {
        return repoV.findByIdParticipante(id);
    }

    @Override
    public void crearFormularios(Integer id){
        //EXPLORACIÓN
        Exploracion exploracion = new Exploracion(0, id, Sexo.MASCULINO, "no", 0, 0, 0, 0, 0);
        repoE.save(exploracion);
        //FINDRISC
        Findrisc findrisc = new Findrisc(0, id, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Bajo");
        repoF.save(findrisc);
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
    public void updateExploracion(String primeravez, Sexo sexo, Integer peso, Integer talla, Integer cmcintura, Integer edad,
            Integer imc, Integer id) {
        repoE.updateExploracion(primeravez, sexo, peso, talla, cmcintura, edad, imc, id);
        
    }

    @Override
    public void updateFindrisc(Integer id,Integer puntosedad, Integer puntosimc, Integer puntoscmcintura, Integer ptosactfisica,
    Integer ptosfrecfruta, Integer ptosmedicacion, Integer ptosglucosa, Integer ptosdiabetes, Integer puntuacion,
    String escalarriesgo){
        repoF.updateFindrisc(id, puntosedad, puntosimc, puntoscmcintura, ptosactfisica, ptosfrecfruta, ptosmedicacion, ptosglucosa, ptosdiabetes, puntuacion, escalarriesgo);
    }
    
    @Override
    public Exploracion findByIdParticipante(Integer id){
        return repoE.findByIdParticipante(id);

    }

    @Override
    public void actualizarFindrisc(Exploracion exploracion){
        //IMPLEMENTAR
    }

    @Override
    public void activarcuenta(Exploracion exploracion, Findrisc findrisc, Integer id){
        //IMPLEMENTAR
    }

    @Override
    public void eliminarcuenta(Integer id){
        //IMPLEMENTAR
    }
}
