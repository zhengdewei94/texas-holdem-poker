package com.zdw.poker.toolkit.type;

import com.google.common.collect.Lists;
import com.zdw.poker.enums.ComparisonResult;
import com.zdw.poker.enums.Point;
import com.zdw.poker.model.Poker;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * 高张
 *
 * @author zheng
 */
public class HighCard implements Type {

    @Override
    public boolean isType(Set<Poker> pokers) {
        return true;
    }

    @Override
    public ComparisonResult compare(Set<Poker> initiatorHand, Set<Poker> passiveHand, Set<Poker> board) {
        List<Poker> initiator = getSortPoker(initiatorHand, board);
        List<Poker> passive = getSortPoker(passiveHand, board);
        // noinspection DuplicatedCode
        for (int i = 0; i < Point.FIVE.getCode(); i++) {
            ComparisonResult compare = initiator.get(i).compare(passive.get(i));
            if (ComparisonResult.EQUAL != compare) {
                return compare;
            }
        }
        return ComparisonResult.EQUAL;
    }

    List<Poker> getSortPoker(Set<Poker> hand, Set<Poker> board) {
        List<Poker> list = Lists.newArrayListWithExpectedSize(7);
        list.addAll(hand);
        list.addAll(board);
        list.sort(Comparator.comparing(Poker::getPoint).reversed());
        return list;
    }
}