package com.edu.homeedu.puzzle.kenken.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import java.lang.Math;

/**
 * Utility class for various conversions used in the KenKen puzzle application.
 */
public final class Converters {
    /**
     * Private constructor to prevent instantiation.
     */
    private Converters() {}

    /**
     * The first uppercase character, used as a reference point for conversions.
     */
    private static final char FIRST_UPPER_CHAR = 'A';

    /**
     * Converts an uppercase character to its ordinal value.
     *
     * @param upperChar The uppercase character to be converted.
     * @return The ordinal value of the uppercase character.
     */
    public static int upperCharToOrdinal(char upperChar) {
        return upperChar - FIRST_UPPER_CHAR + 1;
    }

    /**
     * Converts an ordinal value to its corresponding uppercase character.
     *
     * @param ordinal The ordinal value to be converted.
     * @return The uppercase character corresponding to the ordinal value.
     */
    public static char ordinalToUpperChar(int ordinal) {
        return Character.toString((char) (ordinal - 1 + FIRST_UPPER_CHAR)).charAt(0);
    }

    /**
     * Converts a value in dp (density-independent pixels) to pixels as a floating point number.
     *
     * @param context The context to access the resources and device-specific display metrics.
     * @param dp The value in dp to be converted.
     * @return The converted value in pixels as a float.
     */
    public static float dpToPxF(Context context, float dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
    }

    /**
     * Converts a value in dp (density-independent pixels) to pixels as an integer.
     *
     * @param context The context to access the resources and device-specific display metrics.
     * @param dp The value in dp to be converted.
     * @return The converted value in pixels as an integer.
     */
    public static int dpToPx(Context context, int dp) {
        return Math.round(dpToPxF(context, dp));
    }
}
