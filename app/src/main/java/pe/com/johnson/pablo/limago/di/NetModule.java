package pe.com.johnson.pablo.limago.di;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import pe.com.johnson.pablo.limago.helpers.DefaultSharedPreferencesHelper;
import pe.com.johnson.pablo.limago.interfaces.SharedPreferencesHelper;
import pe.com.johnson.pablo.limago.helpers.RealmClientHelper;

/**
 * @author Pablo Johnson (pablo.88j@gmail.com)
 */
@Module
public class NetModule {

    String mBaseUrl;

    // Constructor needs one parameter to instantiate.
    public NetModule(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    // Dagger will only look for methods annotated with @Provides
    @Provides
    @Singleton
    // Application reference must come from AppModule.class
    SharedPreferencesHelper providesSharedPreferences(Application application) {
        return new DefaultSharedPreferencesHelper(application);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    Realm provideRealmClient(Application application) {
        return new RealmClientHelper(application).getRealmClient();
    }
}
