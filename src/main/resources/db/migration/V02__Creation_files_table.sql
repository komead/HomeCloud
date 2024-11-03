create table files
(
    id bigint auto_increment,
    path varchar(256) not null,
    name varchar(32) not null,
    extension varchar(8) not null,
    size float not null,
    upload_time datetime default current_timestamp,
    primary key (id)
);