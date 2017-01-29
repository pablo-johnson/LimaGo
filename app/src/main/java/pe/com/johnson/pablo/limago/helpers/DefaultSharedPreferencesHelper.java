package pe.com.johnson.pablo.limago.helpers;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import pe.com.johnson.pablo.limago.interfaces.SharedPreferencesHelper;

/**
 * @author Pablo Johnson (pablo.88j@gmail.com)
 */
public class DefaultSharedPreferencesHelper implements SharedPreferencesHelper {

    private static final String DATA_LOADED_KEY = "dataLoadedKey";

    private final SharedPreferences sharedPreferences;

    public DefaultSharedPreferencesHelper(Application application) {
        this.sharedPreferences = application.getSharedPreferences("lima_go_pref", Context.MODE_PRIVATE);
    }

    @Override
    public void clear() {
        SharedPreferences.Editor editor = editor();
        editor.clear();
        editor.apply();
    }

    private  SharedPreferences.Editor editor() {
        return sharedPreferences.edit();
    }

    @Override
    public boolean isInitialDataLoaded() {
        return sharedPreferences.getBoolean(DATA_LOADED_KEY, false);
    }

    @Override
    public void setInitialDataLoaded() {
        editor().putBoolean(DATA_LOADED_KEY, true).apply();
    }
}
