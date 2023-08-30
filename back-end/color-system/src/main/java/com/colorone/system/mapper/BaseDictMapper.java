package com.colorone.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.colorone.system.domain.LabelItem;
import com.colorone.system.domain.entity.BaseDict;
import org.apache.ibatis.annotations.Mapper;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/8/1
 * @备注：
 */
@Mapper
public interface BaseDictMapper extends BaseMapper<BaseDict> {
    /**
     * 根据字典类型获取字典数据
     *
     * @return
     */
    List<LabelItem> selectComDictByType(String type);

    /**
     * 查询字典表数据
     *
     * @param dict
     * @return
     */
    List<BaseDict> selectDictList(BaseDict dict);

    /**
     * 查询子字典数据列表
     *
     * @param dictId 字典父ID
     * @return 字典列表
     */
    List<BaseDict> selectBaseDictChildren(Long dictId);

    /**
     * 根据父ID删除子字典列表
     *
     * @param parentId 父ID
     * @return 执行行数
     */
    Integer deleteChildrenById(Long parentId);


    /**
     * 查询字典子数据
     *
     * @param dicts 父数据列表
     * @return 子数据列表
     */
    List<BaseDict> selectDictChildrenList(List<BaseDict> dicts);
}
