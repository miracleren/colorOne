package com.colorone.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.colorone.system.domain.entity.BaseDept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/8/31
 * @备注：
 */
@Mapper
public interface BaseDeptMapper extends BaseMapper<BaseDept> {
    /**
     * 查询部门列表数据
     *
     * @param dept
     * @return
     */
    List<BaseDept> selectDeptList(BaseDept dept);
}
