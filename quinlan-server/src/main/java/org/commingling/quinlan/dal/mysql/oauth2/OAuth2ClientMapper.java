package org.commingling.quinlan.dal.mysql.oauth2;

import org.apache.ibatis.annotations.Mapper;
import org.commingling.quinlan.common.pojo.PageResult;
import org.commingling.quinlan.dal.dataobject.oauth2.OAuth2ClientDO;
import org.commingling.quinlan.dal.vo.oauth2.OAuth2ClientPageReqVO;
import org.commingling.quinlan.framework.mybatis.mapper.BaseMapperX;
import org.commingling.quinlan.framework.mybatis.query.LambdaQueryWrapperX;


/**
 * OAuth2 客户端 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface OAuth2ClientMapper extends BaseMapperX<OAuth2ClientDO> {

    default PageResult<OAuth2ClientDO> selectPage(OAuth2ClientPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OAuth2ClientDO>()
                .likeIfPresent(OAuth2ClientDO::getName, reqVO.getName())
                .eqIfPresent(OAuth2ClientDO::getStatus, reqVO.getStatus())
                .orderByDesc(OAuth2ClientDO::getId));
    }

    default OAuth2ClientDO selectByClientId(String clientId) {
        return selectOne(OAuth2ClientDO::getClientId, clientId);
    }

}
