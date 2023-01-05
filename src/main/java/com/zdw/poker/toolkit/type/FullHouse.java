package com.zdw.poker.toolkit.type;

import com.zdw.poker.enums.ComparisonResult;
import com.zdw.poker.enums.Point;
import com.zdw.poker.model.Poker;
import com.zdw.poker.toolkit.FullHousePointSummary;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 葫芦(三条+一对)
 *
 * @author zheng
 */
public class FullHouse implements Type {

    @Override
    public boolean isType(Set<Poker> pokers) {
        Map<Point, Long> group = pokers.stream().collect(Collectors.groupingBy(Poker::getPoint, Collectors.counting()));
        // 大于四张牌就不可能是4条
        if (group.size() > Point.FOUR.getCode()) {
            return false;
        }
        // 两个三条 或 一个三条 + 一对
        return (group.containsValue(3L) && group.containsValue(2L))
                || (Collections.frequency(group.values(), 3L) == 2);
    }

    @Override
    public ComparisonResult compare(Set<Poker> initiatorHand, Set<Poker> passiveHand, Set<Poker> board) {
        List<Point> initiator = Type.getHandAndBoard(initiatorHand, board)
                .stream().collect(FullHousePointSummary.statistics()).getPoints();
        List<Point> passive = Type.getHandAndBoard(passiveHand, board)
                .stream().collect(FullHousePointSummary.statistics()).getPoints();
        // 比较三条
        ComparisonResult compare = initiator.get(0).compare(passive.get(0));
        if (compare != ComparisonResult.EQUAL) {
            return compare;
        }
        // 比较对子
        return initiator.get(1).compare(passive.get(1));
    }
}