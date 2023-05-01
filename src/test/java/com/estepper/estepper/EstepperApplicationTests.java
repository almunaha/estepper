package com.estepper.estepper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.estepper.estepper.model.entity.Administrador;
import com.estepper.estepper.model.entity.Coordinador;
import com.estepper.estepper.model.enums.Estado;
import com.estepper.estepper.repository.UsuarioRepository;

@SpringBootTest
class EstepperApplicationTests {

	@Autowired
	private UsuarioRepository usuarioRepo;
	@Autowired
	private BCryptPasswordEncoder hash;

	@Test
	public void crearAdministrador() {
		usuarioRepo.save(new Administrador(0, 333, "Javier", "javier@ucm.es", hash.encode("javierpass"),
				Estado.ALTA,"/img/p1.png"));
	}

	@Test
	public void crearCoordinador() {
		usuarioRepo.save(new Coordinador(0, 222, "Mercedes", "proyectoestepper@gmail.com", hash.encode("mercedespass"),
						Estado.ALTA,"/img/p1.png"));
	}


}
