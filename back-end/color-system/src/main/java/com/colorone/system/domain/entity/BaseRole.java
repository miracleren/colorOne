package com.colorone.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.colorone.common.domain.core.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/6/29
 * @备注：用户角色表
 */
@Data
@ToString
@NoArgsConstructor
@TableName("base_role")
public class BaseRole extends BaseEntity {

    /**
     * comment '角色ID'   bigint(20)      not null autoIncrement
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    Long roleId;

    /**
     * comment '角色名称'   varchar(30)     not null
     */
    String roleName;

    /**
     * comment '角色标识'   varchar(100)    not null
     */
    String roleKey;

    /**
     * comment '显示顺序'   int(4)          not null
     */
    Integer roleSort;

    /**
     * comment '角色状态（0正常 1停用）'  int(1)         not null
     */
    Integer status;

    /**
     * comment '数据权限' varchar(128) null
     */
    String scope;

    /**
     * comment '备注' varchar(500)    default null
     */
    String remark;

    /**
     * 状态名称
     */
    @TableField(exist = false)
    private String statusName;

}
