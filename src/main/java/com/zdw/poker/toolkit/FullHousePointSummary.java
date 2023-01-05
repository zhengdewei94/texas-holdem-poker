package com.zdw.poker.toolkit;

import com.zdw.poker.enums.Point;
import com.zdw.poker.model.Poker;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collector;

/**
 * 确定同花点数
 *
 * @author zheng
 */
public class FullHousePointSummary implements Consumer<Poker> {

    private final Map<Point, Integer> map = new EnumMap<>(Point.class);

    private int count = 0;

    private final List<Point> kinds = new ArrayList<>();

    private final List<Point> pairs = new ArrayList<>();

    public static Collector<Poker, ?, FullHousePointSummary> statistics() {
        return Collector.of(FullHousePointSummary::new,
                FullHousePointSummary::accept, FullHousePointSummary::merge);
    }

    @Override
    public void accept(Poker poker) {
        count++;
        map.compute(poker.getPoint(), (k, v) -> {
            if (v == null) {
                return 1;
            }
            v++;
            // 如果当前v+1 不等于2, 那么一定时3
            if (v == Point.TWO.getCode()) {
                pairs.add(k);
            } else {
                kinds.add(k);
                pairs.remove(k);
            }
            return v;
        });
        if (count == Point.SEVEN.getCode()) {
            map.clear();
        }
    }

    public FullHousePointSummary merge(FullHousePointSummary s) {
        return this;
    }

    public List<Point> getPoints() {
        if (kinds.size() > 1) {
            kinds.sort(Comparator.comparing(Point::getCode));
            return kinds;
        }
        pairs.sort(Comparator.comparing(Point::getCode));
        kinds.add(pairs.get(pairs.size() - 1));
        return kinds;
    }
}
