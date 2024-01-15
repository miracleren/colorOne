package com.colorone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class ColorStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(ColorStartApplication.class, args);
        System.out.println(new Date() + "：颜一简易管理系统启动成功");
    }

}
