alter table apartment
add constraint apartment_uq UNIQUE (number, suffix, type, dorm_id);