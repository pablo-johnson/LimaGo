package pe.com.johnson.pablo.limago.helpers;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import pe.com.johnson.pablo.limago.LimaGoApplication;

/**
 * @author Pablo Johnson (pablo.88j@gmail.com)
 */
public final class RealmClientHelper {

    private static Realm realm;

    public RealmClientHelper(Application application) {
        // Create a RealmConfiguration that saves the Realm file in the app's "files" directory.
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(application)
                .deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfig);
        realm = Realm.getDefaultInstance();
    }

    public Realm getRealmClient() {
        return realm;
    }
}
