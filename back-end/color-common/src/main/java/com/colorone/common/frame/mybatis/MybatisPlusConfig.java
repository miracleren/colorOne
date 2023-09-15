package com.colorone.common.frame.mybatis;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.MybatisMapWrapperFactory;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2022/6/2
 */
@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {
    /**
     * 驼峰Map
     *
     * @return
     */
    @Bean
    public ConfigurationCustomizer toCamelCaseMap() {
        return configuration -> configuration.setObjectWrapperFactory(new MybatisMapWrapperFactory());
    }

    /**
     * 终止对全表的删除或更新操作操作
     * https://baomidou.com/guide/interceptor-block-attack.html
     */
    @Bean
    public BlockAttackInnerInterceptor blockAttackInnerInterceptor() {
        return new BlockAttackInnerInterceptor();
    }


    /**
     * 元对象字段填充控制器
     * https://baomidou.com/guide/auto-fill-metainfo.html
     */
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new BaseMetaObjectHandler();
    }


    /**
     * 分页插件, 一缓和二缓遵循mybatis的规则
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 添加数据权限插件
        DataScopeInterceptor dataScopeInterceptor = new DataScopeInterceptor();
        // 添加自定义的数据权限处理器
        dataScopeInterceptor.setDataScopeHandler(new DataScopeHandler());
        interceptor.addInnerInterceptor(dataScopeInterceptor);
        //向Mybatis过滤器链中添加分页拦截器
        //interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));

        return interceptor;
    }

}
