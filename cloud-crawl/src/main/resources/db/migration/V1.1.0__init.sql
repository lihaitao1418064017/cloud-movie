/*
 Navicat Premium Data Transfer

 Source Server         : 47.99.216.57(ailiyun）
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : 47.99.216.57:3306
 Source Schema         : cloud-crawl

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 31/10/2020 12:21:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for job
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job` (
  `id` varchar(255) NOT NULL,
  `spider_name` varchar(255) DEFAULT NULL,
  `sort_index` int(50) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `thread_num` int(50) DEFAULT NULL,
  `sleep_time` int(50) DEFAULT NULL,
  `retry_times` int(50) DEFAULT NULL COMMENT '秒',
  `retry_sleep_time` int(50) DEFAULT NULL COMMENT '秒',
  `cycle_retry_times` int(50) DEFAULT NULL,
  `time_out` int(50) DEFAULT NULL COMMENT '秒',
  `end_time` bigint DEFAULT NULL COMMENT 'long',
  `success_num` int(50) DEFAULT NULL COMMENT 'p爬虫爬去数量',
  `create_time`  BIGINT(20)   NULL     DEFAULT 0,
  `update_time`  BIGINT(20)   NULL     DEFAULT 0,
  `creator_user` VARCHAR(30)  NULL     DEFAULT NULL,
  `update_user`  VARCHAR(30)  NULL     DEFAULT NULL,
  `status`       INT(11)      NULL     DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for template_field
-- ----------------------------
DROP TABLE IF EXISTS `template_field`;
CREATE TABLE `template_field` (
  `id` varchar(255) NOT NULL,
  `page_id` varchar(255) DEFAULT NULL,
  `detail_or_list` int(10) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `xpath_rule` varchar(255) DEFAULT NULL,
  `sort_index` int(10) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `create_time`  BIGINT(20)   NULL     DEFAULT 0,
  `update_time`  BIGINT(20)   NULL     DEFAULT 0,
  `creator_user` VARCHAR(30)  NULL     DEFAULT NULL,
  `update_user`  VARCHAR(30)  NULL     DEFAULT NULL,
  `status`       INT(11)      NULL     DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for template_page
-- ----------------------------
DROP TABLE IF EXISTS `template_page`;
CREATE TABLE `template_page` (
  `id` varchar(255) NOT NULL,
  `job_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url_regex` varchar(255) DEFAULT NULL,
  `create_time`  BIGINT(20)   NULL     DEFAULT 0,
  `update_time`  BIGINT(20)   NULL     DEFAULT 0,
  `creator_user` VARCHAR(30)  NULL     DEFAULT NULL,
  `update_user`  VARCHAR(30)  NULL     DEFAULT NULL,
  `status`       INT(11)      NULL     DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
