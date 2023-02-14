package com.estepper.estepper.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.estepper.estepper.model.enums.Sexo;

import com.estepper.estepper.model.entity.Participante;

import jakarta.transaction.Transactional;


public interface ParticipanteRepository extends JpaRepository<Participante, Integer> {
    List<Participante> findAll();
    Optional<Participante> findById(Integer id);
    @Modifying
    @Transactional
    @Query("UPDATE Participante p SET p.sexo = :sexo WHERE p.id = :id")
    void update(Sexo sexo, Integer id);

}
