package org.commingling.quinlan.service.social;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.commingling.quinlan.dal.mysql.social.SocialUserBindMapper;
import org.commingling.quinlan.dal.mysql.social.SocialUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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

    @Resource
    private SocialUserBindMapper socialUserBindMapper;



}




