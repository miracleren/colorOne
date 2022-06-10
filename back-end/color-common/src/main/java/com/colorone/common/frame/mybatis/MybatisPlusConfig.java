package com.colorone.common.frame.mybatis;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.MybatisMapWrapperFactory;
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
}
