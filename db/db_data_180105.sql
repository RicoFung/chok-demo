-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: localhost    Database: origami
-- ------------------------------------------------------
-- Server version	5.5.49

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
-- Dumping data for table `tb_category`
--

LOCK TABLES `tb_category` WRITE;
/*!40000 ALTER TABLE `tb_category` DISABLE KEYS */;
INSERT INTO `tb_category` VALUES (30,'植物',2),(31,'交通',4),(32,'昆虫',3),(175,'Hello1',0);
/*!40000 ALTER TABLE `tb_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tb_dt`
--

LOCK TABLES `tb_dt` WRITE;
/*!40000 ALTER TABLE `tb_dt` DISABLE KEYS */;
INSERT INTO `tb_dt` VALUES (1,1,'dtc1'),(2,2,'dtc2'),(3,1,'dtc3'),(4,2,'dtc4'),(5,3,'dtc5'),(6,4,'dtc6'),(7,3,'dtc7'),(8,4,'dtc8'),(9,5,'dtc9');
/*!40000 ALTER TABLE `tb_dt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tb_hd`
--

LOCK TABLES `tb_hd` WRITE;
/*!40000 ALTER TABLE `tb_hd` DISABLE KEYS */;
INSERT INTO `tb_hd` VALUES (1,'hdc1'),(2,'hdc2'),(3,'hdc3'),(4,'hdc4'),(5,'hdc5');
/*!40000 ALTER TABLE `tb_hd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tb_image`
--

LOCK TABLES `tb_image` WRITE;
/*!40000 ALTER TABLE `tb_image` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tb_model`
--

LOCK TABLES `tb_model` WRITE;
/*!40000 ALTER TABLE `tb_model` DISABLE KEYS */;
INSERT INTO `tb_model` VALUES (22,30,'树',1),(23,30,'小草',2),(24,30,'四叶草',3),(25,32,'蝴蝶',1),(26,32,'甲虫',2),(27,31,'小船',1);
/*!40000 ALTER TABLE `tb_model` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-05 15:57:04
