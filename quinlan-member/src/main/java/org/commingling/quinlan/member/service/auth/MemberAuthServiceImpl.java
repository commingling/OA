package org.commingling.quinlan.member.service.auth;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.commingling.quinlan.api.logger.LoginLogApi;
import org.commingling.quinlan.api.oauth2.OAuth2TokenApi;
import org.commingling.quinlan.api.social.SocialUserApi;
import org.commingling.quinlan.common.enums.CommonStatusEnum;
import org.commingling.quinlan.common.enums.UserTypeEnum;
import org.commingling.quinlan.common.enums.logger.LoginLogTypeEnum;
import org.commingling.quinlan.common.enums.logger.LoginResultEnum;
import org.commingling.quinlan.common.enums.oauth2.OAuth2ClientConstants;
import org.commingling.quinlan.common.util.monitor.TracerUtils;
import org.commingling.quinlan.common.util.servlet.ServletUtils;
import org.commingling.quinlan.dal.dto.logger.LoginLogCreateReqDTO;
import org.commingling.quinlan.dal.dto.oauth2.OAuth2AccessTokenCreateReqDTO;
import org.commingling.quinlan.dal.dto.oauth2.OAuth2AccessTokenRespDTO;
import org.commingling.quinlan.member.dal.convert.auth.AuthConvert;
import org.commingling.quinlan.member.dal.dataobject.user.MemberUserDO;
import org.commingling.quinlan.member.dal.vo.auth.AppAuthLoginReqVO;
import org.commingling.quinlan.member.dal.vo.auth.AppAuthLoginRespVO;
import org.commingling.quinlan.member.service.user.MemberUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

import static org.commingling.quinlan.common.enums.error.ErrorCodeConstants.AUTH_LOGIN_BAD_CREDENTIALS;
import static org.commingling.quinlan.common.enums.error.ErrorCodeConstants.AUTH_LOGIN_USER_DISABLED;
import static org.commingling.quinlan.common.exception.util.ServiceExceptionUtil.exception;
import static org.commingling.quinlan.common.util.servlet.ServletUtils.getClientIP;

/**
 * 会员的认证 Service 接口
 *
 * @author 芋道源码
 */
@Service
@Slf4j
public class MemberAuthServiceImpl implements MemberAuthService{

    @Resource
    private MemberUserService userService;

    @Resource
    private LoginLogApi loginLogApi;

    @Resource
    private SocialUserApi socialUserApi;

    @Resource
    private OAuth2TokenApi oauth2TokenApi;

    @Override
    public AppAuthLoginRespVO login(AppAuthLoginReqVO reqVO) {
        // 使用手机 + 密码，进行登录。
        MemberUserDO user = login0(reqVO.getMobile(), reqVO.getPassword());

        // 创建 Token 令牌，记录登录日志
        return createTokenAfterLoginSuccess(user, reqVO.getMobile(), LoginLogTypeEnum.LOGIN_MOBILE);
    }

    private AppAuthLoginRespVO createTokenAfterLoginSuccess(MemberUserDO user, String mobile, LoginLogTypeEnum logType) {
        // 插入登陆日志
        createLoginLog(user.getId(), mobile, logType, LoginResultEnum.SUCCESS);
        // 创建 Token 令牌
        OAuth2AccessTokenRespDTO accessTokenRespDTO = oauth2TokenApi.createAccessToken(new OAuth2AccessTokenCreateReqDTO()
                .setUserId(user.getId()).setUserType(getUserType().getValue())
                .setClientId(OAuth2ClientConstants.CLIENT_ID_DEFAULT));
        // 构建返回结果
        return AuthConvert.INSTANCE.convert(accessTokenRespDTO);
    }

    private MemberUserDO login0(String mobile, String password) {
        final LoginLogTypeEnum logTypeEnum = LoginLogTypeEnum.LOGIN_MOBILE;
        // 校验账号是否存在
        MemberUserDO user = userService.getUserByMobile(mobile);
        if (user == null) {
            createLoginLog(null, mobile, logTypeEnum, LoginResultEnum.BAD_CREDENTIALS);
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        if (!userService.isPasswordMatch(password, user.getPassword())) {
            createLoginLog(user.getId(), mobile, logTypeEnum, LoginResultEnum.BAD_CREDENTIALS);
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        // 校验是否禁用
        if (ObjectUtil.notEqual(user.getStatus(), CommonStatusEnum.ENABLE.getStatus())) {
            createLoginLog(user.getId(), mobile, logTypeEnum, LoginResultEnum.USER_DISABLED);
            throw exception(AUTH_LOGIN_USER_DISABLED);
        }
        return user;
    }

    private void createLoginLog(Long userId, String mobile, LoginLogTypeEnum logType, LoginResultEnum loginResult) {
        // 插入登录日志
        LoginLogCreateReqDTO reqDTO = new LoginLogCreateReqDTO();
        reqDTO.setLogType(logType.getType());
        reqDTO.setTraceId(TracerUtils.getTraceId());
        reqDTO.setUserId(userId);
        reqDTO.setUserType(getUserType().getValue());
        reqDTO.setUsername(mobile);
        reqDTO.setUserAgent(ServletUtils.getUserAgent());
        reqDTO.setUserIp(getClientIP());
        reqDTO.setResult(loginResult.getResult());
        loginLogApi.createLoginLog(reqDTO);
        // 更新最后登录时间
        if (userId != null && Objects.equals(LoginResultEnum.SUCCESS.getResult(), loginResult.getResult())) {
            userService.updateUserLogin(userId, getClientIP());
        }
    }

    private UserTypeEnum getUserType() {
        return UserTypeEnum.MEMBER;
    }

}
