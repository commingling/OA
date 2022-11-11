package org.commingling.quinlan.domain;

import lombok.Data;

/**
 * 用户登录对象
 * @author ：Quinlan
 * @date ：2022/11/10 16:48
 */
@Data
public class LoginBody {

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 唯一标识
     */
    private String uuid;

}
