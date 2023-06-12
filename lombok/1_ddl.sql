create table students (
    id integer,
    name varchar(32),
    email text
);

create table students_2 (
    id integer,
    username text,
    first_name text,
    last_name text,
    email text
);

-- not null constraints
create table students_not_null (
    id integer,
    username text,
    first_name text,
    last_name text,
    email text not null -- email은 null이 될 수 없다
);

-- unique constraints
create table students_unique (
    id integer,
    username text unique,
    first_name text,
    last_name text,
    email text not null
);

-- unique constraints
create table students_pks (
    id integer primary key autoincrement, -- id가 PK이고 자동으로 하나씩 올리면서 생성
    username text unique,
    first_name text,
    last_name text,
    email text not null
);

create table students_final (
    id integer primary key autoincrement,
    username text unique,
    first_name text,
    last_name text,
    email text not null
);

-- alter table
alter table students_2 rename to students_temp;
alter table students_temp rename column first_name to given_name;
alter table students_temp rename column last_name to sur_name;

alter table students_temp add column phone varchar(64);
alter table students_temp add column phone_2 varchar(64) not null default '010-0000-0000';
alter table students_temp drop column phone;

-- 거의 하지 말아야 할 것 drop table
drop table students_temp;


-- D