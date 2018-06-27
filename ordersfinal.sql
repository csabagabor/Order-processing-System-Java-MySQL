-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: orderman
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.29-MariaDB

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`id`, `Name`) VALUES (1,'Apparel'),(2,'Toys'),(3,'Electronics'),(4,'Food'),(5,'Sport Equipment'),(6,'Cars'),(7,'Ships'),(8,'Flowers'),(9,'Jewellery'),(10,'Home&Garden');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `age` int(11) NOT NULL,
  `Phone` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` (`id`, `Name`, `city`, `address`, `email`, `age`, `Phone`) VALUES (1,'Big Joe Davis','Cluj-Napoca',' Bulevardul 54','big_joe@gmail.com',18,'0741111111'),(2,'Small John Dane','Cluj-Napoca','Corneliu Coposu 12','small_john@gmail.com',22,'0741111112'),(3,'Travolta John','Cluj-Napoca','Bulevardul 11','travolta_john@gmail.com',54,'0741111113'),(4,'Anabell Smith ','Cluj-Napoca','Muncii 22','anabell12@gmail.com',22,'0741111114'),(5,'John Dave','Cluj-Napoca','Bulevardul 77','johny112@gmail.com',25,'0741111115'),(6,'Mircea Dan','Cluj-Napoca','Muncii 11','dan112@yahoo.com',72,'0741111116'),(7,'Iris Hieb','Cluj-Napoca','Muncii 77','iris55@gmail.com',55,'0741111117'),(8,'Carrie Victoria','Cluj-Napoca','Eroilor 4','carry22@gmail.com',33,'0741111118'),(9,'Antonio Lup','Cluj-Napoca','Avram Iancu 5','antonio988@yahoo.com',63,'0741111119'),(10,'Velkan Ilie','Bucharest','Unirii  12','il_velkan@yahoo.com',26,'0741111121'),(11,'Horea Gilca','Bucharest','Mosilor 88','ho_gil@gmail.com',84,'0741111122'),(12,'Costica Pavel','Bucharest','Calea Victoriei 9','costic@gmail.com',91,'0741111123'),(13,'Iancu Petri','Bucharest','Calea Victoriei19','iancu31@gmail.com',19,'0741111124'),(14,'Flavius Pavel','Targu Mures','Bolyai 80','flavi21@yahoo.com',43,'0741111125'),(15,'Daniel Nicolae','Targu Mures','Bolyai 70','daniel90@gmail.com',28,'0741111126');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `new_view`
--

DROP TABLE IF EXISTS `new_view`;
/*!50001 DROP VIEW IF EXISTS `new_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `new_view` AS SELECT 
 1 AS `id`,
 1 AS `idClient`,
 1 AS `idProduct`,
 1 AS `quantity`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `idClient` int(11) NOT NULL,
  `idProduct` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `date` date NOT NULL,
  `idShipper` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idClient_idx` (`idClient`),
  KEY `idProduct_idx` (`idProduct`),
  KEY `idShipper_idx` (`idShipper`),
  CONSTRAINT `idClient` FOREIGN KEY (`idClient`) REFERENCES `client` (`id`),
  CONSTRAINT `idProduct` FOREIGN KEY (`idProduct`) REFERENCES `product` (`id`),
  CONSTRAINT `idShipper` FOREIGN KEY (`idShipper`) REFERENCES `shipper` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`id`, `idClient`, `idProduct`, `quantity`, `date`, `idShipper`) VALUES (1,1,1,3,'2018-04-09',1),(2,14,3,10,'2018-04-09',4),(3,6,4,3,'2018-04-09',2),(4,4,4,1,'2018-04-09',4),(5,5,20,3,'2018-04-10',4),(6,3,20,1,'2018-04-10',4),(7,1,7,1,'2018-04-16',1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `quantity` int(11) NOT NULL,
  `unitPrice` int(11) NOT NULL,
  `supplierId` int(11) NOT NULL,
  `categoryId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `supplierID_idx` (`supplierId`),
  KEY `categoryID_idx` (`categoryId`),
  CONSTRAINT `categoryID` FOREIGN KEY (`categoryId`) REFERENCES `category` (`id`),
  CONSTRAINT `supplierID` FOREIGN KEY (`supplierId`) REFERENCES `supplier` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`id`, `Name`, `quantity`, `unitPrice`, `supplierId`, `categoryId`) VALUES (1,'Chrismas T-shirt',110,99,1,1),(2,'Lego Duplo Castle',126,85,2,2),(3,'Lego Castle',137,80,3,2),(4,'Salami Sasesc',0,33,6,4),(5,'Spaghetti Italy',60,13,5,4),(6,'Ski XL',0,723,10,5),(7,'Samsung S8',8,2000,5,3),(8,'Fiat Punto',2,45000,2,6),(9,'Samsung S9',2,2800,2,3),(10,'Minge Fotbal Adidas',20,99,10,5),(11,'Huavei P9',15,1600,5,3),(12,'Lego Duplo Construction',60,50,2,2),(13,'Minge Fotbal Mini',50,55,10,5),(14,'Ford Focus',1,40000,2,6),(15,'Barbie Doll',150,99,3,2),(16,'Bike Merida XST',4,1050,10,5),(17,'Dog Plus',50,25,4,2),(18,'Pokemon cards',49,60,4,2),(19,'Bike Focus Raven',3,5400,10,5),(20,'Necklace silver',11,220,8,9);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipper`
--

DROP TABLE IF EXISTS `shipper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shipper` (
  `id` int(11) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `ShippingTime` int(11) NOT NULL,
  `Phone` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipper`
--

LOCK TABLES `shipper` WRITE;
/*!40000 ALTER TABLE `shipper` DISABLE KEYS */;
INSERT INTO `shipper` (`id`, `Name`, `ShippingTime`, `Phone`) VALUES (1,'Fan Curier',3,'0755555555'),(2,'Cargus',3,'0755555556'),(3,'DHL',2,'0755555557'),(4,'Priority Shipping',1,'0755555558'),(5,'Royal Mail 2',12,'0755555559'),(6,'ExpressShipping',1,'0755555560');
/*!40000 ALTER TABLE `shipper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier` (
  `id` int(11) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Country` varchar(45) NOT NULL,
  `City` varchar(45) NOT NULL,
  `Phone` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` (`id`, `Name`, `Country`, `City`, `Phone`) VALUES (1,'Bosh','Romania','Cluj-Napoca','0040777111'),(2,'Emag','Romania','Cluj-Napoca','0040777112'),(3,'Altex','Romania','Cluj-Napoca','0040777113'),(4,'MediaGalaxy','Romania','Cluj-Napoca','0040777114'),(5,'Tech4','Romania','Bucharest','0040777115'),(6,'MediaMArkt','Romania','Bucharest','0040777116'),(7,'DM','Romania','Bucharest','0040777117'),(8,'H&M','Romania','Cluj-Napoca','0040777118'),(9,'C&A','Romania','Cluj-Napoca','0040777119'),(10,'Hervis','Romania','Cluj-Napoca','0040777122');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'orderman'
--

--
-- Dumping routines for database 'orderman'
--

--
-- Final view structure for view `new_view`
--

/*!50001 DROP VIEW IF EXISTS `new_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `new_view` AS select `orders`.`id` AS `id`,`orders`.`idClient` AS `idClient`,`orders`.`idProduct` AS `idProduct`,`orders`.`quantity` AS `quantity` from `orders` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-16 20:01:16
