package com.estepper.estepper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.estepper.estepper.model.entity.FaseValoracion;

import jakarta.transaction.Transactional;

public interface FaseValoracionRepository extends JpaRepository<FaseValoracion, Integer> {
    List<FaseValoracion> findByIdParticipante(Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE Exploracion SET primeravez = :primeravez, peso = :peso, talla = :talla, cmcintura = :cmcintura, edad = :edad, imc = :imc WHERE id = :id")
    void updateExploracion(String primeravez, Integer peso, Integer talla, Integer cmcintura, Integer edad, Integer imc, Integer id);
}
