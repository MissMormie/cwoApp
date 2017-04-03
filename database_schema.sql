CREATE DATABASE  IF NOT EXISTS `cwo_app` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `cwo_app`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: cwo_app
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `cursist`
--

DROP TABLE IF EXISTS `cursist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cursist` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `voornaam` varchar(45) DEFAULT NULL,
  `tussenvoegsel` varchar(45) DEFAULT NULL,
  `achternaam` varchar(45) DEFAULT NULL,
  `opmerkingen` tinytext,
  `paspoort` date DEFAULT NULL,
  `verborgen` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `cursist_behaald_eis`
--

DROP TABLE IF EXISTS `cursist_behaald_eis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cursist_behaald_eis` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `datum` date DEFAULT NULL,
  `cursist_id` int(10) unsigned NOT NULL,
  `diploma_eis_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `constr_ID` (`cursist_id`,`diploma_eis_id`),
  KEY `fk_cursist_behaald_eis_cursist_idx` (`cursist_id`),
  KEY `fk_cursist_behaald_eis_cwo_eis1_idx` (`diploma_eis_id`),
  CONSTRAINT `fk_cursist_behaald_eis_cursist` FOREIGN KEY (`cursist_id`) REFERENCES `cursist` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cursist_behaald_eis_cwo_eis1` FOREIGN KEY (`diploma_eis_id`) REFERENCES `diploma_eis` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `cursist_foto`
--

DROP TABLE IF EXISTS `cursist_foto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cursist_foto` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `thumbnail` mediumblob,
  `image` mediumblob,
  `cursist_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cursist_id_UNIQUE` (`cursist_id`),
  KEY `fk_cursist_foto_cursist1_idx` (`cursist_id`),
  CONSTRAINT `fk_cursist_foto_cursist1` FOREIGN KEY (`cursist_id`) REFERENCES `cursist` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `cursist_heeft_diploma`
--

DROP TABLE IF EXISTS `cursist_heeft_diploma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cursist_heeft_diploma` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `diploma_behaald` date DEFAULT NULL,
  `cursist_id` int(10) unsigned NOT NULL,
  `diploma_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_index` (`cursist_id`,`diploma_id`),
  KEY `fk_cursist_heeft_diploma_cursist1_idx` (`cursist_id`),
  KEY `fk_cursist_heeft_diploma_diploma1_idx` (`diploma_id`),
  CONSTRAINT `fk_cursist_heeft_diploma_cursist1` FOREIGN KEY (`cursist_id`) REFERENCES `cursist` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cursist_heeft_diploma_diploma1` FOREIGN KEY (`diploma_id`) REFERENCES `diploma` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `diploma`
--

DROP TABLE IF EXISTS `diploma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diploma` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `titel` text,
  `nivo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diploma`
--

LOCK TABLES `diploma` WRITE;
/*!40000 ALTER TABLE `diploma` DISABLE KEYS */;
INSERT INTO `diploma` VALUES (1,'Windsurfen',1),(2,'Windsurfen',2),(3,'Windsurfen',3);
/*!40000 ALTER TABLE `diploma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diploma_eis`
--

DROP TABLE IF EXISTS `diploma_eis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diploma_eis` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `titel` text,
  `omschrijving` text,
  `theorie` tinyint(4) DEFAULT '0',
  `diploma_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cwo_eis_diploma1_idx` (`diploma_id`),
  CONSTRAINT `fk_cwo_eis_diploma1` FOREIGN KEY (`diploma_id`) REFERENCES `diploma` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diploma_eis`
--

LOCK TABLES `diploma_eis` WRITE;
/*!40000 ALTER TABLE `diploma_eis` DISABLE KEYS */;
INSERT INTO `diploma_eis` VALUES (1,'Op- en aftuigen','Board klaarleggen, vin en eventueel zwaard monteren. Baseplate monteren. De twee mast delen schoon in elkaar schuiven. Mast in de mastslurf doen. Mastvoet erin, de neerhaler rijgen en aantrekken in de klem vastzetten en lijn borgen. Giek op de juiste hoogte op de mast bevestigen. Uithaler rijgen en aantrekken, in de klem vastzetten en overige lijn borgen. Ophaalkoord bevestigen en tuigage aan het board koppelen. Aftuigen in omgekeerde volgorde van optuigen, schoonmaken en opruimen.',0,1),(2,'Veiligheid en omgeving','Op- en aftuigen: rekening houden met de wind, omgeving en omstanders. Noodstop: op een veilige manier tuigage in het water leggen en zo nodig zelf in het water springen. Bij voorkeur de giek en/of de voetbanden vasthouden. Noodsein geven: met beide armen gestrekt kruisende beweging boven het hoofd maken. Gesleept worden door een volgboot of andere surfer. Efficiënt peddelen met board (en tuig). Verantwoord varen ten opzichte van medewatersporters. Blijf ten alle tijden bij je board',0,1),(3,'Dragen van board en tuig','Het board en tuig op een veilige manier in twee delen naar het water dragen. Indien mogelijk of nodig met twee personen.',0,1),(4,'Basishouding\r','Board halve wind, mast tussen beide voeten in, tuig op een ergonomische (let op je rug) manier uit het water trekken. Masthand aan de mast of twee handen aan het ophaalkoord. Armen gestrekt. Zeil aan lij zijde en een haakse hoek tussen het board en het zeil.',0,1),(5,'Vaarhouding\r','Vanuit de basishouding beide voeten achter de mast zetten, hierna de zeilhand plaatsen en aantrekken. Rechte rug, armen licht gebogen en mast rechtop.',0,1),(6,'Zeilsturing','Tijdens het varen door middel van zeilsturing rechtdoor en in bochten kunnen varen. ',0,1),(7,'Zeilstanden','Op alle koersen het zeil zo voeren dat er voortstuwing is. Voor de wind varen is geen bindende\nverplichting.\r',0,1),(8,'Overstag gaan','Van minimaal halve wind door de wind naar maximaal halve wind over de andere boeg draaien, waarbij via de nose om de mast heen wordt gelopen. Tijdens de gehele draai mag de mast of het ophaalkoord worden gebruikt om het board te draaien. Tijdens de overstag mag hoogte worden verloren, maar niet meer dan dat in één rak kan worden gecompenseerd.',0,1),(9,'Gijpen\r','Van minimaal halve wind naar maximaal halve wind met de tail door de wind draaien. Tijdens de gehele draai mag de mast of het ophaalkoord worden gebruikt om het board te draaien.',0,1),(10,'Opkruisen\r','Met behulp van enkele aanwijzingen naar een onbezeild bovenwindse lijn kunnen varen',0,1),(11,'Wegsurfen en aankomen\r','Onder begeleiding binnen een straal van twee boardlengtes op een veilige manier bij een van te voren bepaald punt kunnen aankomen. Vervolgens dient men naar de kan te peddelen of zwemmen. Ook van dit punt weer op een veilige manier kunnen wegvaren. Dit zowel bij punten op hogerwal als op lagerwal. ',0,1),(12,'Theorie','Knopen en steken, Surftermen, Onderdelen surfplank, Veiligheid en omgeving\r, Reglementen en Materiaal en onderhoud',1,1),(13,'Op- en aftuigen','Board klaarleggen, vin en eventueel zwaard monteren. Baseplate monteren. De twee mast delen schoon in elkaar schuiven. Mast in de mastslurf doen. Mastvoet erin, de neerhaler rijgen en aantrekken in de klem vastzetten en lijn borgen. Giek op de juiste hoogte op de mast bevestigen. Uithaler rijgen en aantrekken, in de klem vastzetten en overige lijn borgen. Ophaalkoord bevestigen en tuigage aan het board koppelen. Trapeze lijnen bevestigen en afstellen. Aftuigen in omgekeerde volgorde van optuigen, schoonmaken en opruimen.',0,2),(14,'Veiligheid en omgeving','Op- en aftuigen: rekening houden met de wind, omgeving en omstanders.\nNoodstop: op een veilige manier tuigage in het water leggen en zo nodig zelf in het water\nspringen. Bij voorkeur de giek en/of de voetbanden vasthouden.\nStopgijp: Stoppen door het zeil tegen de wind te duwen.\nNoodsein geven: met beide armen gestrekt kruisende beweging boven het hoofd maken.\nGesleept worden door een volgboot of andere surfer. Efficiënt peddelen met board (en tuig).\nVerantwoord varen ten opzichte van medewatersporters. Blijf ten alle tijden bij je board.\nRespect voor de plaatselijke natuur en hun inwoners.\r',0,2),(15,'Dragen van board en tuig','Het board en tuig zelfstandig op een veilige manier in twee delen naar het water dragen.',0,2),(16,'Vaarhouding','Vanuit de basishouding beide voeten achter de mast zetten, hierna de zeilhand plaatsen en aantrekken. Rechte rug, armen licht gebogen en mast rechtop. Houding aanpassen tijdens het trapeze varen, zodat de trapeze gebruikt wordt.',0,2),(17,'Zeilsturing','Tijdens het varen door middel van zeilsturing rechtdoor en in bochten kunnen varen. ',0,2),(18,'Zeilstanden','Het zeil zo voeren dat het grootste gedeelte van de tijd optimale voortstuwing is. Op een voor de windse koers dient het zeil boven het board gehouden te worden mits de wind dit toelaat, snelheid regelen op alle koersen.',0,2),(19,'Overstag gaan','Van aan de wind naar aan de wind over de andere boeg draaien door gebruik te maken van zeilsturing en waarbij via de nose om de mast heen wordt gelopen. Tijdens de overstag pakt men over van giek naar giek. Het board mag stilvallen, maar er mag niet meer 1 boardlengte aan hoogte worden verloren.. Het beginpunt van het nieuwe rak ligt dus maximaal 1  boardlengte onder het eindpunt van het oude rak. ',0,2),(20,'Gijpen\r','In een vloeiende beweging door gebruik van zeilsturing van ruime wind naar ruime wind gaan, waarbij het zeil via de nose gaat met desnoods kort gebruik van de mast. Tijdens de gijp pakt men over van giek naar giek. ',0,2),(21,'Opkruisen','In breed vaarwater door middel van enkele aan de windse slagen naar een onbezeild bovenwindse lijn kunnen varen',0,2),(22,'Wegsurfen en aankomen','Binnen een straal van een boardlengte bij een van te voren bepaald punt kunnen aankomen\nop een veilige manier. Ook van dit punt weer op een veilige manier kunnen wegvaren. Dit van\nen naar elk mogelijk punt.\r',0,2),(23,'Trapeze varen\r','Altijd trapeze om tijdens het varen. Goed kunnen afstellen van je trapezelijnen. In en uithaken\nop halve windse en aan de windse koersen. Men dient rechtdoor te kunnen varen met druk op\nde trapezelijnen. Na het vallen onder water kunnen uithaken. Bij voorover vallen de val kunnen\nopvangen.\r',0,2),(24,'Beachstart','Het board en tuig in de gewenste richting kunnen leggen met handen aan de giek. In kniediep water op het board kunnen stappen en board in controle houden en met de achterste voet als eerste opstappen. Wegvaren zonder gebruik te maken van het ophaalkoord.',0,2),(25,'Toepassen reglementen','De in de theorie staande reglementen in de praktijk kunnen toepassen',0,2),(26,'Kennismaken wedstrijdsurfen','Een wedstrijd kunnen varen voorzien van een start en finish.',0,2),(27,'Freestyle','Twee verschillende tricks uitvoeren te denken aan: sail 360, backwind varen, clew first varen, board 360, fin first varen, railride, wheely, duckgijp, helitack of een andere trick.',0,2),(28,'Zwaardgebruik 2','Indien het zwaard aanwezig is in het board dient men op alle koersen de juiste zwaardstand toe te passen.',0,2),(29,'Theorie','Knopen en steken, Surftermen, Onderdelen, Veiligheid en omgeving, Reglementen, Materiaal en onderhoud\r en Krachten en gevolgen',1,2),(30,'Op- en aftuigen\r','Board klaarleggen, zeilkeuze maken, juiste vin en eventueel zwaard monteren. Baseplate monteren. De twee mastdelen schoon in elkaar schuiven. Mast in de mastslurf doen. Mastvoet  erin, de neerhaler rijgen en aantrekken in de klem vastzetten en lijn borgen. Giek op de juiste hoogte op de mast bevestigen. Uithaler rijgen en aantrekken, in de klem vastzetten en overige lijn borgen. Ophaalkoord bevestigen en tuigage aan het board koppelen. Trapeze lijnen bevestigen en afstellen. Aftuigen in omgekeerde volgorde van optuigen, schoonmaken en opruimen.',0,3),(31,'Trimmen','Afhankelijk van de condities de neerhaler en uithaler strakker of losser zetten. Meer wind hogere spanning, bij minder wind een lagere spanning. Zeillatten op de optimale spanning zetten. Monteren en afstellen van de voetbanden, afhankelijk van het niveau van de surfer zullen deze meer op het board staan of meer naar buiten.',0,3),(32,'Veiligheid en omgeving','Op- en aftuigen: rekening houden met de wind, omgeving en omstanders. Noodstop: op een veilige manier tuigage in het water leggen en zo nodig zelf in het water springen. Bij voorkeur de giek en/of de voetbanden vasthouden. Stopgijp: Stoppen door het zeil tegen de wind te duwen. Noodsein geven: met beide armen gestrekt kruisende beweging boven het hoofd maken. Gesleept worden door een volgboot of andere surfer. Efficiënt peddelen met board (en tuig). Verantwoord varen ten opzichte van medewatersporters. Blijf ten alle tijden bij je board. Respect voor de plaatselijke natuur en hun inwoners.',0,3),(33,'Dragen van board en tuig','De plank en het tuig in zijn geheel naar het water kunnen dragen.',0,3),(34,'Vaarhouding\r','Vanuit de basishouding beide voeten achter de mast zetten, hierna de zeilhand plaatsen en aantrekken. Rechte rug, armen licht gebogen en mast rechtop. Houding aanpassen tijdens het trapeze varen, zodat de trapeze ook doelmatig gebruikt wordt. Bewuste keuze voor ondergrip/bovengrip. Voetbanden worden gebruikt, de druk van de armen en de benen worden overgezet op het board en de mastvoet zodat koers behouden blijft.',0,3),(35,'Zeil- en voetsturing','Tijdens het varen door middel van zeilsturing en voetsturing rechtdoor en in bochten kunnen varen. Tijdens planeren voetsturing kunnen geven op zowel loef-, als lijzijde met als doel het sturen van het board. Indien zwaard aanwezig, voetsturing aanpassen op zwaard.',0,3),(36,'Zeilstanden','Het zeil zo voeren dat het grootste gedeelte van de tijd optimale voortstuwing is. Op een voor de windse koers dient het zeil boven het board gehouden te worden mits de wind dit toelaat, snelheid regelen op alle koersen. Wanneer men het board in plané trekt, dient het zeil dicht getrokken te worden.',0,3),(37,'Overstag gaan','Met een vloeiende beweging van hoog aan de wind naar hoog aan de wind over de andere boeg draaien door middel van zeilsturing en voetsturing, waarbij via de nose om de mast heen wordt gelopen. Overpakken dient men van giek naar giek te doen. Ook mag er geen hoogte verloren worden.',0,3),(38,'Gijpen','In een vloeiende beweging door gebruik van zeilsturing en voetsturing van ruime wind naar ruime wind draaien. Overpakken dient men van giek naar giek te doen. De manoeuvre dient planerend ingezet te kunnen worden. Zeil mag pas van boeg wisselen, zodra binnen de wind wordt gevaren. Korte gijp met hoge draaisnelheid, mag van halve wind naar halve wind. ',0,3),(39,'Opkruisen\r','In nauw en breed vaarwater door middel van enkele hoog aan de windse slagen naar een onbezeild bovenwindse punt kunnen varen. In plané dient het board zo vlak mogelijk gevaren te worden. Wanneer er niet geplaneerd wordt dient op de hoog aan de windse koers het board vlak in de lengte richting en met railbelasting gevaren te worden',0,3),(40,'Wegsurfen en aankomen','Binnen handbereik op een van te voren bepaald punt kunnen aankomen op een veilige manier. Ook van dit punt weer op een veilige manier kunnen wegvaren. Dit van en naar elk mogelijk punt. ',0,3),(41,'Trapeze varen','Altijd trapeze om tijdens het varen. Goed kunnen afstellen van je trapezelijnen. In- en uithaken. Men dient met druk op de lijnen te varen. Comfortabel in de trapeze kunnen hangen. Eventuele vlagen of luwtes dienen opgevangen te worden. Na het vallen onder water kunnen uithaken. Bij voorover vallen de val kunnen opvangen.',0,3),(42,'Waterstart','Het board en tuig in de gewenste richting kunnen leggen met handen aan de giek. Vanuit elke\ngewenste of genoodzaakte positie het board en zeil zo kunnen draaien, dat waterstart mogelijk\nis. Minimaal vanaf heupdiepte dient men op te kunnen stappen.\r',0,3),(43,'Planeren','Vanaf 14 knts planeren verplicht. Tijdens het planeren gebruik maken van trapeze en voetbanden. De zeilstand dient aangepast te worden aan de veranderende schijnbare wind. Het board dient vlak gevaren te worden en er moet aan de trapezelijnen worden gehangen.',0,3),(44,'Voetbanden','Afstellen van je voetbanden (voeten en positie board). Met twee voeten kunnen in- en\nuitstappen in de voetbanden. Snelheid en koers behouden tijdens instappen.\r',0,3),(45,'Toepassen van reglementen','De in de theorie staande reglementen in de praktijk kunnen toepassen.\r',0,3),(46,'Wedstrijdvaren','Een wedstrijdbaan kunnen varen (innerloop/outerloop of downwind slalom). Een tactiek bedenken en uitvoeren bij het starten. Een boei technisch kunnen ronden. Ook dient men een freestyle heat te kunnen varen. Hierin wordt gedurende een beperkte periode punten gescoord voor tricks. De drie beste tricks over beide boegen tellen bij elkaar op. De rider met de meeste punten wint.',0,3),(47,'Freestyle','Vier verschillende tricks uitvoeren te denken aan: sail 360, backwind varen, clew first varen, board 360, fin first varen, railride, wheely, duckgijp, helitack of een andere trick.',0,3),(48,'Zwaardgebruik','Indien het zwaard aanwezig is in het board dient men ten alle tijden op alle koersen de juiste zwaardstand toe te passen. In plané dient men geen zwaard te gebruiken.',0,3),(49,'Theorie','Lijnen, knopen en steken, Surftermen, Onderdelen, Veiligheid en omgeving, Reglementen, Materiaal en onderhoud, Krachten en gevolgen\r, Meteorologie en Wedstrijdvaren',1,3);
/*!40000 ALTER TABLE `diploma_eis` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-03  8:57:37
