alter table equipment_location
drop column dorm_id,
add column apartment_id uuid references apartment(id) not null