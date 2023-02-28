package org.commingling.quinlan.dal.dataobject.social;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.commingling.quinlan.common.social.SocialTypeEnum;
import org.commingling.quinlan.dal.dataobject.BaseDO;

/**
 * 社交（三方）用户
 *
 * @author weir
 */
@TableName(value = "system_social_user", autoResultMap = true)
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocialUserDO extends BaseDO {

    /**
     * 自增主键
     */
    @TableId
    private Long id;
    /**
     * 社交平台的类型
     *
     * 枚举 {@link SocialTypeEnum}
     */
    private Integer type;

    /**
     * 社交 openid
     */
    private String openid;
    /**
     * 社交 token
     */
    private String token;
    /**
     * 原始 Token 数据，一般是 JSON 格式
     */
    private String rawTokenInfo;

    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 原始用户数据，一般是 JSON 格式
     */
    private String rawUserInfo;

    /**
     * 最后一次的认证 code
     */
    private String code;
    /**
     * 最后一次的认证 state
     */
    private String state;

}


