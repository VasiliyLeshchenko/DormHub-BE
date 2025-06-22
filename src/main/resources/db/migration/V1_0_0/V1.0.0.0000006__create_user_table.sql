create table if not exists "user"(
    id              uuid primary key,
    first_name      varchar(255) not null,
    last_name       varchar(255) not null,
    father_name     varchar(255),
    sex             char(1) not null,
    birthdate       date not null,
    phone           varchar(255) not null,
    email           varchar(255) not null
);
