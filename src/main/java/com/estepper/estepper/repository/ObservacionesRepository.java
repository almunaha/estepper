package com.estepper.estepper.repository;

import com.estepper.estepper.model.entity.Coordinador;
import com.estepper.estepper.model.entity.Grupo;
import com.estepper.estepper.model.entity.Observaciones;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ObservacionesRepository extends JpaRepository<Observaciones, Integer> {

    List<Observaciones> findByCoordinador(Coordinador coordinador);

    List<Observaciones> findByGrupo(Grupo grupo);

    Optional<Observaciones> findById(Integer idObservacion);

    @Modifying
    @Transactional
    @Query("DELETE FROM Observaciones o WHERE o.grupo = :g")
    void deleteAllByGrupo(Grupo g);

    @Modifying
    @Transactional
    @Query("DELETE FROM Observaciones o WHERE o.coordinador = :c")
    void deleteAllByCoordinador(Coordinador c);

    @Modifying
    @Transactional
    @Query("update Observaciones SET nota = :nota WHERE id = :id")
    void update(String nota, Integer id);

}
