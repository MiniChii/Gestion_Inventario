/*CREATE DATABASE IF NOT EXISTS petclinic;

ALTER DATABASE petclinic
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

GRANT ALL PRIVILEGES ON petclinic.* TO pc@localhost IDENTIFIED BY 'pc';

USE petclinic;

CREATE TABLE IF NOT EXISTS vets (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  INDEX(last_name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS specialties (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(80),
  INDEX(name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS vet_specialties (
  vet_id INT(4) UNSIGNED NOT NULL,
  specialty_id INT(4) UNSIGNED NOT NULL,
  FOREIGN KEY (vet_id) REFERENCES vets(id),
  FOREIGN KEY (specialty_id) REFERENCES specialties(id),
  UNIQUE (vet_id,specialty_id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS types (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(80),
  INDEX(name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS owners (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  address VARCHAR(255),
  city VARCHAR(80),
  telephone VARCHAR(20),
  INDEX(last_name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS pets (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30),
  birth_date DATE,
  type_id INT(4) UNSIGNED NOT NULL,
  owner_id INT(4) UNSIGNED NOT NULL,
  INDEX(name),
  FOREIGN KEY (owner_id) REFERENCES owners(id),
  FOREIGN KEY (type_id) REFERENCES types(id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS visits (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  pet_id INT(4) UNSIGNED NOT NULL,
  visit_date DATE,
  description VARCHAR(255),
  FOREIGN KEY (pet_id) REFERENCES pets(id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS users (
  username VARCHAR(20) NOT NULL ,
  password VARCHAR(20) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (username)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS roles (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(20) NOT NULL,
  role varchar(20) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uni_username_role (role,username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username)
) engine=InnoDB;
*/

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema gestion_inventario
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema gestion_inventario
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gestion_inventario` DEFAULT CHARACTER SET utf8 ;
USE `gestion_inventario` ;

-- -----------------------------------------------------
-- Table `gestion_inventario`.`Especie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion_inventario`.`Especie` (
  `id` INT NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `etapa` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion_inventario`.`Estante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion_inventario`.`Estante` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `num_repisas` INT NULL,
  `max_volumen` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion_inventario`.`Cuadrante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion_inventario`.`Cuadrante` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `max_estante` INT NULL,
  `estante_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Cuadrante_Estante1_idx` (`estante_id` ASC) ,
  CONSTRAINT `fk_Cuadrante_Estante1`
    FOREIGN KEY (`estante_id`)
    REFERENCES `gestion_inventario`.`Estante` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion_inventario`.`Producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion_inventario`.`Producto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `unidad_medida` INT NULL,
  `precio` INT NULL,
  `contenido` INT NULL,
  `num_minimo` INT NULL,
  `especie_id` INT NOT NULL,
  `cuadrante_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Producto_Especie1_idx` (`especie_id` ASC) ,
  INDEX `fk_Producto_Cuadrante1_idx` (`cuadrante_id` ASC) ,
  CONSTRAINT `fk_Producto_Especie1`
    FOREIGN KEY (`especie_id`)
    REFERENCES `gestion_inventario`.`Especie` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Producto_Cuadrante1`
    FOREIGN KEY (`cuadrante_id`)
    REFERENCES `gestion_inventario`.`Cuadrante` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion_inventario`.`Movimiento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion_inventario`.`Movimiento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cantidad_salida` INT NULL,
  `fecha_salida` DATE NULL,
  `producto_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Movimiento_Producto1_idx` (`producto_id` ASC) ,
  CONSTRAINT `fk_Movimiento_Producto1`
    FOREIGN KEY (`producto_id`)
    REFERENCES `gestion_inventario`.`Producto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion_inventario`.`Proveedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion_inventario`.`Proveedor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rut` VARCHAR(10) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `telefono_contacto` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion_inventario`.`Lote`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion_inventario`.`Lote` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `num_unidades` INT NOT NULL,
  `fecha_ingreso` DATETIME NOT NULL,
  `fecha_vencimiento` DATETIME NULL,
  `proveedor_id` INT NOT NULL,
  `producto_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Lote_Proveedor1_idx` (`proveedor_id` ASC) ,
  INDEX `fk_Lote_Producto1_idx` (`producto_id` ASC) ,
  CONSTRAINT `fk_Lote_Proveedor1`
    FOREIGN KEY (`proveedor_id`)
    REFERENCES `gestion_inventario`.`Proveedor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Lote_Producto1`
    FOREIGN KEY (`producto_id`)
    REFERENCES `gestion_inventario`.`Producto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
