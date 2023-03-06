package com.estepper.estepper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Sesion;

//Los repository son los DAO, que acceden a la bd  -> los que hacen las consultas a PHPYMYADMIN
//Hacen los métodos CRUD
public interface SesionRepository extends JpaRepository<Sesion, Integer>{

    Sesion findByParticipanteAndNumSesion(Participante participante, Integer numSesion);
    List<Sesion> findByParticipante(Participante participante);
}
