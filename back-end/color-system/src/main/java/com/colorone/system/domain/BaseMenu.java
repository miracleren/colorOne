package com.colorone.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 菜单实体类
 *
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2022/11/28
 */
@Data
@ToString
@NoArgsConstructor
@TableName("base_menu")
public class BaseMenu {
    /**
     * '菜单id'
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

    /**
     * '菜单接口名称'
     */
    private String menuName;

    /**
     * '父级id'
     */
    private Integer parentId;

    /**
     * '显示顺序'
     */
    private Integer orderNum;

    /**
     * '菜单类型（c目录 m菜单 b按钮 a接口）'
     */
    private String menuType;

    /**
     * '组件路径'
     */
    private String component;

    /**
     * '路由地址、接口地址'
     */
    private String path;

    /**
     * '菜单是否显示（0显示 1隐藏）'
     */
    private String visible;

    /**
     * '菜单状态（0正常 1停用）'
     */
    private String status;

    /**
     * '菜单图标'
     */
    private String icon;

    /**
     * '备注'
     */
    private String remark;


    //del_flag    bigint(1)      default 0 comment '删除标志（0代表存在 1代表删除）',
    //create_by   varchar(64)  default '' comment '创建者',
    //create_time datetime comment '创建时间',
    //update_by   varchar(64)  default '' comment '更新者',
    //update_time datetime comment '更新时间',

}
