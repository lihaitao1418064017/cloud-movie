package org.lht.boot.security.common;

import org.lht.boot.security.entity.AuthUserDetails;
import org.lht.boot.security.entity.Authentication;

/**
 * @author LiHaitao
 * @description 用户登陆信息
 * @date 2020/3/25 19:50
 **/

public class SecUserDetails extends AuthUserDetails {

    private static final long serialVersionUID = 2017845963758421135L;

    private LoginType loginType = LoginType.NORMAL;

    private String avatar;

    public SecUserDetails(Authentication authentication) {
        super(authentication);
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }


    private String loginTime;


}
