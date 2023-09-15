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
 * @date：2023/8/31
 * @备注：部门数据表
 */
@Data
@ToString
@NoArgsConstructor
@TableName("base_dept")
public class BaseDept extends BaseEntity {
    /**
     * comment '部门主键'   bigint(20) not null auto_increment
     */
    @TableId(value = "dept_id", type = IdType.AUTO)
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
     * comment '负责人'    varchar(64)
     */
    String leader;

    /**
     * comment '联系方式'   varchar(64)
     */
    String contact;

    /**
     * comment '部门排序'   int(4)       default 0
     */
    Integer deptSort;

    /**
     * comment '状态（0正常 1停用）'    int(1)       default 0
     */
    Integer status;

    /**
     * comment '备注' varchar(200) default null
     */
    String remark;


    /**
     * 状态名称
     */
    @TableField(exist = false)
    private String statusName;

}
