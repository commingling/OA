package org.commingling.quinlan.dal.dataobject.oauth2;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.commingling.quinlan.enums.common.UserTypeEnum;
import org.commingling.quinlan.mybatis.core.dataobject.BaseDO;

import java.util.Date;
import java.util.List;

/**
 * OAuth2 刷新令牌
 * @author ：Quinlan
 * @date ：2022/11/07 16:40
 */
@TableName(value = "system_oauth2_refresh_token", autoResultMap = true)
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class OAuth2RefreshTokenDO extends BaseDO {

    /**
     * 编号，数据库字典
     */
    private Long id;
    /**
     * 刷新令牌
     */
    private String refreshToken;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 用户类型
     *
     * 枚举 {@link UserTypeEnum}
     */
    private Integer userType;
    /**
     * 客户端编号
     *
     * 关联 {@link OAuth2ClientDO#getId()}
     */
    private String clientId;
    /**
     * 授权范围
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> scopes;
    /**
     * 过期时间
     */
    private Date expiresTime;


}
