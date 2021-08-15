DROP TABLE IF EXISTS `entity`;
CREATE TABLE `entity`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `gender` tinyint NOT NULL DEFAULT 1,
  `money` decimal(10, 0) UNSIGNED NOT NULL DEFAULT 1000,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_delete` tinyint NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
)
