package com.colorone.system.controller;

import com.colorone.common.domain.core.RequestResult;
import com.colorone.system.domain.entity.BaseDict;
import com.colorone.system.service.BaseDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/8/1
 * @备注：字典
 */

@RestController
@RequestMapping("/dict")
public class BaseDictController {
    @Autowired
    private BaseDictService baseDictService;

    /**
     * 根据字典类型获取字典数据，数据类型关联select组件
     */
    @GetMapping("/select/{type}")
    public RequestResult getSelectDictList(@PathVariable String type) {
        return RequestResult.success(baseDictService.getSelectDictByType(type));
    }

    /**
     * 查询字典数据列表
     *
     * @param dict
     * @return
     */
    @GetMapping("/list")
    public RequestResult getDictList(BaseDict dict) {
        return RequestResult.success(baseDictService.getDictList(dict));
    }
}
