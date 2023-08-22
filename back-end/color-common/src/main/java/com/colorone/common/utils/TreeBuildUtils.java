package com.colorone.common.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.colorone.common.utils.data.MapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2022/11/29 生成树工具类
 */
public class TreeBuildUtils {

    //记录主编号名称
    private String idKey = "id";
    //记录父编号名称
    private String parentIdKey = "parentId";
    //记录子列表名称
    private String childrenKey = "children";

    //生成树数据列表
    List<Map> treeData = new ArrayList<>();

    /**
     * 关键值返回
     */
    List<String> curKeys = new ArrayList<>();
    List<String> newKeys = new ArrayList<>();

    /**
     * 创建对象
     *
     * @return
     */
    public static TreeBuildUtils init() {
        return new TreeBuildUtils();
    }

    public TreeBuildUtils setRel(String curKey, String newKey) {
        curKeys.add(curKey);
        newKeys.add(newKey);
        return this;
    }

    /**
     * 设置tree数据标签
     *
     * @param idKey
     * @param parentIdKey
     * @param childrenKey
     * @return
     */
    public TreeBuildUtils setKey(String idKey, String parentIdKey, String childrenKey) {
        if (idKey != null)
            this.idKey = idKey;
        if (parentIdKey != null)
            this.parentIdKey = parentIdKey;
        if (childrenKey != null)
            this.childrenKey = childrenKey;
        return this;
    }

    /**
     * list map 生成树
     *
     * @param data
     * @return
     */
    public List<Map> build(List<Map> data) {
        if (data != null)
            treeData = data;

        List<Map> treeList = new ArrayList<>();
        List<Object> tempList = new ArrayList<>();
        for (Map t : treeData) {
            tempList.add(t.get(idKey));
        }
        for (Map row : treeData) {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(row.get(parentIdKey))) {
                Map newRow = format(row);
                treeList.add(newRow);
                recursionFn(newRow, row.get(idKey));
            }
        }
        if (treeList.isEmpty()) {
            treeList = data == null ? new ArrayList<>() : data;
        }
        return treeList;
    }


    /**
     * 无参
     *
     * @return
     */
    public List<Map> build() {
        return build(null);
    }

    /**
     * 递归列表
     */
    private void recursionFn(Map row, Object id) {
        // 得到子节点列表
        List<Map> childList = MapUtils.toFilter(treeData, parentIdKey, id);
        if (!childList.isEmpty()) {
            List<Map> newRows = new ArrayList<>();
            for (Map tChild : childList) {
                Map newRow = format(tChild);
                newRows.add(newRow);
                recursionFn(newRow, tChild.get(idKey));
            }
            row.put(childrenKey, newRows);
        }
    }

    /**
     * 转换格式
     *
     * @param row
     * @return
     */
    private Map format(Map row) {
        Map newRow = new HashMap<>();
        if (!curKeys.isEmpty()) {
            for (int i = 0; i < curKeys.size(); i++) {
                newRow.put(newKeys.get(i), row.get(curKeys.get(i)));
            }
            return newRow;
        } else
            return row;
    }


    /**
     * list bean转换成list map
     *
     * @param data
     * @param <T>
     * @return
     */
    public <T> TreeBuildUtils toListMap(List<T> data) {
        treeData = JSON.parseArray(JSONObject.toJSONString(data), Map.class);
        return this;
    }


}
