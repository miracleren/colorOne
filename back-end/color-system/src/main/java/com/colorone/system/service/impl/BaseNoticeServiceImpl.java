package com.colorone.system.service.impl;

import com.colorone.common.utils.PageUtils;
import com.colorone.system.domain.entity.BaseNotice;
import com.colorone.system.mapper.BaseNoticeMapper;
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

    @Override
    public List<BaseNotice> getBaseNoticeList(BaseNotice dict) {
        PageUtils.start();
        return baseNoticeMapper.selectBaseNoticeList(dict);
    }

    @Override
    public Integer addBaseNotice(BaseNotice notice) {
        return baseNoticeMapper.insert(notice);
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
