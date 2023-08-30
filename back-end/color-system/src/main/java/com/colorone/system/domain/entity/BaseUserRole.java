package com.colorone.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.colorone.common.domain.core.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/6/29
 * @备注：
 */
@Data
@ToString
@NoArgsConstructor
@TableName("base_user_role")
public class BaseUserRole extends BaseEntity {

    /**
     * comment '用户ID'  bigint(20) not null
     */
    Long userId;

    /**
     * comment '角色ID'  bigint(20) not null
     */
    Long roleId;

}
