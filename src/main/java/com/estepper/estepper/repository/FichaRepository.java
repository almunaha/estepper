package com.estepper.estepper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estepper.estepper.model.entity.Ficha;

public interface FichaRepository extends JpaRepository<Ficha, Integer>{
    List<Ficha> findByIdSesion(Integer id);
}
