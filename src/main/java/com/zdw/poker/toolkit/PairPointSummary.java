package com.zdw.poker.toolkit;

import com.zdw.poker.enums.Point;
import com.zdw.poker.model.Poker;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collector;

/**
 * 确定 一对/两对 点数
 *
 * @author zheng
 */
public class PairPointSummary implements Consumer<Poker> {

    private final Map<Point, Integer> map = new EnumMap<>(Point.class);

    private int count = 0;

    private final List<Point> pairs = new ArrayList<>();

    public static Collector<Poker, ?, PairPointSummary> statistics() {
        return Collector.of(PairPointSummary::new,
                PairPointSummary::accept, PairPointSummary::merge);
    }

    @Override
    public void accept(Poker poker) {
        count++;
        map.compute(poker.getPoint(), (k, v) -> {
            if (v == null) {
                return 1;
            }
            v++;
            if (v == Point.TWO.getCode()) {
                pairs.add(k);
            }
            return v;
        });
        if (count == Point.SEVEN.getCode()) {
            map.clear();
        }
    }

    public PairPointSummary merge(PairPointSummary s) {
        return this;
    }

    public Point getPair() {
        return pairs.get(0);
    }

    public List<Point> getPairs() {
        pairs.sort(Comparator.comparing(Point::getCode).reversed());
        return pairs;
    }
}
