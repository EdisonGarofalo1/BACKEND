CREATE DATABASE  IF NOT EXISTS `pruebabackend` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pruebabackend`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: pruebabackend
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `id_persona` int NOT NULL AUTO_INCREMENT,
  `apellidos` varchar(60) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `identificacion` varchar(10) DEFAULT NULL,
  `nombres` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id_persona`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (1,'Piguave Loor','2012-12-11','1206505024','Juan Alberto');
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `id_rol` int NOT NULL AUTO_INCREMENT,
  `rol_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'Administrador');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol_opciones`
--

DROP TABLE IF EXISTS `rol_opciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol_opciones` (
  `id_opcion` int NOT NULL AUTO_INCREMENT,
  `nombre_opcion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_opcion`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol_opciones`
--

LOCK TABLES `rol_opciones` WRITE;
/*!40000 ALTER TABLE `rol_opciones` DISABLE KEYS */;
INSERT INTO `rol_opciones` VALUES (1,'GET'),(2,'POST'),(3,'PUT'),(4,'DELETE'),(5,'OPTIONS');
/*!40000 ALTER TABLE `rol_opciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol_rol_opciones`
--

DROP TABLE IF EXISTS `rol_rol_opciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol_rol_opciones` (
  `rol_opciones_id_opcion` int NOT NULL,
  `rol_id_rol` int NOT NULL,
  KEY `FKdp7vgtfjv2orqa1slqfms2cpv` (`rol_id_rol`),
  KEY `FKfthds7uq0l3vd7acafi2l5p2m` (`rol_opciones_id_opcion`),
  CONSTRAINT `FKdp7vgtfjv2orqa1slqfms2cpv` FOREIGN KEY (`rol_id_rol`) REFERENCES `rol` (`id_rol`),
  CONSTRAINT `FKfthds7uq0l3vd7acafi2l5p2m` FOREIGN KEY (`rol_opciones_id_opcion`) REFERENCES `rol_opciones` (`id_opcion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol_rol_opciones`
--

LOCK TABLES `rol_rol_opciones` WRITE;
/*!40000 ALTER TABLE `rol_rol_opciones` DISABLE KEYS */;
INSERT INTO `rol_rol_opciones` VALUES (1,1),(2,1),(3,1),(4,1),(5,1);
/*!40000 ALTER TABLE `rol_rol_opciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol_usuarios`
--

DROP TABLE IF EXISTS `rol_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol_usuarios` (
  `usuario_id_usuario` int NOT NULL,
  `rol_id_rol` int NOT NULL,
  KEY `FKbwq10d35yvo19c5wlrc2857mf` (`rol_id_rol`),
  KEY `FKp26o5netbmvqmdh3cd20jupc5` (`usuario_id_usuario`),
  CONSTRAINT `FKbwq10d35yvo19c5wlrc2857mf` FOREIGN KEY (`rol_id_rol`) REFERENCES `rol` (`id_rol`),
  CONSTRAINT `FKp26o5netbmvqmdh3cd20jupc5` FOREIGN KEY (`usuario_id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol_usuarios`
--

LOCK TABLES `rol_usuarios` WRITE;
/*!40000 ALTER TABLE `rol_usuarios` DISABLE KEYS */;
INSERT INTO `rol_usuarios` VALUES (1,1);
/*!40000 ALTER TABLE `rol_usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sesion`
--

DROP TABLE IF EXISTS `sesion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sesion` (
  `id_sesion` int NOT NULL AUTO_INCREMENT,
  `fecha_cierre` date DEFAULT NULL,
  `fecha_ingreso` date DEFAULT NULL,
  `usuarios_id_usuario` int DEFAULT NULL,
  PRIMARY KEY (`id_sesion`),
  KEY `FKkj5bitc2nja5frtl60avxbth9` (`usuarios_id_usuario`),
  CONSTRAINT `FKkj5bitc2nja5frtl60avxbth9` FOREIGN KEY (`usuarios_id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sesion`
--

LOCK TABLES `sesion` WRITE;
/*!40000 ALTER TABLE `sesion` DISABLE KEYS */;
INSERT INTO `sesion` VALUES (1,'2024-04-19','2024-04-19',1),(2,'2024-04-19','2024-04-19',1),(3,'2024-04-19','2024-04-19',1);
/*!40000 ALTER TABLE `sesion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `intentos_fallidos` int DEFAULT NULL,
  `mail` varchar(120) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `session_active` varchar(1) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `persona_id_persona2` int DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `FKp272gbvrce10jrry0usroxllm` (`persona_id_persona2`),
  CONSTRAINT `FKp272gbvrce10jrry0usroxllm` FOREIGN KEY (`persona_id_persona2`) REFERENCES `persona` (`id_persona`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,0,'jpiguaveloor11@mail.com','12345678J@1','E','Activo','JuanAlberto11',1);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'pruebabackend'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-19 15:31:48
