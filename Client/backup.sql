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
INSERT INTO `airplane` VALUES (1,'M62ED','2023-02-09 06:34:15.328000','B6322',1,200,'2023-02-09 06:34:15.328000',1);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
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
  `least_price` int DEFAULT NULL,
  `price` int DEFAULT NULL,
  `min` int DEFAULT NULL,
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
INSERT INTO `flight` VALUES (6,'2023-02-09 06:34:21.682000',1,'2012-10-01 07:45:00.000000','ssdsd','2012-10-01 07:45:00.000000','dfdf','hello','2023-02-09 06:34:21.682000',1,1,100,NULL,NULL),(7,'2023-02-09 06:55:20.287000',1,'2012-10-01 07:45:00.000000','ssdsd','2012-10-01 07:45:00.000000','dfdf','hello','2023-02-09 06:55:20.287000',1,1,100,NULL,NULL),(8,'2023-02-09 06:55:46.907000',1,'2012-10-01 07:45:00.000000','ssdsd','2012-10-01 07:45:00.000000','dfdf','hello','2023-02-09 06:55:46.907000',1,1,100,NULL,NULL),(9,'2023-02-13 12:00:31.560000',1,'2012-10-01 07:45:00.000000','ssdsd','2012-10-01 07:45:00.000000','dfdf','hello','2023-02-13 12:00:31.560000',1,1,10,NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
INSERT INTO `seat` VALUES (1,'2023-02-09 06:34:21.682000',1,2,100,'first class','2023-02-09 06:34:21.682000',6),(2,'2023-02-09 06:34:21.682000',1,2,100,'first class','2023-02-09 06:34:21.682000',6),(3,'2023-02-09 06:34:21.682000',1,2,100,'first class','2023-02-09 06:34:21.682000',6),(4,'2023-02-09 06:34:21.682000',1,2,100,'first class','2023-02-09 06:34:21.682000',6),(5,'2023-02-09 06:34:21.682000',1,2,100,'first class','2023-02-09 06:34:21.682000',6),(6,'2023-02-09 06:34:21.682000',1,2,100,'first class','2023-02-09 06:34:21.682000',6),(7,'2023-02-09 06:55:20.285000',1,2,100,'first class','2023-02-09 06:55:20.285000',7),(8,'2023-02-09 06:55:20.285000',1,2,100,'first class','2023-02-09 06:55:20.285000',7),(9,'2023-02-09 06:55:20.286000',1,2,100,'first class','2023-02-09 06:55:20.286000',7),(10,'2023-02-09 06:55:20.286000',1,2,100,'first class','2023-02-09 06:55:20.286000',7),(11,'2023-02-09 06:55:20.286000',1,2,100,'first class','2023-02-09 06:55:20.286000',7),(12,'2023-02-09 06:55:20.286000',1,2,100,'first class','2023-02-09 06:55:20.286000',7),(13,'2023-02-09 06:55:46.907000',1,2,100,'first class','2023-02-09 06:55:46.907000',8),(14,'2023-02-09 06:55:46.907000',1,2,100,'first class','2023-02-09 06:55:46.907000',8),(15,'2023-02-09 06:55:46.907000',1,2,100,'first class','2023-02-09 06:55:46.907000',8),(16,'2023-02-09 06:55:46.907000',1,2,100,'first class','2023-02-09 06:55:46.907000',8),(17,'2023-02-09 06:55:46.907000',1,2,100,'first class','2023-02-09 06:55:46.907000',8),(18,'2023-02-09 06:55:46.907000',1,2,100,'first class','2023-02-09 06:55:46.907000',8),(19,'2023-02-13 12:00:31.558000',1,2,100,'first class','2023-02-13 12:00:31.558000',9),(20,'2023-02-13 12:00:31.558000',1,2,10,'first class','2023-02-13 12:00:31.558000',9);
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
INSERT INTO `user` VALUES (1,'Thottiparambil','Angamaly','india','2023-02-09 06:34:04.580000','2001-12-08','abins@gmail.com','Manu Radvi','1234xx78','{bcrypt}$2a$10$wTQolwBdV1KOlm2n.gK1/uaMHLRD3kTi6oseER50Wv2rCeO8nX.py','9074828539',2,2,'2023-02-09 06:34:04.580000'),(2,'sd','kalady','DUBAI','2023-02-13 04:14:20.837000','2001-12-08','passenger@gmail.com','passenger','A2302333','{bcrypt}$2a$10$.7qkHOHw4lWVFwQej3ASA.zRGcQYYOxRra.H9iqhty51Q8o3akVei','9045215689',3,1,'2023-02-13 04:14:20.837000');
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

-- Dump completed on 2023-02-14 15:15:51
