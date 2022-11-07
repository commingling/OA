package org.example.quinlan.controller.auth.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author ：Quinlan
 * @date ：2022/11/07 16:59
 */
@ApiModel("管理后台 - 发送手机验证码 Request VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthSmsSendReqVO {

    @ApiModelProperty(value = "手机号", required = true, example = "yudaoyuanma")
    @NotEmpty(message = "手机号不能为空")
    private String mobile;

    @ApiModelProperty(value = "短信场景", required = true, example = "1")
    @NotNull(message = "发送场景不能为空")
    private Integer scene;

}
