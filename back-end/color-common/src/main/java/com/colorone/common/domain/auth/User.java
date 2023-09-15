package com.colorone.common.domain.auth;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2022/6/2
 * @备注：登录用户基础信息表
 */
@Data
@ToString
@NoArgsConstructor
@TableName("base_user")
public class User implements Serializable {
    /**
     * comment '用户ID'   bigint(20)  not null autoIncrement
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    Long userId;

    /**
     * comment '部门ID'   bigint(20)   default null
     */
    Integer deptId;

    /**
     * comment '用户账号'   varchar(30) not null
     */
    String userName;

    /**
     * comment '用户昵称'   varchar(30) not null
     */
    String nickName;

    /**
     * comment '用户邮箱'   varchar(50)  default ''
     */
    String email;

    /**
     * comment '手机号码'   varchar(11)  default ''
     */
    String phone;

    /**
     * comment '用户性别（0男 1女 2未知）'    varchar(1)      default '0'
     */
    String sex;

    /**
     * comment '密码' varchar(100) default ''
     */
    String password;

    /**
     * comment '帐号状态（0正常 1停用）'  bigint(1)      default 0
     */
    Integer status;

    /**
     * comment '最后登录IP' varchar(128) default ''
     */
    String loginIp;

    /**
     * comment '最后登录时间' datetime
     */
    Date loginDate;

    /**
     * comment '备注' varchar(500) default null
     */
    String remark;

}
