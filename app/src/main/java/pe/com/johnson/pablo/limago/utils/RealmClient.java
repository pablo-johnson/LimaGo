package pe.com.johnson.pablo.limago.utils;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import pe.com.johnson.pablo.limago.LimaGoApplication;

/**
 * @author Pablo Johnson
 */
public final class RealmClient {

    private static Realm realm;

    private RealmClient() {
        // Create a RealmConfiguration that saves the Realm file in the app's "files" directory.
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(LimaGoApplication.getContext())
                .deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfig);
        realm = Realm.getDefaultInstance();
    }

    public static Realm getRealmClient() {
        if (realm == null) {
            new RealmClient();
        }
        return realm;
    }
}
