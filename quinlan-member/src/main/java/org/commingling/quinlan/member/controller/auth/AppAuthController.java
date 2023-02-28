package org.commingling.quinlan.member.controller.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.commingling.quinlan.common.pojo.CommonResult;
import org.commingling.quinlan.member.dal.vo.auth.AppAuthLoginReqVO;
import org.commingling.quinlan.member.dal.vo.auth.AppAuthLoginRespVO;
import org.commingling.quinlan.member.service.auth.MemberAuthService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static org.commingling.quinlan.common.pojo.CommonResult.success;

@Tag(name = "用户 APP - 认证")
@RestController
@RequestMapping("/member/auth")
@Validated
@Slf4j
public class AppAuthController {

    @Resource
    private MemberAuthService authService;

    public CommonResult<AppAuthLoginRespVO> login(@RequestBody @Valid AppAuthLoginReqVO reqVO) {
        return success(authService.login(reqVO));
    }


}
