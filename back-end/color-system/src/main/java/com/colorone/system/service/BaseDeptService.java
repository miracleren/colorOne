package com.colorone.system.service;

import com.colorone.system.domain.entity.BaseDept;

import java.util.List;
import java.util.Map;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/8/31
 * @备注：
 */
public interface BaseDeptService {
    /**
     * 获取部门树列表
     *
     * @param dept 部门实体类
     * @return 列表
     */
    List<BaseDept> getBaseDeptList(BaseDept dept);

    /**
     * 获取部门树列表
     *
     * @param dept 部门实体
     * @return list map
     */
    List<Map> getDeptTreeList(BaseDept dept);

    /**
     * 新增部门数据
     *
     * @param dept 部门实体类
     * @return 成功失败
     */
    Integer addBaseDept(BaseDept dept);

    /**
     * 修改部门数据
     *
     * @param dept 部门实体类
     * @return 成功失败
     */
    Integer editBaseDept(BaseDept dept);

    /**
     * 删除部门数据
     *
     * @param deptId 部门键值
     * @return 成功失败
     */
    Integer deleteBaseDept(Long deptId);

    /**
     * 停用或启用部门
     *
     * @param dept
     * @return
     */
    Integer setBaseDeptStatus(BaseDept dept);
}
