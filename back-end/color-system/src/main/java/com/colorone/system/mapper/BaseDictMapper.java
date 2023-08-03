package com.colorone.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.colorone.system.domain.entity.BaseDict;
import org.apache.ibatis.annotations.Mapper;

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
    List<Map<String, Object>> selectComDictByType(String type);

    /**
     * 查询字典表数据
     *
     * @param dict
     * @return
     */
    List<BaseDict> selectDictList(BaseDict dict);
}
