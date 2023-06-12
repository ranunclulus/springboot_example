select * from users;

-- select column
select first_name, last_name
from users
limit 6;

select first_name, age
from users
where age >= 18
limit 30;

-- 이름, 나이, 잔고, 전화 번호를 출력하는 select문 작성
select first_name, last_name, age, phone, balance
from users
limit 30;

select distinct country
from users;

-- select order by
select first_name, age, balance
from users
order by balance, age;

select *
from users
where age >= 30;