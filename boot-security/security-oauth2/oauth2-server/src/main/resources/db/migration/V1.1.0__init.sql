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

 Date: 08/07/2020 10:10:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for client_details
-- ----------------------------
DROP TABLE IF EXISTS `client_details`;
CREATE TABLE `client_details` (
  `appId`                  VARCHAR(128)  NOT NULL,
  `resourceIds`            VARCHAR(128)  NULL DEFAULT NULL,
  `appSecret`              VARCHAR(128)  NULL DEFAULT NULL,
  `scope`                  VARCHAR(128)  NULL DEFAULT NULL,
  `grantTypes`             VARCHAR(128)  NULL DEFAULT NULL,
  `redirectUrl`            VARCHAR(128)  NULL DEFAULT NULL,
  `authorities`            VARCHAR(128)  NULL DEFAULT NULL,
  `access_token_validity`  INT(11)       NULL DEFAULT NULL,
  `refresh_token_validity` INT(11)       NULL DEFAULT NULL,
  `additionalInformation`  VARCHAR(4096) NULL DEFAULT NULL,
  `autoApproveScopes`      VARCHAR(128)  NULL DEFAULT NULL,
  PRIMARY KEY (`appId`) USING BTREE
)
  ENGINE = InnoDB;

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id`          VARCHAR(128) NULL DEFAULT NULL,
  `token`             BLOB         NULL,
  `authentication_id` VARCHAR(128) NOT NULL,
  `user_name`         VARCHAR(128) NULL DEFAULT NULL,
  `client_id`         VARCHAR(128) NULL DEFAULT NULL,
  `authentication`    BLOB         NULL,
  `refresh_token`     VARCHAR(128) NULL DEFAULT NULL,
  PRIMARY KEY (`authentication_id`) USING BTREE
)
  ENGINE = InnoDB;

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
  `userId`         VARCHAR(128) NULL DEFAULT NULL,
  `clientId`       VARCHAR(128) NULL DEFAULT NULL,
  `scope`          VARCHAR(128) NULL DEFAULT NULL,
  `status`         VARCHAR(10)  NULL DEFAULT NULL,
  `expiresAt`      TIMESTAMP(0) NULL DEFAULT NULL,
  `lastModifiedAt` TIMESTAMP(0) NULL DEFAULT NULL
)
  ENGINE = InnoDB;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id`               VARCHAR(128)  NOT NULL,
  `resource_ids`            VARCHAR(128)  NULL DEFAULT NULL,
  `client_secret`           VARCHAR(128)  NULL DEFAULT NULL,
  `scope`                   VARCHAR(128)  NULL DEFAULT NULL,
  `authorized_grant_types`  VARCHAR(128)  NULL DEFAULT NULL,
  `web_server_redirect_uri` VARCHAR(128)  NULL DEFAULT NULL,
  `authorities`             VARCHAR(128)  NULL DEFAULT NULL,
  `access_token_validity`   INT(11)       NULL DEFAULT NULL,
  `refresh_token_validity`  INT(11)       NULL DEFAULT NULL,
  `additional_information`  VARCHAR(4096) NULL DEFAULT NULL,
  `autoapprove`             VARCHAR(128)  NULL DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
)
  ENGINE = InnoDB;

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `token_id`          VARCHAR(128) NULL DEFAULT NULL,
  `token`             BLOB         NULL,
  `authentication_id` VARCHAR(128) NOT NULL,
  `user_name`         VARCHAR(128) NULL DEFAULT NULL,
  `client_id`         VARCHAR(128) NULL DEFAULT NULL,
  PRIMARY KEY (`authentication_id`) USING BTREE
)
  ENGINE = InnoDB;

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code`           VARCHAR(128) NULL DEFAULT NULL,
  `authentication` BLOB         NULL
)
  ENGINE = InnoDB;

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id`       VARCHAR(128) NULL DEFAULT NULL,
  `token`          BLOB         NULL,
  `authentication` BLOB         NULL
)
  ENGINE = InnoDB;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id`           BIGINT(11)   NOT NULL AUTO_INCREMENT,
  `url`          VARCHAR(255) NULL     DEFAULT NULL,
  `name`         VARCHAR(255) NULL     DEFAULT NULL,
  `sign`         VARCHAR(255) NULL     DEFAULT NULL,
  `description`  VARCHAR(255) NULL     DEFAULT NULL,
  `pid`          BIGINT(11)   NULL     DEFAULT NULL,
  `update_time`  BIGINT(20)   NULL     DEFAULT 0,
  `create_time`  BIGINT(20)   NULL     DEFAULT 0,
  `creator_user` VARCHAR(30)  NULL     DEFAULT NULL,
  `update_user`  VARCHAR(30)  NULL     DEFAULT NULL,
  `status`       INT(11)      NULL     DEFAULT 0,
  `path`         VARCHAR(255) NULL     DEFAULT NULL,
  `type`         VARCHAR(255) NULL     DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
)
  ENGINE = InnoDB;

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username`  VARCHAR(64)  NOT NULL,
  `series`    VARCHAR(64)  NOT NULL,
  `token`     VARCHAR(64)  NOT NULL,
  `last_used` TIMESTAMP(0) NOT NULL,
  PRIMARY KEY (`series`) USING BTREE
)
  ENGINE = InnoDB;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id`           BIGINT(11)   NOT NULL AUTO_INCREMENT,
  `name`         VARCHAR(255) NULL     DEFAULT NULL,
  `sign`         VARCHAR(255) NOT NULL,
  `description`  VARCHAR(255) NULL     DEFAULT NULL,
  `update_time`  BIGINT(20)   NULL     DEFAULT 0,
  `create_time`  BIGINT(20)   NULL     DEFAULT 0,
  `creator_user` VARCHAR(30)  NULL     DEFAULT NULL,
  `update_user`  VARCHAR(30)  NULL     DEFAULT NULL,
  `status`       INT(11)      NULL     DEFAULT 0,
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
  `creator_user`  VARCHAR(30) NULL DEFAULT NULL,
  `update_user`   VARCHAR(30) NULL DEFAULT NULL,
  `status`        INT(11)     NULL DEFAULT 0
)
  ENGINE = InnoDB;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id`           BIGINT(11)   NOT NULL AUTO_INCREMENT,
  `username`     VARCHAR(255) NOT NULL,
  `name`         VARCHAR(255) NULL     DEFAULT NULL,
  `password`     VARCHAR(255) NOT NULL,
  `phone`        VARCHAR(255) NOT NULL,
  `email`        VARCHAR(255) NULL     DEFAULT NULL,
  `identify`     VARCHAR(255) NULL     DEFAULT NULL,
  `sex`          INT(11)      NULL     DEFAULT NULL,
  `age`          INT(20)      NULL     DEFAULT 0,
  `create_time`  BIGINT(20)   NULL     DEFAULT 0,
  `update_time`  BIGINT(20)   NULL     DEFAULT 0,
  `creator_user` VARCHAR(30)  NULL     DEFAULT NULL,
  `update_user`  VARCHAR(30)  NULL     DEFAULT NULL,
  `status`       INT(11)      NULL     DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 105;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id`      BIGINT(11)  NOT NULL,
  `role_id`      BIGINT(11)  NOT NULL,
  `id`           BIGINT(11)  NOT NULL,
  `update_time`  BIGINT(20)  NULL DEFAULT 0,
  `create_time`  BIGINT(20)  NULL DEFAULT 0,
  `creator_user` VARCHAR(30) NULL DEFAULT NULL,
  `update_user`  VARCHAR(30) NULL DEFAULT NULL,
  `status`       INT(11)     NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
)
  ENGINE = InnoDB;

SET FOREIGN_KEY_CHECKS = 1;
