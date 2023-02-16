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

    @Modifying
    @Transactional
    @Query("UPDATE Findrisc SET idParticipante = :idParticipante, puntosedad = :puntosedad, puntosimc = :puntosimc, puntoscmcintura = :puntoscmcintura, ptosactfisica = :ptosactfisica, ptosfrecfruta = :ptosfrecfruta, ptosmedicacion = :ptosmedicacion, ptosglucosa = :ptosglucosa, ptosdiabetes = :ptosdiabetes,  puntuacion = :puntuacion, escalarriesgo = :escalarriesgo WHERE id = :id")
    void updateFindrisc(Integer id,Integer idParticipante,Integer puntosedad, Integer puntosimc, Integer puntoscmcintura, Integer ptosactfisica,
    Integer ptosfrecfruta, Integer ptosmedicacion, Integer ptosglucosa, Integer ptosdiabetes, Integer puntuacion,
    String escalarriesgo);
}
