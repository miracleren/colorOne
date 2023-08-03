package com.colorone.system.service;

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
     * 根据字典类型获取字典数据
     *
     * @param type
     * @return
     */
    List<Map<String, Object>> getSelectDictByType(String type);

    /**
     * 查询字典数据列表
     *
     * @param dict
     * @return
     */
    List<BaseDict> getDictList(BaseDict dict);
}
