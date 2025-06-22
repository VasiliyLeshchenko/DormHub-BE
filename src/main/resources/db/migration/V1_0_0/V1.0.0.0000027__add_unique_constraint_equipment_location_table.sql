alter table equipment_location
add constraint apartment_equipment_uq unique (apartment_id, equipment_id);