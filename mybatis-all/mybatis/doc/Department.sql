CREATE TABLE `department` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `name` varchar(16) NOT NULL COMMENT '姓名',
                              `age` int DEFAULT (0) COMMENT '年龄',
                              `gender` int DEFAULT (0) COMMENT '性别 0 男 1 女',
                              `team` varchar(32) DEFAULT NULL COMMENT '组织',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;