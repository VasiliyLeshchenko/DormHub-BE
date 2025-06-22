create table if not exists block(
    id      uuid primary key,
    number  int not null,
    dorm_id uuid references dorm(id) not null
);
