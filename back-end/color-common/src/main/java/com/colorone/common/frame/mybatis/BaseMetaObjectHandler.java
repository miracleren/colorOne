package com.colorone.common.frame.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.colorone.common.constant.Constants;
import com.colorone.common.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2022/11/29 mybatis自动填充字段公共信息
 */

public class BaseMetaObjectHandler implements MetaObjectHandler {

    //创建人
    private static final String create_by = "createBy";

    //创建时间
    private static final String create_time = "createTime";

    //更新人
    private static final String update_by = "updateBy";

    //更新时间
    private static final String update_time = "updateTime";

    //删除标记
    private static final String del_flag = "delFlag";

    //是否自动填充标识
    private static final String is_auto_field = "autoFill";


    /**
     * 新增记录填充
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //判断是否需要自动填充公共信息
        if (metaObject.hasSetter(is_auto_field)) {
            if ((Boolean) getFieldValByName(is_auto_field, metaObject)) {
                // 删除标记
                if (metaObject.hasSetter(del_flag))
                    setFieldValByName(del_flag, Constants.LOGIC_DELETE_FALSE, metaObject);
                // 创建人
                if (metaObject.hasSetter(create_by))
                    setFieldValByName(create_by, SecurityUtils.getUsername(), metaObject);
                // 创建人
                if (metaObject.hasSetter(update_by))
                    setFieldValByName(update_by, SecurityUtils.getUsername(), metaObject);
                // 创建时间
                if (metaObject.hasSetter(create_time))
                    setFieldValByName(create_time, new Date(), metaObject);
                // 修改时间
                if (metaObject.hasSetter(update_time))
                    setFieldValByName(update_time, new Date(), metaObject);
            }
        }
    }

    /**
     * 修改记录填充
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        //判断是否需要自动填充公共信息
        if (metaObject.hasSetter(is_auto_field)) {
            if ((Boolean) getFieldValByName(is_auto_field, metaObject)) {
                // 更新人
                if (metaObject.hasSetter(update_by))
                    setFieldValByName(update_by, SecurityUtils.getUsername(), metaObject);
                // 修改时间
                if (metaObject.hasSetter(update_time))
                    setFieldValByName(update_time, new Date(), metaObject);
            }
        }
    }
}
