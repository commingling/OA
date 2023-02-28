package org.commingling.quinlan.dal.mysql.oauth2;

import org.apache.ibatis.annotations.Mapper;
import org.commingling.quinlan.dal.vo.token.OAuth2AccessTokenPageReqVO;
import org.commingling.quinlan.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import org.commingling.quinlan.framework.mybatis.mapper.BaseMapperX;

@Mapper
public interface OAuth2AccessTokenMapper extends BaseMapperX<OAuth2AccessTokenDO> {

    default OAuth2AccessTokenDO selectByAccessToken(String accessToken) {
        return selectOne(OAuth2AccessTokenDO::getAccessToken, accessToken);
    }


}
