-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: db:3306
-- Tiempo de generación: 30-11-2023 a las 14:09:15
-- Versión del servidor: 5.7.44
-- Versión de PHP: 8.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `drawing`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Canvas`
--

CREATE TABLE `Canvas` (
  `idObjectes` int(11) NOT NULL,
  `nameCanvas` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `dataCreacio` datetime DEFAULT NULL,
  `user_email` varchar(255) NOT NULL,
  `trash` tinyint(1) DEFAULT NULL,
  `public` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Canvas`
--

INSERT INTO `Canvas` (`idObjectes`, `nameCanvas`, `user_id`, `dataCreacio`, `user_email`, `trash`, `public`) VALUES
(24, 'Mi canvas', NULL, NULL, 'joan@gmail.com', 0, NULL),
(25, 'Image24569', NULL, '2023-11-30 10:00:47', 'francisca@gmail.com', 0, NULL),
(26, 'Image26801', NULL, '2023-11-30 10:04:48', 'aina@gmail.com', 0, NULL),
(27, 'Image86065', NULL, '2023-11-30 10:11:40', 'aina@gmail.com', 0, NULL),
(28, 'Image5689', NULL, '2023-11-30 10:15:29', 'joan@gmail.com', 0, NULL),
(29, 'Image17005', NULL, '2023-11-30 10:17:23', 'joan@gmail.com', 1, NULL),
(30, 'proba esper', NULL, '2023-11-30 10:58:30', 'joan@gmail.com', 0, NULL),
(32, 'lluvia de estrellas', NULL, '2023-11-30 11:13:45', 'aina@gmail.com', 0, NULL),
(34, 'Image45649', NULL, '2023-11-30 11:15:48', 'aina@gmail.com', 0, NULL),
(35, 'paperera', NULL, '2023-11-30 11:53:50', 'joan@gmail.com', 0, NULL),
(36, 'Image30964', NULL, '2023-11-30 14:02:41', 'joan@gmail.com', 0, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Permission`
--

CREATE TABLE `Permission` (
  `idCanvas` int(11) NOT NULL,
  `R` tinyint(1) NOT NULL,
  `idUser` int(11) NOT NULL,
  `W` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id`, `name`, `email`, `password`) VALUES
(4, 'joan', 'joan@gmail.com', '2e80a184267270fc8a50f3f9aef3902e'),
(5, 'Francisca', 'francisca@gmail.com', '2e80a184267270fc8a50f3f9aef3902e'),
(6, 'Aina', 'aina@gmail.com', '2e80a184267270fc8a50f3f9aef3902e');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Version`
--

CREATE TABLE `Version` (
  `idVersion` int(11) NOT NULL,
  `idDraw` int(11) NOT NULL,
  `figuresJSON` text NOT NULL,
  `strokesJSON` text NOT NULL,
  `dateLastModified` date NOT NULL,
  `user_email` varchar(255) NOT NULL,
  `numberObject` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Version`
--

INSERT INTO `Version` (`idVersion`, `idDraw`, `figuresJSON`, `strokesJSON`, `dateLastModified`, `user_email`, `numberObject`) VALUES
(1, 24, '[]', '[{\"puntos\":[{\"x\":79,\"y\":417},{\"x\":80,\"y\":417},{\"x\":81,\"y\":417},{\"x\":87,\"y\":419},{\"x\":98,\"y\":422},{\"x\":112,\"y\":425},{\"x\":130,\"y\":428},{\"x\":150,\"y\":431},{\"x\":171,\"y\":434},{\"x\":195,\"y\":435},{\"x\":223,\"y\":436},{\"x\":258,\"y\":436},{\"x\":304,\"y\":435},{\"x\":353,\"y\":429},{\"x\":394,\"y\":420},{\"x\":433,\"y\":411},{\"x\":475,\"y\":398},{\"x\":514,\"y\":383},{\"x\":550,\"y\":366},{\"x\":579,\"y\":348},{\"x\":600,\"y\":334},{\"x\":617,\"y\":322},{\"x\":631,\"y\":310},{\"x\":639,\"y\":302},{\"x\":644,\"y\":296},{\"x\":646,\"y\":290},{\"x\":648,\"y\":285},{\"x\":649,\"y\":280},{\"x\":649,\"y\":276},{\"x\":650,\"y\":274},{\"x\":650,\"y\":272},{\"x\":650,\"y\":271},{\"x\":650,\"y\":270},{\"x\":650,\"y\":270},{\"x\":650,\"y\":270},{\"x\":650,\"y\":271},{\"x\":650,\"y\":271}],\"color\":\"#000000\",\"size\":\"50\"}]', '2023-11-30', 'joan@gmail.com', 1),
(2, 25, '[{\"type\":\"cuadrado\",\"color\":\"#000000\",\"rellenoFigura\":false,\"size\":\"50\",\"centerX\":213,\"centerY\":291},{\"type\":\"cuadrado\",\"color\":\"#000000\",\"rellenoFigura\":false,\"size\":\"50\",\"centerX\":389,\"centerY\":303},{\"type\":\"cuadrado\",\"color\":\"#000000\",\"rellenoFigura\":false,\"size\":\"50\",\"centerX\":211,\"centerY\":480}]', '[]', '2023-11-30', 'francisca@gmail.com', 1),
(3, 26, '[]', '[{\"puntos\":[{\"x\":200,\"y\":233},{\"x\":200,\"y\":233},{\"x\":200,\"y\":235},{\"x\":200,\"y\":238},{\"x\":200,\"y\":244},{\"x\":200,\"y\":253},{\"x\":200,\"y\":265},{\"x\":200,\"y\":278},{\"x\":200,\"y\":292},{\"x\":200,\"y\":305},{\"x\":201,\"y\":317},{\"x\":203,\"y\":329},{\"x\":206,\"y\":339},{\"x\":209,\"y\":349},{\"x\":212,\"y\":356},{\"x\":216,\"y\":363},{\"x\":219,\"y\":368},{\"x\":223,\"y\":371},{\"x\":227,\"y\":373},{\"x\":232,\"y\":374},{\"x\":238,\"y\":374},{\"x\":247,\"y\":374},{\"x\":260,\"y\":374},{\"x\":276,\"y\":373},{\"x\":293,\"y\":369},{\"x\":313,\"y\":361},{\"x\":334,\"y\":352},{\"x\":352,\"y\":341},{\"x\":368,\"y\":330},{\"x\":381,\"y\":317},{\"x\":393,\"y\":303},{\"x\":402,\"y\":288},{\"x\":411,\"y\":272},{\"x\":419,\"y\":257},{\"x\":427,\"y\":246},{\"x\":434,\"y\":237},{\"x\":442,\"y\":229},{\"x\":448,\"y\":222},{\"x\":455,\"y\":215},{\"x\":462,\"y\":210},{\"x\":470,\"y\":205},{\"x\":477,\"y\":201},{\"x\":484,\"y\":199},{\"x\":491,\"y\":197},{\"x\":498,\"y\":197},{\"x\":505,\"y\":197},{\"x\":514,\"y\":198},{\"x\":522,\"y\":202},{\"x\":531,\"y\":207},{\"x\":539,\"y\":216},{\"x\":546,\"y\":226},{\"x\":551,\"y\":240},{\"x\":556,\"y\":259},{\"x\":561,\"y\":283},{\"x\":563,\"y\":307},{\"x\":565,\"y\":330},{\"x\":567,\"y\":349},{\"x\":569,\"y\":367},{\"x\":570,\"y\":383},{\"x\":572,\"y\":395},{\"x\":573,\"y\":404},{\"x\":575,\"y\":410},{\"x\":577,\"y\":414},{\"x\":579,\"y\":416},{\"x\":583,\"y\":417},{\"x\":591,\"y\":418},{\"x\":603,\"y\":418},{\"x\":621,\"y\":418},{\"x\":641,\"y\":417},{\"x\":659,\"y\":413},{\"x\":677,\"y\":409},{\"x\":692,\"y\":406},{\"x\":702,\"y\":403},{\"x\":708,\"y\":401},{\"x\":713,\"y\":399},{\"x\":716,\"y\":397},{\"x\":719,\"y\":397},{\"x\":722,\"y\":396},{\"x\":724,\"y\":395}],\"color\":\"#d51a1a\",\"size\":\"50\"},{\"puntos\":[{\"x\":167,\"y\":148},{\"x\":167,\"y\":148},{\"x\":171,\"y\":148},{\"x\":183,\"y\":146},{\"x\":206,\"y\":141},{\"x\":241,\"y\":133},{\"x\":283,\"y\":125},{\"x\":325,\"y\":118},{\"x\":361,\"y\":112},{\"x\":391,\"y\":107},{\"x\":413,\"y\":104},{\"x\":431,\"y\":101},{\"x\":443,\"y\":100},{\"x\":453,\"y\":99},{\"x\":460,\"y\":99},{\"x\":467,\"y\":99},{\"x\":472,\"y\":99},{\"x\":476,\"y\":99},{\"x\":479,\"y\":99},{\"x\":480,\"y\":99},{\"x\":482,\"y\":99},{\"x\":482,\"y\":100},{\"x\":483,\"y\":100},{\"x\":482,\"y\":100},{\"x\":480,\"y\":96}],\"color\":\"#d51a1a\",\"size\":\"18\"}]', '2023-11-30', 'aina@gmail.com', 1),
(4, 27, '[]', '[{\"puntos\":[{\"x\":72,\"y\":412},{\"x\":75,\"y\":412},{\"x\":86,\"y\":412},{\"x\":109,\"y\":412},{\"x\":143,\"y\":412},{\"x\":187,\"y\":412},{\"x\":233,\"y\":412},{\"x\":280,\"y\":412},{\"x\":318,\"y\":410},{\"x\":357,\"y\":406},{\"x\":398,\"y\":402},{\"x\":433,\"y\":397},{\"x\":467,\"y\":394},{\"x\":499,\"y\":393},{\"x\":525,\"y\":393},{\"x\":545,\"y\":394},{\"x\":558,\"y\":395},{\"x\":569,\"y\":397}],\"color\":\"#000000\",\"size\":\"50\"}]', '2023-11-30', 'aina@gmail.com', 1),
(5, 28, '[]', '[{\"puntos\":[{\"x\":107,\"y\":378},{\"x\":107,\"y\":378},{\"x\":109,\"y\":378},{\"x\":114,\"y\":378},{\"x\":124,\"y\":378},{\"x\":138,\"y\":378},{\"x\":152,\"y\":378},{\"x\":165,\"y\":378},{\"x\":179,\"y\":378},{\"x\":191,\"y\":380},{\"x\":202,\"y\":383},{\"x\":211,\"y\":387},{\"x\":221,\"y\":391},{\"x\":233,\"y\":395},{\"x\":246,\"y\":398},{\"x\":263,\"y\":401},{\"x\":285,\"y\":403},{\"x\":308,\"y\":404},{\"x\":336,\"y\":403},{\"x\":365,\"y\":400},{\"x\":395,\"y\":392},{\"x\":428,\"y\":381},{\"x\":461,\"y\":367},{\"x\":494,\"y\":352},{\"x\":525,\"y\":337},{\"x\":549,\"y\":325},{\"x\":566,\"y\":315},{\"x\":579,\"y\":308},{\"x\":587,\"y\":303},{\"x\":593,\"y\":300},{\"x\":596,\"y\":298},{\"x\":598,\"y\":297},{\"x\":599,\"y\":296},{\"x\":599,\"y\":295},{\"x\":599,\"y\":295},{\"x\":596,\"y\":294}],\"color\":\"#000000\",\"size\":\"50\"}]', '2023-11-30', 'joan@gmail.com', 1),
(6, 29, '[]', '[{\"puntos\":[{\"x\":136,\"y\":425},{\"x\":138,\"y\":425},{\"x\":143,\"y\":425},{\"x\":152,\"y\":425},{\"x\":165,\"y\":425},{\"x\":185,\"y\":425},{\"x\":211,\"y\":425},{\"x\":239,\"y\":425},{\"x\":268,\"y\":425},{\"x\":297,\"y\":421},{\"x\":324,\"y\":412},{\"x\":348,\"y\":400},{\"x\":370,\"y\":384},{\"x\":390,\"y\":366},{\"x\":408,\"y\":346},{\"x\":423,\"y\":327},{\"x\":435,\"y\":311},{\"x\":443,\"y\":298},{\"x\":448,\"y\":291},{\"x\":452,\"y\":285},{\"x\":453,\"y\":283},{\"x\":454,\"y\":282},{\"x\":454,\"y\":281},{\"x\":454,\"y\":281},{\"x\":454,\"y\":281},{\"x\":453,\"y\":281}],\"color\":\"#000000\",\"size\":\"50\"}]', '2023-11-30', 'joan@gmail.com', 1),
(7, 30, '[{\"type\":\"cuadrado\",\"color\":\"#000000\",\"rellenoFigura\":false,\"size\":\"50\",\"centerX\":206,\"centerY\":241},{\"type\":\"cuadrado\",\"color\":\"#000000\",\"rellenoFigura\":false,\"size\":\"50\",\"centerX\":274,\"centerY\":284}]', '[]', '2023-11-30', 'joan@gmail.com', 1),
(9, 32, '[{\"type\":\"estrella\",\"color\":\"#dff91a\",\"rellenoFigura\":false,\"size\":\"50\",\"centerX\":199,\"centerY\":187},{\"type\":\"estrella\",\"color\":\"#dff91a\",\"rellenoFigura\":false,\"size\":\"50\",\"centerX\":218,\"centerY\":225},{\"type\":\"estrella\",\"color\":\"#dff91a\",\"rellenoFigura\":false,\"size\":\"50\",\"centerX\":316,\"centerY\":279},{\"type\":\"estrella\",\"color\":\"#dff91a\",\"rellenoFigura\":true,\"size\":\"50\",\"centerX\":343,\"centerY\":119},{\"type\":\"estrella\",\"color\":\"#dff91a\",\"rellenoFigura\":true,\"size\":\"50\",\"centerX\":450,\"centerY\":173},{\"type\":\"estrella\",\"color\":\"#dff91a\",\"rellenoFigura\":true,\"size\":\"50\",\"centerX\":424,\"centerY\":341},{\"type\":\"estrella\",\"color\":\"#dff91a\",\"rellenoFigura\":true,\"size\":\"50\",\"centerX\":196,\"centerY\":343},{\"type\":\"estrella\",\"color\":\"#dff91a\",\"rellenoFigura\":true,\"size\":\"160\",\"centerX\":134,\"centerY\":413},{\"type\":\"estrella\",\"color\":\"#dff91a\",\"rellenoFigura\":true,\"size\":\"160\",\"centerX\":429,\"centerY\":435},{\"type\":\"estrella\",\"color\":\"#dff91a\",\"rellenoFigura\":true,\"size\":\"160\",\"centerX\":663,\"centerY\":293}]', '[]', '2023-11-30', 'aina@gmail.com', 1),
(11, 34, '[{\"type\":\"triangle\",\"color\":\"#f22121\",\"rellenoFigura\":false,\"size\":\"50\",\"centerX\":202,\"centerY\":390},{\"type\":\"triangle\",\"color\":\"#f22121\",\"rellenoFigura\":false,\"size\":\"50\",\"centerX\":484,\"centerY\":365},{\"type\":\"triangle\",\"color\":\"#f22121\",\"rellenoFigura\":false,\"size\":\"50\",\"centerX\":374,\"centerY\":429}]', '[]', '2023-11-30', 'aina@gmail.com', 1),
(12, 35, '[]', '[{\"puntos\":[{\"x\":91,\"y\":482},{\"x\":92,\"y\":482},{\"x\":99,\"y\":478},{\"x\":119,\"y\":462},{\"x\":165,\"y\":425},{\"x\":218,\"y\":379},{\"x\":263,\"y\":338},{\"x\":302,\"y\":302},{\"x\":334,\"y\":272},{\"x\":365,\"y\":243},{\"x\":386,\"y\":222},{\"x\":400,\"y\":208},{\"x\":410,\"y\":198},{\"x\":415,\"y\":192},{\"x\":419,\"y\":188},{\"x\":421,\"y\":185},{\"x\":423,\"y\":184},{\"x\":424,\"y\":183},{\"x\":425,\"y\":183},{\"x\":426,\"y\":183},{\"x\":427,\"y\":183},{\"x\":427,\"y\":183},{\"x\":428,\"y\":183},{\"x\":428,\"y\":183},{\"x\":428,\"y\":184},{\"x\":428,\"y\":184},{\"x\":428,\"y\":185},{\"x\":428,\"y\":186},{\"x\":428,\"y\":187},{\"x\":429,\"y\":188}],\"color\":\"#000000\",\"size\":\"50\"}]', '2023-11-30', 'joan@gmail.com', 1),
(13, 36, '[]', '[{\"puntos\":[{\"x\":230,\"y\":280},{\"x\":344,\"y\":283},{\"x\":406,\"y\":285},{\"x\":465,\"y\":291},{\"x\":523,\"y\":300},{\"x\":585,\"y\":315},{\"x\":631,\"y\":329},{\"x\":650,\"y\":337},{\"x\":656,\"y\":340},{\"x\":657,\"y\":342},{\"x\":657,\"y\":343},{\"x\":656,\"y\":347},{\"x\":652,\"y\":352},{\"x\":644,\"y\":358},{\"x\":633,\"y\":364},{\"x\":625,\"y\":368},{\"x\":621,\"y\":369},{\"x\":620,\"y\":370},{\"x\":617,\"y\":371},{\"x\":606,\"y\":374},{\"x\":591,\"y\":378},{\"x\":570,\"y\":382},{\"x\":542,\"y\":386},{\"x\":507,\"y\":390},{\"x\":463,\"y\":396},{\"x\":421,\"y\":401},{\"x\":390,\"y\":404},{\"x\":357,\"y\":407},{\"x\":327,\"y\":408},{\"x\":304,\"y\":407},{\"x\":285,\"y\":406},{\"x\":275,\"y\":406},{\"x\":270,\"y\":406},{\"x\":265,\"y\":406},{\"x\":261,\"y\":406},{\"x\":257,\"y\":406},{\"x\":252,\"y\":406},{\"x\":247,\"y\":407},{\"x\":244,\"y\":407},{\"x\":241,\"y\":408},{\"x\":238,\"y\":409},{\"x\":236,\"y\":410},{\"x\":235,\"y\":411},{\"x\":235,\"y\":411},{\"x\":234,\"y\":412},{\"x\":232,\"y\":413},{\"x\":229,\"y\":415},{\"x\":226,\"y\":417},{\"x\":224,\"y\":419},{\"x\":221,\"y\":421},{\"x\":218,\"y\":424},{\"x\":214,\"y\":428},{\"x\":209,\"y\":431},{\"x\":205,\"y\":434},{\"x\":201,\"y\":437},{\"x\":199,\"y\":439},{\"x\":198,\"y\":441},{\"x\":197,\"y\":444},{\"x\":196,\"y\":446},{\"x\":195,\"y\":449},{\"x\":195,\"y\":452},{\"x\":194,\"y\":455},{\"x\":195,\"y\":459},{\"x\":199,\"y\":465},{\"x\":210,\"y\":477},{\"x\":224,\"y\":487},{\"x\":233,\"y\":492},{\"x\":239,\"y\":494},{\"x\":245,\"y\":495},{\"x\":259,\"y\":494},{\"x\":297,\"y\":491},{\"x\":354,\"y\":485},{\"x\":397,\"y\":481},{\"x\":417,\"y\":479},{\"x\":429,\"y\":479},{\"x\":441,\"y\":477},{\"x\":464,\"y\":473},{\"x\":501,\"y\":465},{\"x\":538,\"y\":456},{\"x\":567,\"y\":451},{\"x\":589,\"y\":449},{\"x\":604,\"y\":449},{\"x\":612,\"y\":448},{\"x\":616,\"y\":448},{\"x\":618,\"y\":447},{\"x\":618,\"y\":447}],\"color\":\"#000000\",\"size\":\"50\"}]', '2023-11-30', 'joan@gmail.com', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Canvas`
--
ALTER TABLE `Canvas`
  ADD PRIMARY KEY (`idObjectes`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `user_email` (`user_email`);

--
-- Indices de la tabla `Permission`
--
ALTER TABLE `Permission`
  ADD KEY `idCanvas` (`idCanvas`),
  ADD KEY `idUser` (`idUser`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `email_2` (`email`);

--
-- Indices de la tabla `Version`
--
ALTER TABLE `Version`
  ADD PRIMARY KEY (`idVersion`),
  ADD KEY `idDraw` (`idDraw`),
  ADD KEY `Version_ibfk_2` (`user_email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Canvas`
--
ALTER TABLE `Canvas`
  MODIFY `idObjectes` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `Version`
--
ALTER TABLE `Version`
  MODIFY `idVersion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `Canvas`
--
ALTER TABLE `Canvas`
  ADD CONSTRAINT `Canvas_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `Canvas_ibfk_2` FOREIGN KEY (`user_email`) REFERENCES `user` (`email`);

--
-- Filtros para la tabla `Permission`
--
ALTER TABLE `Permission`
  ADD CONSTRAINT `Permission_ibfk_1` FOREIGN KEY (`idCanvas`) REFERENCES `Canvas` (`idObjectes`),
  ADD CONSTRAINT `Permission_ibfk_2` FOREIGN KEY (`idUser`) REFERENCES `user` (`id`);

--
-- Filtros para la tabla `Version`
--
ALTER TABLE `Version`
  ADD CONSTRAINT `Version_ibfk_1` FOREIGN KEY (`idDraw`) REFERENCES `Canvas` (`idObjectes`),
  ADD CONSTRAINT `Version_ibfk_2` FOREIGN KEY (`user_email`) REFERENCES `user` (`email`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
