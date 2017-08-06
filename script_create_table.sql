-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema highlander
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema highlander
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `highlander` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema highlanderOLD
-- -----------------------------------------------------
USE `highlander` ;

-- -----------------------------------------------------
-- Table `highlander`.`tb_venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `highlander`.`tb_venda` (
  `id_venda` INT NOT NULL AUTO_INCREMENT,
  `data` DATETIME NULL,
  `loja` INT NULL,
  `pdv` INT NULL,
  `status` VARCHAR(80) NULL,
  PRIMARY KEY (`id_venda`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `highlander`.`tb_item_venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `highlander`.`tb_item_venda` (
  `id_item_venda` INT NOT NULL,
  `id_venda` INT NOT NULL,
  `produto` VARCHAR(80) NULL,
  `preco_unitario` DOUBLE NULL,
  `desconto` DOUBLE NULL,
  INDEX `fk_tb_item_venda_tb_venda_idx` (`id_venda` ASC),
  CONSTRAINT `fk_tb_item_venda_tb_venda`
    FOREIGN KEY (`id_venda`)
    REFERENCES `highlander`.`tb_venda` (`id_venda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `highlander`.`tb_processamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `highlander`.`tb_processamento` (
  `id_processamento` INT NOT NULL AUTO_INCREMENT,
  `data` DATETIME NULL,
  `loja` INT NULL,
  `pdv` INT NULL,
  `nome_arquivo` VARCHAR(80) NULL,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`id_processamento`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
