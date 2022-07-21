CREATE TABLE `user` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `name` varchar(16) NOT NULL,
                           `age` int DEFAULT '0',
                           `email` varchar(32) DEFAULT NULL,
                           `create_time` datetime DEFAULT NULL,
                           `update_time` datetime DEFAULT NULL,
                           `mod_time` datetime DEFAULT NULL,
                           `del` int DEFAULT '0',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;