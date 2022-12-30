CREATE TABLE `tb_user_info`
(
    `id`          int PRIMARY KEY AUTO_INCREMENT,
    `avatar`      varchar(255) DEFAULT NULL,
    `nickname`    varchar(23)  DEFAULT NULL,
    `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `pwd`         varchar(63)  DEFAULT NULL,
    `username`    varchar(15)  DEFAULT NULL,
    UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 default CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPRESSED comment="用户表";


CREATE TABLE `tb_friend`
(
    `id`        int PRIMARY KEY AUTO_INCREMENT,
    `user_id`   int not NULL,
    `friend_id` int not NULL,
    `nickname1` varchar(255) DEFAULT NULL,
    `nickname2` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 default CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPRESSED comment="好友表";


CREATE TABLE `tb_message`
(
    `id`           bigint PRIMARY KEY AUTO_INCREMENT,
    `from_user_id` int         DEFAULT NULL,
    `user_id`      int         DEFAULT NULL,
    `group_id`     int         DEFAULT NULL,
    `msg`          varchar(64) DEFAULT NULL,
    `msg_type`     tinyint     DEFAULT NULL,
    `create_time`  timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=1 default CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPRESSED comment="消息表";
