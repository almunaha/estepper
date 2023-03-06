package com.estepper.estepper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estepper.estepper.model.entity.Actividad;
import com.estepper.estepper.repository.ActividadRepository;

@Service
public class ActividadServiceImpl implements ActividadService{

    @Autowired
    private ActividadRepository actividad;

  
    
}
