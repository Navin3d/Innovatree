CREATE DATABASE Innovatree;
use Innovatree;

SELECT * FROM user;
SELECT * FROM products;
SELECT * FROM achievements;

DELETE FROM products WHERE id = 2;

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `aadhar_id` varchar(255) DEFAULT NULL,
  `total_amount_earned` int DEFAULT NULL,
  `total_amount_paid` int DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `email_reset_token` varchar(255) DEFAULT NULL,
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_4bgmpi98dylab6qdvf9xyaxu4` (`phone_number`),
  UNIQUE KEY `UK_a3imlf41l37utmxiquukk8ajc` (`user_id`),
  UNIQUE KEY `UK_fflltr85u342l57d087mnfwj1` (`aadhar_id`),
  UNIQUE KEY `UK_i3y9rkvqirujxwpx2734y2hrs` (`email_reset_token`)
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `achievements` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `achievement_id` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `image_url` longtext NOT NULL,
  `name` varchar(255) NOT NULL,
  `no_of_plantings` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2pnt9lqntnc4mf3khvu9pelqd` (`achievement_id`),
  UNIQUE KEY `UK_t3dkkxlwrja4dbt5j6bgd407g` (`description`),
  UNIQUE KEY `UK_ktpif54u9a3ssn6rpxxqx6jvp` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `door_no` varchar(255) NOT NULL,
  `address_id` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `zip_code` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_scpdoha0q1mmbp5f9lojr3s9x` (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_address_book` (
  `users_entity_id` bigint NOT NULL,
  `address_book_id` bigint NOT NULL,
  PRIMARY KEY (`users_entity_id`,`address_book_id`),
  KEY `FKmb37uudg32g7vxw3ty3mr5fnb` (`address_book_id`),
  CONSTRAINT `FKa66jf9owsq1m70o0s9mi4kgnx` FOREIGN KEY (`users_entity_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKmb37uudg32g7vxw3ty3mr5fnb` FOREIGN KEY (`address_book_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `user_achievements` (
  `users_entity_id` bigint NOT NULL,
  `achievements_id` bigint NOT NULL,
  PRIMARY KEY (`users_entity_id`,`achievements_id`),
  KEY `FKd1899bx5ym2opnt5r7477ur38` (`achievements_id`),
  CONSTRAINT `FK3xyyx1lqdcry0g2ek7bwmlcf2` FOREIGN KEY (`users_entity_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKd1899bx5ym2opnt5r7477ur38` FOREIGN KEY (`achievements_id`) REFERENCES `achievements` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `address_users` (
  `address_entity_id` bigint NOT NULL,
  `users_id` bigint NOT NULL,
  PRIMARY KEY (`address_entity_id`,`users_id`),
  KEY `FKqgtytujw3se5lgom2i9ppp8rd` (`users_id`),
  CONSTRAINT `FKee6w5uuuk82wgtytgbchegia3` FOREIGN KEY (`address_entity_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FKqgtytujw3se5lgom2i9ppp8rd` FOREIGN KEY (`users_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `achievements_user` (
  `achievements_entity_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`achievements_entity_id`,`user_id`),
  KEY `FKoishbwymfc79luk6a7xk20kbs` (`user_id`),
  CONSTRAINT `FKby2v0rt7yqf785os7txim0l53` FOREIGN KEY (`achievements_entity_id`) REFERENCES `achievements` (`id`),
  CONSTRAINT `FKoishbwymfc79luk6a7xk20kbs` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
