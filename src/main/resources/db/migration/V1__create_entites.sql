CREATE TABLE IF NOT EXISTS `customer` (

  `id` bigint NOT NULL AUTO_INCREMENT,
  `last_name` varchar(255) NOT NULL,
  `street` varchar(255) DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  `cpf` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cwtgtb16nebxu54elskdjei66` (`cpf`),
  UNIQUE KEY `UK_dwk6cx0afu8bs9o4t536v1j5v` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `credit` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `credit_code` binary(16) NOT NULL,
  `credit_value` decimal(38,2) NOT NULL,
  `day_first_installment` date NOT NULL,
  `number_of_installments` int NOT NULL,
  `status` smallint DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fs9sb9vxu86umfd06ybk7l6tv` (`credit_code`),
  KEY `FKosxy6eoxva5hh9tschjwx1nf0` (`customer_id`),
  CONSTRAINT `FKosxy6eoxva5hh9tschjwx1nf0` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ;
