/*
navicat mysql data transfer

source server         :
source server version : 50722
source host           :
source database       : tongyi

target server type    : mysql
target server version : 50722
file encoding         : 65001

date: 2019-02-12 19:51:39
*/

set foreign_key_checks=0;

-- ----------------------------
-- table structure for `qrtz_blob_triggers`
-- ----------------------------
drop table if exists `qrtz_blob_triggers`;
create table `qrtz_blob_triggers` (
  `sched_name` varchar(120) not null,
  `trigger_name` varchar(200) not null,
  `trigger_group` varchar(200) not null,
  `blob_data` blob,
  primary key (`sched_name`,`trigger_name`,`trigger_group`),
  key `sched_name` (`sched_name`,`trigger_name`,`trigger_group`),
  constraint `qrtz_blob_triggers_ibfk_1` foreign key (`sched_name`, `trigger_name`, `trigger_group`) references `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) engine=innodb default charset=utf8;

-- ----------------------------
-- records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- table structure for `qrtz_calendars`
-- ----------------------------
drop table if exists `qrtz_calendars`;
create table `qrtz_calendars` (
  `sched_name` varchar(120) not null,
  `calendar_name` varchar(200) not null,
  `calendar` blob not null,
  primary key (`sched_name`,`calendar_name`)
) engine=innodb default charset=utf8;

-- ----------------------------
-- records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- table structure for `qrtz_cron_triggers`
-- ----------------------------
drop table if exists `qrtz_cron_triggers`;
create table `qrtz_cron_triggers` (
  `sched_name` varchar(120) not null,
  `trigger_name` varchar(200) not null,
  `trigger_group` varchar(200) not null,
  `cron_expression` varchar(120) not null,
  `time_zone_id` varchar(80) default null,
  primary key (`sched_name`,`trigger_name`,`trigger_group`),
  constraint `qrtz_cron_triggers_ibfk_1` foreign key (`sched_name`, `trigger_name`, `trigger_group`) references `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) engine=innodb default charset=utf8;

-- ----------------------------
-- records of qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- table structure for `qrtz_fired_triggers`
-- ----------------------------
drop table if exists `qrtz_fired_triggers`;
create table `qrtz_fired_triggers` (
  `sched_name` varchar(120) not null,
  `entry_id` varchar(95) not null,
  `trigger_name` varchar(200) not null,
  `trigger_group` varchar(200) not null,
  `instance_name` varchar(200) not null,
  `fired_time` bigint(13) not null,
  `sched_time` bigint(13) not null,
  `priority` int(11) not null,
  `state` varchar(16) not null,
  `job_name` varchar(200) default null,
  `job_group` varchar(200) default null,
  `is_nonconcurrent` varchar(1) default null,
  `requests_recovery` varchar(1) default null,
  primary key (`sched_name`,`entry_id`),
  key `idx_qrtz_ft_trig_inst_name` (`sched_name`,`instance_name`),
  key `idx_qrtz_ft_inst_job_req_rcvry` (`sched_name`,`instance_name`,`requests_recovery`),
  key `idx_qrtz_ft_j_g` (`sched_name`,`job_name`,`job_group`),
  key `idx_qrtz_ft_jg` (`sched_name`,`job_group`),
  key `idx_qrtz_ft_t_g` (`sched_name`,`trigger_name`,`trigger_group`),
  key `idx_qrtz_ft_tg` (`sched_name`,`trigger_group`)
) engine=innodb default charset=utf8;

-- ----------------------------
-- records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- table structure for `qrtz_job_details`
-- ----------------------------
drop table if exists `qrtz_job_details`;
create table `qrtz_job_details` (
  `sched_name` varchar(120) not null,
  `job_name` varchar(200) not null,
  `job_group` varchar(200) not null,
  `description` varchar(250) default null,
  `job_class_name` varchar(250) not null,
  `is_durable` varchar(1) not null,
  `is_nonconcurrent` varchar(1) not null,
  `is_update_data` varchar(1) not null,
  `requests_recovery` varchar(1) not null,
  `job_data` blob,
  primary key (`sched_name`,`job_name`,`job_group`),
  key `idx_qrtz_j_req_recovery` (`sched_name`,`requests_recovery`),
  key `idx_qrtz_j_grp` (`sched_name`,`job_group`)
) engine=innodb default charset=utf8;

-- ----------------------------
-- records of qrtz_job_details
-- ----------------------------

-- ----------------------------
-- table structure for `qrtz_locks`
-- ----------------------------
drop table if exists `qrtz_locks`;
create table `qrtz_locks` (
  `sched_name` varchar(120) not null,
  `lock_name` varchar(40) not null,
  primary key (`sched_name`,`lock_name`)
) engine=innodb default charset=utf8;

-- ----------------------------
-- records of qrtz_locks
-- ----------------------------

-- ----------------------------
-- table structure for `qrtz_paused_trigger_grps`
-- ----------------------------
drop table if exists `qrtz_paused_trigger_grps`;
create table `qrtz_paused_trigger_grps` (
  `sched_name` varchar(120) not null,
  `trigger_group` varchar(200) not null,
  primary key (`sched_name`,`trigger_group`)
) engine=innodb default charset=utf8;

-- ----------------------------
-- records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- table structure for `qrtz_scheduler_state`
-- ----------------------------
drop table if exists `qrtz_scheduler_state`;
create table `qrtz_scheduler_state` (
  `sched_name` varchar(120) not null,
  `instance_name` varchar(200) not null,
  `last_checkin_time` bigint(13) not null,
  `checkin_interval` bigint(13) not null,
  primary key (`sched_name`,`instance_name`)
) engine=innodb default charset=utf8;

-- ----------------------------
-- records of qrtz_scheduler_state
-- ----------------------------

-- ----------------------------
-- table structure for `qrtz_simple_triggers`
-- ----------------------------
drop table if exists `qrtz_simple_triggers`;
create table `qrtz_simple_triggers` (
  `sched_name` varchar(120) not null,
  `trigger_name` varchar(200) not null,
  `trigger_group` varchar(200) not null,
  `repeat_count` bigint(7) not null,
  `repeat_interval` bigint(12) not null,
  `times_triggered` bigint(10) not null,
  primary key (`sched_name`,`trigger_name`,`trigger_group`),
  constraint `qrtz_simple_triggers_ibfk_1` foreign key (`sched_name`, `trigger_name`, `trigger_group`) references `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) engine=innodb default charset=utf8;

-- ----------------------------
-- records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- table structure for `qrtz_simprop_triggers`
-- ----------------------------
drop table if exists `qrtz_simprop_triggers`;
create table `qrtz_simprop_triggers` (
  `sched_name` varchar(120) not null,
  `trigger_name` varchar(200) not null,
  `trigger_group` varchar(200) not null,
  `str_prop_1` varchar(512) default null,
  `str_prop_2` varchar(512) default null,
  `str_prop_3` varchar(512) default null,
  `int_prop_1` int(11) default null,
  `int_prop_2` int(11) default null,
  `long_prop_1` bigint(20) default null,
  `long_prop_2` bigint(20) default null,
  `dec_prop_1` decimal(13,4) default null,
  `dec_prop_2` decimal(13,4) default null,
  `bool_prop_1` varchar(1) default null,
  `bool_prop_2` varchar(1) default null,
  primary key (`sched_name`,`trigger_name`,`trigger_group`),
  constraint `qrtz_simprop_triggers_ibfk_1` foreign key (`sched_name`, `trigger_name`, `trigger_group`) references `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) engine=innodb default charset=utf8;

-- ----------------------------
-- records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- table structure for `qrtz_triggers`
-- ----------------------------
drop table if exists `qrtz_triggers`;
create table `qrtz_triggers` (
  `sched_name` varchar(120) not null,
  `trigger_name` varchar(200) not null,
  `trigger_group` varchar(200) not null,
  `job_name` varchar(200) not null,
  `job_group` varchar(200) not null,
  `description` varchar(250) default null,
  `next_fire_time` bigint(13) default null,
  `prev_fire_time` bigint(13) default null,
  `priority` int(11) default null,
  `trigger_state` varchar(16) not null,
  `trigger_type` varchar(8) not null,
  `start_time` bigint(13) not null,
  `end_time` bigint(13) default null,
  `calendar_name` varchar(200) default null,
  `misfire_instr` smallint(2) default null,
  `job_data` blob,
  primary key (`sched_name`,`trigger_name`,`trigger_group`),
  key `idx_qrtz_t_j` (`sched_name`,`job_name`,`job_group`),
  key `idx_qrtz_t_jg` (`sched_name`,`job_group`),
  key `idx_qrtz_t_c` (`sched_name`,`calendar_name`),
  key `idx_qrtz_t_g` (`sched_name`,`trigger_group`),
  key `idx_qrtz_t_state` (`sched_name`,`trigger_state`),
  key `idx_qrtz_t_n_state` (`sched_name`,`trigger_name`,`trigger_group`,`trigger_state`),
  key `idx_qrtz_t_n_g_state` (`sched_name`,`trigger_group`,`trigger_state`),
  key `idx_qrtz_t_next_fire_time` (`sched_name`,`next_fire_time`),
  key `idx_qrtz_t_nft_st` (`sched_name`,`trigger_state`,`next_fire_time`),
  key `idx_qrtz_t_nft_misfire` (`sched_name`,`misfire_instr`,`next_fire_time`),
  key `idx_qrtz_t_nft_st_misfire` (`sched_name`,`misfire_instr`,`next_fire_time`,`trigger_state`),
  key `idx_qrtz_t_nft_st_misfire_grp` (`sched_name`,`misfire_instr`,`next_fire_time`,`trigger_group`,`trigger_state`),
  constraint `qrtz_triggers_ibfk_1` foreign key (`sched_name`, `job_name`, `job_group`) references `qrtz_job_details` (`sched_name`, `job_name`, `job_group`)
) engine=innodb default charset=utf8;

-- ----------------------------
-- records of qrtz_triggers
-- ----------------------------

-- ----------------------------
-- table structure for `schedule_job`
-- ----------------------------
drop table if exists `schedule_job`;
create table `schedule_job` (
  `job_id` varchar(32) not null comment '任务id',
  `bean_name` varchar(200) default null comment 'spring bean名称',
  `method_name` varchar(100) default null comment '方法名',
  `params` varchar(2000) default null comment '参数',
  `cron_expression` varchar(100) default null comment 'cron表达式',
  `status` tinyint(4) default null comment '任务状态  0：正常  1：暂停',
  `remark` varchar(255) default null comment '备注',
  `create_time` datetime default null comment '创建时间',
  primary key (`job_id`)
) engine=innodb default charset=utf8 comment='定时任务';

-- ----------------------------
-- records of schedule_job
-- ----------------------------

-- ----------------------------
-- table structure for `schedule_job_log`
-- ----------------------------
drop table if exists `schedule_job_log`;
create table `schedule_job_log` (
  `log_id` varchar(32) not null comment '任务日志id',
  `job_id` varchar(32) not null comment '任务id',
  `bean_name` varchar(200) default null comment 'spring bean名称',
  `method_name` varchar(100) default null comment '方法名',
  `params` varchar(2000) default null comment '参数',
  `status` tinyint(4) not null comment '任务状态    0：成功    1：失败',
  `error` varchar(2000) default null comment '失败信息',
  `times` int(11) not null comment '耗时(单位：毫秒)',
  `create_time` datetime default null comment '创建时间',
  primary key (`log_id`),
  key `job_id` (`job_id`)
) engine=innodb default charset=utf8 comment='定时任务日志';

-- ----------------------------
-- records of schedule_job_log
-- ----------------------------

-- ----------------------------
-- table structure for `sys_captcha`
-- ----------------------------
drop table if exists `sys_captcha`;
create table `sys_captcha` (
  `uuid` char(36) not null comment 'uuid',
  `code` varchar(6) not null comment '验证码',
  `expire_time` datetime default null comment '过期时间',
  primary key (`uuid`)
) engine=innodb default charset=utf8 comment='系统验证码';

-- ----------------------------
-- records of sys_captcha
-- ----------------------------

-- ----------------------------
-- table structure for `sys_config`
-- ----------------------------
drop table if exists `sys_config`;
create table `sys_config` (
  `id` varchar(32) not null,
  `param_key` varchar(50) default null comment 'key',
  `param_value` varchar(2000) default null comment 'value',
  `status` tinyint(4) default '1' comment '状态   0：隐藏   1：显示',
  `remark` varchar(500) default null comment '备注',
  primary key (`id`),
  unique key `param_key` (`param_key`)
) engine=innodb default charset=utf8 comment='系统配置信息表';

-- ----------------------------
-- records of sys_config
-- ----------------------------
insert into `sys_config` values ('1', 'cloud_storage_config_key', '{\"type\":4,\"qiniuDomain\":\"\",\"qiniuPrefix\":\"\",\"qiniuAccessKey\":\"\",\"qiniuSecretKey\":\"\",\"qiniuBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunPrefix\":\"\",\"aliyunEndPoint\":\"\",\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qcloudBucketName\":\"\",\"diskPath\":\"/usr/local/nginx/html/upload\",\"proxyServer\":\"http://132.232.89.47/upload\"}', '0', '云存储配置信息');
insert into `sys_config` values ('2', 'sms_config_key', '{\"domain\":\"http://web.cr6868.com/asmx/smsservice.aspx?\",\"name\":\"lipengjun\",\"pwd\":\"\",\"sign\":\"【惠州市酷天科技有限公司】\",\"type\":1}', '0', '短信配置');

-- ----------------------------
-- table structure for `sys_dict`
-- ----------------------------
drop table if exists `sys_dict`;
create table `sys_dict` (
  `id` varchar(32) not null,
  `group_id` varchar(32) default null comment '所属分组id',
  `name` varchar(100) default null comment '字典名称',
  `value` varchar(64) default null comment '字典值',
  `sort` int(11) default null comment '排序号',
  `status` int(11) default null comment '状态码',
  `remark` text comment '备注',
  `edited` bit comment '是否可编辑',
  primary key (`id`)
) engine=innodb default charset=utf8 comment='数据字典';

-- ----------------------------
-- records of sys_dict
-- ----------------------------
insert into `sys_dict` values ('37f73ea6b07c40ab8baec7f58b10e69e', '0b5e3fc9c30a4839a881bef0f85fc8af', '男', '1', '1', '1', null,0);
insert into `sys_dict` values ('979439be76954bc1852fdf2aeccf3cbc', '0b5e3fc9c30a4839a881bef0f85fc8af', '未知', '3', '3', '1', null,0);
insert into `sys_dict` values ('fc982423addd41e3852c70f8396a0c6c', '0b5e3fc9c30a4839a881bef0f85fc8af', '女', '2', '2', '1', null,0);
insert into `sys_dict` values ('7936bc509417490ba0df9d938ccd1ce4', '2bbfcb36f9414b71a5d65f497be93496', '是', '1', '1', '1', null,0);
insert into `sys_dict` values ('f6cf775c5cea4c7b8858eb2ce0501177', '2bbfcb36f9414b71a5d65f497be93496', '否', '0', '2', '1', null,0);

-- ----------------------------
-- table structure for `sys_dict_group`
-- ----------------------------
drop table if exists `sys_dict_group`;
create table `sys_dict_group` (
  `id` varchar(32) not null,
  `code` varchar(64) not null comment '分组编码',
  `name` varchar(100) default null comment '分组名称',
  `create_time` datetime default null comment '创建时间',
  `remark` text comment '备注',
  `edited` bit comment '是否可编辑',
  primary key (`id`,`code`)
) engine=innodb default charset=utf8 comment='数据字典分组';

-- ----------------------------
-- records of sys_dict_group
-- ----------------------------
insert into `sys_dict_group` values ('0b5e3fc9c30a4839a881bef0f85fc8af', 'sex', '性别', null, '性别，1：男 2：女 3：未知',0);
insert into `sys_dict_group` values ('2bbfcb36f9414b71a5d65f497be93496', 'is_not', '是否', null, '1：是 0：否',0);

-- ----------------------------
-- table structure for `sys_log`
-- ----------------------------
drop table if exists `sys_log`;
create table `sys_log` (
  `id` varchar(32) not null,
  `user_name` varchar(50) default null comment '用户名',
  `operation` varchar(50) default null comment '用户操作',
  `method` varchar(200) default null comment '请求方法',
  `params` varchar(5000) default null comment '请求参数',
  `time` bigint(20) not null comment '执行时长(毫秒)',
  `ip` varchar(64) default null comment 'ip地址',
  `create_time` datetime default null comment '创建时间',
  primary key (`id`)
) engine=innodb default charset=utf8 comment='系统日志';

-- ----------------------------
-- records of sys_log
-- ----------------------------

-- ----------------------------
-- table structure for `sys_menu`
-- ----------------------------
drop table if exists `sys_menu`;
create table `sys_menu` (
  `menu_id` varchar(8) not null,
  `parent_id` varchar(8) default null comment '父菜单id，一级菜单为0',
  `name` varchar(50) default null comment '菜单名称',
  `url` varchar(200) default null comment '菜单url',
  `perms` varchar(500) default null comment '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) default null comment '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) default null comment '菜单图标',
  `order_num` int(11) default null comment '排序',
  `shows` int(11) default null comment '是否显示',
  primary key (`menu_id`)
) engine=innodb default charset=utf8 comment='菜单管理';

-- ----------------------------
-- records of sys_menu
-- ----------------------------
insert into `sys_menu` values ('10', '0', '系统管理', null, null, 0, 'system', 0,1);
insert into `sys_menu` values ('1001', '10', '菜单管理', 'sys/menu', 'sys:menu:list,sys:menu:info', 1, 'menu', 1,1);
insert into `sys_menu` values ('100101', '1001', '新增', null, 'sys:menu:save,sys:menu:select', 2, null, 0,1);
insert into `sys_menu` values ('100102', '1001', '修改', null, 'sys:menu:update,sys:menu:select', 2, null, 0,1);
insert into `sys_menu` values ('100103', '1001', '删除', null, 'sys:menu:delete', 2, null, 0,1);
insert into `sys_menu` values ('1002', '10', '组织机构', 'sys/org', 'sys:org:list,sys:org:info', 1, 'org', 2,1);
insert into `sys_menu` values ('100201', '1002', '新增', null, 'sys:org:save', 2, null, 0,1);
insert into `sys_menu` values ('100202', '1002', '修改', null, 'sys:org:update', 2, null, 0,1);
insert into `sys_menu` values ('100203', '1002', '删除', null, 'sys:org:delete', 2, null, 0,1);
insert into `sys_menu` values ('1003', '10', '系统参数', 'sys/config', 'sys:config:list,sys:config:info', 1, 'xitongpeizhi', 3,1);
insert into `sys_menu` values ('100301', '1003', '新增', null, 'sys:config:save', 2, null, 0,1);
insert into `sys_menu` values ('100302', '1003', '修改', null, 'sys:config:update', 2, null, 0,1);
insert into `sys_menu` values ('100303', '1003', '删除', null, 'sys:config:delete', 2, null, 0,1);
insert into `sys_menu` values ('1004', '10', '字典管理', 'sys/dictgroup', 'sys:dictgroup:list,sys:dictgroup:info,sys:dict:list,sys:dict:info', 1, 'dict', 4,1);
insert into `sys_menu` values ('100401', '1004', '数据字典新增', null, 'sys:dict:save', 2, null, 0,1);
insert into `sys_menu` values ('100402', '1004', '数据字典修改', null, 'sys:dict:update', 2, null, 0,1);
insert into `sys_menu` values ('100403', '1004', '数据字典删除', null, 'sys:dict:delete', 2, null, 0,1);
insert into `sys_menu` values ('100404', '1004', '数据字典分组新增', null, 'sys:dictgroup:save', 2, null, 0,1);
insert into `sys_menu` values ('100405', '1004', '数据字典分组修改', null, 'sys:dictgroup:update', 2, null, 0,1);
insert into `sys_menu` values ('100406', '1004', '数据字典分组删除', null, 'sys:dictgroup:delete', 2, null, 0,1);

insert into `sys_menu` values ('1005', '10', '文件上传', 'oss/oss', 'sys:oss:list', 1, 'oss', 5,1);
insert into `sys_menu` values ('100501', '1005', '云存储配置', null, 'sys:oss:config', 2, null, 0,1);
insert into `sys_menu` values ('100502', '1005', '上传文件', null, 'sys:oss:upload', 2, null, 0,1);
insert into `sys_menu` values ('100503', '1005', '删除', null, 'sys:oss:delete', 2, null, 0,1);

insert into `sys_menu` values ('1006', '10', '系统日志', 'sys/log', 'sys:log:list', 1, 'log', 6,1);

insert into `sys_menu` values ('1007', '10', '邮件系统', 'sys/maillog', 'sys:maillog:list,sys:maillog:info', '1', 'email', 7,1);
insert into `sys_menu` values ('100701', '1007', '删除', null, 'sys:maillog:delete', '2', null, 0,1);
insert into `sys_menu` values ('100702', '1007', '邮箱配置', null, 'sys:maillog:config', '2', null, 0,1);

insert into `sys_menu` values ('11', '0', '权限管理', null, null, 0, 'auth', 1,1);
insert into `sys_menu` values ('1101', '11', '管理员列表', 'sys/user', 'sys:user:list,sys:user:info', 1, 'admin', 1,1);
insert into `sys_menu` values ('110101', '1101', '重置密码', null, 'sys:user:resetpw', 2, null, 0,1);
insert into `sys_menu` values ('110102', '1101', '新增', null, 'sys:user:save,sys:role:select', 2, null, 0,1);
insert into `sys_menu` values ('110103', '1101', '修改', null, 'sys:user:update,sys:role:select', 2, null, 0,1);
insert into `sys_menu` values ('110104', '1101', '删除', null, 'sys:user:delete', 2, null, 0,1);
insert into `sys_menu` values ('1102', '11', '角色管理', 'sys/role', 'sys:role:list,sys:role:info', 1, 'role', 2,1);
insert into `sys_menu` values ('110201', '1102', '新增', null, 'sys:role:save,sys:menu:list', 2, null, 0,1);
insert into `sys_menu` values ('110202', '1102', '修改', null, 'sys:role:update,sys:menu:list', 2, null, 0,1);
insert into `sys_menu` values ('110203', '1102', '删除', null, 'sys:role:delete', 2, null, 0,1);

insert into `sys_menu` values ('12', '0', '短信平台', null, null, 0, 'duanxinpingtai', 2,1);
insert into `sys_menu` values ('1211', '12', '短信配置', 'sys/smslog', 'sys:smslog:list', 1, 'duanxin', 1,1);
insert into `sys_menu` values ('121101', '1211', '修改配置', null, 'sys:smslog:config', 2, null, 0,1);
insert into `sys_menu` values ('121102', '1211', '删除', null, 'sys:smslog:delete', 2, null, 0,1);
insert into `sys_menu` values ('121103', '1211', '发送短信', null, 'sys:smslog:send', 2, null, 0,1);

insert into `sys_menu` values ('13', '0', '任务调度', null, null, 0, 'diaodu', 3,1);
insert into `sys_menu` values ('1301', '13', '定时任务', 'job/schedule', 'sys:schedule:list,sys:schedule:info', 1, 'job', 1,1);
insert into `sys_menu` values ('130101', '1301', '删除', null, 'sys:schedule:delete', 2, null, 0,1);
insert into `sys_menu` values ('130102', '1301', '暂停', null, 'sys:schedule:pause', 2, null, 0,1);
insert into `sys_menu` values ('130103', '1301', '恢复', null, 'sys:schedule:resume', 2, null, 0,1);
insert into `sys_menu` values ('130104', '1301', '立即执行', null, 'sys:schedule:run', 2, null, 0,1);
insert into `sys_menu` values ('130105', '1301', '日志列表', null, 'sys:schedule:log', 2, null, 0,1);
insert into `sys_menu` values ('130106', '1301', '新增', null, 'sys:schedule:save', 2, null, 0,1);
insert into `sys_menu` values ('130107', '1301', '修改', null, 'sys:schedule:update', 2, null, 0,1);

insert into `sys_menu` values ('14', '0', '工作流管理', null, null, 0, 'activiti', 4,1);
insert into `sys_menu` values ('1401', '14', '流程操作', 'act/reprocdef', 'act:reprocdef:list', 1, 'procdef', 1,1);
insert into `sys_menu` values ('140101', '1401', '激活,挂起', null, 'act:reprocdef:update', 2, null, 0,1);
insert into `sys_menu` values ('140102', '1401', '删除', null, 'act:reprocdef:delete', 2, null, 0,1);
insert into `sys_menu` values ('140103', '1401', '转为模型', null, 'act:reprocdef:converttomodel', 2, null, 0,1);
insert into `sys_menu` values ('140104', '1401', '部署流程', null, 'act:reprocdef:deploy', 2, null, 0,1);
insert into `sys_menu` values ('1402', '14', '模型管理', 'act/remodel', 'act:remodel:list', 1, 'model', 2,1);
insert into `sys_menu` values ('140201', '1402', '新增', null, 'act:remodel:save', 2, null, 0,1);
insert into `sys_menu` values ('140202', '1402', '编辑', null, 'act:remodel:update', 2, null, 0,1);
insert into `sys_menu` values ('140203', '1402', '部署', null, 'act:remodel:deploy', 2, null, 0,1);
insert into `sys_menu` values ('140204', '1402', '导出', null, 'act:remodel:export', 2, null, 0,1);
insert into `sys_menu` values ('140205', '1402', '删除', null, 'act:remodel:delete', 2, null, 0,1);

insert into `sys_menu` values ('15', '0', '开发工具', null, null, 0, 'dev', 5,1);
insert into `sys_menu` values ('1501', '15', '在线用户管理', 'sys/usertoken', 'sys:usertoken:list', 1, 'zaixian', 1,1);
insert into `sys_menu` values ('150101', '1501', '强制下线', null, 'sys:usertoken:offline', 2, null, 0,1);
insert into `sys_menu` values ('1502', '15', '缓存信息', 'sys/redis', 'sys:cache:list', 1, 'redis', 2,1);
insert into `sys_menu` values ('150201', '1502', '删除', null, 'sys:cache:deletecache', 2, null, 0,1);

-- insert into `sys_menu` values ('1503', '15', 'sql监控', 'http://localhost:8888/platform-admin/druid/sql.html', null, 1, 'sql', 3,0);
-- insert into `sys_menu` values ('1504', '15', '接口文档', 'http://localhost:8889/platform-api/doc.html', null, 1, 'interface', 4,1);
insert into `sys_menu` values ('1505', '15', '代码生成器', 'gen/generator', 'sys:generator:list', 1, 'code', 5,1);
insert into `sys_menu` values ('150501', '1505', '生成代码', null, 'sys:generator:code', 2, null, 0,1);
-- insert into `sys_menu` values ('16', '0', 'elk平台', 'http://localhost:5601', null, '1', 'log', 7,0);

insert into `sys_menu` values ('1008', '10', '民族管理', 'sys/nation', 'sys:nation:list,sys:nation:info', '1', 'admin', '0', '1');
insert into `sys_menu` values ('100801', '1008', '新增', null, 'sys:nation:save', '2', null, '0', '1');
insert into `sys_menu` values ('100802', '1008', '修改', null, 'sys:nation:update', '2', null, '0', '1');
insert into `sys_menu` values ('100803', '1008', '删除', null, 'sys:nation:delete', '2', null, '0', '1');



-- ----------------------------
-- table structure for `sys_org`
-- ----------------------------
drop table if exists `sys_org`;
create table `sys_org` (
  `org_no` varchar(10) not null comment '机构编码',
  `org_name` varchar(50) default null comment '机构名称',
  `parent_no` varchar(10) default null comment '上级机构id，一级机构为0',
  `org_type` int(11) default null comment '级别',
  `status` int(11) default '1' comment '状态  0：无效   1：有效',
  `sort` int(11) default null comment '排序',
  `create_user_id` varchar(32) default null comment '创建者id',
  `create_time` datetime default null comment '创建时间',
  primary key (`org_no`)
) engine=innodb default charset=utf8 comment='组织机构';

-- ----------------------------
-- records of sys_org
-- ----------------------------
insert into `sys_org` values ('01', '中华人民共和国', '0', '1', '1', '0', '1', '2019-01-21 16:53:32');

-- ----------------------------
-- table structure for `sys_oss`
-- ----------------------------
drop table if exists `sys_oss`;
create table `sys_oss` (
  `id` varchar(32) not null,
  `url` varchar(200) default null comment 'url地址',
  `create_date` datetime default null comment '创建时间',
  primary key (`id`)
) engine=innodb default charset=utf8 comment='文件上传';

-- ----------------------------
-- records of sys_oss
-- ----------------------------

-- ----------------------------
-- table structure for `sys_role`
-- ----------------------------
drop table if exists `sys_role`;
create table `sys_role` (
  `role_id` varchar(32) not null,
  `role_name` varchar(100) default null comment '角色名称',
  `remark` varchar(100) default null comment '备注',
  `create_user_id` varchar(32) default null comment '创建者id',
  `create_user_org_no` varchar(32) default null comment '创建者所属机构',
  `create_time` datetime default null comment '创建时间',
  primary key (`role_id`)
) engine=innodb default charset=utf8 comment='角色';

-- ----------------------------
-- records of sys_role
-- ----------------------------

-- ----------------------------
-- table structure for `sys_role_menu`
-- ----------------------------
drop table if exists `sys_role_menu`;
create table `sys_role_menu` (
  `id` varchar(32) not null,
  `role_id` varchar(32) default null comment '角色id',
  `menu_id` varchar(8) default null comment '菜单id',
  primary key (`id`)
) engine=innodb default charset=utf8 comment='角色与菜单对应关系';

-- ----------------------------
-- records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- table structure for `sys_role_org`
-- ----------------------------
drop table if exists `sys_role_org`;
create table `sys_role_org` (
  `id` varchar(32) not null,
  `role_id` varchar(32) default null comment '角色id',
  `org_no` varchar(32) default null comment '机构id',
  primary key (`id`)
) engine=innodb default charset=utf8 comment='角色与机构对应关系';

-- ----------------------------
-- records of sys_role_org
-- ----------------------------

-- ----------------------------
-- table structure for `sys_sms_log`
-- ----------------------------
drop table if exists `sys_sms_log`;
create table `sys_sms_log` (
  `id` varchar(32) not null comment '主键',
  `user_id` varchar(32) default null comment '操作人id',
  `content` text comment '必填参数。发送内容（1-500 个汉字）utf-8编码',
  `mobile` text comment '必填参数。手机号码。多个以英文逗号隔开',
  `stime` datetime default null comment '可选参数。发送时间，填写时已填写的时间发送，不填时为当前时间发送',
  `sign` varchar(32) default null comment '必填参数。用户签名',
  `type` varchar(32) default null comment '必填参数。固定值 pt',
  `extno` varchar(255) default null comment '可选参数。扩展码，用户定义扩展码，只能为数字',
  `send_status` int(1) default null comment '1成功 0失败',
  `send_id` varchar(32) default null comment '发送编号',
  `invalid_num` int(11) default null comment '无效号码数',
  `success_num` int(11) default null comment '成功提交数',
  `black_num` int(11) default null comment '黑名单数',
  `return_msg` varchar(50) default null comment '返回消息',
  primary key (`id`)
) engine=innodb default charset=utf8 comment='短信发送日志';

-- ----------------------------
-- records of sys_sms_log
-- ----------------------------

-- ----------------------------
-- table structure for `sys_user`
-- ----------------------------
drop table if exists `sys_user`;
create table `sys_user` (
  `user_id` varchar(32) not null,
  `user_name` varchar(50) not null comment '用户名',
  `real_name` varchar(64) not null,
  `sex` tinyint(4) not null,
  `org_no` varchar(32) default null comment '机构编码',
  `salt` varchar(20) default null comment '盐',
  `email_host` varchar(32) default null comment '邮件服务器地址',
  `email_port` int(11) default null comment '服务器端口',
  `email` varchar(100) default null comment '邮箱',
  `email_pw` varchar(64) default null comment '用户邮箱密码',
  `mobile` varchar(100) default null comment '手机号',
  `status` tinyint(4) default null comment '状态  0：禁用   1：正常',
  `password` varchar(100) default null comment '密码',
  `create_user_id` varchar(32) default null comment '创建者id',
  `create_user_org_no` varchar(32) default null comment '创建人所属机构',
  `create_time` datetime default null comment '创建时间',
  primary key (`user_id`),
  unique key `username` (`user_name`)
) engine=innodb default charset=utf8 comment='系统用户';

-- ----------------------------
-- records of sys_user
-- ----------------------------
insert into `sys_user` values ('1', 'admin', '林佛权', 1, '01', 'yzcmcznvbxocrsz9dm8e', 'smtp.qq.com','25', '147657060@qq.com', '', '13794384396', '1', 'afeb21d897ac93b3df65f87f17786a35a27045174c98c10b608a37acba2cfac3', null, null, '2016-11-11 11:11:11');

-- ----------------------------
-- table structure for `sys_user_role`
-- ----------------------------
drop table if exists `sys_user_role`;
create table `sys_user_role` (
  `id` varchar(32) not null,
  `user_id` varchar(32) default null comment '用户id',
  `role_id` varchar(32) default null comment '角色id',
  primary key (`id`)
) engine=innodb default charset=utf8 comment='用户与角色对应关系';

-- ----------------------------
-- records of sys_user_role
-- ----------------------------

-- ----------------------------
-- table structure for `sys_user_token`
-- ----------------------------
drop table if exists `sys_user_token`;
create table `sys_user_token` (
  `user_id` varchar(32) not null,
  `token` varchar(100) not null comment 'token',
  `expire_time` datetime default null comment '过期时间',
  `update_time` datetime default null comment '更新时间',
  primary key (`user_id`),
  unique key `token` (`token`)
) engine=innodb default charset=utf8 comment='系统用户token';

-- ----------------------------
-- records of sys_user_token
-- ----------------------------

-- ----------------------------
-- table structure for `mall_user`
-- ----------------------------
-- drop table if exists `mall_user`;
-- create table `mall_user` (
-- 	id varchar(32) not null comment '用户id',
-- 	user_name varchar(128) default '' not null comment '用户名',
-- 	password varchar(128) default '' null comment '密码',
-- 	gender tinyint null comment '用户的性别（1是男性，2是女性，0是未知）',
-- 	birthday datetime null comment '生日',
-- 	register_time datetime null comment '注册时间',
-- 	last_login_time datetime null comment '最后登录时间',
-- 	last_login_ip varchar(32) default '' null comment '最后登录ip',
-- 	nickname varchar(60) default '' null comment '微信昵称',
-- 	mobile varchar(32) default '' null comment '手机号',
-- 	register_ip varchar(64) default '' null comment '注册ip',
-- 	head_img_url varchar(255) default '' null comment '用户头像',
-- 	ali_user_id varchar(64) default '' null comment '支付宝用户标识',
-- 	open_id varchar(64) default '' null comment '用户的标识',
-- 	qq_open_id varchar(64) default '' null comment 'qq用户的标识',
-- 	mp_open_id varchar(64) null comment '公众号用户的标识',
-- 	union_id varchar(128) null comment '用户唯一标识',
-- 	subscribe tinyint default 0 comment '公众号关注状态（1是关注，0是未关注），未关注时获取不到其余信息',
-- 	subscribe_time varchar(32) null comment '用户关注公众号时间，为时间戳。如果用户曾多次关注，则取最后关注时间',
--   primary key (`id`),
--   key `open_id` (`open_id`) using btree
-- ) engine=innodb default charset=utf8 comment='会员';

-- ----------------------------
-- records of mall_user
-- ----------------------------
-- insert into `mall_user` values ('1', 'a boy genius 专业小程序开发', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 1, '1992-07-04 02:31:09', '2019-04-12 12:03:48', '2019-06-18 15:37:58', '223.104.35.49', 'a boy genius 专业小程序开发', '15209831990', '117.136.100.200', 'https://wx.qlogo.cn/mmopen/vi_32/dyaiogq83eo62pttzxbohpf0akatjpnwiayamyfrz4j3pyxf8fykmwz3ickiadiboqw0umzbtegcdifhbyiafkm8sibq/132', null, 'ok8kw5geiwayta-z92jfbzxkvnpa', null, null, 1, '');

drop table if exists `sys_mail_log`;
create table `sys_mail_log` (
  `id` varchar(32) not null,
  `sender` varchar(100) not null comment '发送人',
  `receiver` varchar(4000) not null comment '接收人',
  `subject` varchar(500) not null comment '邮件主题',
  `content` varchar(4000) default null comment '发送内容',
  `send_date` datetime default null comment '发送时间',
  `type` tinyint(4) default null comment '0：系统发送邮件 1：用户发送邮件',
  `send_result` tinyint(4) default null comment '发送结果 0:发送成功 1:发送失败',
  `create_user_id` varchar(32) default null comment '创建者id',
  `create_user_org_no` varchar(32) default null comment '创建人所属机构',
  primary key (`id`)
) engine=innodb default charset=utf8 comment='邮件发送日志';


drop table if exists `sys_nation`;
create table `sys_nation` (`code`  varchar(10) not null ,`name`  varchar(255) null ,primary key (`code`))  engine=innodb default charset=utf8 comment='民族';

insert into sys_nation values('01','汉族')   ;
insert into sys_nation values('02','蒙古族')  ;
insert into sys_nation values('03','回族')   ;
insert into sys_nation values('04','藏族')   ;
insert into sys_nation values('05','维吾尔族');
insert into sys_nation values('06','苗族')   ;
insert into sys_nation values('07','彝族')   ;
insert into sys_nation values('08','壮族')   ;
insert into sys_nation values('09','布依族')  ;
insert into sys_nation values('10','朝鲜族')   ;
insert into sys_nation values('11','满族')   ;
insert into sys_nation values('12','侗族')   ;
insert into sys_nation values('13','瑶族')   ;
insert into sys_nation values('14','白族')   ;
insert into sys_nation values('15','土家族')  ;
insert into sys_nation values('16','哈尼族')   ;
insert into sys_nation values('17','哈萨克族')  ;
insert into sys_nation values('18','傣族')   ;
insert into sys_nation values('19','黎族')   ;
insert into sys_nation values('20','傈僳族')  ;
insert into sys_nation values('21','佤族')   ;
insert into sys_nation values('22','畲族')   ;
insert into sys_nation values('23','高山族')  ;
insert into sys_nation values('24','拉祜族')   ;
insert into sys_nation values('25','水族')   ;
insert into sys_nation values('26','东乡族')  ;
insert into sys_nation values('27','纳西族')   ;
insert into sys_nation values('28','景颇族')   ;
insert into sys_nation values('29','柯尔克孜族');
insert into sys_nation values('30','土族')   ;
insert into sys_nation values('31','达斡尔族');
insert into sys_nation values('32','仫佬族')   ;
insert into sys_nation values('33','羌族')   ;
insert into sys_nation values('34','布朗族')  ;
insert into sys_nation values('35','撒拉族')   ;
insert into sys_nation values('36','毛难族')   ;
insert into sys_nation values('37','仡佬族')   ;
insert into sys_nation values('38','锡伯族')   ;
insert into sys_nation values('39','阿昌族')   ;
insert into sys_nation values('40','普米族')   ;
insert into sys_nation values('41','塔吉克族')  ;
insert into sys_nation values('42','怒族')   ;
insert into sys_nation values('43','乌孜别克族')   ;
insert into sys_nation values('44','俄罗斯族')   ;
insert into sys_nation values('45','鄂温克族')   ;
insert into sys_nation values('46','崩龙族')   ;
insert into sys_nation values('47','保安族')   ;
insert into sys_nation values('48','裕固族')   ;
insert into sys_nation values('49','京族')   ;
insert into sys_nation values('50','塔塔尔族');
insert into sys_nation values('51','独龙族')   ;
insert into sys_nation values('52','鄂伦春族')  ;
insert into sys_nation values('53','赫哲族')   ;
insert into sys_nation values('54','门巴族')   ;
insert into sys_nation values('55','珞巴族')   ;
insert into sys_nation values('56','基诺族')   ;