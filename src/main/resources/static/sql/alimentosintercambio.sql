-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-04-2023 a las 00:34:19
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
-- Volcado de datos para la tabla `alimentosintercambio`
--

INSERT INTO `alimentosintercambio` (`id`, `grupo`, `gramos`, `nombre`) VALUES
(1, 'PROTEÍNA', 70, 'almejas'),
(2, 'PROTEÍNA', 70, 'centollo'),
(3, 'PROTEÍNA', 100, 'ostras'),
(4, 'PROTEÍNA', 50, 'gambas'),
(5, 'PROTEÍNA', 150, 'percebes'),
(6, 'PROTEÍNA', 24, 'langostinos'),
(7, 'PROTEÍNA', 100, 'mejillones'),
(8, 'PROTEÍNA', 35, 'cigalas'),
(9, 'PROTEÍNA', 100, 'chirlas'),
(10, 'PROTEÍNA', 30, 'langosta'),
(11, 'PROTEÍNA', 110, 'calamares'),
(12, 'PROTEÍNA', 60, 'vieiras'),
(13, 'PROTEÍNA', 100, 'pulpo'),
(15, 'PROTEÍNA', 70, 'bacalao'),
(16, 'PROTEÍNA', 50, 'dorada'),
(17, 'PROTEÍNA', 50, 'gallo'),
(18, 'PROTEÍNA', 60, 'lenguado'),
(19, 'PROTEÍNA', 70, 'lubina'),
(20, 'PROTEÍNA', 60, 'merluza'),
(21, 'PROTEÍNA', 60, 'pescadilla'),
(22, 'PROTEÍNA', 60, 'percas'),
(23, 'PROTEÍNA', 70, 'rape'),
(24, 'PROTEÍNA', 56, 'rodaballo'),
(25, 'PROTEÍNA', 70, 'salmonetas'),
(26, 'PROTEÍNA', 58, 'bacaladitos'),
(27, 'PROTEÍNA', 50, 'congrio'),
(28, 'PROTEÍNA', 55, 'lucio'),
(29, 'PROTEÍNA', 50, 'carpa'),
(34, 'PROTEÍNA', 50, 'atún'),
(35, 'PROTEÍNA', 36, 'bonito'),
(36, 'PROTEÍNA', 36, 'atún (conserva)'),
(37, 'PROTEÍNA', 50, 'besugo'),
(38, 'PROTEÍNA', 50, 'caballa'),
(39, 'PROTEÍNA', 60, 'salmón'),
(40, 'PROTEÍNA', 40, 'sardina'),
(41, 'PROTEÍNA', 50, 'trucha'),
(42, 'PROTEÍNA', 50, 'boquerones'),
(43, 'PROTEÍNA', 50, 'anchoas'),
(44, 'PROTEÍNA', 50, 'palometa'),
(45, 'PROTEÍNA', 50, 'pez espada'),
(46, 'PROTEÍNA', 50, 'chicharros'),
(47, 'PROTEÍNA', 60, 'arenque'),
(48, 'PROTEÍNA', 50, 'buey'),
(49, 'PROTEÍNA', 50, 'cordero'),
(50, 'PROTEÍNA', 50, 'ternera'),
(51, 'PROTEÍNA', 50, 'cerdo'),
(52, 'PROTEÍNA', 50, 'pollo'),
(53, 'PROTEÍNA', 50, 'faisán'),
(54, 'PROTEÍNA', 50, 'gallina'),
(55, 'PROTEÍNA', 50, 'pavo'),
(56, 'PROTEÍNA', 50, 'liebre'),
(57, 'PROTEÍNA', 50, 'queso fresco'),
(58, 'PROTEÍNA', 50, 'jamón hervido'),
(59, 'PROTEÍNA', 50, 'conejo'),
(60, 'PROTEÍNA', 50, 'huevo'),
(61, 'PROTEÍNA', 50, 'jamón serrano'),
(62, 'PROTEÍNA', 50, 'pato'),
(63, 'PROTEÍNA', 50, 'ganso'),
(64, 'PROTEÍNA', 50, 'queso manchego'),
(65, 'PROTEÍNA', 50, 'queso roquefort'),
(66, 'PROTEÍNA', 50, 'queso camembert'),
(67, 'PROTEÍNA', 50, 'queso gruyère'),
(68, 'PROTEÍNA', 50, 'queso de bola'),
(69, 'PROTEÍNA', 50, 'queso untar'),
(70, 'PROTEÍNA', 75, 'embutidos'),
(71, 'PROTEÍNA', 75, 'salchichas'),
(72, 'FÉCULA', 12, 'arroz'),
(73, 'FÉCULA', 50, 'patata'),
(74, 'FÉCULA', 12, 'pasta de sopa'),
(75, 'FÉCULA', 12, 'sémola'),
(76, 'FÉCULA', 12, 'tapioca'),
(77, 'FÉCULA', 40, 'batata'),
(78, 'FÉCULA', 40, 'boniato'),
(79, 'FÉCULA', 20, 'pan blanco'),
(80, 'FÉCULA', 20, 'pan integral'),
(81, 'FÉCULA', 20, 'pan centeno'),
(82, 'FÉCULA', 20, 'pan inglés'),
(83, 'FÉCULA', 18, 'pan (palitos)'),
(84, 'FÉCULA', 15, 'galletas María'),
(85, 'FÉCULA', 18, 'galletas saladas'),
(86, 'FÉCULA', 15, 'biscotes'),
(87, 'FÉCULA', 13, 'harina de trigo'),
(88, 'FÉCULA', 14, 'harina de maíz'),
(89, 'FÉCULA', 14, 'harina de arroz'),
(90, 'FÉCULA', 14, 'harina de avena'),
(91, 'FÉCULA', 20, 'castañas'),
(92, 'FÉCULA', 40, 'maíz'),
(93, 'FÉCULA', 16, 'judías'),
(94, 'FÉCULA', 18, 'garbanzos'),
(95, 'FÉCULA', 16, 'lentejas'),
(96, 'FÉCULA', 20, 'habas'),
(97, 'VERDURA', 400, 'acelgas'),
(98, 'VERDURA', 80, 'alcachofas'),
(99, 'VERDURA', 200, 'berenjenas'),
(100, 'VERDURA', 250, 'berros'),
(101, 'VERDURA', 200, 'calabacín'),
(102, 'VERDURA', 200, 'cardo'),
(103, 'VERDURA', 110, 'cebolla'),
(104, 'VERDURA', 160, 'col'),
(105, 'VERDURA', 120, 'coles Bruselas'),
(106, 'VERDURA', 100, 'berza (grelos)'),
(107, 'VERDURA', 300, 'apio'),
(108, 'VERDURA', 300, 'brécol'),
(109, 'VERDURA', 300, 'endibias'),
(110, 'VERDURA', 120, 'habas verdes'),
(111, 'VERDURA', 200, 'coliflor'),
(112, 'VERDURA', 260, 'champiñón'),
(113, 'VERDURA', 250, 'escarola'),
(114, 'VERDURA', 250, 'espárragos'),
(115, 'VERDURA', 250, 'espinacas'),
(116, 'VERDURA', 60, 'guisantes'),
(117, 'VERDURA', 140, 'judías verdes'),
(118, 'VERDURA', 350, 'lechuga'),
(119, 'VERDURA', 200, 'lombarda'),
(120, 'VERDURA', 200, 'chucrut'),
(121, 'VERDURA', 300, 'calabaza'),
(122, 'VERDURA', 120, 'dientes de león'),
(123, 'VERDURA', 300, 'perifollo'),
(124, 'VERDURA', 300, 'acederas'),
(125, 'VERDURA', 160, 'pimientos'),
(126, 'VERDURA', 350, 'pepinos'),
(127, 'VERDURA', 120, 'puerros'),
(128, 'VERDURA', 250, 'rábanos'),
(129, 'VERDURA', 100, 'remolacha'),
(130, 'VERDURA', 200, 'repollo'),
(131, 'VERDURA', 260, 'setas'),
(132, 'VERDURA', 250, 'tomate'),
(133, 'VERDURA', 110, 'zanahoria'),
(134, 'VERDURA', 300, 'nabos'),
(135, 'VERDURA', 300, 'achicoría'),
(136, 'VERDURA', 100, 'rutabaga'),
(137, 'VERDURA', 300, 'hinojo'),
(138, 'VERDURA', 300, 'ruibarbo'),
(139, 'FRUTA', 90, 'albaricoque'),
(140, 'FRUTA', 70, 'cerezas'),
(141, 'FRUTA', 70, 'ciruelas'),
(142, 'FRUTA', 80, 'chirimoya'),
(143, 'FRUTA', 120, 'fresas'),
(144, 'FRUTA', 160, 'fresón'),
(145, 'FRUTA', 60, 'granada'),
(146, 'FRUTA', 100, 'grosellas'),
(147, 'FRUTA', 70, 'guindas'),
(148, 'FRUTA', 60, 'higos'),
(149, 'FRUTA', 100, 'mandarinas'),
(150, 'FRUTA', 70, 'manzana'),
(151, 'FRUTA', 80, 'melocotón'),
(152, 'FRUTA', 160, 'melón'),
(153, 'FRUTA', 100, 'naranja'),
(154, 'FRUTA', 80, 'paraguayas'),
(155, 'FRUTA', 70, 'pera'),
(156, 'FRUTA', 70, 'piña natural'),
(157, 'FRUTA', 50, 'plátano'),
(158, 'FRUTA', 150, 'sandía'),
(159, 'FRUTA', 50, 'uvas'),
(160, 'GRASA', 10, 'aceite'),
(161, 'GRASA', 12, 'tocino'),
(162, 'GRASA', 30, 'bacon'),
(163, 'GRASA', 20, 'nata'),
(164, 'GRASA', 20, 'nueces'),
(166, 'LÁCTEO', 250, 'yogur'),
(167, 'LÁCTEO', 100, 'queso de Burgos'),
(168, 'LÁCTEO', 200, 'leche');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
