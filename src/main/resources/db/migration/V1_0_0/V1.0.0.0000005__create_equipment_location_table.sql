create table if not exists equipment_location(
    id              uuid primary key,
    equipment_id    uuid references equipment(id) not null,
    dorm_id         uuid references dorm(id),
    block_id        uuid references block(id),
    room_id         uuid references room(id),
    quantity        int not null check (quantity > 0)
);
