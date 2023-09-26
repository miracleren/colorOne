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
 * 菜单实体类
 *
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2022/11/28
 * @备注：菜单接口权限信息表
 */
@Data
@ToString
@NoArgsConstructor
@TableName("base_menu")
public class BaseMenu extends BaseEntity {
    /**
     * comment '菜单id'   bigint(10)   not null autoIncrement
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    Long menuId;

    /**
     * comment '菜单接口名称' varchar(50) not null
     */
    String menuName;

    /**
     * comment '父级id'   bigint(19)    default 0
     */
    Integer parentId;

    /**
     * comment '显示顺序'   int(4)    default 0
     */
    Integer orderNum;

    /**
     * comment '菜单类型（c目录 m菜单 b按钮 a接口）'  varchar(1)
     */
    String menuType;

    /**
     * comment '组件路径'   varchar(255)
     */
    String component;

    /**
     * comment '路由地址、接口地址'  varchar(200)
     */
    String path;

    /**
     * comment '菜单是否显示（0显示 1隐藏）'    int(1)   default 0
     */
    Integer visible;

    /**
     * comment '菜单状态（0正常 1停用）'  int(1)   default 0
     */
    Integer status;

    /**
     * comment '菜单图标'   varchar(100) default '#'
     */
    String icon;

    /**
     * comment '备注' varchar(500)
     */
    String remark;


    /**
     * 状态名称
     */
    @TableField(exist = false)
    private String statusName;

    /**
     * 菜单类型名称
     */
    @TableField(exist = false)
    private String menuTypeName;
}
