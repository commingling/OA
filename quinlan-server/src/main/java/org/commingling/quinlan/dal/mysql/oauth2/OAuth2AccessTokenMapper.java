package org.commingling.quinlan.dal.mysql.oauth2;

import org.apache.ibatis.annotations.Mapper;
import org.commingling.quinlan.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import org.commingling.quinlan.dal.mysql.mapper.BaseMapperX;
import org.commingling.quinlan.service.oauth2.OAuth2TokenService;

@Mapper
public interface OAuth2AccessTokenMapper extends BaseMapperX<OAuth2AccessTokenDO> {


    default OAuth2AccessTokenDO selectByAccessToken(String accessToken){
        return selectOne(OAuth2AccessTokenDO::getAccessToken, accessToken);
    };
}
