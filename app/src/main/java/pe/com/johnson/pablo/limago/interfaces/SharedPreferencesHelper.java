package pe.com.johnson.pablo.limago.interfaces;

/**
 * Created by pjohnson on 28/01/17.
 */

public interface SharedPreferencesHelper {
    void clear();
    boolean isInitialDataLoaded();
    void setInitialDataLoaded();
}
