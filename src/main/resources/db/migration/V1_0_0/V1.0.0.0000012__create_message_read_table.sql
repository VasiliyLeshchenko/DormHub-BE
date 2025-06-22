create table if not exists message(
    id          uuid primary key,
    user_id     uuid references "user"(id) not null,
    chat_id     uuid references chat(id) not null,
    text        text not null,
    send_time   timestamptz not null,
    is_read     boolean not null default false
);