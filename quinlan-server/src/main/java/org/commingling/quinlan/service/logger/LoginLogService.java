package org.commingling.quinlan.service.logger;

import jakarta.validation.Valid;
import org.commingling.quinlan.dal.dto.logger.LoginLogCreateReqDTO;

/**
 * 登录日志 Service 接口
 */
public interface LoginLogService {

    /**
     * 创建登录日志
     *
     * @param reqDTO 日志信息
     */
    void createLoginLog(@Valid LoginLogCreateReqDTO reqDTO);

}
