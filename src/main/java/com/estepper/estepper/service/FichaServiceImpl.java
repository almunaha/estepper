package com.estepper.estepper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estepper.estepper.model.entity.Ficha;

import com.estepper.estepper.repository.FichaRepository;

@Service
public class FichaServiceImpl implements FichaService{

    @Autowired
    private FichaRepository repo; //inyección de dependencias del participante dao api

    @Override
    public List<Ficha> fichasSesion(Integer id){
        return (List<Ficha>) repo.findByIdSesion(id);
    }
}