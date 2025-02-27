-- MySQL dump 10.13  Distrib 5.5.54, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: airport
-- ------------------------------------------------------
-- Server version	5.5.54-0ubuntu0.12.04.1


SET FOREIGN_KEY_CHECKS=0;

CREATE DATABASE  IF NOT EXISTS `airport`; 
USE `airport`;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `AddressID` int(11) NOT NULL,
  `ZIPCode` varchar(10) NOT NULL,
  `Town` varchar(100) NOT NULL,
  `Street` varchar(100) NOT NULL,
  `CountryCod` char(2) NOT NULL,
  PRIMARY KEY (`AddressID`),
  KEY `CountryCod` (`CountryCod`),
  CONSTRAINT `address_ibfk_1` FOREIGN KEY (`CountryCod`) REFERENCES `country` (`CountryCod`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;

INSERT INTO `address` VALUES (1,'13405','Berlin','Flughafen Tegel','DE'),(2,'40474','Düsseldorf','Flughafenstraße 120','DE'),(3,'60547','Frankfurt am Main','Frankfurt Flughafen','DE'),(4,'85356','München','Nordallee 25','DE'),(5,'95700','Paris','Paris Charles de Gaulle Airport','FR'),(6,'19-004','Attiki Odos','Spata Artemida','GR'),(7,'TW6','London','Greater London TW6','GB'),(8,'40','Ciampino Roma','Via Appia Nuova 1651','IT'),(9,'2061','Gardermoen','Edvard Munchs veg','NO'),(10,'6301','Mississauga','Silver Dart Dr','CA'),(11,'60512','Frankfurt am Main','Hauptstraße 1','DE'),(12,'60157','Frankfurt am Main','Fischerstraße 42','DE'),(13,'60134','Frankfurt am Main','Bankweg 135','DE'),(14,'65495','Frankfurt am Main','Windgasse 9','DE'),(15,'64358','Frankfurt am Main','Achenbachstraße 5','DE'),(16,'65260','Frankfurt am Main','Adalbertstraße 3','DE'),(17,'60154','Frankfurt am Main','Adam-Opel-Straße 4','DE'),(18,'10115','Berlin','Zimmerstraße 4','DE'),(19,'10179','Berlin','Rudi-Dutschke-Straße 134','DE'),(20,'10243','Berlin','Oranienstraße 9','DE');

UNLOCK TABLES;

--
-- Table structure for table `airport`
--

DROP TABLE IF EXISTS `airport`;

CREATE TABLE `airport` (
  `ICAO_Code` varchar(4) NOT NULL,
  `AirportName` varchar(100) NOT NULL,
  `AddressID` int(11) NOT NULL,
  PRIMARY KEY (`ICAO_Code`),
  KEY `AddressID` (`AddressID`),
  CONSTRAINT `airport_ibfk_1` FOREIGN KEY (`AddressID`) REFERENCES `address` (`AddressID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Dumping data for table `airport`
--

LOCK TABLES `airport` WRITE;

INSERT INTO `airport` VALUES ('CYYZ','Toronto Pearson International Airport',10),('EDDF','Frankfurt am Main',3),('EDDL','Düsseldorf International',2),('EDDM','München (Franz Josef Strauß)',4),('EDDT','Berlin-Tegel (Otto-Lilienthal)',1),('EGLC','London City Airport',7),('ENGM','Flughafen Oslo-Gardermoen',9),('LFPG','Paris-Charles de Gaulle',5),('LGAV','Athens International Airport',6),('LIRA','Flughafen Rom-Ciampino',8);

UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;

CREATE TABLE `country` (
  `CountryCod` char(2) NOT NULL,
  `CountryName` varchar(100) NOT NULL,
  PRIMARY KEY (`CountryCod`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;

INSERT INTO `country` VALUES ('CA','Canada'),('DE','Germany'),('FR','France'),('GB','United Kingdom'),('GR','Greece'),('IT','Italy'),('NO','Norway');

UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `ID` int(11) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `AddressID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `AddressID` (`AddressID`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`AddressID`) REFERENCES `address` (`AddressID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop function if exists existeCliente;
delimiter $$
create function existeCliente(clienteId int(11)) returns tinyint
deterministic
begin
	declare existe tinyint;
    select count(*) into existe
    from customer
    where id = clienteId;
    
    return existe;
end $$
delimiter ;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;

INSERT INTO `customer` VALUES (15943155,'Müller','Elias',13),(15946782,'Richter','Thomas',20),(16579435,'Schmidt','Alexander',11),(28943155,'Weber','Sabrina',16),(31764155,'Koch','Nicole',17),(35941358,'Weber','Sabine',18),(35943143,'Schneider','Daniel',14),(35943147,'Schmidt','Stefanie',12),(73941738,'Fischer','David',15),(99188655,'Neumann','Emil',19);

UNLOCK TABLES;

--
-- Table structure for table `flightexecution`
--

DROP TABLE IF EXISTS `flightexecution`;

CREATE TABLE `flightexecution` (
  `FlightNo` varchar(7) NOT NULL,
  `DeparTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Origin` varchar(4) NOT NULL,
  `Destination` varchar(4) NOT NULL,
  `PlaneID` int(11) NOT NULL,
  `DurationMinutes` int(11) NOT NULL,
  PRIMARY KEY (`FlightNo`,`DeparTime`),
  KEY `PlaneID` (`PlaneID`),
  KEY `Origin` (`Origin`),
  KEY `Destination` (`Destination`),
  CONSTRAINT `flightexecution_ibfk_1` FOREIGN KEY (`PlaneID`) REFERENCES `plane` (`PlaneID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `flightexecution_ibfk_2` FOREIGN KEY (`Origin`) REFERENCES `airport` (`ICAO_Code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `flightexecution_ibfk_3` FOREIGN KEY (`Destination`) REFERENCES `airport` (`ICAO_Code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Dumping data for table `flightexecution`
--

LOCK TABLES `flightexecution` WRITE;

INSERT INTO `flightexecution` VALUES ('IBE1684','2017-12-31 10:30:45','LIRA','ENGM',2,200),('IBE1764','2017-12-31 10:30:45','EGLC','EDDT',1,180),('IBE1846','2017-12-31 10:30:45','EDDF','EDDL',3,160),('IBE3843','2017-12-31 10:30:45','ENGM','EDDM',2,120),('IBE4681','2017-12-31 10:30:45','EDDF','LIRA',4,180),('LH1167','2017-12-31 10:30:45','LFPG','LGAV',2,180),('LH1354','2017-12-31 10:30:45','EDDT','LIRA',1,120),('LH1761','2017-12-31 10:30:45','EDDF','EDDT',4,240),('LH1769','2017-12-31 10:30:45','LIRA','EDDF',1,180),('LH1943','2017-12-31 10:30:45','EDDL','EGLC',2,180),('LH1973','2017-12-31 10:30:45','EDDF','LFPG',3,180),('LH2301','2017-07-26 10:50:00','EDDF','CYYZ',2,340),('LH3333','2017-06-05 09:30:45','EDDF','EDDM',1,155),('LH3584','2017-12-31 10:30:45','EDDM','EDDF',1,180),('LH3842','2017-12-31 10:30:45','EGLC','EDDL',4,120),('LH5301','2017-07-02 16:20:00','EDDF','CYYZ',1,740),('LH7660','2017-12-31 10:30:45','LGAV','EDDM',3,240);

UNLOCK TABLES;

--
-- Table structure for table `plane`
--

DROP TABLE IF EXISTS `plane`;

CREATE TABLE `plane` (
  `PlaneID` int(11) NOT NULL,
  `NoOfSeats` int(11) NOT NULL,
  `PlaneType` varchar(100) NOT NULL,
  `PlaneName` varchar(100) NOT NULL,
  PRIMARY KEY (`PlaneID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Dumping data for table `plane`
--

LOCK TABLES `plane` WRITE;

INSERT INTO `plane` VALUES (1,555,'Airbus','Airbus A380'),(2,85,'Fokker','Fokker F100'),(3,162,'Boeing','Boeing 737'),(4,380,'Airbus','Airbus A340');

UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;

CREATE TABLE `reservation` (
  `ReservationID` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `Seats` int(11) NOT NULL,
  `Comment` varchar(1000) DEFAULT NULL,
  `FlightNo` varchar(7) NOT NULL,
  `DeparTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ReservationID`),
  KEY `Matrikelnummer` (`CustomerID`),
  KEY `FlightNo` (`FlightNo`,`DeparTime`),
  CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `customer` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`FlightNo`, `DeparTime`) REFERENCES `flightexecution` (`FlightNo`, `DeparTime`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Dumping data for table `reservation`
--

ALTER TABLE plane
	MODIFY COLUMN PlaneId INT AUTO_INCREMENT;
    
LOCK TABLES `reservation` WRITE;

INSERT INTO `reservation` VALUES (1,16579435,1,'','LH1973','2017-12-31 10:30:45'),(2,35943147,1,'','LH3584','2017-12-31 10:30:45'),(3,15943155,2,'Die Plätze sollten nebeneinander liegen','LH1167','2017-12-31 10:30:45'),(4,35943143,1,'','LH7660','2017-12-31 10:30:45'),(5,73941738,1,'','LH3842','2017-12-31 10:30:45');

UNLOCK TABLES;