CREATE TABLE IF NOT EXISTS `especie`(
`nmid` int NOT NULL AUTO_INCREMENT,
`nomEsp` varchar(50) NOT NULL,
 PRIMARY KEY (`nmid`)
);

CREATE TABLE IF NOT EXISTS `raza`(
`nmid` int NOT NULL AUTO_INCREMENT,
`nomRaz` varchar (50) NOT NULL,
PRIMARY KEY (`nmid`)
 );


CREATE TABLE IF NOT EXISTS `dueno`(
 `nmid`  int NOT NULL AUTO_INCREMENT,
 `tipid`  VARCHAR(50)  NOT NULL,
 `nmtid` int NOT NULL,
 `nomD`  varchar (50) NOT NULL,
 `ciudad` varchar (40) NOT NULL,
 `direccion` varchar (40) NOT NULL,
 `telefono` INT (30)  NOT NULL,
  PRIMARY KEY (`nmid`)
 );

CREATE TABLE IF NOT EXISTS `paciente`(
  `nmid` int NOT NULL AUTO_INCREMENT,
  `nmid_dueno` int NOT NULL,
  `nmid_raza`  int NOT NULL,
  `nmid_esp`  int NOT NULL,
  `nompet` varchar(30) NOT NULL,
  `fecNac` date DEFAULT NULL,
  `fecReg` date DEFAULT NULL,
  PRIMARY KEY (`nmid`)
);


ALTER TABLE `paciente` ADD CONSTRAINT  FK_razaespecie FOREIGN KEY (nmid_esp) REFERENCES especie (nmid);
ALTER TABLE `paciente` ADD CONSTRAINT  FK_pacientedueño  FOREIGN KEY (nmid_dueno) REFERENCES dueno (nmid);
ALTER TABLE `paciente` ADD CONSTRAINT  FK_pacienteraza FOREIGN KEY (nmid_raza) REFERENCES raza (nmid);