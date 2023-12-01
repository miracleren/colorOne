package com.colorone.common.domain.Log;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.Map;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/11/28
 * @备注：系统登录日志表
 */

@Data
@ToString
@NoArgsConstructor
@TableName("base_log_login")
public class BaseLogLogin {

    /**
     * comment '登录记录编号' bigint(20)  not null
     */
    @TableId(value = "log_id", type = IdType.AUTO)
    private Long logId;

    /**
     * comment '用户账号' varchar(30) not null
     */
    private String userName;

    /**
     * comment '登录IP地址' varchar(50)
     */
    private String ipAddress;

    /**
     * comment '浏览器' varchar(50)
     */
    private String browser;

    /**
     * comment '系统' varchar(50)
     */
    private String os;

    /**
     * comment '登录状态' Int(1)
     */
    private Integer status;

    /**
     * comment '登录日志说明' varchar(255)
     */
    private String msg;

    /**
     * comment '日志时间' datetime
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date logTime;


    /**
     * 请求附加参数
     */
    @TableField(exist = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Map<String, Object> params;
}