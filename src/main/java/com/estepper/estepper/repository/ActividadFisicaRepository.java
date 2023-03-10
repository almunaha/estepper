package com.estepper.estepper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.transaction.annotation.Transactional;

import com.estepper.estepper.model.entity.ActividadFisica;
import com.estepper.estepper.model.entity.Participante;


   public interface ActividadFisicaRepository extends JpaRepository<ActividadFisica, Integer>{
        @Modifying
        @Transactional
        @Query("UPDATE ActividadFisica a SET a.vecesafv = :vecesafv, a.horaafv = :horaafv, a.minafv = :minafv, a.metsafv = :metsafv, a.vecesafm = :vecesafm, a.horaafm = :horaafm, a.minafm = :minafm, a.metsafm = :metsafm, a.vecescaminar = :vecescaminar, a.horacaminar = :horacaminar, a.mincaminar = :mincaminar, a.metscaminar = :metscaminar, a.horasentado = :horasentado, a.minsentado = :minsentado, a.metstotales = :metstotales, a.clasificacion = :clasificacion WHERE a.participante = :participante")
        void updateActividadFisica(Integer vecesafv, Integer horaafv, Integer minafv, Integer metsafv, Integer vecesafm, Integer horaafm, Integer minafm, Integer metsafm, Integer vecescaminar, Integer horacaminar, Integer mincaminar, Integer metscaminar, Integer horasentado, Integer minsentado, Integer metstotales, String clasificacion, Participante participante);

        ActividadFisica findByParticipante(Participante participante);

        @Modifying
        @Transactional
        @Query("DELETE FROM ActividadFisica a WHERE a.participante = :participante")
        void deleteByParticipante(Participante participante);
   }


