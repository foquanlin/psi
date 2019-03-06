-- 菜单
CREATE TABLE sys_menu (
  menu_id VARCHAR2(8) NOT NULL,
  parent_id VARCHAR2(8) NOT NULL,
  name varchar2(50),
  url varchar2(200),
  perms varchar2(500),
  type NUMBER(2, 0),
  icon varchar2(50),
  order_num NUMBER(8, 0),
  PRIMARY KEY (menu_id)
);
---表注释：
comment on table sys_menu is '菜单管理';
---添加字段注释：
comment on column sys_menu.menu_id is '菜单ID';
comment on column sys_menu.parent_id is '父菜单ID，一级菜单为0';
comment on column sys_menu.name is '菜单名称';
comment on column sys_menu.url is '菜单URL';
comment on column sys_menu.perms is '授权(多个用逗号分隔，如：user:list,user:create)';
comment on column sys_menu.type is '类型   0：目录   1：菜单   2：按钮';
comment on column sys_menu.icon is '菜单图标';
comment on column sys_menu.order_num is '排序';

-- 系统用户
CREATE TABLE sys_user (
  user_id VARCHAR2(32) NOT NULL,
  user_name varchar2(50) NOT NULL,
  real_name varchar2(64) NOT NULL,
  sex NUMBER(2, 0) NOT NULL,
  org_no varchar2(50) NOT NULL,
  password varchar2(100),
  salt varchar2(20),
  email varchar2(100),
  mobile varchar2(100),
  status NUMBER(2, 0) NOT NULL,
  create_user_id VARCHAR2(32) NOT NULL,
  create_user_org_no VARCHAR2(32) NOT NULL,
  create_time timestamp,
  PRIMARY KEY (user_id)
);
CREATE UNIQUE INDEX index_sys_user_user_name on sys_user(user_name);

---表注释：
comment on table sys_user is '系统用户';
---添加字段注释：
comment on column sys_user.user_name is '用户名';
comment on column sys_user.org_no is '机构编码';
comment on column sys_user.salt is '盐';
comment on column sys_user.email is '邮箱';
comment on column sys_user.mobile is '手机号';
comment on column sys_user.status is '状态  0：禁用   1：正常';
comment on column sys_user.password is '密码';
comment on column sys_user.create_user_id is '创建者ID';
comment on column sys_user.create_user_org_no is '创建人所属机构';
comment on column sys_user.create_time is '创建时间';

-- 系统用户Token
CREATE TABLE sys_user_token (
  user_id VARCHAR2(32) NOT NULL,
  token varchar2(100) NOT NULL,
  expire_time timestamp,
  update_time timestamp,
  PRIMARY KEY (user_id)
);
CREATE UNIQUE INDEX index_token on sys_user_token(token);

---表注释：
comment on table sys_user_token is '系统用户Token';
---添加字段注释：
comment on column sys_user_token.token is 'token';
comment on column sys_user_token.expire_time is '过期时间';
comment on column sys_user_token.update_time is '更新时间';

-- 系统验证码
CREATE TABLE sys_captcha (
  uuid varchar2(36) NOT NULL,
  code varchar2(6) NOT NULL,
  expire_time timestamp,
  PRIMARY KEY (uuid)
);

---表注释：
comment on table sys_captcha is '系统验证码';
---添加字段注释：
comment on column sys_captcha.uuid is 'uuid';
comment on column sys_captcha.code is '验证码';
comment on column sys_captcha.expire_time is '过期时间';

--数据字典
CREATE TABLE sys_dict (
  id varchar2(32) NOT NULL,
  group_id varchar2(32) DEFAULT NULL,
  name varchar2(100) DEFAULT NULL,
  value varchar2(64) DEFAULT NULL,
  sort NUMBER(2, 0) DEFAULT 1,
  status NUMBER(2, 0) DEFAULT 1,
  remark varchar2(500),
  PRIMARY KEY (id)
);

---表注释：
comment on table sys_dict is '数据字典';
---添加字段注释：
comment on column sys_dict.group_id is '所属分组ID';
comment on column sys_dict.name is '字典名称';
comment on column sys_dict.value is '字典值';
comment on column sys_dict.sort is '排序号';
comment on column sys_dict.status is '状态码';
comment on column sys_dict.remark is '备注';

-- 数据字典组
CREATE TABLE sys_dict_group (
  id varchar2(32) NOT NULL,
  code varchar2(64) NOT NULL,
  name varchar2(100) DEFAULT NULL,
  create_time timestamp DEFAULT NULL,
  remark varchar2(500),
  PRIMARY KEY (id,code)
);

---表注释：
comment on table sys_dict_group is '数据字典分组';
---添加字段注释：
comment on column sys_dict_group.code is '分组编码';
comment on column sys_dict_group.name is '分组名称';
comment on column sys_dict_group.create_time is '创建时间';
comment on column sys_dict_group.remark is '备注';

-- 组织机构
CREATE TABLE sys_org (
  org_no varchar2(10) NOT NULL,
  org_name varchar2(50) DEFAULT NULL,
  parent_no varchar2(10) DEFAULT NULL,
  org_type number(11) DEFAULT NULL,
  status number(2,0) DEFAULT 1,
  sort number(4,0) DEFAULT NULL,
  create_user_id varchar2(32) DEFAULT NULL,
  create_time timestamp DEFAULT NULL,
  PRIMARY KEY (org_no)
);

---表注释：
comment on table sys_org is '组织机构';
---添加字段注释：
comment on column sys_org.org_no is '机构编码';
comment on column sys_org.org_name is '机构名称';
comment on column sys_org.parent_no is '上级机构ID，一级机构为0';
comment on column sys_org.org_type is '级别';
comment on column sys_org.status is '状态  0：无效   1：有效';
comment on column sys_org.sort is '排序';
comment on column sys_org.create_user_id is '创建者ID';
comment on column sys_org.create_time is '创建时间';

-- 角色
CREATE TABLE sys_role (
  role_id VARCHAR2(32) NOT NULL,
  role_name varchar2(100),
  remark varchar2(500),
  create_user_id VARCHAR2(32),
  create_user_org_no VARCHAR2(32),
  create_time timestamp,
  PRIMARY KEY (role_id)
);

---表注释：
comment on table sys_role is '角色';
---添加字段注释：
comment on column sys_role.role_name is '角色名称';
comment on column sys_role.remark is '备注';
comment on column sys_role.create_user_id is '创建者ID';
comment on column sys_role.create_user_org_no is '创建者所属机构';
comment on column sys_role.create_time is '创建时间';

-- 用户与角色对应关系
CREATE TABLE sys_user_role (
  id VARCHAR2(32) NOT NULL,
  user_id VARCHAR2(32) NOT NULL,
  role_id VARCHAR2(32) NOT NULL,
  PRIMARY KEY (id)
);

---表注释：
comment on table sys_user_role is '用户与角色对应关系';
---添加字段注释：
comment on column sys_user_role.user_id is '用户ID';
comment on column sys_user_role.role_id is '角色ID';

-- 角色与菜单对应关系
CREATE TABLE sys_role_menu (
  id VARCHAR2(32) NOT NULL,
  role_id VARCHAR2(32),
  menu_id VARCHAR2(32),
  PRIMARY KEY (id)
);

---表注释：
comment on table sys_role_menu is '角色与菜单对应关系';
---添加字段注释：
comment on column sys_role_menu.role_id is '角色ID';
comment on column sys_role_menu.menu_id is '菜单ID';

-- 角色与机构对应关系
CREATE TABLE sys_role_org (
  id VARCHAR2(32) NOT NULL,
  role_id varchar2(32) DEFAULT NULL,
  org_no varchar2(32) DEFAULT NULL,
  PRIMARY KEY (id)
);

---表注释：
comment on table sys_role_org is '角色与机构对应关系';
---添加字段注释：
comment on column sys_role_org.role_id is '角色ID';
comment on column sys_role_org.org_no is '机构ID';


-- 系统配置信息
CREATE TABLE sys_config (
  id VARCHAR2(32) NOT NULL,
  param_key varchar2(50),
  param_value varchar2(4000),
  status NUMBER(2, 0) DEFAULT 1 NOT NULL,
  remark varchar2(500),
  PRIMARY KEY (id)
);
CREATE UNIQUE INDEX index_param_key on sys_config(param_key);

---表注释：
comment on table sys_config is '系统配置信息表';
---添加字段注释：
comment on column sys_config.param_key is 'key';
comment on column sys_config.param_value is 'value';
comment on column sys_config.status is '状态   0：隐藏   1：显示';
comment on column sys_config.remark is '备注';


-- 系统日志
CREATE TABLE sys_log (
  id VARCHAR2(32) NOT NULL,
  user_name varchar2(50),
  operation varchar2(50),
  method varchar2(200),
  params clob,
  time NUMBER(20, 0) NOT NULL,
  ip varchar2(64),
  create_time timestamp,
  PRIMARY KEY (id)
);

---表注释：
comment on table sys_log is '系统日志';
---添加字段注释：
comment on column sys_log.user_name is '用户名';
comment on column sys_log.operation is '用户操作';
comment on column sys_log.method is '请求方法';
comment on column sys_log.params is '请求参数';
comment on column sys_log.time is '执行时长(毫秒)';
comment on column sys_log.ip is 'IP地址';
comment on column sys_log.create_time is '创建时间';

-- 文件上传
CREATE TABLE sys_oss (
  id VARCHAR2(32) NOT NULL,
  url varchar2(200),
  create_date timestamp,
  PRIMARY KEY (id)
);

---表注释：
comment on table sys_oss is '文件上传';
---添加字段注释：
comment on column sys_oss.url is 'URL地址';
comment on column sys_oss.create_date is '创建时间';

-- 定时任务
CREATE TABLE schedule_job (
  job_id VARCHAR2(32) NOT NULL,
  bean_name varchar2(200),
  method_name varchar2(100),
  params varchar2(2000),
  cron_expression varchar2(100),
  status NUMBER(2, 0) NOT NULL,
  remark varchar2(255),
  create_time timestamp,
  PRIMARY KEY (job_id)
);

---表注释：
comment on table schedule_job is '定时任务';
---添加字段注释：
comment on column schedule_job.job_id is '任务id';
comment on column schedule_job.bean_name is 'spring bean名称';
comment on column schedule_job.method_name is '方法名';
comment on column schedule_job.params is '参数';
comment on column schedule_job.cron_expression is 'cron表达式';
comment on column schedule_job.status is '任务状态  0：正常  1：暂停';
comment on column schedule_job.remark is '备注';
comment on column schedule_job.create_time is '创建时间';

-- 定时任务日志
CREATE TABLE schedule_job_log (
  log_id VARCHAR2(32) NOT NULL,
  job_id VARCHAR2(32) NOT NULL,
  bean_name varchar2(200),
  method_name varchar2(100),
  params varchar2(2000),
  status NUMBER(2, 0) NOT NULL,
  error varchar2(2000),
  times NUMBER(10, 0) NOT NULL,
  create_time timestamp,
  PRIMARY KEY (log_id)
);
CREATE INDEX index_job_id on schedule_job_log(job_id);

---表注释：
comment on table schedule_job_log is '定时任务日志';
---添加字段注释：
comment on column schedule_job_log.log_id is '任务日志id';
comment on column schedule_job_log.job_id is '任务id';
comment on column schedule_job_log.bean_name is 'spring bean名';
comment on column schedule_job_log.method_name is '方法名';
comment on column schedule_job_log.params is '参数';
comment on column schedule_job_log.status is '任务状态  0：正常  1：暂停';
comment on column schedule_job_log.error is '失败信息';
comment on column schedule_job_log.times is '耗时(单位：毫秒)';
comment on column schedule_job_log.create_time is '创建时间';


-- 用户表
CREATE TABLE tb_user (
  user_id VARCHAR2(32) NOT NULL,
  user_name varchar2(50),
  subscribe NUMBER(2, 0),
  subscribe_time varchar2(50),
  open_id varchar2(100),
  nickname varchar2(200),
  head_img_url varchar2(200),
  sex NUMBER(2, 0),
  mobile varchar2(20) NOT NULL,
  password varchar2(64),
  create_time timestamp,
  PRIMARY KEY (user_id)
);
CREATE UNIQUE INDEX index_tb_user_user_name on tb_user(user_name);

---表注释：
comment on table tb_user is '用户';
---添加字段注释：
comment on column tb_user.user_name is '用户名';
comment on column tb_user.subscribe is '关注状态（1是关注，0是未关注），未关注时获取不到其余信息';
comment on column tb_user.subscribe_time is '用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间';
comment on column tb_user.open_id is '用户的标识';
comment on column tb_user.nickname is '微信昵称';
comment on column tb_user.head_img_url is '用户头像';
comment on column tb_user.sex is '用户的性别（1是男性，2是女性，0是未知）';
comment on column tb_user.mobile is '手机号';
comment on column tb_user.password is '密码';
comment on column tb_user.create_time is '创建时间';

CREATE TABLE sys_sms_log (
  id VARCHAR2(32) NOT NULL,
  user_id VARCHAR2(32) DEFAULT NULL,
  content clob,
  mobile clob,
  stime timestamp DEFAULT NULL,
  sign VARCHAR2(32) DEFAULT NULL,
  type VARCHAR2(32) DEFAULT NULL,
  extno VARCHAR2(255) DEFAULT NULL,
  send_status NUMBER(2, 0) ,
  send_id VARCHAR2(32) DEFAULT NULL,
  invalid_num NUMBER(8, 0) DEFAULT NULL,
  success_num NUMBER(8, 0) DEFAULT NULL,
  black_num NUMBER(8, 0) DEFAULT NULL,
  return_msg VARCHAR2(50) DEFAULT NULL,
  PRIMARY KEY (id)
);

---表注释：
comment on table sys_sms_log is '短信发送日志';
---添加字段注释：
comment on column sys_sms_log.id is '主键';
comment on column sys_sms_log.user_id is '操作人ID';
comment on column sys_sms_log.content is '必填参数。发送内容（1-500 个汉字）UTF-8编码';
comment on column sys_sms_log.mobile is '必填参数。手机号码。多个以英文逗号隔开';
comment on column sys_sms_log.stime is '可选参数。发送时间，填写时已填写的时间发送，不填时为当前时间发送';
comment on column sys_sms_log.sign is '必填参数。用户签名';
comment on column sys_sms_log.type is '必填参数。固定值 pt';
comment on column sys_sms_log.extno is '可选参数。扩展码，用户定义扩展码，只能为数字';
comment on column sys_sms_log.send_status is '提交状态 0：成功';
comment on column sys_sms_log.send_id is '发送编号';
comment on column sys_sms_log.invalid_num is '无效号码数';
comment on column sys_sms_log.success_num is '成功提交数';
comment on column sys_sms_log.black_num is '黑名单数';
comment on column sys_sms_log.return_msg is '返回消息';

--  quartz自带表结构
CREATE TABLE qrtz_job_details
(
  SCHED_NAME VARCHAR2(120) NOT NULL,
  JOB_NAME  VARCHAR2(200) NOT NULL,
  JOB_GROUP VARCHAR2(200) NOT NULL,
  DESCRIPTION VARCHAR2(250) NULL,
  JOB_CLASS_NAME   VARCHAR2(250) NOT NULL,
  IS_DURABLE VARCHAR2(1) NOT NULL,
  IS_NONCONCURRENT VARCHAR2(1) NOT NULL,
  IS_UPDATE_DATA VARCHAR2(1) NOT NULL,
  REQUESTS_RECOVERY VARCHAR2(1) NOT NULL,
  JOB_DATA BLOB NULL,
  CONSTRAINT QRTZ_JOB_DETAILS_PK PRIMARY KEY (SCHED_NAME,JOB_NAME,JOB_GROUP)
);
CREATE TABLE qrtz_triggers
(
  SCHED_NAME VARCHAR2(120) NOT NULL,
  TRIGGER_NAME VARCHAR2(200) NOT NULL,
  TRIGGER_GROUP VARCHAR2(200) NOT NULL,
  JOB_NAME  VARCHAR2(200) NOT NULL,
  JOB_GROUP VARCHAR2(200) NOT NULL,
  DESCRIPTION VARCHAR2(250) NULL,
  NEXT_FIRE_TIME NUMBER(13) NULL,
  PREV_FIRE_TIME NUMBER(13) NULL,
  PRIORITY NUMBER(13) NULL,
  TRIGGER_STATE VARCHAR2(16) NOT NULL,
  TRIGGER_TYPE VARCHAR2(8) NOT NULL,
  START_TIME NUMBER(13) NOT NULL,
  END_TIME NUMBER(13) NULL,
  CALENDAR_NAME VARCHAR2(200) NULL,
  MISFIRE_INSTR NUMBER(2) NULL,
  JOB_DATA BLOB NULL,
  CONSTRAINT QRTZ_TRIGGERS_PK PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
  CONSTRAINT QRTZ_TRIGGER_TO_JOBS_FK FOREIGN KEY (SCHED_NAME,JOB_NAME,JOB_GROUP)
  REFERENCES QRTZ_JOB_DETAILS(SCHED_NAME,JOB_NAME,JOB_GROUP)
);
CREATE TABLE qrtz_simple_triggers
(
  SCHED_NAME VARCHAR2(120) NOT NULL,
  TRIGGER_NAME VARCHAR2(200) NOT NULL,
  TRIGGER_GROUP VARCHAR2(200) NOT NULL,
  REPEAT_COUNT NUMBER(7) NOT NULL,
  REPEAT_INTERVAL NUMBER(12) NOT NULL,
  TIMES_TRIGGERED NUMBER(10) NOT NULL,
  CONSTRAINT QRTZ_SIMPLE_TRIG_PK PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
  CONSTRAINT QRTZ_SIMPLE_TRIG_TO_TRIG_FK FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
  REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
);
CREATE TABLE qrtz_cron_triggers
(
  SCHED_NAME VARCHAR2(120) NOT NULL,
  TRIGGER_NAME VARCHAR2(200) NOT NULL,
  TRIGGER_GROUP VARCHAR2(200) NOT NULL,
  CRON_EXPRESSION VARCHAR2(120) NOT NULL,
  TIME_ZONE_ID VARCHAR2(80),
  CONSTRAINT QRTZ_CRON_TRIG_PK PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
  CONSTRAINT QRTZ_CRON_TRIG_TO_TRIG_FK FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
  REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
);
CREATE TABLE qrtz_simprop_triggers
(
  SCHED_NAME VARCHAR2(120) NOT NULL,
  TRIGGER_NAME VARCHAR2(200) NOT NULL,
  TRIGGER_GROUP VARCHAR2(200) NOT NULL,
  STR_PROP_1 VARCHAR2(512) NULL,
  STR_PROP_2 VARCHAR2(512) NULL,
  STR_PROP_3 VARCHAR2(512) NULL,
  INT_PROP_1 NUMBER(10) NULL,
  INT_PROP_2 NUMBER(10) NULL,
  LONG_PROP_1 NUMBER(13) NULL,
  LONG_PROP_2 NUMBER(13) NULL,
  DEC_PROP_1 NUMERIC(13,4) NULL,
  DEC_PROP_2 NUMERIC(13,4) NULL,
  BOOL_PROP_1 VARCHAR2(1) NULL,
  BOOL_PROP_2 VARCHAR2(1) NULL,
  CONSTRAINT QRTZ_SIMPROP_TRIG_PK PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
  CONSTRAINT QRTZ_SIMPROP_TRIG_TO_TRIG_FK FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
  REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
);
CREATE TABLE qrtz_blob_triggers
(
  SCHED_NAME VARCHAR2(120) NOT NULL,
  TRIGGER_NAME VARCHAR2(200) NOT NULL,
  TRIGGER_GROUP VARCHAR2(200) NOT NULL,
  BLOB_DATA BLOB NULL,
  CONSTRAINT QRTZ_BLOB_TRIG_PK PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
  CONSTRAINT QRTZ_BLOB_TRIG_TO_TRIG_FK FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
  REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
);
CREATE TABLE qrtz_calendars
(
  SCHED_NAME VARCHAR2(120) NOT NULL,
  CALENDAR_NAME  VARCHAR2(200) NOT NULL,
  CALENDAR BLOB NOT NULL,
  CONSTRAINT QRTZ_CALENDARS_PK PRIMARY KEY (SCHED_NAME,CALENDAR_NAME)
);
CREATE TABLE qrtz_paused_trigger_grps
(
  SCHED_NAME VARCHAR2(120) NOT NULL,
  TRIGGER_GROUP  VARCHAR2(200) NOT NULL,
  CONSTRAINT QRTZ_PAUSED_TRIG_GRPS_PK PRIMARY KEY (SCHED_NAME,TRIGGER_GROUP)
);
CREATE TABLE qrtz_fired_triggers
(
  SCHED_NAME VARCHAR2(120) NOT NULL,
  ENTRY_ID VARCHAR2(95) NOT NULL,
  TRIGGER_NAME VARCHAR2(200) NOT NULL,
  TRIGGER_GROUP VARCHAR2(200) NOT NULL,
  INSTANCE_NAME VARCHAR2(200) NOT NULL,
  FIRED_TIME NUMBER(13) NOT NULL,
  SCHED_TIME NUMBER(13) NOT NULL,
  PRIORITY NUMBER(13) NOT NULL,
  STATE VARCHAR2(16) NOT NULL,
  JOB_NAME VARCHAR2(200) NULL,
  JOB_GROUP VARCHAR2(200) NULL,
  IS_NONCONCURRENT VARCHAR2(1) NULL,
  REQUESTS_RECOVERY VARCHAR2(1) NULL,
  CONSTRAINT QRTZ_FIRED_TRIGGER_PK PRIMARY KEY (SCHED_NAME,ENTRY_ID)
);
CREATE TABLE qrtz_scheduler_state
(
  SCHED_NAME VARCHAR2(120) NOT NULL,
  INSTANCE_NAME VARCHAR2(200) NOT NULL,
  LAST_CHECKIN_TIME NUMBER(13) NOT NULL,
  CHECKIN_INTERVAL NUMBER(13) NOT NULL,
  CONSTRAINT QRTZ_SCHEDULER_STATE_PK PRIMARY KEY (SCHED_NAME,INSTANCE_NAME)
);
CREATE TABLE qrtz_locks
(
  SCHED_NAME VARCHAR2(120) NOT NULL,
  LOCK_NAME  VARCHAR2(40) NOT NULL,
  CONSTRAINT QRTZ_LOCKS_PK PRIMARY KEY (SCHED_NAME,LOCK_NAME)
);

create index idx_qrtz_j_req_recovery on qrtz_job_details(SCHED_NAME,REQUESTS_RECOVERY);
create index idx_qrtz_j_grp on qrtz_job_details(SCHED_NAME,JOB_GROUP);

create index idx_qrtz_t_j on qrtz_triggers(SCHED_NAME,JOB_NAME,JOB_GROUP);
create index idx_qrtz_t_jg on qrtz_triggers(SCHED_NAME,JOB_GROUP);
create index idx_qrtz_t_c on qrtz_triggers(SCHED_NAME,CALENDAR_NAME);
create index idx_qrtz_t_g on qrtz_triggers(SCHED_NAME,TRIGGER_GROUP);
create index idx_qrtz_t_state on qrtz_triggers(SCHED_NAME,TRIGGER_STATE);
create index idx_qrtz_t_n_state on qrtz_triggers(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP,TRIGGER_STATE);
create index idx_qrtz_t_n_g_state on qrtz_triggers(SCHED_NAME,TRIGGER_GROUP,TRIGGER_STATE);
create index idx_qrtz_t_next_fire_time on qrtz_triggers(SCHED_NAME,NEXT_FIRE_TIME);
create index idx_qrtz_t_nft_st on qrtz_triggers(SCHED_NAME,TRIGGER_STATE,NEXT_FIRE_TIME);
create index idx_qrtz_t_nft_misfire on qrtz_triggers(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME);
create index idx_qrtz_t_nft_st_misfire on qrtz_triggers(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME,TRIGGER_STATE);
create index idx_qrtz_t_nft_st_misfire_grp on qrtz_triggers(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME,TRIGGER_GROUP,TRIGGER_STATE);

create index idx_qrtz_ft_trig_inst_name on qrtz_fired_triggers(SCHED_NAME,INSTANCE_NAME);
create index idx_qrtz_ft_inst_job_req_rcvry on qrtz_fired_triggers(SCHED_NAME,INSTANCE_NAME,REQUESTS_RECOVERY);
create index idx_qrtz_ft_j_g on qrtz_fired_triggers(SCHED_NAME,JOB_NAME,JOB_GROUP);
create index idx_qrtz_ft_jg on qrtz_fired_triggers(SCHED_NAME,JOB_GROUP);
create index idx_qrtz_ft_t_g on qrtz_fired_triggers(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP);
create index idx_qrtz_ft_tg on qrtz_fired_triggers(SCHED_NAME,TRIGGER_GROUP);

--初始化数据
INSERT INTO SYS_ORG (ORG_NO, ORG_NAME, PARENT_NO, ORG_TYPE, STATUS, SORT, CREATE_USER_ID, CREATE_TIME) VALUES ('10', '安徽省', '0', 1, 1, 1, '1', TO_TIMESTAMP('2019-01-31 15:04:07.987000', 'YYYY-MM-DD HH24:MI:SS.FF6'));
INSERT INTO SYS_ORG (ORG_NO, ORG_NAME, PARENT_NO, ORG_TYPE, STATUS, SORT, CREATE_USER_ID, CREATE_TIME) VALUES ('1001', '合肥市', '10', 2, 1, 1, '1', TO_TIMESTAMP('2019-02-01 15:12:06.892000', 'YYYY-MM-DD HH24:MI:SS.FF6'));
INSERT INTO SYS_ORG (ORG_NO, ORG_NAME, PARENT_NO, ORG_TYPE, STATUS, SORT, CREATE_USER_ID, CREATE_TIME) VALUES ('1002', '阜阳市', '10', 2, 1, 2, '1', TO_TIMESTAMP('2019-02-01 15:12:23.328000', 'YYYY-MM-DD HH24:MI:SS.FF6'));
INSERT INTO SYS_ORG (ORG_NO, ORG_NAME, PARENT_NO, ORG_TYPE, STATUS, SORT, CREATE_USER_ID, CREATE_TIME) VALUES ('1003', '安庆市', '10', 2, 1, 3, '1', TO_TIMESTAMP('2019-02-01 15:12:43.807000', 'YYYY-MM-DD HH24:MI:SS.FF6'));
INSERT INTO SYS_ORG (ORG_NO, ORG_NAME, PARENT_NO, ORG_TYPE, STATUS, SORT, CREATE_USER_ID, CREATE_TIME) VALUES ('1004', '淮南市', '10', 2, 1, 4, '1', TO_TIMESTAMP('2019-02-01 15:13:00.703000', 'YYYY-MM-DD HH24:MI:SS.FF6'));
INSERT INTO SYS_ORG (ORG_NO, ORG_NAME, PARENT_NO, ORG_TYPE, STATUS, SORT, CREATE_USER_ID, CREATE_TIME) VALUES ('100101', '蜀山区', '1001', 3, 1, 1, '1', TO_TIMESTAMP('2019-02-01 15:13:30.873000', 'YYYY-MM-DD HH24:MI:SS.FF6'));
INSERT INTO SYS_ORG (ORG_NO, ORG_NAME, PARENT_NO, ORG_TYPE, STATUS, SORT, CREATE_USER_ID, CREATE_TIME) VALUES ('100102', '政务区', '1001', 3, 1, 2, '1', TO_TIMESTAMP('2019-02-01 15:13:46.637000', 'YYYY-MM-DD HH24:MI:SS.FF6'));
INSERT INTO SYS_ORG (ORG_NO, ORG_NAME, PARENT_NO, ORG_TYPE, STATUS, SORT, CREATE_USER_ID, CREATE_TIME) VALUES ('100201', '颍泉区', '1002', 3, 1, 1, '1', TO_TIMESTAMP('2019-02-01 15:14:02.608000', 'YYYY-MM-DD HH24:MI:SS.FF6'));
INSERT INTO SYS_ORG (ORG_NO, ORG_NAME, PARENT_NO, ORG_TYPE, STATUS, SORT, CREATE_USER_ID, CREATE_TIME) VALUES ('100202', '颍州区', '1002', 3, 1, 2, '1', TO_TIMESTAMP('2019-02-01 15:14:15.984000', 'YYYY-MM-DD HH24:MI:SS.FF6'));

INSERT INTO SYS_USER (USER_ID, USER_NAME, REAL_NAME, SEX, ORG_NO, PASSWORD, SALT, EMAIL, MOBILE, STATUS, CREATE_USER_ID, CREATE_USER_ORG_NO, CREATE_TIME) VALUES ('1', 'admin', '李鹏军', 1, '01', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', '939961241@qq.com', '15209831990', 1, '1', '0101', TO_TIMESTAMP('2019-01-24 15:44:49.000000', 'YYYY-MM-DD HH24:MI:SS.FF6'));

INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('10', '0', '系统管理', null, null, 0, 'system', 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('1001', '10', '菜单管理', 'sys/menu', 'sys:menu:list,sys:menu:info', 1, 'menu', 1);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('100101', '1001', '新增', null, 'sys:menu:save,sys:menu:select', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('100102', '1001', '修改', null, 'sys:menu:update,sys:menu:select', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('100103', '1001', '删除', null, 'sys:menu:delete', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('1002', '10', '组织机构', 'sys/org', 'sys:org:list,sys:org:info', 1, 'org', 2);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('100201', '1002', '新增', null, 'sys:org:save', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('100202', '1002', '修改', null, 'sys:org:update', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('100203', '1002', '删除', null, 'sys:org:delete', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('1003', '10', '系统参数', 'sys/config', 'sys:config:list,sys:config:info', 1, 'xitongpeizhi', 3);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('100301', '1003', '新增', null, 'sys:config:save', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('100302', '1003', '修改', null, 'sys:config:update', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('100303', '1003', '删除', null, 'sys:config:delete', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('1004', '10', '字典管理', 'sys/dictgroup', 'sys:dictgroup:list,sys:dictgroup:info,sys:dict:list,sys:dict:info', 1, 'dict', 4);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('100401', '1004', '数据字典新增', null, 'sys:dict:save', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('100402', '1004', '数据字典修改', null, 'sys:dict:update', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('100403', '1004', '数据字典删除', null, 'sys:dict:delete', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('100404', '1004', '数据字典分组新增', null, 'sys:dictgroup:save', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('100405', '1004', '数据字典分组修改', null, 'sys:dictgroup:update', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('100406', '1004', '数据字典分组删除', null, 'sys:dictgroup:delete', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('1005', '10', '文件上传', 'oss/oss', 'sys:oss:list', 1, 'oss', 5);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('100501', '1005', '云存储配置', null, 'sys:oss:config', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('100502', '1005', '上传文件', null, 'sys:oss:upload', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('100503', '1005', '删除', null, 'sys:oss:delete', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('1006', '10', '系统日志', 'sys/log', 'sys:log:list', 1, 'log', 6);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('11', '0', '权限管理', null, null, 0, 'auth', 1);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('1101', '11', '管理员列表', 'sys/user', 'sys:user:list,sys:user:info', 1, 'admin', 1);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('110101', '1101', '重置密码', null, 'sys:user:resetPw', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('110102', '1101', '新增', null, 'sys:user:save,sys:role:select', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('110103', '1101', '修改', null, 'sys:user:update,sys:role:select', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('110104', '1101', '删除', null, 'sys:user:delete', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('1102', '11', '角色管理', 'sys/role', 'sys:role:list,sys:role:info', 1, 'role', 2);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('110201', '1102', '新增', null, 'sys:role:save,sys:menu:list', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('110202', '1102', '修改', null, 'sys:role:update,sys:menu:list', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('110203', '1102', '删除', null, 'sys:role:delete', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('12', '0', '短信平台', null, null, 0, 'duanxinpingtai', 2);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('1211', '12', '短信配置', 'sys/smslog', 'sys:smslog:list', 1, 'duanxin', 1);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('121101', '1211', '修改配置', null, 'sys:smslog:config', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('121102', '1211', '删除', null, 'sys:smslog:delete', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('121103', '1211', '发送短信', null, 'sys:smslog:send', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('13', '0', '任务调度', null, null, 0, 'diaodu', 3);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('1301', '13', '定时任务', 'job/schedule', 'sys:schedule:list,sys:schedule:info', 1, 'job', 1);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('130101', '1301', '删除', null, 'sys:schedule:delete', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('130102', '1301', '暂停', null, 'sys:schedule:pause', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('130103', '1301', '恢复', null, 'sys:schedule:resume', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('130104', '1301', '立即执行', null, 'sys:schedule:run', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('130105', '1301', '日志列表', null, 'sys:schedule:log', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('130106', '1301', '新增', null, 'sys:schedule:save', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('130107', '1301', '修改', null, 'sys:schedule:update', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('14', '0', '开发工具', null, null, 0, 'dev', 4);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('1401', '14', '在线用户管理', 'sys/usertoken', 'sys:usertoken:list', 1, 'zaixian', 1);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('140101', '1401', '强制下线', null, 'sys:usertoken:offline', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('1402', '14', '缓存信息', 'sys/redis', 'sys:cache:queryAll', 1, 'redis', 2);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('140201', '1402', '删除', null, 'sys:cache:deleteCache', 2, null, 0);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('1403', '14', 'SQL监控', 'http://localhost:8888/platform-plus/druid/sql.html', null, 1, 'sql', 3);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('1404', '14', '接口文档', 'http://localhost:8888/platform-plus/doc.html', null, 1, 'interface', 4);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('1405', '14', '代码生成器', 'gen/generator', 'sys:generator:list', 1, 'code', 5);
INSERT INTO SYS_MENU (MENU_ID, PARENT_ID, NAME, URL, PERMS, TYPE, ICON, ORDER_NUM) VALUES ('140501', '1405', '生成代码', null, 'sys:generator:code', 2, null, 0);

INSERT INTO SCHEDULE_JOB (JOB_ID, BEAN_NAME, METHOD_NAME, PARAMS, CRON_EXPRESSION, STATUS, REMARK, CREATE_TIME) VALUES ('1', 'tokenTask', 'refreshToken', null, '0 0/30 * * * ?', 1, '刷新微信access_token', TO_TIMESTAMP('2019-01-31 15:00:36.000000', 'YYYY-MM-DD HH24:MI:SS.FF6'));

INSERT INTO SYS_CONFIG (ID, PARAM_KEY, PARAM_VALUE, STATUS, REMARK) VALUES ('1', 'CLOUD_STORAGE_CONFIG_KEY', '{"aliyunAccessKeyId":"","aliyunAccessKeySecret":"","aliyunBucketName":"","aliyunDomain":"","aliyunEndPoint":"","aliyunPrefix":"","qcloudBucketName":"","qcloudDomain":"","qcloudPrefix":"","qcloudSecretId":"","qcloudSecretKey":"","qiniuAccessKey":"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ","qiniuBucketName":"ios-app","qiniuDomain":"http://7xlij2.com1.z0.glb.clouddn.com","qiniuPrefix":"upload","qiniuSecretKey":"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV","type":1}', 0, '云存储配置信息');
INSERT INTO SYS_CONFIG (ID, PARAM_KEY, PARAM_VALUE, STATUS, REMARK) VALUES ('2', 'SMS_CONFIG_KEY', '{\"domain\":\"http://web.cr6868.com/asmx/smsservice.aspx?\",\"name\":\"lipengjun\",\"pwd\":\"\",\"sign\":\"【微同工作室】\",\"type\":1}', 0, '短信配置');

INSERT INTO SYS_DICT_GROUP (ID, CODE, NAME, create_time, REMARK) VALUES ('467086f66e404456b7f51cd2e68eb3ef', 'SEX', '性别', sysdate,null);
INSERT INTO SYS_DICT (ID, GROUP_ID, NAME, VALUE, SORT, STATUS, create_time, REMARK) VALUES ('6e960c35a93b4544b05a8bff42c821f7', '467086f66e404456b7f51cd2e68eb3ef', '男', '1', 1, 1, sysdate, null);
INSERT INTO SYS_DICT (ID, GROUP_ID, NAME, VALUE, SORT, STATUS, create_time, REMARK) VALUES ('5258e31b7e3348eab093a884e78eb64f', '467086f66e404456b7f51cd2e68eb3ef', '女', '2', 2, 1, sysdate, null);
INSERT INTO SYS_DICT (ID, GROUP_ID, NAME, VALUE, SORT, STATUS, create_time, REMARK) VALUES ('9892d578e13e47c7afc5fe0817a690e3', '467086f66e404456b7f51cd2e68eb3ef', '未知', '3', 3, 1, sysdate, null);

-- 账号：15209831990  密码：admin
INSERT INTO TB_USER (USER_ID, USER_NAME, subscribe, subscribe_time, open_id, nickname, head_img_url, sex, mobile, password, create_time) VALUES ('1', '李鹏军', 1, '1550742648', 'oxaA11ulr9134oBL9Xscon5at_Gc', 'Boy Genius', 'http://thirdwx.qlogo.cn/mmopen/PiajxSqBRaEI3eTLaf64kP7sBrpXKbJ7l4h6BWOlJjAQUqibVbsKotVWbzH6QnkTHYmuTMZXuUiaXVo7Ba02XbCxA/132', 1, '15209831990', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', TO_TIMESTAMP('2019-01-31 06:54:40.000000', 'YYYY-MM-DD HH24:MI:SS.FF6'));