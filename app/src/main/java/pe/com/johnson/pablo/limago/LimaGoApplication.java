package pe.com.johnson.pablo.limago;

import android.app.Application;
import android.content.Context;

import pe.com.johnson.pablo.limago.di.AppModule;
import pe.com.johnson.pablo.limago.di.DaggerNetComponent;
import pe.com.johnson.pablo.limago.di.NetComponent;
import pe.com.johnson.pablo.limago.di.NetModule;


/**
 * Created by Pablo on 31/08/16.
 */
public class LimaGoApplication extends Application {

    private NetComponent mNetComponent;

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initializeInjector();
    }

    private void initializeInjector() {
        // Dagger%COMPONENT_NAME%
        mNetComponent = DaggerNetComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .netModule(new NetModule("https://api.github.com"))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

    public static Context getContext() {
        return context;
    }
}
