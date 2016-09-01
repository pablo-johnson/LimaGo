package pe.com.johnson.pablo.limago;

import android.app.Application;
import android.content.Context;

/**
 * Created by Pablo on 31/08/16.
 */
public class LimaGoApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
