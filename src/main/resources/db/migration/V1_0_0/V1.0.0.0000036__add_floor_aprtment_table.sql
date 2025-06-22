alter table apartment
add column floor int not null check(floor >=  0) default 0;