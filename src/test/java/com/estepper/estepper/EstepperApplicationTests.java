package com.estepper.estepper;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.estepper.estepper.model.entity.Usuario;
import com.estepper.estepper.model.enums.Estado;
import com.estepper.estepper.model.enums.Rol;
import com.estepper.estepper.repository.UsuarioRepository;

@SpringBootTest
class EstepperApplicationTests {

	@Autowired
	private UsuarioRepository usuarioRepo;

	@Autowired
	private BCryptPasswordEncoder hash;

	@Test
	public void crearUsuarioTest() {
		Usuario us = new Usuario();
		us.setId(1);
		us.setNombre("mercedes");
		us.setContrasenia(hash.encode("mercedespass"));
		us.setCodigo(222);
		us.setApellidos("Martinez Campos");
		us.setEmail("mercedes@madrid.es");
		us.setRol(Rol.COORDINADOR);
		us.setEstadoCuenta(Estado.ALTA);
		Usuario retorno = usuarioRepo.save(us);
		
		assertTrue(retorno.getContrasenia().equalsIgnoreCase(us.getContrasenia()));

		usuarioRepo.save(new Usuario(2, 333, "Almudena", "Naharro Muñoz", "almunaha@ucm.es",
		hash.encode("almupass"), Rol.ADMINISTRADOR, Estado.ALTA));

		usuarioRepo.save(new Usuario(3, 893, "María", "Díaz Blanco", "maria@ucm.es",
		hash.encode("mariapass"), Rol.PARTICIPANTE, Estado.ALTA));

	}

}
