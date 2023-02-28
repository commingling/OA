package org.commingling.quinlan.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.commingling.quinlan.common.pojo.CommonResult;
import org.commingling.quinlan.dal.vo.AuthLoginReqVO;
import org.commingling.quinlan.dal.vo.AuthLoginRespVO;
import org.commingling.quinlan.service.auth.AdminAuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.commingling.quinlan.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 认证")
@RestController
@RequestMapping("/system/auth")
public class AuthController {

    @Resource
    private AdminAuthService authService;

    @PostMapping("/login")
    @PermitAll
    @Operation(summary = "使用账号密码登录")
    public CommonResult<AuthLoginRespVO> login(@RequestBody @Valid AuthLoginReqVO reqVO) {
        return success(authService.login(reqVO));
    }


}
