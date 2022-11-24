TRUNCATE TABLE `user`;
TRUNCATE TABLE `product`;
TRUNCATE TABLE `category`;


INSERT INTO `user` (`name`, `email`, `password`)
VALUES ('Test', 'Test@Example.com', 'Test'),
       ('Quinn', 'Quinnfontys@gmail.com', 'Password');

INSERT INTO `category` (name)
VALUES ('Apples'),
    ('Bananas');

INSERT INTO `product` (name)
VALUES ('Gala'),
       ('Golden Delicous');