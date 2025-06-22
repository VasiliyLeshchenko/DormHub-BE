create table if not exists credential(
    id          uuid primary key references "user"(id),
    login       varchar(25) not null unique,
    password    varchar(255) not null
)
