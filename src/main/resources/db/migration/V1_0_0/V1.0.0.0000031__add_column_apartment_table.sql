alter table apartment
add column size int not null CHECK(size >= 0) default 0;