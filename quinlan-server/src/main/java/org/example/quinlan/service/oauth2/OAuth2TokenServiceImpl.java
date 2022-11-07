package org.example.quinlan.service.oauth2;

import org.apache.commons.lang3.time.DateUtils;
import org.example.quinlan.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import org.example.quinlan.dal.dataobject.oauth2.OAuth2ClientDO;
import org.example.quinlan.dal.dataobject.oauth2.OAuth2RefreshTokenDO;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;


/**
 * OAuth2.0 Token Service 实现类
 *
 * @author Quinlan
 */
@Service
public class OAuth2TokenServiceImpl implements OAuth2TokenService{
    @Override
    public OAuth2AccessTokenDO createAccessToken(Long userId, Integer userType, String clientId, List<String> scopes) {
//        OAuth2ClientDO clientDO = oauth2ClientService.validOAuthClientFromCache(clientId);
        // 创建刷新令牌
//        OAuth2RefreshTokenDO refreshTokenDO = createOAuth2RefreshToken(userId, userType, clientDO, scopes);
//        // 创建访问令牌
//        return createOAuth2AccessToken(refreshTokenDO, clientDO);
        return null;
    }


}
