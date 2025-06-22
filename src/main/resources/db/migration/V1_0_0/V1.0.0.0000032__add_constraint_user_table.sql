alter table "user"
add constraint sex_constraint CHECK ("user".sex in ('M', 'F'));