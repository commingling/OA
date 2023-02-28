package org.commingling.quinlan.api.social;

import org.commingling.quinlan.dal.dto.social.SocialUserBindReqDTO;
import org.commingling.quinlan.service.social.SocialUserService;

import javax.annotation.Resource;

public class SocialUserApiImpl implements SocialUserApi{

    @Resource
    private SocialUserService socialUserService;

}
