CREATE TABLE `subject` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `did` varchar(16) NOT NULL COMMENT 'department id',
                           `course` varchar(16) DEFAULT '0' COMMENT '课程',
                           `grade` int DEFAULT (0) COMMENT '分数',
                           `ranking` int DEFAULT '0' COMMENT '排名',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;