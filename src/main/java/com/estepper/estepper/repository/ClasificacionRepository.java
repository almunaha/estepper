package com.estepper.estepper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;

import com.estepper.estepper.model.entity.Clasificacion;
import com.estepper.estepper.model.entity.Participante;


   public interface ClasificacionRepository extends JpaRepository<Clasificacion, Integer>{
        @Modifying
        @Transactional
        @Query("UPDATE Clasificacion c SET c.analiticahecha = :analiticahecha, c.glucemia = :glucemia, c.colesterol = :colesterol, c.ldl = :ldl, c.sog = :sog, c.hdl = :hdl, c.trigliceridos = :trigliceridos, c.hbA1c = :hbA1c, c.pediranalitica = :pediranalitica, c.clasificacionusuario = :clasificacionusuario, c.montesa = :montesa, c.motivomontesa = :motivomontesa, c.taller = :taller, c.motivotaller = :motivotaller, c.actividadfisica = :actividadfisica  WHERE c.participante = :participante")
        void updateClasificacion(String analiticahecha, Integer glucemia, Integer colesterol, Integer ldl, Integer sog, Integer hdl, Integer trigliceridos, double hbA1c, String pediranalitica, Integer clasificacionusuario, String montesa, String motivomontesa, String taller, String motivotaller, String actividadfisica, Participante participante);

        Clasificacion findByParticipante(Participante participante);

        @Modifying
        @Transactional
        @Query("DELETE FROM Clasificacion c WHERE c.participante = :participante")
        void deleteByParticipante(Participante participante);
   }


