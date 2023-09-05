package com.colorone.system.controller;

import com.colorone.common.domain.core.RequestResult;
import com.colorone.common.frame.aspect.annotation.ApiExtension;
import com.colorone.common.frame.aspect.enums.PermitType;
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
    @ApiExtension(name = "查询字典数据列表", permitType = PermitType.ROLE)
    public RequestResult getBaseDictList(BaseDict dict) {
        return RequestResult.page(baseDictService.getDictList(dict));
    }

    /**
     * 查询字典树数据
     *
     * @param dict dict 字典实体类
     * @return 字典列表
     */
    @GetMapping("/tree")
    @ApiExtension(name = "查询字典树数据", permitType = PermitType.ROLE)
    public RequestResult getBaseDictTreeList(BaseDict dict) {
        return RequestResult.success(baseDictService.getDictTreeList(dict));
    }

    /**
     * 新增字典数据
     *
     * @param dict 字典实体类
     * @return 成功失败
     */
    @PostMapping("/add")
    @ApiExtension(name = "新增字典数据", permitType = PermitType.ROLE)
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
    @ApiExtension(name = "修改字典数据", permitType = PermitType.ROLE)
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
    @ApiExtension(name = "删除字典数据", permitType = PermitType.ROLE)
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
    @ApiExtension(name = "根据字典类型获取字典数据", permitType = PermitType.LOGIN)
    public RequestResult getSelectDictList(@PathVariable String type) {
        return RequestResult.success(baseDictService.getSelectDictByType(type));
    }


    /**
     * 查询子字典数据列表
     *
     * @param dictId 字典父ID
     * @return 字典列表
     */
    @GetMapping("/children/{dictId}")
    @ApiExtension(name = "查询子字典数据列表", permitType = PermitType.ROLE)
    public RequestResult getBaseDictChildren(@PathVariable Long dictId) {
        return RequestResult.success(baseDictService.getBaseDictChildren(dictId));
    }
}
