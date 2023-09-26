package com.colorone.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/9/25
 * @备注：公告阅读标记数据表
 */
@Data
@ToString
@NoArgsConstructor
@TableName("base_notice_read")
public class BaseNoticeRead {
    /**
     * comment '公告阅读主键' bigint(20) not null auto_increment
     */
    @TableId(value = "read_id", type = IdType.AUTO)
    private Long read_id;

    /**
     * comment '公告编号' bigint(20)
     */
    private Long notice_id;

    /**
     * comment '已读用户编号' bigint(20) not null
     */
    private Long user_id;
}
