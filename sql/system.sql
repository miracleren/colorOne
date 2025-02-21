-- ----------------------------
-- 1、用户基础信息表
-- ----------------------------
drop table if exists base_user;
create table base_user
(
    user_id     bigint(20)  not null auto_increment comment '用户ID',
    dept_id     bigint(20)   default null comment '部门ID',
    user_name   varchar(30) not null comment '用户账号',
    nick_name   varchar(30) not null comment '用户昵称',
    email       varchar(50)  default '' comment '用户邮箱',
    phone       varchar(11)  default '' comment '手机号码',
    sex         varchar(1)      default '0' comment '用户性别（0男 1女 2未知）',
    password    varchar(100) default '' comment '密码',
    status      int(1)      default 0 comment '帐号状态（0正常 1停用）',
    login_ip    varchar(128) default '' comment '最后登录IP',
    login_date  datetime comment '最后登录时间',
    remark      varchar(500) default null comment '备注',

    del_flag    int(1)      default 0 comment '删除标志（0代表存在 1代表删除）',
    create_by   varchar(64)  default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64)  default '' comment '更新者',
    update_time datetime comment '更新时间',

    primary key (user_id)
) engine = innodb
  auto_increment = 1000 comment = '用户信息表';




-- ----------------------------
-- 2、菜单接口权限信息表
-- ----------------------------
create table base_menu
(
    menu_id     bigint(10)   not null auto_increment comment '菜单id',
    menu_name   varchar(50) not null comment '菜单接口名称',
    parent_id   bigint(19)    default 0 comment '父级id',
    order_num   int(4)    default 0 comment '显示顺序',
    menu_type   varchar(1) comment '菜单类型（c目录 m菜单 b操作）',
    component   varchar(255) comment '组件路径',
    path        varchar(200) default '' comment '路由地址、接口地址',
    visible     int(1)   default 0 comment '菜单是否显示（0显示 1隐藏）',
    status      int(1)   default 0 comment '菜单状态（0正常 1停用）',
    icon        varchar(100) default '#' comment '菜单图标',
    remark      varchar(500) comment '备注',

    del_flag    int(1)      default 0 comment '删除标志（0代表存在 1代表删除）',
    create_by   varchar(64)  default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64)  default '' comment '更新者',
    update_time datetime comment '更新时间',

    primary key(menu_id)
)engine = innodb
  auto_increment = 1000 comment = '菜单接口权限信息表';

-- ----------------------------
-- 添加框架默认菜单
-- ----------------------------
-- insert into base_menu
-- values (1, '系统管理', 0, 100, 'c', 'system', 'system', '0', '0', '#', '系统管理目录',0, 'admin', sysdate(), 'admin', sysdate());
-- insert into base_menu
-- values (2, '用户管理', 0, 100, 'm', 'system', 'user', '0', '0', '#', '系统管理用户管理',0, 'admin', sysdate(), 'admin', sysdate());
-- insert into base_menu
-- values (3, '菜单管理', 0, 101, 'm', 'system', 'menu', '0', '0', '#', '系统管理菜单管理',0, 'admin', sysdate(), 'admin', sysdate());



-- ----------------------------
-- 3、用户角色表
-- ----------------------------
create table base_role (
  role_id              bigint(20)      not null auto_increment    comment '角色ID',
  role_name            varchar(30)     not null                   comment '角色名称',
  role_key             varchar(100)    not null                   comment '角色标识',
  role_sort            int(4)          not null                   comment '显示顺序',
  status             int(1)   default 0  not null       comment '角色状态（0正常 1停用）',
  scope         varchar(128) null comment '数据权限',
  remark               varchar(500)    default null               comment '备注',

  del_flag    int(1)      default 0 comment '删除标志（0代表存在 1代表删除）',
  create_by   varchar(64)  default '' comment '创建者',
  create_time datetime comment '创建时间',
  update_by   varchar(64)  default '' comment '更新者',
  update_time datetime comment '更新时间',

  primary key (role_id)
)engine=innodb
 auto_increment = 1000 comment = '用户角色表';
-- ----------------------------
-- 添加框架初始角色
-- ----------------------------
-- insert into base_role values('1', '系统超级管理员', 'A', 1,1,'系统超级管理员角色',0,'', sysdate(), 'OneAdmin', null);


-- ----------------------------
-- 4、用户与角色关联表
-- ----------------------------
create table base_user_role (
  user_id   bigint(20) not null comment '用户ID',
  role_id   bigint(20) not null comment '角色ID',

  del_flag    int(1)      default 0 comment '删除标志（0代表存在 1代表删除）',
  create_by   varchar(64)  default '' comment '创建者',
  create_time datetime comment '创建时间',
  update_by   varchar(64)  default '' comment '更新者',
  update_time datetime comment '更新时间',

  primary key(user_id, role_id)
) engine=innodb comment = '用户和角色关联表';
-- INSERT INTO colorxx.base_user_role (user_id, role_id, del_flag, create_by, create_time, update_by, update_time) VALUES (1, 1, 0, 'OneAdmin', '2023-06-29 15:46:32', '', null);



-- ----------------------------
-- 5、角色与菜单关联表
-- ----------------------------
create table base_role_menu (
  role_id   bigint(20) not null comment '角色ID',
  menu_id   bigint(20) not null comment '菜单ID',

  del_flag    int(1)      default 0 comment '删除标志（0代表存在 1代表删除）',
  create_by   varchar(64)  default '' comment '创建者',
  create_time datetime comment '创建时间',
  update_by   varchar(64)  default '' comment '更新者',
  update_time datetime comment '更新时间',

  primary key(role_id, menu_id)
) engine=innodb comment = '角色与菜单关联表';


-- -----------------------------
-- 6、字典数据表
-- -----------------------------
create table base_dict
(
    dict_id     bigint(20) not null auto_increment comment '字典主键',
    parent_id   bigint(20)              comment '父主键',
    dict_type   varchar(100) default '' comment '字典类型',

    dict_label  varchar(100) default '' comment '字典标签',
    dict_value  varchar(100) default '' comment '字典键值',
    dict_sort   int(4)    default 0 comment '字典排序',
    style       varchar(200) default '' comment '字典样式',
    status      int(1)    default 0 comment '状态（0正常 1停用）',

     remark     varchar(200)    default null               comment '备注',

    del_flag    int(1)    default 0 comment '删除标志（0代表存在 1代表删除）',
    create_by   varchar(64)  default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64)  default '' comment '更新者',
    update_time datetime comment '更新时间',

    primary key (dict_id)
) engine = innodb
  auto_increment = 1000 comment = '字典数据表';


-- -----------------------------
-- 7、部门数据表
-- -----------------------------
create table base_dept
(
    dept_id     bigint(20) not null auto_increment comment '部门主键',
    parent_id   bigint(20) comment '父主键',
    dept_name   varchar(64) not null comment '部门名称',
    ancestors   varchar(1024) comment '父级编号',
    leader      varchar(64) comment '负责人',
    contact     varchar(64) comment '联系方式',
    dept_sort   int(4)       default 0 comment '部门排序',
    status      int(1)       default 0 comment '状态（0正常 1停用）',
    remark      varchar(200) default null comment '备注',



    del_flag    int(1)       default 0 comment '删除标志（0代表存在 1代表删除）',
    create_by   varchar(64)  default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64)  default '' comment '更新者',
    update_time datetime comment '更新时间',

    primary key (dept_id)
) engine = innodb
  auto_increment = 1000 comment = '部门数据表';


-- -----------------------------
-- 8、公告数据表
-- -----------------------------
 create table base_notice
(
    notice_id      bigint(20)  not null auto_increment comment '公告主键',
    notice_title   varchar(64) not null comment '公告标题',
    notice_type    varchar(1)  not null comment '公告类型',
    notice_content varchar(1024) comment '公告内容',

    start_date     datetime comment '公告有效开始时间',
    end_date       datetime comment '公告有效结束时间',

    del_flag       int(1)      default 0 comment '删除标志（0代表存在 1代表删除）',
    create_by      varchar(64) default '' comment '创建者',
    create_time    datetime comment '创建时间',
    update_by      varchar(64) default '' comment '更新者',
    update_time    datetime comment '更新时间',

    primary key (notice_id)
) engine = innodb
  auto_increment = 1000 comment = '公告数据表';

-- -----------------------------
-- 8、公告阅读标记数据表
-- -----------------------------
create table base_notice_read
(
    read_id     bigint(20) not null auto_increment comment '公告阅读主键',
    notice_id   bigint(20) comment '公告编号',
    user_id     bigint(20) not null comment '已读用户编号',

    del_flag    int(1)      default 0 comment '删除标志（0代表存在 1代表删除）',
    create_by   varchar(64) default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64) default '' comment '更新者',
    update_time datetime comment '更新时间',

    primary key (read_id)
) engine = innodb
  auto_increment = 1000 comment = '公告阅读标记数据表';


-- -----------------------------
-- 9、文件池数据表
-- -----------------------------
create table base_file_pool
(
    file_id     varchar(36)  not null comment '文件编号GUID',
    file_name   varchar(128)  not null comment '文件名称',
    save_path   varchar(256)   not null comment '文件保存路径',
    ref_name    varchar(128) not null comment '关联数据表名称',
    ref_id      varchar(36)  not null comment '关联数据表编号',
    class_name  varchar(64)  comment '文件分类名称',
    class_value        varchar(64) comment '文件分类字典值',


    del_flag    int(1)      default 0 comment '删除标志（0代表存在 1代表删除）',
    create_by   varchar(64) default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64) default '' comment '更新者',
    update_time datetime comment '更新时间',

    primary key (file_id)
) engine = innodb comment = '文件池数据表';


-- -----------------------------
-- 10、系统登录日志表
-- -----------------------------
create table base_log_login
(
    log_id     bigint(20)  not null auto_increment comment '登录记录编号',
    user_name  varchar(30) not null comment '用户账号',
    ip_address varchar(50) comment '登录IP地址',
    browser    varchar(50) comment '浏览器',
    os         varchar(50) comment '系统',
    status     int(1) comment '登录状态',
    msg        varchar(255) comment '登录日志说明',
    log_time   datetime comment '日志时间',
    primary key (log_id)
) engine = innodb
  auto_increment = 1000 comment = '系统登录日志表';