drop table if exists `wx_article`;
create table `wx_article`  (
  `id` int(10) unsigned not null auto_increment,
  `type` tinyint(1) null default 1 comment '文章类型[1:普通文章,5:帮助中心]',
  `title` varchar(1024) character set utf8 not null comment '标题',
  `summary` varchar(1024) character set utf8 null default null comment '文章摘要',
  `tags` varchar(255) character set utf8 null default null comment '文章标签',
  `content` longtext character set utf8 null comment '内容',
  `category` varchar(25) character set utf8 null default null comment '分类',
  `sub_category` varchar(25) character set utf8 null default null comment '二级目录',
  `create_time` datetime(0) not null default current_timestamp(0) comment '创建时间',
  `update_time` datetime(0) null default null comment '修改时间',
  `open_count` int(11) null default 0 comment '点击次数',
  `start_time` datetime(0) null default current_timestamp(0) comment '生效时间',
  `end_time` datetime(0) null default null comment '失效时间',
  `target_link` varchar(255) character set utf8 null default null comment '指向外链',
  `image` varchar(255) character set utf8 null default null comment '文章首图',
  primary key (`id`) using btree,
  unique index `idx_title`(`title`) using btree comment '标题不得重复'
) engine = innodb auto_increment = 337 character set = utf8 comment = 'cms文章中心' row_format = dynamic;

-- ----------------------------
-- table structure for wx_account
-- ----------------------------
drop table if exists `wx_account`;
create table `wx_account`  (
  `appid` char(20) character set utf8 not null comment 'appid',
  `name` varchar(50) character set utf8 not null comment '公众号名称',
  `type` tinyint(1) unsigned null default 1 comment '账号类型',
  `verified` tinyint(1) unsigned null default 1 comment '认证状态',
  `secret` char(32) character set utf8 not null comment 'appsecret',
  `token` varchar(32) character set utf8 null default null comment 'token',
  `aes_key` varchar(43) character set utf8 null default null comment 'aeskey',
  primary key (`appid`) using btree
) engine = innodb character set = utf8 comment = '公众号账号' row_format = dynamic;

-- ----------------------------
-- table structure for wx_msg
-- ----------------------------
drop table if exists `wx_msg`;
create table `wx_msg`  (
  `id` bigint(20) not null auto_increment comment '主键',
  `appid` char(20) character set utf8 not null comment 'appid',
  `openid` varchar(32) character set utf8 not null comment '微信用户id',
  `in_out` tinyint(1) unsigned null default null comment '消息方向',
  `msg_type` char(25) character set utf8 null default null comment '消息类型',
  `detail` varchar(255) null comment '消息详情',
  `create_time` datetime(0) null default current_timestamp(0) comment '创建时间',
  primary key (`id`) using btree,
  index `idx_appid`(`appid`) using btree comment 'appid'
) engine = innodb auto_increment = 9 character set = utf8mb4 comment = '微信消息' row_format = dynamic;

-- ----------------------------
-- table structure for wx_msg_reply_rule
-- ----------------------------
drop table if exists `msg_reply_rule`;
drop table if exists `wx_msg_reply_rule`;
create table `wx_msg_reply_rule`  (
  `rule_id` int(11) not null auto_increment,
  `appid` char(20) character set utf8 null default '' comment 'appid',
  `rule_name` varchar(20) character set utf8 not null comment '规则名称',
  `match_value` varchar(200) character set utf8 not null comment '匹配的关键词、事件等',
  `exact_match` tinyint(1) not null default 0 comment '是否精确匹配',
  `reply_type` varchar(20) character set utf8 not null default '1' comment '回复消息类型',
  `reply_content` varchar(1024) character set utf8 not null comment '回复消息内容',
  `status` tinyint(1) not null default 1 comment '规则是否有效',
  `desc` varchar(255) character set utf8 null default null comment '备注说明',
  `effect_time_start` time(0) null default '00:00:00' comment '生效起始时间',
  `effect_time_end` time(0) null default '23:59:59' comment '生效结束时间',
  `priority` int(3) unsigned null default 0 comment '规则优先级',
  `update_time` datetime(0) not null default current_timestamp(0) on update current_timestamp(0) comment '修改时间',
  primary key (`rule_id`) using btree,
  index `idx_appid`(`appid`) using btree comment 'appid'
) engine = innodb auto_increment = 36 character set = utf8 comment = '自动回复规则' row_format = dynamic;

-- ----------------------------
-- records of wx_msg_reply_rule
-- ----------------------------
insert into `wx_msg_reply_rule` values (1, '', '关注公众号', 'subscribe', 0, 'text', '你好，欢迎关注！\n<a href=\"https://github.com/niefy\">点击链接查看我的主页</a>', 1, '关注回复', '00:00:00', '23:59:59', 0, '2020-05-20 15:15:00');

-- ----------------------------
-- table structure for wx_msg_template
-- ----------------------------
drop table if exists `msg_template`;
drop table if exists `wx_msg_template`;
create table `wx_msg_template`  (
  `id` bigint(10) unsigned not null auto_increment comment 'id',
  `appid` char(20) character set utf8 not null comment 'appid',
  `template_id` varchar(100) character set utf8 not null comment '公众号模板id',
  `name` varchar(50) character set utf8 null default null comment '模版名称',
  `title` varchar(20) character set utf8 null default null comment '标题',
  `content` text character set utf8 null comment '模板内容',
  `data` varchar(255) null comment '消息内容',
  `url` varchar(255) character set utf8 null default null comment '链接',
  `miniprogram` varchar(255) null comment '小程序信息',
  `status` tinyint(1) unsigned not null comment '是否有效',
  `update_time` datetime(0) not null default current_timestamp(0) on update current_timestamp(0) comment '修改时间',
  primary key (`id`) using btree,
  unique index `idx_name`(`name`) using btree comment '模板名称',
  index `idx_status`(`status`) using btree comment '模板状态',
  index `idx_appid`(`appid`) using btree comment 'appid'
) engine = innodb auto_increment = 62 character set = utf8 comment = '消息模板' row_format = dynamic;


-- ----------------------------
-- table structure for wx_qr_code
-- ----------------------------
drop table if exists `wx_qr_code`;
create table `wx_qr_code`  (
  `id` bigint(20) not null auto_increment comment 'id',
  `appid` char(20) character set utf8 not null comment 'appid',
  `is_temp` tinyint(1) null default null comment '是否为临时二维码',
  `scene_str` varchar(64) character set utf8mb4 null default null comment '场景值id',
  `ticket` varchar(255) character set utf8mb4 null default null comment '二维码ticket',
  `url` varchar(255) character set utf8mb4 null default null comment '二维码图片解析后的地址',
  `expire_time` datetime(0) null default null comment '该二维码失效时间',
  `create_time` datetime(0) null default null comment '该二维码创建时间',
  primary key (`id`) using btree,
  index `idx_appid`(`appid`) using btree comment 'appid'
) engine = innodb auto_increment = 4 character set = utf8mb4 comment = '公众号带参二维码' row_format = dynamic;

-- ----------------------------
-- table structure for wx_template_msg_log
-- ----------------------------
drop table if exists `wx_template_msg_log`;
create table `wx_template_msg_log`  (
  `log_id` bigint(11) not null auto_increment comment 'id',
  `appid` char(20) character set utf8 not null comment 'appid',
  `touser` varchar(50) character set utf8 null default null comment '用户openid',
  `template_id` varchar(50) character set utf8 null default null comment 'templateid',
  `data` varchar(255) null comment '消息数据',
  `url` varchar(255) character set utf8 null default null comment '消息链接',
  `miniprogram` varchar(255) null comment '小程序信息',
  `send_time` datetime(0) null default null comment '发送时间',
  `send_result` varchar(255) character set utf8 null default null comment '发送结果',
  primary key (`log_id`) using btree,
  index `idx_appid`(`appid`) using btree comment 'appid'
) engine = innodb auto_increment = 116250 character set = utf8 comment = '微信模版消息发送记录' row_format = dynamic;

-- ----------------------------
-- table structure for wx_user
-- ----------------------------
drop table if exists `wx_user`;
create table `wx_user`  (
  `openid` varchar(50) character set utf8mb4 not null comment '微信openid',
  `appid` char(20) character set utf8 not null comment 'appid',
  `phone` char(11) character set utf8mb4 null default null comment '手机号',
  `nickname` varchar(50) character set utf8mb4 null default null comment '昵称',
  `sex` tinyint(4) null default null comment '性别(0-未知、1-男、2-女)',
  `city` varchar(20) character set utf8mb4 null default null comment '城市',
  `province` varchar(255) character set utf8mb4 null default null comment '省份',
  `headimgurl` varchar(1000) character set utf8mb4 null default null comment '头像',
  `subscribe_time` datetime(0) null default null comment '订阅时间',
  `subscribe` tinyint(3) unsigned null default 1 comment '是否关注',
  `unionid` varchar(50) character set utf8mb4 null default null comment 'unionid',
  `remark` varchar(255) character set utf8mb4 null default null comment '备注',
  `tagid_list` varchar(1000) null comment '标签id列表',
  `subscribe_scene` varchar(50) character set utf8mb4 null default null comment '关注场景',
  `qr_scene_str` varchar(64) character set utf8mb4 null default null comment '扫码场景值',
  primary key (`openid`) using btree,
  index `idx_unionid`(`unionid`) using btree comment 'unionid',
  index `idx_appid`(`appid`) using btree comment 'appid'
) engine = innodb character set = utf8mb4 comment = '用户表' row_format = dynamic;


insert into `sys_menu` values ('16', '0', '微信管理', null, null, 0, 'admin', 1,1);
insert into `sys_menu` values ('1601', '16', '公众号菜单', 'wx/wx-menu', '', 1, 'log', 0,1);
insert into `sys_menu` values ('160101', '1601', '更新公众号菜单', '', 'wx:menu:save', 2, 'admin', 0,1);

insert into `sys_menu` values ('1602', '16', '素材管理', 'wx/wx-assets', '', 1, 'admin', 0,1);
insert into `sys_menu` values ('160201', '1602', '查看', '', 'wx:wxassets:list', 2, 'admin', 0,1);
insert into `sys_menu` values ('160202', '1602', '新增修改', '', 'wx:wxassets:save', 2, 'admin', 0,1);
insert into `sys_menu` values ('160203', '1602', '删除', '', 'wx:wxassets:delete', 2, 'admin', 0,1);

insert into `sys_menu` values ('1603', '16', '自动回复规则', 'wx/msg-reply-rule', null, 1, 'admin', 6,1);
insert into `sys_menu` values ('160301', '1603', '查看', null, 'wx:msgreplyrule:list,wx:msgreplyrule:info', 2, 'admin', 6,1);
insert into `sys_menu` values ('160302', '1603', '新增', null, 'wx:msgreplyrule:save', 2, 'admin', 6,1);
insert into `sys_menu` values ('160303', '1603', '修改', null, 'wx:msgreplyrule:update', 2, 'admin', 6,1);
insert into `sys_menu` values ('160304', '1603', '删除', null, 'wx:msgreplyrule:delete', 2, 'admin', 6,1);

insert into `sys_menu` values ('1604', '16', '模板消息', 'wx/msg-template', null, 1, 'admin', 6,1);
insert into `sys_menu` values ('160401', '1604', '查看', null, 'wx:msgtemplate:list,wx:msgtemplate:info', 2, 'admin', 6,1);
insert into `sys_menu` values ('160402', '1604', '新增', null, 'wx:msgtemplate:save', 2, 'admin', 6,1);
insert into `sys_menu` values ('160403', '1604', '修改', null, 'wx:msgtemplate:update', 2, 'admin', 6,1);
insert into `sys_menu` values ('160404', '1604', '删除', null, 'wx:msgtemplate:delete', 2, 'admin', 6,1);

insert into `sys_menu` values ('1605', '16', '带参二维码', 'wx/wx-qrcode', null, 1, 'admin', 6,1);
insert into `sys_menu` values ('160501', '1605', '查看', null, 'wx:wxqrcode:list,wx:wxqrcode:info', 2, 'admin', 6,1);
insert into `sys_menu` values ('160502', '1605', '新增', null, 'wx:wxqrcode:save', 2, 'admin', 6,1);
insert into `sys_menu` values ('160503', '1605', '删除', null, 'wx:wxqrcode:delete', 2, 'admin', 6,1);

insert into `sys_menu` values ('1606', '16', '粉丝管理', 'wx/wx-user', null, 1, 'admin', 6,1);
insert into `sys_menu` values ('160601', '1606', '查看', null, 'wx:wxuser:list,wx:wxuser:info', 2, 'admin', 6,1);
insert into `sys_menu` values ('160602', '1606', '删除', null, 'wx:wxuser:delete', 2, 'admin', 6,1);
insert into `sys_menu` values ('160603', '1606', '同步', '', 'wx:wxuser:save', 2, 'admin', 6,1);

insert into `sys_menu` values ('1607', '16', '公众号消息', 'wx/wx-msg', null, 1, 'admin', 6,1);
insert into `sys_menu` values ('160701', '1607', '查看', null, 'wx:wxmsg:list,wx:wxmsg:info', 2, 'admin', 6,1);
insert into `sys_menu` values ('160702', '1607', '新增', null, 'wx:wxmsg:save', 2, 'admin', 6,1);
insert into `sys_menu` values ('160703', '1607', '删除', null, 'wx:wxmsg:delete', 2, 'admin', 6,1);

insert into `sys_menu` values ('1608', '16', '公众号账号', 'wx/wx-account', null, 1, 'admin', 6,1);
insert into `sys_menu` values ('160801', '1608', '查看', null, 'wx:wxaccount:list,wx:wxaccount:info', 2, 'admin', 6,1);
insert into `sys_menu` values ('160802', '1608', '新增', null, 'wx:wxaccount:save', 2, 'admin', 6,1);
insert into `sys_menu` values ('160803', '1608', '修改', null, 'wx:wxaccount:update', 2, 'admin', 6,1);
insert into `sys_menu` values ('160804', '1608', '删除', null, 'wx:wxaccount:delete', 2, 'admin', 6,1);

insert into `sys_menu` values ('1609', '16', '内容管理', '', '', 0, 'admin', 2,1);
insert into `sys_menu` values ('160901', '1609', '文章管理', 'wx/article', null, 1, 'admin', 6,1);
insert into `sys_menu` values ('16090101', '160901', '查看', null, 'wx:article:list,wx:article:info', 2, 'admin', 6,1);
insert into `sys_menu` values ('16090102', '160901', '新增', null, 'wx:article:save', 2, 'admin', 6,1);
insert into `sys_menu` values ('16090103', '160901', '修改', null, 'wx:article:update', 2, 'admin', 6,1);
insert into `sys_menu` values ('16090104', '160901', '删除', null, 'wx:article:delete', 2, 'admin', 6,1);

insert into `sys_menu` values ('1610', '16', '日志报表', '', '', 0, 'admin', 4,1);
insert into `sys_menu` values ('161001', '1610', '模版消息发送记录', 'wx/template-msg-log', null, 1, 'admin', 6,1);
insert into `sys_menu` values ('16100101', '161001', '列表', null, 'wx:templatemsglog:list', 2, 'admin', 6,1);
insert into `sys_menu` values ('16100102', '161001', '删除', null, 'wx:templatemsglog:delete', 2, 'admin', 6,1);