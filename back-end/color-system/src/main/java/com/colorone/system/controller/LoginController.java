package com.colorone.system.controller;

import com.colorone.common.constant.Constants;
import com.colorone.common.constant.LoginLogInfo;
import com.colorone.common.constant.RedisPrefix;
import com.colorone.common.domain.auth.LoginBody;
import com.colorone.common.domain.auth.LoginUser;
import com.colorone.common.domain.core.RequestResult;
import com.colorone.common.frame.aspect.annotation.ApiExtension;
import com.colorone.common.frame.aspect.enums.PermitType;
import com.colorone.common.frame.asyncTask.AsyncFactory;
import com.colorone.common.frame.asyncTask.AsyncTaskManager;
import com.colorone.common.frame.captcha.CaptchaText;
import com.colorone.common.frame.exception.CaptchaException;
import com.colorone.common.frame.redis.RedisHelper;
import com.colorone.common.frame.security.web.TokenService;
import com.colorone.common.utils.HttpServletUtils;
import com.colorone.common.utils.data.IdUtils;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;


/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/6/29
 * @备注：登录处理
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    RedisHelper redisHelper;

    @Autowired
    Producer producer;

    /**
     * 验证码类型
     */
    @Value("${captcha.type}")
    private String captchaType;

    /**
     * 验证码是否开启
     */
    @Value("${captcha.enabled}")
    private String captchaEnabled;

    /**
     * 登录接口
     *
     * @return
     */
    @PostMapping("/user")
    @ApiExtension(name = "登录接口", permitType = PermitType.ANONYMOUS)
    public RequestResult login(@RequestBody LoginBody loginBody) throws CaptchaException {
        //验证码是否有效
        if ("true".equals(captchaEnabled)) {
            String codeValue = redisHelper.getObject(RedisPrefix.REDIS_CAPTCHA_CODE + loginBody.getCode());
            if (codeValue == null || !codeValue.equals(loginBody.getCaptcha())) {
                AsyncTaskManager.getInstance().execute(AsyncFactory.setLogging(loginBody.getUserName(), Constants.FAIL, LoginLogInfo.CAPTCHA_INVALID));
                throw new CaptchaException(LoginLogInfo.CAPTCHA_INVALID);
            }
            //验证完成清除缓存的验证码
            redisHelper.clearObject(RedisPrefix.REDIS_CAPTCHA_CODE + loginBody.getCode());
        }

        String username = loginBody.getUserName();
        String password = loginBody.getPassword();

        // 该方法会调用loadUserByUsername，传递用户密码给到SpringSecurity执行校验，如果校验失败，会进入BadCredentialsException
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof UsernameNotFoundException) {
                AsyncTaskManager.getInstance().execute(AsyncFactory.setLogging(loginBody.getUserName(), Constants.FAIL, e.getMessage()));
                throw new UsernameNotFoundException(e.getMessage());
            } else {
                AsyncTaskManager.getInstance().execute(AsyncFactory.setLogging(loginBody.getUserName(), Constants.FAIL, LoginLogInfo.BAD_PASSWORD));
                throw new BadCredentialsException(LoginLogInfo.BAD_PASSWORD);
            }
        }

        // 验证通过，设置授权信息至SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 如果验证通过了，从返回的authentication里获得完整的UserDetails信息
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        // 将用户的ID息保存在jwt的token中
        String token = tokenService.createToken(loginUser);

        // 登录成功缓存用户信息
        tokenService.cacheLoginUser(loginUser);

        AsyncTaskManager.getInstance().execute(AsyncFactory.setLogging(loginUser.getUsername(), Constants.SUCCESS, LoginLogInfo.LOGIN_SUCCESS));

        return RequestResult.success(token);
    }

    /**
     * 获取登录用户信息
     *
     * @return
     */
    @GetMapping("/user/info")
    @ApiExtension(name = "获取登录用户信息")
    public RequestResult loginUserInfo() {
        LoginUser loginUser = tokenService.getLoginUser(HttpServletUtils.getRequest());
        RequestResult res = RequestResult.success();
        res.setData("user", loginUser.getUser());
        res.setData("roles", loginUser.getRoles());
        res.setData("permits", loginUser.getPermits());
        return res;
    }


    /**
     * 获取登录验证码
     *
     * @return 验证码
     */
    @GetMapping("/captcha/image")
    @ApiExtension(name = "获取登录验证码", permitType = PermitType.ANONYMOUS)
    public RequestResult getVerifyCode() {
        RequestResult res = new RequestResult();
        if ("true".equals(captchaEnabled)) {
            String text = "", codeValue = "";
            String uuid = IdUtils.randomNoSignUUID();
            BufferedImage image;
            if ("math".equals(captchaType)) {
                String[] math = CaptchaText.creatMathCaptchaText().split("=");
                text = math[0];
                codeValue = math[1];
            } else {
                text = producer.createText();
                codeValue = text;
            }
            image = producer.createImage(text);

            // 转换流信息写出
            FastByteArrayOutputStream os = new FastByteArrayOutputStream();
            try {
                ImageIO.write(image, "jpg", os);
            } catch (IOException e) {
                return RequestResult.error(e.getMessage());
            }

            redisHelper.setObject(RedisPrefix.REDIS_CAPTCHA_CODE + uuid, codeValue, 3, TimeUnit.MINUTES);

            res.setData("image", Base64.getEncoder().encodeToString(os.toByteArray()));
            res.setData("code", uuid);
        } else
            res.setData("code", null);

        return res;
    }
}
