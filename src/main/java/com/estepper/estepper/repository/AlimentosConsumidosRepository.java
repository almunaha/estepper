package com.estepper.estepper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.estepper.estepper.model.entity.AlimentosConsumidos;
import com.estepper.estepper.model.entity.Participante;

import jakarta.transaction.Transactional;

public interface AlimentosConsumidosRepository extends JpaRepository<AlimentosConsumidos, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM AlimentosConsumidos a WHERE a.participante = :p")
    void deleteAllByParticipante(Participante p);

    @Modifying
    @Transactional
    @Query("SELECT a FROM AlimentosConsumidos a WHERE a.participante = :participante AND DATE(a.fecha_consumicion) = CURRENT_DATE()")
    List<AlimentosConsumidos> findByParticipante(Participante participante);
}
