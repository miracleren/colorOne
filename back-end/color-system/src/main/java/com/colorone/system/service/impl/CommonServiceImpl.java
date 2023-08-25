package com.colorone.system.service.impl;

import com.colorone.system.mapper.CommonMapper;
import com.colorone.system.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2022/6/2
 */
@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private CommonMapper commonMapper;

    @Override
    public Map<String, Object> getUser(String userId) {
        return commonMapper.getUser(userId);
    }
}
