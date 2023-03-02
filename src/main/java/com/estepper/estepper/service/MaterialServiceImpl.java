package com.estepper.estepper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estepper.estepper.model.entity.Materiales;
import com.estepper.estepper.repository.MaterialesRepository;


@Service
public class MaterialServiceImpl implements MaterialService {
    
    @Autowired
    private MaterialesRepository m;

    public Materiales getMaterial(Integer id){
        return m.findById(id).get();
    }
}
