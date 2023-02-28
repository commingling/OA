package org.commingling.quinlan.api.social;

import jakarta.annotation.Resource;
import org.commingling.quinlan.service.social.SocialUserService;


public class SocialUserApiImpl implements SocialUserApi{

    @Resource
    private SocialUserService socialUserService;

}
