package com.estepper.estepper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.transaction.annotation.Transactional;

import com.estepper.estepper.model.entity.Antecedentes;
import com.estepper.estepper.model.entity.Participante;


   public interface AntecedentesRepository extends JpaRepository<Antecedentes, Integer>{
        @Modifying
        @Transactional
        @Query("UPDATE Antecedentes a SET a.hta = :hta, a.tiroides = :tiroides, a.patmental = :patmental, a.dislipemias = :dislipemias, a.patmuscesq = :patmuscesq, a.medicacion = :medicacion, a.ecv = :ecv, a.patsensorial = :patsensorial, a.especificar = :especificar, a.fuma = :fuma, a.dejardefumar = :dejardefumar, a.tasistolica = :tasistolica, a.tadiastolica = :tadiastolica WHERE a.participante = :participante")
        void updateAntecedentes(String hta, String tiroides, String patmental, String dislipemias, String patmuscesq, String medicacion, String ecv, String patsensorial, String especificar, String fuma, String dejardefumar, Integer tasistolica, Integer tadiastolica, Participante participante);

        Antecedentes findByParticipante(Participante participante);

        @Modifying
        @Transactional
        @Query("DELETE FROM Antecedentes a WHERE a.participante = :participante")
        void deleteByParticipante(Participante participante);
   }


