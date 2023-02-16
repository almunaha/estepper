package com.estepper.estepper.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Sesion;

//Los repository son los DAO, que acceden a la bd  -> los que hacen las consultas a PHPYMYADMIN
//Hacen los métodos CRUD
public interface SesionRepository extends JpaRepository<Sesion, Integer>{
    Sesion findByParticipante(Optional <Participante> participante);
    Sesion findByNumSesion(Integer numSesion);
}
