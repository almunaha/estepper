package com.estepper.estepper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;

import com.estepper.estepper.model.entity.AlimentacionVal;
import com.estepper.estepper.model.entity.Participante;


   public interface AlimentacionValRepository extends JpaRepository<AlimentacionVal, Integer>{
        @Modifying
        @Transactional
        @Query("UPDATE AlimentacionVal a SET a.aceite = :aceite, a.ptosaceite = :ptosaceite, a.racaceite = :racaceite, a.ptosracaceite = :ptosracaceite, a.racverdura = :racverdura, a.ptosracverdura = :ptosracverdura, a.racfruta = :racfruta, a.ptosracfruta = :ptosracfruta, a.raccarne = :raccarne, a.ptosraccarne = :ptosraccarne, a.racmantequilla = :racmantequilla, a.ptosracmantequilla = :ptosracmantequilla, a.racbebidas = :racbebidas, a.ptosracbebidas = :ptosracbebidas, a.racvino = :racvino, a.ptosracvino = :ptosracvino, a.raclegumbres = :raclegumbres, a.ptosraclegumbres = :ptosraclegumbres, a.racpescado = :racpescado, a.ptosracpescado = :ptosracpescado, a.racreposteria = :racreposteria, a.ptosracreposteria = :ptosracreposteria, a.racfrutosecos = :racfrutosecos, a.ptosracfrutosecos = :ptosracfrutosecos, a.carneblanca = :carneblanca, a.ptoscarneblanca = :ptoscarneblanca, a.racsofrito = :racsofrito, a.ptosracsofrito = :ptosracsofrito, a.puntuacion = :puntuacion, a.adherencia = :adherencia WHERE a.participante = :participante")
        void updateAlimentacionVal(String aceite, Integer ptosaceite, Integer racaceite,
        Integer ptosracaceite, Integer racverdura, Integer ptosracverdura, Integer racfruta, Integer ptosracfruta,
        Integer raccarne, Integer ptosraccarne, Integer racmantequilla, Integer ptosracmantequilla,
        Integer racbebidas, Integer ptosracbebidas, Integer racvino, Integer ptosracvino, Integer raclegumbres,
        Integer ptosraclegumbres, Integer racpescado, Integer ptosracpescado, Integer racreposteria,
        Integer ptosracreposteria, Integer racfrutosecos, Integer ptosracfrutosecos, String carneblanca,
        Integer ptoscarneblanca, Integer racsofrito, Integer ptosracsofrito, Integer puntuacion,
        String adherencia, Participante participante);

        AlimentacionVal findByParticipante(Participante participante);

        @Modifying
        @Transactional
        @Query("DELETE FROM AlimentacionVal a WHERE a.participante = :participante")
        void deleteByParticipante(Participante participante);
   }


