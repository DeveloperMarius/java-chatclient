CREATE TABLE `users` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(200) NOT NULL,
    `password` VARCHAR(200) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `groups` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(200) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `messages` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `user` INT NOT NULL,
    `content` TEXT NOT NULL,
    `sent` BIGINT NOT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO `groups` VALUES (NULL, 'Allgemein');

INSERT INTO `users` VALUES (1, 'Marius', 'password');
INSERT INTO `messages` VALUES (NULL, 1, 'Hallo', 1631686864408);
INSERT INTO `users` VALUES (2, 'Moritz', 'password');
INSERT INTO `messages` VALUES (NULL, 2, 'Hallo', 1631686864408);
INSERT INTO `users` VALUES (3, 'Emil', 'password');
INSERT INTO `messages` VALUES (NULL, 3, 'Hallo', 1631686864408);
INSERT INTO `users` VALUES (4, 'Sebastian', 'password');
INSERT INTO `messages` VALUES (NULL, 4, 'Hallo', 1631686864408);