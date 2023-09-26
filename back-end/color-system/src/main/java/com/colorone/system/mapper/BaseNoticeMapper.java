package com.colorone.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.colorone.system.domain.entity.BaseNotice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/9/25
 * @备注：
 */
@Mapper
public interface BaseNoticeMapper extends BaseMapper<BaseNotice> {
    /**
     * 查询公告表数据
     *
     * @param notice
     * @return
     */
    List<BaseNotice> selectBaseNoticeList(BaseNotice notice);
}
