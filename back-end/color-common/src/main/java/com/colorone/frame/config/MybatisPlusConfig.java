package com.colorone.frame.config;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.MybatisMapWrapperFactory;
import org.mybatis.spring.annotation.MapperScan;
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
@MapperScan("com.colorone.**.mapper")
public class MybatisPlusConfig {

    /**
     * sql注入器配置
     * @return
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new DefaultSqlInjector();
    }

    /**
     * 驼峰Map
     * @return
     */
    @Bean
    public ConfigurationCustomizer toCamelCaseMap() {
        return configuration -> configuration.setObjectWrapperFactory(new MybatisMapWrapperFactory());
    }
}
