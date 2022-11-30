package com.colorone.system.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * Entity基础类
 *
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2022/11/29
 */

@Data
public class BaseEntity {

    /**
     * (使用mybatis-plus确保继承该实体类的表一定要有以下这个几个字段)
     * 删除标志（0代表存在 1代表删除）
     */
    @TableLogic
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    private Integer delFlag;

    /**
     * 信息创建者
     */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;


    /**
     * 信息创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 信息更新者
     */
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 信息更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 是否自动填充通用值字段值
     */
    @TableField(exist = false)
    private Boolean isAutoField = true;

    /**
     * 请求附加参数
     */
    @TableField(exist = false)
    @JsonIgnore
    private Map<String, Object> params;

}
