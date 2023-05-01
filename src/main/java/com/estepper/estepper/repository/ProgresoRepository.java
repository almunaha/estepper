package com.estepper.estepper.repository;

import com.estepper.estepper.model.entity.Progreso;
import com.estepper.estepper.model.entity.Participante;

import com.estepper.estepper.model.enums.TipoProgreso;

import jakarta.transaction.Transactional;

import java.util.List;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

public interface ProgresoRepository extends JpaRepository<Progreso, Integer>{

    List<Progreso> findByParticipanteAndTipoOrderByFechaAsc(Participante participante, TipoProgreso tipo);
    List<Progreso> findByFechaAfterAndTipoAndParticipante(LocalDateTime fecha, TipoProgreso tipo, Participante participante);
    List<Progreso> findByFechaLessThanEqualAndTipoAndParticipante(LocalDateTime fecha, TipoProgreso tipo, Participante participante);
    List<Progreso> findByParticipanteAndTipoAndFechaBetween(Participante participante, TipoProgreso tipo, LocalDateTime fechaInicial, LocalDateTime fechaFinal);


    @Modifying
    @Transactional
    @Query("DELETE FROM Progreso p WHERE p.participante = :p")
    void deleteAllByParticipante(Participante p);   

    Progreso findFirstByParticipanteAndTipoOrderByFechaDesc(Participante participante, TipoProgreso tipo);
    Progreso findFirstByParticipanteAndTipoOrderByFechaAsc(Participante participante, TipoProgreso tipo);

    List<Progreso> findByFechaGreaterThanEqualAndTipo(LocalDateTime fecha, TipoProgreso tipo);


}
