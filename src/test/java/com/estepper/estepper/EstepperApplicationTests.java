package com.estepper.estepper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.estepper.estepper.model.entity.Administrador;
import com.estepper.estepper.model.entity.Coordinador;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Sesion;
import com.estepper.estepper.model.entity.Grupo;
import com.estepper.estepper.model.enums.Asistencia;
import com.estepper.estepper.model.enums.Estado;
import com.estepper.estepper.model.enums.EstadoSesion;
import com.estepper.estepper.model.enums.Sexo;
import com.estepper.estepper.repository.SesionRepository;
import com.estepper.estepper.repository.UsuarioRepository;
import com.estepper.estepper.repository.GrupoRepository;

@SpringBootTest
class EstepperApplicationTests {

	@Autowired
	private UsuarioRepository usuarioRepo;
	@Autowired
	private SesionRepository sesionRepo;
	@Autowired
	private BCryptPasswordEncoder hash;
	@Autowired
	private GrupoRepository grupoRepo;

	@Test
	public void crearUsuarioTest() {

		//Usuario tipo PARTICIPANTE
		usuarioRepo.save(new Participante(0, 111, "Almudena", "Naharro Muñoz", "almunaha@ucm.es", hash.encode("almupass"), 
		Estado.ALTA, 1, null, 10, 2, 22, 3, Sexo.FEMENINO));

		//Usuario tipo COORDINADOR
		usuarioRepo.save(new Coordinador(0, 222, "Mercedes", "Martinez Campos", "mercedes@madrid.es", hash.encode("mercedespass"), 
		Estado.ALTA));

		//Usuario tipo ADMINISTRADOR
		usuarioRepo.save(new Administrador(0, 333, "Javier", "Gómez Blanco", "javier@ucm.es", hash.encode("javierpass"), 
		Estado.ALTA));
		

		/*
		Usuario retorno = usuarioRepo.save(us);
		assertTrue(retorno.getContrasenia().equalsIgnoreCase(us.getContrasenia()));
		*/
	}

	@Test
	public void crearSesionTest() {
		sesionRepo.save(new Sesion(0,1,EstadoSesion.BLOQUEADA,"no",Asistencia.NO,0.0,0.0));
		
	}

	@Test
	public void crearGrupo(){
	    grupoRepo.save(new Grupo(9,999,"Las saltimbanquis",2));
	}

}
