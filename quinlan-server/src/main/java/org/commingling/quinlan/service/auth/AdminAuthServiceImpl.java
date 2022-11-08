package org.commingling.quinlan.service.auth;

import cn.hutool.core.util.ObjectUtil;
import org.commingling.quinlan.controller.auth.vo.AuthLoginReqVO;
import org.commingling.quinlan.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import org.commingling.quinlan.dal.dataobject.user.AdminUserDO;
import org.commingling.quinlan.enums.common.CommonStatusEnum;
import org.commingling.quinlan.enums.common.UserTypeEnum;
import org.commingling.quinlan.enums.logger.LoginLogTypeEnum;
import org.commingling.quinlan.enums.oauth2.OAuth2ClientConstants;
import org.commingling.quinlan.service.oauth2.OAuth2TokenService;
import org.commingling.quinlan.service.user.AdminUserService;
import org.commingling.quinlan.controller.auth.vo.AuthLoginRespVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Auth Service 实现类
 * @author ：Quinlan
 * @date ：2022/11/07 15:39
 */
@Service
public class AdminAuthServiceImpl implements AdminAuthService{

    @Resource
    private AdminUserService userService;

    @Resource
    private OAuth2TokenService oauth2TokenService;

    @Override
    public AuthLoginRespVO login(AuthLoginReqVO reqVO) {
        // 校验验证码
//        verifyCaptcha(reqVO);

        // 使用账号密码，进行登录
        AdminUserDO user = authenticate(reqVO.getUsername(), reqVO.getPassword());

        // 如果 socialType 非空，说明需要绑定社交用户
//        if (reqVO.getSocialType() != null) {
//            socialUserService.bindSocialUser(new SocialUserBindReqDTO(user.getId(), getUserType().getValue(),
//                    reqVO.getSocialType(), reqVO.getSocialCode(), reqVO.getSocialState()));
//        }

        // 创建 Token 令牌，记录登录日志
        return createTokenAfterLoginSuccess(user.getId(), reqVO.getUsername(), LoginLogTypeEnum.LOGIN_USERNAME);
    }

    @Override
    public AdminUserDO authenticate(String username, String password) {
//        final LoginLogTypeEnum logTypeEnum = LoginLogTypeEnum.LOGIN_USERNAME;
        // 校验账号是否存在
        AdminUserDO user = userService.getUserByUsername(username);
        if (user == null) {

        }
        if (!userService.isPasswordMatch(password, user.getPassword())) {

        }
        // 校验是否禁用
        if (ObjectUtil.notEqual(user.getStatus(), CommonStatusEnum.ENABLE.getStatus())) {

        }

        return user;
    }

    private AuthLoginRespVO createTokenAfterLoginSuccess(Long userId, String username, LoginLogTypeEnum logType) {
        // 插入登陆日志
//        createLoginLog(userId, username, logType, LoginResultEnum.SUCCESS);

        // 创建访问令牌
        OAuth2AccessTokenDO accessTokenDO = oauth2TokenService.createAccessToken(userId, getUserType().getValue(),
                OAuth2ClientConstants.CLIENT_ID_DEFAULT, null);
        // 构建返回结果
//        return AuthConvert.INSTANCE.convert(accessTokenDO);
        return null;
    }

    private UserTypeEnum getUserType() {
        return UserTypeEnum.ADMIN;
    }

    @Override
    public void logout(String token, Integer logType) {
        // 删除访问令牌
        OAuth2AccessTokenDO accessTokenDO = oauth2TokenService.removeAccessToken(token);
        if (accessTokenDO == null) {
            return;
        }
        // 删除成功，则记录登出日志
        createLogoutLog(accessTokenDO.getUserId(), accessTokenDO.getUserType(), logType);
    }

    private void createLogoutLog(Long userId, Integer userType, Integer logType) {
        LoginLogCreateReqDTO reqDTO = new LoginLogCreateReqDTO();
        reqDTO.setLogType(logType);
        reqDTO.setTraceId(TracerUtils.getTraceId());
        reqDTO.setUserId(userId);
        reqDTO.setUserType(userType);
        if (ObjectUtil.equal(getUserType().getValue(), userType)) {
            reqDTO.setUsername(getUsername(userId));
        } else {
            reqDTO.setUsername(memberService.getMemberUserMobile(userId));
        }
        reqDTO.setUserAgent(ServletUtils.getUserAgent());
        reqDTO.setUserIp(ServletUtils.getClientIP());
        reqDTO.setResult(LoginResultEnum.SUCCESS.getResult());
        loginLogService.createLoginLog(reqDTO);
    }

}
