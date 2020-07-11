DROP TABLE IF EXISTS `data_dict`;
CREATE TABLE `data_dict` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `pid` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `level` int(10) DEFAULT NULL,
  `status` int(10) DEFAULT NULL,
  `creator_id` varchar(255) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_id` varchar(255) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;