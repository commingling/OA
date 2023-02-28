package org.commingling.quinlan.member.service.auth;

import org.commingling.quinlan.member.dal.vo.auth.AppAuthLoginReqVO;
import org.commingling.quinlan.member.dal.vo.auth.AppAuthLoginRespVO;

import javax.validation.Valid;

/**
 * 会员的认证 Service 接口
 *
 * 提供用户的账号密码登录、token 的校验等认证相关的功能
 *
 * @author 芋道源码
 */
public interface MemberAuthService {

    /**
     * 手机 + 密码登录
     *
     * @param reqVO 登录信息
     * @return 登录结果
     */
    AppAuthLoginRespVO login(@Valid AppAuthLoginReqVO reqVO);

}
