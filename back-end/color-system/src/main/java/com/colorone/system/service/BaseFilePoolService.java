package com.colorone.system.service;

import com.colorone.system.domain.entity.BaseFilePool;

import java.util.List;
import java.util.Map;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/10/20
 * @备注：
 */
public interface BaseFilePoolService {
    /**
     * 新增文件数据
     *
     * @param filePool
     * @return
     */
    int addBaseFilePool(BaseFilePool filePool);

    /**
     * 删除文件数据
     *
     * @param fileId
     * @return
     */
    int deleteBaseFilePool(String fileId);

    /**
     * 查询通用文件列表
     *
     * @param refName
     * @param refId
     * @return
     */
    List<Map> getBaseFilePoolList(String refName, String refId);

    /**
     * 通下文件ID获取文件信息
     *
     * @param fileId
     * @return
     */
    BaseFilePool getBaseFilePoolById(String fileId);

    /**
     * 更新新增记录时的附近列表关联编号
     *
     * @param tempId
     * @param recordId
     * @return
     */
    Integer saveUploadFils(String recordId, String tempId);
}
