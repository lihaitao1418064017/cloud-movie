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
CREATE TABLE `permission`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255)  NOT NULL,
  `name` varchar(255)  NOT NULL,
  `sign` varchar(255)  NOT NULL,
  `description` varchar(255)  NULL DEFAULT NULL,
  `pid` bigint(11) NOT NULL,
  `update_time` bigint(20) NULL DEFAULT 0,
  `create_time` bigint(20) NULL DEFAULT 0,
  `creator_id` varchar(30)  NULL DEFAULT NULL,
  `update_id` varchar(30)  NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB  ;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255)  NOT NULL,
  `sign` varchar(255)  NOT NULL,
  `description` varchar(255)  NULL DEFAULT NULL,
  `update_time` bigint(20) NULL DEFAULT 0,
  `create_time` bigint(20) NULL DEFAULT 0,
  `creator_id` varchar(30)  NULL DEFAULT NULL,
  `update_id` varchar(30)  NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB;

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `role_id` bigint(11) NOT NULL,
  `permission_id` bigint(11) NOT NULL,
  `id` bigint(11) NOT NULL,
  `update_time` bigint(20) NULL DEFAULT 0,
  `create_time` bigint(20) NULL DEFAULT 0,
  `creator_id` varchar(30)  NULL DEFAULT NULL,
  `update_id` varchar(30)  NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT 0
) ENGINE = InnoDB;

-- ----------------------------
-- Table structure for userInfo
-- ----------------------------
DROP TABLE IF EXISTS `userInfo`;
CREATE TABLE `userInfo`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255)  NOT NULL,
  `password` varchar(255)  NOT NULL,
  `phone` varchar(255)  NULL DEFAULT NULL,
  `email` varchar(255)  NULL DEFAULT NULL,
  `identify` varchar(255)  NULL DEFAULT NULL,
  `sex` int(11) NULL DEFAULT NULL,
  `age` int(20) NULL DEFAULT 0,
  `create_time` bigint(20) NULL DEFAULT 0,
  `creator_id` varchar(30)  NULL DEFAULT NULL,
  `update_id` varchar(30)  NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_id` bigint(11) NOT NULL,
  `role_id` bigint(11) NOT NULL,
  `id` bigint(11) NOT NULL,
  `update_time` bigint(20) NULL DEFAULT 0,
  `create_time` bigint(20) NULL DEFAULT 0,
  `creator_id` varchar(30)  NULL DEFAULT NULL,
  `update_id` varchar(30)  NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT 0,
   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB;

SET FOREIGN_KEY_CHECKS = 1;