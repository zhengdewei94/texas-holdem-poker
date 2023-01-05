package com.zdw.poker.toolkit;

import com.zdw.poker.enums.Point;
import com.zdw.poker.model.Poker;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collector;

/**
 * 确定 三条/四条 点数
 *
 * @author zheng
 */
public class KindPointSummary implements Consumer<Poker> {

    private final Map<Point, Integer> map = new EnumMap<>(Point.class);

    private int count = 0;

    private Point threeKind = null;

    private Point fourKind = null;

    public static Collector<Poker, ?, KindPointSummary> statistics() {
        return Collector.of(KindPointSummary::new,
                KindPointSummary::accept, KindPointSummary::merge);
    }

    @Override
    public void accept(Poker poker) {
        count++;
        map.compute(poker.getPoint(), (k, v) -> {
            if (v == null) {
                return 1;
            }
            v++;
            if (v == Point.THREE.getCode()) {
                threeKind = k;
            }
            if (v == Point.FOUR.getCode()) {
                fourKind = k;
                threeKind = threeKind == k ? null : threeKind;
            }
            return v;
        });
        if (count == Point.SEVEN.getCode()) {
            map.clear();
        }
    }

    public KindPointSummary merge(KindPointSummary s) {
        return this;
    }

    public Point getThreeKind() {
        return threeKind;
    }

    public Point getFourKind() {
        return fourKind;
    }
}
