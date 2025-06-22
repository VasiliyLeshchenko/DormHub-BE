create table if not exists dorm(
    id          uuid primary key,
    name        varchar(255) not null,
    type        varchar(255) not null,
    address     varchar(255) not null,
    postal_code int not null,
    phone       varchar(255) not null
);
