create table if not exists duty(
    id             uuid primary key,
    apartment_id   uuid not null references apartment(id) on delete cascade,
    date           date not null
);

create table if not exists duty_tenant(
    duty_id     uuid references duty(id) on delete cascade,
    tenant_id   uuid references tenant(id) on delete cascade,
    PRIMARY KEY (duty_id, tenant_id)
)
