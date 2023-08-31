package com.colorone.system.controller;

import com.colorone.common.domain.core.RequestResult;
import com.colorone.common.frame.aspect.annotation.ApiExtension;
import com.colorone.common.frame.aspect.enums.PermitType;
import com.colorone.system.domain.entity.BaseDept;
import com.colorone.system.service.BaseDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/8/31
 * @备注：部门管理
 */
@RestController
@RequestMapping("/dept")
public class BaseDeptController {
    @Autowired
    private BaseDeptService baseDeptService;

    /**
     * 查询部门数据列表
     *
     * @param dept dept 部门实体类
     * @return 部门列表
     */
    @GetMapping("/list")
    @ApiExtension(name = "查询部门数据列表", permitType = PermitType.ROLE)
    public RequestResult getBaseDeptList(BaseDept dept) {
        return RequestResult.success(baseDeptService.getBaseDeptList(dept));
    }

    /**
     * 查询部门树数据
     *
     * @param dept dept 部门实体类
     * @return 部门列表
     */
    @GetMapping("/tree")
    @ApiExtension(name = "查询部门树数据", permitType = PermitType.LOGIN)
    public RequestResult getBaseDeptTreeList(BaseDept dept) {
        return RequestResult.success(baseDeptService.getDeptTreeList(dept));
    }

    /**
     * 新增部门数据
     *
     * @param dept 部门实体类
     * @return 成功失败
     */
    @PostMapping("/add")
    @ApiExtension(name = "新增部门数据", permitType = PermitType.ROLE)
    public RequestResult addBaseDept(@RequestBody BaseDept dept) {
        return RequestResult.success(baseDeptService.addBaseDept(dept) > 0);
    }

    /**
     * 修改部门数据
     *
     * @param dept 部门实体类
     * @return 成功失败
     */
    @PutMapping("/edit")
    @ApiExtension(name = "修改部门数据", permitType = PermitType.ROLE)
    public RequestResult editBaseDept(@RequestBody BaseDept dept) {
        return RequestResult.success(baseDeptService.editBaseDept(dept) > 0);
    }

    /**
     * 删除部门数据
     *
     * @param deptId 部门键值
     * @return 成功失败
     */
    @DeleteMapping("/delete/{deptId}")
    @ApiExtension(name = "删除部门数据", permitType = PermitType.ROLE)
    public RequestResult deleteBaseDept(@PathVariable Long deptId) {
        return RequestResult.success(baseDeptService.deleteBaseDept(deptId) > 0);
    }


    /**
     * 停用或启用部门
     *
     * @param dept 部门实体类
     * @return 成功失败
     */
    @PutMapping("/set/status")
    @ApiExtension(name = "停用或启用部门", permitType = PermitType.ROLE)
    public RequestResult setBaseDeptStatus(@RequestBody BaseDept dept) {
        return RequestResult.success(baseDeptService.setBaseDeptStatus(dept) > 0);
    }

}
