package com.colorone.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.colorone.system.domain.entity.BaseMenu;
import com.colorone.system.mapper.BaseMenuMapper;
import com.colorone.system.service.BaseMenuService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2022/6/2
 */
@Service
public class BaseMenuServiceImpl implements BaseMenuService {
    @Resource
    private BaseMenuMapper baseMenuMapper;

    @Override
    public List<BaseMenu> getBasMenusList(BaseMenu baseMenu) {
        LambdaQueryWrapper<BaseMenu> query = Wrappers.lambdaQuery();
        return baseMenuMapper.selectList(query);
    }
}
