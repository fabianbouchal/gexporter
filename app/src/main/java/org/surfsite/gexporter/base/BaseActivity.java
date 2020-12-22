package org.surfsite.gexporter.base;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.tracks.exporter.R;

/**
 * Extend every Activity in this project from this BaseActivity. The BaseActivity will handle
 * everything needed for dark mode / light mode (setting theme, changing theme, etc.)
 */
public class BaseActivity extends AppCompatActivity {

    private static final String PREF_KEY_DARK_MODE = "pref_key_dark_mode";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setAppTheme(isDarkModeEnabled());
    }

    private void setAppTheme(boolean isDarkModeEnabled) {
        if (isDarkModeEnabled) {
            setTheme(R.style.AppThemeDark);
        } else {
            setTheme(R.style.AppTheme);
        }
    }

    public boolean isDarkModeEnabled() {
        return PreferenceManager
                .getDefaultSharedPreferences(this)
                .getBoolean(PREF_KEY_DARK_MODE, false);
    }

    public void updateUiMode(boolean setDarkModeEnabled) {
        if (setDarkModeEnabled == isDarkModeEnabled()) {
            // settings not changed -> return, since there's nothing to do here
            return;
        }

        setAppTheme(setDarkModeEnabled);
        updateDarkModeSettingsInSharedPrefs(setDarkModeEnabled);

        // recreate triggers the UI to be rebuilt in order to apply the new theme
        recreate();
    }

    private void updateDarkModeSettingsInSharedPrefs(boolean setDarkModeEnabled) {
        PreferenceManager
                .getDefaultSharedPreferences(this)
                .edit()
                .putBoolean(PREF_KEY_DARK_MODE, setDarkModeEnabled)
                .apply();
    }
}
