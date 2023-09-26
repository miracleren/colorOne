package com.colorone.system.service;

import com.colorone.system.domain.entity.BaseNotice;

import java.util.List;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/9/25
 * @备注：
 */
public interface BaseNoticeService {
    /**
     * 获取公告列表
     *
     * @param dict
     * @return
     */
    List<BaseNotice> getBaseNoticeList(BaseNotice dict);

    /**
     * 新增公告数据
     *
     * @param notice 公告实体类
     * @return 成功失败
     */
    Integer addBaseNotice(BaseNotice notice);

    /**
     * 修改公告数据
     *
     * @param dict 公告实体类
     * @return 成功失败
     */
    Integer editBaseNotice(BaseNotice dict);

    /**
     * 删除公告数据
     *
     * @param dictId 公告键值
     * @return 成功失败
     */
    Integer deleteBaseNotice(Long dictId);
}
