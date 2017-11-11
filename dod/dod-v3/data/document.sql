# Host: 127.0.0.1:4306  (Version 5.0.18-nt)
# Date: 2017-11-11 19:58:14
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "user_info"
#

DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` bigint(20) NOT NULL auto_increment,
  `username` varchar(255) NOT NULL default '',
  `password` varchar(32) NOT NULL default '',
  `role` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "user_info"
#

INSERT INTO `user_info` VALUES (15,'Kate','123','edu.whut.cs.oo.domain.Administrator'),(16,'Jack','123','edu.whut.cs.oo.domain.Operator'),(17,'Rose','123','edu.whut.cs.oo.domain.Browser'),(19,'Bill','123456','edu.whut.cs.oo.domain.Administrator');

#
# Structure for table "document_info"
#

DROP TABLE IF EXISTS `document_info`;
CREATE TABLE `document_info` (
  `document_id` bigint(20) NOT NULL auto_increment,
  `sn` varchar(255) default NULL,
  `timestamp` datetime NOT NULL default '0000-00-00 00:00:00',
  `description` text,
  `name` varchar(255) NOT NULL default '',
  `absolute_path` varchar(255) default NULL,
  `document_user_id` bigint(20) NOT NULL default '0',
  PRIMARY KEY  (`document_id`),
  KEY `FK36EBCB177B6850` (`document_user_id`),
  CONSTRAINT `FK36EBCB177B6850` FOREIGN KEY (`document_user_id`) REFERENCES `user_info` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "document_info"
#

INSERT INTO `document_info` VALUES (1,'D001','2017-11-10 23:21:18','这是一份很重要的档案','第一个档案','e:\\OOP\\uploadfile\\User.java',16),(2,'D002','2017-11-11 13:33:11','sdwerw','User.java','e:\\OOP\\uploadfile\\1510378391601.java',16),(3,'D003','2017-11-11 14:23:46','ewrwerew','1510381426100.java','e:\\OOP\\uploadfile\\1510381426133.java',16),(4,'D004','2017-11-11 14:32:35','sdfs','User.java','e:\\OOP\\uploadfile\\1510381955273.java',16),(5,'D003','2017-11-11 16:12:24','dasfas','People.java','e:\\OOP\\uploadfile\\1510387943983.java',16),(6,'D004','2017-11-11 16:14:24','dsfs','People.java','e:\\OOP\\uploadfile\\1510388064873.java',16),(7,'D005','2017-11-11 17:10:35','dafasdf','lukeall-3.5.0.jar','e:\\OOP\\uploadfile\\1510391435397.jar',16);
