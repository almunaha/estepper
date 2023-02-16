package com.estepper.estepper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estepper.estepper.model.entity.FaseValoracion;
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
    
}
