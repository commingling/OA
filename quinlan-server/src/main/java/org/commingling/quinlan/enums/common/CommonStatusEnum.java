package org.commingling.quinlan.enums.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.commingling.quinlan.core.IntArrayValuable;

import java.util.Arrays;

/**
 * 通用状态枚举
 * @author ：Quinlan
 * @date ：2022/11/07 15:49
 */
@Getter
@AllArgsConstructor
public enum CommonStatusEnum implements IntArrayValuable {

    ENABLE(0, "开启"),
    DISABLE(1, "关闭");

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(CommonStatusEnum::getStatus).toArray();


    /**
     * 状态值
     */
    private final Integer status;
    /**
     * 状态名
     */
    private final String name;

    @Override
    public int[] array() {
        return ARRAYS;
    }

}
