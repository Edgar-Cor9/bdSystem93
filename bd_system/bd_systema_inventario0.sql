-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bd_systema
-- ------------------------------------------------------
-- Server version	9.3.0

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
-- Table structure for table `inventario`
--

DROP TABLE IF EXISTS `inventario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventario` (
  `idinventario` int NOT NULL AUTO_INCREMENT,
  `codproductos` int NOT NULL,
  `idusuarios` int NOT NULL,
  `idproveedor` int DEFAULT NULL,
  `orden` varchar(15) NOT NULL,
  `detalle` varchar(45) NOT NULL,
  `observacion` varchar(45) NOT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `fecha_registro` date NOT NULL,
  `fecha_aprobacion` date DEFAULT NULL,
  `ingreso` int DEFAULT NULL,
  `egreso` int DEFAULT NULL,
  `stock` int NOT NULL,
  `total` double NOT NULL,
  PRIMARY KEY (`idinventario`),
  KEY `idusers_idx` (`idusuarios`),
  KEY `codPro_idx` (`codproductos`),
  KEY `idprovee_idx` (`idproveedor`),
  CONSTRAINT `codPro` FOREIGN KEY (`codproductos`) REFERENCES `productos` (`codproductos`),
  CONSTRAINT `idprovee` FOREIGN KEY (`idproveedor`) REFERENCES `proveedor` (`idProveedor`),
  CONSTRAINT `idusers` FOREIGN KEY (`idusuarios`) REFERENCES `usuarios` (`idusuarios`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventario`
--

LOCK TABLES `inventario` WRITE;
/*!40000 ALTER TABLE `inventario` DISABLE KEYS */;
INSERT INTO `inventario` VALUES (1,1,2,1,'10020000015201','Compra','','Procesado','2025-07-17','2025-07-17',10,NULL,10,190.4),(3,1,2,1,'10020000015202','Compra','','Procesado','2025-07-21','2025-07-22',10,NULL,20,190.4),(4,7,3,1,'100200202010','Compra','','Procesado','2025-07-23','2025-07-23',1,NULL,1,28),(5,1,2,1,'1000200151244','Compra','','Procesado','2025-07-23','2025-08-01',5,NULL,25,95.2),(6,10,3,1,'100200201045','Compra','MARCA ASUS','Procesado','2025-08-04','2025-08-04',10,NULL,10,168),(13,10,2,1,'140101010','Compra','MARCA ASUS','Procesado','2025-08-07','2025-08-07',10,NULL,20,168),(15,10,2,1,'14414141','Compra','MARCA ASUS','Procesado','2025-08-07','2025-08-08',10,NULL,30,168),(22,10,2,1,'10101','Compra','MARCA ASUS','Procesado','2025-08-26','2025-08-26',10,NULL,40,168),(23,10,2,1,'10144','Compra','MARCA ASUS','Procesado','2025-08-26','2025-08-26',5,NULL,45,84);
/*!40000 ALTER TABLE `inventario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-01-17 21:33:40
