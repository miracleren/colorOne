package com.colorone.common.frame.mybatis;

import com.baomidou.mybatisplus.core.plugins.InterceptorIgnoreHelper;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.parser.JsqlParserSupport;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import lombok.*;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SetOperationList;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.SQLException;
import java.util.List;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/9/11
 * @备注：
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class DataScopeInterceptor extends JsqlParserSupport implements InnerInterceptor {

    /**
     * 数据权限处理器
     */
    private DataScopeHandler dataScopeHandler;

    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        if (InterceptorIgnoreHelper.willIgnoreDataPermission(ms.getId())) {
            return;
        }
        PluginUtils.MPBoundSql mpBs = PluginUtils.mpBoundSql(boundSql);
        mpBs.sql(this.parserSingle(mpBs.sql(), ms.getId()));
    }

    /**
     * 查询数据前处理，数据权限过滤注入
     *
     * @param select
     * @param index
     * @param sql
     * @param obj
     */
    @Override
    protected void processSelect(Select select, int index, String sql, Object obj) {
        //long stime = System.currentTimeMillis();

        SelectBody selectBody = select.getSelectBody();
        if (selectBody instanceof PlainSelect) {
            this.setWhere((PlainSelect) selectBody, (String) obj);
        } else if (selectBody instanceof SetOperationList setOperationList) {
            List<SelectBody> selectBodyList = setOperationList.getSelects();
            selectBodyList.forEach(s -> this.setWhere((PlainSelect) s, (String) obj));
        }

        //long etime = System.currentTimeMillis();
        //System.out.println("DataScope权限注入 执行时长：" + (etime - stime) + " 毫秒.");
    }

    /**
     * 设置 where 条件
     *
     * @param plainSelect  查询对象
     * @param whereSegment 查询条件片段
     */
    private void setWhere(PlainSelect plainSelect, String whereSegment) {
        Expression sqlSegment = this.dataScopeHandler.getSqlSegment(plainSelect, whereSegment);
        if (null != sqlSegment) {
            plainSelect.setWhere(sqlSegment);
        }
    }


//    @Override
//    protected void processInsert(Insert insert, int index, String sql, Object obj) {
//        System.out.println( "新增前调用,可修改sql" );
//    }
//
//    @Override
//    protected void processDelete(Delete delete, int index, String sql, Object obj) {
//        System.out.println( "删除前调用,可修改sql" );
//    }
//
//    @Override
//    protected void processUpdate(Update update, int index, String sql, Object obj) {
//        System.out.println("修改调用,可修改sql");
//    }


}
