package org.commingling.quinlan.service.auth;

import cn.hutool.core.util.ObjectUtil;
import com.xingyuv.captcha.model.common.ResponseModel;
import com.xingyuv.captcha.model.vo.CaptchaVO;
import com.xingyuv.captcha.service.CaptchaService;
import jakarta.annotation.Resource;
import jakarta.validation.Validator;
import org.commingling.quinlan.common.enums.CommonStatusEnum;
import org.commingling.quinlan.common.enums.UserTypeEnum;
import org.commingling.quinlan.common.enums.logger.LoginLogTypeEnum;
import org.commingling.quinlan.common.enums.logger.LoginResultEnum;
import org.commingling.quinlan.common.enums.oauth2.OAuth2ClientConstants;
import org.commingling.quinlan.common.util.monitor.TracerUtils;
import org.commingling.quinlan.common.util.servlet.ServletUtils;
import org.commingling.quinlan.common.util.validation.ValidationUtils;
import org.commingling.quinlan.dal.convert.auth.AuthConvert;
import org.commingling.quinlan.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import org.commingling.quinlan.dal.dataobject.user.AdminUserDO;
import org.commingling.quinlan.dal.dto.logger.LoginLogCreateReqDTO;
import org.commingling.quinlan.dal.vo.AuthLoginReqVO;
import org.commingling.quinlan.dal.vo.AuthLoginRespVO;
import org.commingling.quinlan.service.logger.LoginLogService;
import org.commingling.quinlan.service.oauth2.OAuth2TokenService;
import org.commingling.quinlan.service.social.SocialUserService;
import org.commingling.quinlan.service.user.AdminUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.commingling.quinlan.common.enums.error.ErrorCodeConstants.*;
import static org.commingling.quinlan.common.exception.util.ServiceExceptionUtil.exception;

@Service
public class AdminAuthServiceImpl implements AdminAuthService{

    @Resource
    private AdminUserService userService;

    @Resource
    private LoginLogService loginLogService;

    @Resource
    private Validator validator;

    @Resource
    private CaptchaService captchaService;

    @Resource
    private SocialUserService socialUserService;

    @Resource
    private OAuth2TokenService oauth2TokenService;


    /**
     * 验证码的开关，默认为 true
     */
    @Value("${yudao.captcha.enable:false}")
    private Boolean captchaEnable;

    @Override
    public AdminUserDO authenticate(String username, String password) {
        final LoginLogTypeEnum logTypeEnum = LoginLogTypeEnum.LOGIN_USERNAME;
        // 校验账号是否存在
        AdminUserDO user = userService.getUserByUsername(username);
        if (user == null) {
            createLoginLog(null, username, logTypeEnum, LoginResultEnum.BAD_CREDENTIALS);
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        if (!userService.isPasswordMatch(password, user.getPassword())) {
            createLoginLog(user.getId(), username, logTypeEnum, LoginResultEnum.BAD_CREDENTIALS);
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        // 校验是否禁用
        if (ObjectUtil.notEqual(user.getStatus(), CommonStatusEnum.ENABLE.getStatus())) {
            createLoginLog(user.getId(), username, logTypeEnum, LoginResultEnum.USER_DISABLED);
            throw exception(AUTH_LOGIN_USER_DISABLED);
        }
        return user;
    }

    @Override
    public AuthLoginRespVO login(AuthLoginReqVO reqVO) {
        // 校验验证码
        validateCaptcha(reqVO);
        // 使用账号密码，进行登录
        AdminUserDO user = authenticate(reqVO.getUsername(), reqVO.getPassword());

        return createTokenAfterLoginSuccess(user.getId(), reqVO.getUsername(), LoginLogTypeEnum.LOGIN_USERNAME);
    }

    private AuthLoginRespVO createTokenAfterLoginSuccess(Long userId, String username, LoginLogTypeEnum logType) {
        // 插入登陆日志
        createLoginLog(userId, username, logType, LoginResultEnum.SUCCESS);
        // 创建访问令牌
        OAuth2AccessTokenDO accessTokenDO = oauth2TokenService.createAccessToken(userId, getUserType().getValue(),
                OAuth2ClientConstants.CLIENT_ID_DEFAULT, null);
        // 构建返回结果
        return AuthConvert.INSTANCE.convert(accessTokenDO);
    }

    void validateCaptcha(AuthLoginReqVO reqVO) {
        // 如果验证码关闭，则不进行校验
        if (!captchaEnable) {
            return;
        }
        // 校验验证码
        // 校验验证码
        ValidationUtils.validate(validator, reqVO, AuthLoginReqVO.CodeEnableGroup.class);
        CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setCaptchaVerification(reqVO.getCaptchaVerification());
        ResponseModel response = captchaService.verification(captchaVO);
        // 验证不通过
        if (!response.isSuccess()) {
            // 创建登录失败日志（验证码不正确)
            createLoginLog(null, reqVO.getUsername(), LoginLogTypeEnum.LOGIN_USERNAME, LoginResultEnum.CAPTCHA_CODE_ERROR);
            throw exception(AUTH_LOGIN_CAPTCHA_CODE_ERROR, response.getRepMsg());
        }
    }

    private void createLoginLog(Long userId, String username,
                                LoginLogTypeEnum logTypeEnum, LoginResultEnum loginResult) {
        // 插入登录日志
        LoginLogCreateReqDTO reqDTO = new LoginLogCreateReqDTO();
        reqDTO.setLogType(logTypeEnum.getType());
        reqDTO.setTraceId(TracerUtils.getTraceId());
        reqDTO.setUserId(userId);
        reqDTO.setUserType(getUserType().getValue());
        reqDTO.setUsername(username);
        reqDTO.setUserAgent(ServletUtils.getUserAgent());
        reqDTO.setUserIp(ServletUtils.getClientIP());
        reqDTO.setResult(loginResult.getResult());
        loginLogService.createLoginLog(reqDTO);
        // 更新最后登录时间
        if (userId != null && Objects.equals(LoginResultEnum.SUCCESS.getResult(), loginResult.getResult())) {
            userService.updateUserLogin(userId, ServletUtils.getClientIP());
        }

    }

    private UserTypeEnum getUserType() {
        return UserTypeEnum.ADMIN;
    }

}
