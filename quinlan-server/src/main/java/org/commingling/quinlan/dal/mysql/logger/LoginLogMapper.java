package org.commingling.quinlan.dal.mysql.logger;

import cn.hutool.db.PageResult;
import org.apache.ibatis.annotations.Mapper;
import org.commingling.quinlan.common.enums.logger.LoginResultEnum;
import org.commingling.quinlan.dal.dataobject.logger.LoginLogDO;
import org.commingling.quinlan.framework.mybatis.mapper.BaseMapperX;

import java.util.List;


@Mapper
public interface LoginLogMapper extends BaseMapperX<LoginLogDO> {



}
