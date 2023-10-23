package com.colorone.system.service.impl;

import com.colorone.common.utils.PageUtils;
import com.colorone.system.domain.entity.BaseNotice;
import com.colorone.system.mapper.BaseNoticeMapper;
import com.colorone.system.service.BaseFilePoolService;
import com.colorone.system.service.BaseNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/9/25
 * @备注：
 */
@Service
public class BaseNoticeServiceImpl implements BaseNoticeService {
    @Autowired
    private BaseNoticeMapper baseNoticeMapper;

    @Autowired
    private BaseFilePoolService filePoolService;

    @Override
    public List<BaseNotice> getBaseNoticeList(BaseNotice dict) {
        PageUtils.start();
        return baseNoticeMapper.selectBaseNoticeList(dict);
    }

    @Override
    public Integer addBaseNotice(BaseNotice notice) {
        int res = baseNoticeMapper.insert(notice);
        //如果有上传附件，则更新到当前记录下
        if (notice.getParams().containsKey("fileRefId"))
            filePoolService.saveUploadFils(notice.getNoticeId().toString(), notice.getParams().get("fileRefId").toString());
        return res;
    }

    @Override
    public Integer editBaseNotice(BaseNotice notice) {
        return baseNoticeMapper.updateById(notice);
    }

    @Override
    public Integer deleteBaseNotice(Long noticeId) {
        BaseNotice notice = new BaseNotice();
        notice.setNoticeId(noticeId);
        return baseNoticeMapper.deleteById(notice);
    }
}
