package net.sourceforge.opencamera;

import android.content.SharedPreferences;

import net.sourceforge.opencamera.CameraController.CameraController;
import net.sourceforge.opencamera.CameraController.CameraController2;

/**
 * Created by kyle on 8/7/17.
 */

public class AstroMode {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private final String ISO_ASTRO = "3200";

    public AstroMode(SharedPreferences preferences) {
        this.preferences = preferences;
        this.editor = preferences.edit();
    }

    private void saveCurrentSettings() {
        editor.putString(PreferenceKeys.getPreviousISOPreferenceKey(), preferences.getString(PreferenceKeys.getISOPreferenceKey(), "auto"));
        editor.putLong(PreferenceKeys.getPreviousExposureTimePreferenceKey(), preferences.getLong(PreferenceKeys.getExposureTimePreferenceKey(), CameraController.EXPOSURE_TIME_DEFAULT));
        editor.putString(PreferenceKeys.getPreviousBurstModePreferenceKey(), preferences.getString(PreferenceKeys.getBurstModePreferenceKey(), "1"));
        editor.putString(PreferenceKeys.getPreviousFocusPreferenceKey(), preferences.getString(PreferenceKeys.getFocusPreferenceKey(0, false), "focus_mode_auto"));
        editor.putString(PreferenceKeys.getPreviousFlashPreferenceKey(), preferences.getString(PreferenceKeys.getFlashPreferenceKey(0), "flash_auto"));
        editor.commit();
    }


    public void enable() {
        saveCurrentSettings();
        editor.putString(PreferenceKeys.getPhotoModePreferenceKey(), "preference_photo_mode_astro");
        editor.putLong(PreferenceKeys.getExposureTimePreferenceKey(), CameraController2.MAX_EXPOSURE);
        editor.putString(PreferenceKeys.getISOPreferenceKey(), ISO_ASTRO);
        editor.putString(PreferenceKeys.getBurstModePreferenceKey(), "unlimited");
        editor.putString(PreferenceKeys.getFocusPreferenceKey(0, false), "focus_mode_infinity");
        editor.putString(PreferenceKeys.getFlashPreferenceKey(0), "flash_off");
        editor.commit();
    }

    public void disable() {
        editor.putString(PreferenceKeys.getISOPreferenceKey(), preferences.getString(PreferenceKeys.getPreviousISOPreferenceKey(), "auto"));
        editor.putLong(PreferenceKeys.getExposureTimePreferenceKey(), preferences.getLong(PreferenceKeys.getPreviousExposureTimePreferenceKey(), CameraController.EXPOSURE_TIME_DEFAULT));
        editor.putString(PreferenceKeys.getBurstModePreferenceKey(), preferences.getString(PreferenceKeys.getPreviousBurstModePreferenceKey(), "1"));
        editor.putString(PreferenceKeys.getFocusPreferenceKey(0, false), preferences.getString(PreferenceKeys.getPreviousFocusPreferenceKey(), "focus_mode_auto"));
        editor.putString(PreferenceKeys.getFlashPreferenceKey(0), preferences.getString(PreferenceKeys.getPreviousFlashPreferenceKey(), "flash_auto"));
        editor.commit();
    }


}
