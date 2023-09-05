package com.colorone.common.domain.core;

import lombok.Data;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/9/4
 * @备注： 分页结果实体类
 */
@Data
public class PageResult {
    /**
     * 返回列表数据
     */
    private Object rows;

    /**
     * 总行数
     */
    private Long total;

}
