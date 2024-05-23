create sequence account_s;

-- 表
create table springboot3.account
(
    id        integer default nextval('account_s'::regclass) not null
        constraint account_pk
            primary key,
    user_name varchar,
    age       integer,
    birthday  date
);

-- 注释
comment on table springboot3.account is '测试账号';
comment on column springboot3.account.id is '主键';
comment on constraint account_pk on springboot3.account is '主键';
comment on column springboot3.account.user_name is '姓名';
comment on column springboot3.account.age is '年龄';
comment on column springboot3.account.birthday is '生日';
alter table springboot3.account
    owner to postgres;

