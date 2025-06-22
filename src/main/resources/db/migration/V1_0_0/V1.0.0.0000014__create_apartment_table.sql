create table if not exists apartment(
    id          uuid primary key,
    number      int not null,
    suffix      char(1),
    type        varchar(255),
    dorm_id     uuid references dorm(id) not null,
    parent_id   uuid references apartment(id)
);