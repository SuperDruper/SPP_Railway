CREATE DATABASE IF NOT EXISTS `railway` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `railway`;
-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: railway
-- ------------------------------------------------------
-- Server version	5.7.10

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `distance`
--

DROP TABLE IF EXISTS `distance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `distance` (
  `st_id_from` int(11) NOT NULL,
  `st_id_to` int(11) NOT NULL,
  `d_distance` int(11) NOT NULL,
  PRIMARY KEY (`st_id_from`,`st_id_to`),
  KEY `IXFK_distance_station` (`st_id_from`),
  KEY `IXFK_distance_station_02` (`st_id_to`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `distance`
--

LOCK TABLES `distance` WRITE;
/*!40000 ALTER TABLE `distance` DISABLE KEYS */;
INSERT INTO `distance` (`st_id_from`, `st_id_to`, `d_distance`) VALUES (1,2,200),(1,3,200),(1,4,300),(2,3,400),(2,4,500),(3,4,100);
/*!40000 ALTER TABLE `distance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `race`
--

DROP TABLE IF EXISTS `race`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `race` (
  `rc_id` int(11) NOT NULL AUTO_INCREMENT,
  `rt_id` int(11) NOT NULL,
  `tr_id` int(11) NOT NULL,
  PRIMARY KEY (`rc_id`),
  KEY `IXFK_race_route` (`rt_id`),
  KEY `IXFK_race_train` (`tr_id`),
  CONSTRAINT `FK8jwp541lxw816td33s1gc8ny1` FOREIGN KEY (`rt_id`) REFERENCES `route` (`rt_id`),
  CONSTRAINT `FKihsku9xnf0mb2e4xn9ryckij5` FOREIGN KEY (`tr_id`) REFERENCES `train` (`tr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `race`
--

LOCK TABLES `race` WRITE;
/*!40000 ALTER TABLE `race` DISABLE KEYS */;
INSERT INTO `race` (`rc_id`, `rt_id`, `tr_id`) VALUES (1,1,1),(2,2,2);
/*!40000 ALTER TABLE `race` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `race_station`
--

DROP TABLE IF EXISTS `race_station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `race_station` (
  `rcst_id` int(11) NOT NULL AUTO_INCREMENT,
  `rcst_depature` datetime DEFAULT NULL,
  `rcst_arriving` datetime DEFAULT NULL,
  `st_id` int(11) NOT NULL,
  `rc_id` int(11) NOT NULL,
  PRIMARY KEY (`rcst_id`),
  KEY `IXFK_race_station_race` (`rc_id`),
  KEY `staion_datetime_index` (`st_id`,`rcst_depature`),
  CONSTRAINT `FK31gn5iui12ebu0sba7c3cbevf` FOREIGN KEY (`rc_id`) REFERENCES `race` (`rc_id`),
  CONSTRAINT `FK8e06vpy8f5o98abdj25vup3fs` FOREIGN KEY (`st_id`) REFERENCES `station` (`st_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `race_station`
--

LOCK TABLES `race_station` WRITE;
/*!40000 ALTER TABLE `race_station` DISABLE KEYS */;
INSERT INTO `race_station` (`rcst_id`, `rcst_depature`, `rcst_arriving`, `st_id`, `rc_id`) VALUES (1,'2016-06-29 09:00:00',NULL,2,1),(2,'2016-06-29 11:00:00','2016-06-29 10:45:00',1,1),(3,'2016-06-29 13:00:00','2016-06-29 12:50:00',3,1),(4,NULL,'2016-06-29 14:50:00',4,1),(5,'2016-06-29 13:00:00',NULL,2,2),(6,NULL,'2016-06-29 17:00:00',4,2);
/*!40000 ALTER TABLE `race_station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `rl_id` int(11) NOT NULL AUTO_INCREMENT,
  `rl_name` varchar(50) NOT NULL,
  PRIMARY KEY (`rl_id`),
  UNIQUE KEY `rl_name_UNIQUE` (`rl_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`rl_id`, `rl_name`) VALUES (1,'admin'),(2,'user');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route`
--

DROP TABLE IF EXISTS `route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `route` (
  `rt_id` int(11) NOT NULL AUTO_INCREMENT,
  `rt_name` varchar(45) NOT NULL,
  PRIMARY KEY (`rt_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
INSERT INTO `route` (`rt_id`, `rt_name`) VALUES (1,'Витебск-Брест'),(2,'Витебск-Брест');
/*!40000 ALTER TABLE `route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `station`
--

DROP TABLE IF EXISTS `station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `station` (
  `st_id` int(11) NOT NULL AUTO_INCREMENT,
  `st_name` varchar(50) NOT NULL,
  PRIMARY KEY (`st_id`),
  KEY `I_name` (`st_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station`
--

LOCK TABLES `station` WRITE;
/*!40000 ALTER TABLE `station` DISABLE KEYS */;
INSERT INTO `station` (`st_id`, `st_name`) VALUES (3,'Борисов'),(4,'Брест'),(2,'Витебск'),(6,'Гомель'),(1,'Минск'),(5,'Могилёв');
/*!40000 ALTER TABLE `station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_order_date` datetime DEFAULT NULL,
  `t_num` int(11) NOT NULL,
  `t_carriage_num` int(11) NOT NULL,
  `rc_id` int(11) NOT NULL,
  `u_id` int(11) NOT NULL,
  PRIMARY KEY (`t_id`),
  KEY `IXFK_place_user` (`u_id`),
  KEY `I_Place` (`rc_id`,`t_carriage_num`,`t_num`),
  CONSTRAINT `FKppux5520ca6gc3v2wds7q79s6` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`),
  CONSTRAINT `FKr08cwurtyqcdn8v3j54or2jue` FOREIGN KEY (`rc_id`) REFERENCES `race` (`rc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `train`
--

DROP TABLE IF EXISTS `train`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `train` (
  `tr_id` int(11) NOT NULL AUTO_INCREMENT,
  `tr_carriage_amount` int(11) NOT NULL,
  `trt_id` int(11) NOT NULL,
  PRIMARY KEY (`tr_id`),
  KEY `IXFK_train_train_type` (`trt_id`),
  CONSTRAINT `FKll7cj45gejkkb3elaht3sw3k3` FOREIGN KEY (`trt_id`) REFERENCES `train_type` (`trt_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `train`
--

LOCK TABLES `train` WRITE;
/*!40000 ALTER TABLE `train` DISABLE KEYS */;
INSERT INTO `train` (`tr_id`, `tr_carriage_amount`, `trt_id`) VALUES (1,3,3),(2,10,2),(3,1,1);
/*!40000 ALTER TABLE `train` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `train_type`
--

DROP TABLE IF EXISTS `train_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `train_type` (
  `trt_id` int(11) NOT NULL AUTO_INCREMENT,
  `trt_name` varchar(50) NOT NULL,
  `trt_coefficient` double NOT NULL,
  `trt_places_amount` int(11) NOT NULL,
  PRIMARY KEY (`trt_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `train_type`
--

LOCK TABLES `train_type` WRITE;
/*!40000 ALTER TABLE `train_type` DISABLE KEYS */;
INSERT INTO `train_type` (`trt_id`, `trt_name`, `trt_coefficient`, `trt_places_amount`) VALUES (1,'Platzkart',1.2,54),(2,'Kype',1.4,40),(3,'Obschiy',1,81);
/*!40000 ALTER TABLE `train_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_name` varchar(50) DEFAULT NULL,
  `u_surname` varchar(50) DEFAULT NULL,
  `u_email` varchar(255) NOT NULL,
  `u_login` varchar(50) NOT NULL,
  `u_password` varchar(50) NOT NULL,
  `rl_id` int(11) NOT NULL DEFAULT '2',
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `IX_login` (`u_login`),
  KEY `IXFK_user_role_02` (`rl_id`),
  CONSTRAINT `FKpjd159q38mmxk49hmejpii2b0` FOREIGN KEY (`rl_id`) REFERENCES `role` (`rl_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`u_id`, `u_name`, `u_surname`, `u_email`, `u_login`, `u_password`, `rl_id`) VALUES (1,'Вася','Пупкин','VPypkin@gmail.com','pupkin','pupkin',2),(2,'Алексей','Варфоломеев','profprof1997@gmail.com','AccuType-911','AccuType-911',1),(3,'qwe','qwe','qwe','qwe','qwe',2),(4,'123','123','123','123','123',2),(5,'12','12','12','12','12',2),(8,'qw','qw','qw','qw','qw',2),(9,'q','q','q','q','q',2),(12,'321','321','321','321','321',2),(13,'6','6','6','6','6666666',2),(14,'8','8','8','8','1234567890',2),(15,'qwertyuiop','qwertyuiop','qwertyuiop','qwertyuiop','qwertyuiop',2),(16,'в','в','в','в','1234567890',2),(17,'danya','payk','payk@yandex.ru','danya','1234567890',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'railway'
--

--
-- Dumping routines for database 'railway'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-09 22:42:36
