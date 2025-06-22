alter table if exists tenant
add column apartment_id uuid references apartment(id);