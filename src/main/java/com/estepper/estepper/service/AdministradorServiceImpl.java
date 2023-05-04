package com.estepper.estepper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estepper.estepper.model.entity.Administrador;
import com.estepper.estepper.repository.AdministradorRepository;

@Service
public class AdministradorServiceImpl implements AdministradorService {

    @Autowired
    private AdministradorRepository repo; // inyección de dependencias del participante dao api

    @Override
    public Administrador getAdministrador(Integer id) {
        return repo.findById(id).get();
    }

}
