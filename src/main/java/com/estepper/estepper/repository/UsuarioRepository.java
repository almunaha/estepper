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
        Usuario findByNombre(String nombre); //select * from usuario where nombre = u.nombre 
        Usuario findByCodigo(String codigo);
        List<Usuario> findByApellidos(String apellidos); //Buscar por apelldios

       void delete(Usuario usuario);

        @Modifying
        @Transactional
        @Query("UPDATE Usuario u SET u.nombre = :nombre, u.apellidos = :apellidos, u.email = :email, u.contrasenia = :contrasenia WHERE u.id = :id")
        void update(String nombre, String apellidos, String email, String contrasenia, Integer id);


}


