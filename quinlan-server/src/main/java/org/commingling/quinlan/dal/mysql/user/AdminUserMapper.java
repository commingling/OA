package org.commingling.quinlan.dal.mysql.user;

import org.apache.ibatis.annotations.Mapper;
import org.commingling.quinlan.dal.dataobject.user.AdminUserDO;
import org.commingling.quinlan.framework.mybatis.mapper.BaseMapperX;

@Mapper
public interface AdminUserMapper extends BaseMapperX<AdminUserDO> {


    default AdminUserDO selectByUsername(String username) {
        return selectOne(AdminUserDO::getUsername, username);
    }
}
