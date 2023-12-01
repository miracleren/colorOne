package com.colorone.system.service;

import com.colorone.common.domain.Log.BaseLogLogin;

import java.util.List;
import java.util.Map;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/11/30
 * @备注：
 */

public interface BaseLogService {
    /**
     * 查询登录日志数据
     *
     * @param logLogin
     * @return
     */
    List<Map> getBaseLogLoginList(BaseLogLogin logLogin);
}