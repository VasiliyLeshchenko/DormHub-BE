create table if not exists "user"(
    id          uuid primary key,
    full_name  varchar(255) not null
);

create table if not exists user_chat(
    user_id     uuid references "user"(id),
    chat_id     uuid references chat(id),
    PRIMARY KEY (user_id, chat_id)
);