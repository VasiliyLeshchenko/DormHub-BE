create table if not exists message_read_status(
    id          uuid primary key,
    user_id     uuid references "user"(id) not null,
    message_id  uuid references message(id) not null,
    read_time   timestamptz not null,
    is_read     boolean not null default false
);