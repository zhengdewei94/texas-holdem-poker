package com.zdw.poker.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 花色
 *
 * @author zheng
 */
@Getter
@ToString
@AllArgsConstructor
public enum Suit {

    /**
     * 黑桃
     */
    SPADE(1),
    /**
     * 红心
     */
    HEART(2),
    /**
     * 梅花
     */
    CLUB(3),
    /**
     * 方块
     */
    DIAMOND(4);

    private final int code;
}
