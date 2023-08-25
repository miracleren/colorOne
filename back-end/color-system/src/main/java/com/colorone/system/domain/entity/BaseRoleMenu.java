package com.colorone.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.colorone.common.domain.core.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/8/25
 * @备注：
 */
@Data
@ToString
@NoArgsConstructor
@TableName("base_role_menu")
public class BaseRoleMenu extends BaseEntity {

    /**
     * comment '角色ID' bigint(20) not null
     */
    Long roleId;

    /**
     * comment '菜单ID' bigint(20) not null
     */
    Long menuId;
}
