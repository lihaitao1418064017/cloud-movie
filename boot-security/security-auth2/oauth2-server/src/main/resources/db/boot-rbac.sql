/*
 Navicat Premium Data Transfer

 Source Server         : 47.99.216.57(aliyun-self)
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : 47.99.216.57:3306
 Source Schema         : boot-rbac

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 23/06/2020 14:23:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id`          BIGINT(11)   NOT NULL AUTO_INCREMENT,
  `url`         VARCHAR(255) NOT NULL,
  `name`        VARCHAR(255) NOT NULL,
  `path`        VARCHAR(255) NOT NULL,
  `sign`        VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NULL     DEFAULT NULL,
  `pid`         BIGINT(11)   NOT NULL,
  `update_time` BIGINT(20)   NULL     DEFAULT 0,
  `create_time` BIGINT(20)   NULL     DEFAULT 0,
  `creator_id`  VARCHAR(30)  NULL     DEFAULT NULL,
  `update_id`   VARCHAR(30)  NULL     DEFAULT NULL,
  `status`      INT(11)      NULL     DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
)
  ENGINE = InnoDB;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id`          BIGINT(11)   NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(255) NOT NULL,
  `sign`        VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NULL     DEFAULT NULL,
  `update_time` BIGINT(20)   NULL     DEFAULT 0,
  `create_time` BIGINT(20)   NULL     DEFAULT 0,
  `creator_id`  VARCHAR(30)  NULL     DEFAULT NULL,
  `update_id`   VARCHAR(30)  NULL     DEFAULT NULL,
  `status`      INT(11)      NULL     DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
)
  ENGINE = InnoDB;

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `role_id`       BIGINT(11)  NOT NULL,
  `permission_id` BIGINT(11)  NOT NULL,
  `id`            BIGINT(11)  NOT NULL,
  `update_time`   BIGINT(20)  NULL DEFAULT 0,
  `create_time`   BIGINT(20)  NULL DEFAULT 0,
  `creator_id`    VARCHAR(30) NULL DEFAULT NULL,
  `update_id`     VARCHAR(30) NULL DEFAULT NULL,
  `status`        INT(11)     NULL DEFAULT 0
)
  ENGINE = InnoDB;

-- ----------------------------
-- Table structure for userInfo
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id`          BIGINT(11)   NOT NULL AUTO_INCREMENT,
  `username`    VARCHAR(255) NOT NULL,
  `name`        VARCHAR(255) NOT NULL,
  `password`    VARCHAR(255) NOT NULL,
  `phone`       VARCHAR(255) NULL     DEFAULT NULL,
  `email`       VARCHAR(255) NULL     DEFAULT NULL,
  `identify`    VARCHAR(255) NULL     DEFAULT NULL,
  `sex`         INT(11)      NULL     DEFAULT NULL,
  `age`         INT(20)      NULL     DEFAULT 0,
  `create_time` BIGINT(20)   NULL     DEFAULT 0,
  `update_time` BIGINT(20)   NULL     DEFAULT 0,
  `creator_id`  VARCHAR(30)  NULL     DEFAULT NULL,
  `update_id`   VARCHAR(30)  NULL     DEFAULT NULL,
  `status`      INT(11)      NULL     DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
)
  ENGINE = InnoDB;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id`     BIGINT(11)  NOT NULL,
  `role_id`     BIGINT(11)  NOT NULL,
  `id`          BIGINT(11)  NOT NULL,
  `update_time` BIGINT(20)  NULL DEFAULT 0,
  `create_time` BIGINT(20)  NULL DEFAULT 0,
  `creator_id`  VARCHAR(30) NULL DEFAULT NULL,
  `update_id`   VARCHAR(30) NULL DEFAULT NULL,
  `status`      INT(11)     NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
)
  ENGINE = InnoDB;

SET FOREIGN_KEY_CHECKS = 1;
