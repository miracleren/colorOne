package com.colorone.system.controller;

import com.colorone.common.domain.core.RequestResult;
import com.colorone.common.frame.aspect.annotation.ApiExtension;
import com.colorone.common.frame.aspect.enums.PermitType;
import com.colorone.system.domain.entity.BaseNotice;
import com.colorone.system.service.BaseNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/9/25
 * @备注：公告管理
 */
@RestController
@RequestMapping("/notice")
public class BaseNoticeController {
    @Autowired
    private BaseNoticeService baseNoticeService;

    /**
     * 查询公告数据列表
     *
     * @param notice notice 公告实体类
     * @return 公告列表
     */
    @GetMapping("/list")
    @ApiExtension(name = "查询公告数据列表", permitType = PermitType.ROLE)
    public RequestResult getBaseNoticeTreeList(BaseNotice notice) {
        return RequestResult.page(baseNoticeService.getBaseNoticeList(notice));
    }

    /**
     * 新增公告数据
     *
     * @param notice 公告实体类
     * @return 成功失败
     */
    @PostMapping("/add")
    @ApiExtension(name = "新增公告数据", permitType = PermitType.ROLE)
    public RequestResult addBaseNotice(@RequestBody BaseNotice notice) {
        return RequestResult.success(baseNoticeService.addBaseNotice(notice) > 0);
    }

    /**
     * 修改公告数据
     *
     * @param notice 公告实体类
     * @return 成功失败
     */
    @PutMapping("/edit")
    @ApiExtension(name = "修改公告数据", permitType = PermitType.ROLE)
    public RequestResult editBaseNotice(@RequestBody BaseNotice notice) {
        return RequestResult.success(baseNoticeService.editBaseNotice(notice) > 0);
    }

    /**
     * 删除公告数据
     *
     * @param noticeId 公告键值
     * @return 成功失败
     */
    @DeleteMapping("/delete/{noticeId}")
    @ApiExtension(name = "删除公告数据", permitType = PermitType.ROLE)
    public RequestResult deleteBaseNotice(@PathVariable Long noticeId) {
        return RequestResult.success(baseNoticeService.deleteBaseNotice(noticeId) > 0);
    }

}
