package com.zdw.poker.test;

import com.google.common.collect.Sets;
import com.zdw.poker.enums.Point;
import com.zdw.poker.enums.Suit;
import com.zdw.poker.model.Poker;
import com.zdw.poker.toolkit.type.Type;

import java.util.Set;

/**
 * 牌型判断
 *
 * @author zheng
 */
public class JudgmentTest {

    public static void main(String[] args) {

        Set<Poker> pokers = Sets.newHashSet();
        // pokers.add(new Poker(Suit.SPADE, Point.ACE));
        pokers.add(new Poker(Suit.CLUB, Point.KING));
        pokers.add(new Poker(Suit.CLUB, Point.FOUR));
        pokers.add(new Poker(Suit.SPADE, Point.FIVE));
        pokers.add(new Poker(Suit.SPADE, Point.TWO));
        // pokers.add(new Poker(Suit.SPADE, Point.THREE));
        pokers.add(new Poker(Suit.HEART, Point.THREE));
        pokers.add(new Poker(Suit.SPADE, Point.FOUR));
        pokers.add(new Poker(Suit.SPADE, Point.SIX));
        System.out.println(Type.getType(pokers));
    }
}
