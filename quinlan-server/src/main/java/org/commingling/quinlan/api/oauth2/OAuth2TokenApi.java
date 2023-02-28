package org.commingling.quinlan.api.oauth2;

import org.commingling.quinlan.dal.dto.oauth2.OAuth2AccessTokenCreateReqDTO;
import org.commingling.quinlan.dal.dto.oauth2.OAuth2AccessTokenRespDTO;

import javax.validation.Valid;

/**
 * OAuth2.0 Token API 接口
 *
 * @author 芋道源码
 */
public interface OAuth2TokenApi {

    /**
     * 创建访问令牌
     *
     * @param reqDTO 访问令牌的创建信息
     * @return 访问令牌的信息
     */
    OAuth2AccessTokenRespDTO createAccessToken(@Valid OAuth2AccessTokenCreateReqDTO reqDTO);

}
