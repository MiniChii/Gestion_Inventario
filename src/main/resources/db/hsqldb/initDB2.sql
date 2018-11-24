-- MySQL Workbench Forward Engineering

-- -----------------------------------------------------
-- Table Especie
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Especie (
  id INT NOT NULL,
  nombre VARCHAR(45) NULL,
  etapa VARCHAR(45) NULL,
  PRIMARY KEY (id))
;


-- -----------------------------------------------------
-- Table Estante
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Estante (
  id INT NOT NULL AUTO_INCREMENT,
  num_repisas INT NULL,
  max_volumen INT NULL,
  PRIMARY KEY (id))
;


-- -----------------------------------------------------
-- Table Cuadrante
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Cuadrante (
  id INT NOT NULL AUTO_INCREMENT,
  max_estante INT NULL,
  estante_id INT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_Cuadrante_Estante1_idx (estante_id ASC) ,
  CONSTRAINT fk_Cuadrante_Estante1
    FOREIGN KEY (estante_id)
    REFERENCES Estante (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table Producto
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Producto (
  id INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(45) NOT NULL,
  unidad_medida INT NULL,
  precio INT NULL,
  contenido INT NULL,
  num_minimo INT NULL,
  especie_id INT NOT NULL,
  cuadrante_id INT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_Producto_Especie1_idx (especie_id ASC) ,
  INDEX fk_Producto_Cuadrante1_idx (cuadrante_id ASC) ,
  CONSTRAINT fk_Producto_Especie1
    FOREIGN KEY (especie_id)
    REFERENCES Especie (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Producto_Cuadrante1
    FOREIGN KEY (cuadrante_id)
    REFERENCES Cuadrante (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table Movimiento
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Movimiento (
  id INT NOT NULL AUTO_INCREMENT,
  cantidad_salida INT NULL,
  fecha_salida DATE NULL,
  producto_id INT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_Movimiento_Producto1_idx (producto_id ASC) ,
  CONSTRAINT fk_Movimiento_Producto1
    FOREIGN KEY (producto_id)
    REFERENCES Producto (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table Proveedor
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Proveedor (
  id INT NOT NULL AUTO_INCREMENT,
  rut VARCHAR(10) NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  telefono_contacto INT NOT NULL,
  PRIMARY KEY (id))
;


-- -----------------------------------------------------
-- Table Lote
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Lote (
  id INT NOT NULL AUTO_INCREMENT,
  num_unidades INT NOT NULL,
  fecha_ingreso DATETIME NOT NULL,
  fecha_vencimiento DATETIME NULL,
  proveedor_id INT NOT NULL,
  producto_id INT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_Lote_Proveedor1_idx (proveedor_id ASC) ,
  INDEX fk_Lote_Producto1_idx (producto_id ASC) ,
  CONSTRAINT fk_Lote_Proveedor1
    FOREIGN KEY (proveedor_id)
    REFERENCES Proveedor (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Lote_Producto1
    FOREIGN KEY (producto_id)
    REFERENCES Producto (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

