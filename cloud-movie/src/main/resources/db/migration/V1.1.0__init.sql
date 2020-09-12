

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

/**
 演员表
 */
-- ----------------------------
-- Table structure for actor
-- ----------------------------
DROP TABLE IF EXISTS `actor`;
CREATE TABLE `actor` (
  `id` varchar(255)  NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL,
  `sex` int(10) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `vocational` varchar(255) DEFAULT NULL,
  `create_time`  BIGINT(20)   NULL     DEFAULT 0,
  `update_time`  BIGINT(20)   NULL     DEFAULT 0,
  `creator_user` VARCHAR(30)  NULL     DEFAULT NULL,
  `update_user`  VARCHAR(30)  NULL     DEFAULT NULL,
  `status`       INT(11)      NULL     DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB ;

/**
 电影表
 */
-- ----------------------------
-- Table structure for movie
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `director` varchar(255) DEFAULT NULL,
  `intro` varchar(255) DEFAULT NULL,
  `score` double(10,0) DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL,
  `play_times` bigint(50) DEFAULT NULL,
  `year` varchar(255) DEFAULT NULL,
  `scriptwriter` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `uri` varchar(255) NOT NULL,
  `extend_field` varchar(255) DEFAULT NULL,
  `create_time`  BIGINT(20)   NULL     DEFAULT 0,
  `update_time`  BIGINT(20)   NULL     DEFAULT 0,
  `creator_user` VARCHAR(30)  NULL     DEFAULT NULL,
  `update_user`  VARCHAR(30)  NULL     DEFAULT NULL,
  `status`       INT(11)      NULL     DEFAULT 0
) ENGINE=InnoDB ;

SET FOREIGN_KEY_CHECKS = 1;
