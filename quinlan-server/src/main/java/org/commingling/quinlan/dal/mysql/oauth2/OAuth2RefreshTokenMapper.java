package org.commingling.quinlan.dal.mysql.oauth2;

import org.apache.ibatis.annotations.Mapper;
import org.commingling.quinlan.dal.dataobject.oauth2.OAuth2RefreshTokenDO;
import org.commingling.quinlan.framework.mybatis.mapper.BaseMapperX;
import org.commingling.quinlan.framework.mybatis.query.LambdaQueryWrapperX;

@Mapper
public interface OAuth2RefreshTokenMapper extends BaseMapperX<OAuth2RefreshTokenDO> {

    default int deleteByRefreshToken(String refreshToken) {
        return delete(new LambdaQueryWrapperX<OAuth2RefreshTokenDO>()
                .eq(OAuth2RefreshTokenDO::getRefreshToken, refreshToken));
    }

    default OAuth2RefreshTokenDO selectByRefreshToken(String refreshToken) {
        return selectOne(OAuth2RefreshTokenDO::getRefreshToken, refreshToken);
    }

}
