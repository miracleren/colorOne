package com.colorone.system.service.impl;

import com.colorone.system.domain.entity.BaseDict;
import com.colorone.system.mapper.BaseDictMapper;
import com.colorone.system.service.BaseDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/8/1
 * @备注：
 */
@Service
public class BaseDictServiceImpl implements BaseDictService {
    @Autowired
    private BaseDictMapper baseDictMapper;

    @Override
    public List<Map<String, Object>> getSelectDictByType(String type) {
        return baseDictMapper.selectComDictByType(type);
    }

    @Override
    public List<BaseDict> getDictList(BaseDict dict) {
        return baseDictMapper.selectDictList(dict);
    }
}
