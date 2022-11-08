package org.commingling.quinlan.controller.auth;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.commingling.quinlan.common.security.config.SecurityProperties;
import org.commingling.quinlan.controller.auth.vo.AuthLoginReqVO;
import org.commingling.quinlan.controller.auth.vo.AuthLoginRespVO;
import org.commingling.quinlan.enums.logger.LoginLogTypeEnum;
import org.commingling.quinlan.service.auth.AdminAuthService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.commingling.quinlan.common.security.util.SecurityFrameworkUtils.obtainAuthorization;

/**
 * @author ：Quinlan
 * @date ：2022/11/07 15:14
 */
@Api(tags = "管理后台 - 认证")
@RestController
@RequestMapping("/system/auth")
@Validated
@Slf4j
public class AuthController {

    @Resource
    private AdminAuthService authService;

    @Resource
    private SecurityProperties securityProperties;


    @PermitAll
    @PostMapping("/login")
    @ApiOperation("使用账号密码登录")
    public AuthLoginRespVO login(@RequestBody @Valid AuthLoginReqVO reqVO) {
        return authService.login(reqVO);
    }

    @PermitAll
    @PostMapping("/logout")
    @ApiOperation("登出系统")
    public Boolean logout(HttpServletRequest request) {
        String token = obtainAuthorization(request, securityProperties.getTokenHeader());
        if (StrUtil.isNotBlank(token)) {
            authService.logout(token, LoginLogTypeEnum.LOGOUT_SELF.getType());
        }
        return success(true);
    }



}
