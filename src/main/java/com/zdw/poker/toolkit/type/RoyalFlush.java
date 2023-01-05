package com.zdw.poker.toolkit.type;

import com.zdw.poker.enums.ComparisonResult;
import com.zdw.poker.enums.Point;
import com.zdw.poker.enums.Suit;
import com.zdw.poker.model.Poker;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 皇家同花顺
 *
 * @author zheng
 */
public class RoyalFlush implements Type {

    @Override
    public boolean isType(Set<Poker> pokers) {
        Map<Suit, Long> group = pokers.stream()
                // 过滤出大于等于10的牌
                .filter(poker -> poker.getPointCode() >= Point.TEN.getCode())
                // 把牌根据大小分组
                .collect(Collectors.groupingBy(Poker::getSuit, Collectors.counting()));
        // 如果 >= 10 的牌同一个花色出现5次那就代表示同花顺
        return group.containsValue(5L);
    }

    @Override
    public ComparisonResult compare(Set<Poker> initiatorHand, Set<Poker> passiveHand, Set<Poker> board) {
        // 如果出现两个皇家同花顺那只能是台面5张牌是皇家同花顺
        return ComparisonResult.EQUAL;
    }
}