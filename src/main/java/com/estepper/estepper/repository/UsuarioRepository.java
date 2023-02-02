package com.estepper.estepper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estepper.estepper.model.entity.Usuario;
import com.estepper.estepper.model.enums.Rol;

//@Repository
//LOS repository son los DAO, que acceden a la bd  -> los que hacen las consultas a PHPYMYADMIN
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
        Usuario findByNombre(String nombre); //select * from usuario where nombre = u.nombre 
        Usuario findByCodigo(String codigo);
        List <Usuario> findAllByRol(Rol rol);
        List<Usuario> findByApellidos(String apellidos); //Buscar por apelldios

}


