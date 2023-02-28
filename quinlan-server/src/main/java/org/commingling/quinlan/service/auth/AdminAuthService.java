package org.commingling.quinlan.service.auth;

import jakarta.validation.Valid;
import org.commingling.quinlan.dal.vo.AuthLoginReqVO;
import org.commingling.quinlan.dal.vo.AuthLoginRespVO;
import org.commingling.quinlan.dal.dataobject.user.AdminUserDO;

/**
 * 管理后台的认证 Service 接口
 *
 * 提供用户的登录、登出的能力
 *
 * @author 芋道源码
 */
public interface AdminAuthService {

    /**
     * 验证账号 + 密码。如果通过，则返回用户
     *
     * @param username 账号
     * @param password 密码
     * @return 用户
     */
    AdminUserDO authenticate(String username, String password);

    /**
     * 账号登录
     *
     * @param reqVO 登录信息
     * @return 登录结果
     */
    AuthLoginRespVO login(@Valid AuthLoginReqVO reqVO);

}
