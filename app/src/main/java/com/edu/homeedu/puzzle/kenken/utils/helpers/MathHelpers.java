package com.edu.homeedu.puzzle.kenken.utils.helpers;

import java.math.BigDecimal;
import java.util.stream.IntStream;
import java.util.NoSuchElementException;

/**
 * Utility class providing helper methods for mathematical operations.
 * This class cannot be instantiated.
 */
public final class MathHelpers {
    /**
     * Private constructor to prevent instantiation.
     */
    private MathHelpers() {}

    /**
     * Returns the maximum value among the given integers.
     *
     * @param first the first integer
     * @param others additional integers to compare
     * @return the maximum value among the given integers
     * @throws NoSuchElementException if no values are provided
     */
    public static int max(int first, int... others) {
        return IntStream.concat(IntStream.of(first), IntStream.of(others)).max().orElseThrow();
    }

    /**
     * Multiplies a double value with a BigDecimal value.
     *
     * @param a the double value
     * @param b the BigDecimal value
     * @return the result of the multiplication as a BigDecimal
     */
    public static BigDecimal multiply(double a, BigDecimal b) {
        return BigDecimal.valueOf(a).multiply(b);
    }

    /**
     * Compares two BigDecimal values for equality.
     *
     * @param a the first BigDecimal value
     * @param b the second BigDecimal value
     * @return true if the two BigDecimal values are equal, false otherwise
     */
    public static boolean equals(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) == 0;
    }
}
