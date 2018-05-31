CREATE SCHEMA work;
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
CREATE TABLE adminInfo
(
  id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  timer date,
  topUp int,
  online json,
  registNum int,
  registId int,
  systemWeath json
)engine = InnoDB default charset = utf8 comment = '玩家数据';