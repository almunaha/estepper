package com.estepper.estepper.repository;

import jakarta.transaction.Transactional;

import com.estepper.estepper.model.entity.ObjetivoEstadoAnimo;
import com.estepper.estepper.model.entity.Participante;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface ObjetivoEstadoAnimoRepository extends JpaRepository<ObjetivoEstadoAnimo, Integer>{

    @Modifying
    @Transactional
    @Query("DELETE FROM ObjetivoEstadoAnimo o WHERE o.participante = :p")
    void deleteAllByParticipante(Participante p);

    ObjetivoEstadoAnimo findByFechaAndParticipante(Date fecha, Participante p);
    ObjetivoEstadoAnimo findByParticipante(Participante p);
    //List<ObjetivoEstadoAnimo> findByFechaAndParticipante(Date fecha, Participante p);


}





