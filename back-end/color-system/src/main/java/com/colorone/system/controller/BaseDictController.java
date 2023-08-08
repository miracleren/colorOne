package com.colorone.system.controller;

import com.colorone.common.domain.core.RequestResult;
import com.colorone.system.domain.entity.BaseDict;
import com.colorone.system.service.BaseDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
     * 查询字典数据列表
     *
     * @param dict dict 字典实体类
     * @return 字典列表
     */
    @GetMapping("/list")
    public RequestResult getBaseDictList(BaseDict dict) {
        return RequestResult.success(baseDictService.getDictList(dict));
    }

    /**
     * 新增字典数据
     *
     * @param dict 字典实体类
     * @return 成功失败
     */
    @PostMapping("/add")
    public RequestResult addBaseDict(@RequestBody BaseDict dict) {
        return RequestResult.success(baseDictService.addBaseDict(dict) > 0);
    }

    /**
     * 修改字典数据
     *
     * @param dict 字典实体类
     * @return 成功失败
     */
    @PutMapping("/edit")
    public RequestResult editBaseDict(@RequestBody BaseDict dict) {
        return RequestResult.success(baseDictService.editBaseDict(dict) > 0);
    }

    /**
     * 删除字典数据
     *
     * @param dictId 字典键值
     * @return 成功失败
     */
    @DeleteMapping("/delete/{dictId}")
    public RequestResult deleteBaseDict(@PathVariable Long dictId) {
        return RequestResult.success(baseDictService.deleteBaseDict(dictId) > 0);
    }


    /**
     * 根据字典类型获取字典数据，数据类型关联select组件
     *
     * @param type 字典类型标识
     * @return 字典select数据
     */
    @GetMapping("/select/{type}")
    public RequestResult getSelectDictList(@PathVariable String type) {
        return RequestResult.success(baseDictService.getSelectDictByType(type));
    }
}
