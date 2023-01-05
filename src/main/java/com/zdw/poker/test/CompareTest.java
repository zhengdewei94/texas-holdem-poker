package com.zdw.poker.test;

import com.google.common.collect.Sets;
import com.zdw.poker.enums.ComparisonResult;
import com.zdw.poker.enums.Point;
import com.zdw.poker.enums.PokerType;
import com.zdw.poker.enums.Suit;
import com.zdw.poker.model.Poker;

import java.util.Set;

/**
 * 牌型比较
 *
 * @author zheng
 */
public class CompareTest {

    public static void main(String[] args) {
        compareRoyalFlush();
        compareStraightFlush();
        compareFourKind();
        compareFullHouse();
        compareFlush();
        compareStraight();
        compareThreeKind();
        compareTwoPair();
        compareOnePair();
        compareHighCard();
    }

    /**
     * 比较皇家同花顺
     *
     * @author zheng
     */
    public static void compareRoyalFlush() {
        Set<Poker> board = Sets.newHashSet();
        board.add(new Poker(Suit.SPADE, Point.ACE));
        board.add(new Poker(Suit.SPADE, Point.KING));
        board.add(new Poker(Suit.SPADE, Point.QUEEN));
        board.add(new Poker(Suit.SPADE, Point.JACK));
        board.add(new Poker(Suit.SPADE, Point.TEN));

        Set<Poker> initiatorHand = Sets.newHashSet();
        initiatorHand.add(new Poker(Suit.DIAMOND, Point.ACE));
        initiatorHand.add(new Poker(Suit.DIAMOND, Point.TWO));

        Set<Poker> passiveHand = Sets.newHashSet();
        passiveHand.add(new Poker(Suit.HEART, Point.ACE));
        passiveHand.add(new Poker(Suit.CLUB, Point.ACE));

        ComparisonResult compare = PokerType.ROYAL_FLUSH.getType().compare(initiatorHand, passiveHand, board);
        System.out.println("皇家同花顺: " + compare);
    }

    /**
     * 同花顺
     *
     * @author zheng
     */
    public static void compareStraightFlush() {
        Set<Poker> board = Sets.newHashSet();
        board.add(new Poker(Suit.SPADE, Point.QUEEN));
        board.add(new Poker(Suit.SPADE, Point.FIVE));
        board.add(new Poker(Suit.SPADE, Point.FOUR));
        board.add(new Poker(Suit.SPADE, Point.THREE));
        board.add(new Poker(Suit.SPADE, Point.TWO));

        Set<Poker> initiatorHand = Sets.newHashSet();
        initiatorHand.add(new Poker(Suit.DIAMOND, Point.TWO));
        initiatorHand.add(new Poker(Suit.SPADE, Point.ACE));

        Set<Poker> passiveHand = Sets.newHashSet();
        passiveHand.add(new Poker(Suit.CLUB, Point.ACE));
        passiveHand.add(new Poker(Suit.SPADE, Point.SIX));
        ComparisonResult compare = PokerType.STRAIGHT_FLUSH.getType().compare(initiatorHand, passiveHand, board);
        System.out.println("同花顺: " + compare);
    }

    /**
     * 四条
     *
     * @author zheng
     */
    public static void compareFourKind() {
        Set<Poker> board = Sets.newHashSet();
        board.add(new Poker(Suit.CLUB, Point.TEN));
        board.add(new Poker(Suit.DIAMOND, Point.TEN));
        board.add(new Poker(Suit.SPADE, Point.TEN));
        board.add(new Poker(Suit.SPADE, Point.NINE));
        board.add(new Poker(Suit.HEART, Point.TEN));

        Set<Poker> initiatorHand = Sets.newHashSet();
        initiatorHand.add(new Poker(Suit.CLUB, Point.NINE));
        initiatorHand.add(new Poker(Suit.DIAMOND, Point.NINE));

        Set<Poker> passiveHand = Sets.newHashSet();
        passiveHand.add(new Poker(Suit.DIAMOND, Point.JACK));
        passiveHand.add(new Poker(Suit.HEART, Point.NINE));
        ComparisonResult compare = PokerType.FOUR_OF_A_KIND.getType().compare(initiatorHand, passiveHand, board);
        System.out.println("四条: " + compare);
    }

    /**
     * 葫芦
     *
     * @author zheng
     */
    public static void compareFullHouse() {
        Set<Poker> board = Sets.newHashSet();
        board.add(new Poker(Suit.CLUB, Point.TEN));
        board.add(new Poker(Suit.DIAMOND, Point.TEN));
        board.add(new Poker(Suit.SPADE, Point.NINE));
        board.add(new Poker(Suit.HEART, Point.NINE));
        board.add(new Poker(Suit.HEART, Point.TWO));

        Set<Poker> initiatorHand = Sets.newHashSet();
        initiatorHand.add(new Poker(Suit.CLUB, Point.NINE));
        initiatorHand.add(new Poker(Suit.DIAMOND, Point.THREE));

        Set<Poker> passiveHand = Sets.newHashSet();
        passiveHand.add(new Poker(Suit.CLUB, Point.TWO));
        passiveHand.add(new Poker(Suit.DIAMOND, Point.TWO));
        ComparisonResult compare = PokerType.FULL_HOUSE.getType().compare(initiatorHand, passiveHand, board);
        System.out.println("葫芦: " + compare);
    }

    /**
     * 同花
     *
     * @author zheng
     */
    public static void compareFlush() {
        Set<Poker> board = Sets.newHashSet();
        board.add(new Poker(Suit.SPADE, Point.KING));
        board.add(new Poker(Suit.SPADE, Point.JACK));
        board.add(new Poker(Suit.SPADE, Point.NINE));
        board.add(new Poker(Suit.SPADE, Point.SEVEN));
        board.add(new Poker(Suit.SPADE, Point.EIGHT));

        Set<Poker> initiatorHand = Sets.newHashSet();
        initiatorHand.add(new Poker(Suit.CLUB, Point.NINE));
        initiatorHand.add(new Poker(Suit.SPADE, Point.QUEEN));

        Set<Poker> passiveHand = Sets.newHashSet();
        passiveHand.add(new Poker(Suit.CLUB, Point.JACK));
        passiveHand.add(new Poker(Suit.SPADE, Point.ACE));
        ComparisonResult compare = PokerType.FLUSH.getType().compare(initiatorHand, passiveHand, board);
        System.out.println("同花: " + compare);
    }

    /**
     * 比较顺子
     *
     * @author zheng
     */
    public static void compareStraight() {
        Set<Poker> board = Sets.newHashSet();
        board.add(new Poker(Suit.HEART, Point.QUEEN));
        board.add(new Poker(Suit.DIAMOND, Point.JACK));
        board.add(new Poker(Suit.CLUB, Point.TEN));
        board.add(new Poker(Suit.DIAMOND, Point.NINE));
        board.add(new Poker(Suit.DIAMOND, Point.FIVE));


        Set<Poker> initiatorHand = Sets.newHashSet();
        initiatorHand.add(new Poker(Suit.CLUB, Point.EIGHT));
        initiatorHand.add(new Poker(Suit.SPADE, Point.TWO));

        Set<Poker> passiveHand = Sets.newHashSet();
        passiveHand.add(new Poker(Suit.DIAMOND, Point.EIGHT));
        passiveHand.add(new Poker(Suit.SPADE, Point.SEVEN));
        ComparisonResult compare = PokerType.STRAIGHT.getType().compare(initiatorHand, passiveHand, board);
        System.out.println("顺子: " + compare);
    }

    /**
     * 三条
     *
     * @author zheng
     */
    public static void compareThreeKind() {
        Set<Poker> board = Sets.newHashSet();
        board.add(new Poker(Suit.DIAMOND, Point.JACK));
        board.add(new Poker(Suit.HEART, Point.TWO));
        board.add(new Poker(Suit.HEART, Point.THREE));
        board.add(new Poker(Suit.CLUB, Point.TEN));
        board.add(new Poker(Suit.HEART, Point.SEVEN));


        Set<Poker> initiatorHand = Sets.newHashSet();
        initiatorHand.add(new Poker(Suit.SPADE, Point.TEN));
        initiatorHand.add(new Poker(Suit.DIAMOND, Point.TEN));

        Set<Poker> passiveHand = Sets.newHashSet();
        passiveHand.add(new Poker(Suit.SPADE, Point.JACK));
        passiveHand.add(new Poker(Suit.CLUB, Point.JACK));

        ComparisonResult compare = PokerType.THREE_OF_A_KIND.getType().compare(initiatorHand, passiveHand, board);
        System.out.println("三条: " + compare);
    }

    /**
     * 两对
     *
     * @author zheng
     */
    public static void compareTwoPair() {
        Set<Poker> board = Sets.newHashSet();
        board.add(new Poker(Suit.DIAMOND, Point.JACK));
        board.add(new Poker(Suit.HEART, Point.TWO));
        board.add(new Poker(Suit.HEART, Point.THREE));
        board.add(new Poker(Suit.CLUB, Point.TEN));
        board.add(new Poker(Suit.SPADE, Point.TWO));


        Set<Poker> initiatorHand = Sets.newHashSet();
        initiatorHand.add(new Poker(Suit.CLUB, Point.JACK));
        initiatorHand.add(new Poker(Suit.DIAMOND, Point.THREE));

        Set<Poker> passiveHand = Sets.newHashSet();
        passiveHand.add(new Poker(Suit.SPADE, Point.JACK));
        passiveHand.add(new Poker(Suit.HEART, Point.TEN));

        ComparisonResult compare = PokerType.TWO_PAIR.getType().compare(initiatorHand, passiveHand, board);
        System.out.println("两对: " + compare);
    }

    /**
     * 一对
     *
     * @author zheng
     */
    public static void compareOnePair() {
        Set<Poker> board = Sets.newHashSet();
        board.add(new Poker(Suit.DIAMOND, Point.JACK));
        board.add(new Poker(Suit.HEART, Point.TWO));
        board.add(new Poker(Suit.HEART, Point.THREE));
        board.add(new Poker(Suit.CLUB, Point.TEN));
        board.add(new Poker(Suit.SPADE, Point.SEVEN));


        Set<Poker> initiatorHand = Sets.newHashSet();
        initiatorHand.add(new Poker(Suit.CLUB, Point.SEVEN));
        initiatorHand.add(new Poker(Suit.DIAMOND, Point.FOUR));

        Set<Poker> passiveHand = Sets.newHashSet();
        passiveHand.add(new Poker(Suit.DIAMOND, Point.SEVEN));
        passiveHand.add(new Poker(Suit.CLUB, Point.FOUR));

        ComparisonResult compare = PokerType.ONE_PAIR.getType().compare(initiatorHand, passiveHand, board);
        System.out.println("一对: " + compare);
    }

    /**
     * 高张
     *
     * @author zheng
     */
    public static void compareHighCard() {
        Set<Poker> board = Sets.newHashSet();
        board.add(new Poker(Suit.DIAMOND, Point.JACK));
        board.add(new Poker(Suit.HEART, Point.KING));
        board.add(new Poker(Suit.HEART, Point.SIX));
        board.add(new Poker(Suit.CLUB, Point.TEN));
        board.add(new Poker(Suit.SPADE, Point.SEVEN));


        Set<Poker> initiatorHand = Sets.newHashSet();
        initiatorHand.add(new Poker(Suit.CLUB, Point.FIVE));
        initiatorHand.add(new Poker(Suit.DIAMOND, Point.NINE));

        Set<Poker> passiveHand = Sets.newHashSet();
        passiveHand.add(new Poker(Suit.DIAMOND, Point.FIVE));
        passiveHand.add(new Poker(Suit.CLUB, Point.ACE));

        ComparisonResult compare = PokerType.HIGH_CARD.getType().compare(initiatorHand, passiveHand, board);
        System.out.println("高张: " + compare);
    }
}
