package com.colorone.common.frame.captcha;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/8/30
 * @备注：验证码工具配置
 */
@Configuration
public class KaptchaConfig {
    /**
     * 验证码类型
     */
    @Value("${captcha.type}")
    private String captchaType;

    @Bean
    Producer kaptcha() {

        Properties properties = new Properties();
        //是否有边框 默认为true 我们可以自己设置yes，no
        properties.setProperty("kaptcha.border", "no");
        //边框颜色 默认为Color.BLACK
        //properties.setProperty("kaptcha.border.color", "105,179,90");
        //验证码文本字符颜色 默认为Color.BLACK
        properties.setProperty("kaptcha.textproducer.font.color", "19,67,49");
        //验证码图片宽度 默认为200
        properties.setProperty("kaptcha.image.width", "120");
        //验证码图片高度 默认为50
        properties.setProperty("kaptcha.image.height", "38");
        //验证码文本字符大小 默认为40
        properties.setProperty("kaptcha.textproducer.font.size", "30");
        //验证码文本字符长度 默认为5
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        //验证码噪点颜色 默认为Color.BLACK
        properties.setProperty("kaptcha.noise.color", "black");
        //背景颜色渐变，开始颜色
        properties.setProperty("kaptcha.background.clear.from", "158,189,210");
        //背景颜色渐变， 结束颜色
        properties.setProperty("kaptcha.background.clear.to", "143,203,195");
        if ("math".equals(captchaType)) {
            //图片样式 默认 WaterRipple
            properties.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");
            // 干扰实现类
            properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
        }

        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
