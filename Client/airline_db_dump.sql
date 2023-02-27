-- MySQL dump 10.13  Distrib 8.0.32, for Linux (x86_64)
--
-- Host: localhost    Database: airline
-- ------------------------------------------------------
-- Server version	8.0.32-0ubuntu0.22.04.2

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
-- Table structure for table `airplane`
--

DROP TABLE IF EXISTS `airplane`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `airplane` (
  `airplane_id` int NOT NULL AUTO_INCREMENT,
  `airplane_name` varchar(30) NOT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `model_no` varchar(30) NOT NULL,
  `status` tinyint NOT NULL,
  `total_seats` int NOT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`airplane_id`),
  KEY `FKbyvn4q182so8mwjv0tbapli20` (`user_id`),
  CONSTRAINT `FKbyvn4q182so8mwjv0tbapli20` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airplane`
--

LOCK TABLES `airplane` WRITE;
/*!40000 ALTER TABLE `airplane` DISABLE KEYS */;
INSERT INTO `airplane` VALUES (1,'M62ED','2023-02-17 06:56:13.543000','B6322',1,200,'2023-02-17 06:56:13.543000',1);
/*!40000 ALTER TABLE `airplane` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookings` (
  `booking_id` int NOT NULL AUTO_INCREMENT,
  `cancellation` varchar(255) DEFAULT NULL,
  `create_date` datetime(6) NOT NULL,
  `delete_flag` tinyint NOT NULL,
  `status` tinyint NOT NULL,
  `update_date` datetime(6) NOT NULL,
  `flight_id` int NOT NULL,
  `seat_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`booking_id`),
  KEY `FKb70dt8nd6bohqydy1mafx9j02` (`flight_id`),
  KEY `FK22abi3xsv9wca9teap7gajbrr` (`seat_id`),
  KEY `FK65bh1tn1y443fxcah5u36e8fy` (`user_id`),
  CONSTRAINT `FK22abi3xsv9wca9teap7gajbrr` FOREIGN KEY (`seat_id`) REFERENCES `seat` (`seat_id`),
  CONSTRAINT `FK65bh1tn1y443fxcah5u36e8fy` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKb70dt8nd6bohqydy1mafx9j02` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`flight_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES (1,'hwllo','2023-02-21 06:31:20.833000',1,2,'2023-02-21 06:31:20.833000',1,1,1),(2,'hwllo','2023-02-21 06:32:02.389000',1,2,'2023-02-21 06:32:02.389000',1,1,1),(3,'hwllo','2023-02-23 08:57:51.142000',1,2,'2023-02-23 08:57:51.142000',1,1,1),(4,NULL,'2023-02-23 08:57:57.961000',1,2,'2023-02-23 08:57:57.961000',1,1,1),(5,NULL,'2023-02-23 08:58:07.575000',1,2,'2023-02-23 08:58:07.575000',1,1,1);
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` int DEFAULT NULL,
  `status` tinyint NOT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight`
--

DROP TABLE IF EXISTS `flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flight` (
  `flight_id` int NOT NULL AUTO_INCREMENT,
  `create_date` datetime(6) DEFAULT NULL,
  `delete_flag` tinyint NOT NULL,
  `dep_date_time` datetime(6) DEFAULT NULL,
  `departure` varchar(255) DEFAULT NULL,
  `dest_date_time` datetime(6) DEFAULT NULL,
  `destination` varchar(255) DEFAULT NULL,
  `flight_number` varchar(255) DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `airplane_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`flight_id`),
  KEY `FKb8t4272gfgo1feyyidvscbjm0` (`airplane_id`),
  KEY `FKsybl41p362b1vsb506td0led2` (`user_id`),
  CONSTRAINT `FKb8t4272gfgo1feyyidvscbjm0` FOREIGN KEY (`airplane_id`) REFERENCES `airplane` (`airplane_id`),
  CONSTRAINT `FKsybl41p362b1vsb506td0led2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight`
--

LOCK TABLES `flight` WRITE;
/*!40000 ALTER TABLE `flight` DISABLE KEYS */;
INSERT INTO `flight` VALUES (1,'2023-02-17 06:59:37.386000',1,'2012-10-01 07:45:00.000000','dfdf','2012-10-01 07:45:00.000000','dfdf','hello','2023-02-17 06:59:37.386000',1,1),(2,'2023-02-17 06:59:51.318000',1,'2012-10-01 00:00:00.000000','dfdf','2012-10-01 00:00:00.000000','dfdf','hello','2023-02-17 06:59:51.318000',1,1),(3,'2023-02-17 07:00:01.582000',1,'2012-10-01 00:00:00.000000','dfdf','2012-10-01 00:00:00.000000','dfdf','hello','2023-02-17 07:00:01.582000',1,1),(4,'2023-02-17 07:05:01.414000',1,'2012-10-01 00:00:00.000000','dfdf','2012-10-01 00:00:00.000000','dfdf','hello','2023-02-17 07:05:01.414000',1,1),(5,'2023-02-17 07:05:33.819000',1,'2012-10-02 00:00:00.000000','dfdf','2012-10-01 00:00:00.000000','dfdf','hello','2023-02-17 07:05:33.819000',1,1),(6,'2023-02-17 07:05:34.675000',1,'2012-10-02 00:00:00.000000','dfdf','2012-10-01 00:00:00.000000','dfdf','hello','2023-02-17 07:05:34.675000',1,1),(7,'2023-02-17 07:21:47.091000',1,'2012-10-01 00:00:00.000000','dfdf','2012-10-01 00:00:00.000000','dfdf','hello','2023-02-17 07:21:47.091000',1,1),(8,'2023-02-17 07:29:24.085000',1,'2012-10-02 00:00:00.000000','kochi','2012-10-01 00:00:00.000000','kannur','hello','2023-02-17 07:29:24.085000',1,1),(9,'2023-02-17 07:31:03.306000',1,'2012-10-02 00:00:00.000000','kochi','2012-10-01 00:00:00.000000','kochi','hello','2023-02-17 07:31:03.306000',1,1);
/*!40000 ALTER TABLE `flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seat` (
  `seat_id` int NOT NULL AUTO_INCREMENT,
  `create_date` datetime(6) DEFAULT NULL,
  `delete_flag` tinyint DEFAULT NULL,
  `number` int DEFAULT NULL,
  `price` int DEFAULT NULL,
  `seat_type` varchar(45) DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `cp_fk` int NOT NULL,
  PRIMARY KEY (`seat_id`),
  KEY `FKkmt0ow9e7jb952uav0w3shnqj` (`cp_fk`),
  CONSTRAINT `FKkmt0ow9e7jb952uav0w3shnqj` FOREIGN KEY (`cp_fk`) REFERENCES `flight` (`flight_id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
INSERT INTO `seat` VALUES (1,'2023-02-17 06:59:37.386000',1,2,100,'first class','2023-02-17 06:59:37.386000',1),(2,'2023-02-17 06:59:37.386000',1,2,100,'first class','2023-02-17 06:59:37.386000',1),(3,'2023-02-17 06:59:37.386000',1,2,100,'first class','2023-02-17 06:59:37.386000',1),(4,'2023-02-17 06:59:37.386000',1,2,100,'Business Class','2023-02-17 06:59:37.386000',1),(5,'2023-02-17 06:59:37.386000',1,2,100,'first class','2023-02-17 06:59:37.386000',1),(6,'2023-02-17 06:59:37.386000',1,2,100,'first class','2023-02-17 06:59:37.386000',1),(7,'2023-02-17 06:59:51.317000',1,2,100,'first class','2023-02-17 06:59:51.317000',2),(8,'2023-02-17 06:59:51.317000',1,2,100,'first class','2023-02-17 06:59:51.317000',2),(9,'2023-02-17 06:59:51.317000',1,2,100,'first class','2023-02-17 06:59:51.317000',2),(10,'2023-02-17 06:59:51.317000',1,2,100,'first class','2023-02-17 06:59:51.317000',2),(11,'2023-02-17 06:59:51.318000',1,2,100,'first class','2023-02-17 06:59:51.318000',2),(12,'2023-02-17 06:59:51.318000',1,2,100,'first class','2023-02-17 06:59:51.318000',2),(13,'2023-02-17 07:00:01.581000',1,2,100,'first class','2023-02-17 07:00:01.581000',3),(14,'2023-02-17 07:00:01.581000',1,2,100,'first class','2023-02-17 07:00:01.581000',3),(15,'2023-02-17 07:00:01.581000',1,2,100,'first class','2023-02-17 07:00:01.581000',3),(16,'2023-02-17 07:00:01.581000',1,2,100,'first class','2023-02-17 07:00:01.581000',3),(17,'2023-02-17 07:00:01.582000',1,2,100,'first class','2023-02-17 07:00:01.582000',3),(18,'2023-02-17 07:00:01.582000',1,2,100,'first class','2023-02-17 07:00:01.582000',3),(19,'2023-02-17 07:05:01.414000',1,2,100,'first class','2023-02-17 07:05:01.414000',4),(20,'2023-02-17 07:05:01.414000',1,2,100,'first class','2023-02-17 07:05:01.414000',4),(21,'2023-02-17 07:05:01.414000',1,2,100,'first class','2023-02-17 07:05:01.414000',4),(22,'2023-02-17 07:05:01.414000',1,2,100,'first class','2023-02-17 07:05:01.414000',4),(23,'2023-02-17 07:05:01.414000',1,2,100,'first class','2023-02-17 07:05:01.414000',4),(24,'2023-02-17 07:05:01.414000',1,2,100,'first class','2023-02-17 07:05:01.414000',4),(25,'2023-02-17 07:05:33.818000',1,2,100,'first class','2023-02-17 07:05:33.818000',5),(26,'2023-02-17 07:05:33.818000',1,2,100,'first class','2023-02-17 07:05:33.818000',5),(27,'2023-02-17 07:05:33.819000',1,2,100,'first class','2023-02-17 07:05:33.819000',5),(28,'2023-02-17 07:05:33.819000',1,2,100,'first class','2023-02-17 07:05:33.819000',5),(29,'2023-02-17 07:05:33.819000',1,2,100,'first class','2023-02-17 07:05:33.819000',5),(30,'2023-02-17 07:05:33.819000',1,2,50,'first class','2023-02-17 07:05:33.819000',5),(31,'2023-02-17 07:05:34.674000',1,2,100,'first class','2023-02-17 07:05:34.674000',6),(32,'2023-02-17 07:05:34.674000',1,2,100,'first class','2023-02-17 07:05:34.674000',6),(33,'2023-02-17 07:05:34.674000',1,2,100,'first class','2023-02-17 07:05:34.674000',6),(34,'2023-02-17 07:05:34.674000',1,2,100,'first class','2023-02-17 07:05:34.674000',6),(35,'2023-02-17 07:05:34.674000',1,2,100,'first class','2023-02-17 07:05:34.674000',6),(36,'2023-02-17 07:05:34.674000',1,2,100,'first class','2023-02-17 07:05:34.674000',6),(37,'2023-02-17 07:21:47.091000',1,2,50,'first class','2023-02-17 07:21:47.091000',7),(38,'2023-02-17 07:21:47.091000',1,2,50,'first class','2023-02-17 07:21:47.091000',7),(39,'2023-02-17 07:21:47.091000',1,2,50,'first class','2023-02-17 07:21:47.091000',7),(40,'2023-02-17 07:21:47.091000',1,2,50,'first class','2023-02-17 07:21:47.091000',7),(41,'2023-02-17 07:21:47.091000',1,2,50,'first class','2023-02-17 07:21:47.091000',7),(42,'2023-02-17 07:21:47.091000',1,2,50,'first class','2023-02-17 07:21:47.091000',7),(43,'2023-02-17 07:29:24.085000',1,2,100,'first class','2023-02-17 07:29:24.085000',8),(44,'2023-02-17 07:29:24.085000',1,2,100,'first class','2023-02-17 07:29:24.085000',8),(45,'2023-02-17 07:29:24.085000',1,2,100,'first class','2023-02-17 07:29:24.085000',8),(46,'2023-02-17 07:29:24.085000',1,2,100,'first class','2023-02-17 07:29:24.085000',8),(47,'2023-02-17 07:29:24.085000',1,2,100,'first class','2023-02-17 07:29:24.085000',8),(48,'2023-02-17 07:29:24.085000',1,2,100,'first class','2023-02-17 07:29:24.085000',8),(49,'2023-02-17 07:31:03.306000',1,2,100,'first class','2023-02-17 07:31:03.306000',9),(50,'2023-02-17 07:31:03.306000',1,2,100,'first class','2023-02-17 07:31:03.306000',9),(51,'2023-02-17 07:31:03.306000',1,2,100,'first class','2023-02-17 07:31:03.306000',9),(52,'2023-02-17 07:31:03.306000',1,2,100,'first class','2023-02-17 07:31:03.306000',9),(53,'2023-02-17 07:31:03.306000',1,2,100,'first class','2023-02-17 07:31:03.306000',9),(54,'2023-02-17 07:31:03.306000',1,2,100,'first class','2023-02-17 07:31:03.306000',9);
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `create_date` datetime(6) NOT NULL,
  `dob` date DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `passport_number` varchar(50) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `role` tinyint NOT NULL,
  `status` tinyint NOT NULL,
  `update_date` datetime(6) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Thottiparambil','Angamaly','india','2023-02-17 06:54:03.409000','2001-12-08','abins@gmail.com','Manu Radvi','1234xx78','{bcrypt}$2a$10$djB159kowe2NNcSLFYk57.D4MU8pKY4y.YRJStPyk7.vOQVf44zTa','9074828539',2,2,'2023-02-17 06:54:03.409000'),(2,'Thottiparambilf','Angamaly','','2023-02-17 09:21:28.546000','2001-12-08','company@gmail.com','Manu Radvi','1234xx78','{bcrypt}$2a$10$6FBaccx3tx195xWEMLczJerU/KsFLlEfVqtFM/nLhAvKqCFZKUBgG','9074828539',3,2,'2023-02-22 04:26:29.425000');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-23 15:53:32
