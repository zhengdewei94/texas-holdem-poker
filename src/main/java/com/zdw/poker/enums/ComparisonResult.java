package com.zdw.poker.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 比较结果
 *
 * @author zheng
 */
@Getter
@ToString
@AllArgsConstructor
public enum ComparisonResult {

    /**
     * 小的
     */
    SMALL(-1),
    /**
     * 相等
     */
    EQUAL(0),
    /**
     * 大的
     */
    LARGE(1);

    private final int code;
}
