package com.zdw.poker.model;

import com.zdw.poker.enums.ComparisonResult;
import com.zdw.poker.enums.Point;
import com.zdw.poker.enums.Suit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

/**
 * 扑克
 *
 * @author zheng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Poker implements Comparator<Poker>, Comparable<Poker>, Serializable {

    /**
     * 花色
     */
    private Suit suit;

    /**
     * 大小
     */
    private Point point;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Poker)) {
            return false;
        }
        Poker poker = (Poker) o;
        return suit == poker.suit && point == poker.point;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, point);
    }

    public int getPointCode() {
        return this.point.getCode();
    }

    @Override
    public int compare(Poker initiator, Poker passive) {
        return Integer.compare(initiator.getPoint().getCode(), passive.getPoint().getCode());
    }

    @Override
    public int compareTo(@NonNull Poker passive) {
        return compare(this, passive);
    }

    public ComparisonResult compare(Poker passive) {
        if (this.point.getCode() > passive.getPoint().getCode()) {
            return ComparisonResult.LARGE;
        }
        return this.point.getCode() < passive.getPoint().getCode() ? ComparisonResult.SMALL : ComparisonResult.EQUAL;
    }
}
