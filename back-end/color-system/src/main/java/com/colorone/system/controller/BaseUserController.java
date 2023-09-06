package com.colorone.system.controller;

import com.colorone.common.domain.core.RequestResult;
import com.colorone.common.frame.aspect.annotation.ApiExtension;
import com.colorone.common.frame.aspect.enums.PermitType;
import com.colorone.common.utils.SecurityUtils;
import com.colorone.system.domain.entity.BaseUser;
import com.colorone.system.service.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/8/28
 * @备注：用户管理
 */
@RestController
@RequestMapping("/user")
public class BaseUserController {
    @Autowired
    private BaseUserService baseUserService;

    /**
     * 查询用户数据列表
     *
     * @param user user 用户实体类
     * @return 用户列表
     */
    @GetMapping("/list")
    @ApiExtension(name = "查询用户数据列表", permitType = PermitType.ROLE)
    public RequestResult getBaseUserTreeList(BaseUser user) {
        return RequestResult.page(baseUserService.getBaseUserList(user));
    }

    /**
     * 新增用户数据
     *
     * @param user 用户实体类
     * @return 成功失败
     */
    @PostMapping("/add")
    @ApiExtension(name = "新增用户数据", permitType = PermitType.ROLE)
    public RequestResult addBaseUser(@RequestBody BaseUser user) {
        if (baseUserService.checkUserName(user)) {
            return RequestResult.error("新增用户'" + user.getUserName() + "'失败，该登录账号已存在！");
        } else if (baseUserService.checkUserPhone(user)) {
            return RequestResult.error("新增用户'" + user.getUserName() + "'失败，该手机号码已存在！");
        } else if (baseUserService.checkUserEmail(user)) {
            return RequestResult.error("新增用户'" + user.getUserName() + "'失败，该邮箱账号已存在！");
        }

        return RequestResult.success(baseUserService.addBaseUser(user) > 0);
    }

    /**
     * 修改用户数据
     *
     * @param user 用户实体类
     * @return 成功失败
     */
    @PutMapping("/edit")
    @ApiExtension(name = "修改用户数据", permitType = PermitType.ROLE)
    public RequestResult editBaseUser(@RequestBody BaseUser user) {
        if (SecurityUtils.isSuperAdmin(user.getUserId()))
            return RequestResult.error("修改失败，不能编辑超级管理员的用户信息！");
        else if (baseUserService.checkUserPhone(user)) {
            return RequestResult.error("修改用户'" + user.getUserName() + "'失败，该手机号码已存在！");
        } else if (baseUserService.checkUserEmail(user)) {
            return RequestResult.error("修改用户'" + user.getUserName() + "'失败，该邮箱账号已存在！");
        }

        return RequestResult.success(baseUserService.editBaseUser(user) > 0);
    }

    /**
     * 删除用户数据
     *
     * @param userId 用户键值
     * @return 成功失败
     */
    @DeleteMapping("/delete/{userId}")
    @ApiExtension(name = "删除用户数据", permitType = PermitType.ROLE)
    public RequestResult deleteBaseUser(@PathVariable Long userId) {
        if (1L == userId)
            return RequestResult.error("不能删除系统超级管理员用户！");
        return RequestResult.success(baseUserService.deleteBaseUser(userId) > 0);
    }


    /**
     * 切换用户账号状态
     *
     * @param user 用户实体类
     * @return 成功失败
     */
    @PutMapping("/status/switch")
    @ApiExtension(name = "切换用户账号状态", permitType = PermitType.ROLE)
    public RequestResult switchBaseUserStatus(@RequestBody BaseUser user) {
        if (1L == user.getUserId())
            return RequestResult.error("不能切换系统超级管理员用户账号状态！");
        return RequestResult.success(baseUserService.switchBaseUserStatus(user) > 0);
    }

    /**
     * 重置用户密码
     *
     * @param user 用户实体类
     * @return 成功失败
     */
    @PutMapping("/reset/password")
    @ApiExtension(name = "重置用户密码", permitType = PermitType.ROLE)
    public RequestResult resetUserPassword(@RequestBody BaseUser user) {
        if (SecurityUtils.isSuperAdmin(user.getUserId()))
            return RequestResult.error("修改失败，不能编辑超级管理员的用户信息！");

        return RequestResult.success(baseUserService.resetUserPassword(user) > 0);
    }


}
