package pe.com.johnson.pablo.limago.utils;

import android.content.Context;
import android.content.SharedPreferences;

import pe.com.johnson.pablo.limago.LimaGoApplication;

/**
 * @author Pablo Johnson (pablo.88j@gmail.com)
 */
public final class PreferencesManager {

    private static final String DATA_LOADED_KEY = "dataLoadedKey";
    private static PreferencesManager preferencesManager;

    private SharedPreferences sharedPreferences;

    private PreferencesManager() {
        sharedPreferences = LimaGoApplication.getContext().getSharedPreferences("lima_go_pref", Context.MODE_PRIVATE);
    }

    public static synchronized PreferencesManager get() {
        if (preferencesManager == null) {
            preferencesManager = new PreferencesManager();
        }
        return preferencesManager;
    }

    public void setDataLoaded(boolean loaded) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(DATA_LOADED_KEY, loaded);
        editor.apply();
    }

    public boolean isDataLoaded() {
        return sharedPreferences.getBoolean(DATA_LOADED_KEY, false);
    }

}
