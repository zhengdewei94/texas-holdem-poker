package com.zdw.poker.toolkit.type;

import com.zdw.poker.enums.ComparisonResult;
import com.zdw.poker.enums.Point;
import com.zdw.poker.enums.Suit;
import com.zdw.poker.model.Poker;
import com.zdw.poker.toolkit.FlushPointSummary;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 同花
 *
 * @author zheng
 */
public class Flush implements Type {

    @Override
    public boolean isType(Set<Poker> pokers) {
        Map<Suit, Long> group = pokers.stream().collect(Collectors.groupingBy(Poker::getSuit, Collectors.counting()));
        // 同一个花色出现次数 >= 5 次就代表同花
        return group.containsValue(5L) || group.containsValue(6L) || group.containsValue(7L);
    }

    @Override
    public ComparisonResult compare(Set<Poker> initiatorHand, Set<Poker> passiveHand, Set<Poker> board) {
        // 获取主动比较者的所有同花牌,从大到小排序
        List<Point> initiator = Type.getHandAndBoard(initiatorHand, board)
                .stream().collect(FlushPointSummary.statistics()).getFlush();
        // 获取被动比较者的所有同花牌,从大到小排序
        List<Point> passive = Type.getHandAndBoard(passiveHand, board)
                .stream().collect(FlushPointSummary.statistics()).getFlush();
        // 不管返回几张牌,只计算前五张, 从大到小开始比较
        // noinspection DuplicatedCode
        for (int i = 0; i < Point.FIVE.getCode(); i++) {
            ComparisonResult compare = initiator.get(i).compare(passive.get(i));
            if (compare != ComparisonResult.EQUAL) {
                return compare;
            }
        }
        // 5次比较均相等,即相等
        return ComparisonResult.EQUAL;
    }
}