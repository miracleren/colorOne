package com.colorone.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.colorone.common.domain.core.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2022/6/2
 * @备注：用户基础信息表
 */
@Data
@ToString
@NoArgsConstructor
@TableName("base_user")
public class BaseUser extends BaseEntity {
    /**
     * comment '用户ID'   bigint(20)  not null autoIncrement
     */
    @TableId(value = "user_id", type = IdType.AUTO)
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

    /**
     * comment '帐号状态（0正常 1停用）'  int(1)      default 0
     */
    Integer status;

    /**
     * comment '最后登录IP' varchar(128) default ''
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String loginIp;

    /**
     * comment '最后登录时间' datetime
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Date loginDate;

    /**
     * comment '备注' varchar(500) default null
     */
    String remark;


    /**
     * 用户角色组
     */
    @TableField(exist = false)
    private Long[] roles;

    /**
     * 用户新密码
     */
    @TableField(exist = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String newPassword;

}
