package com.colorone.system.service.impl;

import com.colorone.common.utils.TreeBuildUtils;
import com.colorone.system.domain.entity.BaseDept;
import com.colorone.system.mapper.BaseDeptMapper;
import com.colorone.system.mapper.BaseUserMapper;
import com.colorone.system.service.BaseDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/8/31
 * @备注：
 */
@Service
public class BaseDeptServiceImpl implements BaseDeptService {
    @Autowired
    private BaseDeptMapper baseDeptMapper;

    @Autowired
    private BaseUserMapper baseUserMapper;

    @Override
    public List<BaseDept> getBaseDeptList(BaseDept dept) {
        return null;
    }

    @Override
    public List<Map> getDeptTreeList(BaseDept dept) {
        List<BaseDept> depts = baseDeptMapper.selectDeptList(dept);
        List<Map> deptTree = TreeBuildUtils.init().
                setKey("deptId", "parentId", null).
                toListMap(depts).build();

        return deptTree;
    }

    @Override
    public Integer addBaseDept(BaseDept dept) {
        BaseDept parentDept = baseDeptMapper.selectById(dept.getParentId());
        dept.setAncestors(parentDept.getAncestors() + "," + dept.getParentId());
        return baseDeptMapper.insert(dept);
    }

    @Override
    public Integer editBaseDept(BaseDept dept) {
        return baseDeptMapper.updateById(dept);
    }

    @Override
    public Integer deleteBaseDept(Long deptId) {
        BaseDept dept = new BaseDept();
        dept.setDeptId(deptId);
        return baseDeptMapper.deleteById(dept);
    }

    @Override
    public Integer setBaseDeptStatus(BaseDept dept) {
        BaseDept newDept = new BaseDept();
        newDept.setDeptId(dept.getDeptId());
        newDept.setStatus(dept.getStatus());
        int i = baseDeptMapper.updateById(newDept);
        if (i > 0) {
            baseUserMapper.setUserStatusByDept(newDept);
        }
        return i;
    }
}
