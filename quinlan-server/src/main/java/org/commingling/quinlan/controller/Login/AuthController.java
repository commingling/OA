package org.commingling.quinlan.controller.Login;


import org.commingling.quinlan.common.AjaxResult;
import org.commingling.quinlan.domain.LoginBody;
import org.commingling.quinlan.service.SysLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Quinlan
 * @date ：2022/11/07 15:14
 */

@RestController
@RequestMapping("/system/auth")
public class AuthController {



    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody){
        AjaxResult ajax = new AjaxResult();
        // 生成令牌

        return ajax;
    }
}
