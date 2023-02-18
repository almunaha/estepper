package com.estepper.estepper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;

import com.estepper.estepper.model.entity.Exploracion;


   public interface ExploracionRepository extends JpaRepository<Exploracion, Integer>{
        @Modifying
        @Transactional
        @Query("UPDATE Exploracion SET primeravez = :primeravez, peso = :peso, talla = :talla, cmcintura = :cmcintura, edad = :edad, imc = :imc WHERE id = :id")
        void updateExploracion(String primeravez, Integer peso, Integer talla, Integer cmcintura, Integer edad, Integer imc, Integer id);

        Exploracion findByIdParticipante(Integer idParticipante);
   }


