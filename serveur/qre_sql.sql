-- MySQL Script generated by MySQL Workbench
-- 01/19/16 01:42:56
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema qre
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `qre` ;

-- -----------------------------------------------------
-- Schema qre
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `qre` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `qre` ;

-- -----------------------------------------------------
-- Table `qre`.`Classe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qre`.`Classe` ;

CREATE TABLE IF NOT EXISTS `qre`.`Classe` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `libelle` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `qre`.`Groupe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qre`.`Groupe` ;

CREATE TABLE IF NOT EXISTS `qre`.`Groupe` (
  `id` INT NOT NULL,
  `libelle` VARCHAR(45) NULL,
  `classe_id` INT NOT NULL,
  PRIMARY KEY (`id`, `classe_id`),
  CONSTRAINT `fk_Groupe_Classe1`
    FOREIGN KEY (`classe_id`)
    REFERENCES `qre`.`Classe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `qre`.`Etudiant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qre`.`Etudiant` ;

CREATE TABLE IF NOT EXISTS `qre`.`Etudiant` (
  `id` INT NOT NULL,
  `nom` VARCHAR(255) NULL,
  `prenom` VARCHAR(255) NULL,
  `email` VARCHAR(45) NULL,
  `date_naiss` DATE NULL,
  `num_etu` VARCHAR(45) NULL,
  `groupe_id` INT NOT NULL,
  `classe_id` INT NOT NULL,
  PRIMARY KEY (`id`, `groupe_id`, `classe_id`),
  CONSTRAINT `fk_Etudiant_Groupe1`
    FOREIGN KEY (`groupe_id` , `classe_id`)
    REFERENCES `qre`.`Groupe` (`id` , `classe_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `qre`.`Matiere`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qre`.`Matiere` ;

CREATE TABLE IF NOT EXISTS `qre`.`Matiere` (
  `id` INT NOT NULL,
  `libelle` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `qre`.`Professeur`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qre`.`Professeur` ;

CREATE TABLE IF NOT EXISTS `qre`.`Professeur` (
  `id` INT NOT NULL,
  `nom` VARCHAR(255) NULL,
  `prenom` VARCHAR(255) NULL,
  `email` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `qre`.`Emargement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qre`.`Emargement` ;

CREATE TABLE IF NOT EXISTS `qre`.`Emargement` (
  `id` INT NOT NULL,
  `date` DATETIME NOT NULL,
  `url_generated` VARCHAR(255) NOT NULL,
  `type` ENUM('CM', 'TD', 'TP') NULL,
  `matiere_id` INT NOT NULL,
  `professeur_id` INT NOT NULL,
  PRIMARY KEY (`id`, `matiere_id`, `professeur_id`),
  CONSTRAINT `fk_Emargement_Matiere1`
    FOREIGN KEY (`matiere_id`)
    REFERENCES `qre`.`Matiere` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Emargement_Professeur1`
    FOREIGN KEY (`professeur_id`)
    REFERENCES `qre`.`Professeur` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `qre`.`Signature`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qre`.`Signature` ;

CREATE TABLE IF NOT EXISTS `qre`.`Signature` (
  `emargement_id` INT NOT NULL,
  `etudiant_id` INT NOT NULL,
  `signee` TINYINT(1) NULL,
  `date` DATETIME NULL,
  PRIMARY KEY (`emargement_id`, `etudiant_id`),
  CONSTRAINT `fk_Emargement_has_Etudiant_Emargement1`
    FOREIGN KEY (`emargement_id`)
    REFERENCES `qre`.`Emargement` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Emargement_has_Etudiant_Etudiant1`
    FOREIGN KEY (`etudiant_id`)
    REFERENCES `qre`.`Etudiant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `qre`.`Emargement_has_Classe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qre`.`Emargement_has_Classe` ;

CREATE TABLE IF NOT EXISTS `qre`.`Emargement_has_Classe` (
  `emargement_id` INT NOT NULL,
  `matiere_id` INT NOT NULL,
  `classe_id` INT NOT NULL,
  PRIMARY KEY (`emargement_id`, `matiere_id`, `classe_id`),
  CONSTRAINT `fk_Emargement_has_Classe_Emargement1`
    FOREIGN KEY (`emargement_id` , `matiere_id`)
    REFERENCES `qre`.`Emargement` (`id` , `matiere_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Emargement_has_Classe_Classe1`
    FOREIGN KEY (`classe_id`)
    REFERENCES `qre`.`Classe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
