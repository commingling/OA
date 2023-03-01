package org.commingling.quinlan.service.oauth2;

import cn.hutool.core.util.IdUtil;
import jakarta.annotation.Resource;
import org.commingling.quinlan.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import org.commingling.quinlan.dal.dataobject.oauth2.OAuth2ClientDO;
import org.commingling.quinlan.dal.dataobject.oauth2.OAuth2RefreshTokenDO;
import org.commingling.quinlan.dal.mysql.oauth2.OAuth2AccessTokenMapper;
import org.commingling.quinlan.dal.mysql.oauth2.OAuth2RefreshTokenMapper;
import org.commingling.quinlan.dal.redis.oauth2.OAuth2AccessTokenRedisDAO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * OAuth2.0 Token Service 实现类
 *
 * @author 芋道源码
 */
@Service
public class OAuth2TokenServiceImpl implements OAuth2TokenService{

    @Resource
    private OAuth2ClientService oauth2ClientService;

    @Resource
    private OAuth2RefreshTokenMapper oauth2RefreshTokenMapper;

    @Resource
    private OAuth2AccessTokenMapper oauth2AccessTokenMapper;

    @Resource
    private OAuth2AccessTokenRedisDAO oauth2AccessTokenRedisDAO;

    @Override
    public OAuth2AccessTokenDO createAccessToken(Long userId, Integer userType, String clientId, List<String> scopes) {
        OAuth2ClientDO clientDO = oauth2ClientService.validOAuthClientFromCache(clientId);
        // 创建刷新令牌
        OAuth2RefreshTokenDO refreshTokenDO = createOAuth2RefreshToken(userId, userType, clientDO, scopes);
        // 创建访问令牌
        return createOAuth2AccessToken(refreshTokenDO, clientDO);
    }

    private OAuth2AccessTokenDO createOAuth2AccessToken(OAuth2RefreshTokenDO refreshTokenDO, OAuth2ClientDO clientDO) {
        OAuth2AccessTokenDO accessTokenDO = new OAuth2AccessTokenDO().setAccessToken(generateAccessToken())
                .setUserId(refreshTokenDO.getUserId()).setUserType(refreshTokenDO.getUserType())
                .setClientId(clientDO.getClientId()).setScopes(refreshTokenDO.getScopes())
                .setRefreshToken(refreshTokenDO.getRefreshToken())
                .setExpiresTime(LocalDateTime.now().plusSeconds(clientDO.getAccessTokenValiditySeconds()));
        oauth2AccessTokenMapper.insert(accessTokenDO);
        // 记录到 Redis 中
//        oauth2AccessTokenRedisDAO.set(accessTokenDO);
        return accessTokenDO;
    }

    private OAuth2RefreshTokenDO createOAuth2RefreshToken(Long userId, Integer userType, OAuth2ClientDO clientDO, List<String> scopes) {
        OAuth2RefreshTokenDO refreshToken = new OAuth2RefreshTokenDO().setRefreshToken(generateRefreshToken())
                .setUserId(userId).setUserType(userType)
                .setClientId(clientDO.getClientId()).setScopes(scopes)
                .setExpiresTime(LocalDateTime.now().plusSeconds(clientDO.getRefreshTokenValiditySeconds()));
        oauth2RefreshTokenMapper.insert(refreshToken);
        return refreshToken;
    }

    private static String generateAccessToken() {
        return IdUtil.fastSimpleUUID();
    }

    private static String generateRefreshToken() {
        return IdUtil.fastSimpleUUID();
    }

}
