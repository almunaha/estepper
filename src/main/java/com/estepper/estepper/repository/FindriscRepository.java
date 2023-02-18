package com.estepper.estepper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;

import com.estepper.estepper.model.entity.Findrisc;

   public interface FindriscRepository extends JpaRepository<Findrisc, Integer>{
        @Modifying
        @Transactional
        @Query("UPDATE Findrisc SET idParticipante = :idParticipante, puntosedad = :puntosedad, puntosimc = :puntosimc, puntoscmcintura = :puntoscmcintura, ptosactfisica = :ptosactfisica, ptosfrecfruta = :ptosfrecfruta, ptosmedicacion = :ptosmedicacion, ptosglucosa = :ptosglucosa, ptosdiabetes = :ptosdiabetes,  puntuacion = :puntuacion, escalarriesgo = :escalarriesgo WHERE id = :id")
        void updateFindrisc(Integer id,Integer idParticipante,Integer puntosedad, Integer puntosimc, Integer puntoscmcintura, Integer ptosactfisica,
        Integer ptosfrecfruta, Integer ptosmedicacion, Integer ptosglucosa, Integer ptosdiabetes, Integer puntuacion,
        String escalarriesgo);
    }


