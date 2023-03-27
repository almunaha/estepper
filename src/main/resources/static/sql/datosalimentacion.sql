-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-03-2023 a las 00:19:34
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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alimentacion`
--

CREATE TABLE `alimentacion` (
  `id` int(11) NOT NULL,
  `fibra_alimentaria` float NOT NULL,
  `grasas_saturadas` float NOT NULL,
  `hidratos_de_carbono` float NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `porcion` float NOT NULL,
  `proteinas` float NOT NULL,
  `sal` float NOT NULL,
  `tipo` enum('DULCE','CARNE_GRASA','EMBUTIDO','CARNE_MAGRA','PESCADO','HUEVO','LEGUMBRE','FRUTOS_SECOS','LACTEOS','ACEITE','VERDURA','FRUTA','CEREALES','ARROZ','PASTA','PAN') DEFAULT NULL,
  `unidad` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `alimentacion`
--

INSERT INTO `alimentacion` (`id`, `fibra_alimentaria`, `grasas_saturadas`, `hidratos_de_carbono`, `nombre`, `porcion`, `proteinas`, `sal`, `tipo`, `unidad`) VALUES
(1, 8, 0.4, 68, 'Pasta Integral', 100, 13, 0.01, 'PASTA', 'GRAMOS'),
(2, 1.7, 0, 9, 'Cebolla', 100, 1.2, 0, 'VERDURA', 'GRAMOS'),
(3, 1.1, 0.1, 2.4, 'Calabacín', 100, 1.4, 0, 'VERDURA', 'GRAMOS'),
(4, 3, 0.1, 5.5, 'Berenjena', 100, 1.1, 0, 'VERDURA', 'GRAMOS'),
(5, 1, 0.1, 0.6, 'Champiñones', 100, 3.1, 0.01, 'VERDURA', 'GRAMOS'),
(6, 0, 0.1, 0, 'Atún claro al natural', 100, 28, 0.5, 'PESCADO', 'GRAMOS'),
(7, 0, 1.4, 0, 'Aceite de oliva', 10, 0, 0, 'ACEITE', 'GRAMOS'),
(8, 0, 0, 0.8, 'Salsa de soja', 10, 1.1, 1.5, 'PESCADO', 'GRAMOS'),
(9, 2.8, 0, 7.3, 'Zanahoria', 100, 0.9, 0, 'VERDURA', 'GRAMOS'),
(10, 3.2, 0.1, 20, 'Pimiento asado', 100, 2.5, 0.38, 'VERDURA', 'GRAMOS'),
(11, 2.1, 0.1, 6, 'Pimiento rojo', 100, 1.3, 0.01, 'VERDURA', 'GRAMOS'),
(12, 2, 0, 4, 'Pimiento verde', 100, 1, 0, 'VERDURA', 'GRAMOS'),
(13, 1.5, 0, 1.8, 'Espárragos verdes', 100, 2.2, 0.01, 'VERDURA', 'GRAMOS'),
(14, 1.2, 0, 3.9, 'Tomate mediano', 100, 0.9, 0.01, 'VERDURA', 'GRAMOS'),
(15, 0.1, 0, 1, 'Diente de ajo', 3, 0.2, 0, 'VERDURA', 'GRAMOS'),
(16, 0.2, 0, 0.6, 'Pimienta negra', 1, 0.1, 0, 'VERDURA', 'GRAMOS'),
(17, 0, 0, 0.1, 'Vinagre', 15, 0, 0.03, 'FRUTA', 'MILILITROS'),
(18, 3.4, 0, 4.7, 'Judías verdes', 100, 1.9, 0, 'LEGUMBRE', 'GRAMOS'),
(19, 0, 0, 0, 'Sal', 1, 0, 1, 'VERDURA', 'GRAMOS'),
(20, 5.7, 0.1, 12.5, 'Guisantes', 100, 5.4, 0.02, 'LEGUMBRE', 'GRAMOS'),
(21, 0.3, 0, 0.4, 'Orégano', 1, 0.2, 0, 'VERDURA', 'GRAMOS'),
(22, 0.1, 0, 0.1, 'Albahaca', 1, 0.1, 0, 'VERDURA', 'GRAMOS'),
(23, 5.4, 0, 10.5, 'Alcachofa', 100, 3.3, 0.01, 'VERDURA', 'GRAMOS'),
(24, 0, 0.2, 0, 'Bacalao desmigado', 100, 19.2, 2.3, 'PESCADO', 'GRAMOS'),
(25, 2.2, 0, 17, 'Patata', 100, 2, 0.01, 'VERDURA', 'GRAMOS'),
(26, 2.7, 0.2, 76.3, 'Harina de trigo', 100, 10.3, 0, 'CEREALES', 'GRAMOS'),
(27, 0, 39.2, 0, 'Manteca de cerdo', 100, 0.5, 0.01, 'CARNE_GRASA', 'GRAMOS'),
(28, 0, 51, 0.6, 'Mantequilla', 100, 0.9, 0.01, 'ACEITE', 'GRAMOS'),
(29, 0, 0, 0, 'Agua', 100, 0, 0, 'ACEITE', 'MILILITROS'),
(30, 0.7, 0, 0.9, 'Perejil', 10, 0.5, 0.03, 'VERDURA', 'GRAMOS'),
(31, 7.9, 0.1, 20.2, 'Lentejas cocidas', 100, 9, 0.05, 'LEGUMBRE', 'GRAMOS'),
(32, 0.3, 0.1, 28.2, 'Arroz cocido', 100, 2.5, 0.01, 'ARROZ', 'GRAMOS'),
(33, 3.7, 0.1, 5.8, 'Pimentón dulce', 10, 1.5, 0.04, 'VERDURA', 'GRAMOS'),
(34, 0.3, 0, 0.6, 'Hoja laurel', 1, 0.1, 0, 'VERDURA', 'UNIDADES'),
(35, 1.3, 0.03, 1.4, 'Lechuga', 100, 1.4, 0.03, 'VERDURA', 'GRAMOS'),
(36, 3.1, 0.05, 2.3, 'Escarola', 100, 1.5, 0.02, 'VERDURA', 'GRAMOS'),
(37, 3.8, 0, 9.2, 'Remolacha', 1, 1.6, 0.064, 'VERDURA', 'UNIDADES'),
(38, 0.5, 0, 1.9, 'Pepino', 1, 0.3, 0.002, 'FRUTA', 'UNIDADES'),
(39, 0, 2, 0.6, 'Huevo cocido', 1, 6, 0.0154, 'HUEVO', 'UNIDADES'),
(40, 0, 2, 0.6, 'Huevo', 1, 6, 0.14, 'HUEVO', 'UNIDADES'),
(42, 0, 2, 9, 'Leche', 200, 6, 0.1, 'LACTEOS', 'MILILITROS'),
(43, 0, 0.9, 0, 'Pechuga de pollo', 100, 31, 0.07, 'CARNE_MAGRA', 'GRAMOS'),
(44, 1.8, 0, 5.8, 'Tomate cherry', 150, 1.5, 0.003, 'VERDURA', 'GRAMOS'),
(45, 7.6, 0.5, 27, 'Garbanzo seco', 100, 9, 0.01, 'LEGUMBRE', 'GRAMOS'),
(46, 1.6, 0, 9.5, 'Puerro', 89, 1.3, 0.035, 'VERDURA', 'GRAMOS'),
(47, 1.8, 0.4, 23, 'Arroz integral', 100, 2.6, 0.001, 'ARROZ', 'GRAMOS'),
(48, 0, 6, 1, 'Queso feta', 30, 4, 0.24, 'LACTEOS', 'GRAMOS'),
(49, 3, 0.5, 34, 'Panecillo integral', 70, 6, 0.325, 'PAN', 'GRAMOS'),
(50, 0, 0.4, 0, 'Merluza', 100, 20, 0.06, 'PESCADO', 'GRAMOS'),
(51, 1.3, 0, 2, 'Repollo', 70, 1, 0.013, 'VERDURA', 'GRAMOS'),
(52, 0, 0, 2.2, 'Vino blanco', 150, 0.1, 0, 'FRUTA', 'MILILITROS'),
(53, 0, 1.8, 0, 'Ternera', 100, 22, 0.056, 'CARNE_GRASA', 'GRAMOS'),
(54, 0, 0.2, 0, 'Gambas', 100, 24, 0.142, 'PESCADO', 'GRAMOS'),
(55, 0, 0.2, 4, 'Almejas', 100, 14, 0.196, 'PESCADO', 'GRAMOS'),
(56, 0, 0.4, 1, 'Sepia', 100, 19, 0.04, 'PESCADO', 'GRAMOS'),
(57, 1.2, 0, 3.3, 'Tomate rallado', 100, 1.1, 0.019, 'VERDURA', 'GRAMOS'),
(58, 2.2, 0.2, 1.2, 'Espinacas', 100, 2.9, 0.079, 'VERDURA', 'GRAMOS');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `recetas`
--

CREATE TABLE `recetas` (
  `id` int(11) NOT NULL,
  `link` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `recetas`
--

INSERT INTO `recetas` (`id`, `link`, `nombre`) VALUES
(1, 'menusemanal1-5.pdf', 'Ensalada de tomate'),
(2, 'menusemanal1-6.pdf', 'Potaje de garbanzos'),
(3, 'menusemanal1-7.pdf', 'Buñuelos de bacalao'),
(4, 'menusemanal1-8.pdf', 'Pasta con verduras y atún'),
(5, 'menusemanal1-9.pdf', 'Pisto con huevo a la plancha'),
(6, 'menusemanal1-10.pdf', 'Lentejas con arroz y verduras'),
(7, 'menusemanal1-11.pdf', 'Ensalada variada'),
(8, 'menusemanal1-12.pdf', 'Tortilla francesa'),
(9, 'menusemanal1-13.pdf', 'Espinacas rehogadas'),
(10, 'menusemanal1-14.pdf', 'Merluza en salsa'),
(11, 'menusemanal1-15.pdf', 'Brochetas de pollo'),
(12, 'menusemanal1-16.pdf', 'Ensalada de garbanzos'),
(13, 'menusemanal1-17.pdf', 'Tortilla de patata y calabacín'),
(14, 'menusemanal1-18.pdf', 'Menestra de verduras'),
(15, 'menusemanal1-19.pdf', 'Ternera en salsa'),
(16, 'menusemanal1-20.pdf', 'Hamburguesa de lentejas'),
(17, 'menusemanal1-21.pdf', 'Paella'),
(18, 'menusemanal1-22.pdf', 'Salteado de legumbres con verduras');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `receta_alimentacion`
--

CREATE TABLE `receta_alimentacion` (
  `receta_id` int(11) NOT NULL,
  `alimentacion_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `receta_alimentacion`
--

INSERT INTO `receta_alimentacion` (`receta_id`, `alimentacion_id`) VALUES
(1, 22),
(2, 19),
(3, 30),
(4, 1),
(4, 2),
(4, 3),
(4, 4),
(4, 5),
(4, 6),
(4, 7),
(4, 8),
(4, 9),
(4, 11),
(4, 13),
(5, 2),
(5, 3),
(5, 4),
(5, 7),
(5, 11),
(5, 12),
(5, 14),
(5, 40),
(6, 19),
(7, 19),
(8, 19),
(9, 19),
(10, 11),
(10, 16),
(11, 2),
(11, 5),
(11, 7),
(11, 11),
(11, 12),
(11, 19),
(11, 43),
(11, 44),
(12, 16),
(13, 19),
(14, 16),
(15, 16),
(16, 7),
(17, 19),
(18, 19);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alimentacion`
--
ALTER TABLE `alimentacion`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `recetas`
--
ALTER TABLE `recetas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `receta_alimentacion`
--
ALTER TABLE `receta_alimentacion`
  ADD KEY `FKmaunmdpev2wi0rn14n3i8b6os` (`alimentacion_id`),
  ADD KEY `FKd4jyi4r6u26upxlob7jqmhg9t` (`receta_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alimentacion`
--
ALTER TABLE `alimentacion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;

--
-- AUTO_INCREMENT de la tabla `recetas`
--
ALTER TABLE `recetas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `receta_alimentacion`
--
ALTER TABLE `receta_alimentacion`
  ADD CONSTRAINT `FKd4jyi4r6u26upxlob7jqmhg9t` FOREIGN KEY (`receta_id`) REFERENCES `recetas` (`id`),
  ADD CONSTRAINT `FKmaunmdpev2wi0rn14n3i8b6os` FOREIGN KEY (`alimentacion_id`) REFERENCES `alimentacion` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
