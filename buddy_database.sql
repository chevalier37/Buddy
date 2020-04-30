CREATE DATABASE Buddy CHARACTER SET 'utf8';

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `email` varchar(256) NOT NULL,
  `password` varchar(256) NOT NULL,
  `wallet` decimal(8,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `first_last_unique` (`firstname`,`lastname`),
  UNIQUE KEY `email_unique` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `from_id` int NOT NULL,
  `to_id` int NOT NULL,
  `amount` decimal(8,2) NOT NULL,
  `description` varchar(256) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `from_id` (`from_id`),
  KEY `to_id` (`to_id`),
  CONSTRAINT `from_id` FOREIGN KEY (`from_id`) REFERENCES `user` (`id`),
  CONSTRAINT `to_id` FOREIGN KEY (`to_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `connections` (
  `id` int NOT NULL AUTO_INCREMENT,
  `from_id` int NOT NULL,
  `to_id` int NOT NULL,
  PRIMARY KEY (`id`,`from_id`,`to_id`),
  KEY `to_id_connection` (`to_id`),
  KEY `from_id_connection` (`from_id`),
  CONSTRAINT `from_id1` FOREIGN KEY (`from_id`) REFERENCES `user` (`id`),
  CONSTRAINT `to_id1` FOREIGN KEY (`to_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `tranfert_bank_account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `amount` decimal(8,2) NOT NULL,
  `type` tinyint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



