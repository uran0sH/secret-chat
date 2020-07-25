DROP TABLE `User`;
CREATE TABLE IF NOT EXISTS `user` (
    `id` VARCHAR(128) PRIMARY KEY,
    `username` VARCHAR(128),
    `password` VARCHAR(128),
    `gender` INT(1),
    `personality` VARCHAR(128),
    `face_image` VARCHAR(128),
    `face_image_big` VARCHAR(128),
	UNIQUE(`username`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE `friend`;
CREATE TABLE IF NOT EXISTS `friend` (
     `id` VARCHAR(128) PRIMARY KEY,
     `my_id` VARCHAR(128),
     `friend_id` VARCHAR(128)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

 DROP TABLE `friend_request`;
 CREATE TABLE IF NOT EXISTS `friend_request` (
     `id` VARCHAR(128) PRIMARY KEY,
     `send_user_id` VARCHAR(128),
     `receive_user_id` VARCHAR(128),
     `is_accept` TINYINT,
     `request_date_time` DATETIME
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

 CREATE TABLE IF NOT EXISTS `chat_msg` (
     `id` VARCHAR(128) PRIMARY KEY,
     `send_user_id` VARCHAR(128),
     `receive_user_id` VARCHAR(128),
     `msg` TEXT,
     `sign_flag` INT,
     `create_time` DATETIME
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;