package com.zdw.poker.toolkit.type;

import com.google.common.collect.Sets;
import com.zdw.poker.enums.ComparisonResult;
import com.zdw.poker.enums.PokerType;
import com.zdw.poker.model.Poker;

import java.util.Set;

/**
 * 牌的类型接口
 *
 * @author zheng
 */
public interface Type {

    /**
     * 是否是当前类型
     *
     * @param pokers 牌
     * @return boolean
     * @author zheng
     */
    boolean isType(Set<Poker> pokers);

    /**
     * 发起者比较被动者, 发起者大返回 true, 否则 false
     *
     * @param initiatorHand 发起比较者的底牌
     * @param passiveHand   被动接受者的底牌
     * @param board         台面(泛指桌上的五张公共牌)
     * @return ComparisonResult
     * @author zheng
     */
    ComparisonResult compare(Set<Poker> initiatorHand, Set<Poker> passiveHand, Set<Poker> board);

    /**
     * 获取当前牌型
     *
     * @param pokers 牌
     * @return PokerType
     * @author zheng
     */
    static PokerType getType(Set<Poker> pokers) {
        for (PokerType pokerType : PokerType.sortType()) {
            if (pokerType.getType().isType(pokers)) {
                return pokerType;
            }
        }
        return PokerType.HIGH_CARD;
    }

    /**
     * 获取底牌+台面
     *
     * @param hand  底牌
     * @param board 台面(泛指桌上的五张公共牌)
     * @return Set<Poker>
     * @author zheng
     */
    static Set<Poker> getHandAndBoard(Set<Poker> hand, Set<Poker> board) {
        Set<Poker> all = Sets.newHashSet();
        all.addAll(hand);
        all.addAll(board);
        return all;
    }
}