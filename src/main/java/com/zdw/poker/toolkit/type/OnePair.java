package com.zdw.poker.toolkit.type;

import com.zdw.poker.enums.ComparisonResult;
import com.zdw.poker.enums.Point;
import com.zdw.poker.model.Poker;
import com.zdw.poker.toolkit.PairPointSummary;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 一对
 *
 * @author zheng
 */
public class OnePair implements Type {

    @Override
    public boolean isType(Set<Poker> pokers) {
        Map<Point, Long> group = pokers.stream().collect(Collectors.groupingBy(Poker::getPoint, Collectors.counting()));
        return Collections.frequency(group.values(), 2L) == 1;
    }

    @Override
    public ComparisonResult compare(Set<Poker> initiatorHand, Set<Poker> passiveHand, Set<Poker> board) {
        Set<Poker> initiator = Type.getHandAndBoard(initiatorHand, board);
        Set<Poker> passive = Type.getHandAndBoard(passiveHand, board);
        Point initiatorPair = initiator.stream().collect(PairPointSummary.statistics()).getPair();
        Point passivePair = passive.stream().collect(PairPointSummary.statistics()).getPair();
        ComparisonResult compare = initiatorPair.compare(passivePair);
        if (ComparisonResult.EQUAL != compare) {
            return compare;
        }
        // 对子相等, 比较单张
        List<Poker> initiatorHighCard = getHighCard(initiator, initiatorPair);
        List<Poker> passiveHighCard = getHighCard(passive, passivePair);
        // 只比较前面三张
        for (int i = 0; i < Point.THREE.getCode(); i++) {
            compare = initiatorHighCard.get(i).compare(passiveHighCard.get(i));
            if (ComparisonResult.EQUAL != compare) {
                return compare;
            }
        }
        return ComparisonResult.EQUAL;
    }

    /**
     * 获取所有的高张牌,并从大到小排序
     *
     * @param pokers 牌
     * @return List<Poker>
     * @author zheng
     */
    List<Poker> getHighCard(Set<Poker> pokers, Point pair) {
        return pokers.stream().filter(poker -> poker.getPoint() != pair)
                .sorted(Comparator.comparing(Poker::getPoint).reversed())
                .collect(Collectors.toList());
    }
}
