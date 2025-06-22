create table if not exists chat(
   id           uuid primary key,
   name         varchar(255) not null,
   type         varchar(255) not null,
   sector_id    uuid
);
