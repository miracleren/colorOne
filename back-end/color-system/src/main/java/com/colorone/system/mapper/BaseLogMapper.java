package com.colorone.system.mapper;

import com.colorone.common.domain.Log.BaseLogLogin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/11/30
 * @备注：
 */
@Mapper
public interface BaseLogMapper {
    /**
     * 查询登录日志数据
     *
     * @param logLogin
     * @return
     */
    List<Map> selectBaseLogLoginList(BaseLogLogin logLogin);
}