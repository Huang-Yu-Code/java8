DROP DATABASE IF EXISTS `jdbc`;
CREATE DATABASE `jdbc`;
USE `jdbc`;
DROP TABLE IF EXISTS `jdbc_model`;
CREATE TABLE `jdbc_model`
(
    `id`          bigint                  NOT NULL AUTO_INCREMENT,
    `name`        varchar(64)             NOT NULL,
    `gender`      tinyint(1)              NOT NULL,
    `money`       decimal(10, 0) UNSIGNED NOT NULL,
    `create_time` datetime                NOT NULL,
    `delete`      tinyint(1)              NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
)
