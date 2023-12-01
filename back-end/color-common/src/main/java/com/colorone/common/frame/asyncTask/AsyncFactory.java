package com.colorone.common.frame.asyncTask;

import com.colorone.common.domain.Log.BaseLogLogin;
import com.colorone.common.frame.asyncTask.web.BaseLogLoginService;
import com.colorone.common.utils.HttpServletUtils;
import com.colorone.common.utils.IpAddressUtils;
import com.colorone.common.utils.spring.SpringUtils;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.TimerTask;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/11/28
 * @备注：
 */
@Slf4j
public class AsyncFactory {
    public static TimerTask setLogging(final String username, final Integer status, final String message, final Object... args) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(HttpServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = IpAddressUtils.getIpAddress(HttpServletUtils.getRequest());
        return new TimerTask() {
            @Override
            public void run() {
//                String address = AddressUtils.getRealAddressByIP(ip);
//                StringBuilder s = new StringBuilder();
//                s.append(LogUtils.getBlock(ip));
//                s.append(address);
//                s.append(LogUtils.getBlock(username));
//                s.append(LogUtils.getBlock(status));
//                s.append(LogUtils.getBlock(message));
//                // 打印信息到日志
//                sys_user_logger.info(s.toString(), args);

                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                // 登录日志对象数据
                BaseLogLogin logging = new BaseLogLogin();

                String user = username;
                if (user.isEmpty() && message.indexOf("：") > 0) {
                    user = message.split("：")[0];
                }

                logging.setUserName(user);
                logging.setIpAddress(ip);
                logging.setBrowser(browser);
                logging.setOs(os);
                logging.setStatus(status);
                logging.setMsg(message);
                logging.setLogTime(new Date());

                // 插入数据
                SpringUtils.getBean(BaseLogLoginService.class).addBaseLogging(logging);
            }
        };
    }
}