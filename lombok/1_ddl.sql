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
)