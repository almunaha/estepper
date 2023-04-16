package com.estepper.estepper.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.estepper.estepper.model.entity.Administrador;
import com.estepper.estepper.model.entity.MensajeAdmin;

import com.estepper.estepper.model.entity.Usuario;

import jakarta.transaction.Transactional;

public interface MensajeAdminRepository extends JpaRepository<MensajeAdmin, Integer>{
    
    Optional<MensajeAdmin> findById(Integer id);
    List<MensajeAdmin> findByUsuario(Usuario usuario);
    List<MensajeAdmin> findByAdministrador(Administrador administrador);
    List<MensajeAdmin> findByAdministradorAndUsuario(Administrador administrador, Usuario usuario);

    @Modifying
    @Transactional
    @Query("DELETE FROM MensajeAdmin m WHERE m.usuario = :u")
    void deleteAllByUsuario(Usuario u);

    
}
