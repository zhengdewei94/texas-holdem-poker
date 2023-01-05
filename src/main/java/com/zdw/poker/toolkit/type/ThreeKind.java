package com.zdw.poker.toolkit.type;

import com.zdw.poker.enums.ComparisonResult;
import com.zdw.poker.enums.Point;
import com.zdw.poker.model.Poker;
import com.zdw.poker.toolkit.KindPointSummary;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 三条
 *
 * @author zheng
 */
public class ThreeKind implements Type {

    @Override
    public boolean isType(Set<Poker> pokers) {
        Map<Point, Long> group = pokers.stream().collect(Collectors.groupingBy(Poker::getPoint, Collectors.counting()));
        // 大于5张牌就不可能是三条
        return group.size() <= Point.FIVE.getCode() && group.containsValue(3L);
    }

    @Override
    public ComparisonResult compare(Set<Poker> initiatorHand, Set<Poker> passiveHand, Set<Poker> board) {
        // noinspection DuplicatedCode
        Set<Poker> initiator = Type.getHandAndBoard(initiatorHand, board);
        Set<Poker> passive = Type.getHandAndBoard(passiveHand, board);
        Point initiatorKind = initiator.stream().collect(KindPointSummary.statistics()).getThreeKind();
        Point passiveKind = passive.stream().collect(KindPointSummary.statistics()).getThreeKind();
        ComparisonResult compare = initiatorKind.compare(passiveKind);
        // 如果三条不相等, 直接返回结果
        if (ComparisonResult.EQUAL != compare) {
            return compare;
        }
        // 如果三条相等则比较其他单牌大小
        List<Poker> initiatorHighCard = getHighCard(initiator, initiatorKind);
        List<Poker> passiveHighCard = getHighCard(passive, initiatorKind);
        for (int i = 0; i < 2; i++) {
            compare = initiatorHighCard.get(i).compare(passiveHighCard.get(i));
            if (compare == ComparisonResult.EQUAL) {
                continue;
            }
            return compare;
        }
        // 5次比较均相等,即相等
        return ComparisonResult.EQUAL;
    }

    /**
     * 获取其他高张牌,并从大到小排序
     *
     * @param pokers 牌
     * @param kind   四条的大小
     * @return List<Poker>
     * @author zheng
     */
    List<Poker> getHighCard(Set<Poker> pokers, Point kind) {
        return pokers.stream()
                .filter(poker -> poker.getPoint() != kind)
                .sorted(Comparator.comparing(Poker::getPoint).reversed())
                .collect(Collectors.toList());
    }
}