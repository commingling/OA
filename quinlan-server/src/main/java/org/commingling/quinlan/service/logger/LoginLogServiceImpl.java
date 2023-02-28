package org.commingling.quinlan.service.logger;

import jakarta.annotation.Resource;
import org.commingling.quinlan.dal.convert.logger.LoginLogConvert;
import org.commingling.quinlan.dal.dataobject.logger.LoginLogDO;
import org.commingling.quinlan.dal.dto.logger.LoginLogCreateReqDTO;
import org.commingling.quinlan.dal.mysql.logger.LoginLogMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 登录日志 Service 实现
 */
@Service
@Validated
public class LoginLogServiceImpl implements LoginLogService{

    @Resource
    private LoginLogMapper loginLogMapper;

    @Override
    public void createLoginLog(LoginLogCreateReqDTO reqDTO) {
        LoginLogDO loginLog = LoginLogConvert.INSTANCE.convert(reqDTO);
        loginLogMapper.insert(loginLog);
    }

}
