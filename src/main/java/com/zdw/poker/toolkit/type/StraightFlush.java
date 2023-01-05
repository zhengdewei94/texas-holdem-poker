package com.zdw.poker.toolkit.type;

import com.zdw.poker.enums.ComparisonResult;
import com.zdw.poker.enums.Point;
import com.zdw.poker.model.Poker;
import com.zdw.poker.toolkit.FlushPointSummary;

import java.util.Set;

/**
 * 同花顺
 *
 * @author zheng
 */
public class StraightFlush implements Type {

    @Override
    public boolean isType(Set<Poker> pokers) {
        return pokers.stream().collect(FlushPointSummary.statistics()).isStraightFlush();
    }

    @Override
    public ComparisonResult compare(Set<Poker> initiatorHand, Set<Poker> passiveHand, Set<Poker> board) {
        // 获取主动比较者的所有同花牌,从大到小排序
        Point initiatorMax = Type.getHandAndBoard(initiatorHand, board)
                .stream().collect(FlushPointSummary.statistics()).getStraightMaxPoint();
        // 获取被动比较者的所有同花牌,从大到小排序
        Point passiveMax = Type.getHandAndBoard(passiveHand, board)
                .stream().collect(FlushPointSummary.statistics()).getStraightMaxPoint();
        return initiatorMax.compare(passiveMax);
    }
}