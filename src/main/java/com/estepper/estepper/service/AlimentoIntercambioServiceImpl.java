package com.estepper.estepper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estepper.estepper.repository.AlimentoIntercambioRepository;

@Service
public class AlimentoIntercambioServiceImpl implements AlimentoIntercambioService{
 
    @Autowired
    private AlimentoIntercambioRepository repo;
}
