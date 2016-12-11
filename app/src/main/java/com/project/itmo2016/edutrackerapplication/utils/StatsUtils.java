package com.project.itmo2016.edutrackerapplication.utils;

import com.project.itmo2016.edutrackerapplication.R;

/**
 * Created by Aleksandr Tukallo on 08.12.16.
 */

/**
 * Class with utils for working with Stats for barChart in StatsActivity
 */
public final class StatsUtils {

    private final static class Pair {
        final float yValue;
        final int correspondingColor;

        Pair(float yValue, int correspondingColor) {
            this.yValue = yValue;
            this.correspondingColor = correspondingColor;
        }
    }

    /**
     * Array for storing yValues and corresponding colors
     */
    private static final Pair[] colorsForValues = new Pair[]{
            new Pair(0f, R.color.attendance_grade_1),
            new Pair(0.166f, R.color.attendance_grade_2),
            new Pair(0.333f, R.color.attendance_grade_3),
            new Pair(0.493f, R.color.attendance_grade_4),
            new Pair(0.665f, R.color.attendance_grade_5),
            new Pair(0.831f, R.color.attendance_grade_6),
            new Pair(1.0f, R.color.attendance_grade_7)};

    /**
     * Method looks for, closest to yValue, Pair.yValue in array.
     * It is done in O(n) time, can be optimized with search tree.
     *
     * @param yValue
     * @return Pair with closest yValue to parameter is returned.
     */
    private static Pair getClosestPairToYValue(float yValue) {

        for (int i = 1; i < colorsForValues.length; i++) {
            if (colorsForValues[i - 1].yValue <= yValue && colorsForValues[i].yValue >= yValue) {
                if (Math.abs(colorsForValues[i - 1].yValue - yValue) < Math.abs(colorsForValues[i].yValue - yValue))
                    return colorsForValues[i - 1];
                else return colorsForValues[i];
            }
        }

        //if yValue is not in range (it must never happen), still closest is returned
        if (yValue < colorsForValues[0].yValue)
            return colorsForValues[0];
        else return colorsForValues[colorsForValues.length - 1];
    }

    /**
     * Method returns a color for bar with given height
     *
     * @param yValue is a height of a bar
     * @param shift  is a shift to show bars with zero attendance. It is needed, because methods in this
     *               class suppose, that yValue is in [0;1] segment
     * @return color is returned
     */
    public static int getColorForYValue(float yValue, float shift) {
        return getClosestPairToYValue(yValue - shift).correspondingColor;
    }
}