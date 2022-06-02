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
    status      bigint(1)      default 0 comment '帐号状态（0正常 1停用）',
    login_ip    varchar(128) default '' comment '最后登录IP',
    login_date  datetime comment '最后登录时间',
    remark      varchar(500) default null comment '备注',

    del_flag    bigint(1)      default 0 comment '删除标志（0代表存在 1代表删除）',
    create_by   varchar(64)  default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64)  default '' comment '更新者',
    update_time datetime comment '更新时间',

    primary key (user_id)
) engine = innodb
  auto_increment = 1000 comment = '用户信息表';

-- ----------------------------
-- 添加框架默认用户
-- ----------------------------
insert into base_user
values (1, 0, 'admin', '颜一', 'miracleren@gmail.com', '13888888888', '1',
        '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '127.0.0.1', sysdate(), '初始管理员', 0,
        'admin',sysdate(), 'admin', sysdate());

