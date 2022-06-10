package com.colorone.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2022/6/2
 */
@Mapper
public interface CommonMapper {
    Map<String, Object> getUser(String userId);
}
