package com.colorone.system.domain;

import lombok.Data;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/8/29
 * @备注： 组件item实体类
 */
@Data
public class LabelItem {
    /**
     * 节点名称
     */
    private String label;

    /**
     * 节点值
     */
    private Object value;

    /**
     * 样式
     */
    private String style;

    /**
     * 状态
     */
    private Integer disabled;
}
