package com.estepper.estepper.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estepper.estepper.model.entity.Mensaje;





public interface MensajeRepository extends JpaRepository<Mensaje, Integer>{
    
    Optional<Mensaje> findById(Integer id);
    //List<Mensaje> findByIdUsuario(Integer idUsuario);

  
}
