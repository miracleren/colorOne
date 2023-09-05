package com.colorone.system.service;

import com.colorone.system.domain.LabelItem;
import com.colorone.system.domain.entity.BaseDict;

import java.util.List;
import java.util.Map;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/8/1
 * @备注：
 */
public interface BaseDictService {

    /**
     * 查询字典数据列表
     *
     * @param dict dict 字典实体类
     * @return 字典列表
     */
    List<BaseDict> getDictList(BaseDict dict);

    /**
     * 新增字典数据
     *
     * @param dict 字典实体类
     * @return 成功失败
     */
    Integer addBaseDict(BaseDict dict);

    /**
     * 修改字典数据
     *
     * @param dict 字典实体类
     * @return 成功失败
     */
    Integer editBaseDict(BaseDict dict);

    /**
     * 删除字典数据
     *
     * @param dictId 字典键值
     * @return 成功失败
     */
    Integer deleteBaseDict(Long dictId);

    /**
     * 根据字典类型获取字典数据，数据类型关联select组件
     *
     * @param type 字典类型标识
     * @return 字典select数据
     */
    List<LabelItem> getSelectDictByType(String type);


    /**
     * 查询子字典数据列表
     *
     * @param dictId 字典父ID
     * @return 字典列表
     */
    List<BaseDict> getBaseDictChildren(Long dictId);

    /**
     * 查询字典树数据
     *
     * @param dict dict 字典实体类
     * @return 字典列表
     */
    Map getDictTreeList(BaseDict dict);
}
