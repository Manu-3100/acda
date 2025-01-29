-- MySQL dump 10.13  Distrib 5.5.31, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: rental
-- ------------------------------------------------------
-- Server version	5.5.31-0ubuntu0.12.04.2

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

create database rental;
use rental;


--
-- Table structure for table `cargroups`
--

DROP TABLE IF EXISTS `cargroups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cargroups` (
  `car_group_name` char(2) NOT NULL,
  `rate_per_mile` float DEFAULT NULL,
  `rate_per_day` float DEFAULT NULL,
  PRIMARY KEY (`car_group_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargroups`
--

LOCK TABLES `cargroups` WRITE;
/*!40000 ALTER TABLE `cargroups` DISABLE KEYS */;
INSERT INTO `cargroups` VALUES ('A1',110,25),('A2',115,24.5),('A3',155,36.75),('A4',125,33.5),('B1',110,25.9),('B2',125,33),('B3',135,37.75),('B4',135,38.5);
/*!40000 ALTER TABLE `cargroups` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `cust_no` int(11) NOT NULL,
  `cust_name` char(20) DEFAULT NULL,
  `address` char(20) DEFAULT NULL,
  `town` char(20) DEFAULT NULL,
  `county` char(20) DEFAULT NULL,
  `post_code` char(10) DEFAULT NULL,
  `contact` char(20) DEFAULT NULL,
  `pay_method` char(1) DEFAULT NULL,
  PRIMARY KEY (`cust_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (667,'G G WHITTAKER  CO','WHEATLEY ROAD','DONCASTER','YORKSHIRE','DN5 8AA','Chris Whittaker','A'),(668,'THRINGS AND CO','MOUNTEBANK HOUSE','BASINGSTOKE','HANTS','RG22 1EL','Brian Jones','A'),(701,'MCGHIE ENTERPRISES','CAMPFIELD ROAD','BASINGSTOKE','HANTS','BK10 1BC','Bob McGhie','C'),(734,'CARL PETROV LTD','62 CADOGAN AVE','BRISTOL','AVON','BS1 6TQ','Jim Slattery','A'),(1086,'ADT LTD','BUMPERS IND EST','CHIPPENHAM','WILTS','SN14 6LH','James Nugent','C'),(1452,'STEVEN BEARE','NETTLETON HOUSE','CASTLE COMBE','WILTSHIRE','SN13 7NJ','Steven Beare','C'),(1553,'EYRIE BOOKS LTD','2 ST JOHNS AVE','LONDON',NULL,'NW3 6TQ','Alisdair Aird','A'),(2029,'SQL STARS LTD','10 PENN LEA ROAD','BATH','AVON','BA2 5MX','Stan Blethyn','A'),(2267,'WORTHINGTON  CO','16 LENANT ST','LONDON',NULL,'SE1 5SF','David Worthington','A'),(2338,'PETER BOGDANOVICH','52 LONG DEAN','WINDSOR','BERKS','SL14 6TQ','Peter Bogdanovich','A'),(2804,'FOCUST MARKETING LTD','555 SLOUGH ROAD','WOKINGHAM','BERKS','RG23 1JK','Jeanna Polly','C'),(8979,'J PARSONS LTD','PARSONS PLAZA','WEYBRIDGE','SURREY','KT12 8PP','Paul Bentley','A'),(8981,'CARDINAL CARDS LTD','56 LOWER REGENT ST','LONDON',NULL,'W1A 8TW','Linden Boyne','A');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `models`
--

DROP TABLE IF EXISTS `models`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `models` (
  `model_name` char(8) NOT NULL,
  `car_group_name` char(2) DEFAULT NULL,
  `description` char(30) DEFAULT NULL,
  `maint_int` float DEFAULT NULL,
  PRIMARY KEY (`model_name`),
  KEY `fk_models_1` (`car_group_name`),
  CONSTRAINT `fk_models_1` FOREIGN KEY (`car_group_name`) REFERENCES `cargroups` (`car_group_name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `models`
--

LOCK TABLES `models` WRITE;
/*!40000 ALTER TABLE `models` DISABLE KEYS */;
INSERT INTO `models` VALUES ('ASTON V8','A1','ASTON MARTIN V8 VANTAGE',10000),('BMW 635','B2','BMW 635 csi',12000),('BMW 750','B3','BMW 750',12000),('FERR TR','A4','Ferrari Testarossa',10000),('JAG XJ6','A3','Jaguar XJ6 V12 Sovereign',15000),('JAG XJS','A2','Jaguar XJS V12 5.2l',15000),('LAMB COU','A4','Lamborghini Countach',6000),('MERC 560','B3','Mercedes 560 SEL',24000),('P911 TC','A3','Porsche 911 TC Cabriolet',12000),('P944 T','A3','Porsche 944 Turbo',12000),('RR SSPIR','B4','Rolls Royce Silver Spirit',6000);
/*!40000 ALTER TABLE `models` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cars`
--

DROP TABLE IF EXISTS `cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cars` (
  `registration` char(7) NOT NULL,
  `model_name` char(8) DEFAULT NULL,
  `date_bought` date DEFAULT NULL,
  `cost` float DEFAULT NULL,
  `miles_to_date` int(11) DEFAULT NULL,
  `miles_last_service` int(11) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  PRIMARY KEY (`registration`),
  KEY `fk_cars_1` (`model_name`),
  CONSTRAINT `fk_cars_1` FOREIGN KEY (`model_name`) REFERENCES `models` (`model_name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cars`
--

LOCK TABLES `cars` WRITE;
/*!40000 ALTER TABLE `cars` DISABLE KEYS */;
INSERT INTO `cars` VALUES ('E246WFC','RR SSPIR','1988-01-06',84500,52861,48253,'H'),('F111ENT','P911 TC','1988-09-03',75200,30286,24502,'A'),('F651DEK','RR SSPIR','1989-01-10',92500,29610,24460,'A'),('G123RMR','BMW 750','1989-11-16',56829,42400,25366,'A'),('G202XRP','P944 T','1989-02-14',48350,28604,23666,'A'),('G551JBA','JAG XJ6','1989-09-02',45500,13788,11950,'A'),('G899VDU','BMW 635','1989-10-23',45500.3,18675,12667,'A'),('G994PBR','FERR TR','1990-03-31',102450,10662,6004,'A'),('H203PBR','LAMB COU','1990-11-10',130250,7025,903,'H'),('H235BMA','LAMB COU','1990-11-10',130250,9611,1156,'H'),('H266MHU','ASTON V8','1991-04-01',63000,2597,1000,'A'),('H311MHG','MERC 560','1990-11-09',60800,12450,11832,'A'),('H626RPG','JAG XJS','1990-09-26',32650.3,14533,12769,'A'),('H875DES','BMW 750','1991-04-08',58321,8354,973,'A'),('H935CSA','FERR TR','1991-06-23',105600,3005,998,'H'),('J185NED','P911 TC','1991-10-15',78350,875,NULL,'A'),('J516BTA','P944 T','1991-09-02',50301,1502,NULL,'A'),('J644TNR','MERC 560','1991-10-10',65800,1821,1201,'H'),('J706BEG','JAG XJ6','1991-09-16',39250.5,2110,1132,'H'),('J933RCE','FERR TR','1991-10-30',111200,957,NULL,'A');
/*!40000 ALTER TABLE `cars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookings` (
  `booking_no` int(11) NOT NULL,
  `cust_no` int(11) DEFAULT NULL,
  `date_reserved` date DEFAULT NULL,
  `reserved_by` char(12) DEFAULT NULL,
  `date_rent_start` date DEFAULT NULL,
  `rental_period` int(11) DEFAULT NULL,
  `registration` char(7) DEFAULT NULL,
  `miles_out` int(11) DEFAULT NULL,
  `miles_in` int(11) DEFAULT NULL,
  `amount_due` float DEFAULT NULL,
  `paid` char(1) DEFAULT NULL,
  PRIMARY KEY (`booking_no`),
  KEY `fk_bookings_1` (`registration`),
  KEY `fk_bookings_2` (`cust_no`),
  CONSTRAINT `fk_bookings_1` FOREIGN KEY (`registration`) REFERENCES `cars` (`registration`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_bookings_2` FOREIGN KEY (`cust_no`) REFERENCES `customers` (`cust_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES (118,8979,'1991-01-23','JANE B','1991-01-23',4,'H626RPG',800,1692,1123.8,'N'),(265,2029,'1991-04-09','ERIC','1991-05-04',16,'E246WFC',35262,35818,1366.6,'N'),(502,1553,'1990-11-29','JANE B','1990-12-20',15,'G123RMR',500,3045,4002,'N'),(503,2267,'1991-04-12','ERIC','1991-04-26',5,'H203PBR',3521,3862,593.75,'Y'),(504,1553,'1991-04-12','JANE H','1991-04-12',1,'G551JBA',9527,9811,476.95,'N'),(586,667,'1991-04-13','JANE B','1991-04-26',21,'F111ENT',25967,26818,2090.8,'Y'),(599,667,'1991-12-21',NULL,'1992-01-01',3,'H311MHG',12450,NULL,NULL,NULL),(810,1452,'1991-09-03','ERIC','1991-09-10',3,'J516BTA',103,767,1139.45,'Y'),(811,701,'1991-09-04','JANE H','1991-09-05',8,'G202XRP',26197,26833,1279.8,'N'),(812,2338,'1991-09-04','ERIC','1991-10-15',15,'J644TNR',119,1535,2477.85,'N'),(813,2804,'1991-09-04','JANE H','1991-10-08',1,'H235BMA',7899,8054,227.25,'Y'),(2122,734,'1991-09-21',NULL,'1991-09-21',1,'G994PBR',10662,NULL,NULL,NULL),(2123,667,'1991-09-08',NULL,'1991-09-19',5,'H935CSA',23774,25865,501.8,'N'),(2124,667,'1991-09-18',NULL,'1991-09-19',5,'G123RMR',61554,72334,1023,'N'),(2125,668,'1991-10-02',NULL,'1991-10-02',2,'G551JBA',1098,882,100,'Y'),(2126,667,'1991-10-02',NULL,'1991-10-02',10,'H266MHU',5583,6254,873.6,'N'),(2127,668,'1991-10-01',NULL,'1991-10-01',1,'J644TNR',8891,9024,106,'N'),(2128,701,'1991-04-13',NULL,'1991-04-14',10,'H235BMA',44512,45277,512,'N');
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;





USE `rental`;
DROP procedure IF EXISTS `debedasCustomer`;
DELIMITER $$
CREATE PROCEDURE `rental`.`debedasCustomer` (in customer int(11), out total float)
BEGIN
select sum(amount_due) into total from bookings where cust_no=customer and paid='N';
select * from bookings where cust_no=customer and paid='N';
END$$

DELIMITER ;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-06-19 20:27:14
