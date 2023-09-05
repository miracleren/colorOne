package com.colorone.common.utils;

import com.colorone.common.utils.data.StringUtils;
import com.github.pagehelper.PageHelper;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/9/5
 * @备注：分页工具
 */
public class PageUtils {
    /**
     * 加载分布信息，实现自动分页
     * PageHelper.startPage 分页只对下次一sql执行有效，建议紧邻mapper方法
     * 目前只加载params url传参的分布信息
     */
    public static void start() {
        int pageNum = 1, pageSize = 15;
        try {
            if (StringUtils.isNotBlank(HttpServletUtils.getParameter("params[page]")))
                pageNum = Integer.valueOf(HttpServletUtils.getParameter("params[page]"));
            if (StringUtils.isNotBlank(HttpServletUtils.getParameter("params[pageSize]")))
                pageSize = Integer.valueOf(HttpServletUtils.getParameter("params[pageSize]"));
        } catch (Exception e) {
            System.out.print(e.toString());
        }

        PageHelper.startPage(pageNum, pageSize);
    }
}
