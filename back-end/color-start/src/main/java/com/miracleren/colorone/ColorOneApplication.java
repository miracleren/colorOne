package com.miracleren.colorone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class ColorOneApplication {

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(ColorOneApplication.class, args);
        System.out.println("  (♥◠‿◠)ﾉﾞ  颜一简易管理系统启动成功   ლ(´ڡ`ლ)ﾞ ");
    }

}
