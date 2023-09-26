package com.colorone.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.colorone.common.domain.core.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/9/25
 * @备注：公告数据表
 */
@Data
@ToString
@NoArgsConstructor
@TableName("base_notice")
public class BaseNotice extends BaseEntity {
    /**
     * comment '公告主键' bigint(20)  not null auto_increment
     */
    @TableId(value = "notice_id", type = IdType.AUTO)
    private Long noticeId;

    /**
     * comment '公告标题' varchar(64) not null
     */
    private String noticeTitle;

    /**
     * comment '公告类型' varchar(1)  not null
     */
    private String noticeType;

    /**
     * comment '公告内容' varchar(1024)
     */
    private String noticeContent;

    /**
     * comment '公告有效开始时间' datetime
     */

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    /**
     * comment '公告有效结束时间' datetime
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    /**
     * 公告类型名称
     */
    @TableField(exist = false)
    private String noticeTypeName;

    /**
     * 公告创建人名称
     */
    @TableField(exist = false)
    private String createUserName;
}
