package com.estepper.estepper.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estepper.estepper.model.entity.Grupo;

//@Repository
//LOS repository son los DAO, que acceden a la bd  -> los que hacen las consultas a PHPYMYADMIN
public interface GrupoRepository extends JpaRepository<Grupo, Integer>{
        
    Grupo findByNombre(String nombre); //select * from grupo where nombre = g.nombre 
    Grupo findByCodigo(String codigo); //Ver si hacerlo con el código o con el ID
     
}

