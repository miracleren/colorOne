package com.colorone.common.frame.mybatis;

import com.colorone.common.frame.aspect.annotation.DataScope;
import com.colorone.common.utils.ScopeBuild;
import com.colorone.common.utils.SecurityUtils;
import lombok.SneakyThrows;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.PlainSelect;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/9/11
 * @备注：数据权限sql处理
 */
public class DataScopeHandler {

    /**
     * 动太加载数据权限过滤条件，附加到注解执行的sql上
     *
     * @param plainSelect
     * @param classPath
     * @return
     */
    @SneakyThrows(Exception.class)
    public Expression getSqlSegment(PlainSelect plainSelect, String classPath) {
        // 待执行 SQL Where 条件表达式
        Expression where = plainSelect.getWhere();

        //判断方法上是否存在数据权限注解，有则过滤数据权限
        Class<?> classType = Class.forName(classPath.substring(0, classPath.lastIndexOf(".")));
        //给尾_COUNT，为pageHelper分页计数查询
        String methodName = classPath.substring(classPath.lastIndexOf(".") + 1, classPath.lastIndexOf("_COUNT") > 0 ? classPath.lastIndexOf("_COUNT") : classPath.length());
        Method method = Arrays.stream(classType.getMethods()).filter(row -> row.getName().equals(methodName)).findFirst().orElse(null);

        if (method != null && method.getAnnotation(DataScope.class) != null) {
            //所有数据权限跳过所有判断
            String whereClause = SecurityUtils.getUserScopeWhere();
            if (ScopeBuild.isAllScope(whereClause))
                return where;

            // 有别名用别名，无别名用表名，防止字段冲突报错
            if (ScopeBuild.isHavePrefix(whereClause)) {
                Table fromItem = (Table) plainSelect.getFromItem();
                Alias fromItemAlias = fromItem.getAlias();
                String alias = fromItemAlias == null ? fromItem.getName() : fromItemAlias.getName();

                whereClause = whereClause.replace("[prefix]", alias);
            }

            Expression scopeWhere = CCJSqlParserUtil.parseCondExpression(whereClause);
            return new AndExpression(where, scopeWhere);
        }
        return where;
    }
}
