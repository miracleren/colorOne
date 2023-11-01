package com.colorone.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.colorone.common.domain.core.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/9/27
 * @备注：文件池数据表
 */
@Data
@ToString
@NoArgsConstructor
@TableName("base_file_pool")
public class BaseFilePool extends BaseEntity {

    /**
     * comment '文件编号GUID' varchar(36)  not null
     */
    @TableId(value = "file_id", type = IdType.ASSIGN_UUID)
    private String fileId;

    /**
     * comment '文件名称' varchar(128)  not null
     */
    private String fileName;

    /**
     * comment '文件保存路径' varchar(256)   not null
     */
    private String savePath;

    /**
     * comment '关联数据表名称' varchar(128) not null
     */
    private String refName;

    /**
     * comment '关联数据表编号' varchar(36)  not null
     */
    private String refId;

    /**
     * comment '文件类别名称' varchar(64)
     */
    private String className;

    /**
     * comment '文件分类字典值' varchar(64)
     */
    private String classValue;
}
