show variables like 'character_set_client';
show databases ;
create database if not exists work;
use work;
create table user(
  id int primary key auto_increment not null comment '主键，自增',
  account varchar(15) not null comment '账号',
  password varchar(15) null comment '密码',
  baseInfo json null comment '基础信息',
  signIn json null comment '每日签到',
  weath json null comment '财富',
  friends json null comment '好友',
  giftBag json null comment '成长礼包',
  task json null comment '任务'
)engine = InnoDB default charset = utf8 comment = '玩家数据';
alter table user change info baseInfo json;
show variables like '%char%';
set character_set_server=utf8;
set character_set_filesystem=utf8;
truncate table user;