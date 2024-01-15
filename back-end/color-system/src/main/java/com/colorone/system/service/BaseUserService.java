package com.colorone.system.service;

import com.colorone.system.domain.entity.BaseUser;

import java.util.List;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/6/29
 * @备注：
 */
public interface BaseUserService {
    /**
     * 获取用户树列表
     *
     * @param user
     * @return
     */
    List<BaseUser> getBaseUserList(BaseUser user);

    /**
     * 新增用户数据
     *
     * @param user 用户实体类
     * @return 成功失败
     */
    Integer addBaseUser(BaseUser user);

    /**
     * 修改用户数据
     *
     * @param user 用户实体类
     * @return 成功失败
     */
    Integer editBaseUser(BaseUser user);

    /**
     * 删除用户数据
     *
     * @param userId 用户键值
     * @return 成功失败
     */
    Integer deleteBaseUser(Long userId);

    /**
     * 切换用户状态
     *
     * @param user 用户实体类
     * @return 成功失败
     */
    Integer switchBaseUserStatus(BaseUser user);

    /**
     * 验在用户名称是否存在
     *
     * @param user 用户名称
     * @return Boolean
     */
    Boolean checkUserName(BaseUser user);

    /**
     * 验在用户邮箱是否存在
     *
     * @param user 用户邮箱
     * @return Boolean
     */
    Boolean checkUserEmail(BaseUser user);

    /**
     * 验在用户手机号是否存在
     *
     * @param user 用户手机号
     * @return Boolean
     */
    Boolean checkUserPhone(BaseUser user);

    /**
     * 重置用户密码
     *
     * @param user 用户信息
     * @return 成功失败
     */
    Integer resetUserPassword(BaseUser user);

    /**
     * 重置自己密码
     * @param user
     * @return
     */
    Integer resetPasswordBySelf(BaseUser user);

    /**
     * 通过用户Id获取用户信息
     * @param userId
     * @return
     */
    BaseUser getBaseUserById(Long userId);
}
