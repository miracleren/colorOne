package com.colorone.common.utils;

import com.colorone.common.utils.data.EntityUtils;
import com.colorone.common.utils.data.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/9/14
 * @备注：生成权退过滤sql
 */
public class ScopeBuild {
    /**
     * 所有数据权限
     */
    private final static String allScope = "1=1";

    /**
     * 没有任务数据权限
     */
    private final static String noScope = "1=0";

    /**
     * 前缀标识
     */
    private final static String prefix = "[prefix]";

    /**
     * 生成查询过滤权限的sql
     * sql格式实例子  create_by = {user.userName} or [prefix]dept_id = {user.deptId}
     * {user.userName}变量 ，对应LoginUser的属性值
     * [prefix]为数据查询结果的前缀，默认会加载上查询主表前缀，也可以写死某一固定前缀，或不写
     *
     * @param where
     * @param values
     * @return
     */
    public static String where(String where, Object values) {
        //超级系统管理员不加载数据权限
        if (SecurityUtils.isSuperAdmin((Long) EntityUtils.getPropertyValue(values, "user.userId")))
            return allScope;

        if (isAllScope(where))
            return allScope;
        else if (isNoScope(where))
            return noScope;

        // 匹配大括号及其内容
        String regex = "\\{(.*?)\\}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(where);

        //构建过滤条件的数据
        String newWhere = where;
        while (matcher.find()) {
            String name = matcher.group(1);
            Object value = EntityUtils.getPropertyValue(values, name);

            String typeName = value.getClass().getTypeName();
            if (typeName.contains("String") && !value.toString().contains(",")) {
                newWhere = newWhere.replace("{" + name + "}", "'" + value + "'");
            } else {
                newWhere = newWhere.replace("{" + name + "}", value.toString());
            }
        }

        return "(" + newWhere + ")";
    }

    /**
     * 返回是否有所有数据权限
     *
     * @param where
     * @return
     */
    public static boolean isAllScope(String where) {
        return where.replace(" ", "").equals(allScope);
    }

    /**
     * 返回是否有没有任务数据权限
     *
     * @param where
     * @return
     */
    public static boolean isNoScope(String where) {
        return where.replace(" ", "").equals(noScope) || StringUtils.isEmpty(where);
    }

    public static boolean isHavePrefix(String where) {
        return where.contains(prefix);
    }
}
