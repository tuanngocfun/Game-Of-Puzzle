package com.edu.homeedu.puzzle.kenken.utils.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

/**
 * Utility class for managing shared preferences.
 */
public final class PreferenceHelpers {
    // Private constructor to prevent instantiation
    private PreferenceHelpers() {}

    /**
     * Retrieves a boolean value from the shared preferences.
     *
     * @param context The context to use for accessing shared preferences.
     * @param resId The resource ID of the preference key.
     * @param defaultValue The default value to return if the preference does not exist.
     * @return The boolean value of the shared preference, or the default value if it does not exist.
     */
    public static boolean getSharedPreference(Context context, int resId, boolean defaultValue) {
        return getSharedPreferences(context).getBoolean(context.getString(resId), defaultValue);
    }

    /**
     * Gets the default shared preferences for the given context.
     *
     * @param context The context to use for accessing shared preferences.
     * @return The default SharedPreferences instance for the given context.
     */
    private static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}