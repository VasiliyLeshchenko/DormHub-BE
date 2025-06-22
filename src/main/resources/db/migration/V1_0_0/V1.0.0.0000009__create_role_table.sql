create table if not exists role(
    id      uuid primary key,
    name    varchar(255) not null
);

create table if not exists user_role(
    user_id uuid references "user"(id),
    role_id uuid references role(id),
    PRIMARY KEY (user_id, role_id)
);
