package com.zdw.poker.toolkit.type;

import com.zdw.poker.enums.ComparisonResult;
import com.zdw.poker.enums.Point;
import com.zdw.poker.model.Poker;
import com.zdw.poker.toolkit.PairPointSummary;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 两对
 *
 * @author zheng
 */
public class TwoPair implements Type {

    @Override
    public boolean isType(Set<Poker> pokers) {
        Map<Point, Long> group = pokers.stream().collect(Collectors.groupingBy(Poker::getPoint, Collectors.counting()));
        return Collections.frequency(group.values(), 2L) >= 2;
    }

    @Override
    public ComparisonResult compare(Set<Poker> initiatorHand, Set<Poker> passiveHand, Set<Poker> board) {
        Set<Poker> initiator = Type.getHandAndBoard(initiatorHand, board);
        Set<Poker> passive = Type.getHandAndBoard(passiveHand, board);
        // 获取两对的牌大小
        List<Point> initiatorPoint = initiator.stream().collect(PairPointSummary.statistics()).getPairs();
        List<Point> passivePoint = passive.stream().collect(PairPointSummary.statistics()).getPairs();
        // 不管是两对还是三对, 只取前面最大两对
        for (int i = 0; i < Point.TWO.getCode(); i++) {
            ComparisonResult compare = initiatorPoint.get(i).compare(passivePoint.get(i));
            if (ComparisonResult.EQUAL != compare) {
                return compare;
            }
        }
        // 只取前面两个大小
        List<Point> points = initiatorPoint.subList(0, 2);
        return getMax(initiator, points).compare(getMax(passive, points));
    }

    /**
     * 获取最大的单张
     *
     * @param pokers 牌
     * @param points 需要排除的牌大小
     * @return Poker
     * @author zheng
     */
    Poker getMax(Set<Poker> pokers, List<Point> points) {
        List<Poker> list = pokers.stream().filter(poker -> !points.contains(poker.getPoint()))
                .sorted().collect(Collectors.toList());
        return list.get(list.size() - 1);
    }
}