package com.estepper.estepper;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.estepper.estepper.model.entity.Administrador;
import com.estepper.estepper.model.entity.Coordinador;
import com.estepper.estepper.model.entity.Exploracion;
import com.estepper.estepper.model.entity.Findrisc;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Grupo;
import com.estepper.estepper.model.entity.Objetivo;
import com.estepper.estepper.model.enums.EstadoObjetivo;
import com.estepper.estepper.model.enums.Estado;
import com.estepper.estepper.model.enums.Sexo;
import com.estepper.estepper.repository.UsuarioRepository;
import com.estepper.estepper.repository.GrupoRepository;
import com.estepper.estepper.repository.ExploracionRepository;
import com.estepper.estepper.repository.FindriscRepository;

@SpringBootTest
class EstepperApplicationTests {

	@Autowired
	private UsuarioRepository usuarioRepo;
	@Autowired
	private BCryptPasswordEncoder hash;
	@Autowired
	private GrupoRepository grupoRepo;
	@Autowired
	private GrupoRepository objetivoRepo;

	@Autowired
	private FindriscRepository findriscRepo;

	@Autowired
	private ExploracionRepository exploracionRepo;

	@Test
	public void crearUsuarioTest() {

		//Usuario tipo PARTICIPANTE
		Participante almu = usuarioRepo.save(new Participante(0, 111, "Almudena",  "almunaha@ucm.es", hash.encode("almupass"), 
		Estado.BAJA, null, null, 0, 0, 0, 0, null,"/img/p1.png"));
		exploracionRepo.save(new Exploracion(0, almu, Sexo.MASCULINO, "no", 0, 0, 0, 0, 0));
		findriscRepo.save(new Findrisc(0, almu, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Bajo"));
		

		//Usuario tipo COORDINADOR
		Coordinador coor = usuarioRepo.save(new Coordinador(0, 222, "Mercedes",  "mercedes@madrid.es", hash.encode("mercedespass"), 
		Estado.ALTA));

		//Usuario tipo ADMINISTRADOR
		usuarioRepo.save(new Administrador(0, 333, "Javier",  "javier@ucm.es", hash.encode("javierpass"), 
		Estado.ALTA));
		
	   // grupoRepo.save(new Grupo(1, coor.getId(), "909A67BZ5","Las saltimbanquis",0));

		/*
		Usuario retorno = usuarioRepo.save(us);
		assertTrue(retorno.getContrasenia().equalsIgnoreCase(us.getContrasenia()));
		*/
	}

	
	


}
