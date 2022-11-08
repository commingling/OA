package org.commingling.quinlan.service.oauth2;

import org.commingling.quinlan.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import org.commingling.quinlan.dal.mysql.oauth2.OAuth2AccessTokenMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * OAuth2.0 Token Service 实现类
 *
 * @author Quinlan
 */
@Service
public class OAuth2TokenServiceImpl implements OAuth2TokenService{

    @Resource
    private OAuth2AccessTokenMapper oauth2AccessTokenMapper;

    @Resource
    private OAuth2AccessTokenRedisDAO oauth2AccessTokenRedisDAO;

    @Resource
    private OAuth2RefreshTokenMapper oauth2RefreshTokenMapper;

    @Override
    public OAuth2AccessTokenDO createAccessToken(Long userId, Integer userType, String clientId, List<String> scopes) {
//        OAuth2ClientDO clientDO = oauth2ClientService.validOAuthClientFromCache(clientId);
        // 创建刷新令牌
//        OAuth2RefreshTokenDO refreshTokenDO = createOAuth2RefreshToken(userId, userType, clientDO, scopes);
//        // 创建访问令牌
//        return createOAuth2AccessToken(refreshTokenDO, clientDO);
        return null;
    }

    @Override
    public OAuth2AccessTokenDO removeAccessToken(String accessToken) {
        // 删除访问令牌
        OAuth2AccessTokenDO accessTokenDO = oauth2AccessTokenMapper.selectByAccessToken(accessToken);
        if (accessTokenDO == null) {
            return null;
        }
        oauth2AccessTokenMapper.deleteById(accessTokenDO.getId());
        oauth2AccessTokenRedisDAO.delete(accessToken);
        // 删除刷新令牌
        oauth2RefreshTokenMapper.deleteByRefreshToken(accessTokenDO.getRefreshToken());
        return accessTokenDO;
    }


}
