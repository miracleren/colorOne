package com.colorone.system.service.impl;

import com.colorone.common.domain.Log.BaseLogLogin;
import com.colorone.common.utils.PageUtils;
import com.colorone.system.mapper.BaseLogMapper;
import com.colorone.system.service.BaseLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/11/30
 * @备注：
 */
@Service
public class BaseLogServiceImpl implements BaseLogService {
    @Autowired
    private BaseLogMapper logMapper;

    @Override
    public List<Map> getBaseLogLoginList(BaseLogLogin logLogin) {
        PageUtils.start();
        return logMapper.selectBaseLogLoginList(logLogin);
    }
}