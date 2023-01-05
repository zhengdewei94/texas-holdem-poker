package com.zdw.poker.enums;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Comparator;
import java.util.Set;

/**
 * 点数
 *
 * @author zheng
 */
@Getter
@ToString
@AllArgsConstructor
public enum Point implements Comparator<Point> {

    /**
     * 2
     */
    TWO(2),
    /**
     * 3
     */
    THREE(3),
    /**
     * 4
     */
    FOUR(4),
    /**
     * 5
     */
    FIVE(5),
    /**
     * 6
     */
    SIX(6),
    /**
     * 7
     */
    SEVEN(7),
    /**
     * 8
     */
    EIGHT(8),
    /**
     * 9
     */
    NINE(9),
    /**
     * 10
     */
    TEN(10),
    /**
     * J
     */
    JACK(11),
    /**
     * Q
     */
    QUEEN(12),
    /**
     * K
     */
    KING(13),
    /**
     * A
     */
    ACE(14);

    private final int code;

    /**
     * 最小顺子 A2345
     *
     * @return Set<Integer>
     * @author zheng
     */
    public static Set<Point> minStraight() {
        return Sets.newHashSet(Point.TWO, Point.THREE, Point.FOUR, Point.FIVE, Point.ACE);
    }

    @Override
    public int compare(Point initiator, Point passive) {
        return Integer.compare(initiator.getCode(), passive.getCode());
    }

    public ComparisonResult compare(Point passive) {
        if (this.getCode() > passive.getCode()) {
            return ComparisonResult.LARGE;
        }
        return this.getCode() < passive.getCode() ? ComparisonResult.SMALL : ComparisonResult.EQUAL;
    }
}
