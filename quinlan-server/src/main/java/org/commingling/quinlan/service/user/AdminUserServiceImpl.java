package org.commingling.quinlan.service.user;

import org.commingling.quinlan.dal.dataobject.user.AdminUserDO;
import org.commingling.quinlan.dal.mysql.user.AdminUserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 后台用户 Service 实现类
 * @author ：Quinlan
 * @date ：2022/11/07 16:12
 */
@Service
public class AdminUserServiceImpl implements AdminUserService{

    @Resource
    private AdminUserMapper userMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public AdminUserDO getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public boolean isPasswordMatch(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
