package com.estepper.estepper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;

import com.estepper.estepper.model.entity.Exploracion;
import com.estepper.estepper.model.enums.Sexo;


   public interface ExploracionRepository extends JpaRepository<Exploracion, Integer>{
        @Modifying
        @Transactional
        @Query("UPDATE Exploracion e SET e.primeravez = :primeravez, e.sexo = :sexo, e.peso = :peso, e.talla = :talla, e.cmcintura = :cmcintura, e.edad = :edad, e.imc = :imc WHERE e.idParticipante = :idParticipante")
        void updateExploracion(String primeravez, Sexo sexo, Integer peso, Integer talla, Integer cmcintura, Integer edad, Integer imc, Integer idParticipante);

        Exploracion findByIdParticipante(Integer idParticipante);

        @Modifying
        @Transactional
        @Query("DELETE FROM Exploracion e WHERE e.idParticipante = :idParticipante")
        void deleteByIdParticipante(Integer idParticipante);
   }


