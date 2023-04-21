package com.estepper.estepper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estepper.estepper.model.entity.AlimentoIntercambio;
import com.estepper.estepper.repository.AlimentoIntercambioRepository;

@Service
public class AlimentoIntercambioServiceImpl implements AlimentoIntercambioService{
 
    @Autowired
    private AlimentoIntercambioRepository repo;

    @Override
    public List<AlimentoIntercambio> alimentos(){
        return(List<AlimentoIntercambio>) repo.findAll();
    }
}
