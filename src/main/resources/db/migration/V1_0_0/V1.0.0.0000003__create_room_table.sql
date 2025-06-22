create table if not exists room(
    id          uuid primary key,
    number      int not null,
    suffix      char(1),
    dorm_id     uuid references dorm(id) not null,
    block_id    uuid references block(id)
);
