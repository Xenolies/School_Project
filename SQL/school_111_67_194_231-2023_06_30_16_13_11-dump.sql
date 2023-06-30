-- MySQL dump 10.13  Distrib 5.7.42, for Win64 (x86_64)
--
-- Host: 111.67.194.231    Database: school
-- ------------------------------------------------------
-- Server version	5.7.25

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
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `article_id` varchar(255) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `create_time` bigint(20) DEFAULT NULL,
  `comment_num` int(11) DEFAULT NULL,
  `views_num` int(11) DEFAULT NULL,
  `share_num` int(11) DEFAULT NULL,
  `like_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`article_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES ('1010110','hao123','564',1,1,1,1,0),('1010111','张三','789',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_family`
--

DROP TABLE IF EXISTS `article_family`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article_family` (
  `article_id` varchar(255) NOT NULL,
  `family_id` varchar(255) NOT NULL,
  PRIMARY KEY (`article_id`,`family_id`) USING BTREE,
  KEY `family_id` (`family_id`) USING BTREE,
  CONSTRAINT `article_family_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`),
  CONSTRAINT `article_family_ibfk_2` FOREIGN KEY (`family_id`) REFERENCES `family` (`family_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_family`
--

LOCK TABLES `article_family` WRITE;
/*!40000 ALTER TABLE `article_family` DISABLE KEYS */;
/*!40000 ALTER TABLE `article_family` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_image`
--

DROP TABLE IF EXISTS `article_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article_image` (
  `article_image_id` varchar(255) NOT NULL,
  `image_link` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`article_image_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_image`
--

LOCK TABLES `article_image` WRITE;
/*!40000 ALTER TABLE `article_image` DISABLE KEYS */;
INSERT INTO `article_image` VALUES ('01','http://baidu.com');
/*!40000 ALTER TABLE `article_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_user`
--

DROP TABLE IF EXISTS `article_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article_user` (
  `article_id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `like_status` tinyint(1) DEFAULT NULL,
  `fans_state` tinyint(1) DEFAULT NULL,
  `private_state` varchar(255) DEFAULT NULL,
  `mutual_concern` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`article_id`,`user_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  CONSTRAINT `article_user_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`),
  CONSTRAINT `article_user_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_user`
--

LOCK TABLES `article_user` WRITE;
/*!40000 ALTER TABLE `article_user` DISABLE KEYS */;
INSERT INTO `article_user` VALUES ('1010110','1010110',1,1,'1',1);
/*!40000 ALTER TABLE `article_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_image_article`
--

DROP TABLE IF EXISTS `article_image_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article_image_article` (
  `article_id` varchar(255) NOT NULL,
  `article_image_id` varchar(255) NOT NULL,
  PRIMARY KEY (`article_id`,`article_image_id`) USING BTREE,
  KEY `article_image_id` (`article_image_id`) USING BTREE,
  CONSTRAINT `article_image_article_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`),
  CONSTRAINT `article_image_article_ibfk_2` FOREIGN KEY (`article_image_id`) REFERENCES `article_image` (`article_image_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_image_article`
--

LOCK TABLES `article_image_article` WRITE;
/*!40000 ALTER TABLE `article_image_article` DISABLE KEYS */;
INSERT INTO `article_image_article` VALUES ('1010110','01');
/*!40000 ALTER TABLE `article_image_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `classId` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`classId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (2,'软件工程系'),(3,'网络工程系'),(4,'计算机应用系'),(5,'数字传媒系'),(6,'互联网商务系'),(7,'智能工程系');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `family`
--

DROP TABLE IF EXISTS `family`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `family` (
  `family_id` varchar(255) NOT NULL,
  `family_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`family_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `family`
--

LOCK TABLES `family` WRITE;
/*!40000 ALTER TABLE `family` DISABLE KEYS */;
/*!40000 ALTER TABLE `family` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schoolInfo`
--

DROP TABLE IF EXISTS `schoolInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schoolInfo` (
  `classid` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`classid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schoolInfo`
--

LOCK TABLES `schoolInfo` WRITE;
/*!40000 ALTER TABLE `schoolInfo` DISABLE KEYS */;
INSERT INTO `schoolInfo` VALUES (1,'河北软件职业技术学院'),(2,'其他学校');
/*!40000 ALTER TABLE `schoolInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_info`
--

DROP TABLE IF EXISTS `student_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_info` (
  `school` varchar(50) DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL,
  `str_attest_id` varchar(255) NOT NULL,
  `str_name` varchar(50) DEFAULT NULL,
  `str_number` int(11) DEFAULT NULL,
  `avatar` varchar(100) DEFAULT NULL,
  `department` varchar(50) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`str_attest_id`) USING BTREE,
  UNIQUE KEY `str_attest_id` (`str_attest_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_info`
--

LOCK TABLES `student_info` WRITE;
/*!40000 ALTER TABLE `student_info` DISABLE KEYS */;
INSERT INTO `student_info` VALUES ('河北软件职业技术学院',4,'123456789','张新',218405060,NULL,'软件工程系',0);
/*!40000 ALTER TABLE `student_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_infodto`
--

DROP TABLE IF EXISTS `student_infodto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_infodto` (
  `school` varchar(255) DEFAULT NULL,
  `str_name` varchar(255) DEFAULT NULL,
  `str_number` bigint(20) NOT NULL,
  `department` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`str_number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_infodto`
--

LOCK TABLES `student_infodto` WRITE;
/*!40000 ALTER TABLE `student_infodto` DISABLE KEYS */;
INSERT INTO `student_infodto` VALUES ('河北软件职业技术学院','张三',218405060,'软件工程系');
/*!40000 ALTER TABLE `student_infodto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_follow`
--

DROP TABLE IF EXISTS `user_follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_follow` (
  `fans_id` char(19) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `answer_user_id` char(19) NOT NULL COMMENT '被关注者id',
  `user_id` char(19) NOT NULL COMMENT '关注者id',
  `is_deleted` char(1) NOT NULL DEFAULT '0' COMMENT '关注状态 0关注 1取消',
  `create_time` char(13) NOT NULL,
  `update_time` char(13) NOT NULL,
  PRIMARY KEY (`fans_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户关注表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_follow`
--

LOCK TABLES `user_follow` WRITE;
/*!40000 ALTER TABLE `user_follow` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_notification`
--

DROP TABLE IF EXISTS `user_notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_notification` (
  `id` int(50) NOT NULL COMMENT '用户查询id',
  `msg` varchar(500) NOT NULL COMMENT '用户消息Json',
  `image` varchar(500) DEFAULT NULL COMMENT '图片数据',
  `signFlag` int(1) DEFAULT NULL COMMENT '0 未读 1 已读',
  `acceptUserId` varchar(50) DEFAULT NULL COMMENT '接收者用户id',
  `sendUserId` varchar(50) DEFAULT NULL COMMENT '发送者用户id',
  `sendUserName` varchar(50) DEFAULT NULL COMMENT '发送者用户名',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `type` int(1) DEFAULT NULL COMMENT '通知类型  0系统 1点赞文章 2评论文章 3点赞文章评论 4点赞商品 5评论商品 6点赞商品评论 7评论回复评论',
  `content` varchar(500) DEFAULT NULL COMMENT '消息内容',
  `tips` varchar(500) DEFAULT NULL COMMENT '消息提示内容 查询私信时此处为未读私信数',
  `createTime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `commonId` varchar(50) DEFAULT NULL COMMENT '通用id',
  `commonIdType` int(1) DEFAULT NULL COMMENT '通用id类型 0文章 1商品',
  `articleLikeId` varchar(50) DEFAULT NULL COMMENT '点赞文章id',
  `unreadNum` int(50) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户通知表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_notification`
--

LOCK TABLES `user_notification` WRITE;
/*!40000 ALTER TABLE `user_notification` DISABLE KEYS */;
INSERT INTO `user_notification` VALUES (1,'暂无消息',NULL,NULL,'',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,0),(2,'暂无消息',NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,0),(3,'暂无消息',NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,0),(4,'暂无消息',NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `user_notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_student_info`
--

DROP TABLE IF EXISTS `user_student_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_student_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL,
  `str_attest_id` varchar(50) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `str_attest_id` (`str_attest_id`) USING BTREE,
  CONSTRAINT `fk_str_attest_id` FOREIGN KEY (`str_attest_id`) REFERENCES `student_info` (`str_attest_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_student_info`
--

LOCK TABLES `user_student_info` WRITE;
/*!40000 ALTER TABLE `user_student_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_student_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `userId` varchar(255) NOT NULL,
  `id` int(11) NOT NULL,
  `userName` varchar(255) NOT NULL,
  `classId` varchar(255) NOT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `fansNum` int(11) NOT NULL,
  `sex` int(11) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `createTime` bigint(20) NOT NULL,
  `updateTime` bigint(20) NOT NULL,
  `fansState` varchar(255) DEFAULT NULL,
  `userIdentity` varchar(255) DEFAULT NULL,
  `mutualConcern` tinyint(1) NOT NULL,
  `badge` int(11) NOT NULL,
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('1010110',1,'hao','2','1323232322','hao123',9999,1,'123',3424321,234234,'1','1',1,1),('1010111',2,'yuan','2','2131321323','yuan123',999,1,'123',1616565,156565,'1','1',1,1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-30 16:13:46
