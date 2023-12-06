-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: db:3306
-- Tiempo de generación: 06-12-2023 a las 14:37:19
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
  `dataCreacio` datetime DEFAULT CURRENT_TIMESTAMP,
  `user_email` varchar(255) NOT NULL,
  `trash` tinyint(1) DEFAULT NULL,
  `public` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Canvas`
--

INSERT INTO `Canvas` (`idObjectes`, `nameCanvas`, `dataCreacio`, `user_email`, `trash`, `public`) VALUES
(103, 'Image72587', '2023-12-06 14:09:45', 'joan@gmail.com', 0, 0),
(104, 'Image3239', '2023-12-06 14:12:50', 'claudia@gmail.com', 0, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Permission`
--

CREATE TABLE `Permission` (
  `idCanvas` int(11) NOT NULL,
  `permissionType` varchar(1) NOT NULL,
  `user_email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Permission`
--

INSERT INTO `Permission` (`idCanvas`, `permissionType`, `user_email`) VALUES
(103, 'W', 'claudia@gmail.com');

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
(6, 'Aina', 'aina@gmail.com', '2e80a184267270fc8a50f3f9aef3902e'),
(9, 'jose', 'jj@gmail.com', '370bec3471ae0e73d2a22e3dd333ae5f'),
(10, 'Clau', 'claudia@gmail.com', '2e80a184267270fc8a50f3f9aef3902e');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Version`
--

CREATE TABLE `Version` (
  `idVersion` int(11) NOT NULL,
  `idDraw` int(11) NOT NULL,
  `figuresJSON` text NOT NULL,
  `strokesJSON` text NOT NULL,
  `dateLastModified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_email` varchar(255) NOT NULL,
  `numberObject` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Version`
--

INSERT INTO `Version` (`idVersion`, `idDraw`, `figuresJSON`, `strokesJSON`, `dateLastModified`, `user_email`, `numberObject`) VALUES
(89, 103, '[{\"type\":\"cuadrado\",\"color\":\"#060ddb\",\"rellenoFigura\":false,\"size\":\"67\",\"centerX\":418,\"centerY\":86},{\"type\":\"cuadrado\",\"color\":\"#060ddb\",\"rellenoFigura\":false,\"size\":\"67\",\"centerX\":555,\"centerY\":99}]', '[{\"puntos\":[{\"x\":96,\"y\":312},{\"x\":97,\"y\":313},{\"x\":97,\"y\":318},{\"x\":97,\"y\":330},{\"x\":99,\"y\":349},{\"x\":104,\"y\":373},{\"x\":110,\"y\":395},{\"x\":115,\"y\":413},{\"x\":121,\"y\":431},{\"x\":128,\"y\":445},{\"x\":133,\"y\":454},{\"x\":138,\"y\":459},{\"x\":143,\"y\":462},{\"x\":149,\"y\":464},{\"x\":157,\"y\":464},{\"x\":173,\"y\":464},{\"x\":198,\"y\":462},{\"x\":239,\"y\":453},{\"x\":283,\"y\":439},{\"x\":323,\"y\":420},{\"x\":365,\"y\":396},{\"x\":394,\"y\":374},{\"x\":412,\"y\":354},{\"x\":422,\"y\":336},{\"x\":426,\"y\":316},{\"x\":427,\"y\":294},{\"x\":427,\"y\":272},{\"x\":427,\"y\":249},{\"x\":428,\"y\":232},{\"x\":428,\"y\":221},{\"x\":428,\"y\":214},{\"x\":427,\"y\":212},{\"x\":425,\"y\":211},{\"x\":421,\"y\":211},{\"x\":415,\"y\":211},{\"x\":404,\"y\":211},{\"x\":389,\"y\":211},{\"x\":370,\"y\":210},{\"x\":350,\"y\":210},{\"x\":326,\"y\":212},{\"x\":300,\"y\":220},{\"x\":280,\"y\":230},{\"x\":262,\"y\":240},{\"x\":246,\"y\":250},{\"x\":235,\"y\":260},{\"x\":227,\"y\":269},{\"x\":222,\"y\":277},{\"x\":220,\"y\":286},{\"x\":218,\"y\":296},{\"x\":218,\"y\":307},{\"x\":220,\"y\":320},{\"x\":227,\"y\":337},{\"x\":236,\"y\":354},{\"x\":248,\"y\":369},{\"x\":262,\"y\":384},{\"x\":281,\"y\":397},{\"x\":306,\"y\":409},{\"x\":335,\"y\":418},{\"x\":371,\"y\":425},{\"x\":415,\"y\":428},{\"x\":470,\"y\":427},{\"x\":523,\"y\":422},{\"x\":569,\"y\":414},{\"x\":608,\"y\":407},{\"x\":634,\"y\":401},{\"x\":651,\"y\":397},{\"x\":660,\"y\":395},{\"x\":663,\"y\":394},{\"x\":665,\"y\":393},{\"x\":665,\"y\":393},{\"x\":665,\"y\":393},{\"x\":665,\"y\":393},{\"x\":664,\"y\":393},{\"x\":663,\"y\":392}],\"color\":\"#dbc906\",\"size\":\"67\"}]', '2023-12-06 14:09:45', 'joan@gmail.com', 1),
(90, 104, '[{\"type\":\"estrella\",\"color\":\"#c1cd18\",\"rellenoFigura\":false,\"size\":\"200\",\"centerX\":374,\"centerY\":211}]', '[]', '2023-12-06 14:12:50', 'claudia@gmail.com', 1),
(91, 103, '[{\"type\":\"cuadrado\",\"color\":\"#060ddb\",\"rellenoFigura\":false,\"size\":\"67\",\"centerX\":418,\"centerY\":86},{\"type\":\"cuadrado\",\"color\":\"#060ddb\",\"rellenoFigura\":false,\"size\":\"67\",\"centerX\":555,\"centerY\":99}]', '[{\"puntos\":[{\"x\":96,\"y\":312},{\"x\":97,\"y\":313},{\"x\":97,\"y\":318},{\"x\":97,\"y\":330},{\"x\":99,\"y\":349},{\"x\":104,\"y\":373},{\"x\":110,\"y\":395},{\"x\":115,\"y\":413},{\"x\":121,\"y\":431},{\"x\":128,\"y\":445},{\"x\":133,\"y\":454},{\"x\":138,\"y\":459},{\"x\":143,\"y\":462},{\"x\":149,\"y\":464},{\"x\":157,\"y\":464},{\"x\":173,\"y\":464},{\"x\":198,\"y\":462},{\"x\":239,\"y\":453},{\"x\":283,\"y\":439},{\"x\":323,\"y\":420},{\"x\":365,\"y\":396},{\"x\":394,\"y\":374},{\"x\":412,\"y\":354},{\"x\":422,\"y\":336},{\"x\":426,\"y\":316},{\"x\":427,\"y\":294},{\"x\":427,\"y\":272},{\"x\":427,\"y\":249},{\"x\":428,\"y\":232},{\"x\":428,\"y\":221},{\"x\":428,\"y\":214},{\"x\":427,\"y\":212},{\"x\":425,\"y\":211},{\"x\":421,\"y\":211},{\"x\":415,\"y\":211},{\"x\":404,\"y\":211},{\"x\":389,\"y\":211},{\"x\":370,\"y\":210},{\"x\":350,\"y\":210},{\"x\":326,\"y\":212},{\"x\":300,\"y\":220},{\"x\":280,\"y\":230},{\"x\":262,\"y\":240},{\"x\":246,\"y\":250},{\"x\":235,\"y\":260},{\"x\":227,\"y\":269},{\"x\":222,\"y\":277},{\"x\":220,\"y\":286},{\"x\":218,\"y\":296},{\"x\":218,\"y\":307},{\"x\":220,\"y\":320},{\"x\":227,\"y\":337},{\"x\":236,\"y\":354},{\"x\":248,\"y\":369},{\"x\":262,\"y\":384},{\"x\":281,\"y\":397},{\"x\":306,\"y\":409},{\"x\":335,\"y\":418},{\"x\":371,\"y\":425},{\"x\":415,\"y\":428},{\"x\":470,\"y\":427},{\"x\":523,\"y\":422},{\"x\":569,\"y\":414},{\"x\":608,\"y\":407},{\"x\":634,\"y\":401},{\"x\":651,\"y\":397},{\"x\":660,\"y\":395},{\"x\":663,\"y\":394},{\"x\":665,\"y\":393},{\"x\":665,\"y\":393},{\"x\":665,\"y\":393},{\"x\":665,\"y\":393},{\"x\":664,\"y\":393},{\"x\":663,\"y\":392}],\"color\":\"#dbc906\",\"size\":\"67\"}]', '2023-12-06 14:16:58', 'joan@gmail.com', 0),
(92, 104, '[{\"type\":\"estrella\",\"color\":\"#c1cd18\",\"rellenoFigura\":false,\"size\":\"200\",\"centerX\":374,\"centerY\":211}]', '[]', '2023-12-06 14:28:32', 'claudia@gmail.com', 0),
(93, 104, '[{\"type\":\"estrella\",\"color\":\"#c1cd18\",\"rellenoFigura\":false,\"size\":\"200\",\"centerX\":374,\"centerY\":211}]', '[]', '2023-12-06 14:37:18', 'claudia@gmail.com', 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Canvas`
--
ALTER TABLE `Canvas`
  ADD PRIMARY KEY (`idObjectes`),
  ADD KEY `user_email` (`user_email`);

--
-- Indices de la tabla `Permission`
--
ALTER TABLE `Permission`
  ADD KEY `Permission_ibfk_1` (`idCanvas`),
  ADD KEY `Permission_ibfk_2` (`user_email`);

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
  ADD KEY `Version_ibfk_2` (`user_email`),
  ADD KEY `Version_ibfk_1` (`idDraw`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Canvas`
--
ALTER TABLE `Canvas`
  MODIFY `idObjectes` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=105;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `Version`
--
ALTER TABLE `Version`
  MODIFY `idVersion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=94;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `Canvas`
--
ALTER TABLE `Canvas`
  ADD CONSTRAINT `Canvas_ibfk_2` FOREIGN KEY (`user_email`) REFERENCES `user` (`email`);

--
-- Filtros para la tabla `Permission`
--
ALTER TABLE `Permission`
  ADD CONSTRAINT `Permission_ibfk_1` FOREIGN KEY (`idCanvas`) REFERENCES `Canvas` (`idObjectes`) ON DELETE CASCADE,
  ADD CONSTRAINT `Permission_ibfk_2` FOREIGN KEY (`user_email`) REFERENCES `user` (`email`) ON DELETE CASCADE;

--
-- Filtros para la tabla `Version`
--
ALTER TABLE `Version`
  ADD CONSTRAINT `Version_ibfk_1` FOREIGN KEY (`idDraw`) REFERENCES `Canvas` (`idObjectes`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Version_ibfk_2` FOREIGN KEY (`user_email`) REFERENCES `user` (`email`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
