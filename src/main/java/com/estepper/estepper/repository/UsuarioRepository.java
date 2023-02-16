package com.estepper.estepper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.estepper.estepper.model.entity.Usuario;

import jakarta.transaction.Transactional;

//@Repository
//LOS repository son los DAO, que acceden a la bd  -> los que hacen las consultas a PHPYMYADMIN
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
        Usuario findByNickname(String nickname); //select * from usuario where nombre = u.nombre 
        Usuario findByCodigo(Integer codigo);

       void delete(Usuario usuario);

        @Modifying
        @Transactional
        @Query("UPDATE Usuario u SET u.nickname = :nickname, u.email = :email, u.contrasenia = :contrasenia WHERE u.id = :id")
        void update(String nickname, String email, String contrasenia, Integer id);


}


