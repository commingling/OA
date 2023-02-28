package org.commingling.quinlan.service.social;

import cn.hutool.core.lang.Assert;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import org.commingling.quinlan.common.social.SocialTypeEnum;
import org.commingling.quinlan.dal.dataobject.social.SocialUserBindDO;
import org.commingling.quinlan.dal.dataobject.social.SocialUserDO;
import org.commingling.quinlan.dal.dto.social.SocialUserBindReqDTO;
import org.commingling.quinlan.dal.mysql.social.SocialUserBindMapper;
import org.commingling.quinlan.dal.mysql.social.SocialUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static org.commingling.quinlan.common.enums.error.ErrorCodeConstants.SOCIAL_USER_AUTH_FAILURE;
import static org.commingling.quinlan.common.exception.util.ServiceExceptionUtil.exception;
import static org.commingling.quinlan.common.util.json.JsonUtils.toJsonString;

/**
 * 社交用户 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
@Slf4j
public class SocialUserServiceImpl implements SocialUserService{

    @Resource
    private SocialUserMapper socialUserMapper;

    @javax.annotation.Resource
    private SocialUserBindMapper socialUserBindMapper;



}




