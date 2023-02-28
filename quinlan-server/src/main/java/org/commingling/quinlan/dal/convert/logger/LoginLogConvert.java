package org.commingling.quinlan.dal.convert.logger;


import cn.hutool.db.PageResult;
import org.commingling.quinlan.dal.dataobject.logger.LoginLogDO;
import org.commingling.quinlan.dal.dto.logger.LoginLogCreateReqDTO;
import org.commingling.quinlan.dal.vo.loginlog.LoginLogExcelVO;
import org.commingling.quinlan.dal.vo.loginlog.LoginLogRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LoginLogConvert {

    LoginLogConvert INSTANCE = Mappers.getMapper(LoginLogConvert.class);

    PageResult<LoginLogRespVO> convertPage(PageResult<LoginLogDO> page);

    List<LoginLogExcelVO> convertList(List<LoginLogDO> list);

    LoginLogDO convert(LoginLogCreateReqDTO bean);

}
