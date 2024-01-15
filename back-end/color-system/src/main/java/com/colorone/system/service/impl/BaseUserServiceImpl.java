package com.colorone.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.colorone.common.utils.PageUtils;
import com.colorone.common.utils.SecurityUtils;
import com.colorone.system.domain.entity.BaseUser;
import com.colorone.system.domain.entity.BaseUserRole;
import com.colorone.system.mapper.BaseUserMapper;
import com.colorone.system.mapper.BaseUserRoleMapper;
import com.colorone.system.service.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/6/29
 * @备注：
 */
@Service
public class BaseUserServiceImpl implements BaseUserService {
    @Autowired
    private BaseUserMapper baseUserMapper;

    @Autowired
    private BaseUserRoleMapper baseUserRoleMapper;

    @Override
    public List<BaseUser> getBaseUserList(BaseUser user) {
        PageUtils.start();
        return baseUserMapper.selectBaseUserList(user);
    }

    @Override
    public Integer addBaseUser(BaseUser user) {
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        int res = baseUserMapper.insert(user);
        if (res > 0) {
            for (Long roleId : user.getRoles()) {
                BaseUserRole userRole = new BaseUserRole();
                userRole.setRoleId(roleId);
                userRole.setUserId(user.getUserId());
                baseUserRoleMapper.insert(userRole);
            }
        }
        return res;
    }

    @Override
    public Integer editBaseUser(BaseUser user) {
        //防修改用户名称、密码
        user.setUserName(null);
        user.setPassword(null);
        int i = baseUserMapper.updateById(user);

        //清除用户关联的角色
        baseUserRoleMapper.deleteRoleByUserId(user);
        //新增或更新存在的关联数据
        if (i > 0) {
            for (Long r : user.getRoles()) {
                BaseUserRole userRole = new BaseUserRole();
                userRole.setRoleId(r);
                userRole.setUserId(user.getUserId());
                userRole.setUpdateBy(SecurityUtils.getUsername());
                int u = baseUserRoleMapper.updateDelUserRoleExist(userRole);
                if (u == 0)
                    baseUserRoleMapper.insert(userRole);
            }
        }

        return i;
    }

    @Override
    public Integer deleteBaseUser(Long userId) {
        BaseUser user = new BaseUser();
        user.setUserId(userId);
        return baseUserMapper.deleteById(user);
    }

    @Override
    public Integer switchBaseUserStatus(BaseUser user) {
        return baseUserMapper.updateUserStatus(user);
    }

    @Override
    public Boolean checkUserName(BaseUser user) {
        QueryWrapper<BaseUser> query = new QueryWrapper<>();
        query.eq("user_name", user.getUserName());
        return baseUserMapper.selectCount(query) > 0;
    }

    @Override
    public Boolean checkUserEmail(BaseUser user) {
        QueryWrapper<BaseUser> query = new QueryWrapper<>();
        query.eq("email", user.getEmail());
        if (user.getUserId() != null)
            query.ne("user_id", user.getUserId());
        return baseUserMapper.selectCount(query) > 0;
    }

    @Override
    public Boolean checkUserPhone(BaseUser user) {
        QueryWrapper<BaseUser> query = new QueryWrapper<>();
        query.eq("phone", user.getPhone());
        if (user.getUserId() != null)
            query.ne("user_id", user.getUserId());
        return baseUserMapper.selectCount(query) > 0;
    }

    @Override
    public Integer resetUserPassword(BaseUser user) {
        BaseUser newUser = new BaseUser();
        newUser.setUserId(user.getUserId());
        newUser.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return baseUserMapper.updateById(newUser);
    }

    @Override
    public Integer resetPasswordBySelf(BaseUser user) {
        BaseUser newUser = new BaseUser();
        newUser.setUserId(SecurityUtils.getUserId());
        newUser.setPassword(SecurityUtils.encryptPassword(user.getNewPassword()));
        return baseUserMapper.updateById(newUser);
    }

    @Override
    public BaseUser getBaseUserById(Long userId) {
        return baseUserMapper.selectById(userId);
    }
}
