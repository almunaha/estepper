package com.estepper.estepper.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.enums.Sexo;
import com.estepper.estepper.model.entity.Grupo;

import jakarta.transaction.Transactional;


public interface ParticipanteRepository extends JpaRepository<Participante, Integer> {
    List<Participante> findAll();
    Optional<Participante> findById(Integer id);
    List<Participante> findByGrupo(Grupo grupo);
    
    @Modifying
    @Transactional
    @Query("UPDATE Participante p SET p.edad = :edad, p.sexo = :sexo, p.fotoParticipante = :fotoParticipante, p.grupo = :grupo, p.asistencia = :asistencia, p.idCoordinador = :idCoor, p.perdidaDePeso = :perdidadepeso, p.sesionesCompletas = :sesionescompletas WHERE p.id = :id")
    void update(Integer edad, Sexo sexo, String fotoParticipante, Grupo grupo, Integer asistencia, Integer idCoor, Integer perdidadepeso, Integer sesionescompletas, Integer id);

}
