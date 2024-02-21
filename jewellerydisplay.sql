-- MySQL dump 10.13  Distrib 5.7.29, for Win64 (x86_64)
--
-- Host: localhost    Database: jewellerydisplay
-- ------------------------------------------------------
-- Server version	5.7.29-log

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
-- Table structure for table `a`
--

DROP TABLE IF EXISTS `a`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `a` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(10) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `a`
--

LOCK TABLES `a` WRITE;
/*!40000 ALTER TABLE `a` DISABLE KEYS */;
/*!40000 ALTER TABLE `a` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acl_role`
--

DROP TABLE IF EXISTS `acl_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acl_role` (
  `id` char(19) NOT NULL DEFAULT '' COMMENT '角色id',
  `role_name` varchar(20) NOT NULL DEFAULT '' COMMENT '角色名称',
  `role_code` varchar(20) DEFAULT NULL COMMENT '角色编码',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acl_role`
--

LOCK TABLES `acl_role` WRITE;
/*!40000 ALTER TABLE `acl_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `acl_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acl_user`
--

DROP TABLE IF EXISTS `acl_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acl_user` (
  `id` char(19) NOT NULL COMMENT '会员id',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `salt` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `token` varchar(100) DEFAULT NULL COMMENT '用户签名',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`nick_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acl_user`
--

LOCK TABLES `acl_user` WRITE;
/*!40000 ALTER TABLE `acl_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `acl_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crm_banner`
--

DROP TABLE IF EXISTS `crm_banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crm_banner` (
  `id` char(19) NOT NULL DEFAULT '' COMMENT 'ID',
  `title` varchar(20) DEFAULT '' COMMENT '标题',
  `image_url` varchar(500) NOT NULL DEFAULT '' COMMENT '图片地址',
  `link_url` varchar(500) DEFAULT '' COMMENT '链接地址',
  `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='首页banner表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crm_banner`
--

LOCK TABLES `crm_banner` WRITE;
/*!40000 ALTER TABLE `crm_banner` DISABLE KEYS */;
INSERT INTO `crm_banner` VALUES ('1','星空1','\r\nhttps://bysj-1024.oss-cn-beijing.aliyuncs.com/2023/03/17/bg1.jpg\r\n','/jewellery',1,0,'2023-03-17 18:18:30','2023-03-17 18:18:30'),('2','星空2','\r\nhttps://bysj-1024.oss-cn-beijing.aliyuncs.com/2023/03/17/bg2.jpg\r\n','/jewellery',2,0,'2023-03-17 18:18:30','2023-03-17 18:18:30');
/*!40000 ALTER TABLE `crm_banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jewellery_chapter`
--

DROP TABLE IF EXISTS `jewellery_chapter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jewellery_chapter` (
  `id` char(19) NOT NULL COMMENT '种类ID',
  `jewellery_id` char(19) NOT NULL COMMENT '珠宝ID',
  `title` varchar(50) NOT NULL COMMENT '视频标题',
  `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '显示排序',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_jewellery_id` (`jewellery_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='珠宝';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jewellery_chapter`
--

LOCK TABLES `jewellery_chapter` WRITE;
/*!40000 ALTER TABLE `jewellery_chapter` DISABLE KEYS */;
INSERT INTO `jewellery_chapter` VALUES ('10','1633859444964081665','珠宝大纲testtest',0,'2023-03-12 15:47:30','2023-03-12 15:47:30'),('11','1633859444964081665','珠宝大纲test',0,'2023-03-12 15:57:41','2023-03-12 15:57:41'),('1635112969597620226','1635112002026213378','珠宝视频介绍',0,'2023-03-13 10:58:06','2023-03-20 23:44:52'),('1635118478291636225','1635117029843275778','珠宝视频介绍',2,'2023-03-13 11:19:59','2023-03-20 23:43:47'),('1635154535460655105','1635152285094924289','珠宝视频介绍',1,'2023-03-13 13:43:16','2023-03-20 23:38:55'),('1635180847038464001','1635180779656970242','珠宝视频介绍',1,'2023-03-13 15:27:49','2023-03-20 23:38:16'),('1635266022560800770','1635265932395847681','珠宝视频介绍',1,'2023-03-13 21:06:16','2023-03-20 23:37:34'),('1637806343664435202','1637806276282941442','红宝石',1,'2023-03-20 21:20:36','2023-03-20 21:20:36'),('1637833067261329409','1637832994595012609','珠宝视频介绍',1,'2023-03-20 23:06:47','2023-03-20 23:06:47'),('1637833937638129666','1637833857963130882','珠宝视频介绍',1,'2023-03-20 23:10:15','2023-03-20 23:10:15'),('1637835103382011905','1637835048780562434','珠宝饰品介绍',1,'2023-03-20 23:14:53','2023-03-20 23:14:53'),('1637839604784562177','1637839553953792001','珠宝视频介绍',1,'2023-03-20 23:32:46','2023-03-20 23:32:46'),('1637840489019342849','1637840425131704321','珠宝视频介绍',1,'2023-03-20 23:36:17','2023-03-20 23:36:34'),('1637842998471426050','1634944545030414337','珠宝视频介绍',0,'2023-03-20 23:46:15','2023-03-20 23:46:15'),('1637843435085889537','1634906870172987393','珠宝视频介绍',1,'2023-03-20 23:47:59','2023-03-20 23:47:59');
/*!40000 ALTER TABLE `jewellery_chapter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jewellery_classofy`
--

DROP TABLE IF EXISTS `jewellery_classofy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jewellery_classofy` (
  `id` char(19) NOT NULL COMMENT '珠宝类别ID',
  `title` varchar(10) NOT NULL COMMENT '类别名称',
  `parent_id` char(19) NOT NULL DEFAULT '0' COMMENT '父ID',
  `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序字段',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='珠宝种类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jewellery_classofy`
--

LOCK TABLES `jewellery_classofy` WRITE;
/*!40000 ALTER TABLE `jewellery_classofy` DISABLE KEYS */;
INSERT INTO `jewellery_classofy` VALUES ('1631526093427089410','天然玉石','0',0,'2023-03-03 13:25:08','2023-03-03 13:25:08'),('1631526093829742593','矿石','1631526093427089410',0,'2023-03-03 13:25:08','2023-03-03 13:25:08'),('1631526094156898306','晶体','1631526093427089410',0,'2023-03-03 13:25:08','2023-03-03 13:25:08'),('1631526094421139458','石头','1631526093427089410',0,'2023-03-03 13:25:08','2023-03-03 13:25:08'),('1631526094555357186','天然宝石','0',0,'2023-03-03 13:25:08','2023-03-03 13:25:08'),('1631526094685380610','天然晶体宝石','1631526094555357186',0,'2023-03-03 13:25:08','2023-03-03 13:25:08'),('1631526094941233153','珍珠','1631526094555357186',0,'2023-03-03 13:25:08','2023-03-03 13:25:08'),('1631526095071256578','钻石','1631526094555357186',0,'2023-03-03 13:25:08','2023-03-03 13:25:08'),('1631526095272583170','天然彩石','0',0,'2023-03-03 13:25:08','2023-03-03 13:25:08'),('1631526095402606593','寿山石','1631526095272583170',0,'2023-03-03 13:25:08','2023-03-03 13:25:08'),('1631526095528435714','花岗岩','1631526095272583170',0,'2023-03-03 13:25:08','2023-03-03 13:25:08');
/*!40000 ALTER TABLE `jewellery_classofy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jewellery_collect`
--

DROP TABLE IF EXISTS `jewellery_collect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jewellery_collect` (
  `id` char(19) NOT NULL COMMENT '收藏ID',
  `designer_id` char(19) NOT NULL COMMENT '设计师ID',
  `is_deleted` tinyint(3) NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='珠宝收藏';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jewellery_collect`
--

LOCK TABLES `jewellery_collect` WRITE;
/*!40000 ALTER TABLE `jewellery_collect` DISABLE KEYS */;
/*!40000 ALTER TABLE `jewellery_collect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jewellery_comment`
--

DROP TABLE IF EXISTS `jewellery_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jewellery_comment` (
  `id` char(19) NOT NULL COMMENT '设计师ID',
  `jewellery_id` varchar(19) NOT NULL DEFAULT '' COMMENT '珠宝id',
  `designer_id` char(19) NOT NULL DEFAULT '' COMMENT '设计师id',
  `member_id` varchar(19) NOT NULL DEFAULT '' COMMENT '会员id',
  `nickname` varchar(50) DEFAULT NULL COMMENT '会员昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '会员头像',
  `content` varchar(500) DEFAULT NULL COMMENT '评论内容',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_course_id` (`jewellery_id`),
  KEY `idx_teacher_id` (`designer_id`),
  KEY `idx_member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jewellery_comment`
--

LOCK TABLES `jewellery_comment` WRITE;
/*!40000 ALTER TABLE `jewellery_comment` DISABLE KEYS */;
INSERT INTO `jewellery_comment` VALUES ('1638507136675766274','1635265932395847681','8','1638463595517956097','张思楠','http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132','珠宝真不错，真好看',0,'2023-03-22 19:45:18','2023-03-22 19:45:18'),('1647620868181880833','1635180779656970242','7','1637077976384290818','张思楠11','http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132','这个珠宝真好看',0,'2023-04-16 23:20:01','2023-04-16 23:20:01');
/*!40000 ALTER TABLE `jewellery_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jewellery_description`
--

DROP TABLE IF EXISTS `jewellery_description`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jewellery_description` (
  `id` char(19) NOT NULL COMMENT '珠宝ID',
  `description` text COMMENT '珠宝简介',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='珠宝简介';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jewellery_description`
--

LOCK TABLES `jewellery_description` WRITE;
/*!40000 ALTER TABLE `jewellery_description` DISABLE KEYS */;
INSERT INTO `jewellery_description` VALUES ('1633859444964081665','<p>asdadasda</p>','2023-03-09 23:57:02','2023-03-09 23:57:02'),('1634906870172987393','<p>翡翠（jadeite），也称翡翠玉jadeites&nbsp;&nbsp;、翠玉、缅甸玉，是玉的一种。翡翠的正确定义是以硬玉矿物为主的辉石类矿物组成的纤维状集合体&nbsp;。但是翡翠并不等于硬玉。翡翠是在地质作用下形成的达到玉级的石质多晶集合体，主要由硬玉或硬玉及钠质（钠铬辉石）和钠钙质辉石（绿辉石）组成，可含有角闪石、长石、铬铁矿、褐铁矿等。 在古代翡翠是一种生活在南方的鸟，毛色十分美丽，通常有蓝、绿、红、棕等颜色。一般这种鸟雄性的为红色，谓之&ldquo;翡&rdquo;，雌性的为绿色，谓之&ldquo;翠&rdquo;。寸开泰撰写的《腾越乡土志》记载：&ldquo;腾为萃数，玉工满千，制为器皿，发售滇垣各行省。上品良玉，多发往粤东、上海、闽、浙、京都。&rdquo;至清末民初，仅腾冲城内即有翡翠作坊百余家，玉雕工匠三千余人。 产翡翠的缅甸勐拱、密支那一带，距我国云南边境只有150公里。在明朝万历年间，此地曾属永昌府（今云南省保山市）管辖。由于历史的原因，被称为&ldquo;东方瑰宝&rdquo;的翡翠经云南腾冲、瑞丽等边城输入我国，</p>','2023-03-12 21:19:08','2023-03-20 23:47:51'),('1634944545030414337','<p>绿宝石多为浅绿色，成分中富含铯时，呈粉红色，称为玫瑰绿宝石。含铬时，呈鲜艳的翠绿色，称为祖母。含二价铁时，呈淡蓝色，称为海蓝宝石。含三价铁时，呈黄色，称为黄绿宝石。 绿宝石是绿色透明石中的代表，人们说到鲜艳色彩时，就会用&ldquo;绿宝石的绿&rdquo;这种词句。绿宝石比重较低，因此即使颗粒小，但还是比相同克拉数的钻石或红宝石看起来大，这是它的特征。 绿宝石 在绿宝石里面，常常会有很多肉眼即看得见的&ldquo;墨西&rdquo;这种内含物。因此，常会被误认为是瑕疵，实际上这种物质跟会损害宝石价值的瑕疵完全不同。它是结晶形成过程中所必然会产生的内含物，也可以算是天然宝石的一项证据。更是令绿宝石蒙上一层神秘面纱的要素。可是这也跟程度问题有关，若多到有损宝石美丽的程度时亦不佳。绿宝石切割法通常呈长方形，偶尔也有正方形。这是因为，绿宝石和蓝晶本身为六角柱形结晶的缘故，以上述的方式切割，最能发挥效益，显现出最美的一面。</p>','2023-03-12 23:48:50','2023-03-20 23:46:06'),('1635112002026213378','<p>绿宝石多为浅绿色，成分中富含铯时，呈粉红色，称为玫瑰绿宝石。含铬时，呈鲜艳的翠绿色，称为祖母。含二价铁时，呈淡蓝色，称为海蓝宝石。含三价铁时，呈黄色，称为黄绿宝石。 绿宝石是绿色透明石中的代表，人们说到鲜艳色彩时，就会用&ldquo;绿宝石的绿&rdquo;这种词句。绿宝石比重较低，因此即使颗粒小，但还是比相同克拉数的钻石或红宝石看起来大，这是它的特征。 绿宝石 在绿宝石里面，常常会有很多肉眼即看得见的&ldquo;墨西&rdquo;这种内含物。因此，常会被误认为是瑕疵，实际上这种物质跟会损害宝石价值的瑕疵完全不同。它是结晶形成过程中所必然会产生的内含物，也可以算是天然宝石的一项证据。更是令绿宝石蒙上一层神秘面纱的要素。可是这也跟程度问题有关，若多到有损宝石美丽的程度时亦不佳。绿宝石切割法通常呈长方形，偶尔也有正方形。这是因为，绿宝石和蓝晶本身为六角柱形结晶的缘故，以上述的方式切割，最能发挥效益，显现出最美的一面。</p>','2023-03-13 10:54:15','2023-03-20 23:44:44'),('1635117029843275778','<p>和田玉，&ldquo;中国四大名玉&rdquo;之一（其三为陕西蓝田玉、辽宁岫玉和河南独山玉）。传统狭义范畴特指新疆和田地区出产的玉石，以和田&ldquo;子料&rdquo;为代表闻名于世；广义的和田玉指软玉（真玉）。和田玉虽然因新疆和田而命名，但其本身不是地域概念，并非特指新疆和田地区出产的玉，而是一类产品的名称。中国把透闪石成份占98%以上的石头都命名为和田玉，都在国标范围内。&nbsp;&nbsp;&nbsp; 秦始皇统一中国的时候，和田玉因产于昆仑山被称为&ldquo;昆山之玉&rdquo;，以后又因位于&ldquo;于阗国&rdquo;境内而被称为&ldquo;于阗玉&rdquo;。直到清光绪九年（1883年）设立和田直隶州时，才被正式命名为&ldquo;和田玉&rdquo;。 和田玉中的名品，是珍藏于陕西历史博物馆的西汉国宝级文物&ldquo;皇后之玺&rdquo;，其质地为新疆和田羊脂玉，是迄今唯一的汉代皇后玉玺，为首批禁止出国（境）展览文物&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;。2008年北京奥运会会徽徽宝&ldquo;中国印&rdquo;，也是采用新疆和田玉作为材料。</p>','2023-03-13 11:14:14','2023-03-20 23:43:31'),('1635152285094924289','<p>绿宝石多为浅绿色，成分中富含铯时，呈粉红色，称为玫瑰绿宝石。含铬时，呈鲜艳的翠绿色，称为祖母。含二价铁时，呈淡蓝色，称为海蓝宝石。含三价铁时，呈黄色，称为黄绿宝石。 绿宝石是绿色透明石中的代表，人们说到鲜艳色彩时，就会用&ldquo;绿宝石的绿&rdquo;这种词句。绿宝石比重较低，因此即使颗粒小，但还是比相同克拉数的钻石或红宝石看起来大，这是它的特征。 绿宝石 在绿宝石里面，常常会有很多肉眼即看得见的&ldquo;墨西&rdquo;这种内含物。因此，常会被误认为是瑕疵，实际上这种物质跟会损害宝石价值的瑕疵完全不同。它是结晶形成过程中所必然会产生的内含物，也可以算是天然宝石的一项证据。更是令绿宝石蒙上一层神秘面纱的要素。可是这也跟程度问题有关，若多到有损宝石美丽的程度时亦不佳。绿宝石切割法通常呈长方形，偶尔也有正方形。这是因为，绿宝石和蓝晶本身为六角柱形结晶的缘故，以上述的方式切割，最能发挥效益，显现出最美的一面。</p>','2023-03-13 13:34:19','2023-03-20 23:40:49'),('1635180779656970242','<p>银比金活泼，虽然它在地壳中的丰度大约是黄金的15倍，但它很少以单质状态存在，因而它的发现要比金晚。在古代，人们就已经知道开采银矿，由于当时人们取得的银的量很小，使得它的价值比金还贵。公元前1780至1580年间，埃及王朝的法典规定，银的价值为金的2倍，甚至到了17世纪，日本金、银的价值还是相等的。银最早用来做装饰品和餐具，后来才作为货币。 白银，即银，因其色白，故称白银，与黄金相对。多用其作货币及装饰品。古代做通货时称白银。银，永远闪耀着月亮般的光辉，银的论文原意，也就是&ldquo;明亮&rdquo;的意思。我国也常用银字来形容白而有光泽的东西，如银河、银杏、银鱼、银耳、银幕等。</p>','2023-03-13 15:27:33','2023-03-20 23:42:27'),('1635265932395847681','<p>黄金（Gold）是化学元素金（Au）的单质形式，是一种软的，金黄色的，抗腐蚀的贵金属。金是较稀有、较珍贵和极被人看重的金属之一。国际上一般黄金都是以盎司为单位，中国古代是以&ldquo;两&rdquo;作为黄金单位，是一种非常重要的金属。不仅是用于储备和投资的特殊通货，同时又是首饰业、电子业、现代通讯、航天航空业等部门的重要材料。 黄金的化学符号为Au，金融上的英文代码是XAU或者是GOLD。Au的名称来自一个罗马神话中的黎明女神欧若拉（Aurora）的一个故事，意为闪耀的黎明。</p>','2023-03-13 21:05:55','2023-03-20 23:41:25'),('1637832994595012609','<p>彩钻是钻石的一种。通常钻石呈透明色彩，但彩钻是除透明以外的钻石。世间有颜色的钻石，又称为彩钻，主要成因是无色钻石内的微粒起变化而产生的颜色，不同的变化产生不同的颜色，因此颜色越罕有，价值亦愈高。 彩钻的颜色，较常见的有金黄色、棕色、绿色，其它如粉红、红色、蓝色就较为罕有，往往可遇而不可求。如蓝色的霍普钻石，堪称稀世珍宝，价值当然也不菲。 高品质的粉红钻也被视为稀世奇珍，澳大利亚是全球粉红钻唯一的固定来源。</p>','2023-03-20 23:06:30','2023-03-20 23:06:30'),('1637833857963130882','<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;红宝石（ruby）&nbsp;，它是刚玉的一种，主要成分是氧化铝（Al₂O₃）。红色来自铬（Cr），主要为Cr2O3，含量一般0.1~3%，最高者达4%。含Fe、Ti而呈蓝色称蓝宝石，非铬Cr致色的其他颜色的刚玉也统称为蓝宝石。 天然红宝石大多来自亚洲（缅甸、泰国、斯里兰卡、巴基斯坦、中国新疆、中国云南等），非洲（莫桑比克、坦桑尼亚），大洋洲（澳大利亚），以及美洲（美国的蒙大拿州和南卡罗来纳州）。现在的红宝石，主要产于莫桑比克。 天然红宝石非常稀少，因此很珍贵，但是合成红宝石并非太难，所以工业用红宝石都是合成的。1999年，中国山东省昌乐县发现一颗红、蓝刚玉连生体，重量67.5克拉，被称为&ldquo;鸳鸯宝石&rdquo;，称得上是世界罕见的奇迹。2012年在新疆和田地区墨玉县卡拉喀什河河床中发现数枚红宝石矿最大的有32.7克拉。</p>','2023-03-20 23:09:56','2023-03-20 23:09:56'),('1637835048780562434','<p>黄金（Gold）是化学元素金（Au）的单质形式，是一种软的，金黄色的，抗腐蚀的贵金属。金是较稀有、较珍贵和极被人看重的金属之一。国际上一般黄金都是以盎司为单位，中国古代是以&ldquo;两&rdquo;作为黄金单位，是一种非常重要的金属。不仅是用于储备和投资的特殊通货，同时又是首饰业、电子业、现代通讯、航天航空业等部门的重要材料。 黄金的化学符号为Au，金融上的英文代码是XAU或者是GOLD。Au的名称来自一个罗马神话中的黎明女神欧若拉（Aurora）的一个故事，意为闪耀的黎明。</p>','2023-03-20 23:14:40','2023-03-20 23:14:40'),('1637839553953792001','<p>钻石源于古希腊语Adamant，意思是坚硬不可侵犯的物质。钻石的化学成分是碳，这在宝石中是唯一由单一元素组成的，属等轴晶系。常含有0.05%-0.2%的杂质元素，其中最重要的是N和B，他们的存在关系到钻石的类型和性质。晶体形态多呈八面体、菱形十二面体、四面体及它们的聚形。纯净的钻石无色透明，由于微量元素的混入而呈现不同颜色。强金刚光泽。折光率2.417，色散中等，为0.044。均质体。热导率为0.35卡/厘米/秒/度。用热导仪测试，反应最为灵敏。硬度为10，是目前已知最硬的矿物，绝对硬度是石英的1000倍，刚玉的150倍，怕重击，重击后会顺其解理破碎。一组解理完全。密度3.52克/立方厘米。钻石具有发光性，日光照射后 ，夜晚能发出淡青色磷光。X射线照射，发出天蓝色荧光。钻石的化学性质很稳定，在常温下不容易溶于酸和碱，酸碱不会对其产生作用。钻石具有亲油疏水的特性，因此在钻石首饰的保养中，强调避免将钻石处于重油的环境下，影响钻石首饰的火彩&nbsp;&nbsp;。</p>','2023-03-20 23:32:34','2023-03-20 23:32:34'),('1637840425131704321','<p>绿宝石多为浅绿色，成分中富含铯时，呈粉红色，称为玫瑰绿宝石。含铬时，呈鲜艳的翠绿色，称为祖母。含二价铁时，呈淡蓝色，称为海蓝宝石。含三价铁时，呈黄色，称为黄绿宝石。 绿宝石是绿色透明石中的代表，人们说到鲜艳色彩时，就会用&ldquo;绿宝石的绿&rdquo;这种词句。绿宝石比重较低，因此即使颗粒小，但还是比相同克拉数的钻石或红宝石看起来大，这是它的特征。 绿宝石 在绿宝石里面，常常会有很多肉眼即看得见的&ldquo;墨西&rdquo;这种内含物。因此，常会被误认为是瑕疵，实际上这种物质跟会损害宝石价值的瑕疵完全不同。它是结晶形成过程中所必然会产生的内含物，也可以算是天然宝石的一项证据。更是令绿宝石蒙上一层神秘面纱的要素。可是这也跟程度问题有关，若多到有损宝石美丽的程度时亦不佳。绿宝石切割法通常呈长方形，偶尔也有正方形。这是因为，绿宝石和蓝晶本身为六角柱形结晶的缘故，以上述的方式切割，最能发挥效益，显现出最美的一面。</p>','2023-03-20 23:36:02','2023-03-20 23:36:02');
/*!40000 ALTER TABLE `jewellery_description` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jewellery_designer`
--

DROP TABLE IF EXISTS `jewellery_designer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jewellery_designer` (
  `id` char(19) NOT NULL COMMENT '设计师ID',
  `name` varchar(20) NOT NULL COMMENT '设计师姓名',
  `intro` varchar(500) NOT NULL DEFAULT '' COMMENT '设计师简介',
  `career` varchar(500) DEFAULT NULL COMMENT '设计师资历,一句话说明讲师',
  `level` int(10) unsigned NOT NULL COMMENT '头衔 1高级技师 2技师',
  `avatar` varchar(255) DEFAULT NULL COMMENT '设计师头像',
  `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设计师';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jewellery_designer`
--

LOCK TABLES `jewellery_designer` WRITE;
/*!40000 ALTER TABLE `jewellery_designer` DISABLE KEYS */;
INSERT INTO `jewellery_designer` VALUES ('1','11','111','高级技师',1,'https://bysj-1024.oss-cn-beijing.aliyuncs.com/2023/03/01/a9f18c0502ca4221b911875ec207a434file.png',0,1,'2022-12-28 15:24:58','2022-12-28 15:24:58'),('12','test1test2','asdasd','硕士',1,'https://bysj-1024.oss-cn-beijing.aliyuncs.com/2023/03/01/a9f18c0502ca4221b911875ec207a434file.png',1,1,'2022-12-28 15:24:58','2022-12-28 16:32:37'),('1608003252681449473','ljm','asdasd','asdasd',1,'https://bysj-1024.oss-cn-beijing.aliyuncs.com/2023/03/01/a9f18c0502ca4221b911875ec207a434file.png',1,1,'2022-12-28 15:33:45','2022-12-28 15:33:45'),('1608018358576357378','添加测试123','asdasdasdf','博士',2,'https://bysj-1024.oss-cn-beijing.aliyuncs.com/2023/03/01/a9f18c0502ca4221b911875ec207a434file.png',1,1,'2022-12-28 16:33:47','2023-03-01 16:07:01'),('1630167775701901314','zsn111','123456123456','test123，test123',1,'https://bysj-1024.oss-cn-beijing.aliyuncs.com/2023/03/01/a9f18c0502ca4221b911875ec207a434file.png',1,1,'2023-02-27 19:27:39','2023-02-27 19:27:39'),('1630862060809166849','test avatot','asdasdasd','asdasda',1,'https://bysj-1024.oss-cn-beijing.aliyuncs.com/2023/03/01/a9f18c0502ca4221b911875ec207a434file.png',1,1,'2023-03-01 17:26:30','2023-03-01 17:26:30'),('2','zsn','一个天真活泼可爱的女生','高级技师',1,'https://bysj-1024.oss-cn-beijing.aliyuncs.com/2023/03/01/a9f18c0502ca4221b911875ec207a434file.png',0,1,'2023-03-01 17:26:30','2023-03-01 17:26:30'),('3','李刚','高级设计师简介','高级技师',2,'https://bysj-1024.oss-cn-beijing.aliyuncs.com/2023/03/01/a9f18c0502ca4221b911875ec207a434file.png',2,1,'2023-02-27 19:27:39','2023-02-27 19:27:39'),('4','王二','高级设计师简介','高级技师',1,'https://bysj-1024.oss-cn-beijing.aliyuncs.com/2023/03/01/a9f18c0502ca4221b911875ec207a434file.png',0,1,'2023-02-27 19:27:39','2023-02-27 19:27:39'),('5','王五','高级设计师简介','高级技师',1,'https://bysj-1024.oss-cn-beijing.aliyuncs.com/2023/03/01/a9f18c0502ca4221b911875ec207a434file.png',0,1,'2023-02-27 19:27:39','2023-02-27 19:27:39'),('6','珍妮.杜桑','一位彻底改变了当代珠宝的女性”。她是20世纪最伟大的女性且特立独行的女性之一，无可挑剔的品味、对创新的执著，令她成为卡地亚价值观的象征。','高级技师',1,'https://bysj-1024.oss-cn-beijing.aliyuncs.com/2023/03/20/dbca269496b146dfbc3293d4907ee8a6file.png',0,0,'2023-02-27 19:27:39','2023-03-20 22:43:18'),('7','维克多·卡斯特兰','卡斯特兰可不是逆袭的女子，她出生即是很多人都不能到达的巅峰。出身名门，而且她的家族还是那种世代的富贵，家族成员包括普罗旺斯总督、法国元帅、公爵、主教、将军等，这些足以证明她显赫的家族背景。','高级技师',1,'https://bysj-1024.oss-cn-beijing.aliyuncs.com/2023/03/20/d219b52026a346c5b20665ade41f9c7dfile.png',0,0,'2023-02-27 19:27:39','2023-03-20 22:42:45'),('8','阿莱西奥·博斯基','阿莱西奥·博斯基，19岁成为一名自由职业者，因在各种珠宝设计比赛中脱颖而出，而加入了澳大利亚知名珍珠贸易公司。从此开始将自己对于珍珠的理解和天马行空的灵感诠释于珠宝饰品的设计元素中。如今，他作为该公司创作的珠宝设计产品，吸引了珠宝设计行业及奢侈品等社会各界的广泛关注。','高级技师',1,'https://bysj-1024.oss-cn-beijing.aliyuncs.com/2023/03/20/7969afcccb91490691787ba65f0f2e82file.png',0,0,'2023-02-27 19:27:39','2023-03-20 22:42:14'),('9','席晓辉','席晓辉，国际著名设计师，首位荣获德意志联邦共和国国家设计奖的中国设计师。并多次担任国内外著名设计奖项的评委。其品牌设计，产品、包装设计，建筑空间设计，字体设计屡获国际著名设计大奖。曾连续8年2012-2020赢得German Design Award （德意志联邦共和国国家设计奖）。','高级技师',2,'https://bysj-1024.oss-cn-beijing.aliyuncs.com/2023/03/20/a7b963922bd4431eba6327cf23993f93file.png',0,0,'2023-02-27 19:27:39','2023-03-20 22:41:28');
/*!40000 ALTER TABLE `jewellery_designer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jewellery_infor`
--

DROP TABLE IF EXISTS `jewellery_infor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jewellery_infor` (
  `id` char(19) NOT NULL COMMENT '珠宝ID',
  `designer_id` char(19) DEFAULT NULL COMMENT '珠宝ID',
  `title` varchar(50) DEFAULT NULL COMMENT '珠宝标题',
  `price` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '珠宝销售价格',
  `cover` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '珠宝封面图片路径',
  `buy_count` bigint(10) unsigned DEFAULT '0' COMMENT '销售数量',
  `view_count` bigint(10) unsigned DEFAULT '0' COMMENT '浏览数量',
  `version` bigint(20) unsigned DEFAULT '1' COMMENT '乐观锁',
  `status` varchar(10) DEFAULT 'Draft' COMMENT '珠宝状态 Draft未发布  Normal已发布',
  `is_deleted` tinyint(3) DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `classofy_id` char(19) DEFAULT NULL COMMENT '珠宝分类id',
  `classofy_parent_id` char(19) DEFAULT NULL COMMENT '珠宝分类父级id',
  PRIMARY KEY (`id`),
  KEY `idx_title` (`title`),
  KEY `idx_teacher_id` (`designer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='珠宝';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jewellery_infor`
--

LOCK TABLES `jewellery_infor` WRITE;
/*!40000 ALTER TABLE `jewellery_infor` DISABLE KEYS */;
INSERT INTO `jewellery_infor` VALUES ('1634906870172987393','7','翡翠手链',8888.00,'https://bysj-1024.oss-cn-beijing.aliyuncs.com/2023/03/17/jewellery/12.jpg',985,2345,1,'Normal',0,'2023-03-12 21:19:08','2023-03-20 23:48:07','1631526094685380610','1631526094555357186'),('1634944545030414337','6','名品发饰',998.00,'https://bysj-1024.oss-cn-beijing.aliyuncs.com/2023/03/17/jewellery/11.jpg',999,1653,1,'Normal',0,'2023-03-12 23:48:50','2023-03-20 23:46:32','1631526094421139458','1631526093427089410'),('1635112002026213378','8','古典发簪',1234.00,'https://bysj-1024.oss-cn-beijing.aliyuncs.com/2023/03/17/jewellery/1.jpg',1440,3162,1,'Normal',0,'2023-03-13 10:54:15','2023-03-20 23:45:05','1631526094685380610','1631526094555357186'),('1635117029843275778','6','和田玉手链',1111.00,'https://bysj-1024.oss-cn-beijing.aliyuncs.com/2023/03/17/jewellery/5.jpg',1648,1999,1,'Normal',0,'2023-03-13 11:14:14','2023-03-20 23:44:04','1631526093829742593','1631526093427089410'),('1635152285094924289','9','玉佛',2333.00,'https://bysj-1024.oss-cn-beijing.aliyuncs.com/2023/03/17/jewellery/4.jpg',1680,2310,1,'Normal',0,'2023-03-13 13:34:19','2023-03-20 23:40:53','1631526094941233153','1631526094555357186'),('1635180779656970242','7','皇冠',1250.00,'https://bysj-1024.oss-cn-beijing.aliyuncs.com/2023/03/17/jewellery/3.jpg',1788,4362,1,'Normal',0,'2023-03-13 15:27:33','2023-03-20 23:42:31','1631526094156898306','1631526093427089410'),('1635265932395847681','8','精美头饰',10240.00,'https://bysj-1024.oss-cn-beijing.aliyuncs.com/2023/03/17/jewellery/8.jpg',1906,3000,1,'Normal',0,'2023-03-13 21:05:55','2023-03-20 23:41:29','1631526094156898306','1631526093427089410'),('1637832994595012609','9','彩钻戒指',1000.00,'https://bysj-1024.oss-cn-beijing.aliyuncs.com/2023/03/20/86e6e2da8b334a0e933bf31c7850cb19彩钻戒指.jpg',800,4399,1,'Normal',0,'2023-03-20 23:06:30','2023-03-20 23:07:53','1631526095402606593','1631526095272583170'),('1637833857963130882','6','红宝石对戒',998.00,'https://bysj-1024.oss-cn-beijing.aliyuncs.com/2023/03/20/de2ccdb39c7d413f809040f4fdfc7112红宝石对戒.jpg',700,2300,1,'Normal',0,'2023-03-20 23:09:56','2023-03-20 23:10:45','1631526095528435714','1631526095272583170'),('1637835048780562434','8','黄金对戒',999.00,'https://bysj-1024.oss-cn-beijing.aliyuncs.com/2023/03/20/f9a46ccd9b254919b1d2fa1bd59136d8黄金对戒.jpg',600,666,1,'Normal',0,'2023-03-20 23:14:40','2023-03-20 23:15:20','1631526094421139458','1631526093427089410'),('1637839553953792001','7','钻石项链',520.00,'https://bysj-1024.oss-cn-beijing.aliyuncs.com/2023/03/20/b004f9959bf444929a5380fe9dbab6a0钻石项链.jpg',500,520,1,'Normal',0,'2023-03-20 23:32:34','2023-03-20 23:33:19','1631526095071256578','1631526094555357186'),('1637840425131704321','9','绿宝石戒指',999.00,'https://bysj-1024.oss-cn-beijing.aliyuncs.com/2023/03/20/af9f92f26bda4d078c5469d802cf7b6d绿宝石戒指.jpg',400,555,1,'Normal',0,'2023-03-20 23:36:02','2023-03-20 23:36:54','1631526093829742593','1631526093427089410');
/*!40000 ALTER TABLE `jewellery_infor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jewellery_video`
--

DROP TABLE IF EXISTS `jewellery_video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jewellery_video` (
  `id` char(19) NOT NULL COMMENT '视频ID',
  `jewellery_id` char(19) NOT NULL COMMENT '珠宝ID',
  `chapter_id` char(19) NOT NULL COMMENT '大纲id',
  `title` varchar(50) DEFAULT NULL COMMENT '大纲名称',
  `video_source_id` varchar(100) DEFAULT NULL COMMENT '云端视频资源',
  `video_original_name` varchar(100) DEFAULT NULL COMMENT '原始文件名称',
  `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序字段',
  `play_count` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '播放次数',
  `duration` float NOT NULL DEFAULT '0' COMMENT '视频时长（秒）',
  `status` varchar(20) NOT NULL DEFAULT 'Empty' COMMENT 'Empty未上传 Transcoding转码中  Normal正常',
  `size` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '视频源文件大小（字节）',
  `version` bigint(20) unsigned NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_course_id` (`jewellery_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='珠宝视频';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jewellery_video`
--

LOCK TABLES `jewellery_video` WRITE;
/*!40000 ALTER TABLE `jewellery_video` DISABLE KEYS */;
INSERT INTO `jewellery_video` VALUES ('1637833316746919937','1637832994595012609','1637833067261329409','珠宝视频','f70d1220c73071ed814f0764a3fc0102','upload test.mp4',1,0,0,'Empty',0,1,'2023-03-20 23:07:47','2023-03-20 23:07:47'),('1637834040323080193','1637833857963130882','1637833937638129666','珠宝视频','5f1351e0c73171ed80fd6723b78e0102','upload test.mp4',1,0,0,'Empty',0,1,'2023-03-20 23:10:39','2023-03-20 23:10:39'),('1637835176719417346','1637835048780562434','1637835103382011905','珠宝视频','009d6280c73271ed91790764a0fd0102','upload test.mp4',1,0,0,'Empty',0,1,'2023-03-20 23:15:10','2023-03-20 23:15:10'),('1637839712951468033','1637839553953792001','1637839604784562177','珠宝视频','85354100c73471ed814f0764a3fc0102','upload test.mp4',1,0,0,'Empty',0,1,'2023-03-20 23:33:12','2023-03-20 23:33:12'),('1637840630849732609','1637840425131704321','1637840489019342849','珠宝视频','081a5150c73571ed80c90764b3ec0102','upload test.mp4',1,0,0,'Empty',0,1,'2023-03-20 23:36:51','2023-03-20 23:36:51'),('1637840858764017665','1635265932395847681','1635266022560800770','珠宝视频','28450740c73571ed815a0674a2ce0102','upload test.mp4',1,0,0,'Empty',0,1,'2023-03-20 23:37:45','2023-03-20 23:37:45'),('1637841055825002498','1635180779656970242','1635180847038464001','珠宝视频','44518260c73571edacf26632b68f0102','upload test.mp4',1,0,0,'Empty',0,1,'2023-03-20 23:38:32','2023-03-20 23:38:32'),('1637841404673654786','1635152285094924289','1635154535460655105','珠宝视频','75403650c73571eda3c26633b79f0102','upload test.mp4',1,0,0,'Empty',0,1,'2023-03-20 23:39:55','2023-03-20 23:39:55'),('1637842426808762369','1635117029843275778','1635118478291636225','珠宝视频','0738d580c73671ed8f9d6732b68e0102','upload test.mp4',1,0,0,'Empty',0,1,'2023-03-20 23:43:59','2023-03-20 23:43:59'),('1637842694690570241','1635112002026213378','1635112969597620226','珠宝视频','2d657a60c73671edacf26632b68f0102','upload test.mp4',1,0,0,'Empty',0,1,'2023-03-20 23:45:03','2023-03-20 23:45:03'),('1637843054511521794','1634944545030414337','1637842998471426050','珠宝视频','607d01c0c73671eda09a6732b78e0102','upload test.mp4',1,0,0,'Empty',0,1,'2023-03-20 23:46:29','2023-03-20 23:46:29'),('18','1633859444964081665','10','testtest',NULL,NULL,0,0,0,'Empty',0,1,'2023-03-12 15:47:30','2023-03-12 15:47:30'),('19','1633859444964081665','11','test1212',NULL,NULL,0,0,0,'Empty',0,1,'2023-03-12 15:57:41','2023-03-12 15:57:41'),('20','1633859444964081665','11','test11',NULL,NULL,0,0,0,'Empty',0,1,'2023-03-12 15:57:41','2023-03-12 15:57:41');
/*!40000 ALTER TABLE `jewellery_video` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ucenter_member`
--

DROP TABLE IF EXISTS `ucenter_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ucenter_member` (
  `id` char(19) NOT NULL COMMENT '会员id',
  `openid` varchar(128) DEFAULT NULL COMMENT '微信openid',
  `mobile` varchar(11) DEFAULT '' COMMENT '手机号',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `sex` tinyint(2) unsigned DEFAULT NULL COMMENT '性别 1 女，2 男',
  `age` tinyint(3) unsigned DEFAULT NULL COMMENT '年龄',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `sign` varchar(100) DEFAULT NULL COMMENT '用户签名',
  `is_disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用 1（true）已禁用，  0（false）未禁用',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ucenter_member`
--

LOCK TABLES `ucenter_member` WRITE;
/*!40000 ALTER TABLE `ucenter_member` DISABLE KEYS */;
INSERT INTO `ucenter_member` VALUES ('1',NULL,'13435263890','96e79218965eb72c92a549dd5a330112','11111',1,22,'https://bysj-1024.oss-cn-beijing.aliyuncs.com/2023/03/07/da9a5c3845d5427184075d1d22d97ea8hmbb2.jpg',NULL,0,0,'2023-03-18 12:21:10','2023-03-18 12:21:10'),('1637075973281169409',NULL,'12345678911','e10adc3949ba59abbe56e057f20f883e','短信测试',NULL,NULL,'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132',NULL,0,0,'2023-03-18 20:58:22','2023-03-18 20:58:22'),('1637077976384290818',NULL,'15815868669','25f9e794323b453885f5181f1b624d0b','张思楠11',NULL,NULL,'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132',NULL,0,0,'2023-03-18 21:06:20','2023-03-18 21:06:20'),('1637449377016459266',NULL,'13435267890','25f9e794323b453885f5181f1b624d0b','123',NULL,NULL,'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132',NULL,0,0,'2023-03-19 21:42:08','2023-03-19 21:42:08'),('1638463595517956097',NULL,'13435268280','25f9e794323b453885f5181f1b624d0b','张思楠',NULL,NULL,'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132',NULL,0,0,'2023-03-22 16:52:17','2023-03-22 16:52:17');
/*!40000 ALTER TABLE `ucenter_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (5,'Billie',24,'test5@baomidou.com',NULL,NULL,NULL,1),(1575140158926299138,'sinan',22,'1193932350@qq.com',NULL,NULL,NULL,0),(1575150891164598273,'jiaming',23,'1193932350@qq.com','2022-09-28 23:50:12','2022-09-28 23:50:12',NULL,0),(1576451816218959873,'liunan',200,'1193932350@qq.com','2022-10-02 13:59:37','2022-10-02 14:06:17',2,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'jewellerydisplay'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-21  9:14:45
