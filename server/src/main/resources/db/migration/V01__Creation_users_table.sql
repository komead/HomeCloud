create table users
(
    id int auto_increment,
    username varchar(16) unique not null,
    password varchar(128) not null,
    email varchar(32) unique not null,
    role varchar(16),
    primary key (id)
);