drop schema IF EXISTS `cinema_db`;
CREATE SCHEMA IF NOT EXISTS `cinema_db` DEFAULT CHARACTER SET utf8;
USE `cinema_db`;

-- -----------------------------------------------------
-- Table `cinema_db`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema_db`.`user`
(
    `id`      INT auto_increment not null,
    `name`    VARCHAR(45)        NULL,
    `surname` VARCHAR(45)        NULL,
    `email`   VARCHAR(45)        NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE
)
    ENGINE = InnoDB;

select *
from user;
