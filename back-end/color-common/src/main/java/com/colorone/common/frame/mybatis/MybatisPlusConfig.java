package com.colorone.common.frame.mybatis;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.MybatisMapWrapperFactory;
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
}
