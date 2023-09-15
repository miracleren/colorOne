package com.colorone.common.domain.auth;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/9/14
 * @备注：登录用户部门信息实体类
 */
@Data
@ToString
@NoArgsConstructor
@TableName("base_dept")
public class Dept implements Serializable {
    /**
     * comment '部门主键'   bigint(20) not null auto_increment
     */
    Long deptId;

    /**
     * comment '父主键'    bigint(20)
     */
    Long parentId;

    /**
     * comment '部门名称'   varchar(64) not null
     */
    String deptName;

    /**
     * comment '父级编号'  varchar(1024)
     */
    String ancestors;

    /**
     * 部门孩子编号
     */
    @TableField(exist = false)
    String children;

}
