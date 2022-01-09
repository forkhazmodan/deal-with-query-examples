/*
This SQL script is for creating organization related tables including:
    - users

Also for seeding the following tables:
    - users
*/

SET @current = NOW();

CREATE TABLE users
(
    id         int UNSIGNED NOT NULL  AUTO_INCREMENT  PRIMARY KEY,
    age        int,
    email      varchar(255) NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name  varchar(255) NOT NULL,
    created_at datetime     NOT NULL,
    updated_at datetime
);

INSERT INTO users (age, email, first_name, last_name, created_at, updated_at) VALUES
(18, 'user1@gmail.com', 'User1FirstName', 'User1LastName', @current - INTERVAL 1 DAY, @current- INTERVAL 1 DAY),
(21, 'user3@gmail.com', 'User3FirstName', 'User3LastName', @current- INTERVAL 2 DAY, @current- INTERVAL 2 DAY),
(23, 'user4@gmail.com', 'User4FirstName', 'User4LastName', @current - INTERVAL 3 DAY, @current - INTERVAL 3 DAY),
(25, 'user5@gmail.com', 'User5FirstName', 'User5LastName', @current - INTERVAL 4 DAY, @current - INTERVAL 4 DAY),
(27, 'user6@gmail.com', 'User6FirstName', 'User6LastName', @current - INTERVAL 5 DAY, @current - INTERVAL 5 DAY),
(30, 'user7@gmail.com', 'User7FirstName', 'User7LastName', @current - INTERVAL 6 DAY, @current - INTERVAL 6 DAY),
(32, 'user8@gmail.com', 'User8FirstName', 'User8LastName', @current - INTERVAL 7 DAY, @current - INTERVAL 7 DAY),
(35, 'user9@gmail.com', 'User9FirstName', 'User9LastName', @current - INTERVAL 8 DAY, @current - INTERVAL 8 DAY),
(37, 'user10@gmail.com', 'User10FirstName', 'User10LastName', @current - INTERVAL 9 DAY, @current - INTERVAL 9 DAY),
(40, 'user11@gmail.com', 'User11FirstName', 'User11LastName', @current - INTERVAL 10 DAY, @current - INTERVAL 10 DAY),
(42, 'user12@gmail.com', 'User12FirstName', 'User12LastName', @current - INTERVAL 11 DAY, @current - INTERVAL 11 DAY);
