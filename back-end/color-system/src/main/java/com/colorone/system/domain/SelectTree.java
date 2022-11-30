package com.colorone.system.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * selectTree生成树数据树结构实体类
 *
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2022/11/29
 */
@Data
public class SelectTree implements Serializable {

    /**
     * 节点ID
     */
    private Object id;

    /**
     * 节点名称
     */
    private String label;

    /**
     * 父节点ID
     */
    private Object parentId;

    /**
     * 子节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<SelectTree> children;

    public SelectTree() {

    }

    public SelectTree(Map menu) {
        this.id = menu.get("menuId");
        this.label = menu.get("menuName").toString();
        this.parentId = menu.get("parentId");
        this.children = ((List<Map>) menu.get("children")).stream().map(SelectTree::new).collect(Collectors.toList());
    }


}
