package com.colorone.common.domain.core;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/6/29 备注：基础实体类 继承该类必须表包含以下通用字段
 */
@Data
public class BaseEntity implements Serializable {

    /**
     * default 0 comment '删除标志（0代表存在 1代表删除）'
     */
    @TableLogic
    @TableField(value = "del_flag", fill = FieldFill.UPDATE)
    @JsonIgnore
    Integer delFlag;

    /**
     * varchar(64)  default '' comment '创建者'
     */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    @JsonIgnore
    String createBy;

    /**
     * datetime comment '创建时间'
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    Date createTime;

    /**
     * varchar(64)  default '' comment '更新者'
     */
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    @JsonIgnore
    String updateBy;

    /**
     * datetime comment '更新时间'
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    @JsonIgnore
    Date updateTime;


    /**
     * 是否自动填充通用值字段值
     */
    @TableField(exist = false)
    @JsonIgnore
    private Boolean autoFill = true;

    /**
     * 请求附加参数
     */
    @TableField(exist = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Map<String, Object> params;
}
