package org.commingling.quinlan.service.auth;

import org.commingling.quinlan.controller.auth.vo.AuthLoginReqVO;
import org.commingling.quinlan.dal.dataobject.user.AdminUserDO;
import org.commingling.quinlan.controller.auth.vo.AuthLoginRespVO;

import javax.validation.Valid;

/**
 * 管理后台的认证 Service 接口
 *
 * 提供用户的登录、登出的能力
 * @author ：Quinlan
 * @date ：2022/11/07 15:37
 */
public interface AdminAuthService {

    /**
     * 账号登录
     *
     * @param reqVO 登录信息
     * @return 登录结果
     */
    AuthLoginRespVO login(@Valid AuthLoginReqVO reqVO);

    /**
     * 验证账号 + 密码。如果通过，则返回用户
     *
     * @param username 账号
     * @param password 密码
     * @return 用户
     */
    AdminUserDO authenticate(String username, String password);

    /**
     * 基于 token 退出登录
     *
     * @param token token
     * @param logType 登出类型
     */
    void logout(String token, Integer logType);
}
