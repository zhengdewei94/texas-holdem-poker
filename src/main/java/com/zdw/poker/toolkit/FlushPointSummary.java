package com.zdw.poker.toolkit;

import com.google.common.collect.Lists;
import com.zdw.poker.enums.Point;
import com.zdw.poker.enums.Suit;
import com.zdw.poker.model.Poker;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collector;

/**
 * 确定同花点数
 *
 * @author zheng
 */
public class FlushPointSummary implements Consumer<Poker> {

    private final Map<Suit, List<Point>> map = new EnumMap<>(Suit.class);

    private Suit suit = null;

    public static Collector<Poker, ?, FlushPointSummary> statistics() {
        return Collector.of(FlushPointSummary::new,
                FlushPointSummary::accept, FlushPointSummary::merge);
    }

    @Override
    public void accept(Poker poker) {
        map.compute(poker.getSuit(), (k, v) -> {
            if (v == null) {
                return Lists.newArrayList(poker.getPoint());
            }
            v.add(poker.getPoint());
            if (v.size() == Point.FIVE.getCode()) {
                suit = k;
            }
            return v;
        });
    }

    public FlushPointSummary merge(FlushPointSummary s) {
        return this;
    }

    public List<Point> getFlush() {
        List<Point> list = map.get(suit);
        list.sort(Comparator.comparing(Point::getCode).reversed());
        map.clear();
        return list;
    }

    public Point getStraightMaxPoint() {
        return StraightUtil.getStraightMaxPoint(getFlush());
    }

    public boolean isStraightFlush() {
        if (suit == null) {
            return false;
        }
        List<Point> list = map.get(suit);
        list.sort(Comparator.comparing(Point::getCode));
        map.clear();
        return StraightUtil.isStraight(list);
    }
}
