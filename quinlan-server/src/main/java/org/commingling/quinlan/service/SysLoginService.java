package org.commingling.quinlan.service;

import org.springframework.security.core.Authentication;

/**
 * 登录校验方法
 * @author ：Quinlan
 * @date ：2022/11/10 16:51
 */
public class SysLoginService {

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid) {

        Authentication authentication = null;

        return null;
    }
}
