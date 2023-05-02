-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-04-2023 a las 18:01:01
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `estepper`
--

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `codigo`, `contrasenia`, `email`, `estado_cuenta`, `nickname`,`foto_usuario`) VALUES
(2, 172, '$2a$10$XaVd6pxk4/ooyaBelcAfheM0TlMQzzg1tkRA5g2beC6vJ5XujEyq6', 'pplata@ucm.es', 'ALTA', 'Patricia','/img/p1.png'), -- Participante recién dado de alta
(3, 52654, '$2a$10$QEE77L9EWlFAjIdflmVGDOOQBEM0iCkFXlhjUwHwgaXY0OyZ5p2Ta', 'almunaha@ucm.es', 'ALTA', 'Almudena','/img/p1.png'), -- Participante recién dado de alta
(4, 98129, '$2a$10$NkmDZLFCLWxUTkag.PdQkexQsUv5k1b6mGXljXawlg8oVRowrFRGi', 'ineher02@ucm.es', 'BAJA', 'Inés','/img/p1.png'), -- Participante recién registrado. Estado baja.
(5, 333, '$2a$10$9iotMQ9mCZTrM3bmoVlM.e9AgHiq31KwGGDLC21EOg31lnlCIblNi', 'javier@ucm.es', 'ALTA', 'Javier','/img/p1.png'), -- Administrador principal
(6, 222, '$2a$10$jUFlLILjJJOmUOpy23NlyugxyGHKVE/zJxmRKkjWLDXBixlEljT7G', 'proyectoestepper@gmail.com', 'ALTA', 'Mercedes','/img/p1.png'); -- Coordinador principal

--
-- Volcado de datos para la tabla `administradores`
--

INSERT INTO `administradores` (`id`) VALUES
(5);

--
-- Volcado de datos para la tabla `coordinadores`
--

INSERT INTO `coordinadores` (`id`) VALUES
(6);

--
-- Volcado de datos para la tabla `grupos`
--

INSERT INTO `grupos` (`id`, `codigo`, `estado`, `fecha_fin_grupo`, `fecha_inicio_grupo`, `foto_grupo`,`nombre`, `num_participantes`,`id_coordinador`) VALUES
(1, 'CM3SEOCFTBD02G2', 'ACTIVO', NULL, '2023-04-13', '/img/grupos/grupo1.png','Clase A', 2,6),
(2, 'CFH7QMBDW2SCI66', 'ACTIVO', '2023-04-21', '2023-04-13', '/img/grupos/grupo1.png','Clase B', 1,6);

--
-- Volcado de datos para la tabla `participantes`
--

INSERT INTO `participantes` (`asistencia`, `edad`, `id_coordinador`, `perdida_de_peso`, `perdidacmcintura`, `sesiones_completas`, `sexo`, `id`, `id_grupo`) VALUES
(0, 40, 6, 0, 0, 0, 'FEMENINO', 2, 1),
(0, 52, 6, 0, 0, 0, 'FEMENINO', 3, 2),
(0, NULL, NULL, 0, 0, 0, NULL, 4, 1);

--
-- Volcado de datos para la tabla `fasevaloracion`
--

INSERT INTO `fasevaloracion` (`id`, `id_participante`) VALUES
(1, 2),
(2, 2),
(7, 2),
(8, 2),
(9, 2),
(10, 2),
(3, 3),
(4, 3),
(11, 3),
(12, 3),
(13, 3),
(14, 3),
(5, 4),
(6, 4);

--
-- Volcado de datos para la tabla `actividadfisica`
--

INSERT INTO `actividadfisica` (`clasificacion`, `horaafm`, `horaafv`, `horacaminar`, `horasentado`, `metsafm`, `metsafv`, `metscaminar`, `metstotales`, `minafm`, `minafv`, `mincaminar`, `minsentado`, `vecesafm`, `vecesafv`, `vecescaminar`, `id`) VALUES
('moderada', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10),
('moderada', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 14);

--
-- Volcado de datos para la tabla `alimentacionval`
--

INSERT INTO `alimentacionval` (`aceite`, `adherencia`, `carneblanca`, `ptosaceite`, `ptoscarneblanca`, `ptosracaceite`, `ptosracbebidas`, `ptosraccarne`, `ptosracfruta`, `ptosracfrutosecos`, `ptosraclegumbres`, `ptosracmantequilla`, `ptosracpescado`, `ptosracreposteria`, `ptosracsofrito`, `ptosracverdura`, `ptosracvino`, `puntuacion`, `racaceite`, `racbebidas`, `raccarne`, `racfruta`, `racfrutosecos`, `raclegumbres`, `racmantequilla`, `racpescado`, `racreposteria`, `racsofrito`, `racverdura`, `racvino`, `id`) VALUES
('no', 'baja', 'no', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9),
('no', 'baja', 'no', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 13);

--
-- Volcado de datos para la tabla `antecedentes`
--

INSERT INTO `antecedentes` (`dejardefumar`, `dislipemias`, `ecv`, `especificar`, `fuma`, `hta`, `medicacion`, `patmental`, `patmuscesq`, `patsensorial`, `tadiastolica`, `tasistolica`, `tiroides`, `id`) VALUES
('no', 'no', 'no', '', 'no', 'no', 'no', 'no', 'no', 'no', 0, 0, 'no', 8),
('no', 'no', 'no', '', 'no', 'no', 'no', 'no', 'no', 'no', 0, 0, 'no', 12);

--
-- Volcado de datos para la tabla `clasificacion`
--

INSERT INTO `clasificacion` (`actividadfisica`, `analiticahecha`, `clasificacionusuario`, `colesterol`, `glucemia`, `hba1c`, `hdl`, `ldl`, `montesa`, `motivomontesa`, `motivotaller`, `pediranalitica`, `sog`, `taller`, `trigliceridos`, `id`) VALUES
('no', 'no', 0, 0, 0, 0, 0, 0, 'no', '', '', 'no', 0, 'no', 0, 7),
('no', 'no', 0, 0, 0, 0, 0, 0, 'no', '', '', 'no', 0, 'no', 0, 11);

--
-- Volcado de datos para la tabla `exploracion`
--

INSERT INTO `exploracion` (`cmcintura`, `edad`, `imc`, `peso`, `primeravez`, `sexo`, `talla`, `id`) VALUES
(98, 40, 34, 87, 'si', 'FEMENINO', 160, 1),
(90, 52, 38, 98, 'si', 'FEMENINO', 160, 3),
(0, 0, 0, 0, 'no', 'MASCULINO', 0, 5);

--
-- Volcado de datos para la tabla `findrisc`
--

INSERT INTO `findrisc` (`escalarriesgo`, `ptosactfisica`, `ptosdiabetes`, `ptosfrecfruta`, `ptosglucosa`, `ptosmedicacion`, `puntoscmcintura`, `puntosedad`, `puntosimc`, `puntuacion`, `id`) VALUES
(NULL, 2, 0, 1, 5, 2, NULL, NULL, NULL, 17, 2),
(NULL, 2, 0, 1, 5, 2, NULL, NULL, NULL, 19, 4),
('Bajo', 0, 0, 0, 0, 0, 0, 0, 0, 0, 6);

--
-- Volcado de datos para la tabla `fichas`
--

INSERT INTO `fichas` (`id`, `id_participante`) VALUES
(1, 2),
(2, 2),
(3, 2),
(4, 2),
(5, 2),
(6, 2),
(7, 3),
(8, 3),
(9, 3),
(10, 3),
(11, 3),
(12, 3);

--
-- Volcado de datos para la tabla `ficha_eleccion`
--

INSERT INTO `ficha_eleccion` (`num_eleccion`, `texto`, `id`) VALUES
(1, '', 1),
(2, '', 2),
(3, '', 3),
(4, '', 4),
(1, '', 7),
(2, '', 8),
(3, '', 9),
(4, '', 10);

--
-- Volcado de datos para la tabla `ficha_objetivo`
--

INSERT INTO `ficha_objetivo` (`objetivo`, `perdida`, `id`) VALUES
(NULL, NULL, 6),
(NULL, NULL, 12);

--
-- Volcado de datos para la tabla `ficha_taller`
--

INSERT INTO `ficha_taller` (`capacidad`, `dificultades`, `importancia`, `razones`, `temores`, `id`) VALUES
(0, '', 0, '', '', 5),
(0, '', 0, '', '', 11);

--
-- Volcado de datos para la tabla `sesiones`
--

INSERT INTO `sesiones` (`id`, `asistencia`, `cms_perdidos`, `estado`, `num_sesion`, `observaciones`, `peso_perdido`, `id_participante`) VALUES
(1, 'NO', 0, 'ENCURSO', 1, '', 0, 2),
(2, 'NO', 0, 'ENCURSO', 2, '', 0, 2),
(3, 'NO', 0, 'ENCURSO', 3, '', 0, 2),
(4, 'NO', 0, 'ENCURSO', 4, '', 0, 2),
(5, 'NO', 0, 'ENCURSO', 5, '', 0, 2),
(6, 'NO', 0, 'ENCURSO', 6, '', 0, 2),
(7, 'NO', 0, 'ENCURSO', 7, '', 0, 2),
(8, 'NO', 0, 'ENCURSO', 8, '', 0, 2),
(9, 'NO', 0, 'ENCURSO', 9, '', 0, 2),
(10, 'NO', 0, 'ENCURSO', 10, '', 0, 2),
(11, 'NO', 0, 'ENCURSO', 1, '', 0, 3),
(12, 'NO', 0, 'ENCURSO', 2, '', 0, 3),
(13, 'NO', 0, 'ENCURSO', 3, '', 0, 3),
(14, 'NO', 0, 'ENCURSO', 4, '', 0, 3),
(15, 'NO', 0, 'ENCURSO', 5, '', 0, 3),
(16, 'NO', 0, 'ENCURSO', 6, '', 0, 3),
(17, 'NO', 0, 'ENCURSO', 7, '', 0, 3),
(18, 'NO', 0, 'ENCURSO', 8, '', 0, 3),
(19, 'NO', 0, 'ENCURSO', 9, '', 0, 3),
(20, 'NO', 0, 'ENCURSO', 10, '', 0, 3);

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
