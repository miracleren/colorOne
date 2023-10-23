package com.colorone.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.colorone.system.domain.entity.BaseFilePool;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/9/27
 * @备注：
 */
@Mapper
public interface BaseFilePoolMapper extends BaseMapper<BaseFilePool> {
    /**
     * 查询附件关联的列表，数据用于附件组件
     *
     * @param refName
     * @param refId
     * @return
     */
    List<Map> selectBaseFilePoolList(@Param("refName") String refName, @Param("refId") String refId);

    /**
     * 更新记录的关联id
     *
     * @param tempId
     * @param recordId
     * @return
     */
    Integer updateFilePoolRefId(@Param("recordId") String recordId, @Param("tempId") String tempId, @Param("updateBy") String updateBy);
}
