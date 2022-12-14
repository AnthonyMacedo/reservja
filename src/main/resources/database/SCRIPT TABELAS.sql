-- SCRIPTS TABELAS RESERVJA --


CREATE TABLE `apartamento` (
  `ID_APARTAMENTO` int NOT NULL AUTO_INCREMENT,
  `NUM_QUARTO` int NOT NULL,
  `QTD_OCUPANTES` int DEFAULT NULL,
  `STATUS_APARTAMENTO` enum('Disponivel','Ocupado','Reservado') DEFAULT NULL,
  PRIMARY KEY (`ID_APARTAMENTO`),
  UNIQUE KEY  (`NUM_QUARTO`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


CREATE TABLE `CLIENTE` (
 `ID_CLIENTE` int NOT NULL AUTO_INCREMENT,
 `NOME` varchar(255) DEFAULT NULL,
 `CPF` varchar(255) DEFAULT NULL, 
 `TELEFONE` varchar(255) DEFAULT NULL,
 `CELULAR` varchar(255) DEFAULT NULL,
 `EMAIL` varchar(255) DEFAULT NULL,
 `SEXO` varchar(255) DEFAULT NULL,
 `DATA_NASCIMENTO` date DEFAULT NULL,
 `ENDERECO` int DEFAULT NULL,
 PRIMARY KEY (`ID_CLIENTE`),
  KEY (`ENDERECO`),
  CONSTRAINT FOREIGN KEY (`ENDERECO`) REFERENCES `endereco` (`ID_ENDERECO`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


CREATE TABLE `endereco` (
  `ID_ENDERECO` int NOT NULL AUTO_INCREMENT,
  `CEP` varchar(255) DEFAULT NULL,
  `LOGRADOURO` varchar(255) DEFAULT NULL,
  `BAIRRO` varchar(255) DEFAULT NULL,
  `COMPLEMENTO` varchar(255) DEFAULT NULL,
  `MUNICIPIO` varchar(255) DEFAULT NULL,
  `NUMERO` varchar(255) DEFAULT NULL,
  `UF` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_ENDERECO`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



CREATE TABLE `funcionario` (
  `ID_FUNC` int NOT NULL AUTO_INCREMENT,
  `NOME` varchar(255) DEFAULT NULL,
  `CPF` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `TELEFONE` varchar(255) DEFAULT NULL,
  `CELULAR` varchar(255) DEFAULT NULL,
  `DATA_NASCIMENTO` date DEFAULT NULL,
  `USUARIO` varchar(255) DEFAULT NULL,
  `SENHA` varchar(255) DEFAULT NULL,
  `ENDERECO` int DEFAULT NULL,
  PRIMARY KEY (`ID_FUNC`),
  UNIQUE KEY (`USUARIO`),
  KEY (`ENDERECO`),
  CONSTRAINT FOREIGN KEY (`ENDERECO`) REFERENCES `endereco` (`ID_ENDERECO`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `reservas` (
  `ID_RESERVA` int NOT NULL AUTO_INCREMENT,
  `CLIENTE` int DEFAULT NULL,
  `APARTAMENTO` int DEFAULT NULL,
  `DATA_INICIAL` date DEFAULT NULL,
  `DATA_FINAL` date DEFAULT NULL,
  PRIMARY KEY (`ID_RESERVA`),
  KEY (`APARTAMENTO`),
  KEY (`CLIENTE`),
  CONSTRAINT FOREIGN KEY (`CLIENTE`) REFERENCES `cliente` (`ID_CLIENTE`),
  CONSTRAINT FOREIGN KEY (`APARTAMENTO`) REFERENCES `apartamento` (`ID_APARTAMENTO`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
