package com.colorone.system.service.impl;

import com.colorone.common.utils.SecurityUtils;
import com.colorone.system.domain.entity.BaseFilePool;
import com.colorone.system.mapper.BaseFilePoolMapper;
import com.colorone.system.service.BaseFilePoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/10/20
 * @备注：
 */
@Service
public class BaseFilePoolServiceImpl implements BaseFilePoolService {
    @Autowired
    private BaseFilePoolMapper filePoolMapper;

    @Override
    public int addBaseFilePool(BaseFilePool filePool) {
        return filePoolMapper.insert(filePool);
    }

    @Override
    public int deleteBaseFilePool(String fileId) {
        BaseFilePool filePool = new BaseFilePool();
        filePool.setFileId(fileId);
        return filePoolMapper.deleteById(filePool);
    }

    @Override
    public List<Map> getBaseFilePoolList(String refName, String refId) {
        return filePoolMapper.selectBaseFilePoolList(refName, refId);
    }

    @Override
    public BaseFilePool getBaseFilePoolById(String fileId) {
        return filePoolMapper.selectById(fileId);
    }

    @Override
    public Integer saveUploadFils(String recordId, String tempId) {
        String userName = SecurityUtils.getUsername();
        return filePoolMapper.updateFilePoolRefId(recordId, tempId, userName);
    }
}