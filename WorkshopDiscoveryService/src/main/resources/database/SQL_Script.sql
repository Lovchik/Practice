-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema workshop
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema workshop
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `workshop` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `workshop` ;

-- -----------------------------------------------------
-- Table `workshop`.`workshop`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `workshop`.`workshop` (
                                                     `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                                                     `address` VARCHAR(255) NOT NULL,
                                                     `coordinates` VARCHAR(255) NOT NULL,
                                                     `workshop_name` VARCHAR(255) NOT NULL,
                                                     PRIMARY KEY (`id`))
    ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `workshop`.`person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `workshop`.`person` (
                                                   `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                                                   `coordinates` VARCHAR(255) NULL DEFAULT NULL,
                                                   `first_name` VARCHAR(255) NOT NULL,
                                                   `last_name` VARCHAR(255) NOT NULL,
                                                   `password` VARCHAR(255) NOT NULL,
                                                   `phone_number` VARCHAR(255) NOT NULL,
                                                   `is_busy` BIT(1) NULL DEFAULT NULL,
                                                   `idwork_place` BIGINT(20) NOT NULL,
                                                   PRIMARY KEY (`id`),
                                                   INDEX `FKg0qryx601xcw548tjypu8ok03` (`idwork_place` ASC) VISIBLE,
                                                   CONSTRAINT `FKg0qryx601xcw548tjypu8ok03`
                                                       FOREIGN KEY (`idwork_place`)
                                                           REFERENCES `workshop`.`workshop` (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `workshop`.`request`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `workshop`.`request` (
                                                    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                                                    `id_client` BIGINT(20) NOT NULL,
                                                    `id_workshop` BIGINT(20) NOT NULL,
                                                    PRIMARY KEY (`id`),
                                                    INDEX `FKiae4x1nauf2pcfwljotkd1c0m` (`id_client` ASC) VISIBLE,
                                                    INDEX `FK1sep63bld2s2i03n85jk0sjob` (`id_workshop` ASC) VISIBLE,
                                                    CONSTRAINT `FK1sep63bld2s2i03n85jk0sjob`
                                                        FOREIGN KEY (`id_workshop`)
                                                            REFERENCES `workshop`.`workshop` (`id`),
                                                    CONSTRAINT `FKiae4x1nauf2pcfwljotkd1c0m`
                                                        FOREIGN KEY (`id_client`)
                                                            REFERENCES `workshop`.`person` (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `workshop`.`contract`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `workshop`.`contract` (
                                                     `id` BIGINT(20) NOT NULL,
                                                     `status` INT(11) NOT NULL,
                                                     `id_master` BIGINT(20) NOT NULL,
                                                     `id_request` BIGINT(20) NOT NULL,
                                                     `id_workshop` BIGINT(20) NOT NULL,
                                                     PRIMARY KEY (`id`),
                                                     UNIQUE INDEX `UK_7d2ig055y0lj1plx8gni7lkq9` (`id_master` ASC) VISIBLE,
                                                     UNIQUE INDEX `UK_8dlyod4oo78vom04rqoh0q6ap` (`id_request` ASC) VISIBLE,
                                                     INDEX `FKcob364e4bwfgienctn273jg9o` (`id_workshop` ASC) VISIBLE,
                                                     CONSTRAINT `FKan29d0ud4nbgavl4xg1h352li`
                                                         FOREIGN KEY (`id_master`)
                                                             REFERENCES `workshop`.`person` (`id`),
                                                     CONSTRAINT `FKcob364e4bwfgienctn273jg9o`
                                                         FOREIGN KEY (`id_workshop`)
                                                             REFERENCES `workshop`.`workshop` (`id`),
                                                     CONSTRAINT `FKnbyg7gn1k79n9k1w0nhk0y1bg`
                                                         FOREIGN KEY (`id_request`)
                                                             REFERENCES `workshop`.`request` (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `workshop`.`service`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `workshop`.`service` (
                                                    `id` INT(11) NOT NULL AUTO_INCREMENT,
                                                    `service_name` VARCHAR(255) NOT NULL,
                                                    PRIMARY KEY (`id`))
    ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `workshop`.`priceposition`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `workshop`.`priceposition` (
                                                          `id` INT(11) NOT NULL AUTO_INCREMENT,
                                                          `price` DOUBLE NOT NULL,
                                                          `id_service` INT(11) NOT NULL,
                                                          `id_workshop` BIGINT(20) NOT NULL,
                                                          PRIMARY KEY (`id`),
                                                          UNIQUE INDEX `UK_au6ynal885i3dr6okqnrksr35` (`id_service` ASC) VISIBLE,
                                                          INDEX `FK5b8446eqtaffr5kqtppji86eh` (`id_workshop` ASC) VISIBLE,
                                                          CONSTRAINT `FK5b8446eqtaffr5kqtppji86eh`
                                                              FOREIGN KEY (`id_workshop`)
                                                                  REFERENCES `workshop`.`workshop` (`id`),
                                                          CONSTRAINT `FKjamrtvivxmdsxgmn0x7jhenjw`
                                                              FOREIGN KEY (`id_service`)
                                                                  REFERENCES `workshop`.`service` (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `workshop`.`request_service`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `workshop`.`request_service` (
                                                            `id_request` BIGINT(20) NOT NULL,
                                                            `id_service` INT(11) NOT NULL,
                                                            INDEX `FK99ii45f2u66h24sobt02jyc4` (`id_service` ASC) VISIBLE,
                                                            INDEX `FK1ul4hjj5obmcuispvq8yh0qh7` (`id_request` ASC) VISIBLE,
                                                            CONSTRAINT `FK1ul4hjj5obmcuispvq8yh0qh7`
                                                                FOREIGN KEY (`id_request`)
                                                                    REFERENCES `workshop`.`request` (`id`),
                                                            CONSTRAINT `FK99ii45f2u66h24sobt02jyc4`
                                                                FOREIGN KEY (`id_service`)
                                                                    REFERENCES `workshop`.`service` (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
