package pe.com.johnson.pablo.limago.ui.landing;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

import io.realm.Realm;
import pe.com.johnson.pablo.limago.models.District;
import pe.com.johnson.pablo.limago.ui.common.LimaGoPresenter;
import pe.com.johnson.pablo.limago.utils.PreferencesManager;
import pe.com.johnson.pablo.limago.utils.RealmClient;

/**
 * Created by Pablo on 31/08/16.
 */
public class LandingPresenter extends LimaGoPresenter<LandingView> {

    protected LandingPresenter(LandingView view) {
        super(view);
    }

    public void loadInitialData(InputStream is) throws IOException {
        view.showProgressDialog();
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        String json = new String(buffer, "UTF-8");
        Type listType = new TypeToken<ArrayList<District>>() {
        }.getType();
        final ArrayList<District> districtList = new GsonBuilder().create().fromJson(json, listType);
        RealmClient.getRealmClient().executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(districtList);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                PreferencesManager.get().setDataLoaded(true);
                view.dismissProgressDialog();
            }
        });
    }
}
