alter table if exists tenant
drop column if exists block_id, drop column if exists room_id;

alter table if exists equipment_location
drop column if exists block_id, drop column if exists room_id;

drop table room;
drop table block;
