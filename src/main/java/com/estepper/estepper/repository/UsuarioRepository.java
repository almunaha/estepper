package com.estepper.estepper.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estepper.estepper.model.entity.Usuario;

//@Repository
//LOS repository son los DAO, que acceden a la bd  -> los que hacen las consultas a PHPYMYADMIN
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
        Usuario findByNombre(String nombre); //select * from usuario where nombre = u.nombre 
        Usuario findByCodigo(String codigo);
}
