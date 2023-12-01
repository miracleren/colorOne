package com.colorone.common.frame.asyncTask.web;

import com.colorone.common.domain.Log.BaseLogLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/11/28
 * @备注：
 */
@Component
public class BaseLogLoginService {

    @Autowired
    private BaseLogLoginMapper logLoginMapper;

    /**
     * 新增登录日志数据
     *
     * @param logLogin 登录日志实体类
     */
    public void addBaseLogging(BaseLogLogin logLogin) {
        logLoginMapper.insert(logLogin);
    }
}
