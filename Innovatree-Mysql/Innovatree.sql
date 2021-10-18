CREATE DATABASE Innovatree;
use Innovatree;

CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `aadhar_id` varchar(255) DEFAULT NULL,
  `total_amount_earned` int DEFAULT NULL,
  `total_amount_paid` int DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `encrypted_password` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `is_active` bit(1) NOT NULL,
  `is_email_verified` bit(1) NOT NULL,
  `is_enable` bit(1) NOT NULL,
  `is_mobile_no_verified` bit(1) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `no_of_plants` int DEFAULT NULL,
  `role` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `walet_balance` int DEFAULT NULL,
  `email_reset_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UK_9q63snka3mdh91as4io72espi` (`phone_number`),
  UNIQUE KEY `UK_6efs5vmce86ymf5q7lmvn2uuf` (`user_id`),
  UNIQUE KEY `UK_73pwr0wm0hfcapdja2q2a0q0h` (`aadhar_id`),
  UNIQUE KEY `UK_1n7iitap9ng850jm7pedl5iev` (`email_reset_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users_achievements` (
  `users_entity_id` bigint NOT NULL,
  `achievements_id` bigint NOT NULL,
  PRIMARY KEY (`users_entity_id`,`achievements_id`),
  KEY `FKnayy81n8g218qgc9srgnoqpo1` (`achievements_id`),
  CONSTRAINT `FKfeashs0lid2i9kaxth8osbm0l` FOREIGN KEY (`users_entity_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKnayy81n8g218qgc9srgnoqpo1` FOREIGN KEY (`achievements_id`) REFERENCES `achievements_entity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users_address_book` (
  `users_entity_id` bigint NOT NULL,
  `address_book_id` bigint NOT NULL,
  PRIMARY KEY (`users_entity_id`,`address_book_id`),
  KEY `FK2qrdk08rxwu6y7479h76umkvm` (`address_book_id`),
  CONSTRAINT `FK2qrdk08rxwu6y7479h76umkvm` FOREIGN KEY (`address_book_id`) REFERENCES `address_entity` (`id`),
  CONSTRAINT `FK50w82214xq0hvdko7hd8iwsrt` FOREIGN KEY (`users_entity_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `address_entity` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `address_id` varchar(255) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_nummber` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `zipcode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_85yfsbotum5uqjmjommu1fvi0` (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `achievements_entity` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `achievement_id` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image_url` longtext,
  `name` varchar(255) DEFAULT NULL,
  `plants_count` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_p8xdceifu2neoakrylqvc8ag1` (`achievement_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `is_top_selling` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `product_id` varchar(255) DEFAULT NULL,
  `purchases` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SELECT * FROM users;
SELECT * FROM users_achievements;
SELECT * FROM products;