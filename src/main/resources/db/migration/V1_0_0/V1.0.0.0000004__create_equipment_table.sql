create table if not exists equipment(
    id                  uuid primary key,
    name                varchar(255) not null,
    inventory_number    int not null
);
