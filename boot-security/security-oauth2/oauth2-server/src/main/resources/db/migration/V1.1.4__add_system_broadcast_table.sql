DROP TABLE IF EXISTS `system_login_info`;
CREATE TABLE `system_login_info` (
  `id`          VARCHAR(255) NOT NULL,
  `username`    VARCHAR(255) DEFAULT NULL,
  `ip_address`  VARCHAR(255) DEFAULT NULL,
  `msg`         VARCHAR(255) DEFAULT NULL,
  `access_time` DATETIME(0)  DEFAULT NULL,
  `status`      INT(10)      DEFAULT NULL,
  `creator_id`  VARCHAR(255) DEFAULT NULL,
  `create_time` BIGINT(20)   DEFAULT NULL,
  `update_id`   VARCHAR(255) DEFAULT NULL,
  `update_time` BIGINT(20)   DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
