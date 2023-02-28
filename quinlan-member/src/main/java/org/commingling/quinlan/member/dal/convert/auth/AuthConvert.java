package org.commingling.quinlan.member.dal.convert.auth;

import org.commingling.quinlan.dal.dto.oauth2.OAuth2AccessTokenRespDTO;
import org.commingling.quinlan.member.dal.vo.auth.AppAuthLoginRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthConvert {

    AuthConvert INSTANCE = Mappers.getMapper(AuthConvert.class);
    AppAuthLoginRespVO convert(OAuth2AccessTokenRespDTO bean);

}
