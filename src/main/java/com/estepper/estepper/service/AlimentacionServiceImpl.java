package com.estepper.estepper.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.estepper.estepper.repository.AlimentacionRepository;
import com.estepper.estepper.repository.AlimentosConsumidosRepository;

public class AlimentacionServiceImpl implements AlimentacionService{

    @Autowired
    private AlimentacionRepository repoA;

    @Autowired
    private AlimentosConsumidosRepository repoAC;

    //AQUÍ LA IMPLEMENTACIÓN DE LAS FUNCIONES DECLARADAS CON @OVERRIDE
    
}
