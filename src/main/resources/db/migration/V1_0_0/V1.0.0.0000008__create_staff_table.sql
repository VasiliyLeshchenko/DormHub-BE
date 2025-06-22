create table if not exists staffer(
    id          uuid primary key references "user"(id),
    position    varchar(255) not null,
    dorm_id     uuid references dorm(id) not null
);
