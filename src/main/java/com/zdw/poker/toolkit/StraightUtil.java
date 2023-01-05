package com.zdw.poker.toolkit;

import com.zdw.poker.enums.Point;
import com.zdw.poker.exception.ComputeException;
import com.zdw.poker.model.Poker;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 顺子工具类
 *
 * @author zheng
 */
public class StraightUtil {

    private StraightUtil() {
    }

    /**
     * 是否顺子
     *
     * @param pokers 牌
     * @return boolean
     * @author zheng
     */
    public static boolean isStraight(Set<Poker> pokers) {
        // 获取牌大小并小到大排序
        List<Point> points = pokers.stream()
                // 取point
                .map(Poker::getPoint)
                // 去重, 排序
                .distinct().sorted().collect(Collectors.toList());
        // 不满5张不是顺子
        if (points.size() < Point.FIVE.getCode()) {
            return false;
        }
        return isStraight(points);
    }

    /**
     * 判断是否是顺子
     *
     * @param points 点数, 从小到大排序
     * @return boolean
     * @author zheng
     */
    public static boolean isStraight(List<Point> points) {
        // 多的牌的数量(决定循环几次), 从小到大排序
        int surplus = points.size() - Point.FOUR.getCode();
        // 从小到大算,因为只是确定牌型, 暂时不需要获取最大的牌是什么
        // point = 5 : max = 5 - 1 , min = 0
        // point = 6 : max = 6 - 1 , min = 2 - 1  /   max = 6 - 2 , min = 2 - 2
        // point = 7 : max = 7 - 1 , min = 3 - 1  /   max = 7 - 2 , min = 3 - 2  /   max = 7 - 3 , min = 3 - 3
        for (int i = 0; i < surplus; i++) {
            Point min = points.get(i);
            Point max = points.get(points.size() - surplus + i);
            // 顺子当中最大值减最小值, 差值是4
            if (max.getCode() - min.getCode() == Point.FOUR.getCode()) {
                return true;
            }
        }
        // 顺子特殊情况 A2345
        return new HashSet<>(points).containsAll(Point.minStraight());
    }


    /**
     * 获取顺子最大值
     *
     * @param points 牌, 从大到小排序
     * @return Point
     * @author zheng
     */
    public static Point getStraightMaxPoint(List<Point> points) {
        // 多的牌的数量(决定循环几次),从大到小排序
        int surplus = points.size() - Point.FOUR.getCode();
        // 从大到小排序, 从大开始算,因为要获取顺子中最大的牌
        // point = 5 : max = 0 , min = 5 - 1
        // point = 6 : max = 0 , min = 6 - 2  /   max = 1 , min = 6 - 1
        // point = 7 : max = 0 , min = 7 - 3  /   max = 1 , min = 7 - 2  /   max = 2, min = 7 - 1
        for (int i = 0; i < surplus; i++) {
            Point min = points.get(points.size() - surplus + i);
            Point max = points.get(i);
            if (max.getCode() - min.getCode() == Point.FOUR.getCode()) {
                return max;
            }
        }
        if (new HashSet<>(points).containsAll(Point.minStraight())) {
            return Point.FIVE;
        }
        throw new ComputeException("计算顺子最大数异常: flush : " + points);
    }
}
