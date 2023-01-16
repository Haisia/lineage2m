-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: l2m
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `item_price_stats`
--

DROP TABLE IF EXISTS `item_price_stats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_price_stats` (
  `pk` int NOT NULL AUTO_INCREMENT,
  `create_date` date NOT NULL COMMENT 'row 추가 일자',
  `last_price` int NOT NULL COMMENT '최근거래가격',
  `enchant_level` int NOT NULL DEFAULT '0' COMMENT '강화레벨',
  `world_id` int NOT NULL,
  `world_name` varchar(10) NOT NULL,
  `item_info_pk` int NOT NULL,
  PRIMARY KEY (`pk`),
  UNIQUE KEY `pk` (`pk`),
  KEY `item_price_stats_item_info_pk_fk` (`item_info_pk`),
  CONSTRAINT `item_price_stats_item_info_pk_fk` FOREIGN KEY (`item_info_pk`) REFERENCES `item_info` (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='일별 아이템 가격정보 저장 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_price_stats`
--

LOCK TABLES `item_price_stats` WRITE;
/*!40000 ALTER TABLE `item_price_stats` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_price_stats` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-16 16:35:54
