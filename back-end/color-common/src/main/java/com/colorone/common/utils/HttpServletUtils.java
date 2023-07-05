package com.colorone.common.utils;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/7/4
 * @备注：
 */
public class HttpServletUtils {

    /**
     * 将字符串返回
     *
     * @param response
     * @param string
     * @return null
     */
    public static String renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



}
