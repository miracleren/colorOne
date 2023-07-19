package com.colorone.common.frame.security.web;

import com.colorone.common.constant.RedisPrefix;
import com.colorone.common.domain.auth.LoginUser;
import com.colorone.common.utils.data.DateUtils;
import com.colorone.common.utils.data.IdUtils;
import com.colorone.common.utils.data.ObjectUtils;
import com.colorone.common.frame.redis.RedisHelper;
import com.colorone.common.utils.data.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/6/21
 * @备注：token处理
 */
@Component
public class TokenService {
    @Autowired
    private RedisHelper redisHelper;

    /**
     * 令牌自定义标识
     */
    @Value("${token.header}")
    private String header;

    @Value("${token.secret}")
    private String secret;

    @Value("${token.expireTime}")
    private String expireTime;

    /**
     * 令牌前缀
     */
    private static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 令牌标识
     */
    public static final String LOGIN_USER_UUID = "LOGIN_USER_UUID";


    /**
     * 令牌刷新最小时间，毫秒，默认10分钟
     */
    public static final Long REFRESH_MIN_TIME = 10 * 60 * 1000L;


    /**
     * 获取请求里的token
     *
     * @param request
     * @return
     */
    public String getRequestToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StringUtils.isNotEmpty(token) && token.startsWith(TOKEN_PREFIX)) {
            token = token.replace(TOKEN_PREFIX, "");
        }
        return token;
    }

    /**
     * 创建令牌
     *
     * @param loginUser
     * @return
     */
    public String createToken(LoginUser loginUser) {
        loginUser.setUuid(IdUtils.randomUUID());
        //生成登录用户随机ID
        Map<String, Object> claims = new HashMap<>();
        claims.put(LOGIN_USER_UUID, loginUser.getUuid());

        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();

        return token;

    }


    /**
     * 从令牌返回数据
     *
     * @param token 令牌
     * @return 数据声明
     */
    public Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }


    /**
     * 缓存用户数据在redis
     */
    public void cacheLoginUser(LoginUser loginUser) {
        if (ObjectUtils.isNotNull(loginUser) && StringUtils.isNotEmpty(loginUser.getUuid())) {
            loginUser.setLoginTime(new Date());
            //登录信息有效期
            loginUser.setExpireTime(DateUtils.addMin(new Date(), NumberUtils.createInteger(expireTime)));
            // 利用uuid缓存loginUser
            String userKey = RedisPrefix.LOGIN_USER + loginUser.getUuid();
            redisHelper.setObject(userKey, loginUser, NumberUtils.createInteger(expireTime), TimeUnit.MINUTES);
        }
    }

    /**
     * 通过token获取登录用户
     *
     * @param requestToken
     * @return
     */
    public LoginUser getLoginUser(String requestToken) {
        if (StringUtils.isNotEmpty(requestToken)) {
            Claims claims = parseToken(requestToken);
            // 解析对应的权限信息
            String uuid = (String) claims.get(LOGIN_USER_UUID);
            return redisHelper.getObject(RedisPrefix.LOGIN_USER + uuid);
        }
        return null;
    }

    /**
     * 通过request获取登录用户
     * @param request
     * @return
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        return getLoginUser(getRequestToken(request));
    }

    /**
     * 验证token是否即将过期，并刷新token
     *
     * @param loginUser
     */
    public void verifyRefreshToken(LoginUser loginUser) {
        Long diff = DateUtils.differenceMillisecond(new Date(), loginUser.getExpireTime());
        if (diff < REFRESH_MIN_TIME)
            refreshToken(loginUser);
    }

    /**
     * 刷新redis用户登录信息有效时间
     *
     * @param loginUser
     */
    public void refreshToken(LoginUser loginUser) {
        loginUser.setExpireTime(DateUtils.addMin(new Date(), NumberUtils.createInteger(expireTime)));
        // 根据uuid将loginUser缓存，登录信息缓存expireTime
        String userKey = RedisPrefix.LOGIN_USER + loginUser.getUuid();
        redisHelper.setObject(userKey, loginUser, NumberUtils.createInteger(expireTime), TimeUnit.MINUTES);
    }

    /**
     * 清空用户登录信息
     *
     * @param loginUser
     */
    public void clearToken(LoginUser loginUser) {
        String userKey = RedisPrefix.LOGIN_USER + loginUser.getUuid();
         redisHelper.clearObject(userKey);
    }
}
