-- MySQL dump 10.13  Distrib 5.7.31, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: bibliotech
-- ------------------------------------------------------
-- Server version	5.7.31

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
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `author` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'jjacdad'),(2,'dsadsa'),(3,'Machado de Assis'),(4,'dsadsa'),(5,'AAAAAAAA');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `pages` int(11) NOT NULL,
  `is_available` tinyint(4) NOT NULL DEFAULT '1',
  `genre_id` int(11) NOT NULL,
  `author_id` int(11) NOT NULL,
  `deleted_reason` varchar(150) DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `book_genre_id_fk` (`genre_id`),
  KEY `book_author_id_fk` (`author_id`),
  CONSTRAINT `book_author_id_fk` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`),
  CONSTRAINT `book_genre_id_fk` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'aaa',233,1,1,1,'mlllm',1),(2,'kkkkk',3232,1,1,1,'kdsapkdksopak',1),(3,'dsadsa',231312,0,1,1,NULL,0),(4,'312312',43543,0,1,1,NULL,0),(5,'Testeaaa',321,1,1,1,NULL,0),(6,'Kkkkkkkk',3213,1,1,1,NULL,0),(7,'MEUUU',123,1,1,1,NULL,0),(8,'Caramba',32131,1,1,1,NULL,0),(9,'fadsadsads',213,1,1,1,NULL,0),(10,'nendnd',22,1,1,1,NULL,0),(11,'kpdjiojpdsoaijojdjo',213123,1,1,1,NULL,0);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_has_loan`
--

DROP TABLE IF EXISTS `book_has_loan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book_has_loan` (
  `book_id` int(11) NOT NULL,
  `loan_id` int(11) NOT NULL,
  KEY `book_has_loan_book_id_fk` (`book_id`),
  KEY `book_has_loan_loan_id_fk` (`loan_id`),
  CONSTRAINT `book_has_loan_book_id_fk` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `book_has_loan_loan_id_fk` FOREIGN KEY (`loan_id`) REFERENCES `loan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_has_loan`
--

LOCK TABLES `book_has_loan` WRITE;
/*!40000 ALTER TABLE `book_has_loan` DISABLE KEYS */;
INSERT INTO `book_has_loan` VALUES (3,47),(4,47),(3,48),(4,49);
/*!40000 ALTER TABLE `book_has_loan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gender`
--

DROP TABLE IF EXISTS `gender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gender` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gender`
--

LOCK TABLES `gender` WRITE;
/*!40000 ALTER TABLE `gender` DISABLE KEYS */;
INSERT INTO `gender` VALUES (1,'Indefinido'),(2,'Masculino'),(3,'Feminino');
/*!40000 ALTER TABLE `gender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genre` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (1,'dsadsad'),(2,'kkkk'),(3,'Terror'),(4,'Romance'),(5,'Legal'),(6,'Amigão'),(7,'AAAAAAAAA'),(8,'BBBBB');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loan`
--

DROP TABLE IF EXISTS `loan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loan_date` date NOT NULL,
  `delivery_date` date NOT NULL,
  `user_id` int(11) NOT NULL,
  `active` tinyint(1) DEFAULT '1',
  `deleted_reason` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `loan_user_id_fk` (`user_id`),
  CONSTRAINT `loan_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loan`
--

LOCK TABLES `loan` WRITE;
/*!40000 ALTER TABLE `loan` DISABLE KEYS */;
INSERT INTO `loan` VALUES (47,'2021-02-22','2020-03-09',12,0,NULL),(48,'2022-02-23','2020-03-10',15,1,NULL),(49,'2021-02-23','2020-03-10',12,1,NULL);
/*!40000 ALTER TABLE `loan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `school`
--

DROP TABLE IF EXISTS `school`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `school` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `school`
--

LOCK TABLES `school` WRITE;
/*!40000 ALTER TABLE `school` DISABLE KEYS */;
INSERT INTO `school` VALUES (5,'Senai'),(6,'aaaa'),(7,'bb'),(8,'dsadsa'),(9,'iodjsa'),(10,'Teste2');
/*!40000 ALTER TABLE `school` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tokens`
--

DROP TABLE IF EXISTS `tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tokens` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` text NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `tokens_user__fk` (`user_id`),
  CONSTRAINT `tokens_user__fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=181 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tokens`
--

LOCK TABLES `tokens` WRITE;
/*!40000 ALTER TABLE `tokens` DISABLE KEYS */;
INSERT INTO `tokens` VALUES (180,'43d2724c5338cdcd26108fa57aff6a24',12);
/*!40000 ALTER TABLE `tokens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `user_type_id` int(11) NOT NULL,
  `birth_date` date DEFAULT NULL,
  `cpf` varchar(15) DEFAULT NULL,
  `gender_id` int(11) DEFAULT NULL,
  `school_id` int(11) DEFAULT NULL,
  `active` tinyint(1) DEFAULT '1',
  `books` int(11) DEFAULT '0',
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `deleted_reason` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_user_type_id_fk` (`user_type_id`),
  KEY `user_gender_id_fk` (`gender_id`),
  KEY `user_school_id_fk` (`school_id`),
  CONSTRAINT `user_gender_id_fk` FOREIGN KEY (`gender_id`) REFERENCES `gender` (`id`),
  CONSTRAINT `user_school_id_fk` FOREIGN KEY (`school_id`) REFERENCES `school` (`id`),
  CONSTRAINT `user_user_type_id_fk` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (11,'nicolas+re@informant.com.br','202cb962ac59075b964b07152d234b70',2,'2020-10-05','111.111.111-11',1,5,1,0,'AAAAA','AAAAAAAAAAAA',''),(12,'nicolas@informant.com.br','202cb962ac59075b964b07152d234b70',3,'1993-02-11','111.111.111-11',2,5,1,1,'Nicolas','Engler',NULL),(14,'admin@hauseful.com','202cb962ac59075b964b07152d234b70',1,'2020-02-12','231.321.321-32',2,5,1,0,'dsadsadsa','dsadsadsa',''),(15,'nicolas+1@informant.com.br','123',3,'2021-02-22','123.131.231-23',2,5,1,1,'Teste','Teste',NULL),(16,'nicolas+2@informant.com.br','202cb962ac59075b964b07152d234b70',3,'2021-03-02','123.131.231-33',3,10,1,0,'Aaaaaaaaaaa','Bbbbbbbbbbbb',NULL),(17,'aaa@aaa.aaa','202cb962ac59075b964b07152d234b70',3,'2021-03-03','321.321.312-32',1,7,1,0,'Aajsodjoisda','dsadasdsa',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_type`
--

DROP TABLE IF EXISTS `user_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_type`
--

LOCK TABLES `user_type` WRITE;
/*!40000 ALTER TABLE `user_type` DISABLE KEYS */;
INSERT INTO `user_type` VALUES (1,'Administrador'),(2,'Bibliotecário'),(3,'Estudante');
/*!40000 ALTER TABLE `user_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-19 19:31:47
