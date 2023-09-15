package com.colorone.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.colorone.common.frame.aspect.annotation.DataScope;
import com.colorone.system.domain.entity.BaseDept;
import com.colorone.system.domain.entity.BaseUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/6/29
 * @备注：
 */
@Mapper
public interface BaseUserMapper extends BaseMapper<BaseUser> {
    /**
     * 查询用户列表
     *
     * @param user 参数
     * @return 数据列表
     */
    @DataScope
    List<BaseUser> selectBaseUserList(BaseUser user);

    /**
     * 更新用户状态
     *
     * @param user
     * @return
     */
    Integer updateUserStatus(BaseUser user);

    /**
     * 通过部门更新部门下的用户状态
     *
     * @param newDept 部门实体类
     * @return
     */
    Integer setUserStatusByDept(BaseDept newDept);
}
