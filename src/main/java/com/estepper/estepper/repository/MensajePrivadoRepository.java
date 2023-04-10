package com.estepper.estepper.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.estepper.estepper.model.entity.Coordinador;
import com.estepper.estepper.model.entity.MensajePrivado;
import com.estepper.estepper.model.entity.Participante;

import jakarta.transaction.Transactional;

public interface MensajePrivadoRepository extends JpaRepository<MensajePrivado, Integer>{
    
    Optional<MensajePrivado> findById(Integer id);
    List<MensajePrivado> findByParticipante(Participante participante);
    //List<Mensaje> findByIdUsuario(Integer idUsuario);

    @Modifying
    @Transactional
    @Query("DELETE FROM MensajePrivado m WHERE m.coordinador = :c")
    void deleteAllByCoordinador(Coordinador c);

    @Modifying
    @Transactional
    @Query("DELETE FROM MensajePrivado m WHERE m.participante = :p")
    void deleteAllByParticipante(Participante p);


    
}