create table if not exists api_key
(
    pk    int auto_increment
        primary key,
    `key` text not null,
    constraint pk
        unique (pk)
);

