package com.zdw.poker.toolkit.type;

import com.zdw.poker.enums.ComparisonResult;
import com.zdw.poker.enums.Point;
import com.zdw.poker.model.Poker;
import com.zdw.poker.toolkit.StraightUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 顺子
 *
 * @author zheng
 */
public class Straight implements Type {

    @Override
    public boolean isType(Set<Poker> pokers) {
        return StraightUtil.isStraight(pokers);
    }

    @Override
    public ComparisonResult compare(Set<Poker> initiatorHand, Set<Poker> passiveHand, Set<Poker> board) {
        Point initiator = getStraightMaxPoint(initiatorHand, board);
        Point passive = getStraightMaxPoint(passiveHand, board);
        return initiator.compare(passive);
    }


    /**
     * 获取顺子最大的牌大小
     *
     * @param hand  底牌
     * @param board 台面(泛指桌上的五张公共牌)
     * @return Integer
     * @author zheng
     */
    public Point getStraightMaxPoint(Set<Poker> hand, Set<Poker> board) {
        // 获取牌大小并大到小排序
        List<Point> list = Type.getHandAndBoard(hand, board)
                // 取point
                .stream().map(Poker::getPoint)
                // 去重, 排序
                .distinct().sorted(Comparator.comparing(Point::getCode).reversed())
                .collect(Collectors.toList());
        return StraightUtil.getStraightMaxPoint(list);
    }
}