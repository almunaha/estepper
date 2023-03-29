package com.estepper.estepper;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.estepper.estepper.model.entity.Administrador;
//import com.estepper.estepper.model.entity.Alimentacion;
import com.estepper.estepper.model.entity.Coordinador;
import com.estepper.estepper.model.entity.Exploracion;
import com.estepper.estepper.model.entity.Findrisc;
import com.estepper.estepper.model.entity.Participante;
import com.estepper.estepper.model.entity.Grupo;
import com.estepper.estepper.model.enums.Estado;
import com.estepper.estepper.model.enums.Sexo;
//import com.estepper.estepper.model.enums.TipoAlimentacion;
//import com.estepper.estepper.model.enums.TipoUnidad;
import com.estepper.estepper.repository.UsuarioRepository;
import com.estepper.estepper.repository.GrupoRepository;
//import com.estepper.estepper.repository.AlimentacionRepository;
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
	private FindriscRepository findriscRepo;

	@Autowired
	private ExploracionRepository exploracionRepo;

	// @Autowired
	// private AlimentacionRepository alimentacionRepo;

	@Test
	public void crearUsuarioTest() {

		// Usuario tipo PARTICIPANTE
		Participante almu = usuarioRepo
				.save(new Participante(0, 111, "Almudena", "almunaha@ucm.es", hash.encode("almupass"),
						Estado.BAJA, null, null, 0.0, 0, 0, 0, null, "/img/p1.png", 0.0));
		exploracionRepo.save(new Exploracion(0, almu, Sexo.MASCULINO, "no", 0.0, 0.0, 0, 0, 0));
		findriscRepo.save(new Findrisc(0, almu, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Bajo"));

		// Usuario tipo COORDINADOR
		Coordinador coor = usuarioRepo
				.save(new Coordinador(0, 222, "Mercedes", "mercedes@madrid.es", hash.encode("mercedespass"),
						Estado.ALTA));

		// Usuario tipo ADMINISTRADOR
		usuarioRepo.save(new Administrador(0, 333, "Javier", "javier@ucm.es", hash.encode("javierpass"),
				Estado.ALTA));

		grupoRepo.save(new Grupo(1, coor.getId(), "909A67BZ5", "Las saltimbanquis", 0, LocalDate.now(), null, null));

		/*
		 * Usuario retorno = usuarioRepo.save(us);
		 * assertTrue(retorno.getContrasenia().equalsIgnoreCase(us.getContrasenia()));
		 */
	}

	@Test
	public void crearAlimentacion() {
		//METER EL INSERT INTO EN LA BASE DE DATOS EN VEZ DE GENERAR ESTE TEST

		// alimentacionRepo.save(new Alimentacion(1, "Pasta Integral", 0.01f, 8.0f, 0.4f, 68.0f, 13.0f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.PASTA));
		// alimentacionRepo.save(new Alimentacion(2, "Cebolla", 1.7f, 0.0f, 9.0f, 100.0f, 1.2f, TipoUnidad.GRAMOS, 100.0f,
		// 		TipoAlimentacion.VERDURA));
		// alimentacionRepo.save(new Alimentacion(3, "Calabacín", 1.1f, 0.1f, 2.4f, 100.0f, 1.4f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.VERDURA));
		// alimentacionRepo.save(new Alimentacion(4, "Berenjena", 3.0f, 0.1f, 5.5f, 100.0f, 1.1f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.VERDURA));
		// alimentacionRepo.save(new Alimentacion(5, "Champiñones", 1.0f, 0.1f, 0.6f, 100.0f, 3.1f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.VERDURA));
		// alimentacionRepo.save(new Alimentacion(6, "Atún claro al natural", 0.0f, 0.1f, 0.0f, 100.0f, 28.0f,
		// 		TipoUnidad.GRAMOS, 100.0f, TipoAlimentacion.PESCADO));
		// alimentacionRepo.save(new Alimentacion(7, "Aceite de oliva", 0.0f, 1.4f, 0.0f, 10.0f, 0.0f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.ACEITE));
		// alimentacionRepo.save(new Alimentacion(8, "Salsa de soja", 0.0f, 0.0f, 0.8f, 10.0f, 1.1f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.PESCADO));
		// alimentacionRepo.save(new Alimentacion(9, "Zanahoria", 2.8f, 0.0f, 7.3f, 100.0f, 0.9f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.VERDURA));
		// alimentacionRepo.save(new Alimentacion(10, "Pimiento asado", 3.2f, 0.1f, 20.0f, 100.0f, 2.5f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.VERDURA));
		// alimentacionRepo.save(new Alimentacion(11, "Pimiento rojo", 0.01f, 0.1f, 6.0f, 100.0f, 1.3f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.VERDURA));
		// alimentacionRepo.save(new Alimentacion(12, "Pimiento verde", 0.0f, 0.0f, 4.0f, 100.0f, 1.0f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.VERDURA));
		// alimentacionRepo.save(new Alimentacion(13, "Espárragos verdes", 0.01f, 0.0f, 1.8f, 100.0f, 2.2f,
		// 		TipoUnidad.GRAMOS, 100.0f, TipoAlimentacion.VERDURA));
		// alimentacionRepo.save(new Alimentacion(14, "Tomate mediano", 0.01f, 0.0f, 3.9f, 100.0f, 0.9f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.VERDURA));
		// alimentacionRepo.save(new Alimentacion(15, "Diente de ajo", 0.0f, 0.0f, 1.0f, 3.0f, 0.2f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.VERDURA));
		// alimentacionRepo.save(new Alimentacion(16, "Pimienta negra", 0.0f, 0.0f, 0.6f, 1.0f, 0.1f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.VERDURA));
		// alimentacionRepo.save(new Alimentacion(17, "Vinagre", 0.03f, 0.0f, 0.1f, 15.0f, 0.0f, TipoUnidad.MILILITROS,
		// 		100.0f, TipoAlimentacion.FRUTA));
		// alimentacionRepo.save(new Alimentacion(18, "Judías verdes", 0.0f, 3.4f, 4.7f, 100.0f, 1.9f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.LEGUMBRE));
		// alimentacionRepo.save(new Alimentacion(19, "Sal", 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, TipoUnidad.GRAMOS, 1.0f,
		// 		TipoAlimentacion.VERDURA));
		// alimentacionRepo.save(new Alimentacion(20, "Guisantes", 0.1f, 5.7f, 12.5f, 100.0f, 5.4f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.LEGUMBRE));
		// alimentacionRepo.save(new Alimentacion(21, "Orégano", 0.0f, 0.3f, 0.4f, 1.0f, 0.2f, TipoUnidad.GRAMOS, 1.0f,
		// 		TipoAlimentacion.VERDURA));
		// alimentacionRepo.save(new Alimentacion(22, "Albahaca", 0.0f, 0.1f, 0.1f, 1.0f, 0.1f, TipoUnidad.GRAMOS, 1.0f,
		// 		TipoAlimentacion.VERDURA));
		// alimentacionRepo.save(new Alimentacion(23, "Alcachofa", 0.01f, 5.4f, 10.5f, 100.0f, 3.3f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.VERDURA));
		// alimentacionRepo.save(new Alimentacion(24, "Bacalao desmigado", 2.3f, 0.0f, 0.2f, 100.0f, 19.2f,
		// 		TipoUnidad.GRAMOS, 100.0f, TipoAlimentacion.PESCADO));
		// alimentacionRepo.save(new Alimentacion(25, "Patata", 0.01f, 2.2f, 17.0f, 100.0f, 2.0f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.VERDURA));
		// alimentacionRepo.save(new Alimentacion(26, "Harina de trigo", 0.0f, 2.7f, 76.3f, 100.0f, 10.3f,
		// 		TipoUnidad.GRAMOS, 100.0f, TipoAlimentacion.CEREALES));
		// alimentacionRepo.save(new Alimentacion(27, "Manteca de cerdo", 0.0f, 39.2f, 0.0f, 100.0f, 0.5f,
		// 		TipoUnidad.GRAMOS, 100.0f, TipoAlimentacion.CARNE_GRASA));
		// alimentacionRepo.save(new Alimentacion(28, "Mantequilla", 0.0f, 51.0f, 0.6f, 100.0f, 0.9f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.ACEITE));
		// alimentacionRepo.save(new Alimentacion(29, "Agua", 0.0f, 0.0f, 0.0f, 100.0f, 0.0f, TipoUnidad.MILILITROS,
		// 		100.0f, TipoAlimentacion.ACEITE));
		// alimentacionRepo.save(new Alimentacion(30, "Perejil", 0.7f, 0.0f, 0.9f, 10.0f, 0.5f, TipoUnidad.GRAMOS, 100.0f,
		// 		TipoAlimentacion.VERDURA));
		// alimentacionRepo.save(new Alimentacion(31, "Lentejas cocidas", 7.9f, 0.1f, 20.2f, 100.0f, 9.0f,
		// 		TipoUnidad.GRAMOS, 100.0f, TipoAlimentacion.LEGUMBRE));
		// alimentacionRepo.save(new Alimentacion(32, "Arroz cocido", 0.3f, 0.1f, 28.2f, 100.0f, 2.5f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.ARROZ));
		// alimentacionRepo.save(new Alimentacion(33, "Pimentón dulce", 3.7f, 0.1f, 5.8f, 10.0f, 1.5f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.VERDURA));
		// alimentacionRepo.save(new Alimentacion(34, "Hoja laurel", 0.1f, 0.0f, 0.6f, 1.0f, 0.1f, TipoUnidad.UNIDADES,
		// 		1.0f, TipoAlimentacion.VERDURA));
		// alimentacionRepo.save(new Alimentacion(35, "Lechuga", 0.03f, 0.03f, 1.4f, 100.0f, 1.4f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.VERDURA));
		// alimentacionRepo.save(new Alimentacion(36, "Escarola", 0.05f, 0.02f, 2.3f, 100.0f, 1.5f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.VERDURA));
		// alimentacionRepo.save(new Alimentacion(37, "Remolacha", 0.064f, 0.0f, 9.2f, 1.0f, 1.6f, TipoUnidad.UNIDADES,
		// 		1.0f, TipoAlimentacion.VERDURA));
		// alimentacionRepo.save(new Alimentacion(38, "Pepino", 0.002f, 0.0f, 1.9f, 1.0f, 0.3f, TipoUnidad.UNIDADES, 1.0f,
		// 		TipoAlimentacion.FRUTA));
		// alimentacionRepo.save(new Alimentacion(39, "Huevo cocido", 0.0154f, 2.0f, 0.6f, 1.0f, 6.0f, TipoUnidad.UNIDADES,
		// 		1.0f, TipoAlimentacion.HUEVO));
		// alimentacionRepo.save(new Alimentacion(40, "Huevo", 0.14f, 2.0f, 0.6f, 1.0f, 6.0f, TipoUnidad.UNIDADES, 1.0f,
		// 		TipoAlimentacion.HUEVO));
		// alimentacionRepo.save(new Alimentacion(42, "Leche", 0.1f, 2.0f, 9.0f, 200.0f, 6.0f, TipoUnidad.MILILITROS, 1.0f,
		// 		TipoAlimentacion.LACTEOS));
		// alimentacionRepo.save(new Alimentacion(43, "Pechuga de pollo", 0.9f, 6.0f, 0.0f, 100.0f, 31.0f,
		// 		TipoUnidad.GRAMOS, 100.0f, TipoAlimentacion.CARNE_MAGRA));
		// alimentacionRepo.save(new Alimentacion(44, "Tomate cherry", 1.8f, 0.0f, 5.8f, 150.0f, 1.5f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.VERDURA));
		// alimentacionRepo.save(new Alimentacion(45, "Garbanzo seco", 7.6f, 0.5f, 27.0f, 100.0f, 9.0f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.LEGUMBRE));
		// alimentacionRepo.save(new Alimentacion(46, "Puerro", 1.6f, 0.0f, 9.5f, 89.0f, 1.3f, TipoUnidad.GRAMOS, 100.0f,
		// 		TipoAlimentacion.VERDURA));
		// alimentacionRepo.save(new Alimentacion(47, "Arroz integral", 0.0f, 6.0f, 23.0f, 100.0f, 2.6f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.ARROZ));
		// alimentacionRepo.save(new Alimentacion(48, "Queso feta", 0.0f, 6.0f, 1.0f, 30.0f, 4.0f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.LACTEOS));
		// alimentacionRepo.save(new Alimentacion(49, "Panecillo integral", 3.0f, 0.5f, 34.0f, 70.0f, 6.0f,
		// 		TipoUnidad.GRAMOS, 100.0f, TipoAlimentacion.PAN));
		// alimentacionRepo.save(new Alimentacion(50, "Merluza", 0.06f, 0.4f, 0.0f, 100.0f, 20.0f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.PESCADO));
		// alimentacionRepo.save(new Alimentacion(51, "Repollo", 0.013f, 0.0f, 2.0f, 70.0f, 1.0f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.VERDURA));
		// alimentacionRepo.save(new Alimentacion(52, "Vino blanco", 0.0f, 0.0f, 2.2f, 150.0f, 0.1f, TipoUnidad.MILILITROS,
		// 		100.0f, TipoAlimentacion.FRUTA));
		// alimentacionRepo.save(new Alimentacion(53, "Ternera", 0.056f, 0.0f, 0.0f, 100.0f, 22.0f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.CARNE_GRASA));
		// alimentacionRepo.save(new Alimentacion(54, "Gambas", 0.142f, 0.2f, 0.0f, 100.0f, 24.0f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.PESCADO));
		// alimentacionRepo.save(new Alimentacion(55, "Almejas", 0.196f, 0.2f, 4.0f, 100.0f, 14.0f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.PESCADO));
		// alimentacionRepo.save(new Alimentacion(56, "Sepia", 0.04f, 0.4f, 1.0f, 100.0f, 19.0f, TipoUnidad.GRAMOS, 100.0f,
		// 		TipoAlimentacion.PESCADO));
		// alimentacionRepo.save(new Alimentacion(57, "Tomate rallado", 0.019f, 0.0f, 3.3f, 100.0f, 1.1f,
		// 		TipoUnidad.GRAMOS, 100.0f, TipoAlimentacion.VERDURA));
		// alimentacionRepo.save(new Alimentacion(41, "Espinacas", 0.079f, 0.2f, 1.2f, 2.9f, 0.079f, TipoUnidad.GRAMOS,
		// 		100.0f, TipoAlimentacion.VERDURA));
	}

}
