package com.colorone.common.frame.captcha;

import com.colorone.common.frame.redis.RedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/8/30
 * @备注：
 */

public class CaptchaText {
    /**
     * 生成加减法计算验证码
     *
     * @return
     */
    public static String creatMathCaptchaText() {
        int num = (int) (Math.random() * 100);
        int num2 = (int) (Math.random() * 100);

        String[] ops = new String[]{"+", "-"};
        String op = ops[Math.random() > 0.5 ? 1 : 0];

        if (num < num2) {
            int tem = num2;
            num2 = num;
            num = tem;
        }

        return num + op + num2 + "=" + ("-".equals(op) ? num - num2 : num + num2);
    }

}
