alter table tenant
add column faculty varchar(255),
add column course int CHECK (course >= 0)