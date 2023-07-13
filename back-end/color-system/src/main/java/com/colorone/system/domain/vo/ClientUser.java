package com.colorone.system.domain.vo;

import com.colorone.common.domain.auth.LoginUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/7/7
 * @备注：登录返回用户信息
 */
@Data
public class ClientUser {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户名
     */
    String nickName;

    /**
     * 部门ID
     */
    Integer deptId;

    /**
     * 部门ID
     */
    String deptName;

    /**
     * 返回token
     */
    String token;

    @JsonIgnore
    public ClientUser(LoginUser loginUser) {
        this.userName = loginUser.getUser().getUserName();
        this.nickName = loginUser.getUser().getNickName();
    }
}
