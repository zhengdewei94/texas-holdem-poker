package com.zdw.poker.enums;

import com.google.common.collect.Lists;
import com.zdw.poker.toolkit.type.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 牌型
 *
 * @author zheng
 */
@Getter
@ToString
@AllArgsConstructor
public enum PokerType {

    /**
     * 高张
     */
    HIGH_CARD(1, "高张", new HighCard()),
    /**
     * 一对
     */
    ONE_PAIR(2, "一对", new OnePair()),
    /**
     * 两对
     */
    TWO_PAIR(3, "两对", new TwoPair()),
    /**
     * 三条
     */
    THREE_OF_A_KIND(4, "三条", new ThreeKind()),
    /**
     * 顺子
     */
    STRAIGHT(5, "顺子", new Straight()),
    /**
     * 同花
     */
    FLUSH(6, "同花", new Flush()),
    /**
     * 葫芦(三条+一对)
     */
    FULL_HOUSE(7, "葫芦", new FullHouse()),
    /**
     * 四条
     */
    FOUR_OF_A_KIND(8, "四条", new FourKind()),
    /**
     * 同花顺
     */
    STRAIGHT_FLUSH(9, "同花顺", new StraightFlush()),
    /**
     * 皇家同花顺
     */
    ROYAL_FLUSH(10, "皇家同花顺", new RoyalFlush());

    private final int code;

    private final String value;

    private final Type type;

    public static List<PokerType> sortType() {
        ArrayList<PokerType> list = Lists.newArrayList(values());
        list.sort(Comparator.comparing(PokerType::getCode).reversed());
        return list;
    }
}
