package com.estepper.estepper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.transaction.annotation.Transactional;

import com.estepper.estepper.model.entity.Findrisc;
import com.estepper.estepper.model.entity.Participante;

   public interface FindriscRepository extends JpaRepository<Findrisc, Integer>{
        @Modifying
        @Transactional
        @Query("UPDATE Findrisc SET puntosedad = :puntosedad, puntosimc = :puntosimc, puntoscmcintura = :puntoscmcintura, ptosactfisica = :ptosactfisica, ptosfrecfruta = :ptosfrecfruta, ptosmedicacion = :ptosmedicacion, ptosglucosa = :ptosglucosa, ptosdiabetes = :ptosdiabetes,  puntuacion = :puntuacion, escalarriesgo = :escalarriesgo WHERE participante = :participante")
        void updateFindrisc(Participante participante,Integer puntosedad, Integer puntosimc, Integer puntoscmcintura, Integer ptosactfisica,
        Integer ptosfrecfruta, Integer ptosmedicacion, Integer ptosglucosa, Integer ptosdiabetes, Integer puntuacion,
        String escalarriesgo);

        @Modifying
        @Transactional
        @Query("DELETE FROM Findrisc e WHERE e.participante = :participante")
        void deleteByParticipante(Participante participante);
    }


