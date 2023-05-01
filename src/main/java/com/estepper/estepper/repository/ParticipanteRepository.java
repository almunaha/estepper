package com.estepper.estepper.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Grupo;

import com.estepper.estepper.model.enums.Sexo;
import com.estepper.estepper.model.enums.Estado;

import jakarta.transaction.Transactional;


public interface ParticipanteRepository extends JpaRepository<Participante, Integer> {
    List<Participante> findAll();
    Optional<Participante> findById(Integer id);
    List<Participante> findByGrupo(Grupo grupo);
    @Modifying
    @Transactional
    @Query("DELETE FROM Participante m WHERE m.id = :participante")
    void delete(Integer participante);

    @Query("SELECT p FROM Participante p WHERE p.idCoordinador = :idCoordinador OR p.estadoCuenta = :estadoCuenta")
    List<Participante> findByIdCoordinadonOrEstado(Integer idCoordinador, Estado estadoCuenta);
    
    @Modifying //modifca la base de datos
    @Transactional //la consulta se ejecuta en una transacción
    @Query("UPDATE Participante p SET p.edad = :edad, p.sexo = :sexo, p.fotoUsuario = :fotoUsuario, p.grupo = :grupo, p.asistencia = :asistencia, p.idCoordinador = :idCoor, p.perdidaDePeso = :perdidadepeso, p.sesionesCompletas = :sesionescompletas, p.perdidacmcintura = :percmcintura WHERE p.id = :id")
    void update(Integer edad, Sexo sexo, String fotoUsuario, Grupo grupo, Integer asistencia, Integer idCoor, Double perdidadepeso, Integer sesionescompletas, Double percmcintura, Integer id);

    @Query("SELECT p FROM Participante p WHERE p.idCoordinador = :idCoordinador OR p.estadoCuenta = :estadoCuenta")
    public Page<Participante> findByIdCoordinadonOrEstado(Pageable pageable, Integer idCoordinador, Estado estadoCuenta);
}
