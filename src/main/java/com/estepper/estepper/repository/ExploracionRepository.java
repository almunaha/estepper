package com.estepper.estepper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.transaction.annotation.Transactional;

import com.estepper.estepper.model.entity.Exploracion;
import com.estepper.estepper.model.entity.Participante;

import com.estepper.estepper.model.enums.Sexo;

public interface ExploracionRepository extends JpaRepository<Exploracion, Integer> {
     @Modifying
     @Transactional
     @Query("UPDATE Exploracion e SET e.primeravez = :primeravez, e.sexo = :sexo, e.peso = :peso, e.talla = :talla, e.cmcintura = :cmcintura, e.edad = :edad, e.imc = :imc WHERE e.participante = :participante")
     void updateExploracion(String primeravez, Sexo sexo, Double peso, Integer talla, Double cmcintura, Integer edad,
               Integer imc, Participante participante);

     Exploracion findByParticipante(Participante participante);

     @Modifying
     @Transactional
     @Query("DELETE FROM Exploracion e WHERE e.participante = :participante")
     void deleteByParticipante(Participante participante);
}
