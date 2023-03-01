package org.commingling.quinlan.common.pojo;

import lombok.Data;
import org.commingling.quinlan.common.exception.ErrorCode;
import org.commingling.quinlan.common.exception.enums.GlobalErrorCodeConstants;

import java.io.Serializable;

/**
 * 通用返回
 *
 * @param <T> 数据泛型
 */
@Data
public class CommonResult<T> implements Serializable {

    /**
     * 错误码
     *
     * @see ErrorCode#getCode()
     */
    private Integer code;
    /**
     * 返回数据
     */
    private T data;
    /**
     * 错误提示，用户可阅读
     *
     * @see ErrorCode#getMsg() ()
     */
    private String msg;

    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.code = GlobalErrorCodeConstants.SUCCESS.getCode();
        result.data = data;
        result.msg = "";
        return result;
    }



}
