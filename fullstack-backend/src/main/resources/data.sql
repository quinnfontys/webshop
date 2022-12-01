set foreign_key_checks=0;
TRUNCATE TABLE `user`;
TRUNCATE TABLE `product`;
TRUNCATE TABLE `category`;
TRUNCATE TABLE `cart`;
TRUNCATE TABLE `cart_product`;
set foreign_key_checks=1;


INSERT INTO `user` (`email`, `password`, `role`)
VALUES ('Test@Example.com', 'Test' , 1),
       ('Quinnfontys@gmail.com', 'Password', 0);

INSERT INTO `category` (`name`, `description`)
VALUES ('Apples', "This is the first item that came to mind, and suddenly the only things I'm filing this database with is fruit for some reason"),
       ('Bananas', 'These are yellow-ish fruits that obtain their curved shape due to their immense desire for sunlight');

INSERT INTO `product` (`name`, `category_id`, `price`, `description`, `image_file`)
VALUES ('Gala', 1, 2.99, 'An apple :D', 'gala.jpg'),
       ('Golden Delicious', 1, 2.89, 'Delicious apple', 'apple.png'),
       ('Pink Lady', 1, 1.89, 'Despite its name, not pink nor a lady', 'apple.png'),
       ('Fuji', 1, 2.56, 'Many people think Fuji apples are named after Mount Fuji, but the name actually comes from Fujisaki, the Japanese town where they were developed. One large Fuji can give you 15 per cent of your daily vitamin C. Fujis are one of the best apples to freeze.', 'apple.png'),
       ('Granny Smith', 1, 0.12, 'The Granny Smith, also known as a green apple or sour apple, is an apple cultivar which originated in Australia in 1868.[1] It is named after Maria Ann Smith, who propagated the cultivar from a chance seedling. The tree is thought to be a hybrid of Malus sylvestris, the European wild apple, with the domesticated apple Malus domestica as the polleniser.[citation needed] The fruit is hard, firm and with a light green skin and crisp, juicy flesh. The flavour is tart and acidic. It remains firm when baked, making it a popular cooking apple[2] used in pies, where it can be sweetened. The apple goes from being completely green to turning yellow when overripe.[3] The US Apple Association reported in 2019 that the Granny Smith was the third most popular apple in the United States of America.[4]', 'apple.png'),
       ('Honey Crisp', 1, 8.00, 'another apple', 'apple.png'),
       ('Cavendish', 2, 9.10, 'this is a banana', 'banana.jpg'),
       ('Gros Michel', 2, 2.12, 'this is a banana' , 'banana.jpg');

INSERT INTO `cart` (`id`, `user_id`)
VALUES (1, 1),
       (2, 2);

INSERT INTO `cart_product` (`quantity`, `cart_id`, `product_id`)
VALUES (2, 1, 4),
       (3, 1, 2),
       (1, 1, 5),
       (2, 1, 8),
       (1, 2, 3),
       (3, 2, 8);