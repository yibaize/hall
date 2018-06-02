CREATE SCHEMA work;
use work;
create table user(
  id int primary key not null comment '主键，自增',
  account varchar(15) not null comment '账号',
  password varchar(15) null comment '密码',
  baseInfo json null comment '基础信息',
  signIn json null comment '每日签到',
  weath json null comment '财富',
  friends json null comment '好友',
  giftBag json null comment '成长礼包',
  task json null comment '任务'
)engine = InnoDB default charset = utf8 comment = '玩家数据';

CREATE TABLE adminInfo
(
  id int PRIMARY KEY NOT NULL AUTO_INCREMENT comment '自增主键',
  timer date default null comment '当天时间',
  topUp int default 0 comment '当天充值数',
  online json default null comment '当天平均在线人数',
  registNum int default null comment '当天注册人数',
  registId varchar(50) default 0 comment '当天最新注册的id',
  systemWeath json default null comment '系统财富记录'
)engine = InnoDB default charset = utf8 comment = '玩家数据';