package com.colorone.system.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2022/6/2
 */
public interface CommonService {
    Map<String, Object> getUser(String userId);
}
