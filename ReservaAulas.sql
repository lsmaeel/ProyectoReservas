-- MySQL dump 10.13  Distrib 8.0.43, for Linux (x86_64)
--
-- Host: localhost    Database: ProyectoReservas
-- ------------------------------------------------------
-- Server version	8.0.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `aulas`
--

DROP TABLE IF EXISTS `aulas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aulas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `capacidad` int DEFAULT NULL,
  `es_aula_de_ordenadores` bit(1) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `numero_ordenadores` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aulas`
--

LOCK TABLES `aulas` WRITE;
/*!40000 ALTER TABLE `aulas` DISABLE KEYS */;
INSERT INTO `aulas` VALUES (1,30,_binary '\0','Aula 101',NULL),(2,25,_binary '\0','Aula 102',NULL),(3,40,_binary '\0','Aula 103',NULL),(4,20,_binary '','Laboratorio Informática 1',20),(5,25,_binary '','Laboratorio Informática 2',25),(6,100,_binary '\0','Aula Magna',NULL),(7,15,_binary '\0','Sala de Reuniones A',NULL),(8,12,_binary '','Sala de Reuniones B',8),(9,35,_binary '\0','Aula 201',NULL),(10,30,_binary '','Aula Multimedia',15);
/*!40000 ALTER TABLE `aulas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horarios`
--

DROP TABLE IF EXISTS `horarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `horarios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dia_semana` enum('DOMINGO','JUEVES','LUNES','MARTES','MIERCOLES','SABADO','VIERNES') DEFAULT NULL,
  `hora_fin` time(6) DEFAULT NULL,
  `hora_inicio` time(6) DEFAULT NULL,
  `sesion_dia` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horarios`
--

LOCK TABLES `horarios` WRITE;
/*!40000 ALTER TABLE `horarios` DISABLE KEYS */;
INSERT INTO `horarios` VALUES (1,'LUNES','09:30:00.000000','08:00:00.000000',1),(2,'LUNES','11:15:00.000000','09:45:00.000000',2),(3,'LUNES','13:00:00.000000','11:30:00.000000',3),(4,'LUNES','14:45:00.000000','13:15:00.000000',4),(5,'LUNES','16:30:00.000000','15:00:00.000000',5),(6,'LUNES','18:15:00.000000','16:45:00.000000',6),(7,'MARTES','09:30:00.000000','08:00:00.000000',1),(8,'MARTES','11:15:00.000000','09:45:00.000000',2),(9,'MARTES','13:00:00.000000','11:30:00.000000',3),(10,'MARTES','14:45:00.000000','13:15:00.000000',4),(11,'MARTES','16:30:00.000000','15:00:00.000000',5),(12,'MARTES','18:15:00.000000','16:45:00.000000',6),(13,'MIERCOLES','09:30:00.000000','08:00:00.000000',1),(14,'MIERCOLES','11:15:00.000000','09:45:00.000000',2),(15,'MIERCOLES','13:00:00.000000','11:30:00.000000',3),(16,'MIERCOLES','14:45:00.000000','13:15:00.000000',4),(17,'MIERCOLES','16:30:00.000000','15:00:00.000000',5),(18,'MIERCOLES','18:15:00.000000','16:45:00.000000',6),(19,'JUEVES','09:30:00.000000','08:00:00.000000',1),(20,'JUEVES','11:15:00.000000','09:45:00.000000',2),(21,'JUEVES','13:00:00.000000','11:30:00.000000',3),(22,'JUEVES','14:45:00.000000','13:15:00.000000',4),(23,'JUEVES','16:30:00.000000','15:00:00.000000',5),(24,'JUEVES','18:15:00.000000','16:45:00.000000',6),(25,'VIERNES','09:30:00.000000','08:00:00.000000',1),(26,'VIERNES','11:15:00.000000','09:45:00.000000',2),(27,'VIERNES','13:00:00.000000','11:30:00.000000',3),(28,'VIERNES','14:45:00.000000','13:15:00.000000',4),(29,'VIERNES','16:30:00.000000','15:00:00.000000',5),(30,'VIERNES','18:15:00.000000','16:45:00.000000',6);
/*!40000 ALTER TABLE `horarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservas`
--

DROP TABLE IF EXISTS `reservas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `fecha_creacion` date DEFAULT NULL,
  `motivo` varchar(255) DEFAULT NULL,
  `numero_asistentes` int DEFAULT NULL,
  `aula_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrm1nvtnu913c60dqtmi4mj7oh` (`aula_id`),
  CONSTRAINT `FKrm1nvtnu913c60dqtmi4mj7oh` FOREIGN KEY (`aula_id`) REFERENCES `aulas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservas`
--

LOCK TABLES `reservas` WRITE;
/*!40000 ALTER TABLE `reservas` DISABLE KEYS */;
INSERT INTO `reservas` VALUES (1,'2025-10-23','2025-10-15','Clase de Programación',28,1),(2,'2025-10-23','2025-10-15','Laboratorio de Base de Datos',18,4),(3,'2025-10-23','2025-10-16','Seminario de Ingeniería',35,3),(4,'2025-10-24','2025-10-16','Práctica de Redes',22,5),(5,'2025-10-24','2025-10-17','Clase de Matemáticas',30,9),(6,'2025-10-24','2025-10-17','Reunión Departamental',12,7),(7,'2025-10-25','2025-10-18','Conferencia Anual',95,6),(8,'2025-10-25','2025-10-18','Taller de Desarrollo Web',14,8),(9,'2025-10-28','2025-10-19','Clase de Algoritmos',25,2),(10,'2025-10-28','2025-10-19','Práctica de Sistemas Operativos',20,4),(11,'2025-10-29','2025-10-20','Examen de Estructura de Datos',38,3),(12,'2025-10-29','2025-10-20','Proyecto Final',15,10),(13,'2025-10-30','2025-10-20','Presentación de Trabajos',32,9),(14,'2025-10-31','2025-10-21','Tutoría Grupal',10,7),(15,'2025-11-01','2025-10-21','Workshop de Machine Learning',24,5);
/*!40000 ALTER TABLE `reservas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-22  7:48:04
