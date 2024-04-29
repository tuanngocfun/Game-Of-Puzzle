package com.edu.homeedu.puzzle.kenken.ui.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;
import com.edu.homeedu.puzzle.kenken.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        SwitchPreferenceCompat themeSwitch = findPreference(getString(R.string.preference_theme));
        if (themeSwitch != null) {
            int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
            boolean isSystemInDarkTheme = currentNightMode == Configuration.UI_MODE_NIGHT_YES;

            themeSwitch.setChecked(isSystemInDarkTheme);
            updateThemeSwitchSummary(themeSwitch, isSystemInDarkTheme);

            themeSwitch.setOnPreferenceChangeListener((preference, newValue) -> {
                boolean enableDarkTheme = (Boolean) newValue;
                AppCompatDelegate.setDefaultNightMode(
                        enableDarkTheme ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
                );
                updateThemeSwitchSummary(themeSwitch, enableDarkTheme);
                return true;
            });
        }
    }

    private void updateThemeSwitchSummary(SwitchPreferenceCompat switchPref, boolean isDarkThemeEnabled) {
        switchPref.setSummary(isDarkThemeEnabled ? R.string.summary_enable_light_theme : R.string.summary_enable_dark_theme);
    }
}
