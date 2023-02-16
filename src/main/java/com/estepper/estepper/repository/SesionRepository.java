package com.estepper.estepper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import com.estepper.estepper.model.entity.Sesion;

//Los repository son los DAO, que acceden a la bd  -> los que hacen las consultas a PHPYMYADMIN
//Hacen los métodos CRUD
//@Repository
public interface SesionRepository extends JpaRepository<Sesion, Integer>{
    Sesion findByIdParticipante(Integer idParticipante);
    Sesion findByNumSesion(Integer numSesion);

}
