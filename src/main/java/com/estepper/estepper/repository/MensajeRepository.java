package com.estepper.estepper.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.estepper.estepper.model.entity.Grupo;
import com.estepper.estepper.model.entity.Mensaje;
import com.estepper.estepper.model.entity.Usuario;

import jakarta.transaction.Transactional;





public interface MensajeRepository extends JpaRepository<Mensaje, Integer>{
    
    Optional<Mensaje> findById(Integer id);
    //List<Mensaje> findByIdUsuario(Integer idUsuario);

    @Modifying
    @Transactional
    @Query("DELETE FROM Mensaje m WHERE m.usuario = :u")
    void deleteAllByUsuario(Usuario u);

    @Modifying
    @Transactional
    @Query("DELETE FROM Mensaje m WHERE m.grupo = :g")
    void deleteAllByGrupo(Grupo g);
}
