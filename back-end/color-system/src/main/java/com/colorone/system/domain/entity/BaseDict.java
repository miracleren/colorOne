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
 * @date：2023/8/1
 * @备注：数据字典表
 */
@Data
@ToString
@NoArgsConstructor
@TableName("base_dict")
public class BaseDict extends BaseEntity {
    /**
     * comment '字典主键' bigint(20) not null autoIncrement
     */
    @TableId(value = "dict_id", type = IdType.AUTO)
    private Long dictId;

    /**
     * comment '父主键' bigint(20) not null
     */
    private Long parentId;

    /**
     * comment '字典类型' varchar(100) default ''
     */
    private String dictType;

    /**
     * comment '字典标签' varchar(100) default ''
     */
    private String dictLabel;

    /**
     * comment '字典键值' varchar(100) default ''
     */
    private String dictValue;

    /**
     * comment '字典排序' int(4)    default 0
     */
    private Integer dictSort;

    /**
     * comment '字典样式' varchar(200) default ''
     */
    private String style;

    /**
     * comment '状态（0正常 1停用）' int(1)    default 0
     */
    private Integer status;

    /**
     * comment '备注'    varchar(200)    default null
     */
    private String remark;

    /**
     * 状态名称
     */
    @TableField(exist = false)
    private String statusName;
}
