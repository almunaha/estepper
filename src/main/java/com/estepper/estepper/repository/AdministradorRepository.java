package com.estepper.estepper.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estepper.estepper.model.entity.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {

    Optional<Administrador> findById(Integer id);

}
