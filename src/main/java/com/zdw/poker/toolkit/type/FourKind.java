package com.zdw.poker.toolkit.type;

import com.zdw.poker.enums.ComparisonResult;
import com.zdw.poker.enums.Point;
import com.zdw.poker.exception.ComputeException;
import com.zdw.poker.model.Poker;
import com.zdw.poker.toolkit.KindPointSummary;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 四条
 *
 * @author zheng
 */
public class FourKind implements Type {

    @Override
    public boolean isType(Set<Poker> pokers) {
        Map<Point, Long> group = pokers.stream().collect(Collectors.groupingBy(Poker::getPoint, Collectors.counting()));
        // 大于四张牌就不可能是4条
        return group.size() <= Point.FOUR.getCode() && group.containsValue(4L);
    }

    @Override
    public ComparisonResult compare(Set<Poker> initiatorHand, Set<Poker> passiveHand, Set<Poker> board) {
        // noinspection DuplicatedCode
        Set<Poker> initiator = Type.getHandAndBoard(initiatorHand, board);
        Set<Poker> passive = Type.getHandAndBoard(passiveHand, board);
        Point initiatorKind = initiator.stream().collect(KindPointSummary.statistics()).getFourKind();
        Point passiveKind = passive.stream().collect(KindPointSummary.statistics()).getFourKind();
        ComparisonResult compare = initiatorKind.compare(passiveKind);
        if (compare != ComparisonResult.EQUAL) {
            return compare;
        }
        Poker initiatorMax = getMax(initiator, initiatorKind);
        Poker passiveMax = getMax(passive, initiatorKind);
        return initiatorMax.compare(passiveMax);
    }

    /**
     * 获取最大的牌
     *
     * @param pokers 牌
     * @param kind   四条的大小
     * @return Poker
     * @author zheng
     */
    Poker getMax(Set<Poker> pokers, Point kind) {
        return pokers.stream()
                .filter(poker -> poker.getPoint() != kind)
                .max(Poker::compareTo).orElseThrow(new ComputeException("计算四条获取最大单牌错误: pokers: " + pokers));
    }
}