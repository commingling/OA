package org.commingling.quinlan.member.dal.mysql.user;

import org.apache.ibatis.annotations.Mapper;
import org.commingling.quinlan.framework.mybatis.mapper.BaseMapperX;
import org.commingling.quinlan.member.dal.dataobject.user.MemberUserDO;

/**
 * 会员 User Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface MemberUserMapper extends BaseMapperX<MemberUserDO> {

    default MemberUserDO selectByMobile(String mobile) {
        return selectOne(MemberUserDO::getMobile, mobile);
    }


}
