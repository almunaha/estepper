package com.estepper.estepper.repository;
import com.estepper.estepper.model.entity.Progreso;
import com.estepper.estepper.model.enums.TipoProgreso;
import com.estepper.estepper.model.entity.Participante;
import org.springframework.data.jpa.repository.Modifying;
import jakarta.transaction.Transactional;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProgresoRepository extends JpaRepository<Progreso, Integer>{

    List<Progreso> findByParticipanteAndTipoOrderByFechaAsc(Participante participante, TipoProgreso tipo);
    List<Progreso> findByFechaAfterAndTipoAndParticipante(LocalDateTime fecha, TipoProgreso tipo, Participante participante);

    @Modifying
    @Transactional
    @Query("DELETE FROM Progreso p WHERE p.participante = :p")
    void deleteAllByParticipante(Participante p);   

    Progreso findFirstByParticipanteAndTipoOrderByFechaDesc(Participante participante, TipoProgreso tipo);
}
