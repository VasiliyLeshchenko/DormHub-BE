create table if not exists "order" (
    id           uuid         not null primary key,
    created_at   timestamp    not null default now(),
    title        varchar(100) not null,
    description  text         not null,
    status       varchar(50)  not null default 'NEW',
    creator_id   uuid         references tenant(id) ON DELETE SET NULL,
    assigned_id  uuid         references staffer(id) ON DELETE SET NULL
)
