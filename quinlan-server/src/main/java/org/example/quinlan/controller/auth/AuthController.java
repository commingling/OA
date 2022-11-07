package org.example.quinlan.controller.auth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.example.quinlan.controller.auth.vo.AuthLoginReqVO;
import org.example.quinlan.controller.auth.vo.AuthLoginRespVO;
import org.example.quinlan.service.auth.AdminAuthService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.validation.Valid;

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

    @PostMapping("/login")
    @ApiOperation("使用账号密码登录")
    @PermitAll
    public AuthLoginRespVO login(@RequestBody @Valid AuthLoginReqVO reqVO) {
        return authService.login(reqVO);
    }

    public Boolean sendLoginSmsCode(@RequestBody @Valid AuthSmsSendReqVO reqVO) {
        authService.sendSmsCode(reqVO);
        return success(true);
    }

}
