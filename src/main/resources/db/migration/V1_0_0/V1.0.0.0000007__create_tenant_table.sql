create table if not exists tenant(
    id              uuid primary key references "user"(id),
    dorm_id         uuid references dorm(id) not null,
    block_id        uuid references block(id),
    room_id         uuid references room(id),
    check_in_date   date not null,
    check_out_date  date
);
