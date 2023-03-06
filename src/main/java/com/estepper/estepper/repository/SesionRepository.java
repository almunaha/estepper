package com.estepper.estepper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Sesion;

import jakarta.transaction.Transactional;

//Los repository son los DAO, que acceden a la bd  -> los que hacen las consultas a PHPYMYADMIN
//Hacen los métodos CRUD
public interface SesionRepository extends JpaRepository<Sesion, Integer>{

    Sesion findByParticipanteAndNumSesion(Participante participante, Integer numSesion);

    @Modifying
    @Transactional
    @Query("DELETE FROM Sesion s WHERE s.participante = :p")
    void deleteAllByParticipante(Participante p);
 
}
