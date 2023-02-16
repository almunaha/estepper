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
		usuarioRepo.save(new Participante(0, 111, "Almudena",  "almunaha@ucm.es", hash.encode("almupass"), 
		Estado.ALTA, 1, null, 10, 2, 22, 3, Sexo.FEMENINO,"/img/p1.png"));

		usuarioRepo.save(new Participante(1, 444, "Inés","ineher02@ucm.es", hash.encode("inespass"), 
		Estado.ALTA, 1, null, 5, 2, 22, 3, Sexo.FEMENINO,"/img/p1.png"));

		usuarioRepo.save(new Participante(2, 555, "Patricia", "pplata@ucm.es", hash.encode("patripass"), 
		Estado.ALTA, 1, null, 5, 2, 22, 3, Sexo.FEMENINO,"/img/p1.png"));

		usuarioRepo.save(new Participante(3, 666, "Placi",  "placi@gmail.com", hash.encode("placipass"), 
		Estado.ALTA, 1, null, 5, 2, 55, 3, Sexo.FEMENINO,"/img/p1.png"));
		
		usuarioRepo.save(new Participante(4, 777, "Diana",  "diana@gmail.com", hash.encode("mamapass"), 
		Estado.ALTA, 1, null, 5, 2, 54, 3, Sexo.FEMENINO,"/img/p1.png"));



		//Usuario tipo COORDINADOR
		usuarioRepo.save(new Coordinador(0, 222, "Mercedes",  "mercedes@madrid.es", hash.encode("mercedespass"), 
		Estado.ALTA));

		//Usuario tipo ADMINISTRADOR
		usuarioRepo.save(new Administrador(0, 333, "Javier",  "javier@ucm.es", hash.encode("javierpass"), 
		Estado.ALTA));
		

		/*
		Usuario retorno = usuarioRepo.save(us);
		assertTrue(retorno.getContrasenia().equalsIgnoreCase(us.getContrasenia()));
		*/
	}
	
	@Test
	public void crearGrupo(){
	    grupoRepo.save(new Grupo(1,990,"Las saltimbanquis",0));
		grupoRepo.save(new Grupo(2,991,"Los viajeros disfrutones",0));
		grupoRepo.save(new Grupo(3,992,"Los escarabajos",0));
		grupoRepo.save(new Grupo(4,993,"Los súper deportistas",0));
	}

}
